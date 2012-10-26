package TratisFinderTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Core.CommonDef;
import Services.FileServices;
import Services.ICollector;
import Services.WgetCollector;

public class WgetCollectorTests {

	ICollector m_wgetCollector;
	@Before
	public void setUp() throws Exception 
	{
		m_wgetCollector = new WgetCollector();
		if (!FileServices.PathExist(CommonDef.ROOT_DATA_FOLDER )) 
			FileServices.CreateFolder(this.getClass().getName(), CommonDef.ROOT_DATA_FOLDER );
	}
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSaveDataFile() {
		assertTrue(m_wgetCollector.SaveDataFile(CommonDef.ROOT_DATA_FOLDER +"testYnet.xml", "http://www.ynet.co.il"));
	}
	
	@Test
	public void TestPinterrestSiteGet()
	{
		assertTrue(m_wgetCollector.SaveDataFile(CommonDef.ROOT_DATA_FOLDER+ "pinterrest.xml", CommonDef.PINTERSET_URL+ "all/"));
	}

}
