package com.magicalg.madera.entity;

import java.sql.Timestamp;

public class Devis {

	private String reference;
	private String motif;
	private Integer status;
	private Timestamp dateCreation;
	private Timestamp dateFin;
	private Integer tempsContruction;
	private Float prixTTC;
	private Float prixHT;
	private Salarie salarie;
	private Integer idClient;
	private String refGamme;
	private String refMmodulaire;

	public Devis() {}
	
	public Devis(String reference, String motif, Integer status, Timestamp dateCreation, Timestamp dateFin,
			Integer tempsContruction, Float prixTTC, Float prixHT, Salarie salarie, Integer idClient, String refGamme,
			String refMmodulaire) {
		super();
		this.reference = reference;
		this.motif = motif;
		this.status = status;
		this.dateCreation = dateCreation;
		this.dateFin = dateFin;
		this.tempsContruction = tempsContruction;
		this.prixTTC = prixTTC;
		this.prixHT = prixHT;
		this.salarie = salarie;
		this.idClient = idClient;
		this.refGamme = refGamme;
		this.refMmodulaire = refMmodulaire;
	}

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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Timestamp getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Timestamp dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Timestamp getDateFin() {
		return dateFin;
	}

	public void setDateFin(Timestamp dateFin) {
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

	public Salarie getSalarie() {
		return salarie;
	}

	public void setSalarie(Salarie salarie) {
		this.salarie = salarie;
	}

	public Integer getIdClient() {
		return idClient;
	}

	public void setIdClient(Integer idClient) {
		this.idClient = idClient;
	}

	public String getRefGamme() {
		return refGamme;
	}

	public void setRefGamme(String refGamme) {
		this.refGamme = refGamme;
	}

	public String getRefMmodulaire() {
		return refMmodulaire;
	}

	public void setRefMmodulaire(String refMmodulaire) {
		this.refMmodulaire = refMmodulaire;
	}

	@Override
	public String toString() {
		return "Devis [reference=" + reference + ", motif=" + motif + ", status=" + status + ", dateCreation="
				+ dateCreation + ", dateFin=" + dateFin + ", tempsContruction=" + tempsContruction + ", prixTTC="
				+ prixTTC + ", prixHT=" + prixHT + ", salarie=" + salarie + ", idClient=" + idClient + ", refGamme="
				+ refGamme + ", refMmodulaire=" + refMmodulaire + "]";
	}

}
