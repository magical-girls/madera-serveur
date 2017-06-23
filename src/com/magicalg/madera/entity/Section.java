package com.magicalg.madera.entity;

public class Section {

	private Integer id;
	private String nom;

	public Section() {
	}

	public Section(Integer id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Section [id=" + id + ", nom=" + nom + "]";
	}


}
