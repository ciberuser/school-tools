package Core;
import java.io.File;


import Services.FileServices;
import Services.ICollector;
import Services.Logger;

import Elements.Interfaces.*;
import Elements.classes.*;
import Core.Interfaces.*;
import Services.Dom.DomNode;


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;




public class PintersetCrawler extends ACrawler  implements ICrawler {

	//final static String USER_ADDRESS_Path = 
	final static String PINTEREST_SCOUTER = "PinterstScouter";
	final static String USER_ALL_USERS_COMMENT_PATH = CommonDef.CONTANIER_PATH;
	final static String USER_COMMENT_PATH = "//div[@class='convo attribution clearfix']";
	final static String USERS_FOLDER_POOL_PATH = CommonDef.USERS_FOLDER_POOL_PATH;
	final static String PINTEREST_PATH = CommonDef.ROOT_DATA_FOLDER + CommonDef.PINTERSET_XML;
	
	private String m_FilePath;
	private Document doc;
	private DomNode m_node;
	
	
	public PintersetCrawler()
	{
		super();
		m_node = new DomNode(); 
	}
	
	@Override
	public IElement Crawl() {
		
		return Crawl(PINTEREST_PATH);
	}
	
	@Override
	public IElement Crawl(String SavefilePath) 
	{
		m_FilePath = SavefilePath;
		try
		{
			if (DownloadFile(m_FilePath, CommonDef.PINTERSET_URL))
			return StartCrawling();
			//in case of fail...
			WriteLineToLog("failed to download pinterest main file....");
			return null;
		} 
		catch (Exception e)
		{
			PrintErrorParsing(e,"main Pinterest file");
			return null;
		}
	}

	private IElement StartCrawling() throws Exception
	{
		if (m_FilePath == null) 
		{
			WriteLineToLog("Error : no file to main pinterset crawler...");
			return  null;
		}
		
			
		m_FilePath = PINTEREST_PATH;
		
		File fXmlFile = new File(m_FilePath);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		doc = dBuilder.parse(fXmlFile);
		
		Node users = m_node.GetNode(USER_ALL_USERS_COMMENT_PATH,doc); 
		IElement mainElement = new StringDataElement();
		if(users != null)
		{
			NodeList list = m_node.GetNodeList(USER_COMMENT_PATH,users) ;
			CreateResultsPool(USERS_FOLDER_POOL_PATH);
			WriteLineToLog( "Data for of nodes :");
			for (int i = 0 ; i < list.getLength() ; i++)
			{
				Node t=list.item(i);
			  	String[] user_item= t.getTextContent().trim().replace("\t","").split("\n");
			    Node userDetiles =	m_node.GetNode("//a",t);
			    String userName = user_item[0];
			    String Link = user_item[2];
		  
		    	CreateResultsPool(USERS_FOLDER_POOL_PATH +"/" +userName.replace(" ", "_"));
				WriteLineToLog("user to elemnet as key:" + userName+" value : "+Link);
				UserCrawler uct = new  UserCrawler(Link); 
							
				//save pool of intersts.
		    	NodeList user_nudeItem = m_node.GetNodeList("//p//a",t); //(NodeList)xpath.evaluate("//p//a",t,XPathConstants.NODESET);
				for(int j = 0 ; j <user_nudeItem.getLength() ; ++j )
				{
					String Linkitem = m_node.GetAttribute(user_nudeItem.item(j),"href");  //user_nudeItem.item(j).getAttributes().getNamedItem("href").getNodeValue();
					String value = m_node.GetValue(user_nudeItem.item(j));
					if (Linkitem.contains(user_item[2].toLowerCase()))
					{
						WriteLineToLog("value :"+ value+" propery value will be: " +Linkitem);
						//Debug...
					}
					else
					{
				 	//WriteLineToLog("link to add: "+ Linkitem ) ;
					}
				}
    		}
			return mainElement;
		}
		return null;
	}
		
	@Override
	public boolean SaveItem()
	{
		return true;
	}

	@Override
	public boolean CreateResultsPool(String Path)
	{
		return FileServices.CreateFolder(getClass().getName(), Path);
	}
	
	
	

	
	
}
