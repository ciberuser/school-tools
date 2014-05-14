package infobeadCollection;

import genericInfoBead.InfoBead;
import genericInfoBead.Triplet;

import java.math.*;

public class UserTemperature extends InfoBead implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String TRIPLET_ID="temp_triplet";

	int userTemperature; 
	final int maxTemp= 42; 
	final int minTemp= 36;
	
	
	@Override
	public void handleData(Triplet data) {
		 
		
	}


	@Override
	// initialize user temperature 
	public void initialize() {

	}


	@Override
	public void run() {
		
		this.userTemperature = this.minTemp + (int)(Math.random() * ((this.maxTemp-this.minTemp)+1));
	}

}
