#pragma once
#ifndef STRORE_MANAGER_H
#define STRORE_MANAGER_H

#include <string>
#include "RegistryRW.h"
#include "StoreEncoder.h"

enum ERegRoot
{
	eHKEY_LOCAL_MACHINE , 
	eHKEY_CURRENT_USER
};

class  StoreManager
{


#define MAX_DEFUALT_ITEMS 256

#define ERROR_STORE_MAX_ITEMS -2
#define ERROR_STORE_KEY_EXIST -3


public:
	StoreManager(void);
	StoreManager(const int maxItems);
	~StoreManager(void);
	
	inline void SetMaxItems(const int maxItems) 
	{
		m_maxItems = maxItems;
	}

	inline void SetStoreKeyName(const std::string& storeName)
	{
		m_storeStrPath = storeName;
	}

	//BASIC API
	 long Set(const std::string& key , const std::string& val) ;
	std::string Get(const std::string& key) const;
	bool Has(const std::string& key) const;
	
	//EXTEND API
	long DeleveKey(const std::string& key);
	long DeleteStore();
	long DeleleStore(const std::string& storeRegstiryPath);
	bool CreateNewStore();
	bool CreateNewStore(const std::string& storePath);


private:
	void Init();

	int m_maxItems ;
	IStoreEncoder* m_encoder;  //interface - if we whant to replace the encoder
	std::string m_storeStrPath ;
	RegistryRW m_regRW;


};
#endif
