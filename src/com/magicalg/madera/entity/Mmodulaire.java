package com.magicalg.madera.entity;

public class Mmodulaire {

	private String idReferenceMmodulaire;
	private String nom;
	private String commentaire;
	private String referenceGamme;

	public Mmodulaire(String referenceMmodulaire, String nom, String commentaire, String referenceGamme) {
		super();
		this.idReferenceMmodulaire = referenceMmodulaire;
		this.nom = nom;
		this.commentaire = commentaire;
		this.referenceGamme = referenceGamme;
	}

	public String getReferenceMmodulaire() {
		return idReferenceMmodulaire;
	}

	public void setReferenceMmodulaire(String referenceMmodulaire) {
		this.idReferenceMmodulaire = referenceMmodulaire;
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

	public String getReferenceGamme() {
		return referenceGamme;
	}

	public void setReferenceGamme(String referenceGamme) {
		this.referenceGamme = referenceGamme;
	}

	@Override
	public String toString() {
		return "Mmodulaire [referenceMmodulaire=" + idReferenceMmodulaire + ", nom=" + nom + ", commentaire="
				+ commentaire + ", referenceGamme=" + referenceGamme + "]";
	}

}
