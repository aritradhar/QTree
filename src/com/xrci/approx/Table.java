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
import java.util.HashMap;

public class Table {
	
	HashMap<Long, ArrayList<String>> Table;
	H h;
	
	public Table(int k, int maxlen) throws UnsupportedEncodingException
	{
		this.Table = new HashMap<>();
		this.h = new H(k, maxlen); 
	}

	public void insert(String P) throws UnsupportedEncodingException
	{
		long hash = Utils.convert(h.process(P));
		
		ArrayList<String> str = null;
		if(this.Table.containsKey(hash))
		{
			str = this.Table.get(hash);
			str.add(P);
		}
		else
		{
			str = new ArrayList<>();
			str.add(P);
			this.Table.put(hash, str);
		}
		
	}
}
