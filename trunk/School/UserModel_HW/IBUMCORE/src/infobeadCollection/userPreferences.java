package infobeadCollection;

import javax.swing.DropMode;

public class userPreferences {
	
	
	
	public userPreferences (EdrinkPrefs dpref,EdrinkTemp temp )
	{
		m_drink =dpref;
	}
	
	
	public enum EdrinkPrefs
	{
		eTea,
		eCoffee,
		eNone
	}
	public enum EdrinkTemp{
		eHot,
		eCold,
		eNone
	}
	
	public enum milkPrefs
	{
		
	}
	
	public enum foodPrefs
	{
		
	}
	
	EdrinkPrefs m_drink;

}
