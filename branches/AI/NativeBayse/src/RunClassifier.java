
public class RunClassifier {

	/**
	 * @param args
	 */
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
		double count = classifier.GetPR( 25,1 );
		System.out.println("the out of count word in class " +count);
		
	}

}
