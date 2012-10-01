package TratisFinderTests;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Core.CommonDef;
import Core.PintersetCrawler;
import Core.WgetCollector;
import Core.Interfaces.*;
import Services.FileServices;
import Services.ICollector;
import Services.Logger.Logger;


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
		FileServices.DeleteFile(PINTERST_TESTS, CommonDef.USERS_FOLDER_POOL_PATH);
		if (!FileServices.PathExist(CommonDef.ROOT_DATA_FOLDER))
		{
			FileServices.CreateFolder(getClass().getName(), CommonDef.ROOT_DATA_FOLDER);
		}
	}

	@After
	public void tearDown() throws Exception
	{
		FileServices.DeleteFile(PINTERST_TESTS, CommonDef.USERS_FOLDER_POOL_PATH);
	}

	@Test
	public void testCrawl() 
	{
		assertTrue(m_pintersetScouter.Crawl()!=null);
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
			assertTrue(m_pintersetScouter.Crawl() != null);
		}
		assertTrue(FileServices.PathExist(CommonDef.ROOT_DATA_FOLDER +CommonDef.PINTERSET_XML));
	}

	@Test
	public void testCreateResultsPool() {
		fail("Not yet implemented");
	}

	
	
}
