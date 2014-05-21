package infobeadCollection;

import java.sql.Time;

import genericInfoBead.InfoBead;
import genericInfoBead.InfoItem;

import genericInfoBead.Triplet;

public class userCoffeeSelection extends InfoBead implements Runnable {

	public static final String TRIPLET_ID="user_coffee_selection_triplet";
	
	private userPreferences.EmilkPrefs m_milkType = null; 
	private userPreferences.EcoffeeBlend m_coffeeBlend= null; 
	private userPreferences.EcupSize m_cupSize = null; 
	
	private userPreferences.EdrinkPrefs m_drinkPrefs = null;
	private userPreferences.EdrinkTemp m_drinkTemp = null; 
	
	private userPreferences m_finalPrefs;


	@Override
	public void handleData(Triplet data) 
	{
		
	
		if((String)data.getId() ==  userMilkTypePref.TRIPLET_ID)
		{
			m_milkType = (userPreferences.EmilkPrefs)data.getInfoItem().getInfoValue();
		}
		
		if((String)data.getId() == userCupSizePref.TRIPLET_ID)
		{
			m_cupSize = (userPreferences.EcupSize)data.getInfoItem().getInfoValue();
		}
		
		if((String)data.getId() == userCoffeeBlendPref.TRIPLET_ID)
		{
			m_coffeeBlend =  (userPreferences.EcoffeeBlend)data.getInfoItem().getInfoValue();
		}
		
		if ( data.getId() == UserCoffeePrefernces.TRIPLET_ID)
		{
			m_finalPrefs = (userPreferences) data.getInfoItem().getInfoValue();
		}
		
		if (m_finalPrefs!=null)
		{
			if (m_cupSize!=null)
			{
				m_finalPrefs.setCupSize(m_cupSize);
			}
			if (m_coffeeBlend!=null)
			{
				m_finalPrefs.setBlend(m_coffeeBlend);
			}
			if (m_milkType!=null)
			{
				m_finalPrefs.setMilkType(m_milkType);
			}
		}
		//and now just to send !!!
		
	}
		
		

		
		
	


	@Override
	public void initialize() {
		Thread coffeeSelection = new Thread(this, "");
		coffeeSelection.start();
		
	}

	@Override
	public void run() {
		Triplet tripletTest = new Triplet(this.TRIPLET_ID);
		Time t = new Time(System.currentTimeMillis());
		InfoItem data = new InfoItem();
		data.setInferenceTime(t);
		data.setExplainInfo("");
		data.setInfoType("userPreferences");
		data.setInfoValue(m_finalPrefs);
		tripletTest.setTime(t);
		tripletTest.setInfoItem(data);
		pushData(tripletTest);
		
	}

}
