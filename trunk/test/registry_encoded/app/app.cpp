#include "app.h"
#include <iostream>
#include "RegistryStore.h"
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
	//tests
	std::string test("coolWord____tttrrr");
	IStoreEncoder* encoder = new StoreEncoder();
	std::string encoded_test =encoder->Encrypt(test) ;
	std::string deconded_test=encoder->Decrypt(encoded_test);
	std::cout << "encoded string =" <<encoded_test<<"back again ="<<deconded_test<< std::endl;
	if (test !=deconded_test)
	{
		std::cout <<"we hava a problem ..."<<std::endl;
	}
	//RegistryRW rw ;
	//rw.DeleteValue("store","str1");
	//rw.DeleteKey("","store");
	
	int a;
	StoreManager sm1(eHKEY_LOCAL_MACHINE);
	
	StoreManager sm ;
	
	sm.DeleleStore();
	sm.CreateNewStore();
	sm.Has(test);
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
	sm.DeleveKey("str2");
	std::cin >>a ;

	delete encoder;
}