package TratisFinderTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Core.CrawlerProccessor;
import Core.CrawlerRunner;
import Core.ECrawlingType;
import Core.Crawlers.PintersetCrawler;
import Core.Interfaces.ICrawler;
import Core.Interfaces.ICrawlerProcessor;
import Elements.IElement;
import Services.Log.ELogLevel;

public class CrawlerProccessorTest extends test{

	private final int NUMBER_OF_THEARD =  5;
	@Before
	public void setUp() throws Exception 
	{
		CrawlerProccessor.CrawledCount = 0;
		super.setUp();		
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void testMultiThreaded()
	{
		//String[] locks = CrawlerProccessor.GetInstance().getLocks();
		ICrawler crawler  = new  PintersetCrawler();
		IElement mainElem =  crawler.Crawl(CrawlerProccessor.GetInstance().GetDepthCrawling(ECrawlingType.Main));
		assertTrue(mainElem.GetElements().size()==0);
		CrawlerRunner[] runners =  CrawlerProccessor.GetInstance().ExcuteCrawler(mainElem,NUMBER_OF_THEARD);
		
		//need to fix it ....
		
		for (int i =0 ; i< runners.length ; i++)
		{
			synchronized(runners[i].Getlock())
			{
				
					try {
						runners[i].Getlock().wait();//
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						WriteLineToLog("exception happen msg="+e.getMessage(), ELogLevel.ERROR);
					}
			}
		}
	}
	
	
	@Test
	public void testSingelThread() 
	{
		ICrawler crawler  = new  PintersetCrawler();
		IElement mainElem =  crawler.Crawl(CrawlerProccessor.GetInstance().GetDepthCrawling(ECrawlingType.Main));
		assertTrue(mainElem.GetElements().size()==0);
		CrawlerRunner runner = CrawlerProccessor.GetInstance().CrawlTopUserTarget(mainElem);
		synchronized(runner.Getlock())
		{
			try {
				runner.Getlock().wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					WriteLineToLog("exception happen msg="+e.getMessage(), ELogLevel.ERROR);
				}
		}
		assertTrue(mainElem.GetElements().size()>0);
		
	}

	
	
}
