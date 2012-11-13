package Core;


import redis.clients.jedis.Jedis;
import Core.Interfaces.ICrawlingTargets;
import Services.Log.ELogLevel;

public class UsersCrawlingTargets extends CommonCBase implements ICrawlingTargets{
	
	private		Jedis m_jedis;
	private		static UsersCrawlingTargets m_instance = null;
	private 	final static String KEY_NAME = "UserTargets";
		
	enum E_OS {Windows,Linux,Mac}
	
	E_OS m_os ;
	
	public static UsersCrawlingTargets GetInstance() {
		
		if (m_instance==null) 
		{
			m_instance = new UsersCrawlingTargets();
		}
		return m_instance;
	}
	
	boolean ActivateRedis(E_OS os) //need to finish this action 
	{
		if (os == E_OS.Windows)
		{
		
		}
		return true ;
		
	}
	
	private		UsersCrawlingTargets()
	{
		m_os = (System.getProperty("os.name").toLowerCase().indexOf("win") > 0 )? E_OS.Windows : E_OS.Linux ;
		m_jedis = new Jedis("localhost"); // protect against failure
	}
	
	public long NumbertOfTargets()
	{
		return  (m_jedis.exists(KEY_NAME))? 1: 0; //TODO::Fix this problem !!!
	}
	
	public boolean CleanTargets()
	{
		
		//TODO:: implement clean radis !!! 
		return false;
	}
	
	@Override
	public String GetNextTarget() 
	{
		String sNextTarget = "";
		try
		{
			sNextTarget = m_jedis.spop(KEY_NAME);
			WriteLineToLog("Providing next target: "  + sNextTarget, ELogLevel.INFORMATION);
		} 
		catch(Exception e)
		{
			WriteExcptionError(e.getMessage());
		}
		return sNextTarget;
	}

	@Override
	public boolean AddTarget(String sTarget) 
	{
		boolean bSuccessful = false;
		try 
		{
			if (!m_jedis.isConnected()) 
			{
				WriteLineToLog("somehow connection to server is down reconnect to server....", ELogLevel.WARNING);
				m_jedis.connect();
			}
			
			if (!m_jedis.exists(sTarget))
			{
				bSuccessful = m_jedis.sadd(KEY_NAME, sTarget) > 0;
				if (bSuccessful) 
				{
					WriteLineToLog("Added "  + sTarget + " to target list successfully", ELogLevel.INFORMATION);
				} 
			}
		} 
		catch(Exception e)
		{
			WriteExcptionError(e.getMessage());
		}
		
		return bSuccessful;
	}

	private void WriteExcptionError(String msg)
	{
		WriteLineToLog("exception accore msg="+msg, ELogLevel.ERROR);
	}
}
