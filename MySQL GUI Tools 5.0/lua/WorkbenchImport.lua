-- ----------------------------------------------------------------------------------------
-- Copyright (C) 2004 MySQL AB
--
-- This program is free software; you can redistribute it and/or modify
-- it under the terms of the GNU General Public License as published by
-- the Free Software Foundation; either version 2 of the License, or
-- (at your option) any later version.
--
-- This program is distributed in the hope that it will be useful,
-- but WITHOUT ANY WARRANTY; without even the implied warranty of
-- MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
-- GNU General Public License for more details.
--
-- You should have received a copy of the GNU General Public License
-- along with this program; if not, write to the Free Software
-- Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
-- ----------------------------------------------------------------------------------------


-- ----------------------------------------------------------------------------------------
-- @file WorkbenchImport.lua
-- @brief Module that imports docs for MySQL Workbench
-- ----------------------------------------------------------------------------------------


-- ----------------------------------------------------------------------------------------
-- @brief Returns the information about this module
--
--   Every Grt module has to implement this function to return information about the 
-- module. Note that new functions that should be exposed to the Grt have to be listed 
-- here. Function that are not exposed should start with a underscore.
--
-- @return A dict that contains the name and the function names of the module
-- ----------------------------------------------------------------------------------------

function getModuleInfo()
  local moduleInfo= 
    {
      name= "WorkbenchImport", 
      functions= {
        -- workbench commands
        "importDbd4::"
      }, 
      extends= ''
    }

  return moduleInfo
end

-- ----------------------------------------------------------------------------------------
-- @brief Impoarts a DBDesigner4 model file
--
-- @return success or error if file does not exist
-- ----------------------------------------------------------------------------------------
function importDbd4(args)
  local filename= grtV.toLua(args[1])
  local p
  local xF= 1.5
  local yF= 2
  
  local callbacks= {
    StartElement = function (parser, name, attr)

      if name == "DATATYPE" then
        local datatypes= grtV.getGlobal("/rdbmsMgmt/rdbms/4/simpleDatatypes")
        local datatype= datatypes[4]
        local i
        
        for i= 1, grtV.getn(datatypes) do
          if grtV.toLua(datatypes[i].name) == attr.TypeName then
            datatype= datatypes[i]
            break
          end
        end
      
        table.insert(WorkbenchImport.datatypeMappings, datatype)
        
      elseif name == "REGION" then
        local colorId= tonumber(attr.RegionColor)
        local color= "blue"
        
        if colorId == 0 then
          color= "red"
        elseif colorId == 1 then
          color= "yellow"
        elseif colorId == 2 then
          color= "green"
        elseif colorId == 3 then
          color= "cyan"
        elseif colorId == 5 then
          color= "magenta"
        end
        
        Workbench:placeNewLayerElement({tonumber(attr.XPos) * xF, tonumber(attr.YPos) * yF, 
          tonumber(attr.Width) * xF, tonumber(attr.Height) * yF, attr.RegionName, color})
          
      elseif name == "TABLE" then
        local schema= grtV.getGlobal("/workbench/catalog/schemata/0")
        local elem
        
        elem= Workbench:placeNewTableElement({tonumber(attr.XPos) * xF, tonumber(attr.YPos) * yF,
          attr.Tablename, schema})
      
        elem.expanded= (tonumber(attr.Collapsed) == 1) and 0 or 1
        
        -- store current element / object for sub objects 
        WorkbenchImport.currentElement= elem
        WorkbenchImport.currentObject= elem.table
        
        -- remember ID/table mapping
        WorkbenchImport.tableIds[attr.ID]= elem.table
        
      elseif name == "COLUMN" then
        local tbl= WorkbenchImport.currentObject
        local col= grtV.newObj("db.mysql.Column", attr.ColName, "", grtV.toLua(tbl._id))
        local datatypeId= tonumber(attr.idDatatype)
        
        col.isNullable= (tonumber(attr.NotNull) == 1) and 0 or 1
        col.autoIncrement= tonumber(attr.AutoInc)
        col.comment= attr.Comments        
        col.defaultValue= attr.DefaultValue
                
        -- set datatype
        if datatypeId <= table.getn(WorkbenchImport.datatypeMappings) then
          col.simpleType= WorkbenchImport.datatypeMappings[datatypeId]
        else
          col.simpleType= grtV.getGlobal("/rdbmsMgmt/rdbms/4/simpleDatatypes/4")
        end        
        col.datatypeName= col.simpleType.name
        
        if attr.DatatypeParams ~= "" then
          col.datatypeExplicitParams= attr.DatatypeParams
        end
        
        grtV.insert(tbl.columns, col)
        
        -- remember idColumn/column mapping
        WorkbenchImport.columnIds[attr.ID]= col
        
      elseif name == "INDEX" then
        local tbl= WorkbenchImport.currentObject
        local index= grtV.newObj("db.mysql.Index", attr.IndexName, "", grtV.toLua(tbl._id))
        local indexType= tonumber(attr.IndexKind)
        
        if (indexType == 0) or (attr.IndexName == "PRIMARY") then
          index.indexType= "PRIMARY"
          index.isPrimary= 1
          index.unique= 1
          
          tbl.primaryKey= index
        elseif indexType == 2 then
          index.indexType= "UNIQUE"
        elseif indexType == 3 then
          index.indexType= "FULLTEXT"
        else
          index.indexType= "INDEX"
        end
        
        grtV.insert(tbl.indices, index)
        
        -- store sub object
        WorkbenchImport.currentSubObject= index
        
      elseif name == "INDEXCOLUMN" then
        local index= WorkbenchImport.currentSubObject
        local col= WorkbenchImport.columnIds[attr.idColumn]
        
        -- skip primary key
        if (index == nil) or (col == nil) then
          return
        end
        
        indexCol= grtV.newObj("db.mysql.IndexColumn", grtV.toLua(col.name), "", grtV.toLua(index._id))
        indexCol.referedColumn= col
        
        if tonumber(attr.LengthParam) > 0 then
          indexCol.columnLength= tonumber(attr.LengthParam)
        end
        
        grtV.insert(index.columns, indexCol)
        
      elseif name == "RELATION" then
        local tbl= WorkbenchImport.tableIds[attr.DestTable]
        local refTbl= WorkbenchImport.tableIds[attr.SrcTable]
        local i
        
        if (tbl == nil) or (refTbl == nil) then
          return
        end
        
        local fk= grtV.newObj("db.mysql.ForeignKey", attr.RelationName, "", grtV.toLua(tbl._id))
        
        fk.referedTable= refTbl
        fk.comment= attr.Comments
        
        colMaps= grt.split(attr.FKFields, "\\n")
        for i= 1, table.getn(colMaps) do
          local col
          local refCol
          
          colMap= grt.split(colMaps[i], "=")
          
          if (colMap[1] ~= nil) and (colMap[1] ~= "") and 
            (colMap[2] ~= nil) and (colMap[2] ~= "")then               
            col= grtV.getListItemByObjName(tbl.columns, colMap[2])
            refCol= grtV.getListItemByObjName(refTbl.columns, colMap[1])
            
            if (col ~= nil) and (refCol ~= nil) then
              grtV.insert(fk.columns, col._id)
              grtV.insert(fk.referedColumns, refCol._id)
            end
          end
        end
        
        grtV.insert(tbl.foreignKeys, fk)
      
      elseif name == "NOTE" then
        local text= string.gsub(attr.NoteText, "\\n", "\n")
        text= string.gsub(text, "\\a", "'")
        
        Workbench:placeNewNoteElement({tonumber(attr.XPos) * xF, tonumber(attr.YPos) * yF,
          150, 50, "yellow", text})
          
      end
    end,
    EndElement = function (parser, name)
      if name == "TABLE" then
        WorkbenchImport.currentElement= nil
        WorkbenchImport.currentObject= nil
        
      elseif name == "INDEX" then
        WorkbenchImport.currentSubObject= nil
        
      end
    end
  }
  
  WorkbenchImport.datatypeMappings= {}
  WorkbenchImport.tableIds= {}
  WorkbenchImport.columnIds= {}
  
  WorkbenchImport.currentElement= nil
  WorkbenchImport.currentObject= nil
  WorkbenchImport.currentSubObject= nil
  
  
  if not grt.fileExists(filename) then
    grt.error(string.format(_("The file %s does not exist."), filename))
  end
  
  p = lxp.new(callbacks)
  
  for line in io.lines(filename) do
    p:parse(line)
  end
  p:parse()
  p:close()
  
  -- trigger generation of relationships
  Workbench:createRelationshipsFromFKs({grtV.getGlobal("/workbench/model").currentView})
  
  collectgarbage()

  return grt.success()
end

