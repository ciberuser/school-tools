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
	ifstream inputFile;
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

	cout<< "file input : " << argv[1]<<"\n";
	cout<< "algorithm : " << argv[2]<<"\n";

	inputFile.open(argv[1]);
	if (!inputFile.is_open())
	{
		ExitError("can't read file...");
	}
	else
	{
		inputFile>>inputChar;
		string inputString(inputChar);
		Isearch* search = new DFSsearch(inputString);
	}
}


void ExitError(const char* msg)
{
	cout << msg ;
	exit(1);
}