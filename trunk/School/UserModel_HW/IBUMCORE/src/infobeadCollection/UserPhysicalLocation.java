package infobeadCollection;

import java.sql.Time;

import genericInfoBead.InfoBead;
import genericInfoBead.InfoItem;
import genericInfoBead.Triplet;
import java.util.Random;


public class UserPhysicalLocation extends InfoBead implements Runnable{
	

	private static final long serialVersionUID = 1L;
	
	
	public UserPhysicalLocation()
	{
		super();	
	}
	
	@Override
	public void handleData(Triplet data)
	{
		// TODO Auto-generated method stub

	}
	
	

	@Override
	public void initialize()
	{
		m_x_location=0;
		m_y_location =0;
		Thread locationThread = new Thread(this,"");
		locationThread.start();
	}
	
	public void GoTo(int x , int y)
	{
		m_x_location +=x;
		m_y_location +=y;
	}
	
	//if location in map  
	int m_x_location;
	int m_y_location;
	@Override
	public void run() 
	{
		Triplet tripletloc = new Triplet(Location.TRIPLET_ID);
		for (int ix =0, iy=0; ix< 120 ; ix+=10,iy+=10 )
		{
			try {
					Thread.sleep(500);
				
				
				Location loc = new Location(ix, iy);
				Time t = new Time(System.currentTimeMillis());
				InfoItem locationItem = new InfoItem();
				locationItem.setInfoType(Location.LOCATION_ID);
				locationItem.setExplainInfo("user change location!!");
				
				locationItem.setInferenceTime(t);
				locationItem.setInfoValue(loc);
				
				tripletloc.setInfoItem(locationItem);
				tripletloc.setTime(t);
				pushData(tripletloc);
	
				if (ix==100)
				{
					Thread.sleep(3000);
				}
			}
			catch (InterruptedException e) 
			{
				System.out.println("shit ... we have a problem!!\nerr:" +e.getMessage());
			}
				
		}
		
		
		
	}

	
	
}
