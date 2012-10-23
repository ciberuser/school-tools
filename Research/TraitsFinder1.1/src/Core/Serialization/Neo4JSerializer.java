package Core.Serialization;

import scala.util.control.Exception;
import Core.CommonDef;
import Elements.IElement;
import Services.FileServices;
import Services.Log.ELogLevel;
import Services.Neo4J.Neo4JActivation;
import Services.Neo4J.Neo4JServices;


public class Neo4JSerializer extends ASerializer implements IElementSerializer  {

	
	//private Neo4JActivation m_DBActivation;
	
	private Neo4JServices m_neoServies ;
	
	public Neo4JSerializer(IElement element,String dbDir)
	{
		super(element);
		if (!Neo4JActivation.IsActive())
		{
			WriteLineToLog("Activate Neo4J database...", ELogLevel.INFORMATION);
			if (!FileServices.PathExist(dbDir)) FileServices.CreateFolder(GetClassName(), dbDir);
			if (!Neo4JActivation.IsActive())
			{
				if (!Neo4JActivation.Start(dbDir)) 
				{
					WriteLineToLog("database didn't started...",ELogLevel.ERROR);
					
				}
			}
			m_neoServies = new Neo4JServices(Neo4JActivation.GetGraphDatabaseService());
			WriteLineToLog("Neo4J is is active", ELogLevel.INFORMATION);
		}
		m_neoServies = new Neo4JServices(Neo4JActivation.GetGraphDatabaseService());
	}
	
	

	@Override
	public boolean Save()
	{
		return  (m_neoServies.CreateNode(m_element)!=0);
	}

	@Override
	public boolean Link(IElement elemet)
	{
		if (m_element == null)
		{
			WriteLineToLog("no serializer have no element", ELogLevel.ERROR);
			return false;
		}
		
		if ( m_neoServies == null)
		{
			WriteLineToLog("no neo4j services ", ELogLevel.ERROR);
			return false;
		}
		return  m_neoServies.AddWeightRelasion(m_element,elemet) ;
	
	}



	@Override
	public IElementSerializer Load() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public boolean Close() {
		if (Neo4JActivation.IsActive())
		{
			return Neo4JActivation.Stop();
		}
		return false;
	}

	

}