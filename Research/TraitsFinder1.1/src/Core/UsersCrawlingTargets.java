package Core;

import java.io.EOFException;

import redis.clients.jedis.Jedis;
import Core.Interfaces.ICrawlingTargets;
import Services.Log.ELogLevel;

public class UsersCrawlingTargets extends CommonCBase implements ICrawlingTargets{
	
	private		Jedis m_jedis;
	private		static UsersCrawlingTargets m_instance = null;
		
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
		return m_jedis.dbSize();
	}
	
	
	@Override
	public String GetNextTarget() 
	{
		String sNextTarget = "";
		try
		{
			sNextTarget = m_jedis.spop("UserTargets");
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
			if (!m_jedis.exists(sTarget) || m_jedis.dbSize()<CommonDef.MAX_CRAWLING_USER)
			{
				bSuccessful = m_jedis.sadd("UserTargets", sTarget) > 0;
				if (bSuccessful) 
				{
					WriteLineToLog("Added "  + sTarget + " to target list successfully", ELogLevel.INFORMATION);
					
				} else 
				{
					WriteLineToLog("Adding "  + sTarget + " to target list failed", ELogLevel.ERROR);
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
