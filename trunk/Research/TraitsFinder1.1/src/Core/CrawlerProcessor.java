package Core;

import java.util.HashMap;
import java.util.Map;

import Core.Crawlers.UserCrawler;
import Core.Interfaces.ICrawler;
import Core.Interfaces.ICrawlerProcessor;
import Elements.IElement;

public class CrawlerProcessor implements ICrawlerProcessor
{

	private static CrawlerProcessor m_CrawlerProcessor;
	
	
	
	Map <ECrawlingType,Boolean> m_depthbehavior ;
	
	private CrawlerProcessor()
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
	
	public static CrawlerProcessor GetInstance()
	{
		if (m_CrawlerProcessor == null)
		{
			m_CrawlerProcessor = new CrawlerProcessor();
		}
		return m_CrawlerProcessor;
	}

	@Override
	public boolean GetDepthCrawling(ECrawlingType crawlType) {
		
		return m_depthbehavior.get(crawlType);
	}

	public IElement CrawlTopUserTarget()
	{
		IElement userElement = null;
		String userName =  QueueCrawlinTargets.GetInstance().GetNextTarget();
		if (userName!="" && userName!=null)
		{
			ICrawler userCrawler = new UserCrawler(userName);
			userElement =  userCrawler.Crawl(m_depthbehavior.get(ECrawlingType.User));
		}
		return userElement;
	}
	
	
	
	
}
