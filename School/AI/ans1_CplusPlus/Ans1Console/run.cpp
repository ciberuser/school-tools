#include <iostream>
#include <fstream>
#include <string>
#include "run.h"
#include "DFSsearch.h"

using namespace std;
using namespace Search;

void main(int argc,char* argv[])
{
	int numOfItem = argc;
	string alg ;
	string filePath ;
	ofstream inputFile;
	char inputChar[50] ;
	cout<< " *** Puzzle - answare 4 *** \n Inputs:\n ";
	
	if (argv[2] == NULL) 
	{
		ExitError("algorithm not found!");
	}

	if(argv[1] == NULL)
	{
		ExitError("no input file insert");
	}
	filePath = argv[1];
	alg = argv[2];

	cout<< "file input : " << argv[1]<<"\n";
	cout<< "algorithm : " << argv[2]<<"\n";
	
	try
	{
		inputFile.open(filePath.c_str(),ios::in);
		if (!inputFile.is_open())
		{
			inputFile.close();
			ExitError("can't read file...");
		}
		else
		{
			string inputString(inputChar);
			// 	Isearch* search = new DFSsearch(inputString);
			inputFile.close();
		}
	}
	catch (...)
	{
		inputFile.close();
	}

	
	
	
}


void ExitError(const char* msg)
{
	cout << msg ;
	exit(1);
}