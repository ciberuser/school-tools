package Core;

public class PinterestContext extends CoreContext
{
	
	
		
	public final static String USERS_FOLDER_POOL_PATH = ROOT_DATA_FOLDER + "users";
	public final static String BIN_PATH = "bin/";
	public final static String CONTANIER_XPATH = "//body/div[@id='wrapper']/div[@id='ColumnContainer']";
	
	public final static String PINTERSET_URL ="http://pinterest.com/";
	public final static String PINTERSET_XML = "pinterest_main.xml";
	public final static String FOLLOWER_FILE_NAME ="followers.xml";
	

	public PinterestContext()
	{
		OFF_LINE_PATH = USERS_FOLDER_POOL_PATH;
	}
	
	public static ACrawlerProcessor GetProcessor()
	{
		if (m_processor == null) m_processor = new PinterestCrawlersProcessor();
		return m_processor;
	}
	
	public static String AlignUserName(String userName)
	{
		return userName.replace(" ", "_");
	}
	
	public static String AlignSubjectURL(String subjectName)
	{
		return  subjectName.replace("_", "-").replace("'", "-").replace("&", "").replace("--", "-").replace(".", "").replace("?", "");
	}
	
	public static String AlignSubjectName(String subjectName)
	{
		return subjectName.replace("-", "_").toLowerCase().replace("?", "").replace("/", "").replace("\\","").replace("|", "").replace('"', '_').replace("'", "").replace("<", "").replace(">", "").replace("(", "").replace(")","").replace(":", "").replace("*", "").replace("..", "");
	}
	
	
}
