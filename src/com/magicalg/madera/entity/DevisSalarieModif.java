package com.magicalg.madera.entity;

import java.sql.Date;

public class DevisSalarieModif {

	private Integer id;
	private Date datemodif;
	private String commentaire;
	private String referenceDevis;
	private String matriculeSalarie;

	public DevisSalarieModif() {
	}

	public DevisSalarieModif(Integer id, Date datemodif, String commentaire, String referenceDevis,
			String matriculeSalarie) {
		super();
		this.id = id;
		this.datemodif = datemodif;
		this.commentaire = commentaire;
		this.referenceDevis = referenceDevis;
		this.matriculeSalarie = matriculeSalarie;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDatemodif() {
		return datemodif;
	}

	public void setDatemodif(Date datemodif) {
		this.datemodif = datemodif;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public String getReferenceDevis() {
		return referenceDevis;
	}

	public void setReferenceDevis(String referenceDevis) {
		this.referenceDevis = referenceDevis;
	}

	public String getMatriculeSalarie() {
		return matriculeSalarie;
	}

	public void setMatriculeSalarie(String matriculeSalarie) {
		this.matriculeSalarie = matriculeSalarie;
	}

	@Override
	public String toString() {
		return "DevisSalarieModif [id=" + id + ", datemodif=" + datemodif + ", commentaire=" + commentaire
				+ ", referenceDevis=" + referenceDevis + ", matriculeSalarie=" + matriculeSalarie + "]";
	}

}
