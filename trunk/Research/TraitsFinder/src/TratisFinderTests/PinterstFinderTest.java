package TratisFinderTests;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Core.CommonDef;
import Core.PintersetScouter;
import Core.WgetCollector;
import Interfaces.ICollector;
import Interfaces.IScouter;
import Services.FileServices;
import Services.Logger;


public class PinterstFinderTest {

	ICollector m_mainPageCollector;
	IScouter m_pintersetScouter ;
	final static String PINTERS_FILE ="pinterrest_Main.xml"; 
	final static String PINTERST_TESTS = "PintersTests";
	
	
	@Before
	public void setUp() throws Exception 
	{
		m_mainPageCollector = new WgetCollector();
		m_pintersetScouter = new PintersetScouter();
		FileServices.Delete(PINTERST_TESTS, CommonDef.USERS_FOLDER_POOL_PATH);
		if (!FileServices.PathExist(CommonDef.ROOT_DATA_FOLDER))
		{
			FileServices.CreateFolder(getClass().getName(), CommonDef.ROOT_DATA_FOLDER);
		}
	}

	@After
	public void tearDown() throws Exception
	{
		FileServices.Delete(PINTERST_TESTS, CommonDef.USERS_FOLDER_POOL_PATH);
	}

	@Test
	public void testFind() 
	{
		fail("Not yet implemented");
	}

	@Test
	public void testFindString()
	{
		assertTrue(m_mainPageCollector.SaveDataFile(PINTERS_FILE, CommonDef.PINTERSET_URL+ "all/"));
		assertTrue(m_pintersetScouter.Scout(PINTERS_FILE)!=null);
		
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
