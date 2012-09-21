package Services;

import Core.CommonCFinder;

public class  AbstractFactory extends CommonCFinder
{
	private AbstractFactory m_factory;
	
	protected AbstractFactory()
	{
		
	}
	
	public  AbstractFactory GetInstance()
	{
		if (m_factory == null)
		{
			m_factory = new AbstractFactory();
		}
		return m_factory;
		
	}
	
	


}


