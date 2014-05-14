package models;

import genericInfoBead.InfoBead;
import java.net.URL;
import java.net.URLClassLoader;

/*** An automatically generated model activation source
*
* Step 1: create the info-beads
* Step 2: set activated links between the info-beads
* Step 3: initialize the info-beads
*
* @author Eyal Dim
* @ Version 1.0
*/
public class ActivatedModel {

	public ActivatedModel(){
		this.runModel();
	}

	public void runModel() {
		
		try {
			
			// Step 1: load the info-beads from the Eclipse work folder
			URL[] urls;
			URL filePath = new URL(" file:/C:/LocalSVN/UserModel_HW/IBUMCORE/bin/..//bin/infobeadCollection/");
			urls = new URL[]{filePath};
			URLClassLoader classLoader = new URLClassLoader (urls, this.getClass().getClassLoader());
			
			Class<?> cls_0 = Class.forName("infobeadCollection.UserPhysicalLocation", true, classLoader);
			InfoBead UserPhysicalLocation_0= (InfoBead)cls_0.newInstance();
			UserPhysicalLocation_0.setInfoBeadId("UserPhysicalLocation_0");
			UserPhysicalLocation_0.setInfobeadModelId("UM1");

			Class<?> cls_1 = Class.forName("infobeadCollection.UserTemperature", true, classLoader);
			InfoBead UserTemperature_1= (InfoBead)cls_1.newInstance();
			UserTemperature_1.setInfoBeadId("UserTemperature_1");
			UserTemperature_1.setInfobeadModelId("UM1");

			Class<?> cls_2 = Class.forName("infobeadCollection.UserPhysicalCondition", true, classLoader);
			InfoBead UserPhysicalCondition_2= (InfoBead)cls_2.newInstance();
			UserPhysicalCondition_2.setInfoBeadId("UserPhysicalCondition_2");
			UserPhysicalCondition_2.setInfobeadModelId("UM1");

			//Step 2: Connect linked info-beads
			UserTemperature_1.connect(UserPhysicalCondition_2);

			UserPhysicalLocation_0.connect(UserPhysicalCondition_2);

			//Step 3: Initialize info-beads
			UserPhysicalLocation_0.initialize();

			UserTemperature_1.initialize();

			UserPhysicalCondition_2.initialize();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}