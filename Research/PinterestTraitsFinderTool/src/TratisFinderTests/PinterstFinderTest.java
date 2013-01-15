package TratisFinderTests;

import static org.junit.Assert.*;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Core.CoreContext;
import Core.PinterestContext;
import Core.Crawlers.PintersetCrawler;
import Core.Interfaces.*;
import Services.FileServices;



public class PinterstFinderTest extends test {

	ICrawler m_pintersetScouter ;
	final static String PINTERS_FILE ="pinterrest_Main.xml"; 
	final static String PINTERST_TESTS = "PintersTests";
	private boolean m_runCrawler;
	
	@Before
	public void setUp() throws Exception 
	{
		super.setUp();
		m_pintersetScouter = new PintersetCrawler();
		FileServices.DeleteFile(PINTERST_TESTS, PinterestContext.USERS_FOLDER_POOL_PATH);
		if (!FileServices.PathExist(CoreContext.ROOT_DATA_FOLDER))
		{
			FileServices.CreateFolder(getClass().getName(), CoreContext.ROOT_DATA_FOLDER);
		}
	}

	@After
	public void tearDown() throws Exception
	{
		FileServices.DeleteFile(PINTERST_TESTS, PinterestContext.USERS_FOLDER_POOL_PATH);
	}

	@Test
	public void testCrawl() 
	{
		assertTrue(m_pintersetScouter.Crawl(true)!=null);
		
		m_runCrawler = true;
		
	}

	@Test
	public void testCrawlString()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testSaveItem() 
	{
		if (m_runCrawler == false)
		{
			assertTrue(m_pintersetScouter.Crawl(true) != null);
		}
		assertTrue(FileServices.PathExist(CoreContext.ROOT_DATA_FOLDER + PinterestContext.PINTERSET_XML));
	}

	
	
	
}
