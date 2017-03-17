package com.magicalg.madera.model;

import com.magicalg.madera.entity.Login;
import com.magicalg.madera.entity.Salarie;

public class LoginWithSalarie {

	private Login login;
	private Salarie salarie;
	
	public Login getLogin() {
		return login;
	}
	public void setLogin(Login login) {
		this.login = login;
	}
	public Salarie getSalarie() {
		return salarie;
	}
	public void setSalarie(Salarie salarie) {
		this.salarie = salarie;
	}
	
	@Override
	public String toString() {
		return "LoginWithSalarie [login=" + login + ", salarie=" + salarie + "]";
	}
	
	                   
}
