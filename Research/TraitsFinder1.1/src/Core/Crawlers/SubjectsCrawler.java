package Core.Crawlers;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import scala.annotation.target.getter;


import Core.CommonDef;
import Core.CrawlerProcessor;
import Core.ECrawlingType;
import Core.Interfaces.ICrawler;
import Elements.EProperty;
import Elements.IElement;
import Services.FileServices;
import Services.Dom.DomDocument;
import Services.Dom.DomNode;
import Services.Log.ELogLevel;
import Elements.*;

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
		
	
	
	
	final static String ITEM_DESCRIPTION_XPATH = "p[@class='description']";
	final static String ITEM_NUM_LIKES_XPATH = "p[@class='stats colorless']/span";
	final static String ITEM_NAME_XPATH = "div[@class='convo attribution clearfix']/p";
	final static String ITEM_REPIN_XPATH ="";
	final static String ITEM_URL_XPATH = "div[@class='PinHolder']/a";
	
	
	
	public SubjectsCrawler(String userName ,String subjectName)
	{
		m_subjectName = subjectName.replace("-", "_");
		m_userName = userName;
		m_userPath= CommonDef.USERS_FOLDER_POOL_PATH +"//" +userName;
		m_subjectPath = m_userPath + "//" + m_subjectName;
		m_subjectXmlPath =m_userPath +"//"+ m_subjectName+"//"+ m_subjectName+".xml";
		if (!FileServices.PathExist(m_userPath)) FileServices.CreateFolder(GetClassName(), m_userPath);
		
	}
	
	
	
	@Override
	public IElement Crawl(boolean recursive) 
	{
		
		if(m_userName.length() != 0 && m_subjectName.length() != 0 ) return Crawl(m_subjectName,recursive);
		else
		{
			WriteLineToLog("no user name and subject name can't crawled the subject...",ELogLevel.ERROR);
			return null;
		}
		
	}

	protected IElement Crawl(String subjectName,boolean recursive) 
	{
		m_subjectName = subjectName;
		m_subjectURL = CommonDef.PINTERSET_URL + m_userName +"/" + CleanSubject2URL(subjectName) ;
		
		if (!FileServices.PathExist(m_subjectPath)) FileServices.CreateFolder(GetClassName(), m_subjectPath);
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
						String itemDes = GetItemProperty(n, ITEM_DESCRIPTION_XPATH); //can drop
						String itemName = GetItemProperty(n, ITEM_NAME_XPATH);       // can be drop
						String itemLikes = GetItemProperty(n,ITEM_NUM_LIKES_XPATH);
						
						DomNode urlNode = new DomNode(m_itemsNode.GetNode(ITEM_URL_XPATH, n));
						String ItemURL = (urlNode!=null) ? urlNode.GetAttribute("href") : "";		
						
						if (ItemURL=="") WriteLineToLog("Item Url Not Found", ELogLevel.ERROR);
						else
						{
							if (recursive) 
							{
								ICrawler itemCrawler = new ItemCrawler(CommonDef.PINTERSET_URL+ItemURL, m_subjectPath + "//" +itemName+".xml", itemName);
								IElement itemElm = itemCrawler.Crawl(CrawlerProcessor.GetInstance().IsDepthCrawling(ECrawlingType.Item)); //TODO::add actions
								itemElm.AddProperty(EProperty.description.toString(), itemDes);
								if(itemLikes.length() > 0) itemElm.AddProperty(EProperty.likes.toString(), itemLikes); 
								subjectElem.AddElement(itemElm);
							}
						}
					
					}
				}
				return subjectElem;
			} 
			catch (Exception e) {
				WriteLineToLog("excpetion happen:" + e.getMessage(),ELogLevel.ERROR);
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

	

	private String CleanSubject2URL(String sujectName)
	{
		return  sujectName.replace("_", "-").replace("'", "-").replace("&", "").replace("--", "-").replace(".", "");
	}
	
	private String GetItemProperty(Node node,String xpath) throws DOMException, XPathExpressionException
	{
		return m_itemsNode.GetNode(xpath,node).getTextContent().replace(' ', '_').trim();
	}
	
}
