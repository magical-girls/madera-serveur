package com.magicalg.madera.entity;

public class Composant {

	private String idReference;
	private String nom;
	private Float prixHT;
	private String commentaire;
	private Float stock;
	private Integer idFournisseur;

	public Composant() {
	}

	public Composant(String idReference, String nom, Float prixHT, String commentaire, Float stock,
			Integer idFournisseur) {
		super();
		this.idReference = idReference;
		this.nom = nom;
		this.prixHT = prixHT;
		this.commentaire = commentaire;
		this.stock = stock;
		this.idFournisseur = idFournisseur;
	}

	public String getIdReference() {
		return idReference;
	}

	public void setIdReference(String idReference) {
		this.idReference = idReference;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Float getPrixHT() {
		return prixHT;
	}

	public void setPrixHT(Float prixHT) {
		this.prixHT = prixHT;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public Float getStock() {
		return stock;
	}

	public void setStock(Float stock) {
		this.stock = stock;
	}

	public Integer getIdFournisseur() {
		return idFournisseur;
	}

	public void setIdFournisseur(Integer idFournisseur) {
		this.idFournisseur = idFournisseur;
	}

	@Override
	public String toString() {
		return "Composant [idReference=" + idReference + ", nom=" + nom + ", prixHT=" + prixHT + ", commentaire="
				+ commentaire + ", stock=" + stock + ", idFournisseur=" + idFournisseur + "]";
	}

}
