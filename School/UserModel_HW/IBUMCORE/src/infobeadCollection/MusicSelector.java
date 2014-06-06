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
	
	private static final int INDEX_TYPE	= 1;
	private static final int INDEX_AGE = 0;
	private static final int INDEX_SONG = 2;
	
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
						String Type = songdetails[INDEX_TYPE];
						if (!m_songlist.containsKey(Type))
						{
							m_songlist.put(Type, new HashMap<String,String>());					
						}
						m_songlist.get(Type).put(songdetails[INDEX_AGE],songdetails[INDEX_SONG] );
						
			
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
	
	public String ChooseSong(int age,String type)
	{
		int maxTries=m_songlist.size();
		int count=0;
		if(!m_songlist.containsKey(type)) return "this type is not in playlist";
		while(count<maxTries){
			count++;
			
			List<String> keys      = new ArrayList(m_songlist.get(type).keySet());
			String       randomKey = keys.get( new Random().nextInt(keys.size()) );
			String[] agerange= randomKey.split("-");
			if (agerange.length>0)
			{
				if (age > Integer.parseInt(agerange[0]) && age< Integer.parseInt(agerange[1]))
				{
					return m_songlist.get(type).get(randomKey);
				}
			}
			
			
		}
		return "";
	}
	
	private HashMap<String,HashMap<String,String>> m_songlist= new HashMap<String,HashMap<String,String>>() ;
	
	
	public static void main(String[] args) 
	{
		MusicSelector ms=new MusicSelector();
		System.out.println("choose age 55 "+ms.ChooseSong(15,"Dance"));
		}
	
}
