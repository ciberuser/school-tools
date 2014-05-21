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
			
			Class<?> cls_11 = Class.forName("infobeadCollection.UserTemperature", true, classLoader);
			InfoBead UserTemperature_11= (InfoBead)cls_11.newInstance();
			UserTemperature_11.setInfoBeadId("UserTemperature_11");
			UserTemperature_11.setInfobeadModelId("UM1");

			Class<?> cls_12 = Class.forName("infobeadCollection.UserPhysicalLocation", true, classLoader);
			InfoBead UserPhysicalLocation_12= (InfoBead)cls_12.newInstance();
			UserPhysicalLocation_12.setInfoBeadId("UserPhysicalLocation_12");
			UserPhysicalLocation_12.setInfobeadModelId("UM1");

			Class<?> cls_13 = Class.forName("infobeadCollection.UserPhysicalCondition", true, classLoader);
			InfoBead UserPhysicalCondition_13= (InfoBead)cls_13.newInstance();
			UserPhysicalCondition_13.setInfoBeadId("UserPhysicalCondition_13");
			UserPhysicalCondition_13.setInfobeadModelId("UM1");

			Class<?> cls_14 = Class.forName("infobeadCollection.UserNearCoffeeShop", true, classLoader);
			InfoBead UserNearCoffeeShop_14= (InfoBead)cls_14.newInstance();
			UserNearCoffeeShop_14.setInfoBeadId("UserNearCoffeeShop_14");
			UserNearCoffeeShop_14.setInfobeadModelId("UM1");

			Class<?> cls_15 = Class.forName("infobeadCollection.UserCoffeePrefernces", true, classLoader);
			InfoBead UserCoffeePrefernces_15= (InfoBead)cls_15.newInstance();
			UserCoffeePrefernces_15.setInfoBeadId("UserCoffeePrefernces_15");
			UserCoffeePrefernces_15.setInfobeadModelId("UM1");

			Class<?> cls_16 = Class.forName("infobeadCollection.userNeedForHotDrink", true, classLoader);
			InfoBead userNeedForHotDrink_16= (InfoBead)cls_16.newInstance();
			userNeedForHotDrink_16.setInfoBeadId("userNeedForHotDrink_16");
			userNeedForHotDrink_16.setInfobeadModelId("UM1");

			Class<?> cls_17 = Class.forName("infobeadCollection.userNeedForColdDrink", true, classLoader);
			InfoBead userNeedForColdDrink_17= (InfoBead)cls_17.newInstance();
			userNeedForColdDrink_17.setInfoBeadId("userNeedForColdDrink_17");
			userNeedForColdDrink_17.setInfobeadModelId("UM1");

			Class<?> cls_18 = Class.forName("infobeadCollection.userCupSizePref", true, classLoader);
			InfoBead userCupSizePref_18= (InfoBead)cls_18.newInstance();
			userCupSizePref_18.setInfoBeadId("userCupSizePref_18");
			userCupSizePref_18.setInfobeadModelId("UM1");

			Class<?> cls_19 = Class.forName("infobeadCollection.userMilkTypePref", true, classLoader);
			InfoBead userMilkTypePref_19= (InfoBead)cls_19.newInstance();
			userMilkTypePref_19.setInfoBeadId("userMilkTypePref_19");
			userMilkTypePref_19.setInfobeadModelId("UM1");

			Class<?> cls_20 = Class.forName("infobeadCollection.userCoffeeBlendPref", true, classLoader);
			InfoBead userCoffeeBlendPref_20= (InfoBead)cls_20.newInstance();
			userCoffeeBlendPref_20.setInfoBeadId("userCoffeeBlendPref_20");
			userCoffeeBlendPref_20.setInfobeadModelId("UM1");

			Class<?> cls_21 = Class.forName("infobeadCollection.userCoffeeSelection", true, classLoader);
			InfoBead userCoffeeSelection_21= (InfoBead)cls_21.newInstance();
			userCoffeeSelection_21.setInfoBeadId("userCoffeeSelection_21");
			userCoffeeSelection_21.setInfobeadModelId("UM1");

			Class<?> cls_22 = Class.forName("infobeadCollection.userSelectionToBuy", true, classLoader);
			InfoBead userSelectionToBuy_22= (InfoBead)cls_22.newInstance();
			userSelectionToBuy_22.setInfoBeadId("userSelectionToBuy_22");
			userSelectionToBuy_22.setInfobeadModelId("UM1");

			//Step 2: Connect linked info-beads
			UserTemperature_11.connect(UserPhysicalCondition_13);

			UserPhysicalLocation_12.connect(UserPhysicalCondition_13);

			UserPhysicalLocation_12.connect(UserNearCoffeeShop_14);

			UserNearCoffeeShop_14.connect(UserCoffeePrefernces_15);

			UserPhysicalCondition_13.connect(userNeedForHotDrink_16);

			userNeedForHotDrink_16.connect(UserCoffeePrefernces_15);

			UserPhysicalCondition_13.connect(userNeedForColdDrink_17);

			userNeedForColdDrink_17.connect(UserCoffeePrefernces_15);

			UserCoffeePrefernces_15.connect(userCupSizePref_18);

			UserCoffeePrefernces_15.connect(userMilkTypePref_19);

			UserCoffeePrefernces_15.connect(userCoffeeBlendPref_20);

			userCupSizePref_18.connect(userCoffeeSelection_21);

			userMilkTypePref_19.connect(userCoffeeSelection_21);

			userCoffeeBlendPref_20.connect(userCoffeeSelection_21);

			UserCoffeePrefernces_15.connect(userCoffeeSelection_21);

			userCoffeeSelection_21.connect(userSelectionToBuy_22);

			//Step 3: Initialize info-beads
			UserTemperature_11.initialize();

			UserPhysicalLocation_12.initialize();

			UserPhysicalCondition_13.initialize();

			UserNearCoffeeShop_14.initialize();

			UserCoffeePrefernces_15.initialize();

			userNeedForHotDrink_16.initialize();

			userNeedForColdDrink_17.initialize();

			userCupSizePref_18.initialize();

			userMilkTypePref_19.initialize();

			userCoffeeBlendPref_20.initialize();

			userCoffeeSelection_21.initialize();

			userSelectionToBuy_22.initialize();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}