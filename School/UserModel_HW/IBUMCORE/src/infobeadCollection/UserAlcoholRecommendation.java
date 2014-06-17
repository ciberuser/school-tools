package infobeadCollection;

import genericInfoBead.InfoBead;
import genericInfoBead.Triplet;

// this class is using the input from another group info bead- TourismAge
// If age is > 18, tell the waitress bring the alcoholic bevs. menu

public class UserAlcoholRecommendation extends InfoBead {

	@Override
	public void handleData(Triplet data) {
	
		String age = data.getInfoItem().getInfoValue().toString();
		
		double ageDouble = Double.parseDouble(age);
		if(ageDouble > 18)
		{
			System.out.println("==================================================");
			System.out.println("Coffee shop recommender: Waitress, please bring to user alcoholic beverges menu!");
		}
		else
		{
			System.out.println("===================================================");
			System.out.println("Coffee shop recommender: Waitress, please do not bring to user alcoholic beverges menu!");
		}
		
		System.out.println("=============================================");
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}

}
