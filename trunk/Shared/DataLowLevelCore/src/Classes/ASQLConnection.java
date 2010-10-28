package Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ASQLConnection {
	
	
	
	public ASQLConnection(){}
	
	public ASQLConnection(String username,String password,String connectionString ) 
	{
		setUsername(username);
		setPassword(password);
		setConnectionString(connectionString);
	}
	
	protected void Connect()throws Exception
	{
		if (getConnectionString().isEmpty() || getUsername().isEmpty() || getPassword().isEmpty()) throw (new Exception("check connection string ,user name and password "));
		setM_connection(DriverManager.getConnection(getConnectionString(),getUsername() ,getPassword()));
	}
	
	
	protected void Close() throws SQLException
	{
		getConnection().close();
	}
	
	public String getUsername() {
		return m_username;
	}
	public void setUsername(String m_username) {
		this.m_username = m_username;
	}
	public String getPassword() {
		return m_password;
	}
	public void setPassword(String m_password) {
		this.m_password = m_password;
	}
	public String getConnectionString() {
		return m_connectionString;
	}
	
	private void setM_connection(Connection m_connection) {
		this.m_connection = m_connection;
	}

	protected Connection getConnection() {
		return m_connection;
	}

	public void setConnectionString(String string) {
		m_connectionString = string;
	}
	
	protected String m_username;
	protected String m_password;
	protected String m_connectionString;
	private Connection m_connection; 

	
}
