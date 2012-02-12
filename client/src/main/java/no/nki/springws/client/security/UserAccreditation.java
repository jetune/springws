package no.nki.springws.client.security;

import java.io.IOException;

import no.nki.springws.server.utils.UserAccountService;

import org.apache.ws.security.WSPasswordCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

public class UserAccreditation implements CallbackHandler {

	private static Logger logger = LoggerFactory.getLogger(UserAccreditation.class);

	private UserAccountService userAccountService;

	public void setUserAccountService(UserAccountService userAccountService) {
		this.userAccountService = userAccountService;
	}

	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		for (int i = 0; i < callbacks.length; i++) {
			WSPasswordCallback wspasswordCallback = (WSPasswordCallback) callbacks[i];
			String username = wspasswordCallback.getIdentifier();
			String password = userAccountService.getUserPasswordFromRemotePasswordRepository(username);
			wspasswordCallback.setPassword(password);
		}
	}

}
