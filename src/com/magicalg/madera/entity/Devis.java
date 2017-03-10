package com.magicalg.madera.entity;

public class Devis {

	private String reference;
	private String motif;
	private String status;
	private String dateCreation;
	private String dateFin;
	private Integer tempsContruction;
	private Float prixTTC;
	private Float prixHT;
	private Float margeCom;
	private Float margeEnt;

	public static final String _REFUSE = "Refusé";
	public static final String _ENCOURS = "En cours";
	public static final String _ENATTENTE = "En attente";
	public static final String _VALIDE = "Validé";

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getMotif() {
		return motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		if (null != status) {
			if (-1 == status) {
				this.status = _REFUSE;
			} else if (1 == status) {
				this.status = _ENATTENTE;
			} else if (2 == status) {
				this.status = _VALIDE;
			} else {
				this.status = _ENCOURS;
			}
		} else {
			this.status = _ENCOURS;
		}
	}

	public String getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getDateFin() {
		return dateFin;
	}

	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}

	public Integer getTempsContruction() {
		return tempsContruction;
	}

	public void setTempsContruction(Integer tempsContruction) {
		this.tempsContruction = tempsContruction;
	}

	public Float getPrixTTC() {
		return prixTTC;
	}

	public void setPrixTTC(Float prixTTC) {
		this.prixTTC = prixTTC;
	}

	public Float getPrixHT() {
		return prixHT;
	}

	public void setPrixHT(Float prixHT) {
		this.prixHT = prixHT;
	}

	public Float getMargeCom() {
		return margeCom;
	}

	public void setMargeCom(Float margeCom) {
		this.margeCom = margeCom;
	}

	public Float getMargeEnt() {
		return margeEnt;
	}

	public void setMargeEnt(Float margeEnt) {
		this.margeEnt = margeEnt;
	}

	

	@Override
	public String toString() {
		return "Devis [reference=" + reference + ", motif=" + motif + ", status=" + status + ", dateCreation="
				+ dateCreation + ", dateFin=" + dateFin + ", tempsContruction=" + tempsContruction + ", prixTTC="
				+ prixTTC + ", prixHT=" + prixHT + ", margeCom=" + margeCom + ", margeEnt=" + margeEnt + "]";
	}



}
