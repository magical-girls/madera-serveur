package com.magicalg.madera.entity;

public class Module {

	private String idReference;
	private String commentaire;
	private Integer suppression;

	public Module() {
	}

	public Module(String idReference, String commentaire, Integer suppression) {
		super();
		this.idReference = idReference;
		this.commentaire = commentaire;
		this.suppression = suppression;
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

	public Integer getSuppression() {
		return suppression;
	}

	public void setSuppression(Integer suppression) {
		this.suppression = suppression;
	}

	@Override
	public String toString() {
		return "Module [idReference=" + idReference + ", commentaire=" + commentaire + ", suppression=" + suppression
				+ "]";
	}

}
