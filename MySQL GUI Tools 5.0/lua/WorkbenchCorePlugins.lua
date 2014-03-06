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
-- @file WorkbenchCorePlugins.lua
-- @brief Module that contains plugins for the Workbench
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
  local moduleInfo= {
    name= "WorkbenchCorePlugins", 
    functions= {
      "getPluginInfo::",
      "checkColumnNameTypeConsistency::",
      "copyTableColumnNames::",
      "addTablePrefix::"
    }, 
    extends= "PluginInfo"
  }

  return moduleInfo
end

-- ----------------------------------------------------------------------------------------
-- @brief Returns information about the plugins available in this module
--
--   Used by the application to get information about the plugins stored in this module
--
-- @return 1 on success or an error
-- ----------------------------------------------------------------------------------------
function getPluginInfo(args)
  local pluginList= grtV.newList("dict", "base.Plugin")
  local plugin
  
  plugin= grtV.newObj("base.Plugin", "checkColumnNameTypeConsistency", 
    "plugin://com.mysql.grt.base.Plugin.checkColumnNameTypeConsistency", "")
  plugin.caption= _("Check Column Name/Datatype Consistance")
  plugin.description= _("Checks if all columns with the same name also use the same datatype.")
  plugin.groupPath= "Catalog/Checks"
  plugin.moduleName= "WorkbenchCorePlugins"
  plugin.moduleFunctionName= "checkColumnNameTypeConsistency"
  plugin.categories= {"database"}
  plugin.objectStructNames= {}
  plugin.singleArgument= 1
  grtV.insert(pluginList, plugin)
  
  plugin= grtV.newObj("base.Plugin", "copyTableColumnNames", 
    "plugin://com.mysql.grt.base.Plugin.copyTableColumnNames", "")
  plugin.caption= _("Copy Table Columns")
  plugin.description= _("Copies all table column names to the clipboard")
  plugin.groupPath= "Table"
  plugin.moduleName= "WorkbenchCorePlugins"
  plugin.moduleFunctionName= "copyTableColumnNames"
  plugin.categories= {"database"}
  plugin.objectStructNames= {"db.workbench.TableElement"}
  plugin.singleArgument= 1
  grtV.insert(pluginList, plugin)

  plugin= grtV.newObj("base.Plugin", "addTablePrefix", 
    "plugin://com.mysql.grt.base.Plugin.addTablePrefix", "")
  plugin.caption= _("Add Table Prefix")
  plugin.description= _("Adds a Prefix to selected Tables")
  plugin.groupPath= "Table"
  plugin.moduleName= "WorkbenchCorePlugins"
  plugin.moduleFunctionName= "addTablePrefix"
  plugin.categories= {"database"}
  plugin.objectStructNames= {"db.workbench.TableElement"}
  plugin.singleArgument= 1
  grtV.insert(pluginList, plugin)
  
  return grt.success(pluginList)
end

-- ----------------------------------------------------------------------------------------
-- @brief Plugin to copy the column names to the clipboard
--
--   Takes a table and copies its columns to the clipboard
--
-- @param list of db.workbench.TableElement's to operate on
--
-- @return 1 on success
-- ----------------------------------------------------------------------------------------
function copyTableColumnNames(args)
  local tblElements= args[1]
  local tbl
  local i
  local s= ""
  
  for i= 1, grtV.getn(tblElements) do
    if (grtS.get(tblElements[i]) ~= "db.workbench.TableElement") then
      return grt.error(string.format(_("Wrong struct type of element %d", i)))
    end
  end
  
  for i= 1, grtV.getn(tblElements) do
    tbl= tblElements[i].table
    for i= 1, grtV.getn(tbl.columns) do
      local column= tbl.columns[i]
      
      if s ~= "" then
        s= s .. ", "
      end
      s= s .. grtV.toLua(column.name)
    end
  end
  
  Base:copyToClipboard({s})
  
  print(_("The table columns have been copied to the clipboard."))
  
  return grt.success(1)
end


-- ----------------------------------------------------------------------------------------
-- @brief Plugin to check if all columns with the same name have the same datatype
--
--   Goes through all tables of the catalog and checks if all columns with the same name
-- have the same datatype
--
-- @return 1 if check is successful or 0 if not
-- ----------------------------------------------------------------------------------------
function checkColumnNameTypeConsistency(args)
  local catalog= grtV.getGlobal("/workbench/catalog")
  
  local i
  local columnTypes= {}
  local columnDiffs= {}
  
  print(_("Checking the catalog for column name/type consitency."))
  
  -- do for all schemata
  for i= 1, grtV.getn(catalog.schemata) do
    local schema= catalog.schemata[i]
    local j
    
    -- do for all tables
    for j= 1, grtV.getn(schema.tables) do
      local tbl= schema.tables[j]
      local k
      
      -- do for all columns
      for k= 1, grtV.getn(tbl.columns) do
        local col= tbl.columns[k]
        local colName= grtV.toLua(col.name)
        local colInfo= columnTypes[colName]
        
        -- check if a column with the same name exists already
        if colInfo == nil then
          columnTypes[colName]= {schema, tbl, col}
        else
          otherCol= colInfo[3]
          
          local colDatatype= grtV.toLua(DbUtils:getColumnDatatypeAsString({col}))
          local otherColDatatype= grtV.toLua(DbUtils:getColumnDatatypeAsString({colInfo[3]}))
          
          -- if the datatype does not match, add the columns to the list in to format
          -- "schema.table.column datatype != schema.table.column datatype"
          if colDatatype ~= otherColDatatype then
            table.insert(columnDiffs, 
              string.format("%s.%s.%s %s != %s.%s.%s %s",
              grtV.toLua(schema.name), grtV.toLua(tbl.name), colName, colDatatype,
              grtV.toLua(colInfo[1].name), grtV.toLua(colInfo[2].name), grtV.toLua(colInfo[3].name),
              otherColDatatype))
          end
        end
      end
    end
  end
  
  if table.getn(columnDiffs) == 0 then
    print(_("All columns are in an consitent state."))
    
    return grt.success(1)
  else
    print(_("Not all columns are in an consitent state."))
    print(_("The mismatches are:"))
    
    print("")
    
    table.foreach(columnDiffs, print)
    
    return grt.success(0)
  end
end


-- ----------------------------------------------------------------------------------------
-- @brief Adds a prefix to tabales
--
--   Adds a prefix to tabales
--
-- @param list of db.workbench.TableElement's to operate on
--
-- @return 1 on success
-- ----------------------------------------------------------------------------------------
function addTablePrefix(args)
  local tblElements= args[1]
  local tbl
  local i
  local s= ""
  local prefix= ""
  local tblElementCount= grtV.getn(tblElements)
  
  if tblElementCount == 0 then
    return grt.success(0)
  end
  
  for i= 1, tblElementCount do
    if (grtS.get(tblElements[i]) ~= "db.workbench.TableElement") then
      return grt.error(string.format(_("Wrong struct type of element %d", i)))
    end
  end
  
  prefix= input(_("Input Prefix for Selected Tables: "))
  
  if prefix == "" then
    return grt.success(0)
  end
  
  for i= 1, tblElementCount do
    tbl= tblElements[i].table
    
    tbl.name= prefix .. grtV.toLua(tbl.name)
  end
  
  print(string.format(_("The prefix %s has been added to %d table(s)."), prefix, tblElementCount))
  
  return grt.success(1)
end

