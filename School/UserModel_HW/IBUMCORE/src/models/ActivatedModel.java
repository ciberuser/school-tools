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
			
			Class<?> cls_0 = Class.forName("infobeadCollection.UserTemperature", true, classLoader);
			InfoBead UserTemperature_0= (InfoBead)cls_0.newInstance();
			UserTemperature_0.setInfoBeadId("UserTemperature_0");
			UserTemperature_0.setInfobeadModelId("UM1");

			Class<?> cls_1 = Class.forName("infobeadCollection.UserPhysicalLocation", true, classLoader);
			InfoBead UserPhysicalLocation_1= (InfoBead)cls_1.newInstance();
			UserPhysicalLocation_1.setInfoBeadId("UserPhysicalLocation_1");
			UserPhysicalLocation_1.setInfobeadModelId("UM1");

			Class<?> cls_2 = Class.forName("infobeadCollection.UserPhysicalCondition", true, classLoader);
			InfoBead UserPhysicalCondition_2= (InfoBead)cls_2.newInstance();
			UserPhysicalCondition_2.setInfoBeadId("UserPhysicalCondition_2");
			UserPhysicalCondition_2.setInfobeadModelId("UM1");

			Class<?> cls_3 = Class.forName("infobeadCollection.UserNearCoffeeShop", true, classLoader);
			InfoBead UserNearCoffeeShop_3= (InfoBead)cls_3.newInstance();
			UserNearCoffeeShop_3.setInfoBeadId("UserNearCoffeeShop_3");
			UserNearCoffeeShop_3.setInfobeadModelId("UM1");

			Class<?> cls_4 = Class.forName("infobeadCollection.userNeedForHotDrink", true, classLoader);
			InfoBead userNeedForHotDrink_4= (InfoBead)cls_4.newInstance();
			userNeedForHotDrink_4.setInfoBeadId("userNeedForHotDrink_4");
			userNeedForHotDrink_4.setInfobeadModelId("UM1");

			Class<?> cls_5 = Class.forName("infobeadCollection.userNeedForColdDrink", true, classLoader);
			InfoBead userNeedForColdDrink_5= (InfoBead)cls_5.newInstance();
			userNeedForColdDrink_5.setInfoBeadId("userNeedForColdDrink_5");
			userNeedForColdDrink_5.setInfobeadModelId("UM1");

			Class<?> cls_6 = Class.forName("infobeadCollection.UserCoffeePrefernces", true, classLoader);
			InfoBead UserCoffeePrefernces_6= (InfoBead)cls_6.newInstance();
			UserCoffeePrefernces_6.setInfoBeadId("UserCoffeePrefernces_6");
			UserCoffeePrefernces_6.setInfobeadModelId("UM1");

			Class<?> cls_7 = Class.forName("infobeadCollection.userMilkTypePref", true, classLoader);
			InfoBead userMilkTypePref_7= (InfoBead)cls_7.newInstance();
			userMilkTypePref_7.setInfoBeadId("userMilkTypePref_7");
			userMilkTypePref_7.setInfobeadModelId("UM1");

			Class<?> cls_8 = Class.forName("infobeadCollection.userCupSizePref", true, classLoader);
			InfoBead userCupSizePref_8= (InfoBead)cls_8.newInstance();
			userCupSizePref_8.setInfoBeadId("userCupSizePref_8");
			userCupSizePref_8.setInfobeadModelId("UM1");

			Class<?> cls_9 = Class.forName("infobeadCollection.userCoffeeBlendPref", true, classLoader);
			InfoBead userCoffeeBlendPref_9= (InfoBead)cls_9.newInstance();
			userCoffeeBlendPref_9.setInfoBeadId("userCoffeeBlendPref_9");
			userCoffeeBlendPref_9.setInfobeadModelId("UM1");

			Class<?> cls_10 = Class.forName("infobeadCollection.userSelectionToBuy", true, classLoader);
			InfoBead userSelectionToBuy_10= (InfoBead)cls_10.newInstance();
			userSelectionToBuy_10.setInfoBeadId("userSelectionToBuy_10");
			userSelectionToBuy_10.setInfobeadModelId("UM1");

			Class<?> cls_11 = Class.forName("infobeadCollection.userCoffeeSelection", true, classLoader);
			InfoBead userCoffeeSelection_11= (InfoBead)cls_11.newInstance();
			userCoffeeSelection_11.setInfoBeadId("userCoffeeSelection_11");
			userCoffeeSelection_11.setInfobeadModelId("UM1");

			//Step 2: Connect linked info-beads
			UserTemperature_0.connect(UserPhysicalCondition_2);

			UserPhysicalLocation_1.connect(UserPhysicalCondition_2);

			UserPhysicalLocation_1.connect(UserNearCoffeeShop_3);

			UserPhysicalCondition_2.connect(userNeedForHotDrink_4);

			UserPhysicalCondition_2.connect(userNeedForColdDrink_5);

			UserNearCoffeeShop_3.connect(UserCoffeePrefernces_6);

			userNeedForColdDrink_5.connect(UserCoffeePrefernces_6);

			userNeedForHotDrink_4.connect(UserCoffeePrefernces_6);

			UserCoffeePrefernces_6.connect(userMilkTypePref_7);

			UserCoffeePrefernces_6.connect(userCupSizePref_8);

			UserCoffeePrefernces_6.connect(userCoffeeBlendPref_9);

			userCupSizePref_8.connect(userCoffeeSelection_11);

			userMilkTypePref_7.connect(userCoffeeSelection_11);

			userCoffeeBlendPref_9.connect(userCoffeeSelection_11);

			userCoffeeSelection_11.connect(userSelectionToBuy_10);

			//Step 3: Initialize info-beads
			UserTemperature_0.initialize();

			UserPhysicalLocation_1.initialize();

			UserPhysicalCondition_2.initialize();

			UserNearCoffeeShop_3.initialize();

			userNeedForHotDrink_4.initialize();

			userNeedForColdDrink_5.initialize();

			UserCoffeePrefernces_6.initialize();

			userMilkTypePref_7.initialize();

			userCupSizePref_8.initialize();

			userCoffeeBlendPref_9.initialize();

			userSelectionToBuy_10.initialize();

			userCoffeeSelection_11.initialize();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}