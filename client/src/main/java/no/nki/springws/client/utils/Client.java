package no.nki.springws.client.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import no.nki.springws.server.domain.Admin;
import no.nki.springws.server.domain.impl.AdminImpl;
import no.nki.springws.server.service.HelloService;
import no.nki.springws.server.service.exception.BusinessException;

public final class Client {

	private static Logger logger = LoggerFactory.getLogger(Client.class);

	// To start the client:
	// java -cp
	// /home/stephane/programs/apache-tomcat/webapps/springws/WEB-INF/lib/*:server/target/springws-server-1.0-SNAPSHOT.jar:client/target/springws-client-1.0-SNAPSHOT.jar
	// no.nki.springws.client.utils.Client
	public static void main(String args[]) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "spring-client-beans.xml" });
		HelloService helloService = (HelloService) context.getBean("clientService");
		logger.debug(helloService.sendText("A little web service"));
		Admin admin = new AdminImpl("ste@nki.no", "Stephane", "Eybert", "stephane", "mypass");
		logger.debug(helloService.sendTextToAdmin("Hello ", admin));
		Admin retrievedAdmin = helloService.getAdmin("mittiprovence@yhoo.se");
		logger.debug("Retrieved an administrator from the server: " + retrievedAdmin.getFirstname() + " " + retrievedAdmin.getLastname());
		try {
			helloService.makeBusinessException();
		} catch (BusinessException e) {
			logger.debug("==============>>> Business exception: " + e.getMessage() + " " + e.getCause());
			return;
		}

	}

}
