package com.magicalg.madera.entity;

public class ClientComposant {

	private Integer id;
	private Integer idClient;
	private String referenceComposant;

	public ClientComposant() {
	}

	public ClientComposant(Integer id, Integer idClient, String referenceComposant) {
		super();
		this.id = id;
		this.idClient = idClient;
		this.referenceComposant = referenceComposant;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdClient() {
		return idClient;
	}

	public void setIdClient(Integer idClient) {
		this.idClient = idClient;
	}

	public String getReferenceComposant() {
		return referenceComposant;
	}

	public void setReferenceComposant(String referenceComposant) {
		this.referenceComposant = referenceComposant;
	}

	@Override
	public String toString() {
		return "ClientComposant [id=" + id + ", idClient=" + idClient + ", referenceComposant=" + referenceComposant
				+ "]";
	}

}
