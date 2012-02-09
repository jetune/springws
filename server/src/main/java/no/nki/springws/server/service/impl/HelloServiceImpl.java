package no.nki.springws.server.service.impl;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import no.nki.springws.server.domain.Admin;
import no.nki.springws.server.domain.impl.AdminImpl;
//import no.nki.springws.server.interceptor.ExceptionInterceptor;
import no.nki.springws.server.service.HelloService;
import no.nki.springws.server.service.exception.BusinessException;

@WebService(endpointInterface = "no.nki.springws.server.service.HelloService")
public class HelloServiceImpl implements HelloService {

	private static Logger logger = LoggerFactory.getLogger(HelloServiceImpl.class);

	@Override
	public String sendText(String text) {
		return text;
	}

	@Override
	public String sendTextToAdmin(String text, Admin admin) {
		return text + admin.getFirstname() + " " + admin.getLastname();
	}

	@Override
	public Admin getAdmin(String email) {
		AdminImpl admin = new AdminImpl();
		admin.setEmail(email);
		admin.setFirstname("Stephane");
		admin.setLastname("Eybert");
		admin.setLogin("stephane");
		admin.setPassword("toto");
		return admin;
	}

	@Override
    public void makeBusinessException() throws BusinessException {
		logger.debug("===================>>> Making a business exception.");
    	throw new BusinessException();
    }
    
}
