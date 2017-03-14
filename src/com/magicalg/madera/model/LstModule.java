package com.magicalg.madera.model;

import java.util.List;

import com.magicalg.madera.entity.Module;

public class LstModule extends Module{

	private List<String> idGamme;
	
	public LstModule() {
		super();
	}

	public List<String> getIdGamme() {
		return idGamme;
	}

	public void setIdGamme(List<String> list) {
		this.idGamme = list;
	}

}
