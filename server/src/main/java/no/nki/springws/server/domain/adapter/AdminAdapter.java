package no.nki.springws.server.domain.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import no.nki.springws.server.domain.Admin;
import no.nki.springws.server.domain.impl.AdminImpl;

public class AdminAdapter extends XmlAdapter<AdminImpl, Admin> {

	public AdminImpl marshal(Admin admin) throws Exception {
		if (admin instanceof AdminImpl) {
			return (AdminImpl) admin;
		}
		AdminImpl adminImpl = new AdminImpl();
		adminImpl.setEmail(admin.getEmail());
		adminImpl.setFirstname(admin.getFirstname());
		adminImpl.setLastname(admin.getLastname());
		adminImpl.setLogin(admin.getLogin());
		adminImpl.setPassword(admin.getPassword());
		return adminImpl;
	}

	public Admin unmarshal(AdminImpl adminImpl) throws Exception {
		return adminImpl;
	}

}