package architectureUtil;

import editor.EditorMain;

/**
 * Sets up file system directories' paths
 * @author Eyal Dim
 *
 */
public class DirectoriesSetup {

	static String toRoot ="../";
	
	
	private final static String ECLIPSEWORKINGDIRPATH =DirectoriesSetup.class.getProtectionDomain().getCodeSource().getLocation().getPath()+toRoot; /*"D:\\oldwh\\PhD\\PHD2\\eclipse workspace 7Jun2013\\IBUMCORE"*/;
    private final static String ECLIPSEWORKINGDIRPATHURL = DirectoriesSetup.class.getProtectionDomain().getCodeSource().getLocation().toString()+toRoot; //"file://D:/oldwh/PhD/PHD2/eclipse%20workspace%207Jun2013/IBUMCORE";
    
    
	private final static String INFOBEADSPATH = ECLIPSEWORKINGDIRPATH + "\\src\\infobeadCollection\\";
	private final static String METADATAPATH = ECLIPSEWORKINGDIRPATH + "\\elements\\infobeadMetadata\\";
	private final static String HELPATH = ECLIPSEWORKINGDIRPATH + "\\elements\\help\\";
	private final static String USERMODELSPATH = ECLIPSEWORKINGDIRPATH + "\\elements\\userModels\\";
	private final static String GROUPMODELSPATH = ECLIPSEWORKINGDIRPATH + "\\elements\\groupModels\\";
	private final static String INFOPENDANTSPATH = ECLIPSEWORKINGDIRPATH + "\\elements\\infoPendants\\";
	private final static String ACTIVATEDPATH = ECLIPSEWORKINGDIRPATH + "\\elements\\activatedModels\\";
	private final static String READYMODELSPATH = ECLIPSEWORKINGDIRPATH + "\\src\\models\\";
	private final static String OTHERFILESPATH = ECLIPSEWORKINGDIRPATH + "\\elements\\otherFiles\\";
	
	
   private final static String INFOBEADSBINURLPATH = ECLIPSEWORKINGDIRPATHURL + "/bin/infobeadCollection/";
   private final static String INFOBEADSSRCURLPATH = ECLIPSEWORKINGDIRPATHURL + "/src/infobeadCollection/";
   private final static String HMPSBINURLPATH = ECLIPSEWORKINGDIRPATHURL + "/bin/hmps/sensor/";
   private final static String ACTIVATEDMODEL1URL = ECLIPSEWORKINGDIRPATHURL + "/bin/infobeadCollection/";
	
	public String getEclipseworkingdirpath() {
		return ECLIPSEWORKINGDIRPATH;
	}
	public String getInfobeadsPath() {
		return INFOBEADSPATH;
	}

	public String getMetadataPath() {
		return METADATAPATH;
	}
	public String getHelpPath() {
		return HELPATH;
	}
	public String getUserModelsPath() {
		return USERMODELSPATH;
	}
	public String getGroupModelsPath() {
		return GROUPMODELSPATH;
	}
	public String getInfopendantspath() {
		return INFOPENDANTSPATH;
	}
	public String getReadyModelsPath() {
		return READYMODELSPATH;
	}
	public String getInfobeadsBinUrlPath() {
		return INFOBEADSBINURLPATH;
	}
	public String getInfobeadsSrcUrlPath() {
		return INFOBEADSSRCURLPATH;
	}
	public static  String getOtherFilesPath() {
		return OTHERFILESPATH;
	}
	public String getHmpsBinUrlPath() {
		return HMPSBINURLPATH;
	}

	public String getActivatedModelsPath() {
		return ACTIVATEDPATH;
	}
	public String getActivatedmodel1url() {
		return ACTIVATEDMODEL1URL;
	}

	
}
