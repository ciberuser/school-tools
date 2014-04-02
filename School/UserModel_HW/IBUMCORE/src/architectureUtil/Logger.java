package architectureUtil;

import java.io.*;
import java.util.*;
import java.text.*;

/**
 * 
 * 
 * Writes message to log
 * @author Eyal Dim
 * @version 1.0
 *
 */

public class Logger {

	protected static String defaultLogFile = "IBUMlog.txt";
	protected static SimpleDateFormat df = new SimpleDateFormat ("yyyy.MM.dd hh:mm:ss.SSS  ");

    

	/**
	 * Prints a message to the default log file
	 * @param s the message string
	 */
	
	public static void writeToLogFile(String s) throws IOException {
    	try{
    		Date now = new Date();
		    FileWriter aWriter = new FileWriter(defaultLogFile, true);
		    aWriter.write(df.format(now) + ": " + s + "   \n");
		    aWriter.flush();
		    aWriter.close();
    	}
    	catch (IOException e2) {
			e2.printStackTrace();
		}
    }

}
