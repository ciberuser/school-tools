#include "Asearch.h"
#include <iostream>

namespace Search
{


	void Asearch::PrintPhase(const int phaseNum)
	{
		cout<<"Phase number :" << phaseNum ;
		PrintMatrix();
	}

	Asearch::Asearch(const string& inputStr)
	{
	
	}

	void Asearch::PrintMatrix()
	{
		cout<<"*** matrix ***";
		for(int i = 0 ; i<MATIRX_SIZE ; ++i)
		{
			for (int j = 0 ; j< MATIRX_SIZE ;++j)
			{
				cout << m_puzzelMatrix[i][j] <<" ";
			}
			cout<<"\n";
		}
		cout<<"**************";
	}
}