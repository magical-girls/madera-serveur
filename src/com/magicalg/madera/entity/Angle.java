package com.magicalg.madera.entity;

public class Angle {

	private Integer id;
	private String type;
	private Float degre;
	private Integer sectionA;
	private Integer sectionB;

	public Angle() {
	}

	public Angle(Integer id, String type, Float degre, Integer sectionA, Integer sectionB) {
		super();
		this.id = id;
		this.type = type;
		this.degre = degre;
		this.sectionA = sectionA;
		this.sectionB = sectionB;
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

	public Integer getSectionA() {
		return sectionA;
	}

	public void setSectionA(Integer sectionA) {
		this.sectionA = sectionA;
	}

	public Integer getSectionB() {
		return sectionB;
	}

	public void setSectionB(Integer sectionB) {
		this.sectionB = sectionB;
	}

	@Override
	public String toString() {
		return "Angle [id=" + id + ", type=" + type + ", degre=" + degre + ", sectionA=" + sectionA + ", sectionB="
				+ sectionB + "]";
	}

}
