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
	public void handleData(Triplet data) 
	{

		this.needForHotDrink = ((Integer)data.getInfoItem().getInfoValue() < 38);
			
		Triplet tripletTest = new Triplet(this.TRIPLET_ID);
		Time t = new Time(System.currentTimeMillis());
		InfoItem dataItem = new InfoItem();
		dataItem.setInferenceTime(t);
		dataItem.setExplainInfo("Main >> Testing Display Services");
		dataItem.setInfoType("userNeedForHotDrinkBoolean");
		dataItem.setInfoValue(this.needForHotDrink);
		tripletTest.setTime(t);
		tripletTest.setInfoItem(dataItem);
		pushData(tripletTest);
	}

	@Override
	public void initialize() {
		Thread hotDrink = new Thread(this, "");
		hotDrink.start();
		
	}

	@Override
	public void run() 
	{
		
			
		
		
	}

}
