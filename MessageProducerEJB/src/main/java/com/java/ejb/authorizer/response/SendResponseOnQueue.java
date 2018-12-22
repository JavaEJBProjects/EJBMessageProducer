package com.java.ejb.authorizer.response;

import java.util.Properties;

import javax.ejb.Stateless;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.java.ejb.constant.JNDIConstant;

@Stateless(mappedName = "remote/response")
public class SendResponseOnQueue implements ResponseSender {
	
	private QueueConnectionFactory qFactory;
	private QueueConnection qCon;
	private QueueSession qSession;
	private Queue queue;
	private QueueSender qSender;
	private TextMessage message;
    
	@Override
	public void sendMessage(String msg) throws JMSException {
		message.setText(msg);
		qSender.send(message);
		System.out.println("Message Deliverd Successfully");
		
	}

	@Override
	public void close() throws JMSException {
		qSender.close();
	    qSession.close();
	    qCon.close();
		
	}

	@Override
	public void init() throws JMSException,
			NamingException {
		Context ctx = null;
	    String host = "localhost";
	    String port = "3700";
	    Properties props = new Properties();

	    props.setProperty("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
	    props.setProperty("java.naming.factory.url.pkgs", "com.sun.enterprise.naming");
	    props.setProperty("java.naming.factory.state", "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
	    props.setProperty("org.omg.CORBA.ORBInitialHost", host);
	    props.setProperty("org.omg.CORBA.ORBInitialPort", port);
		
    ctx=new InitialContext(props);
	qFactory=(QueueConnectionFactory) ctx.lookup(JNDIConstant.QUEUE_FACTORY);	
	qCon=qFactory.createQueueConnection();
	qSession = qCon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
	queue=(Queue)ctx.lookup(JNDIConstant.QUEUE);
	qSender = qSession.createSender(queue);
    message=qSession.createTextMessage();
    qCon.start();
		
	}

	

}
