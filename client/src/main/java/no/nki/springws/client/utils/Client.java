package no.nki.springws.client.utils;

import java.net.URL;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import no.nki.springws.server.domain.Admin;
import no.nki.springws.server.domain.impl.AdminImpl;
import no.nki.springws.server.service.HelloService;

public final class Client {

	// To start the client:
	// java -cp
	// /home/stephane/programs/apache-tomcat/webapps/springws/WEB-INF/lib/*:server/target/springws-server-1.0-SNAPSHOT.jar:client/target/springws-client-1.0-SNAPSHOT.jar
	// no.nki.springws.client.utils.Client
	public static void main(String args[]) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "spring-client-beans.xml" });
		HelloService helloService = (HelloService) context.getBean("clientService");
		System.out.println(helloService.sendText("A little web service"));
		Admin admin = new AdminImpl("ste@nki.no", "Stephane", "Eybert", "stephane", "mypass");
		System.out.println(helloService.sendTextToAdmin("Hello ", admin));
		Admin retrievedAdmin = helloService.getAdmin("mittiprovence@yhoo.se");
		System.out.println("Retrieved an admin from the server: " + retrievedAdmin.getFirstname() + " " + retrievedAdmin.getLastname());
	}

}
