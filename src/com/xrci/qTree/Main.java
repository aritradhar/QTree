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
import java.util.List;

public class Main {

	@SuppressWarnings("unused")
	public static void main(String[] args) throws NoSuchAlgorithmException {

		QTree qt = new QTree();
		qt.insert(new AssociationRule(new String[]{"a1","a2"}, new String[]{"b1"}));
		qt.insert(new AssociationRule(new String[]{"a1","a3"}, new String[]{"b2"}));
		qt.insert(new AssociationRule(new String[]{"a1","a4"}, new String[]{"b3"}));
		qt.insert(new AssociationRule(new String[]{"a2","a3"}, new String[]{"b4"}));
		qt.insert(new AssociationRule(new String[]{"a2","a4"}, new String[]{"b5"}));

		List<AssociationRule> s = qt.getchAssociationRule(new String[]{"a1", "a2", "a3"}, 0);
		
		for(AssociationRule a : s)
			System.out.println(a.toString());
		
		System.out.println();
	}

}
