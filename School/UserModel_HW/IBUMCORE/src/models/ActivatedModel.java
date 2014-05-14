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
			
			Class<?> cls_0 = Class.forName("infobeadCollection.CoreRTDisplayPort", true, classLoader);
			InfoBead CoreRTDisplayPort_0= (InfoBead)cls_0.newInstance();
			CoreRTDisplayPort_0.setInfoBeadId("CoreRTDisplayPort_0");
			CoreRTDisplayPort_0.setInfobeadModelId("UM1");

			Class<?> cls_1 = Class.forName("infobeadCollection.UserTemperature", true, classLoader);
			InfoBead UserTemperature_1= (InfoBead)cls_1.newInstance();
			UserTemperature_1.setInfoBeadId("UserTemperature_1");
			UserTemperature_1.setInfobeadModelId("UM1");

			//Step 2: Connect linked info-beads
			UserTemperature_1.connect(CoreRTDisplayPort_0);

			//Step 3: Initialize info-beads
			CoreRTDisplayPort_0.initialize();

			UserTemperature_1.initialize();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}