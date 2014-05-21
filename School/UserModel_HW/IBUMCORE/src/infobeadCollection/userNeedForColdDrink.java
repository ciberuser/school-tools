package infobeadCollection;

import java.sql.Time;

import genericInfoBead.InfoBead;
import genericInfoBead.InfoItem;
import genericInfoBead.Triplet;

public class userNeedForColdDrink extends InfoBead  {
	

	private static final long serialVersionUID = 1L;

	public boolean needForColdDrink;
	
	public static final String TRIPLET_ID="need_of_cold_drink_triplet";

	@Override
	public void handleData(Triplet data)
	{
	
		
		this.needForColdDrink = ((Integer)data.getInfoItem().getInfoValue() > 38);
		
			Triplet tripletTest = new Triplet(this.TRIPLET_ID);
			Time t = new Time(System.currentTimeMillis());
			InfoItem dataItem = new InfoItem();
			dataItem.setInferenceTime(t);
			dataItem.setExplainInfo("");
			dataItem.setInfoType(TRIPLET_ID + "boolean");
			dataItem.setInfoValue(this.needForColdDrink);
			tripletTest.setTime(t);
			tripletTest.setInfoItem(dataItem);
			pushData(tripletTest);
		
	}

	@Override
	public void initialize() {
		
	}


}
