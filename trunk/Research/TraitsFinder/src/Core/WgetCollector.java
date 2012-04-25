package Core;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;

import Interfaces.ICollector;
import Services.Logger;

public class WgetCollector extends BaseCFinder implements ICollector {

	
	public  WgetCollector(String name)
	{
		super(name);
	}
	
	public WgetCollector()
	{
		super();
	}
	
	
	@Override
	public boolean SaveDataFile(String PathToSave,String address) {
	
		FileWriter outFile = null;
		PrintWriter out = null; 
		String s;
		try 
		{
			outFile = new FileWriter(PathToSave);
			out = new PrintWriter(outFile);
			BufferedReader r = new BufferedReader(new InputStreamReader(new URL(address).openStream()));
			while ((s = r.readLine())!= null)
			{
				s=s.replace(">", ">\n");
				out.append(s+"\n");
			}
			out.close();
			
		}
		catch (Exception e)
		{
			Logger.GetLogger().WriteLine(m_Name,"Error "+e.toString());
			if(out != null)
			out.close();
			return false;
		}
		return true;
	}

}
