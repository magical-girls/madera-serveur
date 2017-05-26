package com.magicalg.madera.model;

import java.util.List;

import com.magicalg.madera.entity.Angle;
import com.magicalg.madera.entity.Client;
import com.magicalg.madera.entity.Devis;
import com.magicalg.madera.entity.Gamme;
import com.magicalg.madera.entity.Module;
import com.magicalg.madera.entity.Section;

public class PutDevis {

	private Client client;
	private Devis devis;
	private Gamme gamme;
	private List<Angle> lstAngle;
	private List<Section> lstSection;
	private List<Module> lstModule;

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

	public List<Angle> getLstAngle() {
		return lstAngle;
	}

	public void setLstAngle(List<Angle> lstAngle) {
		this.lstAngle = lstAngle;
	}

	public List<Section> getLstSection() {
		return lstSection;
	}

	public void setLstSection(List<Section> lstSection) {
		this.lstSection = lstSection;
	}

	public List<Module> getLstModule() {
		return lstModule;
	}

	public void setLstModule(List<Module> lstModule) {
		this.lstModule = lstModule;
	}

	@Override
	public String toString() {
		return "PutDevis [client=" + client + ", devis=" + devis + ", gamme=" + gamme + ", lstAngle=" + lstAngle
				+ ", lstSection=" + lstSection + ", lstModule=" + lstModule + "]";
	}

}
