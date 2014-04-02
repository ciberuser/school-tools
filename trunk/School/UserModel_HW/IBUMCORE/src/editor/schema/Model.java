package editor.schema;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import editor.factory.EdgeFactory;

/**
 * holds the  model 
 */
//@XmlRootElement(name = "Model")
@XmlType(name = "Model", propOrder = { "modelId", "vertexMap", "edgeMap", "outputLinkMap", "inputLinkMap" })
@XmlAccessorType(XmlAccessType.FIELD)
public class Model {
	
	private Integer modelId = -1;
	
	private LinkedHashMap<Integer, Vertex>	vertexMap;
	
	private LinkedHashMap<Integer, Edge>	edgeMap;
	
//	private LinkedHashMap<String, ArrayList<Integer>> outputLinkMap;
//	private LinkedHashMap<String, ArrayList<Integer>> inputLinkMap;	
	private LinkedHashMap<String, String> outputLinkMap;  // the second string contains a comma separated list of integer IDs
	private LinkedHashMap<String, String> inputLinkMap;	 // the second string contains a comma separated list of integer IDs
	
	/**
	 * constructor , initializes all the linked hash maps of the model
	 */
	public Model() {
		vertexMap = new LinkedHashMap<>();
		edgeMap = new LinkedHashMap<>();
		outputLinkMap = new LinkedHashMap<>();
		inputLinkMap = new LinkedHashMap<>();
	}
	
	/*
	 * Converts a string of comma separated values into an array of integers
	 * @param s the comma separated string
	 * @return the Integer arrayLIst
	 */
	public ArrayList<Integer> string2ArrayListIDs(String s){
		ArrayList<Integer> ids = new ArrayList<Integer>();
		if (s==""){
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
	public String arrayListIds2String(ArrayList<Integer> vertexIds){
		if (vertexIds.isEmpty()){
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
	
	
	public void close(){
		vertexMap.clear();
		edgeMap.clear();
		outputLinkMap.clear();
		inputLinkMap.clear();

	}
	
	/**
	 * gets a vertex and:
	 * 	1) adds the vertex to the vertexMap
	 * 	2) adds links between the vertex and all the matching existing vertices
	 *  3) adds the inputs & outputs of the vertex to the output&input linkMaps
	 * @param vertex 
	 * @return			an ArrayList of edges/links that were created
	 */
	public ArrayList<Edge> addVertex(Vertex vertex){
		Integer vertexId = vertex.getId();
		ArrayList<Edge> createdEdges = null;
		
		String[] vertexInputs = vertex.getInputs();
		String[] vertexOutputs = vertex.getOutputs();
		
		// 1) add the vertex to the model (to the vertexMap)
		vertexMap.put(vertex.getId(), vertex);
		
		// 2) create and add the matching links/edges between the added vertex and all the matching existing vertices
		createdEdges = addLinks(vertex);
		
		// 3) add the vertex's input & output interfaces to the existing ones in the model - to outpurLinkMap & inputLinkMap
		
		// go over the inputs of the vertex and add them to inputLinkMap
		if(vertexInputs != null){
			for(String input : vertexInputs){
				ArrayList<Integer> existingInputs = string2ArrayListIDs(inputLinkMap.get(input));
				
				// if this is the first vertex that implements this interface as an input , initialize an ArrayList for it
				if(existingInputs == null){
					existingInputs = new ArrayList<>();
//					inputLinkMap.put(input, arrayListIds2String(existingInputs));
				}
				
				existingInputs.add(vertexId);
				inputLinkMap.put(input, arrayListIds2String(existingInputs));
			}
			

		}
	
				
		// go over the outputs of the vertex and add them to outputLinkMap
		if(vertexOutputs != null){
			for(String output : vertexOutputs){
				ArrayList<Integer> existingOutputs = string2ArrayListIDs(outputLinkMap.get(output));
				
				// if this is the first vertex that implements this interface as an input , initialize an ArrayList for it
				if(existingOutputs == null){
					existingOutputs = new ArrayList<>();
//					outputLinkMap.put(output, arrayListIds2String(existingOutputs));
				}
				
				existingOutputs.add(vertexId);
				outputLinkMap.put(output, arrayListIds2String(existingOutputs));
			}

//			Editor.getInstance().log("edgeMap: " + edgeMap.toString());
//			Editor.getInstance().log("vertexMap: " + vertexMap.toString());
//			Editor.getInstance().log("outputLinkMap: " + outputLinkMap.toString());
//			Editor.getInstance().log("inputLinkMap: " + inputLinkMap.toString());
//			Editor.getInstance().log("--------------ADD END---------------");

		}
		
		
		return createdEdges;
	}
	
	/**
	 * get a Vertex - v and create the links between v and all matching existing vertices in the model
	 * @param vertex 	add links/edges to all matching interfaces between the vertex and the existing vertices in the model
	 * @return			ArrayList<Edge> of the created edges (all the edges that are connected to the vertex) / null if no edge was created
	 */
	public ArrayList<Edge> addLinks(Vertex vertex){
		ArrayList<Edge> createdEdges = new ArrayList<>();
		
		Integer vertexId = vertex.getId();
		String[] vertexInputs = vertex.getInputs();
		String[] verexOutputs = vertex.getOutputs();
		
		// create links to all matching existing outputs to the vertex inputs
		if(vertexInputs != null) {
			for(String input : vertexInputs){
				ArrayList<Integer> matchingOutputs = string2ArrayListIDs(outputLinkMap.get(input));
				if(matchingOutputs != null){
					for(Integer fromId : matchingOutputs){
						if(fromId != vertexId){
							Edge edge = EdgeFactory.getInstance().create(fromId, vertexId, input);
							if(edge != null) {
								edgeMap.put(edge.getId(), edge);
								createdEdges.add(edge);
							}
						}
					}
				}
			}
		}

		
		// create links to all matching existing inputs to the vertex outputs
		if(verexOutputs != null){
			for(String output:verexOutputs){
				ArrayList<Integer> matchingInputs = string2ArrayListIDs(inputLinkMap.get(output));
				if(matchingInputs != null){
					for(Integer toId : matchingInputs){
						if(toId != vertexId){
							Edge edge = EdgeFactory.getInstance().create(vertexId, toId, output);
							if(edge != null){
								edgeMap.put(edge.getId(), edge);
								createdEdges.add(edge);
							}
						}
					}
				}
			}
		}
				
		if(createdEdges.isEmpty()){
			createdEdges = null;
		}
		
		return createdEdges;
	}

	
	
	
	/**
	 * get an edge id , and change the matching edges type form/to : DIRECTED<==>UNDIRECTED
	 * @param edgeId	an edge which type we want to change
	 */
	public void changeLink(Integer edgeId){	
		Edge edge = edgeMap.get(edgeId);
		if(edge != null){
			edge.changeType();
		}
	}
	
	
	
	
	
	/**
	 * get a Vertex - v and remove the links between v and all matching existing vertices in the model
	 * Removes edge of current vertex from its successor and predecessor linkedMaps
	 * @param vertex 	add links/edges to all matching interfaces between the vertex and the existing vertices in the model
	 * @return			ArrayList<Edge> of the deleted edges (all the edges that are connected to the vertex) / null if no edge was created
	 */
	public ArrayList<Edge> removeLinks(Vertex vertex){
		
		
		ArrayList<Edge> removedEdges = new ArrayList<>();
		

		//---------- delete the edges  -----------------
		
		Integer vertexId = vertex.getId();
		
		String[] vertexInputs = vertex.getInputs();
		String[] vertexOutputs = vertex.getOutputs();
		
		
		// create links to all matching existing outputs to the vertex inputs
		if(vertexInputs != null) {
			for (Integer i=0; i < vertexInputs.length; i++){
				String input = vertexInputs[i];
				ArrayList<Integer> matchingOutputs = string2ArrayListIDs(outputLinkMap.get(input));
				if(matchingOutputs != null){
					for (Integer j=0; j<matchingOutputs.size(); j++){
						Integer fromId = matchingOutputs.get(j);
						if(fromId != vertexId){
							Boolean found = false;
							Integer removedEdgeID = -1;;
							if (fromId != null && vertexId != null && input != null){
								for (Integer k : edgeMap.keySet()){
									if (!found){
										Edge currentEdge = edgeMap.get(k);
										if ((currentEdge.getLinkName().equals(input)) && (currentEdge.getSourceId() == fromId) && (currentEdge.getTargetId() == vertexId)) {
											removedEdges.add(currentEdge);
											removedEdgeID = k;
											found = true;
										}
									}
								}
								if (removedEdgeID > -1){
									edgeMap.remove(removedEdgeID);
								}
							}
						}
					}
				}
			}
		}
		

		
		// create links to all matching existing inputs to the vertex outputs
		if(vertexOutputs != null){
			for (Integer i=0; i < vertexOutputs.length; i++){
				String output = vertexOutputs[i];
				ArrayList<Integer> matchingInputs = string2ArrayListIDs(inputLinkMap.get(output));
				if(matchingInputs != null){
					for (Integer j=0; j<matchingInputs.size(); j++){
						Integer toId = matchingInputs.get(j);
						if(toId != vertexId){
							Boolean found = false;
							Integer removedEdgeID = -1;;
							if (toId != null && vertexId != null && output != null){
								for (Integer k : edgeMap.keySet()){
									if (!found){
										Edge currentEdge = edgeMap.get(k);
										if ((currentEdge.getLinkName().equals(output)) && (currentEdge.getSourceId() == vertexId) && (currentEdge.getTargetId() == toId)) {
											removedEdges.add(currentEdge);
											removedEdgeID = k;
											found = true;
										}
									}
								}
								if (removedEdgeID > -1){
									edgeMap.remove(removedEdgeID);
								}
							}
						}
					}
				}
			}

		}
		
		
		if(removedEdges.isEmpty()){
			removedEdges = null;
		}
		
		
		//--------- delete the vertexID itself from inputLinkMAp and outputLinkMap --------------------
		

		
		ArrayList<String> removeVertexKey = new ArrayList<String>();
		Integer index=-1;
		
		for (String vertexOut : inputLinkMap.keySet() ){
			ArrayList<Integer> vertexOutIds = string2ArrayListIDs(inputLinkMap.get(vertexOut));
			for (Integer id : vertexOutIds ){
				if (id == vertexId){
					removeVertexKey.add(vertexOut);
				}
			}
		}	
			
		for (String interfaceName : removeVertexKey ){
			ArrayList<Integer> interfaceVertexIds = string2ArrayListIDs(inputLinkMap.get(interfaceName));
			index = interfaceVertexIds.indexOf(vertexId);
			interfaceVertexIds.remove((int) index);
			if (interfaceVertexIds.isEmpty()){
				inputLinkMap.remove(interfaceName);
			} else {
				inputLinkMap.put(interfaceName, arrayListIds2String(interfaceVertexIds));
			}
		}
			
		
		removeVertexKey.clear();
		index=-1;
	
		for (String vertexIn : outputLinkMap.keySet() ){
			ArrayList<Integer> vertexInIds = string2ArrayListIDs(outputLinkMap.get(vertexIn));
			for (Integer id : vertexInIds ){
				if (id == vertexId) {
					removeVertexKey.add(vertexIn);
				}
			}
		}	
		
		for (String interfaceName : removeVertexKey ){	
			ArrayList<Integer> interfaceVertexIds = string2ArrayListIDs(outputLinkMap.get(interfaceName));
			index = interfaceVertexIds.indexOf(vertexId);
			interfaceVertexIds.remove((int) index);
			if (interfaceVertexIds.isEmpty()){
				outputLinkMap.remove(interfaceName);
			} else {
				outputLinkMap.put(interfaceName, arrayListIds2String(interfaceVertexIds));
			}
			
		}		

		return removedEdges;
	}
	


	public void removeVertex(Vertex vertex){

		Integer vertexId = vertex.getId();

//		Editor.getInstance().log("edgeMap: " + edgeMap.toString());
//		Editor.getInstance().log("vertexMap: " + vertexMap.toString());
//		Editor.getInstance().log("outputLinkMap: " + outputLinkMap.toString());
//		Editor.getInstance().log("inputLinkMap: " + inputLinkMap.toString());
//		Editor.getInstance().log("--------------start---------------");
		

		
		// 1) Remove  the matching links/edges between the added vertex and all the matching existing vertices in the model
		removeLinks(vertex);
		
		// 2) Remove the vertex from the model (to the vertexMap)
		this.getVertexMap().remove(vertexId);
		
		
//		// 3) Remove vertex from the displayed graph
//		Editor.getInstance().deleteInfoBead(vertexId);
//		
		
//		Editor.getInstance().log("edgeMap: " + edgeMap.toString());
//		Editor.getInstance().log("vertexMap: " + vertexMap.toString());
//		Editor.getInstance().log("outputLinkMap: " + outputLinkMap.toString());
//		Editor.getInstance().log("inputLinkMap: " + inputLinkMap.toString());
//		Editor.getInstance().log("--------------END---------------");

	}


	/* ~~~~~~~~~~~~~~~~~ getters & setters ~~~~~~~~~~~~~~~~~*/
	
	public LinkedHashMap<Integer, Vertex> getVertexMap() {
		return vertexMap;
	}

	public void setVertexMap(LinkedHashMap<Integer, Vertex> vertexMap) {
		this.vertexMap = vertexMap;
	}

	public LinkedHashMap<Integer, Edge> getEdgeMap() {
		return edgeMap;
	}

	public void setEdgeMap(LinkedHashMap<Integer, Edge> edgeMap) {
		this.edgeMap = edgeMap;
	}

	public LinkedHashMap<String, String> getOutputLinkMap() {
		return outputLinkMap;
	}

	public void setOutputLinkMap(
			LinkedHashMap<String,String> outputLinkMap) {
		this.outputLinkMap = outputLinkMap;
	}

	public LinkedHashMap<String, String> getInputLinkMap() {
		return inputLinkMap;
	}

	public void setInputLinkMap(
			LinkedHashMap<String, String> inputLinkMap) {
		this.inputLinkMap = inputLinkMap;
	}

	public Integer getModelId() {
		return modelId;
	}

	public void setModelId(Integer modelId) {
		this.modelId = modelId;
	}
	
	

}
