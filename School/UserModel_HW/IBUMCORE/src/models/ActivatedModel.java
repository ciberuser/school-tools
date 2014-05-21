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
			
			Class<?> cls_0 = Class.forName("infobeadCollection.UserPhysicalCondition", true, classLoader);
			InfoBead UserPhysicalCondition_0= (InfoBead)cls_0.newInstance();
			UserPhysicalCondition_0.setInfoBeadId("UserPhysicalCondition_0");
			UserPhysicalCondition_0.setInfobeadModelId("UM1");

			Class<?> cls_1 = Class.forName("infobeadCollection.UserPhysicalLocation", true, classLoader);
			InfoBead UserPhysicalLocation_1= (InfoBead)cls_1.newInstance();
			UserPhysicalLocation_1.setInfoBeadId("UserPhysicalLocation_1");
			UserPhysicalLocation_1.setInfobeadModelId("UM1");

			Class<?> cls_2 = Class.forName("infobeadCollection.UserTemperature", true, classLoader);
			InfoBead UserTemperature_2= (InfoBead)cls_2.newInstance();
			UserTemperature_2.setInfoBeadId("UserTemperature_2");
			UserTemperature_2.setInfobeadModelId("UM1");

			Class<?> cls_3 = Class.forName("infobeadCollection.UserNearCoffeeShop", true, classLoader);
			InfoBead UserNearCoffeeShop_3= (InfoBead)cls_3.newInstance();
			UserNearCoffeeShop_3.setInfoBeadId("UserNearCoffeeShop_3");
			UserNearCoffeeShop_3.setInfobeadModelId("UM1");

			Class<?> cls_4 = Class.forName("infobeadCollection.UserCoffeePrefernces", true, classLoader);
			InfoBead UserCoffeePrefernces_4= (InfoBead)cls_4.newInstance();
			UserCoffeePrefernces_4.setInfoBeadId("UserCoffeePrefernces_4");
			UserCoffeePrefernces_4.setInfobeadModelId("UM1");

			Class<?> cls_5 = Class.forName("infobeadCollection.userNeedForColdDrink", true, classLoader);
			InfoBead userNeedForColdDrink_5= (InfoBead)cls_5.newInstance();
			userNeedForColdDrink_5.setInfoBeadId("userNeedForColdDrink_5");
			userNeedForColdDrink_5.setInfobeadModelId("UM1");

			Class<?> cls_6 = Class.forName("infobeadCollection.userNeedForColdDrink", true, classLoader);
			InfoBead userNeedForColdDrink_6= (InfoBead)cls_6.newInstance();
			userNeedForColdDrink_6.setInfoBeadId("userNeedForColdDrink_6");
			userNeedForColdDrink_6.setInfobeadModelId("UM1");

			//Step 2: Connect linked info-beads
			UserPhysicalLocation_1.connect(UserPhysicalCondition_0);

			UserTemperature_2.connect(UserPhysicalCondition_0);

			UserPhysicalLocation_1.connect(UserNearCoffeeShop_3);

			UserNearCoffeeShop_3.connect(UserCoffeePrefernces_4);

			UserPhysicalCondition_0.connect(userNeedForColdDrink_5);

			userNeedForColdDrink_5.connect(UserCoffeePrefernces_4);

			UserPhysicalCondition_0.connect(userNeedForColdDrink_6);

			userNeedForColdDrink_6.connect(UserCoffeePrefernces_4);

			//Step 3: Initialize info-beads
			UserPhysicalCondition_0.initialize();

			UserPhysicalLocation_1.initialize();

			UserTemperature_2.initialize();

			UserNearCoffeeShop_3.initialize();

			UserCoffeePrefernces_4.initialize();

			userNeedForColdDrink_5.initialize();

			userNeedForColdDrink_6.initialize();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}