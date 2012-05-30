package Services;

import java.io.File;

import javax.swing.text.StyledEditorKit.BoldAction;

import Core.CommonDef;

public class FileServices 
{
	public static boolean Delete(String module,String path)
	{
		File fileElm = new File(path);
		if (fileElm.exists())
		{
			fileElm.delete();
			WriteLineToLog(module, "the path " + path+" have been deleted");
			return true;
		}
		WriteLineToLog(module, "failed to delete " +path +" path not exist ");
		return false;
	}
	
	public static  boolean CreateFolder(String module, String Path)
	{
		try
		{
			   // Create one directory
			  boolean success = (
			  new File(Path)).mkdir();
			  if (success) 
			  {
				  WriteLineToLog(module, "directory has created at "+Path);
				  return true;
			  }
			  else
			  {
				  WriteLineToLog(module,"failed to create directory :  " + Path); 
				return false;
			  }
			
		}
		catch (Exception e)
		{
			//Catch exception if any
			WriteLineToLog(module ,"error when creating directory Error: " +e.getMessage()); 
			return false;
		}
		
	  	
	}
	
	public static boolean PathExist(String path)
	{
		return new File(path).exists();
	}
	
	private static void WriteLineToLog(String module, String msg)
	{
		Logger.GetLogger().WriteLine(module,msg);
	}

}
