package Core;


import java.util.HashMap;

import java.util.Map;

import Core.Crawlers.UserCrawler;
import Core.Interfaces.ICrawlerProcessor;

import Elements.IElement;
import Services.Log.ELogLevel;

public class CrawlerProccessor extends CommonCBase implements ICrawlerProcessor
{
	private static CrawlerProccessor m_CrawlerProcessor;
	
	public static int CrawledCount; 
	private int m_maxThread  = CommonDef.MAX_RUNNERS;

	
	IElement m_headElement ;
	Map <ECrawlingType,Boolean> m_depthbehavior ;
	
	private CrawlerProccessor()
	{
		Init();
		
	}

	private void Init()
	{
		m_depthbehavior = new HashMap<ECrawlingType, Boolean>();
		m_depthbehavior.put(ECrawlingType.Main, true);
		m_depthbehavior.put(ECrawlingType.User, true);
		m_depthbehavior.put(ECrawlingType.Subject, false);
		m_depthbehavior.put(ECrawlingType.Item,false);
		
	}
	
	@Override
	public boolean LoadProcessor(String processorFilePath) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean GetDepthCrawling(String className)  //TODO upgrage this function !!!
	{
		if (className.contains(ECrawlingType.Main.toString())) return m_depthbehavior.get(ECrawlingType.Main);
		if (className.contains(ECrawlingType.User.toString())) return m_depthbehavior.get(ECrawlingType.User);
		if (className.contains(ECrawlingType.Subject.toString())) return m_depthbehavior.get(ECrawlingType.Subject);
		if (className.contains(ECrawlingType.Item.toString())) return m_depthbehavior.get(ECrawlingType.Item);
		return false;
	}
	
	public static CrawlerProccessor GetInstance()
	{
		if (m_CrawlerProcessor == null)
		{
			m_CrawlerProcessor = new CrawlerProccessor();
		}
		return m_CrawlerProcessor;
	}

	@Override
	public boolean GetDepthCrawling(ECrawlingType crawlType) {
		
		return m_depthbehavior.get(crawlType);
	}
	
	
		
	public CrawlerRunner[] ExcuteCrawler(IElement headElement,int maxExcution)
	{
		long leftUserToCrawl = UsersCrawlingTargets.GetInstance().NumbertOfTargets();
		WriteLineToLog("NumbertOfTargets="+leftUserToCrawl, ELogLevel.INFORMATION);
		int count = 0;
		
		while (leftUserToCrawl > 0 && count <maxExcution)
		{
			if (CrawlTopUserTarget(headElement)!=null)
			{
				WriteLineToLog("count ="+count,ELogLevel.INFORMATION);
				count++; 
			}
			
		}
		return null;
		
	}
	
	//TODO:: need to change it!!!
	public CrawlerRunner CrawlTopUserTarget(IElement headElement)
	{
		CrawlerRunner runner = null;
		if(CrawlerRunner.activeCount() <= m_maxThread)
		{
			String userName =  UsersCrawlingTargets.GetInstance().GetNextTarget();
			if (userName!="")
			{
				runner = new CrawlerRunner(new UserCrawler(userName), m_depthbehavior.get(ECrawlingType.User),new String(userName));
				runner.SetHeadElement(headElement);
				WriteLineToLog("excute Runner for user:"+userName, ELogLevel.INFORMATION);
				CrawledCount++;
				runner.start();
			}
				
		}
		return runner;
	}
	
	
	
	
}
