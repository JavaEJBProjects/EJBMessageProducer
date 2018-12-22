package com.java.web.init;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.jms.JMSException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.AbstractDocument.Content;

import com.java.ejb.authorizer.RequestProcessorRemote;
import com.java.ejb.authorizer.response.ResponseSender;
import com.java.ejb.authorizer.vo.RequestVO;

/**
 * Servlet implementation class TranProcessor
 */
@WebServlet("/TranProcessor")
public class TranProcessor extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	RequestProcessorRemote requestProcessorRemote;
	@EJB
	ResponseSender responseSender;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TranProcessor() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("name");
		String address=request.getParameter("address");
		long mobile=Long.parseLong(request.getParameter("mobile"));
        RequestVO reqVo= requestProcessorRemote.getRequestVO();
        reqVo.setName(name);
        reqVo.setAddress(address);
        reqVo.setMobile(mobile);
        requestProcessorRemote.process(reqVo);      
        System.out.println(requestProcessorRemote.getMessage());
        
        PrintWriter out=response.getWriter();
        out.println("Message:"+requestProcessorRemote.getMessage());
        try {
        	out.println("Message Is Being Sent..." );
			responseSender.init();
			responseSender.sendMessage(requestProcessorRemote.getMessage());
			responseSender.close();
			out.print("Message Has Been Sent" );
		} catch (JMSException | NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
