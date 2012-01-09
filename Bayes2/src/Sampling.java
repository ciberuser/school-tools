import java.io.*;
import java.util.*;
import java.text.*;
import java.util.Random;

import com.sun.org.apache.bcel.internal.generic.GETSTATIC;

/**
 *  This class is a template for implementing the MCMC algorithm.  You
 *  need to fill in the constructor and the
 *  <tt>runMoreIterations()</tt> method.
 */
public class Sampling {

    /**
     *  This is the constructor for the class that you need to fill
     *  in.  Any initialization of the MCMC algorithm should go here.
     *  The parameters to the constructor specify the Bayes net and
     *  query on which MCMC is to be run.
     */
	
	private int GetRandomNum()
	{
		Random genNum= new Random();
    	return genNum.nextInt(2)-1;
	}
	
	private int[] GenSample(int numVarible)
	{
		int[] sample = new int[numVarible];
		for(int i= 0 ; i< numVarible ; i++)
		{
			sample[i] = GetRandomNum();
		}
		return sample;
	}
	
	private BayesNet m_bayesNet ;
	private Query m_query ;
	
	
	
    public Sampling(BayesNet bn, Query q)
    {
    	m_bayesNet = bn;
    	m_query =q;
    	/*
        int quertVar = q.queryVar;
        
    	for (int i =0; i< bn.numVariables ; i++)
    	{
    		if (bn.numParents[i]==0) 
    	}
    	    	
    	for (int i = 0; i<q.evidence.length ; ++i)
    	{
    		System.out.println(q.evidence[i]);
    	}
    	
    	System.out.println("query var : "+ q.queryVar);
    	for (int i=0;i<bn.numVariables ; i++)
    	{
    		System.out.println(bn.varName[i]);
    		
    	}
    	*/
    	
    	
	// fill in initialization code here
    		
    }

    
    public double runMoreIterations(int n)
    {
    	if (m_bayesNet.numVariables ==0) return 0;
    	else
    	{
    		
    		float queryVarCount = 0;
    		float contConditionCount =0;
    		int[] sample;
    		for(int i = 0; i<n ; i++)
    		{
    			 sample= GenSample(m_bayesNet.numVariables);
    			
    			 boolean testCon = true;
    			 for(int j = 0 ; j< m_query.evidence.length ;j++)
    			 {
    				 if (j != m_query.queryVar )
    				 {
    					 testCon &= (m_query.evidence[j] == sample[j] );
    				 }
    				 
    			 }
    			 if (testCon)
    				 {
    				 	contConditionCount++;
    				 	 if (sample[m_query.queryVar] == 0) queryVarCount++;
    				 }
    		}
    		float result = (queryVarCount/contConditionCount);
    		
    		return result;
    	}
    	//int[] sample = GenSample(
    	
    	//double[] a = new double[2];
    	//return a;

    }

}
