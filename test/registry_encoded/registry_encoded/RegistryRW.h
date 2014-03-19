#pragma once
#ifndef REAGISTRY_RW_H
#define REAGISTRY_RW_H

#include <Windows.h>
#include <string>

#define MAX_VALUE_NAME 16383
#define MAX_KEY_SIZE 8

class RegistryRW
{
public:
	RegistryRW(void);
	RegistryRW(const HKEY rootPath);

	~RegistryRW(void);

	 std::string ReadValueFromReg(const std::string& path,const std::string& valueName) const;
	 bool KeyExist(const std::string& path,long &ret) const;
	 bool SubKeyExist(const std::string& path,const std::string& subKey) const;
	 long CreateRegistryKeyLibrary(const std::string& path,const std::string& name) const;
	 int Get_numKeyItems(const std::string& path) const;
	 long DeleteKey(const std::string& path) const ;
	 
	 long CreateRegistryValue(const std::string& path,const std::string& valueName,const std::string& value) const;
private:
	 long GetValueFromKey(const std::string& path,const std::string& valueName,LPBYTE value) const;

	 long GetInfoKey(const HKEY& key ,DWORD & numItems,DWORD & biggestValueSize) const;
	 long OpenRegKey(const std::string& path, HKEY& key) const;
	 std::string CreateErrorMsg(const long ret,bool alarm) const;
	 HKEY m_root ;



};

#endif