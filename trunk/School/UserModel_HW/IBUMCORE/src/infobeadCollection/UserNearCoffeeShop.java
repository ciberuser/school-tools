package infobeadCollection;

import genericInfoBead.InfoBead;
import genericInfoBead.InfoItem;
import genericInfoBead.Triplet;

public class UserNearCoffeeShop extends InfoBead {

	public static final int COFFEE_SHOP_X_LOCATION = 100;
	public static final int COFFEE_SHOP_Y_LOCATION = 100;
	
	public UserNearCoffeeShop()
	{
		super();
	}
	
	@Override
	public void handleData(Triplet data) {
		
		if(data.getId() ==  Location.TRIPLET_ID)
		{
			InfoItem infoData = data.getInfoItem();
			if (infoData.getInfoType() == Location.LOCATION_ID)
			{
				Location loc =  (Location) infoData.getInfoValue();
				if (loc.m_x == COFFEE_SHOP_X_LOCATION && loc.m_y ==  COFFEE_SHOP_Y_LOCATION);
				{
					System.out.print( "user is in the coffeeShop");
				}
				
			}
		}

	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub

	}

	
	
}
