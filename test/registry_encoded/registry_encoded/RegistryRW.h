#pragma once
#ifndef REAGISTRY_RW_H
#define REAGISTRY_RW_H

#include <Windows.h>
#include <string>

#define MAX_VALUE_NAME 16383

class RegistryRW
{
public:
	RegistryRW(void);
	RegistryRW(const HKEY rootPath);

	~RegistryRW(void);

	 std::string ReadValue2Reg(const std::string& path,const std::string& valueName) const;
	 bool KeyExist(const std::string& path,long &ret) const;
	 bool SubKeyExist(const std::string& path,const std::string& subKey) const;
	 long CreateRegistryKeyLibrary(const std::string& path,const std::string& name) const;
	 int Get_numKeyItems(const std::string& key) const;

	 
	 long CreateRegistryValue(const std::string& path,const std::string& valueName,const std::string& value) const;
private:
	 long GetInfoKey(const HKEY& key ,DWORD & numItems,DWORD&  securityDescriptor) const;
	 long OpenRegKey(const std::string& path, HKEY& key) const;
	 std::string CreateErrorMsg(const long ret,bool alarm) const;
	 HKEY m_root ;



};

#endif