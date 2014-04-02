
package modelExport;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import architectureUtil.DirectoriesSetup;


import editor.gui.Editor;
import editor.schema.*;
import edu.uci.ics.jung.graph.util.EdgeType;

/**
 * Generates a main class for a new model
 * @author Eyal
 *
 */

public class GenerateModel {
	
	
	/**
	 * selects a user model for activation 
	 * @param userModel the user model to be activated
	 */
		public void saveModelMainSrc(UserModel userModel){

			LinkedHashMap<Integer, Vertex> infoBeadMap = new LinkedHashMap<>();
			LinkedHashMap<Integer, Edge> linksMap = new LinkedHashMap<>();
			LinkedHashMap<Integer, Integer> infobeadIdToModelIdMap = new LinkedHashMap<Integer, Integer>();
			infoBeadMap.putAll(userModel.getVertexMap());
			linksMap.putAll(userModel.getEdgeMap());
			for (int key : infoBeadMap.keySet() ){
				infobeadIdToModelIdMap.put(key, 1); // single model
			}
			setModelForActivation(infoBeadMap, linksMap, infobeadIdToModelIdMap);
			setModelForActivationFixed(infoBeadMap, linksMap, infobeadIdToModelIdMap);
		}
			
		/**
		 * selects a group model for activation 
		 * @param groupModel the user model to be activated
		 */
			public void saveModelMainSrc(GroupModel groupModel){

				LinkedHashMap<Integer, Vertex> infoBeadMap = new LinkedHashMap<>();
				LinkedHashMap<Integer, Edge> linksMap = new LinkedHashMap<>();
				LinkedHashMap<Integer, Integer> infobeadIdToModelIdMap = new LinkedHashMap<Integer, Integer>();
				infoBeadMap.putAll(groupModel.getVertexMap());
				linksMap.putAll(groupModel.getEdgeMap());
				infobeadIdToModelIdMap.putAll(groupModel.getVertexIdToModelIdMap());
				setModelForActivation(infoBeadMap, linksMap, infobeadIdToModelIdMap);
				setModelForActivationFixed(infoBeadMap, linksMap, infobeadIdToModelIdMap);
			}
				
	
			
			/**
			 * Save a ready made activated model to an XML file
			 * @param file the file to save the data
			 * @param activatedModel the model to be saved
			 * @return boolean : true if succeeded  or false if failed
			 */
			public boolean saveActivatedModelToXML(File file, CustomActivatedModel activatedModel) {
				try {
					JAXBContext context = JAXBContext.newInstance(CustomActivatedModel.class);
					Marshaller marshaller = context.createMarshaller();
					marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
							Boolean.TRUE);

					// write to xml 
					marshaller.marshal(activatedModel, file);
					return true;

				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Failed to save the activated model");
					return false;
				}
			}

			/**
			 * save model data for activation from an external file
			 */
			public void setModelForActivationFixed(LinkedHashMap<Integer, Vertex> infoBeadsMap,  LinkedHashMap<Integer, Edge> linksMap, LinkedHashMap<Integer, Integer> infobeadIdToModelIdMap){
				final String FILE_NAME = "ActivatedModel.eam";
				DirectoriesSetup dir1 = new DirectoriesSetup();
				final String ACTIVATED_PATH = dir1.getActivatedModelsPath();
				File activationFile = new File(ACTIVATED_PATH + FILE_NAME);
				
				if (infoBeadsMap == null  ||  infoBeadsMap.isEmpty() || infoBeadsMap == null  ||  infoBeadsMap.isEmpty() 
						|| infobeadIdToModelIdMap == null  ||  infobeadIdToModelIdMap.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Failed to save the activated model - missing info-beads or info-links or modle id");
					return;
				}

				
				CustomActivatedModel activatedModel = new CustomActivatedModel();
				ArrayList<String> infoBeadsData = new ArrayList<String>();
				ArrayList<String> infoLinksData = new ArrayList<String>();
			
				for (Integer infoBeadIndex : infoBeadsMap.keySet()){
					
						String infoBeadName = "";
						String infoBeadModelId = "";
						
						infoBeadName = infoBeadsMap.get(infoBeadIndex).getName() + "_" + infoBeadIndex ;
						
						String modelType = "UM"; // dummy initialization
						if (Editor.getInstance().isEditingGroupModel()){ // change mTpe to GM if the info bead directly belongs to a group model
							String vertexModelType = Editor.getInstance().getGroupModel().getModelIdToModelTypeMap().get(infobeadIdToModelIdMap.get(infoBeadIndex)).toString();
							if (vertexModelType.equals("GROUPMODEL")){
								modelType = "GM";
							}
						}

						infoBeadModelId =  modelType + infobeadIdToModelIdMap.get(infoBeadIndex);  
						
						infoBeadsData.add(infoBeadName + "," + infoBeadModelId);
				}
					
				for (Integer linkIndex : linksMap.keySet()){
					if (linksMap.get(linkIndex).getType().equals(EdgeType.DIRECTED)) {
						Integer sourceId = linksMap.get(linkIndex).getSourceId();
						Integer targetId = linksMap.get(linkIndex).getTargetId();
						String infoLinkSource = infoBeadsMap.get(sourceId).getName() + "_" + infoBeadsMap.get(sourceId).getId();
						String infoLinkTarget = infoBeadsMap.get(targetId).getName() + "_" + infoBeadsMap.get(targetId).getId();
						infoLinksData.add(infoLinkSource + "," + infoLinkTarget);
					}
				}
				
				activatedModel.setInfoBeads(infoBeadsData);
				activatedModel.setInfoLinks(infoLinksData);
				
				saveActivatedModelToXML(activationFile, activatedModel);
				
			}
			
	
	/**
	 * Generates and saves a user model activation java source file 
	 * @param userModel the user model whose main is generated
	 */
		public void setModelForActivation(LinkedHashMap<Integer, Vertex> infoBeadMap,  LinkedHashMap<Integer, Edge> linksMap, LinkedHashMap<Integer, Integer>infobeadIdToModelIdMap){

			
			final String FILE_PATH = Editor.getInstance().getDSetup().getReadyModelsPath();
			final String FILE_NAME = "ActivatedModel.java";
			final String INFOBEADS_PATH = Editor.getInstance().getDSetup().getInfobeadsBinUrlPath();
			
			File activationFile = new File(FILE_PATH + FILE_NAME);
	
			String javaSource = ""; 
			

			// program start
			javaSource = javaSource + 
				"package models;\n\n" +
				"import genericInfoBead.InfoBead;\n"+
				"import java.net.URL;\n"+
				"import java.net.URLClassLoader;\n"+
				"\n"+
				"/**"+
				"* An automatically generated model activation source\n"+
				"*\n"+
				"* Step 1: create the info-beads\n"+
				"* Step 2: set activated links between the info-beads\n"+  
				"* Step 3: initialize the info-beads\n"+  
				"*\n"+ 
				"* @author Eyal Dim\n"+
				"* @ Version 1.0\n"+
				"*/\n"+
				"public class ActivatedModel {\n"+  
				"\n"+
				"	public ActivatedModel(){\n" +
				"		this.runModel();\n" +
				"	}\n" +
				"\n" +
				"	public void runModel() {\n" +
				"		\n"+
				"		try {\n"+
				"			\n"+
				"			// Step 1: load the info-beads from the Eclipse work folder\n"+
				"			URL[] urls;\n"+
//				"			urls = new URL[]{(new File(\".\")).toURI().toURL()};\n"+
				"			URL filePath = new URL(\" "+ INFOBEADS_PATH + "\");\n" +
				"			urls = new URL[]{filePath};\n" +
				"			URLClassLoader classLoader = new URLClassLoader (urls, this.getClass().getClassLoader());\n" +
				"			\n";

			
			
			/* 
			 * get a new instance of a info-bead
			 * 
			 * The following should look at the end like this source code:
			 * 
			 * 	Class<?> cls# = Class.forName("infoBeadType.infoBeadName", true, classLoader);			
			 * 	InfoBead infoBeadName = (InfoBead)cls#.newInstance();
			 * 	infoBeadName.setInfoBeadId("#");
			 * 
			 * 		Where:
			 * 			 # 				is the vertex ID
			 * 			 infoBeadType   is the info-bead folder (sensor, service or regular info-bead)
			 * 			 infoBeadName	is the info-bead name
			 */
			for (Integer infoBeadId : infoBeadMap.keySet()){
				Vertex infoBead = infoBeadMap.get(infoBeadId);
				String loadedInfoBead = "";
				loadedInfoBead += "\t\t\tClass<?> cls_" + infoBeadId + " = Class.forName(\"";
				loadedInfoBead += "infobeadCollection.";  // the package name
				loadedInfoBead += infoBead.getName() +
						"\", true, classLoader);\n\t\t\tInfoBead " + 
						infoBead.getName() + "_" + infoBeadId +
						"= (InfoBead)cls_"+ infoBeadId + ".newInstance();\n";
				loadedInfoBead += "\t\t\t" + infoBead.getName() + "_" + infoBeadId +
						".setInfoBeadId(\"" + infoBead.getName() + "_" + infoBeadId + "\");\n";
				loadedInfoBead += "\t\t\t" + infoBead.getName() + "_" + infoBeadId +
						".setInfobeadModelId(";
				String mType = "UM"; // model type if info-bead directly belongs to a user model 
				if (Editor.getInstance().isEditingGroupModel()){ // change mTpe to GM if the info bead directly belongs to a group model
					String vertexModelType = Editor.getInstance().getGroupModel().getModelIdToModelTypeMap().get(infobeadIdToModelIdMap.get(infoBeadId)).toString();
					if (vertexModelType.equals("GROUPMODEL")){
						mType = "GM";
					}
				}
				loadedInfoBead +=  "\"" + mType +infobeadIdToModelIdMap.get(infoBeadId) + "\");\n\n";
				javaSource += loadedInfoBead;
			}

			
			
			/*
			 * Step 2: Connect linked info-beads
			 * 
			 * The following should look like this source code:
			 * 
			 * 	SendingInfoBead#.connect(ReceivingInfoBead#);
			 * 
			 *  	Where # is the id of the vertex in the graph
			 */
			javaSource = javaSource + "\t\t\t//Step 2: Connect linked info-beads\n";
			for (Integer linkId : linksMap.keySet()){
				if (linksMap.get(linkId).getType().equals(EdgeType.DIRECTED)) {
					Edge link = linksMap.get(linkId);
					Integer inputToLinkId = link.getSourceId();
					Integer outputOfLinkId = link.getTargetId();
					String connectingLink = "\t\t\t" + infoBeadMap.get(inputToLinkId).getName() + "_" + inputToLinkId +
							".connect(" + infoBeadMap.get(outputOfLinkId).getName() + "_" + outputOfLinkId + ");\n";
					javaSource += connectingLink + "\n";
				}
			}
				
				
			// Step 3: Initialize info-Beads' operation	
			javaSource += "\t\t\t//Step 3: Initialize info-beads\n";
			for (Integer infoBeadId : infoBeadMap.keySet()){
				Vertex infoBead = infoBeadMap.get(infoBeadId);
				javaSource += "\t\t\t" + infoBead.getName() + "_" + infoBeadId + ".initialize();\n\n";
			}

			
			javaSource += "\t\t} catch (Exception e) {\n";
			javaSource += "\t\t\te.printStackTrace();\n\t\t}\n\t}\n}";		
					
		try {

			if (activationFile.exists()){
				activationFile.delete();
			}
			FileWriter fw = new FileWriter(activationFile.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(javaSource);
			bw.close();
	
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "*** Error -  a problem writning to the activation file");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
}

