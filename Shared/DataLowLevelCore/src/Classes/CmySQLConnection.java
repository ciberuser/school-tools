package Classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import Interfaces.*;



public class CmySQLConnection extends ASQLConnection implements ISQLConnection
{
	final String  DRIVER_STRING = "com.mysql.jdbc.Driver";
	public CmySQLConnection(){}
	
	public CmySQLConnection(String userName,String password,String connectionString) throws SQLException
	{
		super(userName,password,connectionString);
	}
	
	public CmySQLConnection(String connectionString)
	{
		super(connectionString);
	}
	
	@Override
	public void Connect() {
		// TODO Auto-generated method stub
	
		try 
		{
			Class.forName(DRIVER_STRING).newInstance();
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
		super.setUsername(userName);
		super.setPassword(Password);
		Connect();
	}

	@Override
	public ResultSet ExcuteQurey(String sqlQurey) throws SQLException {
		return super.ExcuteQurey(sqlQurey);
	}

	
	public ResultSet ExcuteStoredProceduresOutParm(String procdureName,int[] dataType) throws Exception
	{
		return super.ExcuteStoredProceduresOutParm(procdureName,dataType);
	}

	@Override
	public Connection GetConnection() 
	{
		return super.getConnection();
	}

	

	@Override
	public void Reconnect(String userName, String Password) {
		// TODO Auto-generated method stub
		Connect(userName,Password);
	}

	
	
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

	@Override
	public ResultSet ExcuteStoredProceduresInParm(String procdureName,Object[] dataType) throws Exception {
		
		return super.ExcuteStoredProceduresInParm(procdureName,dataType);
	}

}
