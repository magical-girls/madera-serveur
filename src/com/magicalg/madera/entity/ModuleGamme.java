package com.magicalg.madera.entity;

public class ModuleGamme {

	private Integer id;
	private String referenceGamme;
	private String referenceModule;

	public ModuleGamme() {
	}

	public ModuleGamme(Integer id, String referenceGamme, String referenceModule) {
		super();
		this.id = id;
		this.referenceGamme = referenceGamme;
		this.referenceModule = referenceModule;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReferenceGamme() {
		return referenceGamme;
	}

	public void setReferenceGamme(String referenceGamme) {
		this.referenceGamme = referenceGamme;
	}

	public String getReferenceModule() {
		return referenceModule;
	}

	public void setReferenceModule(String referenceModule) {
		this.referenceModule = referenceModule;
	}

	@Override
	public String toString() {
		return "ModuleGamme [id=" + id + ", referenceGamme=" + referenceGamme + ", referenceModule=" + referenceModule
				+ "]";
	}

}
