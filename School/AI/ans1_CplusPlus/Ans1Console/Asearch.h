#ifndef ASEARCH_H
#define ASEARCH_H

#include "Isearch.h"
#include <string>

using namespace std;


namespace Search
{
	class Asearch:public Isearch
	{
		public:
			Asearch(const string& inputStr);
			void PrintPhase(const int phaseNum);
		protected:
			int m_puzzelMatrix[MATIRX_SIZE][MATIRX_SIZE];
			void PrintMatrix();
	};
};


#endif //ASEARCH_H



