package com.java.ejb.authorizer.parser;

public class ParserHelper {
	
	StringBuilder buffer;
	
	public ParserHelper()
	{
		this.buffer=new StringBuilder();
	}
	public void append(String str)
	{
		buffer.append(str);
		buffer.append("|");
	}
	
	public void append(long a)
	{
		buffer.append(a);
		buffer.append("|");
	}
	
	public String nullToEmpty(String a)
	{
		if(a==null)
		{
		return "";	
		}
		else
			return a.trim();
	}
	
	public String nullToEmpty(long a)
	{
		if(a==0)
		{
		return "0";	
		}
		else
			return Long.toString(a);
	}


}
