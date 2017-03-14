package com.magicalg.madera.entity;

public class Module {

	private String idReference;
	private String commentaire;

	public Module() {
	}

	public Module(String idReference, String commentaire) {
		super();
		this.idReference = idReference;
		this.commentaire = commentaire;
	}

	public String getIdReference() {
		return idReference;
	}

	public void setIdReference(String idReference) {
		this.idReference = idReference;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	

	@Override
	public String toString() {
		return "Module [idReference=" + idReference + ", commentaire=" + commentaire +"]";
	}

}
