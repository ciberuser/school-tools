package Services.Neo4J;




import java.util.Iterator;
import java.util.Map;

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
	private Index<Relationship> m_indexRelation; 
			
	public Neo4JServices(GraphDatabaseService service)
	{
		
			m_services = service;
			m_indexNode =  m_services.index().forNodes(EProperty.name.toString());
			m_indexRelation = m_services.index().forRelationships(RelType.Users.toString());
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
				//tempNode.setProperty(EProperty.name.toString(),name );
				m_indexNode.add(tempNode, EProperty.name.toString(),name );
				WriteLineToLog("node created id: "+ tempNode.getId(), ELogLevel.INFORMATION);
			}
			else
			{
				tempNode =  hits.getSingle();
				WriteLineToLog("found node id:" +tempNode.getId(), ELogLevel.INFORMATION);
			}
			Iterator itr =  element.GetProperties().entrySet().iterator();
			while (itr.hasNext())
			{
				Map.Entry pair = (Map.Entry) itr.next();
				tempNode.setProperty(pair.getKey().toString(), pair.getValue().toString());
				WriteLineToLog("add prop to node: propkey="+ pair.getKey().toString()+" propvalue= " +pair.getValue().toString(), ELogLevel.INFORMATION);
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
	
	public Relationship AddRelasion(IElement elm1 ,IElement elm2, RelationshipType relType)
	{
		Relationship relReturn ;
		Transaction tx =null;
		try
		{
			Node elm1Node = m_indexNode.get(EProperty.name.toString(), elm1.GetName()).getSingle();
			Node elm2Node = m_indexNode.get(EProperty.name.toString(), elm2.GetName()).getSingle();
			if ((elm1Node.getId() == elm2Node.getId()) || elm1Node == null || elm2Node == null ) return null;
			tx = m_services.beginTx();
			for(Relationship rel :elm1Node.getRelationships(RelType.Weight))
			{
				if (rel.getOtherNode(elm2Node) !=null)
				{
					relReturn =rel;
					break;
				}
			}
			relReturn =  elm1Node.createRelationshipTo(elm2Node,relType);
			tx.success();
			WriteLineToLog("set new relation between : " +elm1Node.getProperty(EProperty.name.toString())+ " to " +elm2Node.getProperty(EProperty.name.toString())+" created reltype=" +relType.toString(),ELogLevel.INFORMATION );
			tx.finish();
			return relReturn;
		}
		catch (Exception e) {
			WriteLineToLog("exception occur msg="+e.getMessage(),ELogLevel.ERROR);
			return null;
		}
		finally 
		{
			if (tx!=null) tx.finish();
		}
	}
		
	
	public boolean AddWeightRelasion(IElement elm1 ,IElement elm2)
	{
		Relationship rel =  AddRelasion(elm1, elm2, RelType.Weight);
		if (rel == null) return false;
		boolean status = false;
		Transaction tx = m_services.beginTx();
		try
		{
			if (!rel.hasProperty(CommonDef.NEO_WEIGHT))
			{
				rel.setProperty(CommonDef.NEO_WEIGHT, 1);
				status = true;
			}
			else
			{
				int count = (Integer)rel.getProperty(CommonDef.NEO_WEIGHT);
				count++;
				rel.setProperty(CommonDef.NEO_WEIGHT, count);
				status = true;
			}
			tx.success();
			tx.finish();
			return status;
		}
		catch(Exception e)
		{
			WriteToLog("error occur msg=" +e.getMessage(), ELogLevel.ERROR);
			tx.finish();
			return false;
		}
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
