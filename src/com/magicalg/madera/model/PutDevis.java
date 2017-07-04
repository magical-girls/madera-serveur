package com.magicalg.madera.model;

import java.util.List;

import com.magicalg.madera.entity.Client;
import com.magicalg.madera.entity.Devis;
import com.magicalg.madera.entity.Gamme;

public class PutDevis {

	private Client client;
	private Devis devis;
	private Gamme gamme;
	private List<Modules> modules;

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
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

	public List<Modules> getModules() {
		return modules;
	}

	public void setModules(List<Modules> modules) {
		this.modules = modules;
	}

	@Override
	public String toString() {
		return "PutDevis [client=" + client + ", devis=" + devis + ", gamme=" + gamme + ", modules=" + modules + "]";
	}


}
