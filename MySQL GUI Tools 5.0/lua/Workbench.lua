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
-- @file Workbench.lua
-- @brief Module that contains program logic for MySQL Workbench
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
      name= "Workbench", 
      functions= {
        -- workbench commands
        "initWorkbench::", 
        "shutdownWorkbench::",
        "registerEditors::",
        "registerPlugins::",
        "newDocument::",
        "loadDocumentData::",
        "clearDocument::",

        "createModelForSchema::",
        
        -- element placement
        "placeNewTableElement::",
        "placeNewViewElement::",
        "placeNewRoutineGroupElement::",
        "placeNewNoteElement::",
        "placeNewImageElement::",
        "placeNewLayerElement::",
        "placeElementFromObject::",
        
        "relationshipStart::",
        "relationshipClickedPoint::",
        "relationshipCancel::",
        
        "getColorPresets::",
                
        -- layouting
        "verticalAlign::",
        "horizontalAlign::",
        "autoSpaceElements::",

        "centerElementsAt::",
        
        -- others
        "arrangeObjectsOnCurrentView::",
        "arrangeSchemaOnNewView::",
        "arrangeSchemaOnCurrentView::",
        "loadAndAutoArrange::",
        "loadAndAutoArrangeInNewView::",
        "addView",
        "deleteObjectAtPoint::",
        "deleteSelection::",
        "deleteRelationship::",
        "selectLayer::",
        "addRoutine::",
        "createTrigger::",
        "raiseObjectAtPoint::",
        "selectAll::",
        "bringSelectionToFront::",
        "sendSelectionToBack::",
        
        "collapseElements::",
        "expandElements::",

        "deleteFKsWithTable::",
        "createRelationshipsFromFKs::",
        
        "addPluginToAppPluginGroups::",
        
        -- synchronization aux
        "collectSqlScript::",
        
        -- export
        "generateSqlCreateScript::"
      }, 
      extends= ''
    }

  return moduleInfo
end


-- ----------------------------------------------------------------------------------------
-- @brief Initalizes the application
--
--   Creates 
--
-- @return success
-- ----------------------------------------------------------------------------------------
function initWorkbench(args) 
  -- get infos about all available RDBMS
  local rdbmsMgmt= RdbmsManagement:getManagementInfo()
  grtV.setGlobal("/rdbmsMgmt", rdbmsMgmt)
  
  -- create main workbench object
  local workbench= grtV.newObj("db.workbench.Workbench", "Workbench", "", "")
  grtV.setGlobal("/workbench", workbench)
  
  -- set the model version
  workbench.version= 2
  
  -- register plugins
  registerPlugins({{"database", "workbench"}})

  -- free cached GRT values on Lua side
  collectgarbage()
  
  return grt.success()
end


-- ----------------------------------------------------------------------------------------
-- @brief Performs actions when the Workbench is shut down
--
--   Performs several actions shortly before the application is shut down
--
-- @return 1 on success or an error
-- ----------------------------------------------------------------------------------------
function shutdownWorkbench(args)
  RdbmsManagement:storeConns({grtV.getGlobal("/rdbmsMgmt/storedConns")})
  
  return grt.success()
end

-- ----------------------------------------------------------------------------------------
-- @brief Registers GUI editors for objects
--
--   Registers several editors that will be used when Base:editObj() is called
--
-- @return 1 on success or an error
-- ----------------------------------------------------------------------------------------
function registerEditors(args)
  local app= grtV.getGlobal("/app")
  local EditorList= app.editors
  local Editor
  local EDIT_ACTION=1001
  local LOCK_ACTION=1002
  local PLUGINS_ACTION=1003
  
  if EditorList == nil then
    EditorList= grtV.newList("dict", "base.ObjectEditor")
    grtV.setGlobal("/app/editors", EditorList)
  end
  
  -- db.Schema
  Editor= grtV.newObj("base.ObjectEditor", "SchemaEditor", "editor://com.mysql.grt.db.Schema", "")
  Editor.moduleName= "WorkbenchUi"
  Editor.moduleFunctionName= "editSchema"
  Editor.objectStructName= "db.Schema"
  Editor.rating= 1
  Editor.actionId= EDIT_ACTION
  grtV.insert(EditorList, Editor)
  
  -- db.Table
  Editor= grtV.newObj("base.ObjectEditor", "TableEditor", "editor://com.mysql.grt.db.Table", "")
  Editor.moduleName= "WorkbenchUi"
  Editor.moduleFunctionName= "editTable"
  Editor.objectStructName= "db.Table"
  Editor.rating= 1
  Editor.actionId= EDIT_ACTION
  grtV.insert(EditorList, Editor)
  
  -- db.View
  Editor= grtV.newObj("base.ObjectEditor", "ViewEditor", "editor://com.mysql.grt.db.View", "")
  Editor.moduleName= "WorkbenchUi"
  Editor.moduleFunctionName= "editView"
  Editor.objectStructName= "db.View"
  Editor.rating= 1
  Editor.actionId= EDIT_ACTION
  grtV.insert(EditorList, Editor)

  -- db.Routine
  Editor= grtV.newObj("base.ObjectEditor", "RoutineEditor", "editor://com.mysql.grt.db.Routine", "")
  Editor.moduleName= "WorkbenchUi"
  Editor.moduleFunctionName= "editRoutine"
  Editor.objectStructName= "db.Routine"
  Editor.rating= 1
  Editor.actionId= EDIT_ACTION
  grtV.insert(EditorList, Editor)
  
  -- db.RoutineGroup
  Editor= grtV.newObj("base.ObjectEditor", "RoutineGroupEditor", "editor://com.mysql.grt.db.RoutineGroup", "")
  Editor.moduleName= "WorkbenchUi"
  Editor.moduleFunctionName= "editRoutineGroup"
  Editor.objectStructName= "db.RoutineGroup"
  Editor.rating= 1
  Editor.actionId= EDIT_ACTION
  grtV.insert(EditorList, Editor)
  
  Editor= grtV.newObj("base.ObjectEditor", "PluginsMenuHandler", "editor://com.mysql.grt.PluginsMenu", "")
  Editor.moduleName= "WorkbenchUi"
  Editor.moduleFunctionName= "popupPluginsMenu"
  Editor.objectStructName= "db.DatabaseObject"
  Editor.rating= 1
  Editor.actionId= PLUGINS_ACTION
  grtV.insert(EditorList, Editor)

  return grt.success(EditorList)
end

-- ----------------------------------------------------------------------------------------
-- @brief 
--
-- @param path
-- @param @pluginId 
--
-- @return 1 on success or an error
-- ----------------------------------------------------------------------------------------

function addPluginToAppPluginGroups(args)
  local currentGroupList
  local currentGroup
  local path
  local i
  local p
  local app= grtV.getGlobal("/app")
  
  path= grtV.toLua(args[1])
  id= grtV.toLua(args[2])
  
  -- if app.pluginHierachy == nil then
  --  grtV.setGlobal("/app/pluginHierarchy", grtV.newList("dict", "base.PluginGroup"))
  -- end

  currentGroupList= grtV.getGlobal("/app/pluginGroups")
  currentGroup= nil

  -- ensure the subtree for the path exists
  
  p = 1
  for i= 1, string.len(path) do
    if ((string.sub(path, i, i) == "/") or (i == string.len(path))) and (i > p) then
      local subGroupName
      
      if(string.sub(path, i, i) == "/") then
        subGroupName = string.sub(path, p, i-1)
      else
        subGroupName = string.sub(path, p, i)
      end
      
      local subGroup= grtV.getListItemByObjName(currentGroupList, subGroupName)
      
      if(subGroup == nil) then
        subGroup= grtV.newObj("base.PluginGroup", subGroupName, "", "");
        grtV.insert(currentGroupList, subGroup)
        -- grtV.insert(app.pluginGroups, subGroup)
      end
      
      currentGroup= subGroup
      currentGroupList= subGroup.subGroups
      p = i + 1
    end
  end
  
  if currentGroup ~= nil then
    if currentGroup.plugins == nil then
      currentGroup.plugins= grtV.newList("string", "")
    end
    
    -- add ref to the plugin
    grtV.insert(currentGroup.plugins, id)
    
    return grt.success()
  end

  return grt.error("addPluginToAppPluginGroups failed")
end

-- ----------------------------------------------------------------------------------------
-- @brief Scans for and registers plugins
--
--   Gets plugin information from all modules that implement "PluginInfo". The 
-- categoryList parameter is used to filter the plugins so only plugins written for this
-- application are registerd.
--
-- @param categoryList a list of plugin categories that should be registerd
--
-- @return 1 on success or an error
-- ----------------------------------------------------------------------------------------
function registerPlugins(args)
  local categoryList= args[1]
  local categories
  local app= grtV.getGlobal("/app")
  
  if grtV.typeOf(categoryList) == "list" then
    categories= grtV.toLua(categoryList)
  else
    categories= categoryList
  end

  if app.pluginGroups == nil then
    grtV.setGlobal("/app/pluginGroups", grtV.newList("dict", "base.PluginGroup"))
  end
  
  if app.plugins == nil then
    grtV.setGlobal("/app/plugins", grtV.newList("dict", "base.Plugin"))
  end
  
  -- scan all modules that implement PluginInfo
  local modules= grtM.get("PluginInfo")
  local i
  
  -- Call the getPluginInfo function of all modules that extend PluginInfo
  for i= 1, table.getn(modules) do
    local plugins= grtM.callFunction(modules[i], "getPluginInfo")
    local j
    
    for j= 1, grtV.getn(plugins) do
      local plugin= plugins[j]
      local k
      local found= false
      
      -- check if one category of the plugin is in the categoryList
      for k= 1, table.getn(categories) do
        local pluginCategories= plugin.categories
        local l
        
        for l= 1, grtV.getn(pluginCategories) do
          if categories[l] == grtV.toLua(pluginCategories[l]) then
            found= true
            break
          end
        end
        
        if found then        
          break
        end
      end
      
      -- add plugin
      if found then
        grtV.insert(app.plugins, plugin)
        addPluginToAppPluginGroups({plugin.groupPath, plugin._id})
      end
    end
  end
    
  collectgarbage()

  return grt.success()
end

-- ----------------------------------------------------------------------------------------
-- @brief Sets up the workbench model with a new, empty document.
--
--
-- @param rdbmsName name of the db.mgmt.Rdbms to use
--
-- ----------------------------------------------------------------------------------------
function newDocument(args)
  local rdbmsName= (args[1] ~= nil) and grtV.toLua(args[1]) or ""
  if rdbmsName == "" then
    rdbmsName= "Mysql"
  end
  local rdbmsVersion= (args[2] ~= nil) and grtV.toLua(args[2]) or "5.1.6"
  
  -- Find model RDBMS
  local rdbmsMgmt= grtV.getGlobal("/rdbmsMgmt")
  local rdbms= nil
  local i  
  
  for i= 1, grtV.getn(rdbmsMgmt.rdbms) do
    local rdbmsItem= rdbmsMgmt.rdbms[i]
    if (rdbmsItem.name ~= nil) and (grtV.toLua(rdbmsItem.name) == rdbmsName) then
      rdbms= rdbmsItem
      break
    end
  end
  
  if rdbms == nil then
    return grt.error(string.format(
      _("There is no RDBMS with the name %s in the /rdbmsMgmt/rdbms list."), rdbmsName))
  end
  
  
  -- get workbench and set RDBMS
  local workbench= grtV.getGlobal("/workbench")
  workbench.rdbms= rdbms
  
  -- remove old views if present
  if workbench.model.views ~= nil then
    for i= grtV.getn(workbench.model.views), 1, -1 do
      grtV.remove(workbench.model.views, i)
    end
  end

  
  -- initalize the catalog
  local catalog
  
  -- build struct names
  local databaseObjectPackage= grtV.toLua(rdbms.databaseObjectPackage)
  local catalogStructName= databaseObjectPackage .. ".Catalog"
  local schemaStructName= databaseObjectPackage .. ".Schema"

  -- check if structs exists  
  if ((not grtS.exists(catalogStructName)) or 
    (not grtS.exists(schemaStructName))) then
    return grt.error(string.format(_("The struct definition for %s or %s cannot be found."), 
      catalogStructName, schemaStructName))
  end
  
  catalog= grtV.newObj(catalogStructName, "default", "", grtV.toLua(workbench._id))
  catalog.oldName= "default"
  
  -- set catalog version
  local versionSplit= grt.split(rdbmsVersion, "%.")
  catalog.version= grtV.newObj("db.Version", "Version", "", grtV.toLua(catalog._id))  
  if table.getn(versionSplit) >= 1 then
    catalog.version.major= tonumber(versionSplit[1])
  end
  if table.getn(versionSplit) >= 2 then
    catalog.version.minor= tonumber(versionSplit[2])
  end
  if table.getn(versionSplit) >= 3 then
    catalog.version.release= tonumber(versionSplit[3])
  end
  
  -- fill reference lists
  for i= 1, grtV.getn(rdbms.simpleDatatypes) do
    grtV.insert(catalog.simpleDatatypes, rdbms.simpleDatatypes[i]._id)
  end
  for i= 1, grtV.getn(rdbms.characterSets) do
    grtV.insert(catalog.characterSets, rdbms.characterSets[i]._id)
  end
  
  -- create a schema for the catalog and add it to the model
  local schema= grtV.newObj(schemaStructName, "test", "", grtV.toLua(catalog._id))
  schema.oldName= "test"
  schema.defaultCharacterSetName= "latin1"
  -- schema.defaultCollationName= "latin1_swedish_ci"
  
  
  grtV.insert(catalog.schemata, schema)
  workbench.catalog= catalog


  -- initialize properties struct
  local info= grtV.newObj("db.workbench.ModelInfo", "Properties", "", grtV.toLua(workbench.model._id))

  info.modelName= "New Model"
  info.version= "1.0"
  info.project= "Name of the project"
  info.dateCreated= os.date("%b %d, %Y")
  info.dateChanged= os.date("%b %d, %Y")
  workbench.model.properties= info


  -- initalize the model by creating a view (the creation of the view automatically triggers 
  -- the creation of the view's rootLayer)
  local view= grtV.newObj("db.workbench.View", "Main View", "", grtV.toLua(workbench.model._id))
  -- view.rootLayer.description= _("The root layer of this view that contains all other layers.")
  
  
  -- initialize workbench data dict
  workbench.customData= grtV.newDict("", "")

  collectgarbage()

  return grt.success()
end


function recreateElementDefaultProperties(soure, target)
  target.top= grtV.toLua(soure.top)
  target.left= grtV.toLua(soure.left)
  target.width= grtV.toLua(soure.width)
  target.height= grtV.toLua(soure.height)
  target.visible= grtV.toLua(soure.visible)
  target.enabled= grtV.toLua(soure.enabled)
  target.locked= grtV.toLua(soure.locked)
  target.expanded= grtV.toLua(soure.expanded)
end


function recreateElement(wb, info)
  local type= grtS.get(info)
  local elem= nil
  
  if type == "db.workbench.TableElement" then
    elem= grtV.newObj("db.workbench.TableElement", grtV.toLua(info.name), "", "")
    
    recreateElementDefaultProperties(info, elem)
    
    elem.columnsExpanded= grtV.toLua(info.columnsExpanded)
    elem.indicesExpanded= grtV.toLua(info.indicesExpanded)
    elem.foreignKeysExpanded= grtV.toLua(info.foreignKeysExpanded)
    elem.triggersExpanded= grtV.toLua(info.triggersExpanded)
    
    elem.color= grtV.toLua(info.color)
    elem.table= info.table
    
  elseif type == "db.workbench.ViewElement" then
    elem= grtV.newObj("db.workbench.ViewElement", grtV.toLua(info.name), "", "")
    
    recreateElementDefaultProperties(info, elem)
    
    elem.view= info.view
    
  elseif type == "db.workbench.RoutinesElement" then
    elem= grtV.newObj("db.workbench.RoutinesElement", grtV.toLua(info.name), "", "")
    
    recreateElementDefaultProperties(info, elem)    
    
    elem.routineGroup= info.routineGroup
    
  elseif type == "db.workbench.NoteElement" then
    elem= grtV.newObj("db.workbench.NoteElement", grtV.toLua(info.name), "", "")
    
    recreateElementDefaultProperties(info, elem)
    
    elem.color= grtV.toLua(info.color)
    elem.text= grtV.toLua(info.text)
    
  else
    print("Unknown element type: "..type)
    return nil
  end
  
  if elem ~= nil then

  end
  
  return elem
end


function migrateDoc(doc)
  -- version 2
  if (doc.version == nil) or (grtV.toLua(doc.version) < 2) then
    -- table's primaryKeys have changed to indices
    local i
    
    for i= 1, grtV.getn(doc.catalog.schemata) do
      local tbls= doc.catalog.schemata[i].tables
      local j
      
      for j= 1, grtV.getn(tbls) do
        local tbl= tbls[j]
        local idxStructName= grtS.getMemberContentStruct(grtS.get(tbl), "indices")
        local idx= grtV.newObj(idxStructName, "PRIMARY", "", grtV.toLua(tbl._id))
        local idxColStructName= grtS.getMemberContentStruct(grtS.get(tbl), "columns")
        local k
        
        idx.isPrimary= 1
        idx.unique= 1
        
        for k= 1, grtV.getn(tbl.primaryKey.columns) do
          local col_id= grtV.toLua(tbl.primaryKey.columns[k])
          local l
          local col= nil
          
          for l=1, grtV.getn(tbl.columns) do
            if col_id == grtV.toLua(tbl.columns[l]._id) then
              col= tbl.columns[l]
              break
            end
          end
          
          if col ~= nil then          
            local idxCol= grtV.newObj(idxColStructName, grtV.toLua(col.name), "", grtV.toLua(idx._id))
            
            idxCol.referedColumn= col_id
          
            grtV.insert(idx.columns, idxCol)
          end
        end
        
        tbl.primaryKey= idx
        grtV.insert(tbl.indices, idx)
      end
    end
    
    doc.version= 2
  end
end


function fixupDocument(wb)
  if wb.model.properties == nil then
    -- check properties struct
    local info= grtV.newObj("db.workbench.ModelInfo", "Properties", "", grtV.toLua(wb.model._id))

    info.modelName= "New Model"
    info.version= "1.0"
    info.project= "Name of the project"
  
    wb.model.properties= info
  end
end


function clearDocument(args)
  local wb= grtV.getGlobal("/workbench")
  -- remove old views if present
  if wb.model.views ~= nil then
    for i= grtV.getn(wb.model.views), 1, -1 do
      grtV.remove(wb.model.views, i)
    end
  end
  collectgarbage()
  return grt.success()
end


function loadDocumentData(args)
  local wb= grtV.getGlobal("/workbench")
  local doc= args[1]
  local view, vinfo
  local layer, linfo
  local elem, einfo
  local rel, rinfo
  local model
  local i, vc
  local j, lc
  local r, rc
  local ec
  local tableMap= {}

  -- fix any incompatibilities with files saved with older versions
  fixupDocument(wb)

  -- remove old views if present
  if wb.model.views ~= nil then
    for i= grtV.getn(wb.model.views), 1, -1 do
      grtV.remove(wb.model.views, i)
    end
  end
  collectgarbage()

  -- migrate catalog
  migrateDoc(doc)

  -- set the catalog subtree to the loaded one
  wb.catalog= doc.catalog

  -- set plugin data  
  if doc.customData ~= nil then
    wb.customData= doc.customData
  else
    wb.customData= grtV.newDict("", "")
  end
  
  -- set the rdbms subtree to the loaded one
  wb.rdbms= doc.rdbms
  
  -- load diagram stuff
  model= doc.model

  vc= grtV.getn(model.views)
  for i=1, vc do
    -- recreate the view
    vinfo= model.views[i]
    view= grtV.newObj("db.workbench.View", grtV.toLua(vinfo.name), "", grtV.toLua(wb.model._id))
    -- set attributes
    if vinfo.elementLayoutClass ~= nil then
      view.elementLayoutClass= grtV.toLua(vinfo.elementLayoutClass)
    end
    if vinfo.connectionLayoutClass ~= nil then
      view.connectionLayoutClass= grtV.toLua(vinfo.connectionLayoutClass)
    end
    
    -- recreate layers in the view
    -- dont create the root layer because it's auto-created by the view
    lc= grtV.getn(vinfo.layers)
    for j=2,lc do
      linfo= vinfo.layers[j]
      layer= grtV.newObj("db.workbench.Layer", grtV.toLua(linfo.name), "", grtV.toLua(wb.model._id))

      layer.top= grtV.toLua(linfo.top)
      layer.left= grtV.toLua(linfo.left)
      layer.width= grtV.toLua(linfo.width)
      layer.height= grtV.toLua(linfo.height)
      if linfo.color ~= nil then
        layer.color= grtV.toLua(linfo.color)
      end
      layer.description= grtV.toLua(linfo.description)
      grtV.insert(view.layers, layer)
    end
    
    -- recreate elements in the view
    ec= grtV.getn(vinfo.elements)
    for j=1,ec do
      einfo= vinfo.elements[j]
      elem = recreateElement(wb, einfo)
      if elem ~= nil then
        grtV.insert(view.elements, elem)
        elem.visible= einfo.visible
      
        if grtS.get(einfo) == "db.workbench.TableElement" then
          tableMap[grtV.toLua(einfo._id)] = elem
        end
      end
    end

    -- update recreated relationships
    rc= grtV.getn(vinfo.relationships)
    for r=1, rc do
      local startTableElem, endTableElem
      rinfo= vinfo.relationships[r]
      startTableElem= tableMap[grtV.toLua(rinfo.startTable)]
      endTableElem= tableMap[grtV.toLua(rinfo.endTable)]

      if (startTableElem == nil) or (endTableElem == nil) then
        print("Ignoring invalid relationship in loaded document: "..grtV.toLua(rinfo.name))
      else
      	rel= grtV.newObj("db.workbench.Relationship", grtV.toLua(rinfo.name), "", grtV.toLua(wb.model._id))
      	rel.startTable= startTableElem
      	rel.endTable= endTableElem
      	rel.startMany= grtV.toLua(rinfo.startMany)
      	rel.endMany= grtV.toLua(rinfo.endMany)
      	rel.startMandatory= grtV.toLua(rinfo.startMandatory)
      	rel.endMandatory= grtV.toLua(rinfo.endMandatory)
      	rel.startCaption= grtV.toLua(rinfo.startCaption)
      	rel.startCaptionXOffs= grtV.toLua(rinfo.startCaptionXOffs)
      	rel.startCaptionYOffs= grtV.toLua(rinfo.startCaptionYOffs)
      	rel.endCaption= grtV.toLua(rinfo.endCaption)
      	rel.endCaptionXOffs= grtV.toLua(rinfo.endCaptionXOffs)
      	rel.endCaptionYOffs= grtV.toLua(rinfo.endCaptionYOffs)
      	rel.caption= grtV.toLua(rinfo.caption)    
      	rel.captionXOffs= grtV.toLua(rinfo.captionXOffs)
      	rel.captionYOffs= grtV.toLua(rinfo.captionYOffs)
      	rel.comment= grtV.toLua(rinfo.comment)
        grtV.insert(view.relationships, rel)
      end
    end
  end

  -- createRelationshipsFromFKs({view})

  return grt.success()
end



function createModelForSchema(args)
  local schema= args[1]
  local view= grtV.getGlobal("/workbench/model/currentView")
  local i
  
  for i,table in schema.tables do
      local elem
      
      elem= grtV.newObj("db.workbench.TableElement", grtV.toLua(table.name), "", "")

      elem.left= math.mod(i, 10) * 200
      elem.top= (i/10) * 200
      elem.table= table
      
      grtV.insert(view.elements, elem)
      elem.visible= 1
  end
  
  return grt.success()
end


function createPrintPreview(args)
   local view
   
end


-- ========================================================================================


function getCharsetFromCollation(collation)
  if collation == "binary" then
    return "binary"
  else
    return string.sub(collation, 1, string.find(collation, "_", 1, true) - 1)
  end
end

-- ----------------------------------------------------------------------------------------
-- @brief Creates a new table and places it in the indicated position in the current 
--        canvas view.
--
-- @param top Coordinate for the top of the table
-- @param left Coordinate for the left of the table 
-- @param name Initial name of the table to be created
-- @param schema the GRT schema value the table belongs to
-- @param engine the name of the engine to use, is optional
-- @param collation the name of thecollation to use, is optional
-- @param color the name of style color, is optional
--
-- @return the table object on success or an error
-- ----------------------------------------------------------------------------------------
function placeNewTableElement(args)
  local left= args[1]
  local top= args[2]
  local name= args[3]
  local schema= args[4]
  -- optional engine parameter
  local engine= (grtV.getn(args) >= 5) and args[5] or nil
  -- optional collation parameter
  local collation= (grtV.getn(args) >= 6) and args[6] or nil
  local color= (grtV.getn(args) >= 7) and args[7] or "blue"
  local tableStructName
  local tbl
  
  tableStructName= grtV.toLua(grtV.getGlobal("/workbench/rdbms/databaseObjectPackage")) .. ".Table"
  tbl= grtV.newObj(tableStructName, grtV.toLua(name), "", grtV.toLua(schema._id))

  tbl.oldName= grtV.toLua(name)
  if engine ~= nil then
    tbl.tableEngine= engine
  end
  if collation ~= nil then
    tbl.defaultCharacterSetName= getCharsetFromCollation(grtV.toLua(collation))
    tbl.defaultCollationName= collation
  end
  grtV.insert(schema.tables, tbl)
  
  -- place element
  local elem= placeElementFromObject({tbl, left, top}).value
  
  elem.color= color
  elem.visible= 1
  return grt.success(elem)
end


function placeNewViewElement(args)
  local left= args[1]
  local top= args[2]
  local name= args[3]
  local schema= args[4]
  local view
  local viewStructName

  viewStructName= grtV.toLua(grtV.getGlobal("/workbench/rdbms/databaseObjectPackage")) .. ".View"
  view= grtV.newObj(viewStructName, grtV.toLua(name), "", grtV.toLua(schema._id))
  grtV.insert(schema.views, view)

  -- place element
  local elem= placeElementFromObject({view, left, top}).value

  elem.visible= 1
  
  return grt.success(elem)
end


function placeNewRoutineGroupElement(args)
  local left= args[1]
  local top= args[2]
  local name= args[3]
  local schema= args[4]
  local routineGroup
  local routineStructName

  routineStructName= grtV.toLua(grtV.getGlobal("/workbench/rdbms/databaseObjectPackage")) .. ".RoutineGroup"

  routineGroup= grtV.newObj(routineStructName, grtV.toLua(name), "", grtV.toLua(schema._id))
  grtV.insert(schema.routineGroups, routineGroup)

  -- place element
  local elem= placeElementFromObject({routineGroup, left, top}).value

  elem.visible= 1
  
  return grt.success(elem)
end


function formatSourceDestTableIdentifier(fmt, stable, dtable)
  local stableName= grtV.toLua(stable.name)
  local dtableName= grtV.toLua(dtable.name)

  return grtU.replace(grtU.replace(fmt, "%stable%", stableName), "%dtable%", dtableName)
end

function formatTableIdentifier(fmt, table)
  local tableName= grtV.toLua(table.name)
  local schemaName= grtV.toLua(table.owner.name)
  return grtU.replace(grtU.replace(fmt, "%table%", tableName), "%schema%", schemaName)
end

function formatColumnIdentifier(fmt, table, column)
  local tableName= grtV.toLua(table.name)
  local schemaName= grtV.toLua(table.owner.name)
  local columnName= grtV.toLua(column.name)
  
  return grtU.replace(grtU.replace(grtU.replace(fmt, "%table%", tableName), "%schema%", schemaName), "%column%", columnName)
end


-- ----------------------------------------------------------------------------------------
-- @brief Initiates relationship creation, creating a new context to be used during it.
--
-- @param relType specifies the relationship type, "1n", "11" or "nm"
-- @param relSourceOptional specifies wether the relationship source is optional
-- @param relTargetOptional specifies wether the relationship target is optional
--
-- @return returns a context object which should be used in the other related functions
-- ----------------------------------------------------------------------------------------
function relationshipStart(args)
  local relType= grtV.toLua(args[1])
  local relSourceOptional= grtV.toLua(args[2])
  local relTargetOptional= grtV.toLua(args[3])
  
  return grt.success({
      relType= relType, 
      relSourceOptional= relSourceOptional,
      relTargetOptional= relTargetOptional,
      step= 0, 
      mapping= grtV.newList("list"), 
      pkcount= {}
    })
end


function createFKFromTablePKs(sourceTable, destTable)
  local i, c
  local newFK
  local pk= sourceTable.primaryKey
  local opts= grtV.getGlobal("/app/options")
  local fkNameFormat= grtV.toLua(opts.FKNameTemplate) or "FK%table%"
  local fkColumnNameFormat= grtV.toLua(opts.FKColumnNameTemplate) or "FK%table%%column%"

  -- check if there is a PK
  if (pk == nil) or (grtV.toLua(pk) == "") then
    return -1
  end
  
  -- create a new FK
  newFK= grtV.newObj("db.ForeignKey", formatTableIdentifier(fkNameFormat, sourceTable), "", grtV.toLua(destTable._id))

  newFK.deleteRule = grtV.toLua(opts.FKDeleteRule) or "NO ACTION"
  newFK.updateRule = grtV.toLua(opts.FKUpdateRule) or "NO ACTION"

  newFK.referedTable= sourceTable
  newFK.referedTableName= sourceTable.name

  c= grtV.getn(pk.columns)
  for i=1,c do
    local pkCol= pk.columns[i]
    local column= pkCol.referedColumn
    local newColumn

    newColumn= grtV.newObj(grtS.get(column), formatColumnIdentifier(fkColumnNameFormat, sourceTable, column), "", grtV.toLua(destTable._id))
    -- copy column stuff
    DbUtils:copyColumnType({newColumn, column})
    -- add to the table
    grtV.insert(destTable.columns, newColumn)
    -- add the new column to the FK
    grtV.insert(newFK.columns, newColumn._id)
    grtV.insert(newFK.referedColumns, column._id)
    grtV.insert(newFK.referedColumnNames, grtV.toLua(column.name))
  end
  
  -- add the FK to the table
  if destTable.foreignKeys == nil then
    local fkList= grtV.newList("dict", grtS.get(newFK))
    destTable.foreignKeys= fkList
  end
  grtV.insert(destTable.foreignKeys, newFK)
  
  return 1
end


function createNMRelationshipFromTablePKs(sourceTableElem, destTableElem)
  local i, c
  local sfk, dfk
  local sourceTable= sourceTableElem.table
  local destTable= destTableElem.table
  local spk= sourceTable.primaryKey
  local dpk= destTable.primaryKey
  local opts= grtV.getGlobal("/app/options")
  local auxTableFormat= grtV.toLua(opts.AuxTableTemplate) or "%stable%_has_%dtable%"
  local fkNameFormat= grtV.toLua(opts.FKNameTemplate) or "FK%table%"
  local fkColumnNameFormat= grtV.toLua(opts.FKColumnNameTemplate) or "FK%table%%column%"
  local auxTable

  -- check if there is a PK
  if (spk == nil) or (grtV.toLua(spk) == "") then
    return -1
  end
  if (dpk == nil) or (grtV.toLua(dpk) == "") then
    return -1
  end

  -- create the aux. table
  auxTable= grtV.newObj(grtS.get(sourceTable), formatSourceDestTableIdentifier(auxTableFormat, sourceTable, destTable), "", grtV.toLua(sourceTable.owner._id))
  auxTable.tableEngine= sourceTable.tableEngine
  auxTable.oldName= auxTable.name

  grtV.insert(sourceTable.owner.tables, auxTable)

  -- create a new FK pointing to source table and one to the dest table
  sfk= grtV.newObj("db.ForeignKey", formatTableIdentifier(fkNameFormat, sourceTable), "", grtV.toLua(auxTable._id))
  sfk.deleteRule = grtV.toLua(opts.FKDeleteRule) or "NO ACTION"
  sfk.updateRule = grtV.toLua(opts.FKUpdateRule) or "NO ACTION"
  sfk.referedTable= sourceTable
  sfk.referedTableName= sourceTable.name
  c= grtV.getn(spk.columns)
  for i=1,c do
    local pkCol= spk.columns[i]
    local column= pkCol.referedColumn
    local newColumn

    newColumn= grtV.newObj(grtS.get(column), formatColumnIdentifier(fkColumnNameFormat, sourceTable, column), "", grtV.toLua(auxTable._id))
    -- copy column stuff
    DbUtils:copyColumnType({newColumn, column})
    -- add to the table
    grtV.insert(auxTable.columns, newColumn)
    -- add the new column to the FK
    grtV.insert(sfk.columns, newColumn._id)
    grtV.insert(sfk.referedColumns, column._id)
    grtV.insert(sfk.referedColumnNames, grtV.toLua(column.name))
  end

  dfk= grtV.newObj("db.ForeignKey", formatTableIdentifier(fkNameFormat, destTable), "", grtV.toLua(auxTable._id))
  dfk.deleteRule = grtV.toLua(opts.FKDeleteRule) or "NO ACTION"
  dfk.updateRule = grtV.toLua(opts.FKUpdateRule) or "NO ACTION"
  dfk.referedTable= destTable
  dfk.referedTableName= destTable.name
  c= grtV.getn(dpk.columns)
  for i=1,c do
    local pkCol= dpk.columns[i]
    local column= pkCol.referedColumn
    local newColumn

    newColumn= grtV.newObj(grtS.get(column), formatColumnIdentifier(fkColumnNameFormat, destTable, column), "", grtV.toLua(auxTable._id))
    -- copy column stuff
    DbUtils:copyColumnType({newColumn, column})
    -- add to the table
    grtV.insert(auxTable.columns, newColumn)
    -- add the new column to the FK
    grtV.insert(dfk.columns, newColumn._id)
    grtV.insert(dfk.referedColumns, column._id)
    grtV.insert(dfk.referedColumnNames, grtV.toLua(column.name))
  end
  
  -- add the FKs to the table
  if auxTable.foreignKeys == nil then
    local fkList= grtV.newList("dict", grtS.get(sfk))
    auxTable.foreignKeys= fkList
  end
  grtV.insert(auxTable.foreignKeys, sfk)
  grtV.insert(auxTable.foreignKeys, dfk)


  -- create a figure element for the aux table and place it between the
  -- source and dest tables
  local tableElem
  local top, left

  top= (grtV.toLua(sourceTableElem.top) + grtV.toLua(destTableElem.top))/2
  left= (grtV.toLua(sourceTableElem.left) + grtV.toLua(destTableElem.left))/2

  tableElem= placeElementFromObject({auxTable, left, top}).value
  
  tableElem.visible= 1
  
  return 1
end


function createFKFromMapping(context)
  local i, c
  local newFK
  local opts= grtV.getGlobal("/app/options")
  local fkNameFormat= grtV.toLua(opts.FKNameTemplate) or "FK%table%"
  local fkColumnNameFormat= grtV.toLua(opts.FKColumnNameTemplate) or "FK%table%%column%"
  local sourceTable= context.sourceTable
  local destTable= context.destTable

  -- create a new FK
  newFK= grtV.newObj("db.ForeignKey", formatTableIdentifier(fkNameFormat, sourceTable), "", grtV.toLua(destTable._id))

  newFK.deleteRule = grtV.toLua(opts.FKDeleteRule) or "NO ACTION"
  newFK.updateRule = grtV.toLua(opts.FKUpdateRule) or "NO ACTION"

  newFK.referedTable= sourceTable
  newFK.referedTableName= sourceTable.name

  c= grtV.getn(context.mapping)
  for i=1,c do
    local srcColumn= context.mapping[i][1]
    local dstColumn= context.mapping[i][2]

    if (dstColumn == nil) or (grtV.toLua(dstColumn) == "") then
      dstColumn= grtV.newObj(grtS.get(srcColumn), formatColumnIdentifier(fkColumnNameFormat, sourceTable, srcColumn), "", grtV.toLua(destTable._id))
      -- copy column stuff
      dstColumn.datatypeName= srcColumn.datatypeName
      dstColumn.precision= srcColumn.precision
      dstColumn.scale= srcColumn.scale
      dstColumn.length= srcColumn.length
      dstColumn.datatypeExplicitParams= srcColumn.datatypeExplicitParams
      -- add to the table
      grtV.insert(destTable.columns, dstColumn)
    end
    grtV.insert(newFK.columns, dstColumn._id)
    grtV.insert(newFK.referedColumns, srcColumn._id)
    grtV.insert(newFK.referedColumnNames, grtV.toLua(srcColumn.name))
  end
  -- add the FK to the table
  if destTable.foreignKeys == nil then
    local fkList= grtV.newList("dict", grtS.get(newFK))
    destTable.foreignKeys= fkList
  end
  grtV.insert(destTable.foreignKeys, newFK)  
end


function tableColumnIsPK(table, column)
  local i, c
  
  if table.primaryKey == nil then
    return 0
  end
  
  c= grtV.getn(table.primaryKey.columns)
  for i=1,c do
    if grtV.toLua(table.primaryKey.columns[i]) == grtV.toLua(column._id) then
      return 1
    end
  end
  return 0
end


function compareColumnTypes(column1, column2)
  if grtV.toLua(column1.datatypeName) ~= grtV.toLua(column2.datatypeName) then
    return 0
  end
  if grtV.toLua(column1.datatypeExplicitParams) ~= grtV.toLua(column2.datatypeExplicitParams) then
    return 0
  end
  if grtV.toLua(column1.length) ~= grtV.toLua(column2.length) then
    return 0
  end
  if grtV.toLua(column1.precision) ~= grtV.toLua(column2.precision) then
    return 0
  end
  if grtV.toLua(column1.scale) ~= grtV.toLua(column2.scale) then
    return 0
  end
  
  return 1
end


-- ----------------------------------------------------------------------------------------
-- @brief Must be called to handle click events in the canvas during relationship creations.
--
-- @param context the relationship creation context
-- @param x the X coordinate where the click occurred
-- @param y the Y coordinate where the click occurred
--
-- @return returns a status message which should be displayed to the user on success
-- ----------------------------------------------------------------------------------------
function relationshipClickedPoint(args)
  local context= args[1]
  local x= grtV.toLua(args[2])
  local y= grtV.toLua(args[3])
  local result
  local msg= ""
  local error= 0
  local view= grtV.lookupId(grtV.getGlobal("/workbench/model/currentView"))

  result= WorkbenchController:objectAtPoint({x,y})

  if (result ~= nil) and (result.element ~= nil) and (grtS.get(result.element) == "db.workbench.TableElement") then
    local table= result.element.table
    local tableElem= result.element
    
    if grtV.toLua(context.step) == 0 then -- select source object
      local column= nil
      if result.detail ~= nil then
        local columnStructName= grtS.get(result.detail)
        if grtS.isOrInheritsFrom(columnStructName, "db.Column") then
          column= result.detail
        end
      end
      
      if column == nil then
        -- a table was selected, there must be a PK defined
        if table.primaryKey == nil then
          msg= "Selected table has no PK, please select the column to add as FK."
          error= 2
        else
          msg= "Source table selected. "
          context.sourceTableElem= tableElem
          context.sourceTable= table
          context.step= 1 -- select dest table
          
          -- start rubberband
          WorkbenchController:rubberbandStart({
            grtV.toLua(result.element.left) + grtV.toLua(result.element.width)/2, 
            grtV.toLua(result.element.top) + 10})
        end
      elseif grtV.toLua(context.relType) == "nm" then
        -- a column was selected but we're creating a n:m relationship
        msg= _("Please select a table for the n:m relationship.")
        error= 2
      else
        -- check if the column is part of PK
        if tableColumnIsPK(table, column) then
          local bounds

          msg= "Source column selected. Select matching column for "..grtV.toLua(column.name)
          context.sourceColumn= column
          context.sourceTable= table
          context.step= 2 -- select dest column
          
          bounds= WorkbenchController:detailFrame({result.element, result.detail})
          WorkbenchController:rubberbandStart({
            grtV.toLua(bounds[1]) + grtV.toLua(bounds[3])/2,
            grtV.toLua(bounds[2]) + grtV.toLua(bounds[4])/2})
        else
          msg= _("Source column must be a PK.")
          error= 2
        end
      end
    elseif grtV.toLua(context.step) == 1 then -- select dest table, after having src table selected
      local res
      if grtV.toLua(context.relType) ~= "nm" then
        -- add all PK columns from the source table as FK of the dest table
        res= createFKFromTablePKs(context.sourceTable, table)
      else
        -- create an aux. table with FKs pointing to both tables
        res= createNMRelationshipFromTablePKs(context.sourceTableElem, tableElem)
      end
      
      context.step= -1
      context.sourceTable= nil
      context.sourceTableElem= nil
      context.mapping= grtV.newList("")
      
      if res ~= -1 then
        msg= string.format(_("Foreign Key created in table %s."), grtV.toLua(table.name))
        createRelationshipsFromFKs({view})
      else
        if grtV.toLua(context.relType) ~= "nm" then
          msg= _("The source table must have a primary key.")
        else
	  msg= _("The source and destination tables must have primary keys.")
        end
      end
      
      -- stop the rubberband
      WorkbenchController:rubberbandStop()
      
      -- notify about task completion
      WorkbenchController:taskComplete()
    elseif grtV.toLua(context.step) == 2 then -- select dest column or table
      -- add the selected column of the source table as FK of the dest table
      -- if column as selected in dest table, then turn that into FK
      local column= nil

      if result.detail ~= nil then
        local columnStructName= grtS.get(result.detail)
        if (columnStructName == "db.Column") or (grtS.inheritsFrom(columnStructName, "db.Column")) then
          column= result.detail
        end
      end
            
      if (context.destTable ~= nil) and (grtV.toLua(context.destTable._id) ~= grtV.toLua(table._id)) then
        msg= "Selected column in invalid table, please select next column from table "..grtV.toLua(context.destTable.name)
        error= 2
      else
        if context.destTable == nil then
          context.destTable= table
        end

        if column == nil then
          -- a table was selected, add a new FK column to the dest table 
          grtV.insert(context.mapping, {context.sourceColumn, ""})
          msg= "Added new FK column. "
          context.step= 2 -- select another source column
        else
	  -- check if the dest column has the same type as the source column
          if compareColumnTypes(context.sourceColumn, column) == 1 then
            grtV.insert(context.mapping, {context.sourceColumn, column})
            msg= "Added FK column. "
            context.step= 2
          else
            msg= "Selected destination column does not match type. "
            error= 2
            
            WorkbenchController:rubberbandStop()
          end
        end

        if error == 0 then
          -- auto-select next source PK
          local i1,c1,i2,c2
          local stable= context.sourceColumn.owner
      
          context.sourceColumn= nil

          c1= grtV.getn(stable.primaryKey.columns)
          for i1=1,c1 do
            local col= stable.primaryKey.columns[i1]["referedColumn"]
            local found= 0
            c2= grtV.getn(context.mapping)
            for i2=1,c2 do
              if grtV.toLua(context.mapping[i2][1]._id) == grtV.toLua(col._id) then
                found= 1
                break
              end
            end
            if found == 0 then
              context.sourceColumn= col
              msg= msg .. "Please select the matching column or table for column "..grtV.toLua(context.sourceColumn.name)
              break
            end
          end
      
          if context.sourceColumn == nil then
            -- end of operation
	    msg= "Foreign Key created in table "..grtV.toLua(table.name)..". "
            createFKFromMapping(context)
        
            context.step= -1
            context.sourceColumn= nil
            context.mapping= grtV.newList()
            createRelationshipsFromFKs({view})
          
            -- stop the rubberband
            WorkbenchController:rubberbandStop()
          
            -- notify about task completion
            WorkbenchController:taskComplete()
          end
        end -- error == 0
      end
    else
      print("BAD STEP")
    end
  else
    error= 1
  end
  
  if error > 0 then
    -- notify about task abortion
    WorkbenchController:taskCanceled()
  end
  
  if error == 1 then
    msg= "Invalid object selected. "
    -- stop the rubberband
    WorkbenchController:rubberbandStop()
  end

  if grtV.toLua(context.step) == 0 then
    if error ~= 2 then
      if created then
        msg= msg .. "Please select source table or column."
      else
        msg= msg .. "Please select source table or column for next FK."
      end
    end
  elseif grtV.toLua(context.step) == 1 then
    msg= msg .. "Please select the destination table."
  elseif grtV.toLua(context.step) == 2 then
    msg= msg
  end
  
  return grt.success(msg)
end


-- ----------------------------------------------------------------------------------------
-- @brief Cancels an ongoing relationship creation.
--
-- @param context the relationship creation context
--
-- @return returns 1 on success or and error
-- ----------------------------------------------------------------------------------------
function relationshipCancel(args)
  -- notify about task abortion
  WorkbenchController:rubberbandStop()
  WorkbenchController:taskCanceled()

  return grt.success()
end

-- ----------------------------------------------------------------------------------------
-- @brief Creates a new layer and places it in the indicated position in the current 
--        canvas view.
--
-- @param top Coordinate for the top of the layer
-- @param left Coordinate for the left of the layer 
-- @param width Coordinate for the top of the layer
-- @param height Coordinate for the left of the table 
-- @param name Initial name of the layer to be created
-- @param color Initial color of the layer
--
-- @return the created layer on success or an error
-- ----------------------------------------------------------------------------------------
function placeNewLayerElement(args)
  local left= grtV.toLua(args[1])
  local top= grtV.toLua(args[2])
  local width= grtV.toLua(args[3])
  local height= grtV.toLua(args[4])
  local name= grtV.toLua(args[5])
  local color= (grtV.getn(args) >= 6) and grtV.toLua(args[6]) or "blue"
  
  local model= grtV.getGlobal("/workbench/model")  
  local layer
  layer= grtV.newObj("db.workbench.Layer", name, "", grtV.toLua(model._id))
  layer.description= name
  layer.color= color
  if width < 0 then
    layer.left= left + width
    layer.width= -width
  else
    layer.left= left
    layer.width= width
  end
  if height < 0 then
    layer.top= top + height
    layer.height= -height
  else
    layer.top= top
    layer.height= height
  end
  grtV.insert(model.currentView.layers, layer)
  return grt.success(layer)
end


function placeNewImageElement(args)
  local x= grtV.toLua(args[1])
  local y= grtV.toLua(args[2])
  local file= grtV.toLua(args[3])
  local model= grtV.getGlobal("/workbench/model")
  
  local image
  
  image= grtV.newObj("db.workbench.ImageElement", "", "", grtV.toLua(model._id))
  
  image.left= x
  image.top= y
  image.filename= file
  
  grtV.insert(model.currentView.elements, image)
  image.visible= 1
  
  return grt.success(image)
end



-- ----------------------------------------------------------------------------------------
-- @brief Creates a new note and places it in the indicated position in the current 
--        canvas view.
--
-- @param top Coordinate for the top of the note
-- @param left Coordinate for the left of the note 
-- @param width Width of the note
-- @param height height of the note 
-- @param color the name of style color
--
-- @return the note object on success or an error
-- ----------------------------------------------------------------------------------------
function placeNewNoteElement(args)
  local x= grtV.toLua(args[1])
  local y= grtV.toLua(args[2])
  local width= grtV.toLua(args[3])
  local height= grtV.toLua(args[4])
  local color= grtV.toLua(args[5])
  local text= (grtV.getn(args) >= 6) and grtV.toLua(args[6]) or "Note"
  local model= grtV.getGlobal("/workbench/model")
  local note
  
  note= grtV.newObj("db.workbench.NoteElement", "note", "", grtV.toLua(model._id))
  
  note.left= x
  note.top= y
  note.width= width
  note.height= height
  note.text= text
  note.color= color
  
  grtV.insert(model.currentView.elements, note)
  note.visible= 1
  
  return grt.success(note)
end

-- ========================================================================================

function verticalAlign(args)
  local model= grtV.getGlobal("/workbench/model")
  local how= grtV.toLua(args[1])
  local elem
  local i, c
  local p
  
  
  c= grtV.getn(model.currentView.selection)
  if how == "T" then
    p= 100000000
    for i=1,c do
      elem= model.currentView.selection[i]
      if p > grtV.toLua(elem.top) then
        p= grtV.toLua(elem.top)
      end
    end
    for i=1,c do
      elem= model.currentView.selection[i]
      elem.top= p
    end
  elseif how == "C" then
    p= 0
    for i=1,c do
      elem= model.currentView.selection[i]
      p= p + grtV.toLua(elem.top)+grtV.toLua(elem.height)
    end
    p = (p / c)
    for i=1,c do
      elem= model.currentView.selection[i]
      elem.top= p - grtV.toLua(elem.height)/2
    end
  else 
    p= 0
    for i=1,c do
      elem= model.currentView.selection[i]
      if p < grtV.toLua(elem.top)+grtV.toLua(elem.height) then
        p= grtV.toLua(elem.top)+grtV.toLua(elem.height)
      end
    end
    for i=1,c do
      elem= model.currentView.selection[i]
      elem.top= p - grtV.toLua(elem.height)
    end
  end
  
  return grt.success()
end


function horizontalAlign(args)
  local model= grtV.getGlobal("/workbench/model")
  local how= grtV.toLua(args[1])
  local elem
  local i, c
  local p

  c= grtV.getn(model.currentView.selection)
  if how == "L" then
    p= 100000000
    for i=1,c do
      elem= model.currentView.selection[i]
      if p > grtV.toLua(elem.left) then
        p= grtV.toLua(elem.left)
      end
    end
    for i=1,c do
      elem= model.currentView.selection[i]
      elem.left= p
    end
  elseif how == "C" then
    p= 0
    for i=1,c do
      elem= model.currentView.selection[i]
      p= p + grtV.toLua(elem.left)+grtV.toLua(elem.width)
    end
    p = p / c
    for i=1,c do
      elem= model.currentView.selection[i]
      elem.left= p - grtV.toLua(elem.width)/2
    end
  else 
    p= 0
    for i=1,c do
      elem= model.currentView.selection[i]
      if p < grtV.toLua(elem.left)+grtV.toLua(elem.width) then
        p= grtV.toLua(elem.left)+grtV.toLua(elem.width)
      end
    end
    for i=1,c do
      elem= model.currentView.selection[i]
      elem.left= p - grtV.toLua(elem.width)
    end
  end
  return grt.success()
end


function autoSpaceElements(args)
  local vert= grtV.toLua(args[1])
  local spacing= grtV.toLua(args[2])
  local model= grtV.getGlobal("/workbench/model")
  local elem
  local i, j, c
  local x, y
  local elements= {}
  local found

  c= grtV.getn(model.currentView.selection)
  
  if vert == 0 then
    for i=1,c do
      found= 0
      for j=1,i-1 do
        if grtV.toLua(model.currentView.selection[i].left) < grtV.toLua(elements[j].left) then
          table.insert(elements, j, model.currentView.selection[i])
          found= 1
        end
      end
      if found == 0 then
        table.insert(elements, model.currentView.selection[i])
      end
    end

    for i=1,c do
      elem= elements[i]
      if i == 1 then
        x= grtV.toLua(elem.left)
        y= grtV.toLua(elem.top)
      end
      elem.left= x
      elem.top= y
      x= x + grtV.toLua(elem.width) + spacing
    end
  else
    for i=1,c do
      found= 0
      for j=1,i-1 do
        if grtV.toLua(model.currentView.selection[i].top) < grtV.toLua(elements[j].top) then
          table.insert(elements, j, model.currentView.selection[i])
          found= 1
        end
      end
      if found == 0 then
        table.insert(elements, model.currentView.selection[i])
      end
    end
  
    for i=1,c do
      elem= elements[i]
      if i == 1 then
        x= grtV.toLua(elem.left)
        y= grtV.toLua(elem.top)
      end
      elem.left= x
      elem.top= y
      y= y + grtV.toLua(elem.height) + spacing 
    end
  end
  return grt.success()  
end


function centerElementsAt(args)
  local x= grtV.toLua(args[1])
  local y= grtV.toLua(args[2])
  local curView= grtV.getGlobal("/workbench/model/currentView")
  local lowestX= 9999999999.0
  local lowestY= 9999999999.0
  local highestX= 0.0
  local highestY= 0.0

  local i, c

  c= grtV.getn(curView.elements)
  for i= 1, c do
    local elem= curView.elements[i]
    lowestX= math.min(lowestX, grtV.toLua(elem.x))
    lowestY= math.min(lowestY, grtV.toLua(elem.y))
    highestX= math.max(highestX, grtV.toLua(elem.x)+grtV.toLua(elem.width))
    highestY= math.max(highestY, grtV.toLua(elem.y)+grtV.toLua(elem.height))
  end
end

-- ========================================================================================

function copySelection(args)
  local data  
  local model= grtV.getGlobal("/workbench/model")
  local selection= model.currentView.selection
  local i, c
   
  c= grtV.getn(selection)
  --for i=1, c do
    
  --end
  
  return grt.success(data)
end


function cutSelection(args)
  local data= copySelection(args)
  deleteSelection(args)
  return data
end


function pasteSelection(args)
  local data= args[1]
  return grt.success()
end



function countElementsWithObject(object)
    local v, vc, e, ec
    local view
    local count= 0
    local model= grtV.getGlobal("/workbench/model")
    
    vc= grtV.getn(model.views)
    for v=1,vc do
      view= model.views[v]
      ec= grtV.getn(view.elements)
      for e=1,ec do
        local elem= view.elements[e]
        if grtS.get(elem) == "db.workbench.RoutinesElement" then
          local r, rc
          local routines= elem.routineGroup.routines
          rc= grtV.getn(routines)
          for r=1,rc do
            if grtV.toLua(object.id) == grtV.toLua(routines[r]) then
              count= count+1
            end
          end
        else
          if grtS.get(elem) == "db.workbenchTableElement" then
            if grtV.toLua(object.id) == grtV.toLua(elem.table.id) then
              count= count+1
            end
          elseif grtS.get(elem) == "db.workbench.ViewElement" then
            if grtV.toLua(object.id) == grtV.toLua(elem.view.id) then
              count= count+1
            end
          end
        end
      end
    end
    return count
end


function deleteObject(obj)
  local model= grtV.getGlobal("/workbench/model")
  
  if grtS.get(obj) == "db.workbench.TableElement" then
    if countElementsWithObject(obj.table) <= 1 then
      local table= obj.table
      grtV.removeObj(table.owner.tables, table)
    end
  elseif grtS.get(obj) == "db.workbench.RoutinesElement" then
      local routines= obj.routineGroup.routines
      local j
      
      if countElementsWithObject(obj.routineGroup) <= 1 then
        grtV.removeObj(obj.routineGroup.owner.routineGroups, obj.routineGroup)
      end
      for j=1,grtV.getn(routines) do
        local rout= grtV.lookupId(routines[j])
        if countElementsWithObject(rout) <= 1 then
          grtV.removeObj(rout.owner.routines, rout)
        end
      end
  elseif grtS.get(obj) == "db.workbench.ViewElement" then
    if countElementsWithObject(obj.view) <= 1 then
      local view= obj.view
      grtV.removeObj(view.owner.views, view)
    end
  end
  WorkbenchController:deleteElement({obj})
end


function deleteObjectAtPoint(args)
  local x= grtV.toLua(args[1])
  local y= grtV.toLua(args[2])
  
  local result= WorkbenchController:objectAtPoint({x,y})
  
  if result ~= nil then
    deleteObject(result.element)
  end
  collectgarbage()
  return grt.success()
end

--------------------------------------------------------------------------------------
-- @brief Deletes selected elements from the model
--------------------------------------------------------------------------------------
function deleteSelection(args)
  local model= grtV.getGlobal("/workbench/model")
  local selection= model.currentView.selection
  local i
  
  for i=grtV.getn(selection),1,-1 do
    deleteObject(selection[i])
  end
  
  selection= nil
  
  collectgarbage()
  
  return grt.success()
end

-- ========================================================================================

function selectLayer(args)
  local layer= args[1]
  local i, c
  
  c= grtV.getn(layer.elements)
  for i=1, c do
    WorkbenchController:selectObject({layer.elements[i]})
  end
  
  c= grtV.getn(layer.subLayers)
  for i=1, c do
    selectLayer({layer.subLayers[i]})
  end
  
  return grt.success()
end


function raiseLayer(args)
end

function lowerLayer(args)
end


-- ----------------------------------------------------------------------------------------
-- @brief Creates a new element based on the object struct and places it in the indicated 
--        position in the current canvas view.
--
-- @param obj Object to place or a list of objects for collection objects
-- @param top Coordinate for the top of the table
-- @param left Coordinate for the left of the table 
--
-- @return the table element on success or an error
-- ----------------------------------------------------------------------------------------
function placeElementFromObject(args)
  local obj= args[1]
  local left= args[2]  
  local top= args[3]
  
  local model= grtV.getGlobal("/workbench/model")
  local elementList= model.currentView.elements
  local elem= nil
  local objType= grtV.typeOf(obj)
  
  if objType == "dict" then
    local objStructName= grtS.get(obj)
    local objName= grtV.toLua(obj.name)
    
    -- create Table element
    if grtS.isOrInheritsFrom(objStructName, "db.Table") then
      elem= grtV.newObj("db.workbench.TableElement", objName, "", "")
      elem.top= top
      elem.left= left
      elem.width= 160
      elem.table= obj  
      grtV.insert(elementList, elem)
      
    -- create View element
    elseif grtS.isOrInheritsFrom(objStructName, "db.View") then
      elem= grtV.newObj("db.workbench.ViewElement", objName, "", "")
      elem.top= top
      elem.left= left
      elem.width= 320
      elem.height= 120
      elem.view= obj
      grtV.insert(elementList, elem)
      
    elseif grtS.isOrInheritsFrom(objStructName, "db.RoutineGroup") then
      elem= grtV.newObj("db.workbench.RoutinesElement", objName, "", "")
      elem.top= top
      elem.left= left
      elem.routineGroup= obj
      grtV.insert(elementList, elem)      
    end
  elseif (objType == "list") and (grtV.getn(obj) > 0) then
    local objStructName= grtS.get(obj[1])
    local schema= obj[1].owner
    
    -- create Routine Group element
    if grtS.isOrInheritsFrom(objStructName, "db.Routine") and (schema ~= nil) then
      local i
      local routineGroup
      
      routineGroup= grtV.newObj("db.RoutineGroup", "Routine Group", "", grtV.toLua(schema._id))
      grtV.insert(schema.routineGroups, routineGroup)
      
      for i= 1, grtV.getn(obj) do
        grtV.insert(routineGroup.routines, obj[i]._id)
      end
      
      elem= placeElementFromObject({routineGroup, left, top}).value
      -- elem.visible= 1
    end
  end

  return grt.success(elem)
end

-- ----------------------------------------------------------------------------------------
-- @brief Arranges the given objects on the current view in a grid
--
-- @param objs list of objects to arrange
-- @param offsetX x offset of the area to place the objects
-- @param offsetY y offset of the area to place the objects
-- @param cols number of columns to use, optional
-- @param space the space between the objects, optional
--
-- @return returns 1 on success or and error
-- ----------------------------------------------------------------------------------------
function arrangeObjectsOnCurrentView(args)
  local objs= args[1]
  local offsetX= grtV.toLua(args[2])
  local offsetY= grtV.toLua(args[3])
  local cols= (grtV.getn(args) >= 4) and grtV.toLua(args[4]) or 10
  local space= (grtV.getn(args) >= 5) and grtV.toLua(args[5]) or 50
  local i
  local x= 0
  local y= 0
  local spaceX= space * 3
  local spaceY= space
  local maxRowHeight= 200
  local routineList= grtV.newList("dict", "db.Routine")
  local elementList= {}
  local currentCol= 1
  
  for i= 1, grtV.getn(objs) do
    local objStructName= grtS.get(objs[i])
    
    -- collect db.Routine and make a single RoutinesElement
    if grtS.isOrInheritsFrom(objStructName, "db.Routine") then
      grtV.insert(routineList, objs[i])
      
    -- place all other objects
    else
      local elem
      elem= placeElementFromObject({objs[i], offsetX + x, offsetY + y}).value
      table.insert(elementList, elem)
      
      x= x + grtV.toLua(elem.width) + spaceX
      
      if maxRowHeight < grtV.toLua(elem.height) then
        maxRowHeight= grtV.toLua(elem.height)
      end
    end
    
    currentCol= currentCol + 1
    
    -- only do the specified number of columns
    if currentCol > cols then
      currentCol= 1
      x= 0
      y= y + maxRowHeight + spaceY
      maxRowHeight= 200
    end
  end
  
  -- if there have been routines, places the RoutinesElement
  if grtV.getn(routineList) > 0 then
    local elem
    
    -- print("Place " .. grtV.getn(routineList) .. " routines")
    
    elem= placeElementFromObject({routineList, offsetX + x, offsetY + y}).value
    table.insert(elementList, elem)
  end
  
  -- set all elements visible
  for i= 1, table.getn(elementList) do
    elementList[i].expanded= 0
    elementList[i].visible= 1
  end
  
  createRelationshipsFromFKs({grtV.lookupId(grtV.getGlobal("/workbench/model/currentView"))})
  
  -- free cached GRT values on Lua side
  collectgarbage()

  return grt.success()
end


-- ----------------------------------------------------------------------------------------
-- @brief Arranges the given objects on a new view in a grid
--
-- @param model
-- @param name the name of the new view
-- @param schema the schema object that should be arranged
-- @param offsetX x offset of the area to place the objects
-- @param offsetY y offset of the area to place the objects
-- @param cols number of columns to use, optional
-- @param space the space between the objects, optional
--
-- @return returns 1 on success or and error
-- ----------------------------------------------------------------------------------------
function arrangeSchemaOnNewView(args)
  addView({args[1], args[2]})
  return arrangeSchemaOnCurrentView({args[3],args[4],args[5],args[6],args[7]})
end

-- ----------------------------------------------------------------------------------------
-- @brief Arranges the given objects on the current view in a grid
--
-- @param schema the schema object that should be arranged
-- @param offsetX x offset of the area to place the objects
-- @param offsetY y offset of the area to place the objects
-- @param cols number of columns to use, optional
-- @param space the space between the objects, optional
--
-- @return returns 1 on success or and error
-- ----------------------------------------------------------------------------------------
function arrangeSchemaOnCurrentView(args)
  local schema= args[1]
  local offsetX= grtV.toLua(args[2])
  local offsetY= grtV.toLua(args[3])
  local cols= (grtV.getn(args) >= 4) and grtV.toLua(args[4]) or nil
  local space= (grtV.getn(args) >= 5) and grtV.toLua(args[5]) or nil
  local i
  local schemaStructName= grtS.get(schema)
  local schemaObjList= grtV.newList("dict")
  
  -- check all schemata member variables
  local members= grtS.getMembers(schemaStructName)
  for i= 1, grtV.getn(members) do
  
    -- if the member variable is a list
    if (grtS.getMemberType(schemaStructName, members[i]) == "list") then
      local memberContentType= grtS.getMemberContentStruct(schemaStructName, members[i])
      
      -- check content type of the list
      if ((memberContentType ~= nil) and grtS.inheritsFrom(memberContentType, "db.DatabaseObject")) then
        local objList= schema[members[i]]
        local j
        
        if objList ~= nil then
          -- build schemaObjList
          for j= 1, grtV.getn(objList) do
            grtV.insert(schemaObjList, objList[j])
          end
        end
      end
    end
  end
  
  if grtV.getn(schemaObjList) > 0 then
    arrangeObjectsOnCurrentView({schemaObjList, offsetX, offsetY, cols, space})
  end
end


function loadAndAutoArrangeInNewView(args)
  local schema= args[1]
  local viewName= args[2]
  addView({grtV.getGlobal("/workbench/model"), viewName})
  return loadAndAutoArrange({schema, grtV.getGlobal("/workbench/model/currentView")})
end

-- ----------------------------------------------------------------------------------------
-- @brief Creates GC objects and arranges them objects on the current view using a more sophisticated algo.
--
-- @param schema the schema object that should be arranged
-- @param view The view whose objects must be loaded and arranged.
--
-- @return returns 1 on success or and error
-- ----------------------------------------------------------------------------------------
function loadAndAutoArrange(args)
  local schema= args[1]
  local view= args[2]
  local i
  local schemaStructName= grtS.get(schema)
  local schemaObjList= grtV.newList("dict")

  -- check all schemata member variables
  local members= grtS.getMembers(schemaStructName)
  for i= 1, grtV.getn(members) do
  
    -- if the member variable is a list
    if (grtS.getMemberType(schemaStructName, members[i]) == "list") then
      local memberContentType= grtS.getMemberContentStruct(schemaStructName, members[i])
      
      -- check content type of the list
      if ((memberContentType ~= nil) and grtS.inheritsFrom(memberContentType, "db.DatabaseObject")) then
        local objList= schema[members[i]]
        local j
        
        if objList ~= nil then
          -- build schemaObjList
          for j= 1, grtV.getn(objList) do
            grtV.insert(schemaObjList, objList[j])
          end
        end
      end
    end
  end
  
  if grtV.getn(schemaObjList) > 0 then
    arrangeObjectsOnCurrentView({schemaObjList, 0, 0})
    WorkbenchController:arrangeObjects({view, 0})
  end
  return grt.success()
end

-- ----------------------------------------------------------------------------------------
-- @brief Adds a view to the canvas and makes it the new current view
--
-- @param model the model object
-- @param name the name of the new view
--
-- @return returns 1 on success or and error
-- ----------------------------------------------------------------------------------------
function addView(args)
  local model= args[1]
  local viewName= grtV.toLua(args[2])
  local view
  
  view= grtV.newObj("db.workbench.View", viewName, "", grtV.toLua(model._id))
  model.currentView= view
  
  return grt.success(view)
end



function relationshipExists(view, table1, table2)
  local i, rel
  local rc

  rc= grtV.getn(view.relationships)
  for i=1,rc do
    rel= view.relationships[i]
    if (grtV.toLua(rel.startTable) == "") or (grtV.toLua(rel.endTable) == "") then
      print("Invalid relationship "..grtV.toLua(rel.name))
    elseif (rel.startTable.table == table1) and (rel.endTable.table == table2) then
      return 1
    end
  end
  return 0
end


function tableElementForTable(view, table)
  local i, t
  local c
  
  c= grtV.getn(view.elements)
  for i=1,c do
    t= view.elements[i]
    if grtS.get(t) == "db.workbench.TableElement" then
      if grtV.toLua(t.table._id) == grtV.toLua(table._id) then
        return t
      end
    end
  end
  return nil
end



function createRelationshipElement(view, sourceTable, sourceType, destTable, destType)
  local wb= grtV.getGlobal("/workbench")
  local rel
  
  rel= grtV.newObj("db.workbench.Relationship", grtV.toLua(sourceTable.name).."_"..grtV.toLua(destTable.name), "", grtV.toLua(wb.model._id))

  if sourceType == "1" then
    rel.startMany=0
    rel.startMandatory=1
  elseif sourceType == "0" then
    rel.startMany=0
    rel.startMandatory=0
  elseif sourceType == "*" then
    rel.startMany= 1
    rel.startMandatory= 0
  else
    rel.startMany=1
    rel.startMandatory=1
  end
  if destType == "1" then
    rel.endMany=0
    rel.endMandatory=1
  elseif destType == "0" then
    rel.endMany=0
    rel.endMandatory=0
  elseif destType == "*" then
    rel.endMany= 1
    rel.endMandatory= 0
  else
    rel.endMany=1
    rel.endMandatory=1
  end
  rel.startTable= sourceTable
  rel.endTable= destTable
  rel.captionYOffs= grtV.toLua(rel.captionYOffs) + 30
  grtV.insert(view.relationships, rel)
  return rel
end

-- ----------------------------------------------------------------------------------------
-- @brief Processes tables in the view, turning Foreign Keys into relationships
--
-- @param view the view to process
--
-- @return returns 1 on success or and error
-- ----------------------------------------------------------------------------------------
function createRelationshipsFromFKs(args)
  local view= args[1]
  local i, ec, elem
  local j, fc, fk
  local table
  local rel
  local refTable

  ec= grtV.getn(view.elements)
  for i=1,ec do
    elem= view.elements[i]
    if grtS.get(elem) == "db.workbench.TableElement" then
      table= elem.table
      if (table ~= nil) and (table.foreignKeys ~= nil) then
        fc= grtV.getn(table.foreignKeys)
        for j=1,fc do
          fk= table.foreignKeys[j]
          refTable= fk.referedTable
          if refTable ~= nil then
            if relationshipExists(view, refTable, table) == 0 then
              local stype, dtype
              local stable, dtable

              stype= "1"
              dtype= "+"
              
              -- TODO: try to detect n:m relationships?
              
              stable= tableElementForTable(view, refTable)
              dtable= tableElementForTable(view, table)

              if (stable ~= nil) and (dtable ~= nil) then
                rel= createRelationshipElement(view, stable, stype, dtable, dtype)
                rel.caption= fk.name
              end
            end
          end
        end
      end
    end
  end

  return grt.success()
end


-- ----------------------------------------------------------------------------------------
-- @brief Returns a list of available colors for different elements
--
-- @return returns the list of colors
-- ----------------------------------------------------------------------------------------

function getColorPresets(args)
  return grt.success({
    table= {
      {colorName="blue", color="#98BFDA"},
      {colorName="red", color="#EEBFBF"},
      {colorName="green", color="#BFEAA8"},
    },
    layer= {
      {colorName="white", color="#F8F8F8", border="#AAAAAA"},
      {colorName="red", color="#FDE1DD", border="#FFFFFF"},
      {colorName="blue", color="#D2E7F6", border="#FFFFFF"},
      {colorName="green", color="#E0FCD2", border="#FFFFFF"},
      {colorName="yellow", color="#FFFEE6", border="#AAAAAA"},
      {colorName="cyan", color="#E2FFFF", border="#FFFFFF"},
      {colorName="magenta", color="#FFE1F7", border="#FFFFFF"}
    },
    note= {
      {colorName="white", color="#FFFFFF"},
      {colorName="yellow", color="#FFEEA9"},
    },
    view= {
      {colorName="orange", color="#FEDE58"}
    },
    package= {
      {colorName="green", color="#98D8A5"}
    }})
end

-- ----------------------------------------------------------------------------------------
-- @brief Returns a list of available colors for different elements
--
-- @return returns the list of colors
-- ----------------------------------------------------------------------------------------

function generateSqlCreateScript(args)
  local sql
  local catalog= grtV.getGlobal("/workbench/catalog")
  
  TransformationMysql:generateSqlCreateStatements({catalog})
  
  sql= TransformationMysql:getSqlScript({catalog})
  
  return grt.success(sql)
end

-- ----------------------------------------------------------------------------------------
-- @brief Returns a new created routine
--
-- @param schema
-- @param name
--
-- @return returns the list of colors
-- ----------------------------------------------------------------------------------------
function addRoutine(args)
  local schema= args[1]
  local name= grtV.toLua(args[2])

  local routine
  local structName
  
  structName= grtV.toLua(grtV.getGlobal("/workbench/rdbms/databaseObjectPackage")) .. ".Routine"
  routine= grtV.newObj(structName, grtV.toLua(name), "", grtV.toLua(schema._id))  
  routine.routineType= "PROCEDURE"
  
  grtV.insert(schema.routines, routine)

  return grt.success(routine)
end

-- ----------------------------------------------------------------------------------------
-- @brief Returns a new created trigger
--
-- @param table
-- @param name
--
-- @return returns the newly created trigger
-- ----------------------------------------------------------------------------------------
function createTrigger(args)
  local table= args[1]
  local name= grtV.toLua(args[2])

  local trigger
  local structName
  
  structName= grtV.toLua(grtV.getGlobal("/workbench/rdbms/databaseObjectPackage")) .. ".Trigger"
  trigger= grtV.newObj(structName, grtV.toLua(name), "", grtV.toLua(table._id))  
  
  -- grtV.insert(table.triggers, trigger)

  return grt.success(trigger)
end


function raiseObjectAtPoint(args)
  local x= grtV.toLua(args[1])
  local y= grtV.toLua(args[2])
  local result

  result= WorkbenchController:objectAtPoint({x,y})
  if (result ~= nil) and (result.element ~= nil) then
    WorkbenchController:bringToFront(result.element)
  end
  return grt.success()
end

-- ----------------------------------------------------------------------------------------
-- @brief Selects all elements on a layer
--
-- @param view can be ommited, in that case the current view is chosen
--
-- @return success
-- ----------------------------------------------------------------------------------------
function selectAll(args)
  local view= (grtV.getn(args) >= 1) and args[1] or grtV.getGlobal("/workbench/model").currentView
  local i

  if view ~= nil then
    for i= grtV.getn(view.selection), 1, -1 do
      WorkbenchController:unselectObject({view.selection[i]})
    end
    
    for i= 1, grtV.getn(view.elements) do
      WorkbenchController:selectObject({view.elements[i]})
    end
  end
  
  return grt.success()
end

-- ----------------------------------------------------------------------------------------
-- @brief Selects all elements on a layer
--
-- @param view can be ommited, in that case the current view is chosen
--
-- @return success
-- ----------------------------------------------------------------------------------------
function bringSelectionToFront(args)
  local view= (grtV.getn(args) >= 1) and args[1] or grtV.getGlobal("/workbench/model").currentView
  local i

  if view ~= nil then   
    for i= 1, grtV.getn(view.selection) do
      WorkbenchController:bringToFront({view.selection[i]})
    end
  end
end

-- ----------------------------------------------------------------------------------------
-- @brief Selects all elements on a layer
--
-- @param view can be ommited, in that case the current view is chosen
--
-- @return success
-- ----------------------------------------------------------------------------------------
function sendSelectionToBack(args)
  local view= (grtV.getn(args) >= 1) and args[1] or grtV.getGlobal("/workbench/model").currentView
  local i

  if view ~= nil then
    for i= 1, grtV.getn(view.selection) do
      WorkbenchController:sendToBack({view.selection[i]})
    end
  end
end



function removeFKToTable(fromTable, toTable)
  local j, k
  
  for j= grtV.getn(fromTable.foreignKeys), 1, -1 do
    local fk= fromTable.foreignKeys[j]

    if (fk ~= nil) and grtV.toLua(fk.referedTable._id) == grtV.toLua(toTable._id) then
      grtV.remove(fromTable.foreignKeys, j)
      for k= grtV.getn(fromTable.columns), 1, -1 do
        local column= fromTable.columns[k]
        local l

        for l= 1, grtV.getn(fk.referedColumns) do
          if grtV.toLua(fk.columns[l]) == grtV.toLua(column._id) then
            grtV.remove(fromTable.columns, k)
            break
          end
        end
      end
    end
  end
end


-- ----------------------------------------------------------------------------------------
-- @brief Deletes all foreign keys that refer to the given table. Must be 
--        called when a table is deleted.
--
-- @param table that foreign keys point to.
--
-- @return success
-- ----------------------------------------------------------------------------------------
function deleteFKsWithTable(args)
  local delTable= args[1]
  local schemata
  local s, i, j, k
  local id= grtV.toLua(delTable.table._id)
  local tableName= grtV.toLua(delTable.table.name)

  schemata= delTable.table.owner.owner.schemata

  for s= 1, grtV.getn(schemata) do
    local schema= schemata[s]
    local schemaId= grtV.toLua(schema._id)
    local schemaName= grtV.toLua(schema.name)
    
    for i= 1, grtV.getn(schema.tables) do
      local tbl= schema.tables[i]

      for j= grtV.getn(tbl.foreignKeys), 1, -1 do
        local fk= tbl.foreignKeys[j]

        if (fk ~= nil) and ((grtV.toLua(fk.referedTableSchemaName) == schemaName) and (grtV.toLua(fk.referedTableName) == tableName) 
                            or grtV.toLua(fk.referedTable._id) == id) then
          grtV.remove(tbl.foreignKeys, j)
          for k= grtV.getn(tbl.columns), 1, -1 do
            local column= tbl.columns[k]
            local l

            for l= 1, grtV.getn(fk.referedColumns) do
              if grtV.toLua(fk.columns[l]) == grtV.toLua(column._id) then
                grtV.remove(tbl.columns, k)
                break
              end
            end
          end
        end
      end
    end
  end
  collectgarbage()
  return grt.success()
end



function deleteRelationship(args)
  local rel= args[1]

  -- when logical view is enabled, check if this is a n:m
  
  removeFKToTable(rel.startTable.table, rel.endTable.table)
  removeFKToTable(rel.endTable.table, rel.startTable.table)
  
  rel.startTable= nil
  rel.endTable= nil

  grtV.removeObj(rel.owner.currentView.relationships, rel)

  collectgarbage()

  return grt.success()
end


-- ----------------------------------------------------------------------------------------
-- @brief Traverses a SQL changes tree from the synchronization module and creates a 
--        single script string with all the snippets
--
-- @param tree the changes tree
--
-- @return script string
-- ----------------------------------------------------------------------------------------
function collectSqlScriptFromTree(tree)
  local script= nil
  local obj, text
  local children
  local i, c

  obj= tree["dbObject"]
  if obj == nil then
    obj= tree["modelObject"]
  end

  if obj ~= nil then    
    text= obj["sql"]
    if text ~= nil then
      text= grtV.toLua(text)
    end

    if text ~= nil then
      script= text
    end
    
    children= tree["children"]
    if children ~= nil then
      c= grtV.getn(children)
      for i= 1, c do
        text= collectSqlScriptFromTree(children[i])
        if text ~= nil then
          if script ~= nil then
            script= script .. ";\n" .. text
          else
            script= text
          end
        end
      end
    end
  end
  return script
end

function collectSqlScript(args)
  local script= collectSqlScriptFromTree(args[1])
  
  return grt.success(script)
end




function collapseExpandElements(elementList, flag, compartments)
  local i, c

  c= grtV.getn(elementList)

  for i= 1, c do
    local elem= elementList[i]
    local type= grtS.get(elem)

    if type == "db.workbench.TableElement" then
      if compartments ~= 0 then
        elem.columnsExpanded= flag
        elem.indicesExpanded= flag
        elem.foreignKeysExpanded= flag
        elem.triggersExpanded= flag
      else
        elem.expanded= flag
      end
    elseif type == "db.workbench.ViewElement" then
      if compartments == 0 then
        elem.expanded= flag
      end
    elseif type == "db.workbench.RoutineGroupElement" then
      if compartments == 0 then
        elem.expanded= flag
      end
    end
  end
end


function collapseElements(args)
  local view= grtV.lookupId(grtV.getGlobal("/workbench/model/currentView"))
  local compartments= grtV.toLua(args[1])

  if grtV.getn(view.selection) > 0 then
    return collapseExpandElements(view.selection, 0, compartments)
  else
    return collapseExpandElements(view.elements, 0, compartments)
  end
  return grt.success()
end


function expandElements(args)
  local view= grtV.lookupId(grtV.getGlobal("/workbench/model/currentView"))
  local compartments= grtV.toLua(args[1])
  
  if grtV.getn(view.selection) > 0 then
    return collapseExpandElements(view.selection, 1, compartments)
  else
    return collapseExpandElements(view.elements, 1, compartments)
  end
  return grt.success()
end


