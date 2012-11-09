package Core.Crawlers;



import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import Core.CommonDef;
import Core.CrawlerProccessor;
import Core.ECrawlingType;
import Core.UsersCrawlingTargets;
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
	private String m_followersXmlPath;
	private String m_followersUrl;
	
	private DomDocument m_documnet;
	private DomNode m_itemsNode;
		
	
	
	
	final static String ITEM_DESCRIPTION_XPATH = "p[@class='description']";
	final static String ITEM_NUM_LIKES_XPATH = "p[@class='stats colorless']/span";
	final static String ITEM_NAME_XPATH = "div[@class='convo attribution clearfix']/p";
	final static String ITEM_REPIN_XPATH ="";
	final static String ITEM_URL_XPATH = "div[@class='PinHolder']/a";
	final static String FOLLOWERS_FILE_NAME = CommonDef.FOLLOWER_FILE_NAME;
	final static String FOLLOWERS_URL = "followers";
	final static String FOLLOWERS_USER_LIST ="//body/div[@class='FixedContainer']/div[@id='PeopleList']/div[@class='person']";
	
	
	
	public SubjectsCrawler(String userName ,String subjectName ,String subjectUrl)
	{
		Init(userName,subjectName,subjectUrl);
		
	}
	
	public SubjectsCrawler(String userName ,String subjectName)
	{
		Init(userName, subjectName,CommonDef.PINTERSET_URL + m_userName +"/" + CleanSubject2URL(subjectName));
	}
	
	private void Init(String userName ,String subjectName ,String subjectUrl)
	{
		m_subjectName = CommonDef.AlignSubjectName(subjectName);
		m_userName = userName;
		m_userPath= CommonDef.USERS_FOLDER_POOL_PATH +"//" +userName;
		m_subjectURL = subjectUrl;
		m_subjectPath = m_userPath + "//" + m_subjectName;
		m_followersXmlPath = m_subjectPath + "//" +  FOLLOWERS_FILE_NAME;
		
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
		m_followersUrl = m_subjectURL +FOLLOWERS_URL;
		
		if (!FileServices.PathExist(m_subjectPath)) FileServices.CreateFolder(GetClassName(), m_subjectPath);
		IElement subjectElem =null;
		if (DownloadFile(m_subjectXmlPath, m_subjectURL))
		{
			try 
			{
				subjectElem = new SubjectElement(m_subjectName);
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
								IElement itemElm = itemCrawler.Crawl(CrawlerProccessor.GetInstance().GetDepthCrawling(ECrawlingType.Item)); //TODO::add actions
								itemElm.AddProperty(EProperty.description.toString(), itemDes);
								if(itemLikes.length() > 0) itemElm.AddProperty(EProperty.likes.toString(), itemLikes); 
								subjectElem.AddElement(itemElm);
							}
						}
					
					}
				}
				
				DownloadFile(m_followersXmlPath, m_followersUrl);
				if (!FileServices.PathExist(m_followersXmlPath))
				{
					WriteLineToLog("followers didn't run...", ELogLevel.ERROR);
				}
				else
				{
					CrawlFollowers(m_followersXmlPath, itemsNode);			
				}
				
				return subjectElem;
			} 
			catch (Exception e) {
				WriteLineToLog("excpetion happen:" + e.getMessage(),ELogLevel.ERROR);
				WriteLineToLog("cause="+e.getCause().toString(), ELogLevel.ERROR); 
				return subjectElem;
			}
		}
		
		return null;
	}
	
	@Override
	public boolean SaveItem() {
		// TODO Auto-generated method stub
		return false;
	}

	private void CrawlFollowers(String followerXml ,Node elementNode)
	{
		try
		{
			m_documnet = new DomDocument(followerXml);
			Node foolowersNode = m_documnet.GetNode(FOLLOWERS_USER_LIST);
			NodeList foolowersNodes = foolowersNode.getChildNodes();
			for(int i =0 ; i<foolowersNodes.getLength() ;++i)
			{
				Node n = foolowersNodes.item(i);
				
				if (n.getNodeType() == elementNode.ELEMENT_NODE) 
				{
					DomNode ns = new DomNode(n);
					String  userfollow =null;
					if (ns != null)
					{
						userfollow = ns.GetAttribute(n, "href") ;
					}
					if (userfollow!="") 
					{
						userfollow = userfollow.replaceAll("/","");
						UsersCrawlingTargets.GetInstance().AddTarget(userfollow);
					}
				}
			}
		}
		catch(Exception e )
		{
			WriteToLog("exception happen on followes crawling msg=" +e.getMessage(), ELogLevel.ERROR);
			
		}
	}

	private String CleanSubject2URL(String sujectName)
	{
		return  CommonDef.AlignSubjectURL(sujectName);
	}
	
	private String GetItemProperty(Node node,String xpath) throws DOMException, XPathExpressionException
	{
		return m_itemsNode.GetNode(xpath,node).getTextContent().replace(' ', '_').trim();
	}
	
}
