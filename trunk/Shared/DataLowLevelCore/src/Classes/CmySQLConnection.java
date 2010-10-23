package Classes;

import java.sql.Connection;
import java.sql.ResultSet;

public class CmySQLConnection extends ASQLConnection implements Interfaces.ISQLConnection
{

	public CmySQLConnection(){}
	
	public CmySQLConnection(String userName,String password,String connectionString) {
		super(userName,password,connectionString);
	}
	
	
	@Override
	public void Connect() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Connect(String connectionString, String userName,
			String Password) {
		// TODO Auto-generated method stub
		
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

}
