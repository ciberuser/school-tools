package Services.Neo4J;



import javax.lang.model.element.Element;


import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.IndexHits;

import Core.CommonCBase;
import Core.CommonDef;
import Elements.IElement;
import Elements.EProperty;
import Services.Log.ELogLevel;

public class Neo4JServices extends CommonCBase 
{
	
	GraphDatabaseService m_services;
	private Index<Node> m_indexNode;
	
	public Neo4JServices(GraphDatabaseService service)
	{
		
			m_services = service;
			m_indexNode =  m_services.index().forNodes(EProperty.name.toString());
	}
	
	public long CreateNode(IElement element)
	{
		Node tempNode ;
		if (m_services ==null) {
			WriteLineToLog("no Neo4j serives",ELogLevel.ERROR);
			return 0;
		}
		Transaction tx = m_services.beginTx();
		try
        {
			String name = element.GetProperty(EProperty.name.toString()).toString();
			IndexHits<Node> hits = m_indexNode.get(EProperty.name.toString(), name);
			if (hits.size() == 0)
			{
				tempNode =  m_services.createNode(); 
				tempNode.setProperty(EProperty.name.toString(),name );
				m_indexNode.add(tempNode, EProperty.name.toString(),name );
				WriteLineToLog("node created id: "+ tempNode.getId(), ELogLevel.INFORMATION);
			}
			else
			{
				tempNode =  hits.getSingle();
				WriteLineToLog("found node id:" +tempNode.getId(), ELogLevel.INFORMATION);
			}
			tx.success();
			
        }
		catch(Exception ex)
		{
			WriteLineToLog("transaction failed:" +ex.getMessage(), ELogLevel.ERROR);
			tx.finish();
			return 0;
		}
		tx.finish();
         
		return tempNode.getId();
		
	}
	
	public long GetNodeElementId(IElement element)
	{
		Node returnNode = m_indexNode.get(EProperty.name.toString(), element.GetName()).getSingle();
		if (returnNode != null) return returnNode.getId();
		return CommonDef.NOT_EXIST_IN_DB;
	}
	
	public boolean AddRelasion(IElement elm1 ,IElement elm2, RelationshipType relType)
	{
		Node elm1Node = m_indexNode.get(EProperty.name.toString(), elm1.GetName()).getSingle();
		Node elm2Node = m_indexNode.get(EProperty.name.toString(), elm2.GetName()).getSingle();
		if (elm1Node.getId() == elm2Node.getId()) 	return false;
		Relationship rel =  elm1Node.createRelationshipTo(elm2Node,relType);
		return true;
	}
	
	public String GetNodeProperty(long NodeId , String Property)
	{
		return m_services.getNodeById(NodeId).getProperty(Property).toString();
	}
	
	public String GetNodeProperty(IElement element , String Property)
	{
		long nodeId = GetNodeElementId(element);
		if (nodeId != CommonDef.NOT_EXIST_IN_DB) return GetNodeProperty(nodeId, Property);
		return "";
	}
	
	
	
	
	
	
	
}
