package Core.Crawlers;


import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


import Core.CoreContext;
import Core.PinterestContext;
import Core.EPinterestCrawlingType;
import Core.PinterestStatisticsBuilder;
import Core.Statistics;
import Core.StatisticsBuilder;
import Core.StatisticsBuilder.EStatsType;
import Core.Interfaces.ICrawler;
import Elements.IElement;
import Elements.SubjectElement;
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
	private final static String SUBJECTS_XPATH  ="//*[@id='ColumnContainer']/ul" /* CommonDef.CONTANIER_XPATH + "/ul"*/;
	private final static String SUBJECT_NAME_XPATH = "div/h3/a";
	
	

		
	public UserCrawler(String userName)
	{
		if (!FileServices.PathExist(PinterestContext.USERS_FOLDER_POOL_PATH)) FileServices.CreateFolder(GetClassName(),PinterestContext.USERS_FOLDER_POOL_PATH);
		m_userName = userName;
		m_userPath = PinterestContext.USERS_FOLDER_POOL_PATH + "//" + m_userName;
		m_userXmlPath = m_userPath + "//" + userName + ".xml" ; 
		Init();
	}


	private void Init()
	{
		if (!FileServices.PathExist(m_userPath))
		{
			FileServices.CreateFolder(GetClassName(), m_userPath);
		}
		
	}
	
	@Override
	public IElement Crawl(boolean recursive) 
	{
		return Crawl(PinterestContext.PINTERSET_URL + m_userName+ "",recursive);		
	}
	
	protected IElement Crawl(String userUrl,boolean recursive) { //TODO::refactor!!!!

		
		IElement userElement = null;
		m_userUrl = userUrl;
		if (m_userName.isEmpty() || m_userPath.isEmpty() || m_userXmlPath.isEmpty()) return null; //need to refactor!!!
		try {
				if (DownloadFile(m_userXmlPath, m_userUrl))
				{
					m_documnet = new DomDocument(m_userXmlPath);
					Node node =  m_documnet.GetNode(SUBJECTS_XPATH);
					if (node!=null)
					{
						m_node = new DomNode(node);
						NodeList allSubjects =  node.getChildNodes();
						userElement = new UserElement(m_userName);
						for(int i =0 ; i<allSubjects.getLength() ;++i)
						{
							if(recursive) //need to remove , don't needed !!
							{
								Node n =  allSubjects.item(i);
								if (n.getNodeType() == Node.ELEMENT_NODE)
								{
												
									String subjectUrl = new DomNode(m_node.GetNode(SUBJECT_NAME_XPATH,n)).GetAttribute("href");
									if (subjectUrl != "") 
									{
										subjectUrl = PinterestContext.PINTERSET_URL  + subjectUrl;
										WriteLineToLog("subjectURL="+subjectUrl, ELogLevel.INFORMATION);
									}
									
									Node subjectNode = m_node.GetNode(SUBJECT_NAME_XPATH,n);
									String subjectName = (subjectNode != null)? subjectName = subjectNode.getTextContent().replace(' ', '_') : (subjectUrl != "")? subjectUrl.split("/")[2]  : "";
									if (subjectName == "" ||subjectUrl == "") 
									{
										WriteLineToLog("missing subject url or name skip this node...", ELogLevel.WARNING);
										continue;
									}
									subjectUrl = subjectUrl.replaceAll("api.", ""); //test it!!!
									if (CoreContext.COLLECT_STATISIC)
									{
										StatisticsBuilder.GetInstance().AddHit(PinterestStatisticsBuilder.EPinStatsType.eUserSubject,m_userName, subjectName);
									}
									IElement subjectElm =null;
									if (!CoreContext.FAST_MODE)
									{
										ICrawler subjectCrawler = new SubjectsCrawler(m_userName, subjectName,subjectUrl);
										subjectElm = subjectCrawler.Crawl(PinterestContext.GetProcessor().GetDepthCrawling(EPinterestCrawlingType.Subject));//TODO:: Add b
										
									}
									else
									{
										subjectElm  = new SubjectElement(subjectName);
									}
									if (subjectElm == null)
									{
										WriteLineToLog("subject element is null!! subjectname=" +subjectName, ELogLevel.ERROR);
									}
									else
									{
										userElement.AddElement(subjectElm);
									}
									
								}
							}
						}
					}
					return userElement; //TODO:: need to add processor!!
				}
				
			}
			catch (Exception ex)
			{
				PrintErrorParsing(ex, m_userName +" crawling" );
				
			} 
		return userElement;
		

	
	}


	@Override
	public boolean SaveItem() {
		// TODO Auto-generated method stub
		return false;
	}


	
	
	
	
}
