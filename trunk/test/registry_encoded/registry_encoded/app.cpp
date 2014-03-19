#include "app.h"
#include <iostream>
#include "StoreManager.h"
#include "StoreEncoder.h"
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
	std::string test("coolWord____tttrrr");
	IStoreEncoder* encoder = new StoreEncoder();
	std::string encoded_test =encoder->Encrypt(test) ;
	std::string deconded_test=encoder->Decrypt(encoded_test);
	std::cout << "encoded string =" <<encoded_test<<"back again ="<<deconded_test<< std::endl;
	if (test !=deconded_test)
	{
		std::cout <<"we hava a problem ..."<<std::endl;
	}
	
	
	int a;
	StoreManager sm ;
	if (sm.Has("str5")) std::cout<<"great1" <<std::endl;
	for (int i =0;i <258 ; ++i)
	{
		std::string data ="data";
		std::string str ="str";
		str= str+std::to_string((long double)i);
		data= data+std::to_string((long double)i);
		sm.Set(str,data);
		//sm.Set("koko","cskjfeiopm");
		std::string val =  sm.Get(str);
		std::cout <<"val=" <<val << std::endl;
		sm.Set(str,data);
		if (sm.Has(str) ) std::cout << "great!!!" << std::endl;
		 val =  sm.Get(str);
		std::cout <<"val=" <<val << std::endl;
	}
	std::cin >>a ;

	delete encoder;
}
