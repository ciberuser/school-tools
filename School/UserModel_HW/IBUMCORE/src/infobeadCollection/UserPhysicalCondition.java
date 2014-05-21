package infobeadCollection;

import java.sql.Time;

import genericInfoBead.InfoBead;
import genericInfoBead.InfoItem;
import genericInfoBead.Triplet;

public class UserPhysicalCondition extends InfoBead implements Runnable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Location location = null; 
	UserTemperature temp = null;
	public static final String TRIPLET_ID="pysicalCondition_triplet";
	private int m_last_temp =0;
	@Override
	public void handleData(Triplet data) {

		
		if(data.getId().equals(UserTemperature.TRIPLET_ID))
		{
			
			Integer temp = (Integer)data.getInfoItem().getInfoValue();
			if (m_last_temp != temp)
			 {
				m_last_temp = temp;
				PrintMsg("user Temperature : "+ temp.toString());
			 }
			
						
			Triplet pysicalTriplet = new Triplet(TRIPLET_ID);
			Time t = new Time(System.currentTimeMillis());
			InfoItem dataItem = new InfoItem();
			dataItem.setInferenceTime(t);
			dataItem.setExplainInfo("user physical condition");
			dataItem.setInfoType("");
			dataItem.setInfoUnits("");
			dataItem.setInfoValue(temp);
			pysicalTriplet.setTime(t);
			pysicalTriplet.setInfoItem(dataItem);
			if (this.location!=null) 
			{
				if (this.location.getM_x() ==UserNearCoffeeShop.COFFEE_SHOP_X_LOCATION && this.location.getM_y() == UserNearCoffeeShop.COFFEE_SHOP_Y_LOCATION);
				pushData(pysicalTriplet);
			}
		}
				
		
		if(data.getId().equals(Location.TRIPLET_ID))
		{
			this.location = (Location)data.getInfoItem().getInfoValue();
			//this.PrintMsg(this.location.toString());
		}
		
		
	}

	@Override
	public void initialize() {
		Thread physicalConThread = new Thread(this, "user physical condition thread");
		physicalConThread.start();
		
		
	}

	@Override
	public void run() {
	
		/*Triplet tripletTest = new Triplet("pysicalCondition_triplet");
			Time t = new Time(System.currentTimeMillis());
			InfoItem data = new InfoItem();
			data.setInferenceTime(t);
			data.setExplainInfo("user physical condition");
			data.setInfoType("");
			data.setInfoUnits("");
			//data.setInfoValue();
			tripletTest.setTime(t);
			tripletTest.setInfoItem(data);
			pushData(tripletTest);
		*/
	}

}
