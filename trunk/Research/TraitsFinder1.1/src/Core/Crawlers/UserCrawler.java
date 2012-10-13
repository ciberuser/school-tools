package Core.Crawlers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//import javax.lang.model.element.Element;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;


import Core.CommonDef;
import Core.Interfaces.ICrawler;
import Elements.IElement;
import Elements.StringDataElement;
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
	private final static String SUBJECTS_XPATH  = CommonDef.CONTANIER_XPATH + "/ul";
	private final static String SUBJECT_NAME_XPATH = "div/h3/a";
	
	private IElement m_element;
	private List<String> m_subjects;
	
		
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
	}
	
	@Override
	public IElement Crawl() 
	{
		return Crawl(CommonDef.PINTERSET_URL + m_userName+ "");		
	}
	
	protected IElement Crawl(String userUrl) {

		m_userUrl = userUrl;
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
							m_subjects.add(subjectName);
						}
					}
					if (CreateResultsPool(m_userPath)) return m_element;
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
	public boolean CreateResultsPool(String Path) 
	{
		m_element = new StringDataElement();
		m_element.SetName(m_userName);
		if (FileServices.PathExist(Path) && !m_subjects.isEmpty())
		{
			for (String subjectName : m_subjects)
			{
				//FileServices.CreateFolder(GetClassName(), Path+"//"+ subjectName);
				ICrawler subjectCrawler = new SubjectsCrawler(m_userName, subjectName);
				IElement subjectElem = subjectCrawler.Crawl();
				m_element.AddElement(subjectElem);
				
			}
			return true;
		}
		return false;
	}
	
	
	
	
	
}
