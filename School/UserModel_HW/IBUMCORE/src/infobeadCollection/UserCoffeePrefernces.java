package infobeadCollection;

import genericInfoBead.InfoBead;
import genericInfoBead.Triplet;

public class UserCoffeePrefernces extends InfoBead implements Runnable {


	private boolean m_neerCoffeeShop; 
	private boolean m_want_cold ;
	private boolean m_want_hot;
	
	public UserCoffeePrefernces()
	{
		super();
		m_neerCoffeeShop = false;
		m_want_cold =false;
		m_want_hot = false;
		
	}
	
	public static final String TRIPLET_ID="coffee_prefs_triplet";
	/*
    public String  
			if((Integer)data.getInfoItem().getInfoValue() > 38)
		{
			this.needForColdDrink = true; 
		}
*/
	
	@Override
	public void handleData(Triplet data) {
		
		switch(data.getId())
		{
		case userNeedForColdDrink.TRIPLET_ID :
			m_want_cold = (boolean)data.getInfoItem().getInfoValue();
		break;
		
		
		case userNeedForHotDrink.TRIPLET_ID : 
			m_want_hot = (boolean)data.getInfoItem().getInfoValue();
		break;
		
		
		case UserNearCoffeeShop.TRIPLET_ID: 
		m_neerCoffeeShop =  (boolean)data.getInfoItem().getInfoValue();
		break;
		}
		if (!m_neerCoffeeShop)
		{
			PrintMsg("user is not in  the coffee shop ...:(");
		}
		else
		{
				
		}
		
		

		
	}

	@Override
	public void initialize() {
		Thread locationThread = new Thread(this,"userCoffeePreferncesThread");
		locationThread.start();
	}
	
		@Override
	public void run() 
	{
	/*
			this.userPrefs = this.needForColdDrink.toString() + '|' + this.need_for_hot_drink.toString() + '|' + this.user_location_near_coffeeShop.toString;

			Triplet tripletTest = new Triplet("coffee_prefs_triplet");

			Time t = new Time(System.currentTimeMillis());
			InfoItem data = new InfoItem();
			data.setInferenceTime(t);
			data.setExplainInfo("this triplet shows user preferences for coffee");
			data.setInfoType("user temperature");
			data.setInfoUnits("C�");
			
			
			data.setInfoValue(this.userPrefs);
			tripletTest.setTime(t);
			tripletTest.setInfoItem(data);
			pushData(tripletTest); 
*/
			
	}

}
