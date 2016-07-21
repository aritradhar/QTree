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


package com.xrci.qTree;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Node 
{
	public String alphabet;
	double weight;
	Map<String, Node> children;
	public List<AssociationRule> rules;
	byte[] hash;
	
	public Node(String alphabet)
	{
		this.alphabet = alphabet;
		this.weight = 0;
		this.children = new HashMap<>();
		rules = new ArrayList<AssociationRule>();
	}
	
	public void calculateHash() throws NoSuchAlgorithmException
	{
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		double wg = this.children.size();
		byte[] bytes = (this.alphabet.concat(new Double(wg).toString())).getBytes();
		this.hash = digest.digest(bytes);
	}
	
	public double getWeight()
	{
		return this.children.size();
	}
	
	public Node searchChild(String item)
	{
		if(this.isLeaf())
			return null;
		if(!this.children.containsKey(item))
			return null;
		
		return this.children.get(item);
	}
	
	public boolean isLeaf()
	{
		return this.children.size() == 0;
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.alphabet.equals(((Node)obj).alphabet);
	}
	
	@Override
	public String toString() {
		return this.alphabet;
	}
}
