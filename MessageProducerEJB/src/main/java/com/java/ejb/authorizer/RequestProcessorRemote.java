package com.java.ejb.authorizer;

import javax.ejb.Remote;

import com.java.ejb.authorizer.vo.RequestVO;

@Remote
public interface RequestProcessorRemote {
	
	
	public void process(RequestVO request);
	public RequestVO getRequestVO();
	public String getMessage();

}
