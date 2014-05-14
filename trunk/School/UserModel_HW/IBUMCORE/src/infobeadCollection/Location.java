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
	
	int m_x;
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
	int m_y;
}
