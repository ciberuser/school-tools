package infobeadCollection;

import java.sql.Time;

import genericInfoBead.InfoBead;
import genericInfoBead.InfoItem;
import genericInfoBead.Triplet;

public class userCupSizePref extends InfoBead {
	
	public static final String TRIPLET_ID="cup_size_triplet";
	userPreferences.EcupSize selectedCupSize; 

	@Override
	public void handleData(Triplet data) {

		 this.selectedCupSize = userPreferences.EcupSize.getRandom();
		 
		 	PrintMsg("user want " +  selectedCupSize.toString());
			Triplet tripletCupSize = new Triplet(this.TRIPLET_ID);
			Time t = new Time(System.currentTimeMillis());
			InfoItem dataOut = new InfoItem();
			dataOut.setInferenceTime(t);
			dataOut.setExplainInfo("user want size of  " +selectedCupSize.toString() +"size cup");
			dataOut.setInfoType("userPreferences");
			dataOut.setInfoValue(this.selectedCupSize);
			tripletCupSize.setTime(t);
			tripletCupSize.setInfoItem(dataOut);
			pushData(tripletCupSize);		
		
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}

}
