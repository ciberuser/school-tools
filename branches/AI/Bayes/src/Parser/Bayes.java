package Parser;

import network.*;

import java.util.Scanner;
import java.io.*;
import java.util.regex.*;


public class Bayes extends BasePareser
{

	/**
	 * @param args
	 */
	
	
	public static void main(String[] args)
	{
		System.out.println("please choose action :\nLoad <network file> - load bayes net\nRunQueries " +
				"<queries file> - run file with Queris\nexit- exit from program");
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		
		try
		{
			String line;
			NetLoader lb;
		
			while (( line = stdin.readLine() ) != null/* && line.length()!=0*/ )
			{
				Scanner scanner = new Scanner(line);
				if (scanner.hasNext(EXIT)) break ;
				if (scanner.hasNext(LOAD)) 
					{
					try
					{
						lb = new NetLoader(line); 
					}
					catch (Exception ex)
					{
						System.out.println(ex.getMessage());
					}
					}
				if (scanner.hasNext(RUN_QUERIES))
				{
					
				}
			
			
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub

	}

}
