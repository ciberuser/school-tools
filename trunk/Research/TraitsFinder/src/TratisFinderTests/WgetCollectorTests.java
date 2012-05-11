package TratisFinderTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Core.WgetCollector;
import Interfaces.ICollector;

public class WgetCollectorTests {

	ICollector m_wgetCollector;
	@Before
	public void setUp() throws Exception 
	{
		m_wgetCollector = new WgetCollector("WgetCollector");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSaveDataFile() {
		assertTrue(m_wgetCollector.SaveDataFile("testYnet.xml", "http://www.ynet.co.il/home/0,7340,L-8,00.html"));
	}
	
	@Test
	public void TestPinterrestSiteGet()
	{
		assertTrue(m_wgetCollector.SaveDataFile("pinterrest.xml", "http://pinterest.com/all/"));
	}

}
