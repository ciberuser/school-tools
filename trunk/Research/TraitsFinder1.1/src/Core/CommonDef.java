package Core;

public class CommonDef 
{
	public final static String ROOT_DATA_FOLDER = "data/";
	public final static String USERS_FOLDER_POOL_PATH = ROOT_DATA_FOLDER + "users";
	
	public final static String BIN_PATH = "bin/";
	public final static String REDIS_PATH = BIN_PATH + "redis/";
	
	public final static String CONTANIER_XPATH = "//body/div[@id='wrapper']/div[@id='ColumnContainer']";
	public final static String EMPTY="";
	public final static String PINTERSET_URL ="http://pinterest.com/";
	public final static String PINTERSET_XML = "pinterest_main.xml";
	
	public final static String FOLLOWER_FILE_NAME ="followers.xml";
	
	public final static String GRAPH_DB_DIR = ROOT_DATA_FOLDER + "DB_DIR/";
	
	public final static String NEO_WEIGHT = "weight";
	
	//DB logic:
	
	public final static long NOT_EXIST_IN_DB = -1;
	
	public final static int NOT_EXIST = -1;
	
	public final static long MAX_CRAWLING_USER = 150;
	
	public static String AlignUserName(String userName)
	{
		return userName.replace(" ", "_");
	}
	
	public static String AlignSubjectURL(String subjectName)
	{
		return  subjectName.replace("_", "-").replace("'", "-").replace("&", "").replace("--", "-").replace(".", "");
	}
	
	public static String AlignSubjectName(String subjectName)
	{
		return subjectName.replace("-", "_");
	}
}
