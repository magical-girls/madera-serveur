package com.magicalg.madera.entity;

public class ComposantModule {

	private Integer id;
	private String referenceComposant;
	private String referenceModule;

	public ComposantModule() {
	}

	public ComposantModule(Integer id, String referenceComposant, String referenceModule) {
		super();
		this.id = id;
		this.referenceComposant = referenceComposant;
		this.referenceModule = referenceModule;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReferenceComposant() {
		return referenceComposant;
	}

	public void setReferenceComposant(String referenceComposant) {
		this.referenceComposant = referenceComposant;
	}

	public String getReferenceModule() {
		return referenceModule;
	}

	public void setReferenceModule(String referenceModule) {
		this.referenceModule = referenceModule;
	}

	@Override
	public String toString() {
		return "ComposantModule [id=" + id + ", referenceComposant=" + referenceComposant + ", referenceModule="
				+ referenceModule + "]";
	}

}
