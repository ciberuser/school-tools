package TratisFinderTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Core.PinterestFinder;
import Core.WgetCollector;
import Interfaces.ICollector;
import Interfaces.IFinder;

public class PinterstFinderTest {

	ICollector m_mainPageCollector;
	IFinder m_pintersetFinder ;
	final static String PINTERS_FILE ="pinterrest_Main.xml"; 
	
	
	@Before
	public void setUp() throws Exception 
	{
		m_mainPageCollector = new WgetCollector();
		m_pintersetFinder = new PinterestFinder();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFind() 
	{
		fail("Not yet implemented");
	}

	@Test
	public void testFindString()
	{
		assertTrue(m_mainPageCollector.SaveDataFile(PINTERS_FILE, "http://pinterest.com/all/"));
		assertTrue(m_pintersetFinder.Find(PINTERS_FILE)!=null);
		
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
