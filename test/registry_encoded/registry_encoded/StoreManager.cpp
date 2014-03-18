#include "StoreManager.h"

#define MAX_KEY_SIZE 8



StoreManager::StoreManager(void):m_maxItems(MAX_DEFUALT_ITEMS),m_storeStrPath("store")
{
	Init();
}

StoreManager::StoreManager(const int maxItems):m_maxItems(maxItems)
{
	Init();
}

StoreManager::~StoreManager(void)
{
}

void StoreManager::Init()
{
	long ret;
	//add delete
	if (!m_regRW.KeyExist(m_storeStrPath,ret))
	{
		m_regRW.CreateRegistryKeyLibrary("",m_storeStrPath);
	}


}

long StoreManager::Set(const std::string& key , const std::string& val)
{
	long ret;
	std::string keyStr,valStr;

	
	
	if  (key.size() > MAX_KEY_SIZE-1 )
	{
		keyStr = key.substr(0,MAX_KEY_SIZE) ;
	}
	else
	{ 
		keyStr= key;
		keyStr.resize(MAX_KEY_SIZE,' ');
	}
	if (val.size() >  MAX_VALUE_NAME-1)
	{
		valStr=	val.substr(0,MAX_VALUE_NAME-1);
	}
	else
	{
		valStr = val;
		valStr.resize(MAX_VALUE_NAME-1,' '); 
	}
			
	ret = m_regRW.CreateRegistryValue(m_storeStrPath,keyStr,valStr);

	return ret;
}


std::string StoreManager::Get(const std::string& key)
{ 

	//need to add encription 
	return m_regRW.ReadValue2Reg(m_storeStrPath,key);
}

bool StoreManager::Has(const std::string& key)
{
	return  m_regRW.SubKeyExist(m_storeStrPath,key); 
}

