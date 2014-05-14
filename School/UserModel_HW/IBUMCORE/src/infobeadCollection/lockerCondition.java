package infobeadCollection;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class lockerCondition 
{
	
	private lockerCondition()
	{
		m_lock =  new ReentrantLock();
		m_isLock = false;
	}
	
	private static lockerCondition m_lockerCondition;
	private static Lock m_lock;
	private boolean m_isLock;
	
	public boolean Lock()
	{
		m_isLock =  m_lock.tryLock();
		return m_isLock;
	}
	
	public void unLock()
	{
		m_isLock = false;
		
		m_lock.unlock();
	}
	
	
	public boolean isLock()
	{
		return m_isLock;
	}
	public boolean Lock(Object obj)
	{
		try {
			obj.wait(100000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean Unlock(Object obj)
	{
		obj.notifyAll();
		return true;
	}
	
	
	public static lockerCondition getInstance()
	{
		if (m_lockerCondition== null)
		{
			m_lockerCondition = new lockerCondition();
		}
		return m_lockerCondition;
	}
	
	
	
	
	
}