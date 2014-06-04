package infobeadCollection;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class MusicSelector 
{
	
	
	private static String songlistfilepath="src/infobeadCollection/song _list.txt";
	public MusicSelector()
	{
		File f = new File(songlistfilepath);
		if(f.exists() && !f.isDirectory()) 
		{
			try 
			{
				String sCurrentLine;
				BufferedReader reader =	 new BufferedReader(new FileReader(songlistfilepath));
				while ((sCurrentLine = reader.readLine()) != null) 
				{
					if (!sCurrentLine.isEmpty())
					{
						//System.out.println(sCurrentLine);
						String[] songdetails= sCurrentLine.split(";");
						m_songlist.put(songdetails[0], songdetails[1]);
						//System.out.println("songdetails[0]= "+songdetails[0]+"songdetails[1] ="+songdetails[1]);
					
					}
				}
			}
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				System.out.println("some kind of problem"+e.getMessage());
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("song list file didnt found");
		}
		
	}
	
	public String ChooseSong(int age)
	{
		int maxTries=m_songlist.size();
		int count=0;
		while(count<maxTries){
			count++;
			
			List<String> keys      = new ArrayList(m_songlist.keySet());
			String       randomKey = keys.get( new Random().nextInt(keys.size()) );
			String[] agerange= randomKey.split("-");
			if (agerange.length>0)
			{
				if (age > Integer.parseInt(agerange[0]) && age< Integer.parseInt(agerange[1]))
				{
					return m_songlist.get(randomKey);
				}
			}
			
			
		}
		return "";
	}
	
	private HashMap<String,String> m_songlist= new HashMap<String,String>() ;
	
	
	public static void main(String[] args) 
	{
		MusicSelector ms=new MusicSelector();
		System.out.println("choose age 55 "+ms.ChooseSong(55));
		}
	
}
