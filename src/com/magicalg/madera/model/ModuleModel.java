package com.magicalg.madera.model;

public class ModuleModel {

	private String id;
	private String section;
	private Integer longueur;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public Integer getLongueur() {
		return longueur;
	}
	public void setLongueur(Integer longueur) {
		this.longueur = longueur;
	}
	@Override
	public String toString() {
		return "ModuleModel [id=" + id + ", section=" + section + ", longueur=" + longueur + "]";
	}
	
	

}
