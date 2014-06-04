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
			URL filePath = new URL(" file:/C:/LocalSVN/trunk/School/UserModel_HW/IBUMCORE/bin/..//bin/infobeadCollection/");
			urls = new URL[]{filePath};
			URLClassLoader classLoader = new URLClassLoader (urls, this.getClass().getClassLoader());
			
			Class<?> cls_0 = Class.forName("infobeadCollection.GroupLocation", true, classLoader);
			InfoBead GroupLocation_0= (InfoBead)cls_0.newInstance();
			GroupLocation_0.setInfoBeadId("GroupLocation_0");
			GroupLocation_0.setInfobeadModelId("GM2");

			Class<?> cls_1 = Class.forName("infobeadCollection.groupMeal", true, classLoader);
			InfoBead groupMeal_1= (InfoBead)cls_1.newInstance();
			groupMeal_1.setInfoBeadId("groupMeal_1");
			groupMeal_1.setInfobeadModelId("GM2");

			//Step 2: Connect linked info-beads
			GroupLocation_0.connect(groupMeal_1);

			//Step 3: Initialize info-beads
			GroupLocation_0.initialize();

			groupMeal_1.initialize();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}