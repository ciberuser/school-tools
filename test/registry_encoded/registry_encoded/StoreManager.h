#pragma once
#ifndef STRORE_MANAGER_H
#define STRORE_MANAGER_H

#include <string>
#include "RegistryRW.h"


class StoreManager
{

#define MAX_DEFUALT_ITEMS 256

public:
	StoreManager(void);
	StoreManager(const int maxItems);
	~StoreManager(void);
	//API
	long Set(const std::string& key , const std::string& val);
	std::string Get(const std::string& key);
	bool Has(const std::string& key);


private:
	/*
	inline bool AssertKeySize(const std::string& key) const
	{
		return (key.size() <  m_maxKeySize);
	}

	inline bool AssertValSize(const std::string& val) const
	{
		return (val.size() < m_maxValueSize) ;
	}
	*/
	void Init();

	int m_maxItems ;
	int m_maxKeySize;
	int m_maxValueSize;

	std::string m_storeStrPath ;
	RegistryRW m_regRW;
};
#endif
