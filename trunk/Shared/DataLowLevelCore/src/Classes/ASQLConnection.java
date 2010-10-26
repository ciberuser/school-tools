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
		m_connection = DriverManager.getConnection(getConnectionString(),getUsername() ,getPassword());
	}
	
	protected void Close() throws SQLException
	{
		m_connection.close();
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
	public void setConnectionString(String string) {
		m_connectionString = string;
	}
	
	protected String m_username;
	protected String m_password;
	protected String m_connectionString;
	protected Connection m_connection; 

	
}
