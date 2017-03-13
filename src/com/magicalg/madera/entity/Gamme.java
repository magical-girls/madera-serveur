package com.magicalg.madera.entity;

public class Gamme {

	private String idReference;
	private String nom;
	private String commentaire;
	
	public Gamme() {}

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

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public Gamme(String idReference, String nom, String commentaire) {
		super();
		this.idReference = idReference;
		this.nom = nom;
		this.commentaire = commentaire;
	}

	@Override
	public String toString() {
		return "Gamme [idReference=" + idReference + ", nom=" + nom + ", commentaire=" + commentaire + "]";
	}

}
