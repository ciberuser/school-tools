package Core;


import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URL;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.PrettyXmlSerializer;
import org.htmlcleaner.TagNode;

import Interfaces.ICollector;
import Services.Logger;



public class WgetCollector extends BaseCFinder implements ICollector {

	
	public  WgetCollector(String name)
	{
		super(name);
	}
	
	public WgetCollector()
	{
		super("WgetCollector");
	}
	
	
	@Override
	public boolean SaveDataFile(String PathToSave,String address) {
	
		Logger.GetLogger().WriteLine(m_Name, " save url "+ address+" to clean file, file path : "+ PathToSave +" ....");
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
			Logger.GetLogger().WriteLine(m_Name,"done saving file!! save on : "+PathToSave);
			return true;
		} 
		catch (Exception e) {
			Logger.GetLogger().WriteLine(m_Name,"Error "+e.toString());
			Logger.GetLogger().WriteLine(m_Name, e.getCause().toString());
			return false;
		}
		 
						
	}
	

	

	
}
