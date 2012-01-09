import java.io.*;
import java.util.*;
import java.text.*;

/**
 *  This class contains a <tt>main</tt> for running MCMC and printing
 *  the results periodically.  You are welcome to write your own
 *  <tt>main</tt>, but your MCMC code must also work correctly with
 *  the <tt>main</tt> provided here.
 */
public class RunSampling {

   
    public static void main(String argv[])
	throws FileNotFoundException, IOException {

	int numSamples = 0;
	
	String bn_filename = null;
	String query_filename = null;

	try {
	    bn_filename = argv[0];
	    query_filename = argv[1];
	    numSamples = Integer.parseInt(argv[2]);
	   // freq = Integer.parseInt(argv[3]);
	} catch(Exception e) {
	    System.err.println("Arguments: <bn_filename> <query_filename> <max_iterations> <printing_freq>");
	    return;
	}

	BayesNet bn = new BayesNet(bn_filename);

	Query q = new Query(bn, query_filename);

	Sampling sample = new Sampling(bn, q);

	System.out.println("-------------------------------------");
	System.out.print("runnig sampling file "+bn_filename);
	System.out.print(" with query file "+ query_filename);
	System.out.print("\nnumber of samples : ");
	System.out.print(numSamples);
	sample.runMoreIterations(numSamples);
	

    }

}
