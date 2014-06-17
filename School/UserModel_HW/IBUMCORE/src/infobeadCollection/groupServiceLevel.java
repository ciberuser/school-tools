package infobeadCollection;

import java.util.Date;
import java.util.List;

import genericInfoBead.InfoBead;
import genericInfoBead.Triplet;


// this info bead class recommends to the coffee shop manager what service level
// this specific group should get, depndingg on its size and time of the day
// this class uses the 'groupServiceLevelSuggestion' class for making
// the recommendation
public class groupServiceLevel extends InfoBead

{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void handleData(Triplet data) {

		// get group size
		List<UserPersonalData> groupOfUsers =(List<UserPersonalData>)data.getInfoItem().getInfoValue();
		int numberOfUsers = 	groupOfUsers.size();
		
		Date time = data.getTime();
		
		groupServiceLevelSuggestion ls = new groupServiceLevelSuggestion();
		
		String suggestedServiceLevel = ls.getSuggestion(numberOfUsers, time).toString();
		
		System.out.println("System suggest service level to the group: " + suggestedServiceLevel);
		
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}

}
