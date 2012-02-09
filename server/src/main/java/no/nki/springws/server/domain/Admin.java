package no.nki.springws.server.domain;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import no.nki.springws.server.domain.adapter.AdminAdapter;

@XmlJavaTypeAdapter(AdminAdapter.class)
public interface Admin {

	public String getFirstname();

	public String getLastname();

	public String getLogin();

	public String getPassword();

	public String getEmail();

}
