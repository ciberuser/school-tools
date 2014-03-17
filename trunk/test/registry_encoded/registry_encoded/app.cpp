#include "app.h"
#include <iostream>
#include "RegistryRW.h"


#define TEST_REG_PATH "SOFTWARE\\Ashampoo\\Ashampoo Burning Studio 6"

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
	std::cin >>a ; 
}
