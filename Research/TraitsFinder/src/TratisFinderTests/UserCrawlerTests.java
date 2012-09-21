package TratisFinderTests;

import static org.junit.Assert.*;

import org.junit.Test;

import Core.CommonDef;
import Core.UserCrawler;
import Core.Interfaces.ICrawler;
import Elements.Interfaces.IElement;

public class UserCrawlerTests {

	
	private final static String  SITE_PATH = CommonDef.PINTERSET_URL;
	private final String[] TESTS_USERS ={
			"ivashka13",
			"mrspepin",
			"janetswartz",
			"micheledegio",
			"ceciliamoden"
			};
	
	@Test
	public void testCrawl() 
	{
		
	}

	@Test
	public void testCrawlString() 
	{
		for(String user : TESTS_USERS)
		{
			
			ICrawler userCrawler = new UserCrawler(user);
			IElement element = userCrawler.Crawl();
			assertTrue(element != null);
			
		}
	}

	@Test
	public void testSaveItem() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateResultsPool() {
		fail("Not yet implemented");
	}

}
