package Core;

import redis.clients.jedis.Jedis;
import Core.Interfaces.ICrawlingTargets;
import Services.Log.ELogLevel;

public class UsersCrawlingTargets extends CommonCBase implements ICrawlingTargets{
	
	private		Jedis m_jedis;
	private		static UsersCrawlingTargets m_instance = null;
	public static UsersCrawlingTargets GetInstance() {
		if (m_instance==null) 
		{
			m_instance = new UsersCrawlingTargets();
		}
		return m_instance;
	}
	private		UsersCrawlingTargets() {
		m_jedis = new Jedis("localhost"); // protect against failure
	}
	@Override
	public String GetNextTarget() {
		String sNextTarget = "";
		try
		{
			sNextTarget = m_jedis.spop("UserTargets");
			WriteLineToLog("Providing next target: "  + sNextTarget, ELogLevel.INFORMATION);
		} catch(Exception e){}
		return sNextTarget;
	}

	@Override
	public boolean AddTarget(String sTarget) {
		boolean bSuccessful = false;
		try 
		{
			bSuccessful = m_jedis.sadd("UserTargets", sTarget) > 0;
			if (bSuccessful) 
			{
				WriteLineToLog("Added "  + sTarget + " to target list successfully", ELogLevel.INFORMATION);
			} else 
			{
				WriteLineToLog("Adding "  + sTarget + " to target list failed", ELogLevel.ERROR);
			}
		} catch(Exception e){}
		
		return bSuccessful;
	}

}
