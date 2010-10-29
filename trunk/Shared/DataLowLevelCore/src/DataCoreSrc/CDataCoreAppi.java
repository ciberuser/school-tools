package DataCoreSrc;
import java.util.HashMap;
import Classes.*;


public class CDataCoreAppi
{
	static CDataCoreAppi m_instance;
	
	HashMap <String,DataBaseType> m_SQLTarget;
	
	public static CDataCoreAppi GetInstance()
	{
		if (m_instance == null ) return (new CDataCoreAppi());
		return m_instance;
	}
	
	private void InitSQLTarget()
	{
		m_SQLTarget = new HashMap<String,DataBaseType>();
	}
	
	public DataBaseType SetSQLTarget(EDataBaseType databaseType,String databaseURL) throws Exception
	{
		switch (databaseType)
		{
			case eMYSQL:
			DataBaseType temp = new DataBaseType(new CmySQLConnection(databaseURL),databaseType,databaseURL);
			m_SQLTarget.put(databaseURL,temp);
			return GetSQLTarget(databaseURL);
			default:
				throw (new Exception("not supported yet"));
		}
		
	}
	
	public DataBaseType GetSQLTarget(String databaseUrl) // need to improve
	{
		return m_SQLTarget.get(databaseUrl);
	}
	
	
	private CDataCoreAppi()
	{
		InitSQLTarget();
	}
	
}
