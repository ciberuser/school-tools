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
	private boolean m_activeNeo4J;
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
	}
	
	

	@Override
	public void Save()
	{
		m_neoServies.CreateNode(m_element);
	}



	@Override
	public void Link(IElement elemet) {
		// TODO Auto-generated method stub
		
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
