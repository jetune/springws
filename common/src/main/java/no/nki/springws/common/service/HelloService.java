package no.nki.springws.common.service;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.WebMethod;

import no.nki.springws.common.domain.Admin;
import no.nki.springws.common.service.exception.BusinessException;

@WebService
public interface HelloService {

	@WebMethod
	public String sendText(@WebParam(name="text") String text);
	
	@WebMethod
	public String sendTextToAdmin(@WebParam(name="text") String text, Admin admin);
	
	@WebMethod
	public Admin getAdmin(@WebParam(name="email") String email);
	
	@WebMethod
    public void makeBusinessException() throws BusinessException;
    
}
