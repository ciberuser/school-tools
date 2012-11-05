package Core;

import java.awt.image.CropImageFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import Core.Crawlers.UserCrawler;
import Core.Interfaces.ICrawlerProcessor;

import Elements.IElement;
import Services.Log.ELogLevel;

public class CrawlerProccessor extends CommonCBase implements ICrawlerProcessor
{
	private final String  LOCK_NAME= "RunnerNum"; 
	private static CrawlerProccessor m_CrawlerProcessor;
	
	public static int CrawledCount; 
	final static int MAX_THREAD = 4;
	private int m_maxThread  = MAX_THREAD;
	public Object MasterLock;

	private CrawlerRunner[] m_crawlersRunner = null;
	private String[] m_Locks ;
	
	public String[] getLocks() {
		return m_Locks;
	}
	

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
		
		m_crawlersRunner = new CrawlerRunner[m_maxThread];
		m_Locks = new String[m_maxThread];
		for(int i =0; i< m_maxThread ; i++)
		{
			m_Locks[i] = new String(LOCK_NAME+i);
			m_crawlersRunner[i] = new CrawlerRunner(m_Locks[i]);
		}
		
		
		
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
	
	private int FindAvailableRunner()
	{
		for(int i=0 ; i <m_maxThread ; i++)
		{
			if (!m_crawlersRunner[i].isAlive())
				return i;
		}
		return CommonDef.NOT_EXIST;
	}
		
	public CrawlerRunner[] ExcuteCrawler(IElement headElement,int maxExcution)
	{
		long leftUserToCrawl =UsersCrawlingTargets.GetInstance().NumbertOfTargets();
		WriteLineToLog("NumbertOfTargets="+leftUserToCrawl, ELogLevel.INFORMATION);
		int count = 0;
		//List<CrawlerRunner>  runners = new ArrayList<CrawlerRunner>();
		while (leftUserToCrawl > 0 && count <maxExcution)
		{
			WriteToLog("number of user are left to crawl = "+leftUserToCrawl, ELogLevel.INFORMATION);
			CrawlTopUserTarget(headElement);
			count++; 
		}
		return null;
		//return runners.toArray(new CrawlerRunner[0]);
	}
	
	//TODO:: need to change it!!!
	public CrawlerRunner CrawlTopUserTarget(IElement headElement)
	{
		CrawlerRunner runner = null;
		String userName =  UsersCrawlingTargets.GetInstance().GetNextTarget();
		if (userName!="")
		{
			int runnerIndex = FindAvailableRunner();
			if (runnerIndex!=CommonDef.NOT_EXIST)
			{
				 runner =  m_crawlersRunner[runnerIndex];
				runner.setCrawler(new UserCrawler(userName));
				runner.setRecursive(m_depthbehavior.get(ECrawlingType.User));
				runner.SetHeadElement(headElement);
				WriteLineToLog("excute Runner_" +runnerIndex, ELogLevel.INFORMATION);
				CrawledCount++;
				runner.start();
						
				
			}
			else
			{
				WriteLineToLog("no available runner is found..", ELogLevel.WARNING);
				UsersCrawlingTargets.GetInstance().AddTarget(userName);
			}
			
		
		}
		return runner;
	}
	
	
	
	
}
