package App;

	import java.io.ObjectInputStream.GetField;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
//import org.neo4j.kernel.impl.nioneo.xa.WriteTransaction;

import Services.FileServices;
import Services.Log.*;
import Services.Neo4J.Neo4JActivation;
import Core.CommonCBase;
import Core.CoreContext;

import Core.CrawlerRunner;
import Core.EPinterestCrawlingType;
import Core.PinterestContext;
import Core.QueueCrawlinTargets;
import Core.Crawlers.OffLineUsersCrawler;
import Core.Crawlers.PintersetCrawler;
import Core.Serialization.ESerializerType;
import Core.Serialization.IElementSerializer;
import Core.Serialization.SerializerFactory;

import Elements.IElement;


public class AppRunner extends CommonCBase {
	
	//commands 
	private final static String FLAG_DEBUG 		= "debug";
	private final static String FLAG_VERBOS		= "verbos";
	private final static String FLAG_RUNNERS 	= "runners";
	private final static String FLAG_MAX_USERS  = "users";
	private final static String FLAG_HELP 		= "help";
	private final static String FLAG_GRAPH		= "graphPath";
	private final static String FLAG_OFF_LINE 	= "offline";
	private final static String FLAG_MEMORY_GRAPH    ="memoryGraph";
	private final static String FLAG_LOAD_ELM		 ="loadElm";	
		
	private final static String APP_NAME = "Traits Finder Research tool"; 
	private final static String CLI_VERSION ="1.0.2.0";

	private static boolean DESERIALIZE_MODE = false;
	
	//private StatisticsDumper m_statDumper = new StatisticsDumper(CoreContext.ROOT_DATA_FOLDER);
	private static boolean b_help;
	
	
	
		
	private static void  Init()
	{
		
		Logger.GetLogger().SetPrintLevel(Core.CoreContext.LOGGER_LEVEL);
		CleanRun();
		if (!FileServices.PathExist(CoreContext.ROOT_DATA_FOLDER))
		{
			FileServices.CreateFolder(APP_NAME, CoreContext.ROOT_DATA_FOLDER);
		}
		if (!FileServices.PathExist(PinterestContext.USERS_FOLDER_POOL_PATH)) FileServices.CreateFolder(APP_NAME,PinterestContext.USERS_FOLDER_POOL_PATH);
		if (!FileServices.PathExist(CoreContext.GRAPH_DB_DIR) )FileServices.CreateFolder(APP_NAME, CoreContext.GRAPH_DB_DIR);
		
		RegisterFinishFunction();
		b_help = false;
	}
	
	private static boolean SaveElement(IElement element)
	{
		if (element != null)
		{
			element.Serialize();
			return true;
		}
		return false;
	}
	
	
	private static Options InitOptions()
	{
		Options options = new Options();
		options.addOption(OptionBuilder.withArgName(FLAG_RUNNERS).hasArg().withDescription("number of runners").create(FLAG_RUNNERS));
		options.addOption(FLAG_DEBUG,false,"set debug mode - will increase loglevel to information");
		options.addOption(FLAG_VERBOS,false,"set to full debug mode. (will show more debug information)");
		options.addOption(OptionBuilder.withArgName("number of user to crawl").hasArg().withDescription("maximum number of users to crawl").create(FLAG_MAX_USERS));
		options.addOption(OptionBuilder.withArgName("graph folder path").hasArg().withDescription("save data to graph : set the graph path ").create(FLAG_GRAPH));
		options.addOption(FLAG_HELP,false,"show help");
		options.addOption(OptionBuilder.withArgName("local users folder path").hasArg().withDescription("activate crawler from local folder").create(FLAG_OFF_LINE));
		options.addOption(OptionBuilder.withArgName("element file (tfe) ").hasArg().withDescription("load element from file file ").create(FLAG_LOAD_ELM));
		options.addOption(FLAG_MEMORY_GRAPH , false ,"graph will cache into memeory");
		return options;
	}
	
	private static void PrintHelp(Options options)
	{
		HelpFormatter hf = new HelpFormatter();
		hf.printHelp(APP_NAME, options);
	}
	
	private static void PrintLine()
	{
		for(int i=0;i<80;i++) System.out.print("=");
	}
	
	private static void PrintHead()
	{
		System.out.println("\n"+APP_NAME);
		PrintLine();			
		System.out.println("\nCLI version="+CLI_VERSION +" ,core version=" + CoreContext.CORE_VERSION +" start time:" +GetTime());
		PrintLine();
		System.out.println("");
	}
	
	private static void RegisterFinishFunction()
	{
		Runtime.getRuntime().addShutdownHook( new Thread()
        {
			@Override
            public void run()
            {
				if (!b_help && !DESERIALIZE_MODE)
				{
					StatisticsDumper dumper = new StatisticsDumper(CoreContext.ROOT_DATA_FOLDER);
					dumper.DumpStatistics();
					if ( !ElementDumper.GetInstance().DumpElemenet())
					{
						System.out.println("fail to dump element !");
					}
					
				}
				System.out.println("PinterestTraitsFinderTool ended endtime = " +GetTime());
            }
        } );
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
		long maxUser=CoreContext.MAX_CRAWLING_USER;
		Options options = InitOptions();
		String offLineDirPath ="";
		CommandLineParser parser = new PosixParser();
		PrintHead();
		
		IElement mainElement  = null;
		String elmFile = null;
		
		try {
	        // parse the command line arguments
	        CommandLine line = parser.parse( options, args );
	        if (line.hasOption(FLAG_DEBUG)) 
	        {
	        	CoreContext.LOGGER_LEVEL = ELogLevel.INFORMATION;
	        	Logger.SetPrintLevel(ELogLevel.INFORMATION);
	        }
	    	        
	        if (line.hasOption(FLAG_VERBOS))
	        {
	        	CoreContext.LOGGER_LEVEL = ELogLevel.VERBOS;
	        	Logger.SetPrintLevel(ELogLevel.VERBOS);
	        }
	        
	        if (line.hasOption(FLAG_RUNNERS))
	        {
	        	int maxRunner = Integer.parseInt(line.getOptionValue(FLAG_RUNNERS));
	        	CoreContext.MAX_RUNNERS = maxRunner;
	        }
	        
	        if (line.hasOption(FLAG_MAX_USERS))
	        {
	        	maxUser = Long.parseLong(line.getOptionValue(FLAG_MAX_USERS));
	        	CoreContext.MAX_CRAWLING_USER = maxUser;
	        }
	        
	        if (line.hasOption(FLAG_MEMORY_GRAPH) && line.hasOption(FLAG_GRAPH))
	        {
	        	CoreContext.DB_MEMORY_MODE = true;
	        }
	        
	        if (line.hasOption(FLAG_HELP))
	        {
	        	PrintHelp(options);
	        	b_help = true;
	        	return ;
	        }
	        if (line.hasOption(FLAG_GRAPH))
	        {
	        	String graphPath = line.getOptionValue(FLAG_GRAPH);
	        	CoreContext.GRAPH_DB_DIR = graphPath;
	        	CoreContext.SET_GRAPH = true;
	        	Neo4JActivation.Start(CoreContext.GRAPH_DB_DIR);     	
	        }
	        
	        if (line.hasOption(FLAG_OFF_LINE))
	        {
	        	CoreContext.OFF_LINE_MODE = true;
	        	offLineDirPath = line.getOptionValue(FLAG_OFF_LINE);
	           	CoreContext.OFF_LINE_PATH = offLineDirPath;
	           	PinterestContext.USERS_FOLDER_POOL_PATH = offLineDirPath;
	        }
	        
	        if (line.hasOption(FLAG_LOAD_ELM))
	        {
	        	elmFile = line.getOptionValue(FLAG_LOAD_ELM);
	        	if (!FileServices.PathExist(elmFile))
	        	{
	        		System.out.println("Error! the file "+ elmFile+" not exist !!! will exit");
	        		return;
	        	}
	        	DESERIALIZE_MODE = true;
	        	System.out.println("deserialize mode!!");
	        }
	    	        
	    }
	    catch( ParseException exp ) {
	        // oops, something went wrong
	    	System.out.println("ERROR !!! : Wrong command or bad syntax\nmsg=" +exp.getMessage() );
	    	PrintHelp(options);
	    	
	    	return ;
	    }
		
				
		if (DESERIALIZE_MODE && CoreContext.OFF_LINE_MODE )
		{
			System.out.println("offline mode can't work with serialize file mode ");
			return ;
		}
		
		boolean depMain = PinterestContext.GetProcessor().GetDepthCrawling(EPinterestCrawlingType.Main);
		if (CoreContext.OFF_LINE_MODE)
		{
			System.out.println("creating user list, searching for user inside " +offLineDirPath + "path ");
			mainElement = new OffLineUsersCrawler(offLineDirPath).Crawl(depMain) ;
		}
		else
		{
			if (DESERIALIZE_MODE)
			{
				if (elmFile!=null)
				{
					System.out.println("deserialize "+elmFile + " file!");
					IElementSerializer elmSer =  SerializerFactory.GetInstance().AllocateSerializer(ESerializerType.eElemnetObj, mainElement, elmFile);
					mainElement = elmSer.Load();
					if (CoreContext.SET_GRAPH == true)
					{
						System.out.println("load to grave is not yet implemented will show all elemenets:");
					}
					int i= 0;
					if (mainElement == null)
					{
						System.out.println("Error !!! failed to load main element !!!");
						return;
					}
					for (IElement elm : mainElement.GetElements())
					{ 
						i++;
						System.out.println(i+ ". "+ elm.GetName());
					}
				}
			}
			else
			{
				System.out.println("start crawling main page...");
				mainElement = new PintersetCrawler().Crawl(depMain);
			}
		}
			
		//mainElement = (!CoreContext.OFF_LINE_MODE)? new PintersetCrawler().Crawl(depMain) : new OffLineUsersCrawler(offLineDirPath).Crawl(depMain) ;
		if (CoreContext.OFF_LINE_MODE) 
		{
			maxUser = QueueCrawlinTargets.GetInstance().NumbertOfTargets();
		}
		System.out.print("done!\n");
		
		if (mainElement !=null && !DESERIALIZE_MODE)
		{
			ElementDumper.GetInstance().SetElemenet(mainElement);
			PinterestContext.GetProcessor().ExcuteCrawler(mainElement, EPinterestCrawlingType.User,  maxUser) ;
		}
		else 
		{
			if (mainElement==null)
			System.out.println("Error !!!! end run failed to create main element!");
		}
		//System.out.println("done!!! check result");
	}
	
	private static  String GetTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		return dateFormat.format(cal.getTime());
	}

}
