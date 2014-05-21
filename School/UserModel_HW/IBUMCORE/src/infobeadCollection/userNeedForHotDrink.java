package infobeadCollection;

import java.sql.Time;

import genericInfoBead.InfoBead;
import genericInfoBead.InfoItem;
import genericInfoBead.Triplet;

public class userNeedForHotDrink extends InfoBead implements Runnable{


	private static final long serialVersionUID = 1L;

	public boolean needForHotDrink; 
	
	public static final String TRIPLET_ID="need_of_hot_drink_triplet";
	
	@Override
	public void handleData(Triplet data) {

		if((Integer)data.getInfoItem().getInfoValue() < 38)
		{
			this.needForHotDrink = true; 
		}
		
	}

	@Override
	public void initialize() {
		Thread hotDrink = new Thread(this, "");
		hotDrink.start();
		
	}

	@Override
	public void run() {
		Triplet tripletTest = new Triplet(this.TRIPLET_ID);
		Time t = new Time(System.currentTimeMillis());
		InfoItem data = new InfoItem();
		data.setInferenceTime(t);
		data.setExplainInfo("Main >> Testing Display Services");
		data.setInfoType("userNeedForHotDrinkBoolean");
		data.setInfoValue(this.needForHotDrink);
		tripletTest.setTime(t);
		tripletTest.setInfoItem(data);
		pushData(tripletTest);
		
		
	}

}
