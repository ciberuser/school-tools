package infobeadCollection;


import java.sql.Time;

import genericInfoBead.InfoBead;
import genericInfoBead.InfoItem;
import genericInfoBead.Triplet;

public class userCoffeeBlendPref extends InfoBead implements Runnable{

	
	
	public static final String TRIPLET_ID="blend_type_triplet";

	@Override
	public void handleData(Triplet data) {
			 
		PrintMsg("got triplet...");
		 userPreferences.EcoffeeBlend userSelectBlend; 
		 userSelectBlend = userPreferences.EcoffeeBlend.getRandom();
		 PrintMsg("user Want " +userSelectBlend );
		 
				Triplet tripletTest = new Triplet(this.TRIPLET_ID);
				Time t = new Time(System.currentTimeMillis());
				InfoItem dataOut = new InfoItem();
				dataOut.setInferenceTime(t);
				dataOut.setExplainInfo("");
				dataOut.setInfoType("userPreferences");
				dataOut.setInfoValue(userSelectBlend);
				tripletTest.setTime(t);
				tripletTest.setInfoItem(dataOut);
				pushData(tripletTest);		
	}

	@Override
	public void initialize() {
		Thread blend = new Thread(this,this.TRIPLET_ID +"thread");
		blend.start();
		
	}

	@Override
	public void run() {
		while(true){}
		
	}
	
	


}
