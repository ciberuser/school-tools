package modelExport;

import genericInfoBead.InfoBead;
import architectureUtil.DirectoriesSetup;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/*** An automatically generated model activation source
*
* Step 1: create the info-beads
* Step 2: set activated links between the info-beads
* Step 3: initialize the info-beads
*
* @author Eyal Dim
* @ Version 1.0
*/
public class ActivatedModel1 {

	public ActivatedModel1(){
		this.runModel();
	}

	public void runModel() {
		
		try {
			
			// Step 1: load the info-beads from the Eclipse work folder
			DirectoriesSetup dir = new DirectoriesSetup();
			URL[] urls;
			URL filePath = new URL(dir.getActivatedmodel1url());
			urls = new URL[]{filePath};
			URLClassLoader classLoader = new URLClassLoader (urls, this.getClass().getClassLoader());
			
			CustomActivatedModel  runModel = new CustomActivatedModel();
			String runFileName = dir.getActivatedModelsPath() +  "ActivatedModel.eam";
			File runDataFile = new File(runFileName);
			runModel = loadActivatedModel(runDataFile);
			
			ArrayList<String> infoBeads = new ArrayList<String>();
			ArrayList<String> infoLinks = new ArrayList<String>();
			infoBeads.addAll(runModel.getInfoBeads());
			infoLinks.addAll(runModel.getInfoLinks());
			
//			HashMap<String, Class<?>> clsName = new HashMap<String, Class<?>>();
			HashMap<String, InfoBead> infoBeadInstance = new HashMap<String, InfoBead>();

			for (String infoBead : infoBeads){
				String[] nameAndModel;
				String[] nameSplit;
				nameAndModel = infoBead.split(","); // separate info-bead name from its modelID
				String infoBeadName = nameAndModel[0];
				String infoBeadModelId = nameAndModel[1];
				nameSplit = nameAndModel[0].split("_"); //Separate class name form its copyID
				String className = nameSplit[0];
				
				Class<?> cls = Class.forName("infobeadCollection." + className, true, classLoader);
//				clsName.put(infoBeadName, cls);
				InfoBead ib = (InfoBead)cls.newInstance();
				infoBeadInstance.put(infoBeadName, ib);
				ib.setInfoBeadId(infoBeadName);
				ib.setInfobeadModelId(infoBeadModelId);
				
			}
			

			//Step 2: Connect linked info-beads
			
			for (String infoLink : infoLinks){
				String[] linkedInfoBeads = infoLink.split(",");
				String sourceInfoBead = linkedInfoBeads[0];
				String targetInfoBead = linkedInfoBeads[1];
				infoBeadInstance.get(sourceInfoBead).connect(infoBeadInstance.get(targetInfoBead));
			}

	
			//Step 3: Initialize info-beads
			
			for (String key : infoBeadInstance.keySet() ){
				infoBeadInstance.get(key).initialize();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
		

		
	/**
	 * load the activated model from a file
	 * 
	 * @param file   an activated model file
	 * @return CustomeActivatedModel object loaded from the file
	 * @throws JAXBException
	 */
	public static CustomActivatedModel loadActivatedModel(File file) {
		if (file != null && file.isFile()) {
			CustomActivatedModel activatedModel = (CustomActivatedModel) loadXML(file, CustomActivatedModel.class);
			return activatedModel;
		}
		return null;
	}
	
	
	
	/**
		 * Loads an activated model from a file in an XML format 
		 * 
		 * @param file
		 *            The file to load the data from
		 * @param name
		 *            The type of the file to be loaded
		 * @return The loaded object
		 */
		static Object loadXML(File file, Class<?> name) {
			Object loaded = null;

			try {
				JAXBContext context = JAXBContext.newInstance(name);
				Unmarshaller unmarshaller = context.createUnmarshaller();
				if (file.isFile()) {
					loaded = unmarshaller.unmarshal(file);
				}

			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}

			return loaded;

		}
		
		
	
	
}