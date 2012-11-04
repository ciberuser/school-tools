package TratisFinderTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Core.CommonDef;
import Core.Crawlers.UserCrawler;
import Core.Interfaces.ICrawler;
import Elements.EProperty;
import Elements.IElement;
import Services.FileServices;
import Services.Log.ELogLevel;

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
		
		/*if (FileServices.PathExist(CommonDef.USERS_FOLDER_POOL_PATH))
		{
			FileServices.DeleteFolder(getClass().getName(), CommonDef.USERS_FOLDER_POOL_PATH);
			FileServices.CreateFolder(getClass().getName(), CommonDef.USERS_FOLDER_POOL_PATH);
			
		}
		*/
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCrawl()
	{
		for (String user :TESTS_USERS)
		{
			IElement usersElement = null;
			ICrawler crawler = new UserCrawler(user);
			usersElement = crawler.Crawl(true);
			assertTrue(usersElement!=null);
			assertTrue(FileServices.PathExist(CommonDef.USERS_FOLDER_POOL_PATH + "/" +CommonDef.AlignUserName(user) ));
			WriteToLog("user name for element  = " +usersElement.GetProperty(EProperty.name.toString()).toString() , ELogLevel.INFORMATION);
			assertTrue (usersElement.GetProperty(EProperty.name.toString()).toString().compareTo(user) == 0);
			
			
		}
		IsCrawled =true;
		
	}
	
	
	@Test
	public void testSave()
	{
		
	}


}
