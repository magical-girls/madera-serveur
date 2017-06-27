package com.magicalg.madera.model;

import java.util.List;

public class AddDevis {

	private String nomClient;
	private String prenomClient;
	private String naissanceClient;
	private String telClient;
	private String adresseClient;
	private String professionClient;
	private String mailClient;
	private String idMatriculeSalarie;
	private String referenceDevis;
	private String motifDevis;
	private Float margeComDevis;
	private Float margeEntDevis;
	private String idReferenceGamme;
	private List<Modules> modules;

	public String getNomClient() {
		return nomClient;
	}

	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}

	public String getPrenomClient() {
		return prenomClient;
	}

	public void setPrenomClient(String prenomClient) {
		this.prenomClient = prenomClient;
	}

	public String getNaissanceClient() {
		return naissanceClient;
	}

	public void setNaissanceClient(String naissanceClient) {
		this.naissanceClient = naissanceClient;
	}

	public String getTelClient() {
		return telClient;
	}

	public void setTelClient(String telClient) {
		this.telClient = telClient;
	}

	public String getAdresseClient() {
		return adresseClient;
	}

	public void setAdresseClient(String adresseClient) {
		this.adresseClient = adresseClient;
	}

	public String getProfessionClient() {
		return professionClient;
	}

	public void setProfessionClient(String professionClient) {
		this.professionClient = professionClient;
	}

	public String getMailClient() {
		return mailClient;
	}

	public void setMailClient(String mailClient) {
		this.mailClient = mailClient;
	}

	public String getIdMatriculeSalarie() {
		return idMatriculeSalarie;
	}

	public void setIdMatriculeSalarie(String idMatriculeSalarie) {
		this.idMatriculeSalarie = idMatriculeSalarie;
	}

	public String getReferenceDevis() {
		return referenceDevis;
	}

	public void setReferenceDevis(String referenceDevis) {
		this.referenceDevis = referenceDevis;
	}

	public String getMotifDevis() {
		return motifDevis;
	}

	public void setMotifDevis(String motifDevis) {
		this.motifDevis = motifDevis;
	}

	public Float getMargeComDevis() {
		return margeComDevis;
	}

	public void setMargeComDevis(Float margeComDevis) {
		this.margeComDevis = margeComDevis;
	}

	public Float getMargeEntDevis() {
		return margeEntDevis;
	}

	public void setMargeEntDevis(Float margeEntDevis) {
		this.margeEntDevis = margeEntDevis;
	}

	public String getIdReferenceGamme() {
		return idReferenceGamme;
	}

	public void setIdReferenceGamme(String idReferenceGamme) {
		this.idReferenceGamme = idReferenceGamme;
	}

	public List<Modules> getModules() {
		return modules;
	}

	public void setModules(List<Modules> modules) {
		this.modules = modules;
	}

	@Override
	public String toString() {
		return "AddDevis [nomClient=" + nomClient + ", prenomClient=" + prenomClient + ", naissanceClient="
				+ naissanceClient + ", telClient=" + telClient + ", adresseClient=" + adresseClient
				+ ", professionClient=" + professionClient + ", mailClient=" + mailClient + ", idMatriculeSalarie="
				+ idMatriculeSalarie + ", referenceDevis=" + referenceDevis + ", motifDevis=" + motifDevis
				+ ", margeComDevis=" + margeComDevis + ", margeEntDevis=" + margeEntDevis + ", idReferenceGamme="
				+ idReferenceGamme + ", modules=" + modules + "]";
	}


	

}
