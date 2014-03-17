#include "app.h"
#include <iostream>
#include "RegistryRW.h"
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
	int a ;
	std::cout<<"hello" <<std::endl;
	//test zone
	RegistryRW regRw;
	std::string val= regRw.ReadValue2Reg(TEST_REG_PATH,"ReportTransformURL");
	std::cout << "val="<<val <<std::endl;
	long ret = regRw.KeyExist("dsjkle");
	std::cout <<"ret= "<<ret << std::endl;
	RegistryRW regRw2(HKEY_CURRENT_USER);
	if (regRw2.CreateRegistryKeyLibrary(TEST_SOFTWARE_USER,"store")==-2) std::cout<<"key already exist"<<std::endl;
	
	std::cin >>a ; 
}
