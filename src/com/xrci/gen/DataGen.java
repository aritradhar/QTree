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


package com.xrci.gen;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.xrci.qTree.AssociationRule;

public class DataGen 
{
	public static final int MASTER_TABLE_ASSOC_LEVEL = 5;
	
	public static final String SYMBOL = "abcdefghijklmnopqrstuvwxyz"; 
	public static Random rand = new Random();
	public static Set<String> lst = new HashSet<>();

	public static ConcurrentHashMap<Integer, List<AssociationRule>> ruleTables = 
			new ConcurrentHashMap<Integer, List<AssociationRule>>();

	public static void makeAssociationRuleTables(int maxlen, int count)
	{
		List<AssociationRule> associationRules = makeAssoc(maxlen, count);

		for(AssociationRule associationRule : associationRules)
		{
			String[] p1 = associationRule.p1;
			int assocRuleLength = p1.length;
			if(!ruleTables.containsKey(assocRuleLength))
			{
				List<AssociationRule> tempList = new ArrayList<>();
				tempList.add(associationRule);
				ruleTables.put(assocRuleLength, tempList);
			}
			else
			{
				List<AssociationRule> tempList = ruleTables.get(assocRuleLength);
				tempList.add(associationRule);
			}
		}

		//System.out.println();
	}

	public static List<AssociationRule> matchAssoc(String transaction,  Set<String> l, int len, double weight)
	{
		Set<String> transactionHS = new HashSet<>();
		for(Character c : transaction.toCharArray())
			transactionHS.add(c.toString());


		List<AssociationRule> out = new ArrayList<>();
		//Set<String> l = makeSubTrans(transaction.toCharArray(), len);

		for(String subTrans : l)
		{
			int lenTemp = subTrans.length();
			List<AssociationRule> rules = ruleTables.get(lenTemp);

			for(AssociationRule associationRule : rules)
			{
				String[] p = associationRule.p1;
				int counter = 0;
				for(String symbol : p)
				{
					if(transactionHS.contains(symbol))
						counter++;
					else
						break;
				}
				if(counter == lenTemp && associationRule.weight >= weight)
					out.add(associationRule);
			}
		}

		return out;
	}


	public static List<AssociationRule> makeAssoc(int maxlen, int count)
	{
		List<AssociationRule> out = new ArrayList<AssociationRule>();
		Random rand = new Random();

		for(int i = 0; i < count; i++)
		{
			AssociationRule assoc= new AssociationRule();

			int len = rand.nextInt(maxlen + 1);
			if(len == 0)
				len++;

			int len1 = rand.nextInt(maxlen + 1);
			if(len1 == 0)
				len1++;

			String[] p1 = new String[len];
			String[] p2 = new String[len1];

			for(int j = 0; j < len; j++)
				p1[j] = new Character(SYMBOL.charAt(rand.nextInt(26))).toString();


			for(int j = 0; j < len1; j++)
				p2[j] = new Character(SYMBOL.charAt(rand.nextInt(26))).toString();

			assoc.p1 = p1;
			assoc.p2 = p2;
			assoc.weight = rand.nextDouble();

			out.add(assoc);
		}

		return out;
	}

	public static String[] makeTransaction(int len)
	{
		String[] p = new String[len];
		for(int i = 0; i < len; i++)
			p[i] =  new Character(SYMBOL.charAt(rand.nextInt(26))).toString();

		return p;
	}


	static void printAllKLength(char set[], int k) {
		int n = set.length;        
		printAllKLengthRec(set, "", n, k);
	}

	static void printAllKLengthRec(char set[], String prefix, int n, int k) 
	{

		if (k == 0) 
		{
			char[] temp = prefix.toCharArray();
			Arrays.sort(temp);
			StringBuffer sb = new StringBuffer(new String(temp));
			removeDuplicates(sb);
			//System.out.println(sb);
			prefix = sb.toString();
			lst.add(prefix);
			return;
		}

		for (int i = 0; i < n; ++i) 
		{
			String newPrefix = prefix + set[i]; 
			printAllKLengthRec(set, newPrefix, n, k - 1); 
		}
	}


	public static Set<String> makeSubTrans(char[] transaction, int k)
	{
		for(int i = 1; i <= k; i++)
		{
			printAllKLength(transaction, i);
		}
		return lst;
	}


	public static void removeDuplicates(StringBuffer str) {                        

		int len = str.length();
		if (len < 2) {                         
			return;
		}

		int tail = 1;

		for (int i = 1; i < len; ++i) 
		{
			int j;
			for (j = 0; j < tail; ++j) 
			{
				if (str.charAt(i) == str.charAt(j)) 
				{
					break;
				}      
			}
			if (j == tail) 
			{                       
				str.setCharAt(tail, str.charAt(i));
				++tail;
			}
		}
		str.setLength(tail);
	}

	public static boolean contain(AssociationRule ar, String t)
	{
		HashSet<String> set = new HashSet<>();

		for(char c : t.toCharArray())
		{
			set.add(new Character(c).toString());
		}
		return contain(ar, set);
	}
	public static boolean contain(AssociationRule ar, HashSet<String> t)
	{
		String[] p = ar.p1;
		int len = p.length;

		int i = 0;
		for(String symbol : p)
		{
			if(t.contains(symbol))
				i++;
		}

		if(len == i)
			return true;

		return false;
	}
	
	public static String makeTransactionR(int len)
	{
		return SYMBOL.substring(0, len);
	}

	public static void main(String[] args) throws IOException {

		FileWriter fw = new FileWriter("result.txt");
		
		int[] test = 
			{
				1000, 5,3,
				1000, 5,5,
				1000, 10,3,
				1000, 10,5,
				1000, 15,3,
				1000, 15,5,
				1000, 20,3,
				1000, 20,5,
				
				10000, 5,3,
				10000, 5,5,
				10000, 10,3,
				10000, 10,5,
				10000, 15,3,
				10000, 15,5,
				10000, 20,3,
				10000, 20,5,

				50000, 5,3,
				50000, 5,5,
				50000, 10,3,
				50000, 10,5,
				50000, 15,3,
				50000, 15,5,
				50000, 20,3,
				50000, 20,5,

				100000, 5,3,
				100000, 5,5,
				100000, 10,3,
				100000, 10,5,
				100000, 15,3,
				100000, 15,5,
				100000, 20,3,
				100000, 20,5,

				500000, 5,3,
				500000, 5,5,
				500000, 10,3,
				500000, 10,5,
				500000, 15,3,
				500000, 15,5,
				500000, 20,3,
				500000, 20,5,

				1000000, 5,3,
				1000000, 5,5,
				1000000, 10,3,
				1000000, 10,5,
				1000000, 15,3,
				1000000, 15,5,
				1000000, 20,3,
				1000000, 20,5,
			};

		int i = 0;
		//DataGen.makeAssociationRuleTables(5, 500000);
		//System.err.println("---------------");
		while(i != test.length)
		{
			System.out.println(test[i]);
			fw.append(new Integer(test[i]).toString() + "|");
			DataGen.makeAssociationRuleTables(MASTER_TABLE_ASSOC_LEVEL, test[i++]);


			System.out.println(test[i]);
			fw.append(new Integer(test[i]).toString() + "|");
			String transaction = makeTransactionR(test[i++]);

			System.out.println(test[i]);
			fw.append(new Integer(test[i]).toString() + "|");
			
			Set<String> tmp = makeSubTrans(transaction.toCharArray(), test[i]);
			Set<String> t = new HashSet<>();
			int p = 0;
			for(String st : tmp)
			{
				if(p == 10)
					break;
				t.add(st);
				p++;
			}
			
			long start = System.currentTimeMillis();

			matchAssoc(transaction, t,  test[i++], 0.35d);
			//i++;
			long end = System.currentTimeMillis() - start;

			//System.out.println("Matching transactions : " + matched.size());
			System.out.println("Matching time :" + end + " ms");
			fw.append(new Long(end).toString() + "\n");
			
			System.gc();
		}
		
		fw.close();

	}
}
