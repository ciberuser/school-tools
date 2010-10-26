package Classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import Interfaces.*;

public class CmySQLConnection extends ASQLConnection implements ISQLConnection
{

	public CmySQLConnection(){}
	
	public CmySQLConnection(String userName,String password,String connectionString) throws SQLException
	{
		super(userName,password,connectionString);
	}
	
	
	@Override
	public void Connect() {
		// TODO Auto-generated method stub
	
		try 
		{
			super.Connect();
		}
		catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}

	@Override
	public void Connect(String connectionString, String userName,
			String Password)
	{
	
		super.setConnectionString(connectionString);
		super.setUsername(userName);
		super.setPassword(Password);
		Connect();
		
		
		
	}

	@Override
	public void Connect(String userName, String Password) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ResultSet ExcuteQurey(String sqlQurey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet ExcuteStoredProcedures(String procdureName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Connection GetConnection() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void LoadDriver(String driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Reconnect(String userName, String Password) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void SetDriver(String driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Close() 
	{
		try
		{
			super.Close();	
		}
		
		catch(SQLException ex)
		{
			
		}
		
		}

}
