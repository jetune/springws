package no.nki.springws.client.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import no.nki.springws.common.domain.Admin;
import no.nki.springws.common.domain.impl.AdminImpl;
import no.nki.springws.common.service.HelloService;
import no.nki.springws.common.service.exception.BusinessException;

public final class Client {

	private static Logger logger = LoggerFactory.getLogger(Client.class);

	public static void main(String args[]) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "spring-client-properties.xml", "spring-client.xml", "user-account-client.xml", "user-account-services.xml" });
		HelloService helloService = (HelloService) context.getBean("helloService");
		logger.debug(helloService.sendText("A little web service"));
		Admin admin = new AdminImpl("ste@nki.no", "Stephane", "Eybert", "stephane", "mypass");
		logger.debug(helloService.sendTextToAdmin("Hello ", admin));
		Admin retrievedAdmin = helloService.getAdmin("mittiprovence@yhoo.se");
		logger.debug("The web service returned the administrator: " + retrievedAdmin.getFirstname() + " " + retrievedAdmin.getLastname());
		try {
			helloService.makeBusinessException();
		} catch (BusinessException e) {
			logger.debug("==============>>> Business exception message: " + e.getMessage());
			return;
		}

	}

}
