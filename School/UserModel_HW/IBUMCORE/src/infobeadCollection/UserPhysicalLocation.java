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
		//this.PrintMsg("user go location x=" +x +" y= " +y);
		m_x_location =x;
		m_y_location =y;
	}
	
	
	
	private void GoingForwad_120()
	{
		for (int ix =0, iy=0; ix< 120 ; ix+=10,iy+=10 )
		{
			try {
					Thread.sleep(500);
					GoTo(ix, iy);
					SendLocationTreplet();
			}
			catch (Exception e){}
		}
	}
	
	private void GoingBack_120()
	{
		for (int ix =120, iy=120; ix>0 ; ix-=10,iy-=10 )
		{
			try {
					Thread.sleep(500);
					GoTo(ix, iy);
					SendLocationTreplet();
			}
			catch (Exception e){}
		}
	}
	
	
	void SendLocationTreplet()
	{
		Triplet tripletloc = new Triplet(Location.TRIPLET_ID);
		Location loc = new Location(m_x_location, m_y_location);
		Time t = new Time(System.currentTimeMillis());
		InfoItem locationItem = new InfoItem();
		locationItem.setInfoType(Location.LOCATION_ID);
		locationItem.setExplainInfo("user change location!!");
		
		locationItem.setInferenceTime(t);
		locationItem.setInfoValue(loc);
			
		tripletloc.setInfoItem(locationItem);
		tripletloc.setTime(t);
		pushData(tripletloc);
	
		if (m_x_location==100 && m_x_location==100)
		{
			try
			{
				Thread.sleep(3000);
			} catch (InterruptedException e) {	e.printStackTrace();}
		}
	}
	
	
	
	
	//if location in map  
	private int m_x_location;
	private int m_y_location;
	@Override
	public void run() 
	{
		
		for (int i=0 ; i<30 ; ++i)
		{
			
			if (i%2==0)
			{
				GoingForwad_120();
			}
			else
			{
				GoingBack_120();
			}
					
			
		}
			
				
		
		}
		
		
		
	}

	
	

