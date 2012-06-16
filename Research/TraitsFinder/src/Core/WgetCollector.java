package Core;


import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URL;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.PrettyXmlSerializer;
import org.htmlcleaner.TagNode;

import Services.ICollector;
import Services.Logger;



public class WgetCollector extends BaseCFinder implements ICollector {

	

	
	public WgetCollector()
	{
		
	}
	
	
	@Override
	public boolean SaveDataFile(String PathToSave,String address) {
	
		WriteLineToLog(" save url "+ address+" to clean file, file path : "+ PathToSave +" ....");
		//Logger.GetLogger().WriteLine(m_Name, " save url "+ address+" to clean file, file path : "+ PathToSave +" ....");
		CleanerProperties props = new CleanerProperties();
		 
		// set some properties to non-default values
		props.setTranslateSpecialEntities(true);
		props.setTransResCharsToNCR(true);
		props.setOmitComments(true);
		 
		// do parsing
		TagNode tagNode;
		try
		{
			tagNode = new HtmlCleaner(props).clean( new URL(address));
			// serialize to xml file
			new PrettyXmlSerializer(props).writeToFile(
				    tagNode, PathToSave, "utf-8"
				);
			WriteLineToLog("done saving file!! save on : "+PathToSave);
			//Logger.GetLogger().WriteLine(m_Name,"done saving file!! save on : "+PathToSave);
			return true;
		} 
		catch (Exception e) {
			WriteLineToLog("Error "+e.toString());
			WriteLineToLog(e.getCause().toString());
			//Logger.GetLogger().WriteLine(m_Name,"Error "+e.toString());
			//Logger.GetLogger().WriteLine(m_Name, e.getCause().toString());
			return false;
		}
		 
						
	}
	

	

	
}
