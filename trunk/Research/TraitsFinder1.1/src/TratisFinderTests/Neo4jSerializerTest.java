package TratisFinderTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Core.CommonCBase;
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
import Services.Log.ELogLevel;
import Services.Neo4J.Neo4JActivation;
import Services.Neo4J.Neo4JServices;

public class Neo4jSerializerTest extends CommonCBase{

	private final static String PROP_NAME = "car";
	private final static String PROP_DES  = "bmw m3";
	
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
		
		m_element = new SubjectElement(PROP_NAME);
		m_element.AddProperty(EProperty.description.toString(),PROP_NAME);
				
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
		assertTrue(Neo4JActivation.IsActive());
		Neo4JServices ns = new Neo4JServices(Neo4JActivation.GetGraphDatabaseService());
		m_serializer.Save();
		long elementId = ns.GetNodeElementId(m_element);
		WriteLineToLog("the element id is : "+elementId , ELogLevel.INFORMATION);
		assertTrue(elementId != CommonDef.NOT_EXIST_IN_DB);
		assertTrue(ns.GetNodeProperty(m_element, EProperty.name.toString()) == PROP_NAME );
		assertTrue(ns.GetNodeProperty(m_element, EProperty.description.toString()) == PROP_DES );	
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
