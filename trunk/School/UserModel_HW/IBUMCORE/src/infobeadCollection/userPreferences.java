package infobeadCollection;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.DropMode;

public class userPreferences {
	
	
	private Lock m_lock ;
	private lockerCondition m_lockerCondition ;
	public userPreferences ()
	{
		m_lock = new ReentrantLock();
		
		m_milk =EmilkPrefs.None;
		m_temp=EdrinkTemp.None;
		
	}
	
	
	void SetWhatToDrink (EdrinkPrefs type ,EdrinkTemp temp,EmilkPrefs milk)
	{
		
		m_lock.lock();
		m_drink =type;
		m_temp =temp;
		m_milk = milk;
		m_lock.unlock();
	}
	
	void SetWhatToDrink(EdrinkTemp  temp)
	{
		m_lock.lock();
		m_drink = EdrinkPrefs.getRandom();
		m_temp = temp;
		m_lock.unlock();
	}
	
	public enum EdrinkPrefs
	{
		Tea ,
		Coffee;
		
		 public static EdrinkPrefs getRandom() {
	        return values()[(int) (Math.random() * values().length)];
	    }
	}
	
	public enum EcupSize
	{
		small, 
		medium,
		large;		
		
		 public static EcupSize getRandom() {
		        return values()[(int) (Math.random() * values().length)];
		    }
	}
	
	public enum EdrinkTemp{
		Hot,
		Cold,
		None,
		
	}
	
	public enum EcoffeeBlend
	{
		brazilian, 
		colombian, 
		african; 
		
		 public static EcoffeeBlend getRandom() {
		        return values()[(int) (Math.random() * values().length)];
		    }
	}
	
	
	public enum EmilkPrefs
	{
		Regolar,
		Low_Lactose,
		Soy,
		None;
		
		 public static EmilkPrefs getRandom() {
	        return values()[(int) (Math.random() * values().length)];
	    }
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

		outMsg+="i want " + m_temp.toString() ;
		String milkString = (m_milk==EmilkPrefs.None) ? "" :" with " + m_milk.toString() +" milk ";
		if (m_drink==EdrinkPrefs.Coffee)
		{
			outMsg += " " +m_blend.toString()+" " + m_drink.toString() +milkString+ " on a " +m_cEcupSize.toString() +" cup \n" ;
		}
		else
		{
			outMsg += " " + m_drink.toString() +milkString+ " on a " +m_cEcupSize.toString() +" cup \n" ;
		
		}
		return outMsg;
	}
	
	public void setCupSize(EcupSize cupSize) 
	{
		m_lock.lock();
		this.m_cEcupSize = cupSize;
		m_lock.unlock();
	}
	
	public void setBlend(EcoffeeBlend blend)
	{
		m_lock.lock();
		this.m_blend = blend;
		m_lock.unlock();
		
	}
	
	public void setMilkType(EmilkPrefs milk)
	{
		m_lock.lock();
		m_milk = milk;
		m_lock.unlock();
	}
	
	
	private EmilkPrefs m_milk;
	private EdrinkTemp m_temp;
	private EdrinkPrefs m_drink;
	private EcupSize m_cEcupSize;
	private EcoffeeBlend m_blend;
	
}
