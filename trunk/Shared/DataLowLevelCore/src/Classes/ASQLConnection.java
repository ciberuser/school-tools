package Classes;


public class ASQLConnection {
	
	protected String m_username;
	protected String m_password;
	protected String m_connectionString;
	
	public ASQLConnection(){}
	
	public ASQLConnection(String username,String password,String connectionString )
	{
		setUsername(username);
		setPassword(password);
		setConnectionString(connectionString);
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
	

	
}
