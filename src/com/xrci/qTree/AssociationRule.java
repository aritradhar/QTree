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

public class AssociationRule 
{
	public String[] p1;
	public String[] p2;
	
	public double weight;
	
	public AssociationRule(String[] p1, String[] p2)
	{
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public AssociationRule()
	{
		
	}
	
	public String getPreString()
	{
		StringBuffer sb = new StringBuffer();
		int i = 0;
				
		for(String p : p1)
		{
			if(i == 0)
				sb.append(p);
			else
				sb.append(",").append(p);
			++i;
		}
		
		return sb.toString();
	}
	
	public void reset()
	{
		p1 = null;
		p2 = null;
	}
	@Override
	public String toString() {
		
		StringBuffer sb = new StringBuffer();
		int i = 0;
				
		for(String p : p1)
		{
			if(i == 0)
				sb.append(p);
			else
				sb.append(",").append(p);
			++i;
		}
		
		sb.append("->");
		
		i = 0;
		
		for(String p : p2)
		{
			if(i == 0)
				sb.append(p);
			else
				sb.append(",").append(p);
			
			++i;
		}
		return sb.toString();
	}
}
