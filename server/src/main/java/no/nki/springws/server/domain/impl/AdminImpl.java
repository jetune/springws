package no.nki.springws.server.domain.impl;

import javax.xml.bind.annotation.XmlType;

import no.nki.springws.server.domain.Admin;

@XmlType(name = "no.nki.springws.model.domain.Admin")
public class AdminImpl implements Admin {

	private String email;
	private String firstname;
	private String lastname;
	private String login;
	private String password;

	public AdminImpl() {
	}

	public AdminImpl(String email, String firstname, String lastname, String login, String password) {
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.login = login;
		this.password = password;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
