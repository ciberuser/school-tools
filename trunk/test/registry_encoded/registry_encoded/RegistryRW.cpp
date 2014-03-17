#include "RegistryRW.h"
#include <iostream>

#define VALUE_SIZE 1024

RegistryRW::RegistryRW(void):m_root(HKEY_LOCAL_MACHINE)
{

}

RegistryRW::RegistryRW(const HKEY rootPath):m_root(rootPath)
{

}

RegistryRW::~RegistryRW(void)
{
}

long RegistryRW::OpenRegKey(const std::string& path,HKEY& key) const
{
	return RegOpenKeyExA(m_root , path.c_str(),0, KEY_QUERY_VALUE, &key);
}

std::string RegistryRW::CreateErrorMsg(const long ret,bool alarm) const
{
	std::string errMsg("Error:");
	switch(ret)
		{
			case ERROR_FILE_NOT_FOUND :
				errMsg.append(" didn't found value ");
				break;
			case ERROR_NO_MATCH:
				errMsg.append(" did not match ");
				break;
			case ERROR_ACCESS_DENIED:
				errMsg.append(" acceess denied");
				break;
			case ERROR_INVALID_PARAMETER:
				errMsg.append(" invalid parameter ");
				break;
			default : 
				errMsg.append("ret=" + std::to_string((long double)ret));
		}
	if (alarm)
		std::cout<<errMsg<<std::endl;
	return errMsg;

}

std::string RegistryRW::ReadValue2Reg(const std::string& path,const std::string& valueName) const
{
	HKEY hkey ;
	long ret;
	TCHAR value[VALUE_SIZE]; 
    DWORD bufLen = VALUE_SIZE*sizeof(TCHAR);
	ret=OpenRegKey(path,hkey);
	std::string errMsg("Error:");
	if (ret != ERROR_SUCCESS )
	{
		return  CreateErrorMsg(ret,false);
	}

	ret = RegQueryValueExA(hkey, valueName.c_str(), 0, 0, (LPBYTE) value, &bufLen);
    RegCloseKey(hkey);
	if (bufLen  > VALUE_SIZE*sizeof(TCHAR))
	{
		return std::string("Error: value is bigget them buffer size");
	}
    if ( (ret != ERROR_SUCCESS) )
	{
		errMsg.append("some kind of problem : ");
		errMsg+ std::to_string((long double)ret);
		return errMsg;
    }
    std::string stringValue = std::string(value, (size_t)bufLen - 1);
    size_t i = stringValue.length();
    while( i > 0 && stringValue[i-1] == '\0' ){
        --i;
    }
    return stringValue.substr(0,i); 
}

void RegistryRW::WriteValue2Reg(const std::string& path,const std::string& valueName,const std::string& value) const
{
		
}

bool RegistryRW::KeyExist(const std::string& path ,long &ret) const
{
	HKEY key;
	ret =  OpenRegKey(path,key);
	if (ret == ERROR_SUCCESS)
	{
		RegCloseKey(key);
		return true;
	}
	return false;
}

long RegistryRW::CreateRegistryKeyLibrary(const std::string& path,const std::string& keyName) const
{
	long ret ;
	if (!KeyExist(path,ret)) 
	{
		CreateErrorMsg(ret,true);
		return ret;
	}
	std::string NewKeyPath = path + "\\" + keyName;
	if (KeyExist(NewKeyPath)) return -2;
	HKEY key ;
	
	//need to omit 
	ret = OpenRegKey(path,key);
	if (ret != ERROR_SUCCESS)
	{
			CreateErrorMsg(ret,true);
			return ret;
	}
		
	ret= RegCreateKeyEx(m_root, NewKeyPath.c_str(), 0L, NULL, REG_OPTION_NON_VOLATILE, KEY_ALL_ACCESS, NULL, &key, NULL );
	if (ret != ERROR_SUCCESS ) CreateErrorMsg(ret,false);
	RegCloseKey(key);
	return 0;
}

long RegistryRW::CreateRegistryValue(const std::string& path,const std::string& valueName,const std::string& value) const
{
	return 0;
}

