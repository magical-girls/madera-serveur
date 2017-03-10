package com.magicalg.madera.model;

import com.magicalg.madera.entity.Devis;

public class ListDevis {

	private String reference;
	private String client;
	private String creation;
	private String modif;
	private String status;

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getCreation() {
		return creation;
	}

	public void setCreation(String creation) {
		this.creation = creation;
	}

	public String getModif() {
		return modif;
	}

	public void setModif(String modif) {
		this.modif = modif;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		if(null != status){
			if(-1 == status){
				this.status = Devis._REFUSE;
			} else if (1 == status){
				this.status = Devis._ENATTENTE;
			} else if (2 == status){
				this.status = Devis._VALIDE;
			} else {
				this.status = Devis._ENCOURS;
			}
		} else {
			this.status = Devis._ENCOURS;
		}
	}

	@Override
	public String toString() {
		return "ListDevis [reference=" + reference + ", client=" + client + ", creation=" + creation + ", modif="
				+ modif + ", status=" + status + "]";
	}

}
