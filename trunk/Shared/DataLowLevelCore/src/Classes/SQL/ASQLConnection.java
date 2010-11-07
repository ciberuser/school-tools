package Classes.SQL;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;



public class ASQLConnection {
	
	
	
	public ASQLConnection(){}
	
	public ASQLConnection(String username,String password,String connectionString ) 
	{
		setUsername(username);
		setPassword(password);
		setConnectionString(connectionString);
	}
	
	public ASQLConnection(String connectionString)
	{
		setConnectionString(connectionString);
	}
	
	protected void Connect()throws Exception
	{
		if (getConnectionString().isEmpty() || getUsername().isEmpty() || getPassword().isEmpty()) throw (new Exception("check connection string ,user name and password "));
		setConnection(DriverManager.getConnection(getConnectionString(),getUsername() ,getPassword()));
	}

	protected void Connect(String connectionString,String userName, String Password) throws Exception
	{
		setConnectionString(connectionString);
		setUsername(userName);
		setPassword(Password);
		Connect();
	}
	
	private String questionMarkReturn(int parmNumber)
	{
		String returnString = "";
		for (int i = 0; i < parmNumber; ++i)
		{
			returnString +="?";
			if (i+1 <= (parmNumber-1)) returnString += ",";
		}
		return returnString;
	}
	
	CallableStatement PerpareForCalling(String ProcCallName) throws Exception
	{
		if (getConnection() == null) throw (new Exception("connection not establish yet"));
		return getConnection().prepareCall(ProcCallName); 
	}
	
	protected ResultSet ExcuteStoredProceduresOutParm(String procdureName,int[] dataType) throws Exception
	{
		String ProcName = "{ call " + procdureName +  "(" + questionMarkReturn(dataType.length)+") }";
		CallableStatement cs = PerpareForCalling(ProcName);
		if (dataType.length > 0) 
		{
			for (int i = 0 ; i < dataType.length ; ++i )
			cs.registerOutParameter(i+1,dataType[i]); 
		}
		return cs.executeQuery();
			
	}
	protected ResultSet ExcuteQurey(String sqlQurey) throws SQLException
	{
		Statement cs = getConnection().createStatement();
		return cs.executeQuery(sqlQurey);
	}
	
	protected ResultSet ExcuteStoredProceduresInParm(String procdureName,Object[] dataType) throws Exception
	{
		String ProcName = "{call " + procdureName +  "(" + questionMarkReturn(dataType.length)+") }";
		CallableStatement cs = PerpareForCalling(ProcName);
		
		if (dataType.length > 0) 
		{
			for (int i = 0 ; i < dataType.length ; ++i )
			{
				if (dataType[i] instanceof String)
				{
					cs.setString(i,dataType[i].toString());
				}
				if (dataType[i] instanceof Integer )
				{
					cs.setInt(i+1, (int)(Integer)dataType[i]);
				}
				if (dataType[i] instanceof Boolean)
				{
					cs.setBoolean(i+1,(Boolean) dataType[i]);
				}
				if (dataType[i] instanceof Long)
				{
					cs.setLong(i+i, (Long)dataType[i]);
				}
				if (dataType[i] instanceof Timestamp)
				{
					cs.setTimestamp(i+i,(Timestamp)dataType[i]);
				}
			}
			
		}
		return cs.executeQuery();
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
	
	private void setConnection(Connection m_connection) {
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
