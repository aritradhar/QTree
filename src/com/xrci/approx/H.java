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

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class H {

	String P;
	Random rand;
	List<Integer[]> A;
	int k, maxlan;
	HashMap<Character, Integer[]> charMap;
	
	/**
	 * 
	 * @param P input string
	 * @throws UnsupportedEncodingException
	 */
	public H(String P) throws UnsupportedEncodingException
	{
		this.P = P;
		this.rand = new Random();
		preprocess();
	}
	/**
	 * 
	 * @param P input string
	 * @param k number of hash
	 * @param maxlen max length of association rule 
	 * @throws UnsupportedEncodingException
	 */
	public H(String P, int k, int maxlen) throws UnsupportedEncodingException
	{
		this.P = P;
		this.k = k * 5;
		this.maxlan = maxlen;
		this.rand = new Random();
		preprocess();
	}
	
	/**
	 * 
	 * @param k number of hash
	 * @param maxlen max length of association rule 
	 * @throws UnsupportedEncodingException
	 */
	public H(int k, int maxlen) throws UnsupportedEncodingException
	{
		this.k = k;
		this.maxlan = maxlen;
		this.rand = new Random();
		preprocess();
	}
	
	private void preprocess()
	{
		A = new ArrayList<Integer[]>();
		for(int i = 0; i < k; i++)
		{
			Integer[] temp = new Integer[this.maxlan];
			for(int j = 0; j < this.maxlan; j++)
				temp[j] = (this.rand.nextBoolean()) ? 1 : 0;
			
			A.add(temp);
		}
		
		charMap = new HashMap<>();
		String charSet = "abcdefghijklmnopqrstuvwxyz";
		
		for(Character c : charSet.toCharArray())
		{
			int cI = c - 97;
			String bStr = Integer.toBinaryString(cI);
			bStr = new StringBuilder(bStr).reverse().toString();
			Integer[] vector = new Integer[5];
			Arrays.fill(vector, 0);
			
			int i = 0;
			for(char cB : bStr.toCharArray())
				vector[i++] = (cB == '1') ? 1 : 0;
			
			this.charMap.put(c, vector);
		}
	}
	
	public int[] process(String P) throws UnsupportedEncodingException
	{
		this.P = P;
		return this.process();
	}
	
	public int[] process() throws UnsupportedEncodingException
	{		
		int[] hash = new int[this.k];
		
		if(this.P == null)
			throw new RuntimeException("Message string is null");

		//byte[] bytes = this.P.getBytes("ISO-8859-1");
		int out = 0, i = 0;
		
		for(Integer[] a : this.A)
		{
			out = 0;
			for(char c : this.P.toCharArray())
			{
				Integer[] pc = this.charMap.get(c);
				out += Utils.innerProduct(pc, a);
			}
			
			hash[i++] = (out % 2 == 0) ? 0 : 1;
		}
		return hash;
	}

	public void reset()
	{
		this.P = null;
	}
	
	
	

	//test
	public static void main(String[] args) throws UnsupportedEncodingException {

		int[] a = {1,2,3,4};
		float[] d = Utils.normalize_1(a);
		
		H h = new H("abc", 4, 10);
		int[] hash = h.process();
		
		for(int hs : hash)
			System.out.print(hs + " ");
		
		System.out.println();
		
		hash = h.process("abc");
		
		for(int hs : hash)
			System.out.print(hs + " ");
		
		System.out.println();
		
		hash = h.process("abd");
		
		for(int hs : hash)
			System.out.print(hs + " ");
		
		System.out.println("\n" + Utils.convert(hash));
		
	}
}
