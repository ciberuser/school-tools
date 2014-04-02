package editor.factory;

import editor.schema.GroupModel;
import editor.schema.UserModel;


/**
 * Manages models, allows more than one user model in a group model
 * @author Eyal
 *
 */
public class ModelFactory {
	
	private static int counter = 0;
	private static ModelFactory instance = new ModelFactory();
	
	private ModelFactory(){
	}
	
	public static ModelFactory getInstance() {
		return instance;
	}
	
    public void resetUMCount() {
    	counter = 0;
    }
    
    public void setUMCount( int count){
    	counter = count;
    }
   
    
    public  Integer getCounterForNewModel(){
    	
    	Integer returnValue = counter;
    	
    	counter += 1;
    	    	
    	return returnValue;
    }
    
    
    public UserModel createUserModel(){
    	
		UserModel userModel = new UserModel();
		
		return userModel;
	}
    
    public GroupModel createGroupModel(){
    	
    	GroupModel groupModel = new GroupModel();
  		
  		return groupModel;
  	}

}
