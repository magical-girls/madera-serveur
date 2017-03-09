package com.magicalg.madera.entity;

public class Salarie {

	private String idMatricule;
	private String nom;
	private String prenom;
	private String mail;
	private String tel;

	public String getIdMatricule() {
		return idMatricule;
	}

	public void setIdMatricule(String idMatricule) {
		this.idMatricule = idMatricule;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Salarie(String idMatricule, String nom, String prenom, String mail, String tel) {
		super();
		this.idMatricule = idMatricule;
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.tel = tel;
	}

	@Override
	public String toString() {
		return "Salarie [idMatricule=" + idMatricule + ", nom=" + nom + ", prenom=" + prenom + ", mail=" + mail
				+ ", tel=" + tel + "]";
	}

}
