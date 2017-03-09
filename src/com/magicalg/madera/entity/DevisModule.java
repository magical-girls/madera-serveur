package com.magicalg.madera.entity;

public class DevisModule {

	private Integer id;
	private String idReferenceDevis;
	private String referenceModule;

	public DevisModule() {
	}

	public DevisModule(Integer id, String idReferenceDevis, String referenceModule) {
		super();
		this.id = id;
		this.idReferenceDevis = idReferenceDevis;
		this.referenceModule = referenceModule;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIdReferenceDevis() {
		return idReferenceDevis;
	}

	public void setIdReferenceDevis(String idReferenceDevis) {
		this.idReferenceDevis = idReferenceDevis;
	}

	public String getReferenceModule() {
		return referenceModule;
	}

	public void setReferenceModule(String referenceModule) {
		this.referenceModule = referenceModule;
	}

	@Override
	public String toString() {
		return "DevisModule [id=" + id + ", idReferenceDevis=" + idReferenceDevis + ", referenceModule="
				+ referenceModule + "]";
	}

}
