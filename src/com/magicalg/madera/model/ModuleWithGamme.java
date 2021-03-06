package com.magicalg.madera.model;

import java.util.List;

import com.magicalg.madera.entity.Module;

public class ModuleWithGamme extends Module{

	private List<String> idGamme;
	
	public ModuleWithGamme() {
		super();
	}

	public List<String> getIdGamme() {
		return idGamme;
	}

	public void setIdGamme(List<String> list) {
		this.idGamme = list;
	}

	@Override
	public String toString() {
		return "ModuleWithGamme [idGamme=" + idGamme + ", toString()=" + super.toString() + "]";
	}
	
	

}
