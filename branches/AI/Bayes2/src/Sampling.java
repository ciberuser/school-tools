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
	
	private double GetRandomNum()
	{
		Random genNum= new Random();
		  	return genNum.nextDouble();
	}
	
	private int[] GenSample( BayesNet bn)
	{
		int numVariables = bn.numVariables;
		int[] sample = new int[numVariables];
		
		for(int i= 0 ; i< numVariables ; i++)
		{
			sample[i] = GetValue(bn,GetRandomNum(),i,sample);
		}
		for (int i=0; i<sample.length ;i++) sample[i]--;
		return sample;
	}
	
	
	private int GetValue(BayesNet bn,double d ,int i,int[] sample)
	{
		
		double dub = bn.getCondProb(i,sample);
		if (dub > d) return 1;
		else return 0;
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
    	
    	if (m_bayesNet.numVariables == 0) return 0;
    	else
    	{
    		float queryVarCount = 0;
    		float contConditionCount =1;
    		int[] sample;
    		for(int i = 0; i<n ; i++)
    		{
    			 sample= GenSample(m_bayesNet);
    			
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
    		System.out.print((int)queryVarCount);
    		System.out.print("/");
    		System.out.print((int)contConditionCount);
    		System.out.print(" = ");
    		return result;
    	}
    	//int[] sample = GenSample(
    	
    	//double[] a = new double[2];
    	//return a;

    }

}
