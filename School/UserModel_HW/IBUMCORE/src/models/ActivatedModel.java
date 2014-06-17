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
			
			Class<?> cls_0 = Class.forName("infobeadCollection.TourismAge", true, classLoader);
			InfoBead TourismAge_0= (InfoBead)cls_0.newInstance();
			TourismAge_0.setInfoBeadId("TourismAge_0");
			TourismAge_0.setInfobeadModelId("UM1");

			Class<?> cls_1 = Class.forName("infobeadCollection.TourismDateOfBirth", true, classLoader);
			InfoBead TourismDateOfBirth_1= (InfoBead)cls_1.newInstance();
			TourismDateOfBirth_1.setInfoBeadId("TourismDateOfBirth_1");
			TourismDateOfBirth_1.setInfobeadModelId("UM1");

			Class<?> cls_2 = Class.forName("infobeadCollection.TourismEmploymentDomain", true, classLoader);
			InfoBead TourismEmploymentDomain_2= (InfoBead)cls_2.newInstance();
			TourismEmploymentDomain_2.setInfoBeadId("TourismEmploymentDomain_2");
			TourismEmploymentDomain_2.setInfobeadModelId("UM1");

			Class<?> cls_3 = Class.forName("infobeadCollection.TourismFavoriteSeason", true, classLoader);
			InfoBead TourismFavoriteSeason_3= (InfoBead)cls_3.newInstance();
			TourismFavoriteSeason_3.setInfoBeadId("TourismFavoriteSeason_3");
			TourismFavoriteSeason_3.setInfobeadModelId("UM1");

			Class<?> cls_4 = Class.forName("infobeadCollection.TourismReligion", true, classLoader);
			InfoBead TourismReligion_4= (InfoBead)cls_4.newInstance();
			TourismReligion_4.setInfoBeadId("TourismReligion_4");
			TourismReligion_4.setInfobeadModelId("UM1");

			Class<?> cls_5 = Class.forName("infobeadCollection.UserAlcoholRecommendation", true, classLoader);
			InfoBead UserAlcoholRecommendation_5= (InfoBead)cls_5.newInstance();
			UserAlcoholRecommendation_5.setInfoBeadId("UserAlcoholRecommendation_5");
			UserAlcoholRecommendation_5.setInfobeadModelId("UM1");

			Class<?> cls_6 = Class.forName("infobeadCollection.UserUsingSodexoOrRestoPass", true, classLoader);
			InfoBead UserUsingSodexoOrRestoPass_6= (InfoBead)cls_6.newInstance();
			UserUsingSodexoOrRestoPass_6.setInfoBeadId("UserUsingSodexoOrRestoPass_6");
			UserUsingSodexoOrRestoPass_6.setInfobeadModelId("UM1");

			Class<?> cls_7 = Class.forName("infobeadCollection.UsersSeasonalPrefs", true, classLoader);
			InfoBead UsersSeasonalPrefs_7= (InfoBead)cls_7.newInstance();
			UsersSeasonalPrefs_7.setInfoBeadId("UsersSeasonalPrefs_7");
			UsersSeasonalPrefs_7.setInfobeadModelId("UM1");

			Class<?> cls_8 = Class.forName("infobeadCollection.UserReligionFoodConstrains", true, classLoader);
			InfoBead UserReligionFoodConstrains_8= (InfoBead)cls_8.newInstance();
			UserReligionFoodConstrains_8.setInfoBeadId("UserReligionFoodConstrains_8");
			UserReligionFoodConstrains_8.setInfobeadModelId("UM1");

			//Step 2: Connect linked info-beads
			TourismDateOfBirth_1.connect(TourismAge_0);

			TourismAge_0.connect(UserAlcoholRecommendation_5);

			TourismEmploymentDomain_2.connect(UserUsingSodexoOrRestoPass_6);

			TourismFavoriteSeason_3.connect(UsersSeasonalPrefs_7);

			TourismReligion_4.connect(UserReligionFoodConstrains_8);

			//Step 3: Initialize info-beads
			TourismAge_0.initialize();

			TourismDateOfBirth_1.initialize();

			TourismEmploymentDomain_2.initialize();

			TourismFavoriteSeason_3.initialize();

			TourismReligion_4.initialize();

			UserAlcoholRecommendation_5.initialize();

			UserUsingSodexoOrRestoPass_6.initialize();

			UsersSeasonalPrefs_7.initialize();

			UserReligionFoodConstrains_8.initialize();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}