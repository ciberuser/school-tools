package Core.Crawlers;


import Services.Log.ELogLevel;

import Core.ACrawlerFactory;
import Core.ECrawlingType;
import Core.EPinterestCrawlingType;
import Core.Interfaces.ICrawler;

public class PinterestCrawlerFactory extends ACrawlerFactory 
{
	
	private PinterestCrawlerFactory()
	{
		
	}
		
		
	public static PinterestCrawlerFactory GetInstance()
	{
		if (m_instance == null ) m_instance = new PinterestCrawlerFactory();
		return (PinterestCrawlerFactory) m_instance;
	}
	
	public ICrawler GenerateCrawler(ECrawlingType type , Object[] parms)
	{
		try
		{
			EPinterestCrawlingType pinterestType =(EPinterestCrawlingType) type;
			String failedPrefix = "failed to generate"; 
			switch (pinterestType)
			{
				case User:
					
					if (parms[0] == null) throw new Exception( failedPrefix+" UserCrawler : userName parameter is null ");
					String userName = (String) ((parms[0] != null) ? parms[0] : null);
					return new UserCrawler(userName);
				case Subject:
					if (parms[0] == null || parms[1] == null || parms[2] == null) throw new Exception(failedPrefix + " subjectCrawler one of the paramter is null" );
					return new SubjectsCrawler((String)parms[0],(String) parms[1], (String)parms[2]);	
				case Main:
					return new PintersetCrawler();
				case Item :
				throw new Exception("no support!!!");
			}
		}
		catch(Exception ex )
		{
			String msg ="Error : failed to create crawler. msg="+ ex.getMessage();
			WriteLineToLog(msg,ELogLevel.ERROR);
			WriteToConsole(msg);
		}
		return null;
	}
}
