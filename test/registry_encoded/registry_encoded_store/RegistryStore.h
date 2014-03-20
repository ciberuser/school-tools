#ifndef REGISTRY_STORE_H
#define REGISTRY_STORE_H

#include <string>
#include <Windows.h>

#pragma warning(disable:4251) //dll import issue 
#pragma warning(disable:4996) //strncpy - i know the issue that it's not adding null termination at end of string - i deal with this.

#ifdef _WINDLL
#          define DLL_EXPORT __declspec(dllexport)
#          define DLL_TEMPLATE
#          define DLL_DECLSPECIFIER    __declspec(dllexport)
#else
	#ifdef STATIC_LIB
	#          define DLL_EXPORT
	#		   define DLL_TEMPLATE
	#		   define DLL_DECLSPECIFIER
	#else
	#          define DLL_EXPORT __declspec(dllimport)
	#          define DLL_TEMPLATE  extern
	#          define DLL_DECLSPECIFIER    __declspec(dllimport)
	
	#endif

#endif

DLL_TEMPLATE template class DLL_DECLSPECIFIER std::basic_string<char>;


///////////////////////////////////////////////////////////////////////////////////////
//header contain 3 classes definition  :
// StoreEncoder - encoded values for registry 
// RegistryRW - read/write values in registry
// store manager - API class - save key/value encoded pair at the registry
////////////////////////////////////////////////////////////////////////////////////////

//encoder interface - not part of API
class IStoreEncoder
{
public:
	virtual std::string Encrypt(const std::string& str) = 0;
	virtual std::string Decrypt(const std::string& str) = 0;

};
//////////////////////////////////////////////////////////////////////////////////////
// encoder class implament IStoreEncoder 
class DLL_EXPORT StoreEncoder : public IStoreEncoder
{
public:
	std::string Encrypt(const std::string& str);
	std::string Decrypt(const std::string& str);
	StoreEncoder(void);
	~StoreEncoder(void);

private:
	char m_key;
};
///////////////////////////////////////////////////////////////////////////////////////
//class RegistryRW - write/read values from registry 

#define MAX_VALUE_NAME 16383
#define MAX_KEY_SIZE 8

class DLL_EXPORT RegistryRW
{
public:
	RegistryRW(void);
	RegistryRW(const HKEY rootPath);

	~RegistryRW(void);

	 inline void SetRoot(const HKEY rootPath)
	 {
		 m_root = rootPath;
	 }
	 
	 std::string ReadValueFromReg(const std::string& path,const std::string& valueName) const;
	 bool KeyExist(const std::string& path,long &ret) const;
	 bool SubKeyExist(const std::string& path,const std::string& subKey) const;
	 long CreateRegistryKeyLibrary(const std::string& path,const std::string& name) const;
	 int Get_numKeyItems(const std::string& path) const;
	 long DeleteKey(const std::string& path,const std::string& subKey) const ;
	 long DeleteValue(const std::string & path,const std::string& name) const;
	 long CreateRegistryValue(const std::string& path,const std::string& valueName,const std::string& value) const;

private:
	 long GetValueFromKey(const std::string& path,const std::string& valueName,LPBYTE value) const;

	 long GetInfoKey(const HKEY& key ,DWORD & numItems,DWORD & biggestValueSize) const;
	 long OpenRegKey(const std::string& path, HKEY& key) const;
	 std::string CreateErrorMsg(const long ret,bool alarm) const;
	 HKEY m_root ;
};
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//library API (StoreManager class)
//
enum ERegRoot
{
	eHKEY_LOCAL_MACHINE , 
	eHKEY_CURRENT_USER
};
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// API class StoreManager - save pair of key/values in registry in when the values is saved encoded.
// 
class DLL_EXPORT StoreManager
{

#define MAX_DEFUALT_ITEMS 256

#define ERROR_STORE_MAX_ITEMS -2
#define ERROR_STORE_KEY_EXIST -3


public:
	//c'tors
	StoreManager(void);
	StoreManager(const int maxItems);
	StoreManager(const int maxItems ,const std::string& storeName);
	StoreManager(ERegRoot root); 
	StoreManager(const int maxItems ,const std::string& storeName,ERegRoot root);
	~StoreManager(void);
	
	inline void SetMaxItems(const int maxItems) 
	{
		m_maxItems = maxItems;
	}

	inline void SetStoreKeyName(const std::string& storeName)
	{
		m_storeStrPath = storeName;
	}

	inline void SetRegistryRoot(ERegRoot root)
	{
		if (root = eHKEY_CURRENT_USER)
		{
			m_regRW.SetRoot(HKEY_CURRENT_USER);
		}
		else
		{
			m_regRW.SetRoot(HKEY_LOCAL_MACHINE);
		}

	}

	//BASIC API
	 long Set(const std::string& key , const std::string& val) ;
	std::string Get(const std::string& key) const;
	bool Has(const std::string& key) const;
	
	//EXTEND API
	long DeleveKey(const std::string& key);
	long DeleleStore(const std::string& storeRegstiryPath = std::string(""));
	bool CreateNewStore();
	bool CreateNewStore(const std::string& storePath);


private:
	void Init(const std::string& storePath=std::string(""));

	int m_maxItems ;
	IStoreEncoder* m_encoder;  //interface - if we whant to replace the encoder
	std::string m_storeStrPath ;
	RegistryRW m_regRW;
	


};


#endif