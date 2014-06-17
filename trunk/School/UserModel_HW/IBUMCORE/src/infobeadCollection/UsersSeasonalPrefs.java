package infobeadCollection;

import genericInfoBead.InfoBead;
import genericInfoBead.Triplet;

// this class uses the information from the TourismFavoriteSeason to recommend
// for certain seasonal dishesh to the user 
public class UsersSeasonalPrefs extends InfoBead {

	@Override
	public void handleData(Triplet data) {
		
String seasonalPref = data.getInfoItem().getInfoValue().toString();
		
		if(seasonalPref.equals("Summer"))
		{
			System.out.println("=============================================");
			System.out.println("Coffeshop recommender: Recommend summer dishes");
		}
		
		if(seasonalPref.equals("Winter"))
		{
			System.out.println("=============================================");
			System.out.println("Coffeshop recommender: Recommend winter dishes");
		}
		
		if(seasonalPref.equals("Spring"))
		{
			System.out.println("=============================================");
			System.out.println("Coffeshop recommender: Recommend Spring dishes");
		}
		
		if(seasonalPref.equals("Autumn"))
		{
			System.out.println("=============================================");
			System.out.println("Coffeshop recommender: Recommend Autumn dishes");
		}
		System.out.println("=============================================");
		
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}

}
