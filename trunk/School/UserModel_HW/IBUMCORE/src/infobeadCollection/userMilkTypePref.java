package infobeadCollection;

import java.sql.Time;

import genericInfoBead.InfoBead;
import genericInfoBead.InfoItem;
import genericInfoBead.Triplet;

public class userMilkTypePref extends InfoBead {

	
	public static final String TRIPLET_ID ="milk_type_triplet";
	
	@Override
	public void handleData(Triplet data)
	{
		
		
		userPreferences.EmilkPrefs milkpref =userPreferences.EmilkPrefs.getRandom();
		PrintMsg("user want " +milkpref.toString());
		Triplet tripletTest = new Triplet(this.TRIPLET_ID);
		Time t = new Time(System.currentTimeMillis());
		InfoItem dataOut = new InfoItem();
		dataOut.setInferenceTime(t);
		dataOut.setExplainInfo("");
		dataOut.setInfoType("userPreferences");
		dataOut.setInfoValue(milkpref);
		tripletTest.setTime(t);
		tripletTest.setInfoItem(dataOut);
		pushData(tripletTest);		
	}

	@Override
	public void initialize() {
	

	}

}
