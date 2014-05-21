package infobeadCollection;

import java.sql.Time;

import org.apache.poi.hssf.util.HSSFColor.TURQUOISE;

import services.CoreRTDisplayService;
import genericInfoBead.InfoBead;
import genericInfoBead.InfoItem;
import genericInfoBead.Triplet;

public class UserNearCoffeeShop extends InfoBead {

	public static final int COFFEE_SHOP_X_LOCATION = 100;
	public static final int COFFEE_SHOP_Y_LOCATION = 100;
	
	public static final String TRIPLET_ID ="user_near_coffee_shop";
	
	public UserNearCoffeeShop()
	{
		super();
	}
	//private CoreRTDisplayService m_display;
	@Override
	public void handleData(Triplet data) {
		
		//System.out.println("got triplet!!!");
		
		if(data.getId() ==  Location.TRIPLET_ID)
		{
			InfoItem infoData = data.getInfoItem();
			if (infoData.getInfoType() == Location.LOCATION_ID)
			{
			
				Location loc =  (Location) infoData.getInfoValue();
				System.out.println("got user location:"+loc.toString());
					
				
				try {Thread.sleep(1500);} catch (InterruptedException e) {}
				Triplet tripletNearCoffee = new Triplet(TRIPLET_ID);
				Time t = new Time(System.currentTimeMillis());
				InfoItem pushdata = new InfoItem();
				boolean at_theCoffeShop =false;
				if ((loc.getM_x()==COFFEE_SHOP_X_LOCATION) && loc.getM_y() == COFFEE_SHOP_Y_LOCATION)
				{
					at_theCoffeShop= true;
					System.out.println("user at the coffee shop");
					
				}
				
				
				
				pushdata.setInfoValue(new Boolean(at_theCoffeShop));
				
				tripletNearCoffee.setInfoItem(pushdata);
				tripletNearCoffee.setTime(t);
				pushData(tripletNearCoffee);
				
				
			}
		}
		//m_display.displayTriplet(data);
	}

	@Override
	public void initialize() {
		//m_display = new CoreRTDisplayService();

	}

	
	
}
