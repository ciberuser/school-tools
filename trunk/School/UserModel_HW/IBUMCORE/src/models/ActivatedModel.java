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
			
			Class<?> cls_0 = Class.forName("infobeadCollection.GroupSongSelection", true, classLoader);
			InfoBead GroupSongSelection_0= (InfoBead)cls_0.newInstance();
			GroupSongSelection_0.setInfoBeadId("GroupSongSelection_0");
			GroupSongSelection_0.setInfobeadModelId("GM3");

			Class<?> cls_1 = Class.forName("infobeadCollection.GroupLocation", true, classLoader);
			InfoBead GroupLocation_1= (InfoBead)cls_1.newInstance();
			GroupLocation_1.setInfoBeadId("GroupLocation_1");
			GroupLocation_1.setInfobeadModelId("GM3");

			//Step 2: Connect linked info-beads
			GroupLocation_1.connect(GroupSongSelection_0);

			//Step 3: Initialize info-beads
			GroupSongSelection_0.initialize();

			GroupLocation_1.initialize();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}