package com.java.ejb.authorizer.response;

import javax.ejb.Remote;
import javax.jms.JMSException;
import javax.jms.QueueSender;
import javax.naming.Context;
import javax.naming.NamingException;

@Remote
public interface ResponseSender {
	
	public void sendMessage(String msg) throws JMSException;
	public void close() throws JMSException;
	public void init() throws JMSException,NamingException;

}
