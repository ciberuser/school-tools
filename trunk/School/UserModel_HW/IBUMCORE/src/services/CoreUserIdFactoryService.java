package services;


/**
 * 
 * @author Eyal Dim
 * @version 1.0
 * 
 * Supplies the user ID (for a user model)
 * The ID is a string that starts with "UI"
 * 
 * 
 */
public class CoreUserIdFactoryService {
	
	private static Integer counter = 0;
	private static CoreUserIdFactoryService instance = new CoreUserIdFactoryService();
	
	
	public static CoreUserIdFactoryService getInstance() {
		return instance;
	}
	
    public void resetUserIdCount() {
    	counter = 0;
    }
    
    public void setUserIdCount(Integer count) {
     	counter = count;
    }
    
    
    public String nextUserIdCount(){
    	counter += 1;
    	return ("UI" + counter.toString());
    }
    
 
 }
