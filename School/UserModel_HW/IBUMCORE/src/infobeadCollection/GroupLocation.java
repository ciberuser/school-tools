package infobeadCollection;


import java.sql.Time;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import genericInfoBead.InfoBead;
import genericInfoBead.InfoItem;
import genericInfoBead.Triplet;

public class GroupLocation extends InfoBead {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String TRIPLET_ID="group_location_triplet";

	@Override
	public void handleData(Triplet data) {


	}

	@Override
	public void initialize() {
	
		  int Min = 5;
		  int Max = 20; 
		 
		  // random group size 
		 int groupSize = Min + (int)(Math.random() * ((Max - Min) + 1));
		 
		 // pool of user locations
		 ExecutorService usersLocationThreadPool = Executors.newFixedThreadPool(Max);

	        for (int i = 0; i < groupSize; i++) 
	          {
	            Runnable singleUserThread = new UserPhysicalLocation();
	            usersLocationThreadPool.execute(singleUserThread);
	          }
	        
	        
	        // number of user threads can use this casting in the next info bead
	        int poolSize = ((ThreadPoolExecutor) usersLocationThreadPool).getLargestPoolSize();
	        
	        System.out.println("new group enter coffeeshop, size: " + poolSize);
	        
	        // when finished executing all threads, the group is in the right location
	        Triplet groupLocTrip = new Triplet(this.TRIPLET_ID);
			Time t = new Time(System.currentTimeMillis());
	        
	        InfoItem data = new InfoItem();
	    	data.setInferenceTime(t);
			data.setExplainInfo("users groop thread pool");
			data.setInfoType("user location thread pool ");
			data.setInfoUnits("Executors.newFixedThreadPool");
			data.setInfoValue(usersLocationThreadPool);
			
			

		
			groupLocTrip.setTime(t);
			groupLocTrip.setInfoItem(data);
			
			
			pushData(groupLocTrip);
	}

}
