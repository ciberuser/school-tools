package Core;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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
	private final static  String SUBJECTS_PATH  = CommonDef.CONTANIER_PATH +"/ul[@class='sortable']";
	
	
		
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
		return Crawl(CommonDef.PINTERSET_URL + "//" + m_userName);		
	}
	
	
	@Override
	public IElement Crawl(String userUrl) {

		m_userUrl = userUrl;
		try {
			
				if (DownloadFile(m_userXmlPath, m_userUrl))
				{
					m_documnet = new DomDocument(m_userXmlPath);
					NodeList itemsNodes =  m_documnet .GetNodes(SUBJECTS_PATH);
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
