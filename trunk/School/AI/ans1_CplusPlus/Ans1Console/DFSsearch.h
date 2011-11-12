#ifndef DFS_SEARCH_H
#define DFS_SEARCH_H

#include "Asearch.h"

namespace Search
{
	class DFSsearch:public Asearch
	{
	public: 
		void Search() ;
		DFSsearch(const string& inputStr);
	};

}


#endif