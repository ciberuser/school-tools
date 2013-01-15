package Core;

import java.util.HashMap;

import Core.Crawlers.PinterestCrawlerFactory;
import Core.Interfaces.ICrawlerProcessor;

public class PinterestCrawlersProcessor extends ACrawlerProcessor implements ICrawlerProcessor {

	
	
	public PinterestCrawlersProcessor()
	{
		super();
		m_crawlerFactory = PinterestCrawlerFactory.GetInstance();
		m_depthbehavior = new HashMap<ECrawlingType, Boolean>();
		m_depthbehavior.put(EPinterestCrawlingType.Main, true);
		m_depthbehavior.put(EPinterestCrawlingType.User, true);
		m_depthbehavior.put(EPinterestCrawlingType.Subject, false);
		m_depthbehavior.put(EPinterestCrawlingType.Item,false);
	}
	
	public boolean GetDepthCrawling(String className)  //TODO upgrage this function !!!
	{
		
		if (className.contains(EPinterestCrawlingType.Main.toString())) return m_depthbehavior.get(EPinterestCrawlingType.Main);
		if (className.contains(EPinterestCrawlingType.User.toString())) return m_depthbehavior.get(EPinterestCrawlingType.User);
		if (className.contains(EPinterestCrawlingType.Subject.toString())) return m_depthbehavior.get(EPinterestCrawlingType.Subject);
		if (className.contains(EPinterestCrawlingType.Item.toString())) return m_depthbehavior.get(EPinterestCrawlingType.Item);
		return false;
		
	}
	
	@Override
	public boolean LoadProcessor(String processorFilePath) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean GetDepthCrawling(ECrawlingType crawlType) {
		
		return m_depthbehavior.get(crawlType);
	}

}
