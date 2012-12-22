package Core.Crawlers;


import Services.FileServices;

import Elements.EProperty;
import Elements.IElement;
import Elements.StringDataElement;

import Core.CommonDef;
import Core.QueueCrawlinTargets;


import Core.Interfaces.*;
import Services.Dom.DomDocument;
import Services.Dom.DomNode;
import Services.Log.ELogLevel;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


//need to refactor all this class!!!!

public class PintersetCrawler extends ACrawler  implements ICrawler {

	final static String PINTEREST_SCOUTER = "PinterstScouter";
	final static String USER_ALL_USERS_COMMENT_PATH = CommonDef.CONTANIER_XPATH + "/div/div[@class='convo attribution clearfix']/a";
	final static String PINTEREST_PATH = CommonDef.ROOT_DATA_FOLDER + CommonDef.PINTERSET_XML;
	
	private String m_FilePath;
	
	
	
	public PintersetCrawler()
	{
		super();
	}
	
	@Override
	public IElement Crawl(boolean recursive) {
		
		return Crawl(PINTEREST_PATH);
	}
	
	
	protected IElement Crawl(String SavefilePath) 
	{
		m_FilePath = SavefilePath;
		IElement main = null;
		try
		{
			if (ForceDownLoadFile(m_FilePath, CommonDef.PINTERSET_URL))
				 main = StartCrawling();
		} 
		catch (Exception e)
		{
			PrintErrorParsing(e,"main Pinterest file");
		}
		return main;
	}

	private IElement StartCrawling() throws Exception
	{
		IElement mainElement = null;
		if (!FileServices.PathExist(m_FilePath))
		{
			WriteToLog("no file to parse download failed...", ELogLevel.ERROR);
		}
		else
		{
			DomDocument documetService = new DomDocument(m_FilePath);
			
			NodeList allusers=  documetService.GetNodes(USER_ALL_USERS_COMMENT_PATH);
			if (allusers!=null)
			{
				mainElement = new StringDataElement(EProperty.name.toString(), "main");
			}
			
			for(int i =0 ; i<allusers.getLength() ;++i)
			{
				Node n = allusers.item(i);
				String userName = new DomNode(n).GetAttribute("href");
				if (userName!="")
				{
					userName = userName.replace("/", "");
					QueueCrawlinTargets.GetInstance().AddTarget(userName);
				}
			}
		}
		
		return mainElement;
	}
		
	@Override
	public boolean SaveItem()
	{
		return true;
	}

	
	
	

	
	
}
