package infobeadCollection;

import java.sql.Time;

import genericInfoBead.InfoBead;
import genericInfoBead.InfoItem;
import genericInfoBead.Triplet;

public class userNeedForColdDrink extends InfoBead implements Runnable {
	

	private static final long serialVersionUID = 1L;

	public boolean needForColdDrink;
	
	public static final String TRIPLET_ID="need_of_cold_drink_triplet";

	@Override
	public void handleData(Triplet data) {

		if((Integer)data.getInfoItem().getInfoValue() > 38)
		{
			this.needForColdDrink = true; 
		}
	}

	@Override
	public void initialize() {
		Thread coldDrink = new Thread(this, "");
		coldDrink.start();
		
	}

	@Override
	public void run() {
		Triplet tripletTest = new Triplet(this.TRIPLET_ID);
		Time t = new Time(System.currentTimeMillis());
		InfoItem data = new InfoItem();
		data.setInferenceTime(t);
		data.setExplainInfo("");
		data.setInfoType(TRIPLET_ID + "boolean");
		data.setInfoValue(this.needForColdDrink);
		tripletTest.setTime(t);
		tripletTest.setInfoItem(data);
		pushData(tripletTest);
		
	}

}
