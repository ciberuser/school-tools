package infobeadCollection;



import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import genericInfoBead.InfoBead;
import genericInfoBead.InfoItem;
import genericInfoBead.Triplet;

// this class generates a pool of users, entering together to the coffee shop
// all together
// it pushes the data for the usage of the rest of the grroup models
public class GroupLocation extends InfoBead implements Runnable {

	 private List<UserPersonalData> m_usersInGroup ;
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String TRIPLET_ID="group_location_triplet";
	public static final int GROUP_SIZE = 3; 
	private boolean selfGenerated; 
		

	@Override
	// this method gets the user threads, when few of them are in the same location 
	// push data
	public void handleData(Triplet data) {
		

			if((boolean)data.getInfoItem().getInfoValue())
			{
				UserPersonalData temp = new UserPersonalData();
				m_usersInGroup.add(temp);

				if(m_usersInGroup.size() >= GROUP_SIZE	)
				{
					pushGroupData();
					selfGenerated = true; 
					
					
				}
			}
					
		
	}

	@Override
	public void initialize() {
		m_usersInGroup = new ArrayList<UserPersonalData>();
		selfGenerated = false; 
		Thread groupThread = new Thread(this, "");
		groupThread.start();
		
	}

	@Override
	public void run() {
		
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (m_usersInGroup.size()==0)
		{
		System.out.println("going to run group location");
		while(!this.selfGenerated)
		{
			
			try {
			    Thread.sleep(2000);
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
			
		  int Min = 5;
		  int Max = 20; 
		 
		  // random group size 
		 int groupSize = Min + (int)(Math.random() * ((Max - Min) + 1));
		 
		 // pool of user locations
		 ExecutorService usersLocationThreadPool = Executors.newFixedThreadPool(Max);

		 
	        for (int i = 0; i < groupSize; i++) 
	          {
	        		m_usersInGroup.add(new UserPersonalData());
	        		UserPhysicalLocation singleUserThread = new UserPhysicalLocation();
	        		singleUserThread.isPartOfGoup = true;
	        		usersLocationThreadPool.execute(singleUserThread);
	          }
	        pushGroupData();   
	        
	        
	        // number of user threads can use this casting in the next info bead
	       
	      
		}
		}
	}

	private void pushGroupData() {
		
		
		// when finished executing all threads, the group is in the right location
		Triplet groupLocTrip = new Triplet(this.TRIPLET_ID);
		Time t = new Time(System.currentTimeMillis());
		
		InfoItem data = new InfoItem();
		data.setInferenceTime(t); 
		data.setExplainInfo("users groop thread pool");
		data.setInfoType("user location thread pool ");
		data.setInfoUnits("Executors.newFixedThreadPool");
		data.setInfoValue(m_usersInGroup);
		this.PrintMsg("new group enter coffeeshop, size: " + m_usersInGroup.size());
		
			
		groupLocTrip.setTime(t);
		groupLocTrip.setInfoItem(data);
		
		
		pushData(groupLocTrip);
	}

}
