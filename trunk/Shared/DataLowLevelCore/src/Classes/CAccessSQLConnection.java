package Classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import Interfaces.ISQLConnection;

public class CAccessSQLConnection extends ASQLConnection implements ISQLConnection {

	final String  DRIVER_STRING = "sun.jdbc.odbc.JdbcOdbcDriver";
		
	public CAccessSQLConnection(){}
	
	public CAccessSQLConnection(String userName,String password,String connectionString)
	{
		super(userName,password,connectionString);
	}
	
	public CAccessSQLConnection(String connectionString)
	{
		super(connectionString);
	}
	
	public void Close()  {
		try
		{
			super.Close();
		}	 catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void Connect() {
		try 
		{
			Class.forName(DRIVER_STRING).newInstance();
			super.Connect();
		}
		catch (Exception ex) {
			
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

	@Override
	public ResultSet ExcuteStoredProceduresInParm(String procdureName,
			Object[] dataType) throws Exception {
		throw (new Exception("can't implmented in Access..."));
		
	}

	@Override
	public ResultSet ExcuteStoredProceduresOutParm(String procdureName,
			int[] dataType) throws Exception {
		throw (new Exception("can't implmented in Access..."));
	}

	@Override
	public Connection GetConnection() {
		return super.getConnection();
	}

	@Override
	public void Reconnect(String userName, String Password) {
		Connect(userName,Password);

	}

}
