package infobeadCollection;

import genericInfoBead.InfoBead;
import genericInfoBead.Triplet;


// this info bead is using the data from reused info bead 'TourismEmploymentDomain"
// it checks for the possibility that the user owns a restaurant magnetic card
public class UserUsingSodexoOrRestoPass extends InfoBead {

	@Override
	public void handleData(Triplet data) {
	String EmploymentDomain = data.getInfoItem().getInfoValue().toString();
		
		if(EmploymentDomain.equals("Hightech"))
		{
			System.out.println("=============================================");
			System.out.println("Coffeshop recommender: Ask if user owns Sibus / RestoPass card ");
		}
		if(EmploymentDomain.equals("Independent"))
		{
			System.out.println("=============================================");
			System.out.println("Coffeshop recommender: Ask if user want to register Sibus / RestoPass card");
		}


		System.out.println("=============================================");
		
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}

}
