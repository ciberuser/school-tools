package Core;

public abstract class BaseCFinder 
{
	
	
	public void SetClassName(String name)
	{
		m_Name = name;
	}
	
	public BaseCFinder(String name)
	{
		m_Name = name;
	}
	
	public BaseCFinder(){}
	
	protected static String m_Name = ""; 
}
