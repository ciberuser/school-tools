package infobeadCollection;

import genericInfoBead.InfoBead;
import genericInfoBead.Triplet;

public class userCoffeePrefernces extends InfoBead implements Runnable {


	private boolean m_neerCoffeeShop; 
	public userCoffeePrefernces()
	{
		super();
		m_neerCoffeeShop = false;
	}
	
	
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleData(Triplet data) {
		
		switch(data.getId())
		{
		case userNeedForColdDrink.TRIPLET_ID : ;
		break;
		
		case userNeedForHotDrink.TRIPLET_ID : ;
		break;
		
		case UserNearCoffeeShop.TRIPLET_ID: 
			m_neerCoffeeShop =  (boolean)data.getInfoItem().getInfoValue();
		break;
			
		}

		
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}

}
