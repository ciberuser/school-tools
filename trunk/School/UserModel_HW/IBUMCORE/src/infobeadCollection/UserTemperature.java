package infobeadCollection;

import genericInfoBead.InfoBead;
import genericInfoBead.Triplet;
import genericInfoBead.InfoItem;

import java.math.*;
import java.sql.Time;

import org.omg.CORBA.INTERNAL;

public class UserTemperature extends InfoBead implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String TRIPLET_ID="temperature_triplet";

	Integer userTemperature; 
	final int maxTemp= 42; 
	final int minTemp= 36;
	
	
	@Override
	public void handleData(Triplet data) {
		 
		
	}


	@Override
	// initialize user temperature 
	public void initialize() {

		Thread gal = new Thread(this, "");
		gal.start();
	}


	@Override
	public void run() {
		
		
		
		for(int i = 0; i<11; i++)
		{
			Integer temperature = this.minTemp + (int)(Math.random() * ((this.maxTemp-this.minTemp)+1));
			Triplet tripletTest = new Triplet("temperature_triplet");
			Time t = new Time(System.currentTimeMillis());
			InfoItem data = new InfoItem();
			data.setInferenceTime(t);
			data.setExplainInfo("Main >> Testing Display Services");
			data.setInfoType("user temperature");
			data.setInfoUnits("C°");
			
			
			data.setInfoValue(temperature);
			tripletTest.setTime(t);
			tripletTest.setInfoItem(data);
			pushData(tripletTest);
		}
	}

}
