package TratisFinderTests;

import static org.junit.Assert.*;

import org.junit.Test;

import Core.UsersCrawlingTargets;

public class CrawlerTargetsTest extends test {
	
	
	@Test
	public void CrawlerTargets() {
		UsersCrawlingTargets pUsersCrawlingTargets = UsersCrawlingTargets.GetInstance();
		pUsersCrawlingTargets.AddTarget("Amit");
		pUsersCrawlingTargets.AddTarget("Oz");
		pUsersCrawlingTargets.AddTarget("Tsvika");
		String NextTarget = pUsersCrawlingTargets.GetNextTarget();
		assertTrue(NextTarget.compareTo("Amit")==0 || NextTarget.compareTo("Oz")==0 || NextTarget.compareTo("Tsvika")==0);
		NextTarget = pUsersCrawlingTargets.GetNextTarget();
		assertTrue(NextTarget.compareTo("Amit")==0 || NextTarget.compareTo("Oz")==0 || NextTarget.compareTo("Tsvika")==0);
		NextTarget = pUsersCrawlingTargets.GetNextTarget();
		assertTrue(NextTarget.compareTo("Amit")==0 || NextTarget.compareTo("Oz")==0 || NextTarget.compareTo("Tsvika")==0);
		NextTarget = pUsersCrawlingTargets.GetNextTarget();
		assertTrue(NextTarget==null);
	}
}
