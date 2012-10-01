package TratisFinderTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Core.CommonCFinder;
import Core.CommonDef;
import Core.UserCrawler;
import Core.Interfaces.ICrawler;
import Elements.Interfaces.IElement;
import Services.FileServices;

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
		if (FileServices.PathExist(CommonDef.USERS_FOLDER_POOL_PATH))
		{
			FileServices.DeleteFolder(getClass().getName(), CommonDef.USERS_FOLDER_POOL_PATH);
			FileServices.CreateFolder(getClass().getName(), CommonDef.USERS_FOLDER_POOL_PATH);
			
		}
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCrawl()
	{
		for (String user :TESTS_USERS)
		{
			ICrawler crawler = new UserCrawler(user);
			assertTrue(crawler.Crawl()!=null);
		}
		
	}
	
	@Test
	public void testCreateResultsPool()
	{
		testCrawl();
		for(String user :TESTS_USERS)
		{
			assertTrue(FileServices.PathExist(CommonDef.USERS_FOLDER_POOL_PATH +"/" + user)); 
		}
	}


}
