#include "RegistryRW.h"
#include <iostream>

#define VALUE_SIZE 1024

//HKEY_LOCAL_MACHINE

RegistryRW::RegistryRW(void):m_root(HKEY_CURRENT_USER)
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
	return RegOpenKeyExA(m_root , path.c_str(),0, KEY_ALL_ACCESS, &key);
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
			case ERROR_MORE_DATA:
				errMsg.append("  error more data");
				break;
			default : 
				errMsg.append("ret=" + std::to_string((long double)ret));
		}
	if (alarm)
		std::cout<<errMsg<<std::endl;
	return errMsg;

}

long RegistryRW::GetValueFromKey(const std::string& path,const std::string& valueName,LPBYTE value) const
{
	HKEY hkey ;
	long ret;
	
    DWORD bufLen = MAX_VALUE_NAME*sizeof(TCHAR);
	ret=OpenRegKey(path,hkey);
	std::string errMsg("Error:");
	if (ret != ERROR_SUCCESS )
	{
		return ret;
	}

	ret = RegQueryValueExA(hkey, valueName.c_str(), 0, 0, value, &bufLen);
    RegCloseKey(hkey);
	if (bufLen  > MAX_VALUE_NAME*sizeof(TCHAR))
	{
		return ERROR_BUFFER_OVERFLOW;
	}
    if ( (ret != ERROR_SUCCESS) )
	{
		return ret;
    }
	return ret;
}

std::string RegistryRW::ReadValueFromReg(const std::string& path,const std::string& valueName) const
{
	std::string value;
	DWORD bufLen = MAX_VALUE_NAME*sizeof(TCHAR);
	TCHAR valueStr[MAX_VALUE_NAME]; 

	long ret = GetValueFromKey(path,valueName,(LPBYTE)valueStr);
	if  (ret!= ERROR_SUCCESS) return CreateErrorMsg(ret,false);
    std::string stringValue = std::string(valueStr, (size_t)bufLen - 1);
    size_t i = stringValue.length();
    while( i > 0 && stringValue[i-1] == '\0' ){
        --i;
    }
    return stringValue.substr(0,i); 
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
	std::string  NewKeyPath = (path.size()==0)? keyName : path + "\\" + keyName;
	if (KeyExist(NewKeyPath,ret)) return -2;
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

long RegistryRW::DeleteKey(const std::string& path,const std::string& subKey) const
{ 
	HKEY key;
	long
	ret = OpenRegKey(path,key);
	if (ret!=ERROR_SUCCESS) return ret;
#ifdef _WIN32 
	ret =RegDeleteKeyEx(key, subKey.c_str(), KEY_WOW64_32KEY, 0);
#else
	ret= RegDeleteKeyEx(key, subKey.c_str(), KEY_WOW64_64KEY, 0);
#endif
	
	return ret;
}

long RegistryRW::DeleteValue(const std::string & path,const std::string& name) const
{
	long ret;
	if (KeyExist(path,ret) && SubKeyExist(path,name))
	{
		ret= RegDeleteKeyValue(m_root,path.c_str(),name.c_str());
	}
	return ret;
}
long RegistryRW::CreateRegistryValue(const std::string& path,const std::string& valueName,const std::string& value) const
{
	long ret= 0;
	if (!KeyExist(path,ret)) return ret;
	HKEY key ;
	TCHAR valueBuf[MAX_VALUE_NAME] ={0};
	strncpy(valueBuf, value.c_str(),value.length());
	valueBuf[value.length()]='\0';
	ret = RegOpenKeyExA(m_root , path.c_str(),0, KEY_WRITE, &key);
	if (ret != ERROR_SUCCESS ) 
	{
		CreateErrorMsg(ret,true);
		return ret;
	}
	if (key!=NULL)
	{
		ret = RegSetValueEx(key,valueName.c_str(),0,REG_SZ,(LPBYTE)valueBuf,MAX_VALUE_NAME);
	}
	if (ret != ERROR_SUCCESS)
	{
		CreateErrorMsg(ret,true);
	}
	RegCloseKey(key);

	return ret;
}

bool RegistryRW::SubKeyExist(const std::string& path,const std::string& subKey) const 
{
	TCHAR  val [MAX_VALUE_NAME];
	return  (GetValueFromKey(path,subKey ,(LPBYTE) val) == ERROR_SUCCESS) ;
	
}

int RegistryRW::Get_numKeyItems(const std::string& path) const
{
	HKEY hkey;
	DWORD numItems = 0;
	DWORD biggest;
	long ret ;
	ret = OpenRegKey(path,hkey);
	if (ret != ERROR_SUCCESS)
	{
		CreateErrorMsg(ret,true);
		return -1; //error 
	}
	GetInfoKey(hkey,numItems,biggest);
	return numItems;
}

long RegistryRW::GetInfoKey(const HKEY& key ,DWORD & numItems,DWORD & biggestValueSize) const
{
	long ret;

    TCHAR    achClass[MAX_PATH] = TEXT("");  // buffer for class name 
    DWORD    cchClassName = MAX_PATH;  // size of class string 
    DWORD    cValues;              // number of values for key 
    DWORD    maxValueSize; // size of security descriptor 
   

	 ret = RegQueryInfoKey(
        key,                    // key handle 
        achClass,                // buffer for class name 
        &cchClassName,           // size of class string 
        NULL,                    // reserved 
        NULL,               // number of subkeys 
        NULL,            // longest subkey size 
        NULL,            // longest class string 
        &cValues,                // number of values for this key 
        NULL,            // longest value name 
        &maxValueSize,         // longest value data 
	    NULL,			// security descriptor 
        NULL);

	 numItems = cValues;
	 biggestValueSize = maxValueSize;
	 return ret;
}

