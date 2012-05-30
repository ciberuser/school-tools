package Services;

import Core.BaseCFinder;

public class  AbstractFactory extends BaseCFinder
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


