package com.magicalg.madera.model;

import java.util.List;

import com.magicalg.madera.entity.Composant;

public class ComposantWithModule extends Composant{

	private List<String> idModule;
	
	public ComposantWithModule() {
		super();
	}

	public List<String> getIdModule() {
		return idModule;
	}

	public void setIdModule(List<String> idModule) {
		this.idModule = idModule;
	}

	@Override
	public String toString() {
		return "ComposantWithModule [idModule=" + idModule + ", getIdReference()=" + getIdReference() + ", getNom()="
				+ getNom() + ", getPrixHT()=" + getPrixHT() + ", getCommentaire()=" + getCommentaire() + ", getStock()="
				+ getStock() + ", toString()=" + super.toString() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + "]";
	}
	
}
