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
			GroupLocation_0.setInfobeadModelId("GM3");

			Class<?> cls_1 = Class.forName("infobeadCollection.groupWaiterSelection", true, classLoader);
			InfoBead groupWaiterSelection_1= (InfoBead)cls_1.newInstance();
			groupWaiterSelection_1.setInfoBeadId("groupWaiterSelection_1");
			groupWaiterSelection_1.setInfobeadModelId("GM3");

			//Step 2: Connect linked info-beads
			GroupLocation_0.connect(groupWaiterSelection_1);

			//Step 3: Initialize info-beads
			GroupLocation_0.initialize();

			groupWaiterSelection_1.initialize();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}