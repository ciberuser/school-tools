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
			URL filePath = new URL(" file://D:/oldwh/PhD/PHD2/eclipse%20workspace%207Jun2013/IBUMCORE/bin/infobeadCollection/");
			urls = new URL[]{filePath};
			URLClassLoader classLoader = new URLClassLoader (urls, this.getClass().getClassLoader());
			
			Class<?> cls_0 = Class.forName("infobeadCollection.CoreHellowWorld", true, classLoader);
			InfoBead CoreHellowWorld_0= (InfoBead)cls_0.newInstance();
			CoreHellowWorld_0.setInfoBeadId("CoreHellowWorld_0");
			CoreHellowWorld_0.setInfobeadModelId("UM1");

			Class<?> cls_1 = Class.forName("infobeadCollection.CoreRTDisplayPort", true, classLoader);
			InfoBead CoreRTDisplayPort_1= (InfoBead)cls_1.newInstance();
			CoreRTDisplayPort_1.setInfoBeadId("CoreRTDisplayPort_1");
			CoreRTDisplayPort_1.setInfobeadModelId("UM1");

			//Step 2: Connect linked info-beads
			CoreHellowWorld_0.connect(CoreRTDisplayPort_1);

			//Step 3: Initialize info-beads
			CoreHellowWorld_0.initialize();

			CoreRTDisplayPort_1.initialize();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}