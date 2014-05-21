package infobeadCollection;


import java.sql.Time;

import genericInfoBead.InfoBead;
import genericInfoBead.InfoItem;
import genericInfoBead.Triplet;

public class coffeeBlend extends InfoBead {

	userPreferences.coffeeBlend userSelectBlend; 
	
	public static final String TRIPLET_ID="blend_type_triplet";

	@Override
	public void handleData(Triplet data) {
			 
		 this.userSelectBlend = userPreferences.coffeeBlend.getRandom();
		 
		 
				Triplet tripletTest = new Triplet(this.TRIPLET_ID);
				Time t = new Time(System.currentTimeMillis());
				InfoItem dataOut = new InfoItem();
				dataOut.setInferenceTime(t);
				dataOut.setExplainInfo("");
				dataOut.setInfoType("userPreferences");
				dataOut.setInfoValue(this.userSelectBlend);
				tripletTest.setTime(t);
				tripletTest.setInfoItem(dataOut);
				pushData(tripletTest);		
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}
	


}