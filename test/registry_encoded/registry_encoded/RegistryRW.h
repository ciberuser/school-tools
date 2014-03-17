#pragma once

#include <Windows.h>
#include <string>

class RegistryRW
{
public:
	RegistryRW(void);
	RegistryRW(const HKEY rootPath);

	~RegistryRW(void);

	 std::string ReadValue2Reg(const std::string& path,const std::string& valueName) const;
	 void WriteValue2Reg(const std::string& path,const std::string& valueName,const std::string& value) const; 
	 bool KeyExist(const std::string& path,long &ret =0) const;
	 long CreateRegistryKeyLibrary(const std::string& path,const std::string& name) const;
	 long CreateRegistryValue(const std::string& path,const std::string& valueName,const std::string& value) const;
private:
	 long OpenRegKey(const std::string& path, HKEY& key) const;
	 std::string CreateErrorMsg(const long ret,bool alarm) const;
	 HKEY m_root ;



};

