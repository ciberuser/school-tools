package App;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.neo4j.kernel.impl.nioneo.xa.WriteTransaction;

import Services.FileServices;
import Services.Log.*;
import Services.Neo4J.Neo4JActivation;
import Core.CommonCBase;
import Core.CommonDef;
import Core.CrawlerProccessor;
import Core.CrawlerRunner;
import Core.ECrawlingType;
import Core.QueueCrawlinTargets;
import Core.Crawlers.OffLineUsersCrawler;
import Core.Crawlers.PintersetCrawler;
import Core.Interfaces.ICrawler;
import Elements.IElement;


public class AppRunner extends CommonCBase {
	
	//commands 
	private final static String FLAG_DEBUG 		= "debug";
	private final static String FLAG_RUNNERS 	= "runners";
	private final static String FLAG_MAX_USERS  = "users";
	private final static String FLAG_HELP 		= "help";
	private final static String FLAG_GRAPH		= "graphPath";
	private final static String FLAG_OFF_LINE 	= "offline";
		
	private final static String APP_NAME = "Traits Finder CLI"; 
	private final static String CLI_VERSION ="1.0.0.2";

	private static void  Init()
	{
		
		CleanRun();
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
		options.addOption(OptionBuilder.withArgName("number of user to crawl").hasArg().withDescription("maximum number of users to crawl").create(FLAG_MAX_USERS));
		options.addOption(OptionBuilder.withArgName("graph folder path").hasArg().withDescription("save data to graph : set the graph path ").create(FLAG_GRAPH));
		options.addOption(FLAG_HELP,false,"show help");
		options.addOption(OptionBuilder.withArgName("local folder path").hasArg().withDescription("activate crawler from local folder").create(FLAG_OFF_LINE));
		return options;
	}
	
	private static void PrintHelp(Options options)
	{
		HelpFormatter hf = new HelpFormatter();
		hf.printHelp(APP_NAME, options);
	}
	private static void PrintLine()
	{
		for(int i=0;i<65;i++) System.out.print("=");
	}
	
	private static void PrintHead()
	{
		System.out.println("\n"+APP_NAME);
		PrintLine();			
		System.out.println("\nCLI version="+CLI_VERSION +" ,core version=" + CommonDef.CORE_VERSION);
		PrintLine();
		System.out.println("");
	}
	
		
	private static void CleanRun()
	{
		ThreadGroup runnerGroup = CrawlerRunner.currentThread( ).getThreadGroup( );
		if (runnerGroup.activeCount()>1) runnerGroup.destroy();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) //TODO:: to refactor!!! 
	{
		Init();
		long maxUser=CommonDef.MAX_CRAWLING_USER;
		Options options = InitOptions();
		String offLineDirPath ="";
		CommandLineParser parser  =new PosixParser();
		PrintHead();
		
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
	        	PrintHelp(options);
	        	return ;
	        }
	        if (line.hasOption(FLAG_GRAPH))
	        {
	        	String graphPath = line.getOptionValue(FLAG_GRAPH);
	        	CommonDef.GRAPH_DB_DIR = graphPath;
	        	CommonDef.SET_GRAPH = true;
	        	Neo4JActivation.Start(CommonDef.GRAPH_DB_DIR);     	
	        }
	        
	        if (line.hasOption(FLAG_OFF_LINE))
	        {
	        	CommonDef.OFF_LINE_MODE = true;
	        	
	        	offLineDirPath = line.getOptionValue(FLAG_OFF_LINE);
	        }
	        
	    }
	    catch( ParseException exp ) {
	        // oops, something went wrong
	    	System.out.println("ERROR !!! : Wrong command or bad syntax\nmsg=" +exp.getMessage() );
	    	PrintHelp(options);
	    	
	    	return ;
	    }

		
		IElement mainElement  = null;
		System.out.print("start crawling main page...");
		boolean depMain = CrawlerProccessor.GetInstance().GetDepthCrawling(ECrawlingType.Main);
		mainElement = (!CommonDef.OFF_LINE_MODE)? new PintersetCrawler().Crawl(depMain) : new OffLineUsersCrawler(offLineDirPath).Crawl(depMain) ;
		if (CommonDef.OFF_LINE_MODE) 
		{
			maxUser = QueueCrawlinTargets.GetInstance().NumbertOfTargets();
		}
		System.out.print("done!\n");
		
		if (mainElement !=null)
		{
			CrawlerProccessor.GetInstance().ExcuteCrawler(mainElement, maxUser);
		}
		else 
		{
			System.out.println("Error !!!! end run failed to create main element!");
		}
		//System.out.println("done!!! check result");

	}

}