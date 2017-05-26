package com.magicalg.madera.entity;

public class Angle {

	private Integer id;
	private String type;
	private Float degre;
	private String moduleA;
	private String moduleB;
	private String referenceDevis;

	public Angle() {
	}

	public Angle(Integer id, String type, Float degre, String moduleA, String moduleB) {
		super();
		this.id = id;
		this.type = type;
		this.degre = degre;
		this.moduleA = moduleA;
		this.moduleB = moduleB;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Float getDegre() {
		return degre;
	}

	public void setDegre(Float degre) {
		this.degre = degre;
	}

	public String getModuleA() {
		return moduleA;
	}

	public void setModuleA(String sectionA) {
		this.moduleA = sectionA;
	}

	public String getModuleB() {
		return moduleB;
	}

	public void setSectionB(String sectionB) {
		this.moduleB = sectionB;
	}

	@Override
	public String toString() {
		return "Angle [id=" + id + ", type=" + type + ", degre=" + degre + ", moduleA=" + moduleA + ", moduleB="
				+ moduleB + "]";
	}

}
