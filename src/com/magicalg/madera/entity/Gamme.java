package com.magicalg.madera.entity;

public class Gamme {

	private String idReferenceGamme;
	private String nom;
	private String commentaire;
	private Integer suppression;

	public String getIdReferenceGamme() {
		return idReferenceGamme;
	}

	public void setIdReferenceGamme(String idReferenceGamme) {
		this.idReferenceGamme = idReferenceGamme;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public Integer getSuppression() {
		return suppression;
	}

	public void setSuppression(Integer suppression) {
		this.suppression = suppression;
	}

	public Gamme(String idReferenceGamme, String nom, String commentaire, Integer suppression) {
		super();
		this.idReferenceGamme = idReferenceGamme;
		this.nom = nom;
		this.commentaire = commentaire;
		this.suppression = suppression;
	}

	@Override
	public String toString() {
		return "Gamme [idReferenceGamme=" + idReferenceGamme + ", nom=" + nom + ", commentaire=" + commentaire
				+ ", suppression=" + suppression + "]";
	}

}
