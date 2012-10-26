package Core.Crawlers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

//import javax.lang.model.element.Element;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;


import Core.CommonDef;
import Core.CrawlerProcessor;
import Core.ECrawlingType;
import Core.Interfaces.ICrawler;
import Elements.IElement;
import Elements.StringDataElement;
import Elements.UserElement;
import Services.FileServices;
import Services.Dom.*;
import Services.Log.ELogLevel;
public class UserCrawler extends ACrawler  implements ICrawler
{
	private String m_userName;
	private String m_userPath;
	private String m_userUrl ;
	private String m_userXmlPath;
	
	private DomDocument m_documnet ;
	private DomNode m_node;
	private final static String SUBJECTS_XPATH  = CommonDef.CONTANIER_XPATH + "/ul";
	private final static String SUBJECT_NAME_XPATH = "div/h3/a";
	
	private IElement m_userElement;
	private List<String> m_subjects;
	private Map<String,ICrawler> m_subjectLists;
		
	public UserCrawler(String userName)
	{
		if (!FileServices.PathExist(CommonDef.USERS_FOLDER_POOL_PATH)) FileServices.CreateFolder(GetClassName(),CommonDef.USERS_FOLDER_POOL_PATH);
		m_userName = userName;
		m_userPath = CommonDef.USERS_FOLDER_POOL_PATH + "//" + m_userName;
		m_userXmlPath = m_userPath + "//" + userName + ".xml" ; 
		Init();
	}


	private void Init()
	{
		if (!FileServices.PathExist(m_userPath))
		{
			FileServices.CreateFolder(GetClassName(), m_userPath);
		}
		m_subjects = new ArrayList<String>();
		m_subjectLists = new HashMap<String, ICrawler>();
	}
	
	@Override
	public IElement Crawl(boolean recursive) 
	{
		return Crawl(CommonDef.PINTERSET_URL + m_userName+ "",recursive);		
	}
	
	protected IElement Crawl(String userUrl,boolean recursive) {

		m_userUrl = userUrl;
		if (m_userName.isEmpty() || m_userPath.isEmpty() || m_userXmlPath.isEmpty()) return null;
		try {
				if (DownloadFile(m_userXmlPath, m_userUrl))
				{
					m_documnet = new DomDocument(m_userXmlPath);
					Node node =  m_documnet.GetNode(SUBJECTS_XPATH);
					m_node = new DomNode(node);
					NodeList allSubjects =  node.getChildNodes();/* m_node.GetNodeList("//li") */;
					m_userElement = new UserElement(m_userName);
					for(int i =0 ; i<allSubjects.getLength() ;++i)
					{
						if(recursive)
						{
							Node n =  allSubjects.item(i);
							if (n.getNodeType() == node.ELEMENT_NODE)
							{
								String subjectName = m_node.GetNode(SUBJECT_NAME_XPATH,n).getTextContent().replace(' ', '_');
								ICrawler subjectCrawler = new SubjectsCrawler(m_userName, subjectName);
								IElement subjectElm = subjectCrawler.Crawl(CrawlerProcessor.GetInstance().GetDepthCrawling(ECrawlingType.Subject));//TODO:: Add b
								if (subjectElm == null)
								{
									WriteLineToLog("subject element is null!! subjectname=" +subjectName, ELogLevel.ERROR);
								}
								else
								{
									m_userElement.AddElement(subjectElm);
								}
								
							}
						}
					}
					 return m_userElement; //TODO:: need to add processor!!
				}
				
			}
			catch (Exception ex)
			{
				PrintErrorParsing(ex, m_userName +" crawling" );
				return null;
			} 
			
		return null;
		

	
	}


	@Override
	public boolean SaveItem() {
		// TODO Auto-generated method stub
		return false;
	}


	
	
	
	
}
