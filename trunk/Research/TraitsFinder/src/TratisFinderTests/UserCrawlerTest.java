package TratisFinderTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Core.UserCrawler;
import Core.Interfaces.ICrawler;
import Elements.Interfaces.IElement;

public class UserCrawlerTest {

	
	private final String[] TESTS_USERS ={
			"ivashka13",
			"mrspepin",
			"janetswartz",
			"micheledegio",
			"ceciliamoden"
			};
	
	
	@Before
	public void setUp() throws Exception 
	{
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCrawl() {
		fail("Not yet implemented");
	}

	@Test
	public void testCrawlString()
	{
		for(String user : TESTS_USERS)
		{
			
			ICrawler userCrawler = new UserCrawler(user);
			assertTrue(userCrawler.Crawl() != null);
			
		}
	}

}
