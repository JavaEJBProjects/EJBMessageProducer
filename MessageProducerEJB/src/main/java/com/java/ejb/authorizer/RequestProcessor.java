package com.java.ejb.authorizer;

import javax.ejb.Stateless;

import com.java.ejb.authorizer.parser.AuthParser;
import com.java.ejb.authorizer.vo.RequestVO;

/**
 * Session Bean implementation class RequestProcessor
 */
@Stateless(mappedName = "remote/request")
public class RequestProcessor implements RequestProcessorRemote {

	private String message;
	
	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	//Parser parser=new Parser();
    /**
     * Default constructor. 
     */
    public RequestProcessor() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void process(RequestVO request) {
		
		AuthParser parser=new AuthParser();
		message=parser.toRequest(request).toString();
	}

	@Override
	public RequestVO getRequestVO() {
		// TODO Auto-generated method stub
		return new RequestVO();
	}

}
