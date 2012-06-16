package Core;
import java.io.File;


import Services.FileServices;
import Services.Logger;

import Elements.Interfaces.*;
import Elements.classes.*;
import Core.Interfaces.*;


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;




public class PintersetScouter extends BaseCFinder  implements IScouter {

	//final static String USER_ADDRESS_Path = 
	final static String PINTEREST_SCOUTER = "PinterstScouter";
	final static String USER_ALL_USERS_COMMENT_PATH = "//body/div[@id='wrapper']/div[@id='ColumnContainer']";
	final static String USER_COMMENT_PATH = "//div[@class='convo attribution clearfix']";
	final static String USERS_FOLDER_POOL_PATH = CommonDef.USERS_FOLDER_POOL_PATH;
	
	String m_FilePath;
	private Document doc;
	
	@Override
	public IElement Scout() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IElement Scout(String item) {
		m_FilePath = item;
		File fXmlFile = new File(item);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		try 
		{
			
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(fXmlFile);
						
			XPathFactory factory = XPathFactory.newInstance();
			XPath xpath = factory.newXPath();
			Node users = (Node)xpath.evaluate(USER_ALL_USERS_COMMENT_PATH, doc,XPathConstants.NODE);
			IElement mainElement = new StringDataElement();
			if(users != null)
			{
			
				NodeList list = (NodeList)xpath.evaluate(USER_COMMENT_PATH,users,XPathConstants.NODESET);
				
				CreateResultsPool(USERS_FOLDER_POOL_PATH);
				WriteLineToLog( "Data for of nodes :");
					for (int i = 0 ; i < list.getLength() ; i++)
					{
						Node t=list.item(i);
					  	String[] user_item= t.getTextContent().trim().replace("\t","").split("\n");
					    Node userDetiles =	(Node)xpath.evaluate("//a",t,XPathConstants.NODE);
					    String userName = user_item[0];
					    String Link = user_item[2];
					    //if (userDetiles.getAttributes().getNamedItem("title")!=null)
						//{
					    	//String userName =userDetiles.getAttributes().getNamedItem("title").getNodeValue(); // user_item[0];
					    	//Link = userDetiles.getAttributes().getNamedItem("href").getNodeValue();
					    	CreateResultsPool(USERS_FOLDER_POOL_PATH +"/" +userName.replace(" ", "_"));
							WriteLineToLog("user to elemnet as key:" + userName+" value : "+Link);
						//}
						
						
					//	WriteLineToLog(" add element key:" +  user_item[2] +" value : " +userLink );
						
						//save pool of intersts.
					  	NodeList user_nudeItem = (NodeList)xpath.evaluate("//p//a",t,XPathConstants.NODESET);
						for(int j = 0 ; j <user_nudeItem.getLength() ; ++j )
						{
							String Linkitem =  user_nudeItem.item(j).getAttributes().getNamedItem("href").getNodeValue();
							String value = user_nudeItem.item(j).getTextContent();
							if (Linkitem.contains(user_item[2].toLowerCase()))
							{
								WriteLineToLog("value :"+ value+" propery value will be: " +Linkitem);
							}
							else
							{
								WriteLineToLog("link to add: "+ Linkitem ) ;
							}
						}
					  	//NamedNodeMap nnm= user_nudeItem.getAttributes();
						//Node nx= nnm.getNamedItem("href");
						//String st= nx.getNodeValue();
						
						
						
					}
				//}
			}
			return mainElement;
			
			
		} 
		catch (Exception e)
		{
			WriteLineToLog("Error on parsing main pinterset error : "+ e.toString());
			Logger.GetLogger().Write(PINTEREST_SCOUTER, e.toString());
			e.printStackTrace();
			return null;
		}
		
		
	}

	@Override
	public boolean SaveItem() {
			return true;
	}

	@Override
	public boolean CreateResultsPool(String Path)
	{
		return FileServices.CreateFolder(getClass().getName(), Path);
		
	  	
	}
	/*
	private void WriteLineToLog(String msg)
	{
		Logger.GetLogger().WriteLine(PINTEREST_SCOUTER,msg);
	}

*/
	
	
	
}
