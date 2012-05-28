package Core;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import Services.FileServices;
import Services.Logger;

import Elements.StringDataElement;
import Interfaces.IElement;
import Interfaces.IScouter;

import javax.xml.crypto.NodeSetData;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



public class PintersetScouter implements IScouter {

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
					  	//creating user data.
						String userName = user_item[0];
						CreateResultsPool(USERS_FOLDER_POOL_PATH +"/" +userName.replace(" ", "_"));
						WriteLineToLog("user to elemnet :" + userName);
						WriteLineToLog("add property type:" +  user_item[2]);
						//save pool of intersts.
					  	NodeList user_nudeItem = (NodeList)xpath.evaluate("//p//a",t,XPathConstants.NODESET);
						for(int j = 0 ; j <user_nudeItem.getLength() ; ++j )
						{
							String Link =  user_nudeItem.item(j).getAttributes().getNamedItem("href").getNodeValue();
							if (Link.contains(user_item[2]))
							{
								WriteLineToLog("propery value will be: " +Link);
							}
							else
							{
								WriteLineToLog("link to add: "+ Link) ;
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
		return FileServices.CreateFolder(PINTEREST_SCOUTER, Path);
		
	  	
	}
	
	private void WriteLineToLog(String msg)
	{
		Logger.GetLogger().WriteLine(PINTEREST_SCOUTER,msg);
	}

	
	
	
}
