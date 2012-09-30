package Core;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


import Core.Interfaces.ICrawler;
import Elements.Interfaces.IElement;
import Services.FileServices;
import Services.Dom.DomDocument;
import Services.Dom.DomNode;
import Elements.classes.*;

public class SubjectsCrawler extends ACrawler implements ICrawler
{

	private String m_subjectName;
	private String m_subjectPath;
	private String m_userPath;
	private String m_userName;
	private String m_subjectXmlPath;
	private String m_subjectURL ;
	
	private DomDocument m_documnet;
	private DomNode m_itemsNode;
		
	Map<String,String> _ITEM_PROPERTIES = new HashMap<String, String>();
	
	
	final static String ITEM_DESCRIPTION_XPATH = "p[@class='description']";
	final static String ITEM_NUM_LIKES_XPATH = "p[@class='stats colorless']/span";
	final static String ITEM_NAME_XPATH = "div[@class='convo attribution clearfix']/p";
	
	
	
	public SubjectsCrawler(String userName ,String subjectName)
	{
		m_subjectName = subjectName;
		m_userName = userName;
		m_userPath= CommonDef.USERS_FOLDER_POOL_PATH +"//" +userName;
		m_subjectPath = m_userPath + "//" + m_subjectName;
		m_subjectXmlPath =m_userPath +"//" + m_subjectName.replace("-", "_")+".xml";
		Init();
	}
	
	void Init()
	{
		_ITEM_PROPERTIES.put("ITEM_DESCRIPTION_XPATH", ITEM_DESCRIPTION_XPATH);
		_ITEM_PROPERTIES.put("ITEM_NUM_LIKES_XPATH", ITEM_NUM_LIKES_XPATH);
		_ITEM_PROPERTIES.put("ITEM_NAME_XPATH", ITEM_NAME_XPATH);
	
	}
	
	@Override
	public IElement Crawl() 
	{
		
		if(m_userName.length() != 0 && m_subjectName.length() != 0 ) return Crawl(m_subjectName);
		else
		{
			WriteLineToLog("no user name and subject name can't crawled the subject...");
			return null;
		}
		
	}

	
	protected IElement Crawl(String subjectName) 
	{
		m_subjectName = subjectName;
		m_subjectURL = CommonDef.PINTERSET_URL + m_userName +"/" +subjectName ;
		if (!FileServices.PathExist(m_userPath)) FileServices.CreateFolder(GetClassName(), m_userPath);
		//if (FileServices.PathExist(m_subjectPath)) FileServices.CreateFolder(module, Path)
		if (DownloadFile(m_subjectXmlPath, m_subjectURL))
		{
			try 
			{
				IElement subjectElem = new SubjectElement(m_subjectName);
				m_documnet = new DomDocument(m_subjectXmlPath);
				Node itemsNode = m_documnet.GetNode(CommonDef.CONTANIER_XPATH);
				m_itemsNode = new DomNode(itemsNode);
				NodeList allItems =  itemsNode.getChildNodes();
				
				for(int i =0 ; i<allItems.getLength() ;++i)
				{
					
					Node n =  allItems.item(i);
					if (n.getNodeType() == itemsNode.ELEMENT_NODE)
					{
						
						
						String itemDes = m_itemsNode.GetNode(ITEM_DESCRIPTION_XPATH,n).getTextContent().replace(' ', '_').trim();
						String itemName = m_itemsNode.GetNode(ITEM_NAME_XPATH,n).getTextContent().replace(' ', '_').trim();
						
						IElement elem = new SubjectElement(itemName);
						elem.AddProperty(EProperty.description.toString(),itemDes);
						subjectElem.AddElement(elem);
					}
				}
				return subjectElem;
			} 
			catch (Exception e) {
				WriteLineToLog("error excpetion happen:" + e.getMessage());
				return null;
				
			}
			
		}
		
		return null;
	}

	@Override
	public boolean SaveItem() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean CreateResultsPool(String Path) {
		// TODO Auto-generated method stub
		return false;
	}

}
