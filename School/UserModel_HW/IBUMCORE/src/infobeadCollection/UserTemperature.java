package infobeadCollection;

import genericInfoBead.InfoBead;
import genericInfoBead.Triplet;

import java.math.*;

public class UserTemperature extends InfoBead{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	int userTemperature; 
	final int maxTemp= 42; 
	final int minTemp= 36;
	
	@Override
	public void handleData(Triplet data) {
		// TODO Auto-generated method stub
		
	}


	@Override
	// initialize user temperature 
	public void initialize() {
		this.userTemperature = this.minTemp + (int)(Math.random() * ((this.maxTemp-this.minTemp)+1));
	}

}
