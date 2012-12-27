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
		CrawledCount =0;
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
	
	
		
	public CrawlerRunner[] ExcuteCrawler(IElement headElement,long maxExcution)
	{
		long leftUserToCrawl = QueueCrawlinTargets.GetInstance().NumbertOfTargets();
		WriteLineToLog("NumbertOfTargets="+leftUserToCrawl, ELogLevel.INFORMATION);
		long userCrawled = 0;
		while (leftUserToCrawl > 0 && userCrawled <maxExcution)
		{
			if (CrawlTopUserTarget(headElement)!=null)
			{
				if (userCrawled % 50 == 0)
				{
					
					WriteToConsole("crawling proceed with " + userCrawled + " users !!");
					WriteToConsole("queue exist with " + QueueCrawlinTargets.GetInstance().NumbertOfTargets() +" users are waiting to crawl...");
				}
				WriteLineToLog("count ="+userCrawled,ELogLevel.INFORMATION);
				if (CommonDef.SET_GRAPH && userCrawled %100 == 0)
				{
					String msg = String.format("graph updated with %d users",userCrawled);
					WriteLineToLog(msg,ELogLevel.INFORMATION);
					WriteToConsole(msg);
				}
				userCrawled++; 
			}
			
		}
		String msg = (userCrawled == maxExcution )? String.format("crawler end collecting %d users ", userCrawled) :
			(leftUserToCrawl == 0) ? "crawler don't have users to crawled , need to restart crawling" : "stop from unknow reason ";
		WriteToConsole(msg);
		WriteLineToLog(msg,ELogLevel.WARNING);
		return null;
	}
	

	public CrawlerRunner CrawlTopUserTarget(IElement headElement)
	{
		CrawlerRunner runner = null;
			
	//	WriteLineToLog("activeCount= "+CrawlerRunner.activeCount(),ELogLevel.INFORMATION);
		if (m_maxThread == 1)
		{
			String userName =  QueueCrawlinTargets.GetInstance().GetNextTarget();
			if (userName !="" && userName != null )	//TODO::why this again...
			{
				IElement userElm = new UserCrawler(userName).Crawl(m_depthbehavior.get(ECrawlingType.User));
				if (userElm != null)
				{
					if (CommonDef.SET_GRAPH) userElm.Serialize();
					headElement.AddElement(userElm);
					return runner;
				}
				WriteLineToLog("failed to create user element",ELogLevel.ERROR);
			}
			return runner;
		}
		
		if(CrawlerRunner.activeCount() <= m_maxThread)
		{
			String userName =  QueueCrawlinTargets.GetInstance().GetNextTarget();
			if (userName!=""&& userName!=null)
			{
				WriteLineToLog("users left to crawled=" + QueueCrawlinTargets.GetInstance().NumbertOfTargets(), ELogLevel.INFORMATION);
				runner = new CrawlerRunner(new UserCrawler(userName), m_depthbehavior.get(ECrawlingType.User),new String(userName));
				runner.SetHeadElement(headElement);
				String runMsg ="execute Runner for user:"+userName;
				WriteLineToLog(runMsg, ELogLevel.INFORMATION);
				WriteToConsole(runMsg);
				CrawledCount++;
				runner.start();
			}
				
		}
		return runner;
	}
	
	
	
	
}
