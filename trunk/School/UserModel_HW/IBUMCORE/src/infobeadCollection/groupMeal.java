package infobeadCollection;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import javax.swing.JOptionPane;

import genericInfoBead.InfoBead;
import genericInfoBead.Triplet;

// this class recommends a group of users (threads pool), a group meal
// depending on the group size and hour of the day 
// this class uses the groupMealSuggestion class to recommend the group. 
public class groupMeal extends InfoBead{

	@Override
	public void handleData(Triplet data) {
		

		 
		List<UserPersonalData> groupOfUsers =(List<UserPersonalData>)data.getInfoItem().getInfoValue();
		int numberOfUsers = 	groupOfUsers.size();
		
		//int numberOfUsers = pool.getLargestPoolSize();
		
		Date time = data.getTime();
		
		groupMealSuggestion su = new groupMealSuggestion();
		
		String suggestedMeal = su.getSuggestion(numberOfUsers, time).toString();
		
		//JOptionPane.showMessageDialog(null,"System suggestion to the group: " + suggestedMeal ,"Group suggestion",JOptionPane.YES_NO_OPTION);
		System.out.println("System suggestion to the group: " + suggestedMeal);
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}

}
