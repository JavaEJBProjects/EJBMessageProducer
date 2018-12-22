package com.java.ejb.authorizer.parser;

import com.java.ejb.authorizer.vo.RequestVO;

public class AuthParser
{

	public String delimiter="|";
	ParserHelper parser=new ParserHelper();
	
	public StringBuilder toRequest(RequestVO req) 
	{
		parser.append(req.getName());
		parser.append(req.getAddress());
		parser.append(req.getMobile());	
		StringBuilder buffer=parser.buffer;
		return buffer;
	}
}
