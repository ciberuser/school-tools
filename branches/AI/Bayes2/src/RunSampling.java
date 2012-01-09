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

	int max_iters = 0;
	
	String bn_filename = null;
	String query_filename = null;

	try {
	    bn_filename = argv[0];
	    query_filename = argv[1];
	    max_iters = Integer.parseInt(argv[2]);
	   // freq = Integer.parseInt(argv[3]);
	} catch(Exception e) {
	    System.err.println("Arguments: <bn_filename> <query_filename> <max_iterations> <printing_freq>");
	    return;
	}

	BayesNet bn = new BayesNet(bn_filename);

	Query q = new Query(bn, query_filename);

	Sampling sample = new Sampling(bn, q);

	NumberFormat nf = new DecimalFormat("0.00000000");
	
	System.out.print("the sampling result :");
	System.out.print(nf.format( sample.runMoreIterations(max_iters) ));
	

    }

}
