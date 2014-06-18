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
			
			Class<?> cls_4 = Class.forName("infobeadCollection.UserPhysicalLocation", true, classLoader);
			InfoBead UserPhysicalLocation_4= (InfoBead)cls_4.newInstance();
			UserPhysicalLocation_4.setInfoBeadId("UserPhysicalLocation_4");
			UserPhysicalLocation_4.setInfobeadModelId("UM18");

			Class<?> cls_5 = Class.forName("infobeadCollection.UserPhysicalLocation", true, classLoader);
			InfoBead UserPhysicalLocation_5= (InfoBead)cls_5.newInstance();
			UserPhysicalLocation_5.setInfoBeadId("UserPhysicalLocation_5");
			UserPhysicalLocation_5.setInfobeadModelId("UM18");

			Class<?> cls_6 = Class.forName("infobeadCollection.UserPhysicalLocation", true, classLoader);
			InfoBead UserPhysicalLocation_6= (InfoBead)cls_6.newInstance();
			UserPhysicalLocation_6.setInfoBeadId("UserPhysicalLocation_6");
			UserPhysicalLocation_6.setInfobeadModelId("UM18");

			Class<?> cls_7 = Class.forName("infobeadCollection.UserNearCoffeeShop", true, classLoader);
			InfoBead UserNearCoffeeShop_7= (InfoBead)cls_7.newInstance();
			UserNearCoffeeShop_7.setInfoBeadId("UserNearCoffeeShop_7");
			UserNearCoffeeShop_7.setInfobeadModelId("UM18");

			Class<?> cls_8 = Class.forName("infobeadCollection.GroupLocation", true, classLoader);
			InfoBead GroupLocation_8= (InfoBead)cls_8.newInstance();
			GroupLocation_8.setInfoBeadId("GroupLocation_8");
			GroupLocation_8.setInfobeadModelId("GM15");

			Class<?> cls_9 = Class.forName("infobeadCollection.GroupSongSelection", true, classLoader);
			InfoBead GroupSongSelection_9= (InfoBead)cls_9.newInstance();
			GroupSongSelection_9.setInfoBeadId("GroupSongSelection_9");
			GroupSongSelection_9.setInfobeadModelId("GM15");

			Class<?> cls_10 = Class.forName("infobeadCollection.UserPhysicalLocation", true, classLoader);
			InfoBead UserPhysicalLocation_10= (InfoBead)cls_10.newInstance();
			UserPhysicalLocation_10.setInfoBeadId("UserPhysicalLocation_10");
			UserPhysicalLocation_10.setInfobeadModelId("UM23");

			Class<?> cls_11 = Class.forName("infobeadCollection.UserPhysicalLocation", true, classLoader);
			InfoBead UserPhysicalLocation_11= (InfoBead)cls_11.newInstance();
			UserPhysicalLocation_11.setInfoBeadId("UserPhysicalLocation_11");
			UserPhysicalLocation_11.setInfobeadModelId("UM23");

			Class<?> cls_12 = Class.forName("infobeadCollection.UserPhysicalLocation", true, classLoader);
			InfoBead UserPhysicalLocation_12= (InfoBead)cls_12.newInstance();
			UserPhysicalLocation_12.setInfoBeadId("UserPhysicalLocation_12");
			UserPhysicalLocation_12.setInfobeadModelId("UM23");

			Class<?> cls_13 = Class.forName("infobeadCollection.UserNearCoffeeShop", true, classLoader);
			InfoBead UserNearCoffeeShop_13= (InfoBead)cls_13.newInstance();
			UserNearCoffeeShop_13.setInfoBeadId("UserNearCoffeeShop_13");
			UserNearCoffeeShop_13.setInfobeadModelId("UM23");

			Class<?> cls_14 = Class.forName("infobeadCollection.GroupLocation", true, classLoader);
			InfoBead GroupLocation_14= (InfoBead)cls_14.newInstance();
			GroupLocation_14.setInfoBeadId("GroupLocation_14");
			GroupLocation_14.setInfobeadModelId("GM15");

			Class<?> cls_15 = Class.forName("infobeadCollection.groupMeal", true, classLoader);
			InfoBead groupMeal_15= (InfoBead)cls_15.newInstance();
			groupMeal_15.setInfoBeadId("groupMeal_15");
			groupMeal_15.setInfobeadModelId("GM15");

			Class<?> cls_16 = Class.forName("infobeadCollection.UserPhysicalLocation", true, classLoader);
			InfoBead UserPhysicalLocation_16= (InfoBead)cls_16.newInstance();
			UserPhysicalLocation_16.setInfoBeadId("UserPhysicalLocation_16");
			UserPhysicalLocation_16.setInfobeadModelId("UM26");

			Class<?> cls_17 = Class.forName("infobeadCollection.UserPhysicalLocation", true, classLoader);
			InfoBead UserPhysicalLocation_17= (InfoBead)cls_17.newInstance();
			UserPhysicalLocation_17.setInfoBeadId("UserPhysicalLocation_17");
			UserPhysicalLocation_17.setInfobeadModelId("UM26");

			Class<?> cls_18 = Class.forName("infobeadCollection.UserPhysicalLocation", true, classLoader);
			InfoBead UserPhysicalLocation_18= (InfoBead)cls_18.newInstance();
			UserPhysicalLocation_18.setInfoBeadId("UserPhysicalLocation_18");
			UserPhysicalLocation_18.setInfobeadModelId("UM26");

			Class<?> cls_19 = Class.forName("infobeadCollection.UserNearCoffeeShop", true, classLoader);
			InfoBead UserNearCoffeeShop_19= (InfoBead)cls_19.newInstance();
			UserNearCoffeeShop_19.setInfoBeadId("UserNearCoffeeShop_19");
			UserNearCoffeeShop_19.setInfobeadModelId("UM26");

			Class<?> cls_20 = Class.forName("infobeadCollection.UserPhysicalLocation", true, classLoader);
			InfoBead UserPhysicalLocation_20= (InfoBead)cls_20.newInstance();
			UserPhysicalLocation_20.setInfoBeadId("UserPhysicalLocation_20");
			UserPhysicalLocation_20.setInfobeadModelId("UM29");

			Class<?> cls_21 = Class.forName("infobeadCollection.UserPhysicalLocation", true, classLoader);
			InfoBead UserPhysicalLocation_21= (InfoBead)cls_21.newInstance();
			UserPhysicalLocation_21.setInfoBeadId("UserPhysicalLocation_21");
			UserPhysicalLocation_21.setInfobeadModelId("UM29");

			Class<?> cls_22 = Class.forName("infobeadCollection.UserPhysicalLocation", true, classLoader);
			InfoBead UserPhysicalLocation_22= (InfoBead)cls_22.newInstance();
			UserPhysicalLocation_22.setInfoBeadId("UserPhysicalLocation_22");
			UserPhysicalLocation_22.setInfobeadModelId("UM29");

			Class<?> cls_23 = Class.forName("infobeadCollection.UserNearCoffeeShop", true, classLoader);
			InfoBead UserNearCoffeeShop_23= (InfoBead)cls_23.newInstance();
			UserNearCoffeeShop_23.setInfoBeadId("UserNearCoffeeShop_23");
			UserNearCoffeeShop_23.setInfobeadModelId("UM29");

			Class<?> cls_24 = Class.forName("infobeadCollection.GroupLocation", true, classLoader);
			InfoBead GroupLocation_24= (InfoBead)cls_24.newInstance();
			GroupLocation_24.setInfoBeadId("GroupLocation_24");
			GroupLocation_24.setInfobeadModelId("GM15");

			Class<?> cls_25 = Class.forName("infobeadCollection.groupWaiterSelection", true, classLoader);
			InfoBead groupWaiterSelection_25= (InfoBead)cls_25.newInstance();
			groupWaiterSelection_25.setInfoBeadId("groupWaiterSelection_25");
			groupWaiterSelection_25.setInfobeadModelId("GM15");

			Class<?> cls_26 = Class.forName("infobeadCollection.GroupLocation", true, classLoader);
			InfoBead GroupLocation_26= (InfoBead)cls_26.newInstance();
			GroupLocation_26.setInfoBeadId("GroupLocation_26");
			GroupLocation_26.setInfobeadModelId("GM15");

			Class<?> cls_27 = Class.forName("infobeadCollection.groupWaiterRequest", true, classLoader);
			InfoBead groupWaiterRequest_27= (InfoBead)cls_27.newInstance();
			groupWaiterRequest_27.setInfoBeadId("groupWaiterRequest_27");
			groupWaiterRequest_27.setInfobeadModelId("GM15");

			//Step 2: Connect linked info-beads
			UserPhysicalLocation_4.connect(UserNearCoffeeShop_7);

			UserPhysicalLocation_5.connect(UserNearCoffeeShop_7);

			UserPhysicalLocation_6.connect(UserNearCoffeeShop_7);

			UserNearCoffeeShop_7.connect(GroupLocation_8);

			GroupLocation_8.connect(GroupSongSelection_9);

			UserPhysicalLocation_10.connect(UserNearCoffeeShop_13);

			UserPhysicalLocation_11.connect(UserNearCoffeeShop_13);

			UserPhysicalLocation_12.connect(UserNearCoffeeShop_13);

			UserNearCoffeeShop_13.connect(GroupLocation_14);

			GroupLocation_14.connect(groupMeal_15);

			UserPhysicalLocation_16.connect(UserNearCoffeeShop_19);

			UserPhysicalLocation_17.connect(UserNearCoffeeShop_19);

			UserPhysicalLocation_18.connect(UserNearCoffeeShop_19);

			UserPhysicalLocation_20.connect(UserNearCoffeeShop_23);

			UserPhysicalLocation_21.connect(UserNearCoffeeShop_23);

			UserPhysicalLocation_22.connect(UserNearCoffeeShop_23);

			UserNearCoffeeShop_19.connect(GroupLocation_24);

			GroupLocation_24.connect(groupWaiterSelection_25);

			UserNearCoffeeShop_23.connect(GroupLocation_26);

			GroupLocation_26.connect(groupWaiterRequest_27);

			//Step 3: Initialize info-beads
			UserPhysicalLocation_4.initialize();

			UserPhysicalLocation_5.initialize();

			UserPhysicalLocation_6.initialize();

			UserNearCoffeeShop_7.initialize();

			GroupLocation_8.initialize();

			GroupSongSelection_9.initialize();

			UserPhysicalLocation_10.initialize();

			UserPhysicalLocation_11.initialize();

			UserPhysicalLocation_12.initialize();

			UserNearCoffeeShop_13.initialize();

			GroupLocation_14.initialize();

			groupMeal_15.initialize();

			UserPhysicalLocation_16.initialize();

			UserPhysicalLocation_17.initialize();

			UserPhysicalLocation_18.initialize();

			UserNearCoffeeShop_19.initialize();

			UserPhysicalLocation_20.initialize();

			UserPhysicalLocation_21.initialize();

			UserPhysicalLocation_22.initialize();

			UserNearCoffeeShop_23.initialize();

			GroupLocation_24.initialize();

			groupWaiterSelection_25.initialize();

			GroupLocation_26.initialize();

			groupWaiterRequest_27.initialize();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}