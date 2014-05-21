package infobeadCollection;

import genericInfoBead.InfoBead;
import genericInfoBead.Triplet;

public class userCoffeePrefernces extends InfoBead implements Runnable {


	private static final long serialVersionUID = 1L;
	
	public static final String TRIPLET_ID="coffee_prefs_triplet";
	
	public boolean need_for_hot_drink = false;
	public boolean need_for_cold_drink = false; 
	public boolean user_location_near_coffeeShop = false; 
	public String userPrefs; 

			if((Integer)data.getInfoItem().getInfoValue() > 38)
		{
			this.needForColdDrink = true; 
		}

	@Override
	public void handleData(Triplet data) {
		
		switch(data.getId())
		{
		case userNeedForColdDrink.TRIPLET_ID : 
		if((Boolean)data.getInfoItem().getInfoValue()){this.need_for_cold_drink = true;};
		break;
		
		
		case userNeedForHotDrink.TRIPLET_ID :
		if((Boolean)data.getInfoItem().getInfoValue())
		{this.need_for_hot_drink = true;};
		break;
		
		
		case UserNearCoffeeShop.TRIPLET_ID: 
		m_neerCoffeeShop =  (boolean)data.getInfoItem().getInfoValue();
		break;
		}

		
	}

	@Override
	public void initialize() {
				Thread prefs = new Thread(this, "");
				prefs.start();
		
	}
	
		@Override
	public void run() {
	
			this.userPrefs = this.needForColdDrink.toString() + '|' + this.need_for_hot_drink.toString() + '|' + this.user_location_near_coffeeShop.toString;

			Triplet tripletTest = new Triplet("coffee_prefs_triplet");

			Time t = new Time(System.currentTimeMillis());
			InfoItem data = new InfoItem();
			data.setInferenceTime(t);
			data.setExplainInfo("this triplet shows user preferences for coffee");
			data.setInfoType("user temperature");
			data.setInfoUnits("C°");
			
			
			data.setInfoValue(this.userPrefs);
			tripletTest.setTime(t);
			tripletTest.setInfoItem(data);
			pushData(tripletTest); 

	}

}
