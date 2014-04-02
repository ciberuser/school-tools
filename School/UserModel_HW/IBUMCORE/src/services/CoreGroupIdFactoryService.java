package services;


/**
 * 
 * @author Eyal Dim
 * @version 1.0
 * 
 * supplies the group ID (for a group model)
 * The group ID is a string that starts with GI
 * 
 */
public class CoreGroupIdFactoryService {
	
	private static Integer counter = 0;
	private static CoreGroupIdFactoryService instance = new CoreGroupIdFactoryService();
	
	
	public static CoreGroupIdFactoryService getInstance() {
		return instance;
	}
	
    public void resetGroupIdCount() {
    	counter = 0;
    }
    
    public void setGroupIdCount(Integer count) {
     	counter = count;
    }
    
    
    public String nextGroupIdCount(){
    	counter += 1;
    	return ("GI" + counter.toString());
    }
    
 
 }
