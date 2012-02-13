package no.nki.springws.client.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import no.nki.springws.common.security.UserAccount;
import no.nki.springws.common.security.UserAccountService;
import no.nki.springws.common.service.HelloService;

public class UserAccountServiceImpl implements UserAccountService {

	private static Logger logger = LoggerFactory.getLogger(UserAccountServiceImpl.class);

	@Override
	public String getUserPasswordFromRemotePasswordRepository(String username) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "spring-client-properties.xml", "user-account-client.xml", "user-account-services.xml" });
		UserAccount userAccount = (UserAccount) context.getBean("userAccount");
		logger.debug("=================>>> For the username: " + username + " got the password: " + userAccount.getPassword());
		if (username.equals(userAccount.getUsername())) {
			return userAccount.getPassword();
		} else {
			return null;
		}
	}

}
