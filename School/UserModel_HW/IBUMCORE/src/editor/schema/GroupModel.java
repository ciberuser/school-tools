package editor.schema;

import java.awt.Point;
import java.util.HashSet;
import java.util.LinkedHashMap;
//import java.util.Set;

import javax.swing.JOptionPane;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import editor.factory.ModelFactory;
import editor.factory.VertexFactory;
import editor.gui.Editor;
import editor.gui.Editor.ElementType;


/**
 * holds the group model 
 */
@XmlRootElement(name = "GroupModel")
@XmlType(propOrder = {  "thisGroupModelId", "selectedModelId",
		"vertexIdToModelIdMap","modelIdToVertexIdMap", 
		"modelIdToModelTypeMap",
		"modelIdToParentModelIdMap", "modelIdToSonModelIdMap"
		})
@XmlAccessorType(XmlAccessType.FIELD)

public class GroupModel extends Model{
	

	
	public enum ModelType {USERMODEL, GROUPMODEL, ACTIVATEDUSERMODEL, ACTIVATEDGROUPMODEL};
	
	private final Integer thisGroupModelId; // holds the ID of the current model
	private Integer selectedModelId;      // in a group model that contains other group or user models this parameter holds the id of the model currently selected for editing
	
	private LinkedHashMap<Integer, Integer> vertexIdToModelIdMap;	   // maps a vertex Id to the model Id
	private LinkedHashMap<Integer, String> modelIdToVertexIdMap; // maps the model Id to its vertices Ids, using a string to allow JABX marshaling to xml
	
	private LinkedHashMap<Integer,ModelType > modelIdToModelTypeMap;	   // maps the model id to the model type	
	private LinkedHashMap<Integer, String> modelIdToParentModelIdMap; // maps the model Id to its parent models Ids, using a string to allow JABX marshaling to xml
	private LinkedHashMap<Integer, String> modelIdToSonModelIdMap; // maps the model Id to its son models Ids, using a string to allow JABX marshaling to xml

		
	/**
	 * constructor , initializes all the linked hash maps of the group model
	 */
	public GroupModel() {
		
	
		super();

		thisGroupModelId = ModelFactory.getInstance().getCounterForNewModel();
		setSelectedModelId (thisGroupModelId);
		setModelId(thisGroupModelId);
		
		vertexIdToModelIdMap = new LinkedHashMap<>();
		modelIdToVertexIdMap = new LinkedHashMap<>();
		
		modelIdToModelTypeMap = new LinkedHashMap<>();
		modelIdToModelTypeMap.put(getThisGroupModelId(), ModelType.GROUPMODEL);
		
		modelIdToParentModelIdMap = new LinkedHashMap<>();
		modelIdToSonModelIdMap = new LinkedHashMap<>();
		
		
	}
	
	
	/*
	 * Converts a string of comma separated values into an array of integers
	 * @param s the comma separated string
	 * @return the Integer arrayLIst
	 */
	public HashSet<Integer> string2IDs(String s){
		HashSet<Integer> ids = new HashSet<Integer>();
		if (s=="") {
			return ids;
		} else {
			Integer subSIndex = 0;
			String subS = new String();
			subS = s;
			String convertedVal = new String();
			if (subS != null){
				while ( subS.length() > 0){
					subSIndex = subS.indexOf(",");
					if (subSIndex == 0){  // skip comma
						subS = subS.substring(subSIndex+1);
						subSIndex = subS.indexOf(",");
					} 
					if (subSIndex == -1){  // last item, comma not found
						subSIndex = subS.length();
					}  
					convertedVal = subS.substring(0, subSIndex);
					if (!convertedVal.equals("")){
						ids.add(Integer.parseInt(convertedVal));
					}
					subS = subS.substring(subSIndex);
				}
			}
			return ids;
		}
			
	}
	
	

	/*
	 * Converts an ArrayList of Integers into a string of comma separated values
	 * @param ids the Integer arrayLIst
	 * @return  the comma separated string
	 */
	public String ids2String(HashSet<Integer> vertexIds){
		if (vertexIds.isEmpty()) {
			return "";
		} else {
			String s = new String();
			for (Integer id : vertexIds){
				s = s + id.toString() + ",";
			}
			s = s.substring(0, s.length() - 1); // remove last comma
			return s;
		}
	}
	
//	
	public void close(){

		super.close();
		
		vertexIdToModelIdMap.clear();
		modelIdToVertexIdMap.clear();
		
		modelIdToModelTypeMap.clear();
		
		modelIdToParentModelIdMap.clear();
		modelIdToSonModelIdMap.clear();
		
	}
	
	
	
	 /**
	 * gets a vertex ID and handles its mapping to the group model
	 * @param vertexId    The vertex ID
	 */
	
	public void addVertexToGroup(Integer vertexId, Integer modelId){
		
		HashSet<Integer> vertexIds = new HashSet<Integer>();
		if (modelIdToVertexIdMap.containsKey(modelId)){
			vertexIds.addAll(string2IDs(modelIdToVertexIdMap.get(modelId)));
		}
		vertexIds.add(vertexId);
		modelIdToVertexIdMap.put(modelId, ids2String(vertexIds));
			
		
		vertexIdToModelIdMap.put(vertexId, modelId);
	}
	
	

	/**
	 * Gets a vertex ID and removes it from the group model mapping
	 * @param vertexId    The vertex ID
	 */
	public void removeVertexFromGroup(Integer vertexId){
		
		HashSet<Integer> vertexIds = new HashSet<Integer>();
		Integer modelId = vertexIdToModelIdMap.get(vertexId);
		vertexIds.addAll(string2IDs(modelIdToVertexIdMap.get(modelId)));
		if (vertexIds.contains(vertexId)){
			vertexIds.remove(vertexId);
		}
		modelIdToVertexIdMap.put(modelId, ids2String(vertexIds));

		vertexIdToModelIdMap.remove(vertexId);
		
			
	}


	/**
	 * Copies and creates vertex content into a new vertex
	 * @param inVertex the copied vertex
	 * @return the new vertex with the same content and a different Id
	 */
	public Vertex CopyAndCreateVertex (Vertex inVertex){
		
		Vertex outVertex = new Vertex();
		String vName;
		ElementType vType;
		Point vPoint = new Point();
		String[] metadataInput;
		String[] metadataOutput;
		
		vName = inVertex.getName();
		vType = inVertex.getType();
		vPoint.setLocation(inVertex.getX(), inVertex.getY());
		metadataInput = inVertex.getInputs();
		metadataOutput = inVertex.getOutputs();
		outVertex = VertexFactory.getInstance().copyAndCreate(vName, metadataInput, metadataOutput, vType, vPoint);

		return outVertex;
	}
	
	
	
	
	/** Adds a new sub--model to the current model
	 * 
	 * @Param model  the group model to be added
	 * @return the sub-Model Id
	 */
	public Integer addNewSubGroupModel(GroupModel model){
		
		
		 LinkedHashMap<Integer, Integer> oldToNewModelId = new  LinkedHashMap<Integer, Integer>();
		 for (Integer oldId : model.getModelIdToModelTypeMap().keySet()){
			 oldToNewModelId.put(oldId, ModelFactory.getInstance().getCounterForNewModel());
		 }

		 // manage model type mapping
		 for (Integer oldId : model.getModelIdToModelTypeMap().keySet()){
			this.modelIdToModelTypeMap.put(oldToNewModelId.get(oldId) , model.getModelIdToModelTypeMap().get(oldId));
		 }
			 
			// Manage model parent structure mapping
		 for (Integer oldId : model.getModelIdToParentModelIdMap().keySet()){
			 HashSet<Integer> oldPaerntsModelSet = new HashSet<Integer>();
			 HashSet<Integer> newPaerntsModelSet = new HashSet<Integer>();
			 oldPaerntsModelSet.addAll(string2IDs(model.getModelIdToParentModelIdMap().get(oldId)));
			 for (Integer parentId : oldPaerntsModelSet){
				 newPaerntsModelSet.add(oldToNewModelId.get(parentId));
			 }
//			 this.modelIdToParentModelIdMap.remove(oldToNewModelId.get(oldId));
			 this.modelIdToParentModelIdMap.put(oldToNewModelId.get(oldId), ids2String(newPaerntsModelSet));
		 }
		 HashSet<Integer> thisParent = new HashSet<Integer>();
		 thisParent.add(this.getModelId());
		 
		 this.modelIdToParentModelIdMap.remove(oldToNewModelId.get(model.getModelId()));
		 this.modelIdToParentModelIdMap.put(oldToNewModelId.get(model.getModelId()), ids2String(thisParent));
		 
		 
			// Manage model son structure mapping
		 for (Integer oldId : model.getModelIdToSonModelIdMap().keySet()){
			 HashSet<Integer> oldSonsModelSet = new HashSet<Integer>();
			 HashSet<Integer> newSonsModelSet = new HashSet<Integer>();
			 oldSonsModelSet.addAll(string2IDs(model.getModelIdToSonModelIdMap().get(oldId)));
			 for (Integer sonId : oldSonsModelSet){
				 newSonsModelSet.add(oldToNewModelId.get(sonId));
			 }
			 this.modelIdToSonModelIdMap.remove(oldToNewModelId.get(oldId));
			 this.modelIdToSonModelIdMap.put(oldToNewModelId.get(oldId), ids2String(newSonsModelSet));
		 }

		 HashSet<Integer> thisSon = new HashSet<Integer>();
		 thisSon.addAll(string2IDs(this.getModelIdToSonModelIdMap().get(this.getModelId())));
		 thisSon.add(oldToNewModelId.get(model.getModelId()));
		 this.modelIdToSonModelIdMap.put(this.getModelId(), ids2String(thisSon));
		 
		 return oldToNewModelId.get(model.getModelId());
		 
	}
		 
		 
		 
	/** Adds a new sub-user-model to the current model
	 * 
	 * @Param model  the user model to be added
	 * @return the sub-Model Id
	 */
	public Integer addNewSubUserModel(UserModel model){
		
		
		Integer newModelId = ModelFactory.getInstance().getCounterForNewModel();
		 
		// manage model type mapping
		this.modelIdToModelTypeMap.put(newModelId,ModelType.USERMODEL);
			 
		// Manage model parent structure mapping
		 HashSet<Integer> newPaerntsModelSet = new HashSet<Integer>();
		 newPaerntsModelSet.add(this.getModelId());
		 this.modelIdToParentModelIdMap.put(newModelId, ids2String(newPaerntsModelSet));
		 
		// Manage model son structure mapping
		 HashSet<Integer> newSonsModelSet = new HashSet<Integer>();
		 Integer thisId = this.getModelId();
		 if (this.getModelIdToSonModelIdMap().get(thisId) != null){
			 newSonsModelSet.addAll(string2IDs(this.getModelIdToSonModelIdMap().get(thisId)));
		 }
		 newSonsModelSet.add(newModelId);
		 this.modelIdToSonModelIdMap.put(thisId, ids2String(newSonsModelSet));
			 
		 return newModelId;
		 
	}
		 
		 

	/** Adds a new sub-info-pendnt to the current model
	 * 
	 * @Param model  the info-pendant to be added
	 * @return the sub-Model Id
	 */
	public void addNewSubInfoPendant(InfoPendant infoPendant){
		
		
	   // Manage vertices map
		 for (Integer oldVertexId : infoPendant.getVertexMap().keySet()){
			 Vertex oldVertex = infoPendant.getVertexMap().get(oldVertexId);
			 this.CopyAndCreateVertex(oldVertex);
		 }
		 
	}
		 
	
	
	/** Removes a  sub-user-model from the current group model
	 * 
	 * @Param deletedModelId  the ID of the user model to be removed
	 */
	public void removeSubUserModel(Integer deletedModelId){
		
		if ((deletedModelId == null) || (!modelIdToModelTypeMap.containsKey(deletedModelId)) || (!modelIdToModelTypeMap.get(deletedModelId).equals(ModelType.USERMODEL)) ){
			JOptionPane.showMessageDialog(null, "*** Erorr, <removeSubUserModel> Illegal user model type");
			return;
		}
				
		LinkedHashMap<Integer, Vertex> vMap = new LinkedHashMap<>();
		vMap.putAll(this.getVertexMap());
		for (Integer vId : vMap.keySet()){
			if (vertexIdToModelIdMap.get(vId) != null){
				if (vertexIdToModelIdMap.get(vId).equals(deletedModelId)) {
					this.removeVertex(vMap.get(vId));
					removeVertexFromGroup(vId);
					Editor.getInstance().getGraphDisplay().removeVertexFromGraph(vId);
				}
			}
		}
		
		// manage model type mapping
		modelIdToModelTypeMap.remove(deletedModelId);
		 
		// Manage model structure mapping
		modelIdToParentModelIdMap.remove(deletedModelId);
		for (Integer mId : modelIdToSonModelIdMap.keySet()){
			HashSet<Integer> modelSet = new HashSet<Integer>();
			modelSet.addAll(string2IDs(modelIdToSonModelIdMap.get(mId)));
			modelSet.remove(deletedModelId);
			modelIdToSonModelIdMap.put(mId, ids2String(modelSet));
		}
		modelIdToSonModelIdMap.remove(deletedModelId);
	}


	/** removes a new sub-group-model to the current group model
	 * 
	 * @Param deletedGroupModelId  the group model to be removed
	 */
	public void removeSubGroupModel(Integer deletedGroupModelId){
		
		if ((deletedGroupModelId == null) || (!modelIdToModelTypeMap.containsKey(deletedGroupModelId)) || (!modelIdToModelTypeMap.get(deletedGroupModelId).equals(ModelType.GROUPMODEL)) ){
			JOptionPane.showMessageDialog(null, "*** Erorr, <removeSubUserModel> Illegal group model type");
			return;
		}

		// Remove all son models
		HashSet<Integer> modelSet = new HashSet<Integer>();
		modelSet.addAll(string2IDs(modelIdToSonModelIdMap.get(deletedGroupModelId)));
		if ((modelSet != null) && (!modelSet.isEmpty())){
			for (Integer sonModelId : modelSet){
				if (modelIdToModelTypeMap.get(sonModelId) != null){
					if (modelIdToModelTypeMap.get(sonModelId).equals(ModelType.GROUPMODEL) ){
						removeSubGroupModel(sonModelId);
					} else if (modelIdToModelTypeMap.get(sonModelId).equals(ModelType.USERMODEL) ){
						removeSubUserModel(sonModelId);
					}
				}
			}
		}
		
		for (Integer mId : modelIdToSonModelIdMap.keySet()){
			modelSet = new HashSet<Integer>();
			modelSet.addAll(string2IDs(modelIdToSonModelIdMap.get(mId)));
			modelSet.remove(deletedGroupModelId);
			modelIdToSonModelIdMap.put(mId, ids2String(modelSet));
		}
		modelIdToSonModelIdMap.remove(deletedGroupModelId);
		
		// manage parent collection
		modelIdToParentModelIdMap.remove(deletedGroupModelId);

		
		// manage group vertices		
		LinkedHashMap<Integer, Vertex> vMap = new LinkedHashMap<>();
		vMap.putAll(this.getVertexMap());
		for (Integer vId : vMap.keySet()){
			if (vertexIdToModelIdMap.get(vId) != null){
				if (vertexIdToModelIdMap.get(vId).equals(deletedGroupModelId)) {
					this.removeVertex(vMap.get(vId));
					removeVertexFromGroup(vId);
					Editor.getInstance().getGraphDisplay().removeVertexFromGraph(vId);

				}
			}
		}
		
		// manage model type mapping
		modelIdToModelTypeMap.remove(deletedGroupModelId);
		 
	}
	
	
	
	//---------------  Getters and setters  ------------------
	public Integer getSelectedModelId() {
		return selectedModelId;
	}
	public void setSelectedModelId(Integer selectedModelId) {
		this.selectedModelId = selectedModelId;
	}
	public Integer getThisGroupModelId() {
		return thisGroupModelId;
	}
	public LinkedHashMap<Integer, Integer> getVertexIdToModelIdMap() {
		return vertexIdToModelIdMap;
	}
	public LinkedHashMap<Integer, String> getModelIdToVertexIdMap() {
		return modelIdToVertexIdMap;
	}
	public LinkedHashMap<Integer, ModelType> getModelIdToModelTypeMap() {
		return modelIdToModelTypeMap;
	}
	public LinkedHashMap<Integer, String> getModelIdToParentModelIdMap() {
		return modelIdToParentModelIdMap;
	}
	public LinkedHashMap<Integer, String> getModelIdToSonModelIdMap() {
		return modelIdToSonModelIdMap;
	}
	public void setVertexIdToModelIdMap(
			LinkedHashMap<Integer, Integer> vIdToModelIdMap) {
		this.vertexIdToModelIdMap = vIdToModelIdMap;
	}
	public void setModelIdToVertexIdMap(
			LinkedHashMap<Integer, String> mIdToVertexIdMap) {
		this.modelIdToVertexIdMap = mIdToVertexIdMap;
	}
	public void setModelIdToModelTypeMap(
			LinkedHashMap<Integer, ModelType> mIdToModelTypeMap) {
		this.modelIdToModelTypeMap = mIdToModelTypeMap;
	}
	public void setModelIdToParentModelIdMap(
			LinkedHashMap<Integer, String> mIdToParentModelIdMap) {
		this.modelIdToParentModelIdMap = mIdToParentModelIdMap;
	}
	public void setModelIdToSonModelIdMap(
			LinkedHashMap<Integer, String> mIdToSonModelIdMap) {
		this.modelIdToSonModelIdMap = mIdToSonModelIdMap;
	}
}