package infobeadCollection;

import java.sql.Time;

import genericInfoBead.InfoBead;
import genericInfoBead.InfoItem;
import genericInfoBead.Triplet;

public class UserPhysicalCondition extends InfoBead implements Runnable {

	
	Location location = null; 
	UserTemperature temp = null;
	
	@Override
	public void handleData(Triplet data) {

		if(data.getId().equals(Location.TRIPLET_ID))
		{
			this.location = (Location)data.getInfoItem().getInfoValue();
			
		}
		
		if(data.getId().equals(UserTemperature.TRIPLET_ID))
		{
			this.temp = (UserTemperature)data.getInfoItem().getInfoValue();
			
		}
		
		while(this.location == null && this.temp == null){}
		

	}

	@Override
	public void initialize() {
		Thread physicalConThread = new Thread(this, "user physical condition thread");
		physicalConThread.start();
		
		
	}

	@Override
	public void run() {
		Triplet tripletTest = new Triplet("user physical condition info bead");

			int randomSleep = 500 + (int)(Math.random() * ((2000 - 500) + 1));

			Time t = new Time(System.currentTimeMillis());
			InfoItem data = new InfoItem();
			data.setInferenceTime(t);
			data.setExplainInfo("Main >> Testing Display Services");
			data.setInfoType("test");
			data.setInfoUnits("testUnits");
			//data.setInfoValue();
			tripletTest.setTime(t);
			tripletTest.setInfoItem(data);
			pushData(tripletTest);
		
	}

}
