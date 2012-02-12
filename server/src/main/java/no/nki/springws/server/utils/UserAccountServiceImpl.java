package no.nki.springws.server.utils;

public class UserAccountServiceImpl implements UserAccountService {

	@Override
	public String getUserPasswordFromRemotePasswordRepository(String username) {
		if (username.equals("stephane")) {
			return "myownpassword";
		} else {
			return null;
		}
	}

}
