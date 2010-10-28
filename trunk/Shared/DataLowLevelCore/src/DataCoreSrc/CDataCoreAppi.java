package DataCoreSrc;


public class CDataCoreAppi
{
	static CDataCoreAppi m_instance;
	
	public static CDataCoreAppi GetInstance()
	{
		if (m_instance == null ) return (new CDataCoreAppi());
		return m_instance;
	}
	
	private CDataCoreAppi()
	{
		
	}
	
}
