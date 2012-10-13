package Services.Neo4J;



import javax.lang.model.element.Element;


import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.IndexHits;

import Core.CommonCFinder;
import Elements.Interfaces.IElement;
import Elements.classes.EProperty;
import Services.Log.ELogLevel;

public class Neo4JServices extends CommonCFinder 
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
				WriteLineToLog("add node id: "+ tempNode.getId(), ELogLevel.INFORMATION);
			}
			else
			{
				tempNode =  hits.getSingle();
			}
			tx.success();
			
        }
		catch(Exception ex)
		{
			WriteLineToLog("transaction failed:" +ex.getMessage(), ELogLevel.ERROR);
			tx.finish();
			return 0;
		}
		finally
        {
            tx.finish();
            
        }
		return tempNode.getId();
		
	}
	
	public boolean AddRelasion(IElement elm1 ,IElement elm2, RelationshipType relType)
	{
		Node elm1Node = m_indexNode.get(EProperty.name.toString(), elm1.GetName()).getSingle();
		Node elm2Node = m_indexNode.get(EProperty.name.toString(), elm2.GetName()).getSingle();
		if (elm1Node.getId() == elm2Node.getId()) 	return false;
		Relationship rel =  elm1Node.createRelationshipTo(elm2Node,relType);
		
		return true;
		
	}
	
	
	
	
	
}
