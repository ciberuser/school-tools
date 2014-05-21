package infobeadCollection;

import javax.swing.DropMode;

public class userPreferences {
	
	
	
	public userPreferences ()
	{
		m_milk =EmilkPrefs.None;
		m_drink=EdrinkPrefs.None;
		m_temp=EdrinkTemp.None;
		m_food=EfoodPrefs.None;
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
		Coffee ,
		None
		
		
		
	}
	public enum EdrinkTemp{
		Hot,
		Cold,
		None,
		
	}
	
	
	public enum EmilkPrefs
	{
		Normal,
		Soy,
		None
	}
	
	void SetWhatToEat(EfoodPrefs foodType)
	{
		m_food = foodType;
	}
	
	public enum EfoodPrefs
	{
		Snack,
		Meal,
		Pastry,
		None
	}
	
	
	public EdrinkPrefs getDrink() {
		return m_drink;
	}


	public EfoodPrefs getFoodType() {
		return m_food;
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
		if (m_drink!=EdrinkPrefs.None)
		{
			outMsg+="i want " + m_temp.toString() +" "+ m_drink.toString() +" with " + m_milk.toString() +"\n" ;
		}
		
		if (m_food!= EfoodPrefs.None)
		{
			outMsg += "i want to eat " + m_food.toString();
		}
		
		
		return outMsg;
	}
	
	private EfoodPrefs m_food;
	private EmilkPrefs m_milk;
	private EdrinkTemp m_temp;
	private EdrinkPrefs m_drink;

}
