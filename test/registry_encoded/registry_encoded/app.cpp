#include "app.h"
#include <iostream>
#include "StoreManager.h"
#include <Windows.h>

#define TEST_REG_PATH "SOFTWARE\\Ashampoo\\Ashampoo Burning Studio 6"
#define TEST_SOFTWARE_USER "Software"
app::app(void)
{
}


app::~app(void)
{
}


void main()
{
	/*
	int a ;
	std::cout<<"hello" <<std::endl;
	//test zone
	RegistryRW regRw;
	std::string val= regRw.ReadValue2Reg(TEST_REG_PATH,"ReportTransformURL");
	std::cout << "val="<<val <<std::endl;
	long ret;
	if (! regRw.KeyExist("dsjkle",ret))  std::cout <<"success test key not exit!!! ret= "<<ret << std::endl;
	RegistryRW regRw2(HKEY_CURRENT_USER); //add support to 
	if (regRw2.CreateRegistryKeyLibrary(TEST_SOFTWARE_USER,"store")==-2) std::cout<<"key already exist"<<std::endl;
	ret = regRw2.CreateRegistryValue(std::string(TEST_SOFTWARE_USER"\\store"),"itemKey","fdsgdd");	
	*/
	int a;
	StoreManager sm ;
	sm.Set("koko","cskjfeiopm");
	if (sm.Has("koko    ") ) std::cout << "great!!!" << std::endl;
	std::cin >>a ; 
}
