package Core;

import java.io.IOException;

//import javax.lang.model.element.Element;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;


import Core.Interfaces.ICrawler;
import Elements.Interfaces.IElement;
import Services.FileServices;
import Services.Dom.*;
public class UserCrawler extends ACrawler  implements ICrawler
{
	private String m_userName;
	private String m_userPath;
	private String m_userUrl ;
	private String m_userXmlPath;
	
	private DomDocument m_documnet ;
	private DomNode m_node;
	private final static String SUBJECTS_XPATH  = CommonDef.CONTANIER_PATH + "/ul";
	private final static String SUBJECT_NAME_XPATH = "div/h3/a";
	
	
		
	public UserCrawler(String userName)
	{
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
	}
	
	@Override
	public IElement Crawl() 
	{
		return Crawl(CommonDef.PINTERSET_URL + m_userName+ "");		
	}
	
	
	@Override
	public IElement Crawl(String userUrl) {

		m_userUrl = userUrl;
		NodeList itemsNodes;
		try {
			
				if (DownloadFile(m_userXmlPath, m_userUrl))
				{
					m_documnet = new DomDocument(m_userXmlPath);
					Node node =  m_documnet.GetNode(SUBJECTS_XPATH);
					m_node = new DomNode(node);
					NodeList allSubjects =  node.getChildNodes();/* m_node.GetNodeList("//li") */;
					for(int i =0 ; i<allSubjects.getLength() ;++i)
					{
						
						Node n =  allSubjects.item(i);
						if (n.getNodeType() == node.ELEMENT_NODE)
						{
							String subjectName = m_node.GetNode(SUBJECT_NAME_XPATH,n).getTextContent().replace(' ', '_');
							FileServices.CreateFolder(GetClassName(), m_userPath+"//"+ subjectName);
							
						}
					}
				
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


	@Override
	public boolean CreateResultsPool(String Path) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	
	
}
