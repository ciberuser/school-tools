
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
		String testData = args[3];
		String testLabel = args[4];
		
		Classifier classifier = new Classifier(WordsFile,AritcleListFile,classFile);
		
		
		FindWords(classifier);
		System.out.println("2 )the 10 most discriminative word features : ");
		for (int i = 0 ; i < BestWordsIndexs.length ; i++)
		{
			System.out.println(BestWordsIndexs[i]+" : " + classifier.GetWord(BestWordsIndexs[i]));
			
		}
		
		System.out.println("\n3)learing data acorrding to "+AritcleListFile + " and "+ classFile);
		System.out.println("accuracy of train data :" + classifier.Learn());
		
		System.out.println("evaluating the classifier according to files: " +testData +" and "+ testLabel);
		double success = classifier.EvaluateData(testData, testLabel);
		System.out.println("classifier success to classify at accuracy of :" +success);
			
		
		
	}
	
	static void FindWords2(Classifier classfier)
	{
		
	}

	static void FindWords(Classifier classfier)
	{
		
		int countWords = classfier.GetCountOfWords();
		for (int i = 1 ; i < countWords ; i++)
		{ 
		   double res =	Math.log(classfier.GetPR(i, 1)) - Math.log(classfier.GetPR(i,2));
		   
		   double resLog=Math.abs(res);
		   
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


