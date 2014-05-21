package infobeadCollection;

import javax.swing.DropMode;

public class userPreferences {
	
	
	
	public userPreferences ()
	{
		m_milk =EmilkPrefs.None;

		m_temp=EdrinkTemp.None;
	}
	
	
	void SetWhatToDrink (EdrinkPrefs type ,EdrinkTemp temp,EmilkPrefs milk)
	{
		m_drink =type;
		m_temp =temp;
		m_milk = milk;
	}
		
	public enum EdrinkPrefs
	{
		Tea ,
		Coffee;
		
		 public static EdrinkPrefs getRandom() {
	        return values()[(int) (Math.random() * values().length)];
	    }
	}
	
	public enum cupSize
	{
		small, 
		medium,
		large,		
	}
	
	public enum EdrinkTemp{
		Hot,
		Cold,
		None,
		
	}
	
	public enum coffeeBlend
	{
		brazilian, 
		colombian, 
		african, 
	}
	
	
	public enum EmilkPrefs
	{
		Normal,
		Soy,
		None
	}
	

	
	
	public EdrinkPrefs getDrink() {
		return m_drink;
	}

	public EmilkPrefs getMilkType() {
		return m_milk;
	}


	public EdrinkTemp getTempatore() {
		return m_temp;
	}
	
	
	public String IWant2Buy()
	{
		String  outMsg ="";

		outMsg+="i want " + m_temp.toString() +" "+ m_drink.toString() +" with " + m_milk.toString() +"\n" ;

		return outMsg;
	}
	

	private EmilkPrefs m_milk;
	private EdrinkTemp m_temp;
	private EdrinkPrefs m_drink;

}
