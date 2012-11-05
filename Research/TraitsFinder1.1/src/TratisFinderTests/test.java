package TratisFinderTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Core.CommonCBase;
import Core.CommonDef;
import Services.FileServices;

public class test extends CommonCBase {

	@Before
	public void setUp() throws Exception
	{
		if (!FileServices.PathExist(CommonDef.ROOT_DATA_FOLDER))
		{
			FileServices.CreateFolder(this.getClass().getName(), CommonDef.ROOT_DATA_FOLDER);
		}
		if (!FileServices.PathExist(CommonDef.USERS_FOLDER_POOL_PATH)) FileServices.CreateFolder(GetClassName(),CommonDef.USERS_FOLDER_POOL_PATH);
		
	}

	@After
	public void tearDown() throws Exception {
	}
  /*
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	*/

}
