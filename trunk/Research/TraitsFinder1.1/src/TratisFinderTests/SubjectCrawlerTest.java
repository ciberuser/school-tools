package TratisFinderTests;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import java.util.Iterator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Core.CommonDef;
import Core.Crawlers.SubjectsCrawler;
import Core.Interfaces.ICrawler;
import Elements.IElement;
import Services.FileServices;

public class SubjectCrawlerTest {

	//  user ,subject
	Map<String,String> m_testSubject = new HashMap<String, String>();
		
	
	@Before
	public void setUp() throws Exception
	{
		
		if (!FileServices.PathExist(CommonDef.ROOT_DATA_FOLDER)) FileServices.CreateFolder(this.getClass().getName(), CommonDef.ROOT_DATA_FOLDER);
		if (!FileServices.PathExist(CommonDef.USERS_FOLDER_POOL_PATH)) FileServices.CreateFolder(this.getClass().getName(), CommonDef.USERS_FOLDER_POOL_PATH);
		
		m_testSubject.put("fashionhut", "crazy-cute-cartilage-earrings");
		m_testSubject.put("teganco", "pretty-just-pretty");
		m_testSubject.put("brookemckee","accessorize");
		m_testSubject.put("brookemckee", "kitchen-things");
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testCrawler()
	{
		Iterator<Entry<String, String>> it =    m_testSubject.entrySet().iterator();
		while( it.hasNext())
		{
			Map.Entry<String, String> userSubjectPairs = (Map.Entry<String, String>) it.next();
			
			ICrawler crawler = new SubjectsCrawler(userSubjectPairs.getKey(),userSubjectPairs.getValue());
			IElement elem = crawler.Crawl() ;
			assertTrue(elem != null);
			assertTrue(elem.GetName() == userSubjectPairs.getValue());
						
		}
		
	}

}
