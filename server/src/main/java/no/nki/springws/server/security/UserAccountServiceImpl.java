package no.nki.springws.server.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import no.nki.springws.common.security.UserAccount;
import no.nki.springws.common.security.UserAccountService;

public class UserAccountServiceImpl implements UserAccountService {

	private static Logger logger = LoggerFactory.getLogger(UserAccountServiceImpl.class);

	@Override
	public String getUserPasswordFromRemotePasswordRepository(String username) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "spring-server-properties.xml", "user-account-server.xml", "user-account-services.xml" });
		UserAccount userAccount = (UserAccount) context.getBean("userAccount");
		logger.debug("=================>>> The server is using the username: " + username + " and the password: " + userAccount.getPassword());
		if (username.equals(userAccount.getUsername())) {
			return userAccount.getPassword();
		} else {
			return null;
		}
	}

}
