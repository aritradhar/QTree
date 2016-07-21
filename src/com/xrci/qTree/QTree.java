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

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class QTree 
{
	public Node ROOT;
	Map<String, Node> topItemTable;
	
	public QTree()
	{
		this.ROOT = new Node("Root");
		topItemTable = new HashMap<>();
	}
	
	public void insertAssociationRule(AssociationRule ar) throws NoSuchAlgorithmException
	{
		this.insert(ar);
	}
	
	public void insert(AssociationRule ar) throws NoSuchAlgorithmException
	{
		int counter = 0;
		
		Node temp = this.ROOT;
		
		String[] alphabets = ar.p1;

		for(String alphabet : alphabets)
		{
			if(temp.children.containsKey(alphabet))
			{
				temp.rules.add(ar);
				temp = temp.children.get(alphabet);
				continue;
			}
			Node newNode = new Node(alphabet);
			temp.children.put(alphabet, newNode);
			if(counter == 0)
				this.topItemTable.put(newNode.alphabet, newNode);
			temp.calculateHash();
			temp.rules.add(ar);
			temp = newNode;		
			temp.rules.add(ar);
			++counter;
		}
	}
	
	public List<AssociationRule> getchAssociationRule(String[] transaction, double th)
	{
		Set<String> transactionSet = new HashSet<>();
		for(String item : transaction)
			transactionSet.add(item);
		
		List<AssociationRule> out = new ArrayList<AssociationRule>();
		Node temp = this.ROOT;
		if(temp.isLeaf())
			return out;
		
		for(String item : transaction)
		{
			if(!this.topItemTable.containsKey(item))
				continue;
			
			Node entryNode = this.topItemTable.get(item);
			if(entryNode.weight < th)
				continue;
			
			while(true)
			{
				
			}
		}
		
		for(String item : transaction)
		{
			if(temp.isLeaf())
				out.addAll(temp.rules);
			Node nextItem = temp.searchChild(item);
			if(nextItem == null)
				continue;
			
			if(nextItem.alphabet.equals(item) && temp.weight >= th)
				temp = nextItem;
		}
		return out;
	}
	
}
