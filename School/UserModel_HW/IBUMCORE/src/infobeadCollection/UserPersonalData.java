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
	public enum ELang{Hebrew,English,Russian,Arabic}
	
	
		
	public UserPersonalData()
	{
		GeneratePersonalty();
	}
	
	public void GeneratePersonalty()
	{
		int pick = new Random().nextInt(EmusicType.values().length);
	    m_music= EmusicType.values()[pick];
	    m_age = new Random().nextInt(60);
	    pick = new Random().nextInt(ELang.values().length);
	    m_language =ELang.values()[pick];
	}
	
	public String GetLanguage()
	{
		return m_language.toString();
	}
	private int m_age;
	
	private ELang m_language;
	public int getAge() {
		return m_age;
	}

	private EmusicType m_music;
	
	
	public String getMusicType() 
	{
		return m_music.toString();
	}
	
	
}
