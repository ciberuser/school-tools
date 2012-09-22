package Core;

import Core.Interfaces.ICrawler;
import Elements.Interfaces.IElement;

public class SubjectsCrawler extends ACrawler implements ICrawler
{

	private String m_subjectName;
	private String m_subjectPath;
	private String m_userPath;
	private String m_subjectXmlPath;
	private String m_subjectURL ;
	
	
	
	public SubjectsCrawler(String userName ,String subjectName)
	{
		m_subjectName = subjectName;
		m_userPath= CommonDef.ROOT_DATA_FOLDER +"//" +userName;
		m_subjectPath = m_userPath + "//" + m_subjectName;
		m_subjectXmlPath = m_subjectPath +"//" + m_subjectName+".xml";
		
		
	}
	
	@Override
	public IElement Crawl() 
	{
		
		return null;
	}

	@Override
	public IElement Crawl(String subjectName) 
	{
		m_subjectName =subjectName;
		m_subjectURL = CommonDef.PINTERSET_URL +"//" + m_userPath +"//" +subjectName;
		
		
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
