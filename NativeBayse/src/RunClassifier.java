
public class RunClassifier {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("insert file with word list , data and lables");
		
		String WordsFile = args[0];
		//String AritcleListFile =  args[1] ;
		
		//String ClassFile =  args[2] ;
		
		Classifier classifier = new Classifier();
		
		classifier.AddWordList(WordsFile);
		
		
		
	}

}
