package TratisFinderTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Core.Serialization.ESerializerType;
import Core.Serialization.SerializerFactory;
import Elements.SubjectElement;
import Elements.UserElement;

public class UserElementsLinkTest {

	
	UserElement user1 ;
	UserElement user2;
	String USER_LINK_GRAPH_TEST = "TestUserLink";
	
	@Before
	public void setUp() throws Exception
	{
		
		SubjectElement sbElmShared1 = new SubjectElement("Pizza");
		SubjectElement sbElmShared2 = new SubjectElement("cars");
		user1 = new UserElement("ben");
		user2 = new UserElement("joe");
		user1.AddElement(sbElmShared1);
		user1.AddElement(sbElmShared2);
		user1.AddElement(new SubjectElement("race"));
		user1.AddElement(new SubjectElement("java"));
		user2.AddElement(sbElmShared2);
		user2.AddElement(sbElmShared1);
		user2.AddElement(new SubjectElement("biff"));
		user1.SetSerializer(SerializerFactory.GetInstance().GetSerializer(ESerializerType.eNeo4J, user1, USER_LINK_GRAPH_TEST));
		user2.SetSerializer(SerializerFactory.GetInstance().GetSerializer(ESerializerType.eNeo4J, user2, USER_LINK_GRAPH_TEST));
		
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSerialize() {
		fail("Not yet implemented");
	}
	/*
	@Test
	public void testLink() {
		fail("Not yet implemented");
	}

	@Test
	public void testUserElement() {
		fail("Not yet implemented");
	}
	

	@Test
	public void testSetSerializer() 
	{
		
		
	}
	*/

}
