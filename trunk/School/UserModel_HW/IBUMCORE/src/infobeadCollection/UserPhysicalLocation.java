package infobeadCollection;

import genericInfoBead.InfoBead;
import genericInfoBead.Triplet;

public class UserPhysicalLocation extends InfoBead implements Runnable{

	
	
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
	public void run() {
		// TODO Auto-generated method stub
		
	}

	
	
}
