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
			
			Class<?> cls_0 = Class.forName("infobeadCollection.UserNearCoffeeShop", true, classLoader);
			InfoBead UserNearCoffeeShop_0= (InfoBead)cls_0.newInstance();
			UserNearCoffeeShop_0.setInfoBeadId("UserNearCoffeeShop_0");
			UserNearCoffeeShop_0.setInfobeadModelId("UM1");

			Class<?> cls_1 = Class.forName("infobeadCollection.UserPhysicalLocation", true, classLoader);
			InfoBead UserPhysicalLocation_1= (InfoBead)cls_1.newInstance();
			UserPhysicalLocation_1.setInfoBeadId("UserPhysicalLocation_1");
			UserPhysicalLocation_1.setInfobeadModelId("UM1");

			//Step 2: Connect linked info-beads
			UserPhysicalLocation_1.connect(UserNearCoffeeShop_0);

			//Step 3: Initialize info-beads
			UserNearCoffeeShop_0.initialize();

			UserPhysicalLocation_1.initialize();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}