package services;


/**
 * 
 * @author Eyal Dim
 * @version 1.0
 * 
 * Supplies the user ID (for a user model)
 * The ID is a string that starts with "OI"
 * 
 * 
 */
public class CoreOwnerIdFactoryService {
	
	private static Integer counter = 0;
	private static CoreOwnerIdFactoryService instance = new CoreOwnerIdFactoryService();
	
	
	public static CoreOwnerIdFactoryService getInstance() {
		return instance;
	}
	
    public void resetOwnerIdCount() {
    	counter = 0;
    }
    
    public void setOwnerIdCount(Integer count) {
     	counter = count;
    }
    
    
    public String nextOwnerIdCount(){
    	counter += 1;
    	return ("OI" + counter.toString());
    }
    
 
 }
