package Core;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;

import Interfaces.ICollector;

public class WgetCollector implements ICollector{

	@Override
	public boolean SaveDataFile(String PathToSave,String data) {
	
		FileWriter outFile = null;
		PrintWriter out = null; 
		String s;
		try 
		{
			outFile = new FileWriter(PathToSave);
			out = new PrintWriter(outFile);
			BufferedReader r = new BufferedReader(new InputStreamReader(new URL(data).openStream()));
			while ((s = r.readLine())!= null)
			{
				out.append(s);
			}
			out.close();
			
		} catch (Exception e) {
			if(out != null)
			out.close();
			return false;
		}
		return true;
	}

}
