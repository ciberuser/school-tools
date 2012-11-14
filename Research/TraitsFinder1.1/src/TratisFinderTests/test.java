package TratisFinderTests;



import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import Core.CommonCBase;
import Core.CommonDef;
import Services.FileServices;
import Services.Log.ELogLevel;

public class test extends CommonCBase {

	@Before
	public void setUp() throws Exception
	{
		CommonDef.LOGGER_LEVEL =ELogLevel.INFORMATION;
		if (!FileServices.PathExist(CommonDef.ROOT_DATA_FOLDER))
		{
			FileServices.CreateFolder(this.getClass().getName(), CommonDef.ROOT_DATA_FOLDER);
		}
		if (!FileServices.PathExist(CommonDef.USERS_FOLDER_POOL_PATH)) FileServices.CreateFolder(GetClassName(),CommonDef.USERS_FOLDER_POOL_PATH);
		
	}

	@After
	public void tearDown() throws Exception {
	}
 
	@Test
	public void test() 
	{
		String s ="/marenbaarlid/been-there/";
		String s1[] = s.split("/");
		System.out.println(s1[2]);
		assertTrue(s1[2].compareTo("been-there")==0);
		
	}
	

}
