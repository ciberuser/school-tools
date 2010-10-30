package DataCoreSrc;
import Interfaces.*;

public class DataBaseType 
{
	ISQLConnection m_sqlConnection;
	EDataBaseType m_eDataBaseType;
	String m_ConnectionStringUrl;
	
	public DataBaseType(){	}
	
	public DataBaseType(ISQLConnection sqlConnection,EDataBaseType eDataType,String ConnectionString)
	{
		setSqlConnection(sqlConnection);
		setEDataBaseType(eDataType);
		setConnectionStringUrl(ConnectionString);
	}
	
	public ISQLConnection getSqlConnection() {
		return m_sqlConnection;
	}
	public void setSqlConnection(ISQLConnection connection) {
		m_sqlConnection = connection;
	}
	public EDataBaseType getEDataBaseType() {
		return m_eDataBaseType;
	}
	public void setEDataBaseType(EDataBaseType dataBaseType) {
		m_eDataBaseType = dataBaseType;
	}
	public String getConnectionStringUrl() {
		return m_ConnectionStringUrl;
	}
	public void setConnectionStringUrl(String connectionStringUrl) {
		m_ConnectionStringUrl = connectionStringUrl;
	}

}
