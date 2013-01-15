package TratisFinderTests;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import java.util.Iterator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Core.CommonCBase;
import Core.CoreContext;
import Core.PinterestContext;
import Core.Crawlers.SubjectsCrawler;
import Core.Interfaces.ICrawler;
import Elements.IElement;
import Services.FileServices;


public class SubjectCrawlerTest extends test{

	//  user ,subject
	Map<String,String> m_testSubject = new HashMap<String, String>();
		
	
	@Before
	public void setUp() throws Exception
	{
		
		if (!FileServices.PathExist(CoreContext.ROOT_DATA_FOLDER)) FileServices.CreateFolder(this.getClass().getName(), CoreContext.ROOT_DATA_FOLDER);
		if (!FileServices.PathExist(PinterestContext.USERS_FOLDER_POOL_PATH)) FileServices.CreateFolder(this.getClass().getName(), PinterestContext.USERS_FOLDER_POOL_PATH);
		
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
			String subjectSaveName = PinterestContext.AlignSubjectName(userSubjectPairs.getValue());
			ICrawler crawler = new SubjectsCrawler(userSubjectPairs.getKey(),userSubjectPairs.getValue());
			IElement elem = crawler.Crawl(false) ;
			assertTrue(elem != null);
			
			//WriteLineToLog("subject elem name= " +elem.GetName()+ " check :"+userSubjectPairs.getValue().replace("-", "_"), ELogLevel.INFORMATION);
			assertTrue(elem.GetName().compareTo(subjectSaveName)==0); // TODO :: add generic way....
			assertTrue(FileServices.PathExist(PinterestContext.USERS_FOLDER_POOL_PATH +"//"+ userSubjectPairs.getKey()+"//"+subjectSaveName ));
			assertTrue(FileServices.PathExist(PinterestContext.USERS_FOLDER_POOL_PATH +"//"+ userSubjectPairs.getKey()+"//"+subjectSaveName +"//"+PinterestContext.FOLLOWER_FILE_NAME));
		}
		
	}

	@Test
	public void testSave()
	{
		String userKey = "fashionhut";
		String Subject2 = "books-worth-reading";	
		ICrawler crawler = new SubjectsCrawler(userKey,m_testSubject.get(userKey));
		
		IElement subElem = crawler.Crawl(false) ;
		IElement subElem2 =  new SubjectsCrawler(userKey, Subject2).Crawl(false);
		assertTrue(subElem!=null & subElem2 != null);
		subElem.Serialize();
		subElem2.Serialize();
		subElem.Link(subElem2);
	}
	
}
