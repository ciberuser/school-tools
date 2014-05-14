package infobeadCollection;

import genericInfoBead.InfoBead;
import genericInfoBead.Triplet;

public class UserPhysicalCondition extends InfoBead {

	
	Location location; 
	UserTemperature temp;
	
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
		
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}

}
