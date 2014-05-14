package infobeadCollection;

import services.CoreRTDisplayService;
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
	//private CoreRTDisplayService m_display;
	@Override
	public void handleData(Triplet data) {
		
		System.out.println("got triplet!!!");
		
		if(data.getId() ==  Location.TRIPLET_ID)
		{
			InfoItem infoData = data.getInfoItem();
			if (infoData.getInfoType() == Location.LOCATION_ID)
			{
			
				Location loc =  (Location) infoData.getInfoValue();
				System.out.println("got user location!! x="+loc.getM_x()+" y=" + loc.getM_y() );
				if ((loc.getM_x()==COFFEE_SHOP_X_LOCATION) && (loc.getM_y()==COFFEE_SHOP_Y_LOCATION));
				{
					System.out.print( "user is in the coffeeShop");
				}
				
			}
		}
		//m_display.displayTriplet(data);
	}

	@Override
	public void initialize() {
		//m_display = new CoreRTDisplayService();

	}

	
	
}
