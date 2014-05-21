package infobeadCollection;

public class Location 
{
	public Location(int x,int y)
	{
		m_x=x;
		m_y=y;
	}
	
	public Location(){};
	public static final String LOCATION_ID="location";
	public static final String TRIPLET_ID ="location_triplet";
	
	@Override
	public String toString()
	{
		return " x=" +m_x +" y=" +m_y+" ";
	};
	
	private int m_x;
	public int getM_x() {
		
		return m_x;
	}
	public void setM_x(int m_x) {
		this.m_x = m_x;
	}
	public int getM_y() {
		return m_y;
	}
	public void setM_y(int m_y) {
		this.m_y = m_y;
	}
	private int m_y;
}
