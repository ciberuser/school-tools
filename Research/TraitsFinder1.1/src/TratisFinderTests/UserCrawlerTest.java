package TratisFinderTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Core.CommonDef;
import Core.Crawlers.UserCrawler;
import Core.Interfaces.ICrawler;
import Elements.IElement;
import Services.FileServices;

public class UserCrawlerTest  extends test{

	
	boolean IsCrawled =false;
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
		super.setUp();
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
			assertTrue(crawler.Crawl(true)!=null);
		}
		IsCrawled =true;
		
	}
	
	/*
	@Test
	public void testCreateResultsPool()
	{
		if (!IsCrawled)	testCrawl();
		for(String user :TESTS_USERS)
		{
			assertTrue(FileServices.PathExist(CommonDef.USERS_FOLDER_POOL_PATH +"/" + user)); 
		}
	}
	*/
	
	@Test
	public void testSave()
	{
		
	}


}