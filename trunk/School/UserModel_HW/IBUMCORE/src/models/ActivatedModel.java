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
			URL filePath = new URL(" file:/C:/LocalSvn2/IBUMCORE/bin/..//bin/infobeadCollection/");
			urls = new URL[]{filePath};
			URLClassLoader classLoader = new URLClassLoader (urls, this.getClass().getClassLoader());
			
			Class<?> cls_0 = Class.forName("infobeadCollection.UserTemperature", true, classLoader);
			InfoBead UserTemperature_0= (InfoBead)cls_0.newInstance();
			UserTemperature_0.setInfoBeadId("UserTemperature_0");
			UserTemperature_0.setInfobeadModelId("UM1");

			Class<?> cls_1 = Class.forName("infobeadCollection.userSelectionToBuy", true, classLoader);
			InfoBead userSelectionToBuy_1= (InfoBead)cls_1.newInstance();
			userSelectionToBuy_1.setInfoBeadId("userSelectionToBuy_1");
			userSelectionToBuy_1.setInfobeadModelId("UM1");

			Class<?> cls_2 = Class.forName("infobeadCollection.UserPhysicalLocation", true, classLoader);
			InfoBead UserPhysicalLocation_2= (InfoBead)cls_2.newInstance();
			UserPhysicalLocation_2.setInfoBeadId("UserPhysicalLocation_2");
			UserPhysicalLocation_2.setInfobeadModelId("UM1");

			Class<?> cls_3 = Class.forName("infobeadCollection.UserPhysicalCondition", true, classLoader);
			InfoBead UserPhysicalCondition_3= (InfoBead)cls_3.newInstance();
			UserPhysicalCondition_3.setInfoBeadId("UserPhysicalCondition_3");
			UserPhysicalCondition_3.setInfobeadModelId("UM1");

			Class<?> cls_4 = Class.forName("infobeadCollection.userNeedForHotDrink", true, classLoader);
			InfoBead userNeedForHotDrink_4= (InfoBead)cls_4.newInstance();
			userNeedForHotDrink_4.setInfoBeadId("userNeedForHotDrink_4");
			userNeedForHotDrink_4.setInfobeadModelId("UM1");

			Class<?> cls_5 = Class.forName("infobeadCollection.userNeedForColdDrink", true, classLoader);
			InfoBead userNeedForColdDrink_5= (InfoBead)cls_5.newInstance();
			userNeedForColdDrink_5.setInfoBeadId("userNeedForColdDrink_5");
			userNeedForColdDrink_5.setInfobeadModelId("UM1");

			Class<?> cls_6 = Class.forName("infobeadCollection.UserNearCoffeeShop", true, classLoader);
			InfoBead UserNearCoffeeShop_6= (InfoBead)cls_6.newInstance();
			UserNearCoffeeShop_6.setInfoBeadId("UserNearCoffeeShop_6");
			UserNearCoffeeShop_6.setInfobeadModelId("UM1");

			Class<?> cls_7 = Class.forName("infobeadCollection.userMilkTypePref", true, classLoader);
			InfoBead userMilkTypePref_7= (InfoBead)cls_7.newInstance();
			userMilkTypePref_7.setInfoBeadId("userMilkTypePref_7");
			userMilkTypePref_7.setInfobeadModelId("UM1");

			Class<?> cls_8 = Class.forName("infobeadCollection.userCupSizePref", true, classLoader);
			InfoBead userCupSizePref_8= (InfoBead)cls_8.newInstance();
			userCupSizePref_8.setInfoBeadId("userCupSizePref_8");
			userCupSizePref_8.setInfobeadModelId("UM1");

			Class<?> cls_9 = Class.forName("infobeadCollection.userCoffeeSelection", true, classLoader);
			InfoBead userCoffeeSelection_9= (InfoBead)cls_9.newInstance();
			userCoffeeSelection_9.setInfoBeadId("userCoffeeSelection_9");
			userCoffeeSelection_9.setInfobeadModelId("UM1");

			Class<?> cls_10 = Class.forName("infobeadCollection.UserCoffeePrefernces", true, classLoader);
			InfoBead UserCoffeePrefernces_10= (InfoBead)cls_10.newInstance();
			UserCoffeePrefernces_10.setInfoBeadId("UserCoffeePrefernces_10");
			UserCoffeePrefernces_10.setInfobeadModelId("UM1");

			Class<?> cls_11 = Class.forName("infobeadCollection.userCoffeeBlendPref", true, classLoader);
			InfoBead userCoffeeBlendPref_11= (InfoBead)cls_11.newInstance();
			userCoffeeBlendPref_11.setInfoBeadId("userCoffeeBlendPref_11");
			userCoffeeBlendPref_11.setInfobeadModelId("UM1");

			//Step 2: Connect linked info-beads
			UserTemperature_0.connect(UserPhysicalCondition_3);

			UserPhysicalLocation_2.connect(UserPhysicalCondition_3);

			UserPhysicalCondition_3.connect(userNeedForHotDrink_4);

			UserPhysicalCondition_3.connect(userNeedForColdDrink_5);

			UserPhysicalLocation_2.connect(UserNearCoffeeShop_6);

			userCupSizePref_8.connect(userCoffeeSelection_9);

			userMilkTypePref_7.connect(userCoffeeSelection_9);

			userCoffeeSelection_9.connect(userSelectionToBuy_1);

			UserNearCoffeeShop_6.connect(UserCoffeePrefernces_10);

			userNeedForColdDrink_5.connect(UserCoffeePrefernces_10);

			userNeedForHotDrink_4.connect(UserCoffeePrefernces_10);

			UserCoffeePrefernces_10.connect(userMilkTypePref_7);

			UserCoffeePrefernces_10.connect(userCupSizePref_8);

			UserCoffeePrefernces_10.connect(userCoffeeSelection_9);

			UserCoffeePrefernces_10.connect(userCoffeeBlendPref_11);

			userCoffeeBlendPref_11.connect(userCoffeeSelection_9);

			//Step 3: Initialize info-beads
			UserTemperature_0.initialize();

			userSelectionToBuy_1.initialize();

			UserPhysicalLocation_2.initialize();

			UserPhysicalCondition_3.initialize();

			userNeedForHotDrink_4.initialize();

			userNeedForColdDrink_5.initialize();

			UserNearCoffeeShop_6.initialize();

			userMilkTypePref_7.initialize();

			userCupSizePref_8.initialize();

			userCoffeeSelection_9.initialize();

			UserCoffeePrefernces_10.initialize();

			userCoffeeBlendPref_11.initialize();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}