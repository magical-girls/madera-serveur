package com.magicalg.madera.entity;

public class Fournisseur {

	private Integer id;
	private String societe;
	private String tel;
	private String adresse;
	private String pays;
	private String categorie;
	private String contactTel;
	private String contactNom;
	private String contactPrenom;
	private String contactMail;
	private String horaires;

	public Fournisseur() {
	}

	public Fournisseur(Integer id, String societe, String tel, String adresse, String pays, String categorie,
			String contactTel, String contactNom, String contactPrenom, String contactMail, String horaires) {
		super();
		this.id = id;
		this.societe = societe;
		this.tel = tel;
		this.adresse = adresse;
		this.pays = pays;
		this.categorie = categorie;
		this.contactTel = contactTel;
		this.contactNom = contactNom;
		this.contactPrenom = contactPrenom;
		this.contactMail = contactMail;
		this.horaires = horaires;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSociete() {
		return societe;
	}

	public void setSociete(String societe) {
		this.societe = societe;
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

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public String getContactTel() {
		return contactTel;
	}

	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}

	public String getContactNom() {
		return contactNom;
	}

	public void setContactNom(String contactNom) {
		this.contactNom = contactNom;
	}

	public String getContactPrenom() {
		return contactPrenom;
	}

	public void setContactPrenom(String contactPrenom) {
		this.contactPrenom = contactPrenom;
	}

	public String getContactMail() {
		return contactMail;
	}

	public void setContactMail(String contactMail) {
		this.contactMail = contactMail;
	}

	public String getHoraires() {
		return horaires;
	}

	public void setHoraires(String horaires) {
		this.horaires = horaires;
	}

	@Override
	public String toString() {
		return "Fournisseur [id=" + id + ", societe=" + societe + ", tel=" + tel + ", adresse=" + adresse + ", pays="
				+ pays + ", categorie=" + categorie + ", contactTel=" + contactTel + ", contactNom=" + contactNom
				+ ", contactPrenom=" + contactPrenom + ", contactMail=" + contactMail + ", horaires=" + horaires + "]";
	}

}
