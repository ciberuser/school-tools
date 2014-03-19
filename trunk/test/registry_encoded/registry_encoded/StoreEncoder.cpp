#include "StoreEncoder.h"


StoreEncoder::StoreEncoder(void):m_key('g')
{
}


StoreEncoder::~StoreEncoder(void)
{
}

std::string StoreEncoder::Encrypt(const std::string& str)
{
	std::string encryptStr(str);
	for (unsigned int i = 0 ; i< str.length(); i++)
	{
		encryptStr[i]=(str[i])^m_key;
	}
	return encryptStr;
}

std::string StoreEncoder::Decrypt(const std::string& str)
{
	std::string decryptStr(str);
	for (unsigned int i = 0 ; i< str.length(); i++)
	{
		decryptStr[i]=(str[i])^m_key;
	}
	return decryptStr;
}