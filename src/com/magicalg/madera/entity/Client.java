package com.magicalg.madera.entity;

public class Client {

	private Integer id;
	private String nom;
	private String prenom;
	private String naissance;
	private String tel;
	private String adresse;
	private String profession;
	private String mail;
	private String creation;

	public Client(Integer id, String nom, String prenom, String naissance, String tel, String adresse,
			String profession, String mail, String creation) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.naissance = naissance;
		this.tel = tel;
		this.adresse = adresse;
		this.profession = profession;
		this.mail = mail;
		this.creation = creation;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getNaissance() {
		return naissance;
	}

	public void setNaissance(String naissance) {
		this.naissance = naissance;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getCreation() {
		return creation;
	}

	public void setCreation(String creation) {
		this.creation = creation;
	}
	

	@Override
	public String toString() {
		return "Client [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", naissance=" + naissance + ", tel=" + tel
				+ ", adresse=" + adresse + ", profession=" + profession + ", mail=" + mail + ", creation=" + creation
				+ "]";
	}

}
