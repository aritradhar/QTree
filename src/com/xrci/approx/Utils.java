//*************************************************************************************
//*********************************************************************************** *
//author Aritra Dhar 																* *
//Research Engineer																  	* *
//Xerox Research Center India													    * *
//Bangalore, India																    * *
//--------------------------------------------------------------------------------- * * 
///////////////////////////////////////////////// 									* *
//The program will do the following:::: // 											* *
///////////////////////////////////////////////// 									* *
//version 1.0 																		* *
//*********************************************************************************** *
//*************************************************************************************


package com.xrci.approx;

public class Utils 
{
	
	/**
	 * Convert {@code k} length {0,1} vector to a unique {@code long} bucket identifier
	 * @param arr
	 * @return
	 */
	public static long convert(int[] arr)
	{
		long out = 0x0;
		for(int i = 0; i < arr.length; i++)
		{
			if(!(arr[i] !=0 || arr[i] != 1))
				throw new RuntimeException("Error array contain");
			
			out |= arr[i] << i;
			
		}
		return out;
	}
	
	public static int innerProduct(Integer[] P, Integer[] A)
	{
		int out = 0;
		
		for(int i = 0; i < P.length; i++)
			out += P[i] * A[i];
		
		return out;
	}
	
	public static float innerProduct(float[] P, Integer[] A)
	{
		float out = 0;
		
		for(int i = 0; i < P.length; i++)
			out += (float) P[i] * A[i];
		
		return out;
	}
	
	public static float[] doubleNormalize(int[] A)
	{
		return Utils.normalize_2(Utils.normalize_1(A));
	}
	
	public static float[] normalize_1(int[] A)
	{
		int tot = 0;
		float[] D = new float[A.length];
		
		for(Integer a : A)
			tot += a;
		
		int i = 0;
		
		for(Integer a : A)
			D[i++] = (float) a/tot;
		
		return D;
	}
	
	public static float[] normalize_2(float[] A)
	{
		float tot = 0;
		float[] D = new float[A.length];
		
		for(float d : A)
			tot += d * d;
		
		tot = (float) Math.sqrt(tot);
		
		int i = 0;
		
		for(float d : A)
			D[i++] = (float) (d/tot * Math.sqrt(1 - d*d/(tot * tot)));
		
		return D;
	}
	
	public static float[] normalize_T(Integer[] A)
	{
		float[] D = new float[A.length];
		
		int i =0;
		
		for(Integer a : A)
			D[i++] = (float) a/A.length;
		
		return D;
	}
	
}
