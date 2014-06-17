package infobeadCollection;

import genericInfoBead.InfoBead;
import genericInfoBead.Triplet;

public class UserReligionFoodConstrains extends InfoBead {

	@Override
	public void handleData(Triplet data) {
		// TODO Auto-generated method stub
		
		String religion = data.getInfoItem().getInfoValue().toString();
		
		if(religion.equals("MUSLIM"))
		{
			System.out.println("=============================================");
			System.out.println("Coffeshop recommender: Please bring Halal menu");
		}
		
		if(religion.equals("JEWISH"))
		{
			System.out.println("=============================================");
			System.out.println("Coffeshop recommender: Please bring Druze menu");
		}
		
		if(religion.equals("CHRISTIAN"))
		{
			System.out.println("=============================================");
			System.out.println("Coffeshop recommender: User is CHRISTIAN, no constrains");
		}
		System.out.println("=============================================");
				
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}

}
