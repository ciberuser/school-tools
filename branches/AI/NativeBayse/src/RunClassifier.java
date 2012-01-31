
import java.math.*;

public class RunClassifier {

	
	static final int BEST_WORD = 10; 
	static int[] BestWordsIndexs = new int[BEST_WORD];
	static double[] logResults = new double[BEST_WORD];
	
	static void Init()
	{
		for(int i = 0; i<BEST_WORD ; ++i)
		{
			BestWordsIndexs[i] = 0;
			logResults[i] = 0;
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("insert file with word list , data and lables");
		
		String WordsFile = args[0];
		String AritcleListFile =  args[1] ;
		String classFile =  args[2] ;
		
		Classifier classifier = new Classifier();
		
		classifier.AddWordList(WordsFile);
		classifier.Words2Articles(AritcleListFile);
		classifier.ClassArticle(classFile);
		
		System.out.println("the out of count word in class");
		FindWords(classifier);
		//double count = classifier.GetPR( 25,1 );
		for (int i =0 ; i< BEST_WORD;++i)
		{
			//System.out.println("word res :" +logResults[i]);
			System.out.println("word index:" +BestWordsIndexs[i]);	
		}
		
		
	}


	static void FindWords(Classifier classfier)
	{
		
		int countWords = classfier.GetCountOfWords();
		for (int i = 1 ; i < countWords ; i++)
		{ 
		   double res =	Math.log(classfier.GetPR(i, 1)) - Math.log(classfier.GetPR(i,2));
		   
		   double resLog=  Math.abs(res);
		   
		   for(int j = 0 ; j<BEST_WORD ; j++)
		   {
			   if (logResults[j] < resLog || logResults[j]==0 )
			   {
				  logResults[j] = resLog;
				  BestWordsIndexs[j] = i;
				  break;
			   }
		   }
		}
		
	}
	
}


