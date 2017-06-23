package com.magicalg.madera.entity;

public class Angle {

	private Integer id;
	private String type;

	public Angle() {
	}

	public Angle(Integer id, String type) {
		super();
		this.id = id;
		this.type = type;
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

	@Override
	public String toString() {
		return "Angle [id=" + id + ", type=" + type +"]";
	}

}
