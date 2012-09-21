package Core;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import Core.Interfaces.ICrawler;
import Elements.Interfaces.IElement;
import Services.FileServices;
import Services.Dom.*;
public class UserCrawler extends CommonCFinder  implements ICrawler
{
	private String m_userName;
	
	private WgetCollector m_collector;
	
	private DomDocument m_documnet ;
	private final static  String SUBJECTS_PATH  = CommonDef.CONTANIER_PATH +"/ul[@class='sortable']";
	

		
	public UserCrawler(String userName)
	{
		m_userName = userName;
		Init();
	}


	private void Init()
	{
		m_collector = new WgetCollector();
	}
	
	@Override
	public IElement Crawl() {
		if( m_userName != null || m_userName !="" )
		{
			String userFolder  = CommonDef.USERS_FOLDER_POOL_PATH+"//" +m_userName;
			if (!FileServices.PathExist(userFolder))
			{
				FileServices.CreateFolder(this.GetClassName(), userFolder);
			}
			String userXML =  userFolder +"//" +  m_userName +".xml";
			
			if ( m_collector.SaveDataFile(userXML ,CommonDef.PINTERSET_URL+"//" + m_userName))
			{
				try {
					m_documnet = new DomDocument(userXML);
					NodeList itemsNodes =  m_documnet .GetNodes(SUBJECTS_PATH);
					
				}
				catch (Exception ex)
				{
					WriteLineToLog("Error :" +ex.getMessage());
					WriteLineToLog("stuck trace :" +ex.getStackTrace());
				} 
				
			}
			
			
			

		}
		return null;
		
	}


	@Override
	public IElement Crawl(String item) {
		// TODO Auto-generated method stub
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
