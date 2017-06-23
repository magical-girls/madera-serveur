package com.magicalg.madera.model;

import java.util.List;

import com.magicalg.madera.entity.Client;
import com.magicalg.madera.entity.Composant;
import com.magicalg.madera.entity.Devis;
import com.magicalg.madera.entity.Gamme;
import com.magicalg.madera.entity.Salarie;

public class DevisId {

	private Client client;
	private Salarie salarie;
	private Devis devis;
	private Gamme gamme;
	private List<Modules> lstModule;
	private List<Composant> lstComposant;

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Salarie getSalarie() {
		return salarie;
	}

	public void setSalarie(Salarie salarie) {
		this.salarie = salarie;
	}

	public Devis getDevis() {
		return devis;
	}

	public void setDevis(Devis devis) {
		this.devis = devis;
	}

	public Gamme getGamme() {
		return gamme;
	}

	public void setGamme(Gamme gamme) {
		this.gamme = gamme;
	}


	public List<Modules> getLstModule() {
		return lstModule;
	}

	public void setLstModule(List<Modules> lstModule) {
		this.lstModule = lstModule;
	}

	public List<Composant> getLstComposant() {
		return lstComposant;
	}

	public void setLstComposant(List<Composant> lstComposant) {
		this.lstComposant = lstComposant;
	}

	@Override
	public String toString() {
		return "DevisId [client=" + client + ", salarie=" + salarie + ", devis=" + devis + ", gamme=" + gamme
				+ ", lstModule=" + lstModule + ", lstComposant=" + lstComposant + "]";
	}

}
