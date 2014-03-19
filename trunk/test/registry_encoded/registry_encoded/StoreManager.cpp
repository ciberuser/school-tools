#include "StoreManager.h"

#define MAX_KEY_SIZE 8



StoreManager::StoreManager(void):m_maxItems(MAX_DEFUALT_ITEMS),m_storeStrPath("store"),m_encoder(NULL)
{
	Init();
}

StoreManager::StoreManager(const int maxItems):m_maxItems(maxItems)
{
	Init();
}

StoreManager::~StoreManager(void)
{
	if (m_encoder != NULL) delete m_encoder;
}

void StoreManager::Init()
{
	
	/*if (!m_regRW.KeyExist(m_storeStrPath,ret))
	{
		m_regRW.CreateRegistryKeyLibrary("",m_storeStrPath);
	}
	*/
	CreateNewStore("");
	m_encoder = new StoreEncoder();

}
long StoreManager::DeleteStore()
{
	return DeleleStore("");	
}

long StoreManager::DeleleStore(const std::string& storeRegstiryPath)
{
	return  m_regRW.DeleteKey(storeRegstiryPath,m_storeStrPath);
}
bool StoreManager::CreateNewStore()
{
	return CreateNewStore("");
}

long StoreManager::DeleveKey(const std::string& key)
{
	return m_regRW.DeleteValue(m_storeStrPath,key);
}

bool StoreManager::CreateNewStore(const std::string& storePath)
{
	long ret;
	if (!m_regRW.KeyExist(m_storeStrPath,ret))
	{
		ret = m_regRW.CreateRegistryKeyLibrary(storePath,m_storeStrPath);
	}
	return (ret==ERROR_SUCCESS);
}

long StoreManager::Set(const std::string& key , const std::string& val)
{
	long ret;
	std::string keyStr,valStr,valStrEnc;

	if (m_regRW.Get_numKeyItems(m_storeStrPath) >= m_maxItems)
	{
		return ERROR_STORE_MAX_ITEMS;
	}
	//validate string size - if not will shrink
	keyStr =  (key.length() > MAX_KEY_SIZE) ?  key.substr(0,MAX_KEY_SIZE-1) : key ; 
	valStr = (val.size() >  MAX_VALUE_NAME) ? val.substr(0,MAX_VALUE_NAME-1) :val;
	valStrEnc= (m_encoder!=NULL)? m_encoder->Encrypt(valStr):valStr;
	ret = m_regRW.CreateRegistryValue(m_storeStrPath,keyStr,valStrEnc);

	return ret;
}

std::string StoreManager::Get(const std::string& key) const
{ 
	if (!Has(key)) return std::string(" Key not exist!!");
	std::string encVal =  m_regRW.ReadValueFromReg(m_storeStrPath,key);
	return (m_encoder!=NULL) ? m_encoder->Decrypt(encVal) :encVal ;
}

bool StoreManager::Has(const std::string& key) const
{
	return  m_regRW.SubKeyExist(m_storeStrPath,key); 
}

