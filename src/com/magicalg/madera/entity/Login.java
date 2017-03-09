package com.magicalg.madera.entity;

public class Login {

	private Integer id;
	private String login;
	private String mdp;
	private String matriculeSalarie;

	public Login() {
	}

	public Login(Integer id, String login, String mdp, String matriculeSalarie) {
		super();
		this.id = id;
		this.login = login;
		this.mdp = mdp;
		this.matriculeSalarie = matriculeSalarie;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public String getMatriculeSalarie() {
		return matriculeSalarie;
	}

	public void setMatriculeSalarie(String matriculeSalarie) {
		this.matriculeSalarie = matriculeSalarie;
	}

	@Override
	public String toString() {
		return "Login [id=" + id + ", login=" + login + ", mdp=" + mdp + ", matriculeSalarie=" + matriculeSalarie + "]";
	}

}
