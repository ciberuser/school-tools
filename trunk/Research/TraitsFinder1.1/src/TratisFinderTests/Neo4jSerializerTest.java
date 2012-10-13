package TratisFinderTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Core.CommonDef;
import Core.Serialization.ESerializerType;
import Core.Serialization.IElementSerializer;
import Core.Serialization.Neo4JSerializer;
import Core.Serialization.SerializerFactory;
import Elements.IElement;
import Elements.EProperty;
import Elements.EnumElement;
import Elements.SubjectElement;
import Services.FileServices;

public class Neo4jSerializerTest {

	private String m_DBdir = CommonDef.ROOT_DATA_FOLDER+"TestDBRoot/"; 
	IElement m_element ;
	IElementSerializer m_serializer;
	
	@Before
	public void setUp() throws Exception
	{
		InitElement();
		m_serializer =  SerializerFactory.GetInstance().GetSerializer(ESerializerType.eNeo4J, m_element, m_DBdir);
	}

	
	void InitElement()
	{
		m_element = new SubjectElement("car");
		m_element.AddProperty(EProperty.description.toString(),"bmw m3");
				
	}
	
	@After
	public void tearDown() throws Exception 
	{
		m_serializer.Close();
	}

	@Test
	public void testNeo4JSerializer()
	{
		assertTrue(m_serializer!=null);
		assertTrue(FileServices.PathExist(m_DBdir));
	}

	@Test
	public void testSave()
	{
		m_serializer.Save();
	}

	@Test
	public void testSaveIElement() {
		fail("Not yet implemented");
	}

	@Test
	public void testLoad() {
		fail("Not yet implemented");
	}

}
