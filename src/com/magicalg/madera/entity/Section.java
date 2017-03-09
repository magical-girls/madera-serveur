package com.magicalg.madera.entity;

public class Section {

	private Integer id;
	private Float longueur;
	private Integer idDevisMod;

	public Section() {
	}

	public Section(Integer id, Float longueur, Integer idDevisMod) {
		super();
		this.id = id;
		this.longueur = longueur;
		this.idDevisMod = idDevisMod;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Float getLongueur() {
		return longueur;
	}

	public void setLongueur(Float longueur) {
		this.longueur = longueur;
	}

	public Integer getIdDevisMod() {
		return idDevisMod;
	}

	public void setIdDevisMod(Integer idDevisMod) {
		this.idDevisMod = idDevisMod;
	}

	@Override
	public String toString() {
		return "Section [id=" + id + ", longueur=" + longueur + ", idDevisMod=" + idDevisMod + "]";
	}

}
