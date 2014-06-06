package infobeadCollection;

import java.util.Random;

public class UserPersonalData 
{
	
	public enum EmusicType
	{
		Rock,
		Pop,
		Rap,
		Dance,
		classic
	
	}
		
	public UserPersonalData()
	{
		GeneratePersonalty();
	}
	
	public void GeneratePersonalty()
	{
		int pick = new Random().nextInt(EmusicType.values().length);
	    m_music= EmusicType.values()[pick];
	    m_age = new Random().nextInt(60);
	}
	
	private int m_age;
	
	public int getAge() {
		return m_age;
	}

	private EmusicType m_music;

	public String getMusicType() 
	{
		return m_music.toString();
	}
	
	
}
