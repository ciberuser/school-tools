package App;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

import Services.FileServices;
import Services.Log.*;
import Core.CommonCBase;
import Core.CommonDef;
import Core.CrawlerProccessor;
import Core.ECrawlingType;
import Core.Crawlers.PintersetCrawler;
import Core.Interfaces.ICrawler;
import Elements.IElement;


public class AppRunner extends CommonCBase {
	
	
	private final static String FLAG_DEBUG 		= "d";
	private final static String FLAG_RUNNERS 	= "t";
	private final static String FLAG_MAX_USERS  = "u";
	private final static String FLAG_HELP 		= "help";
	
	
	
	private final static String APP_NAME = "Traits Finder CLI"; 
	
	
	private static void  Init()
	{
		if (!FileServices.PathExist(CommonDef.ROOT_DATA_FOLDER))
		{
			FileServices.CreateFolder(APP_NAME, CommonDef.ROOT_DATA_FOLDER);
		}
		if (!FileServices.PathExist(CommonDef.USERS_FOLDER_POOL_PATH)) FileServices.CreateFolder(APP_NAME,CommonDef.USERS_FOLDER_POOL_PATH);
		if (!FileServices.PathExist(CommonDef.GRAPH_DB_DIR) )FileServices.CreateFolder(APP_NAME, CommonDef.GRAPH_DB_DIR);
		Logger.SetPrintLevel(ELogLevel.WARNING);
	}
	
	private static Options InitOptions()
	{
		Options options = new Options();
		options.addOption(OptionBuilder.withArgName(FLAG_RUNNERS).hasArg().withDescription("number of runners").create(FLAG_RUNNERS));
		options.addOption(FLAG_DEBUG,false,"set debug node - will increase loglevel to information");
		options.addOption(OptionBuilder.withArgName(FLAG_MAX_USERS).hasArg().withDescription("maximum number of users to crawl").create(FLAG_MAX_USERS));
		return options;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Init();
		long maxUser=CommonDef.MAX_CRAWLING_USER;
		Options options = InitOptions();
		HelpFormatter hf = new HelpFormatter();
		CommandLineParser parser  =new PosixParser();
		
		try {
	        // parse the command line arguments
	        CommandLine line = parser.parse( options, args );
	        if (line.hasOption(FLAG_DEBUG)) 
	        {
	        	CommonDef.LOGGER_LEVEL = ELogLevel.INFORMATION;
	        }
	        
	        if (line.hasOption(FLAG_RUNNERS))
	        {
	        	int maxRunner = Integer.parseInt(line.getOptionValue(FLAG_RUNNERS));
	        	CommonDef.MAX_RUNNERS = maxRunner;
	        }
	        if (line.hasOption(FLAG_MAX_USERS))
	        {
	        	maxUser = Long.parseLong(line.getOptionValue(FLAG_MAX_USERS));
	        	CommonDef.MAX_CRAWLING_USER = maxUser;
	        }
	        if (line.hasOption(FLAG_HELP))
	        {
	        	hf.printHelp(APP_NAME, options);
	        }
	        
	    }
	    catch( ParseException exp ) {
	        // oops, something went wrong
	    	System.out.println("worng command or argument's \n");
	    	hf.printHelp(APP_NAME, options);
	    	return ;
	    }

		
		IElement mainElement  = null;
		ICrawler pinterserCrawler = new PintersetCrawler();
		System.out.println("start crawling main page...");
		mainElement =  pinterserCrawler.Crawl(CrawlerProccessor.GetInstance().GetDepthCrawling(ECrawlingType.Main));
		if (mainElement !=null)
		{
			CrawlerProccessor.GetInstance().ExcuteCrawler(mainElement, maxUser);
		
		}
		

	}

}
