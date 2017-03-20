package com.magicalg.madera.model;

import com.magicalg.madera.entity.Section;

public class SectionWithRefModule extends Section {
	
	private String refModule;
	
	public SectionWithRefModule() {
		super();
	}

	public String getRefModule() {
		return refModule;
	}

	public void setRefModule(String refModule) {
		this.refModule = refModule;
	}

	@Override
	public String toString() {
		return "SectionWithRefModule [refModule=" + refModule + ", toString()=" + super.toString() + "]";
	}

	

}
