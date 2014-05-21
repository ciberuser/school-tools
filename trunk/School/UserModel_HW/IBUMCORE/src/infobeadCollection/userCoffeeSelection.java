package infobeadCollection;

import java.sql.Time;

import genericInfoBead.InfoBead;
import genericInfoBead.InfoItem;

import genericInfoBead.Triplet;

public class userCoffeeSelection extends InfoBead implements Runnable {

	public static final String TRIPLET_ID="user_coffee_selection_triplet";
	
	userPreferences.EmilkPrefs milkType = null; 
	userPreferences.coffeeBlend coffeeBlend= null; 
	userPreferences.cupSize cupSize = null; 
	
	userPreferences.EdrinkPrefs drinkPrefs = null;
	userPreferences.EdrinkTemp drinkTemp = null; 
	
	userPreferences finalPrefs;


	@Override
	public void handleData(Triplet data) {
		
		
		
		if((String)data.getId() == "milk_type_triplet")
		{
			this.milkType = (userPreferences.EmilkPrefs)data.getInfoItem().getInfoValue();
		}
		
		if((String)data.getId() == "cup_size_triplet")
		{
			this.cupSize = (userPreferences.cupSize)data.getInfoItem().getInfoValue();
		}
		
		if((String)data.getId() == "blend_type_triplet")
		{
			this.coffeeBlend =  (userPreferences.coffeeBlend)data.getInfoItem().getInfoValue();
		}
		
		
		userPreferences p = new userPreferences();
		

		this.drinkPrefs =  userPreferences.EdrinkPrefs.getRandom();
		
		p.SetWhatToDrink(this.drinkPrefs, this.drinkTemp, this.milkType);
		
		this.finalPrefs = p; 

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
		data.setInfoValue(this.finalPrefs);
		tripletTest.setTime(t);
		tripletTest.setInfoItem(data);
		pushData(tripletTest);
		
	}

}
