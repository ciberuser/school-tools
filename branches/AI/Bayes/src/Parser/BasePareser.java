package Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BasePareser 
{
	protected static final String EXIT = "[Ee][Xx][Ii][tT]";
	protected static final String LOAD = "[Ll][oO][Aa][Dd][ ]*[A-Za-z.-]*";
	protected static final String RUN_QUERIES = "[R][r]un[Q][q]ueries[ ]*[A-Za-z.-]*";
	
	
	protected String ReadFile(String filePath)
	{
		File netFile = new File(filePath);
		if (!netFile.exists()) return null;
		else
		{
			try
			{
				Scanner scan = new Scanner(netFile);
				scan.useDelimiter("\\Z");
				return scan.next();
			}
			catch (FileNotFoundException e)
			{
				return null;
			}
		
		}
	}
	
}
