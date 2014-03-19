#pragma once
#ifndef STORE_ENCODER_H
#define STORE_ENCODER_H

#include <string>

class IStoreEncoder
{
public:
	virtual std::string Encrypt(const std::string& str) = 0;
	virtual std::string Decrypt(const std::string& str) = 0;

};

class StoreEncoder : public IStoreEncoder
{
public:
	std::string Encrypt(const std::string& str);
	std::string Decrypt(const std::string& str);
	StoreEncoder(void);
	~StoreEncoder(void);

private:
	char m_key;
};
#endif 