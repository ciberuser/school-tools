#ifndef ISEARCH_H
#define ISEARCH_H

#define  MATIRX_SIZE 3 

namespace Search
{

	class Isearch
	{
	public:
		virtual void Search() = 0;
		virtual void PrintPhase(const int phaseNum) = 0;
	};
};

#endif //ISEARCH_H
