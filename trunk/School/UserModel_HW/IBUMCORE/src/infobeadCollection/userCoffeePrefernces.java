package infobeadCollection;

import genericInfoBead.InfoBead;
import genericInfoBead.Triplet;

public class userCoffeePrefernces extends InfoBead implements Runnable {


	private static final long serialVersionUID = 1L;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleData(Triplet data) {
		
		switch(data.getId())
		{
		case userNeedForColdDrink.TRIPLET_ID : ;
		
		
		case userNeedForHotDrink.TRIPLET_ID : ;
		
		
		//case userPhysicalLocation.TRIPLET_ID : ;
		}

		
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}

}
