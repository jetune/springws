package no.nki.springws.server.service;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;

import no.nki.springws.common.service.HelloService;
import no.nki.springws.common.domain.Admin;
import no.nki.springws.common.domain.impl.AdminImpl;
import no.nki.springws.common.service.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloServiceTest extends AbstractWebServiceTest {

	private static Logger logger = LoggerFactory.getLogger(HelloServiceTest.class);

	@Autowired
	private HelloService helloIntegrationService;

	private Admin admin0;

	protected void setHelloIntegrationService(HelloService helloIntegrationService) {
		this.helloIntegrationService = helloIntegrationService;
	}

	public HelloServiceTest() {
	}

	@Before
	public void beforeAnyTest() throws Exception {
	}

	@Test
	public void testSendText() {
		String text = "A little web service";
		assertEquals(text, helloIntegrationService.sendText(text));
	}

	@Test
	public void testSendTextToAdmin() {
		String text = "Hello ";
		admin0 = new AdminImpl("ste@nki.no", "Stephane", "Eybert", "stephane", "mypass");
		assertEquals(text + admin0.getFirstname() + " " + admin0.getLastname(), helloIntegrationService.sendTextToAdmin(text, admin0));
	}

	@Test
	public void testgetAdmin() {
		String email = "mittiprovence@yhoo.se";
		Admin retrievedAdmin = helloIntegrationService.getAdmin(email);
		assertEquals(email, retrievedAdmin.getEmail());
	}
	
	@Test
	public void testMakeBusinessException() {
		try {
			helloIntegrationService.makeBusinessException();
		} catch (BusinessException e) {
			logger.debug("==============>>> The business exception: " + e.getMessage());
			return;
		}
		fail("The expected business exception was not thrown.");
	}
	
}