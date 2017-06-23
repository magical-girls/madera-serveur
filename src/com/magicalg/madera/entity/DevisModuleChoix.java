package com.magicalg.madera.entity;

public class DevisModuleChoix {

	private Integer id;
	private String idDevis;
	private String moduleA;
	private Integer idSectionA;
	private Integer longueurA;
	private String moduleB;
	private Integer idSectionB;
	private Integer longueurB;
	private Integer IdAngle;
	private Float angle;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getIdDevis() {
		return idDevis;
	}
	public void setIdDevis(String idDevis) {
		this.idDevis = idDevis;
	}
	public String getModuleA() {
		return moduleA;
	}
	public void setModuleA(String moduleA) {
		this.moduleA = moduleA;
	}
	public Integer getIdSectionA() {
		return idSectionA;
	}
	public void setIdSectionA(Integer idSectionA) {
		this.idSectionA = idSectionA;
	}
	public Integer getLongueurA() {
		return longueurA;
	}
	public void setLongueurA(Integer longueurA) {
		this.longueurA = longueurA;
	}
	public String getModuleB() {
		return moduleB;
	}
	public void setModuleB(String moduleB) {
		this.moduleB = moduleB;
	}
	public Integer getIdSectionB() {
		return idSectionB;
	}
	public void setIdSectionB(Integer idSectionB) {
		this.idSectionB = idSectionB;
	}
	public Integer getLongueurB() {
		return longueurB;
	}
	public void setLongueurB(Integer longueurB) {
		this.longueurB = longueurB;
	}
	public Integer getIdAngle() {
		return IdAngle;
	}
	public void setIdAngle(Integer idAngle) {
		IdAngle = idAngle;
	}
	public Float getAngle() {
		return angle;
	}
	public void setAngle(Float angle) {
		this.angle = angle;
	}
	@Override
	public String toString() {
		return "DevisModuleChoix [id=" + id + ", idDevis=" + idDevis + ", moduleA=" + moduleA + ", idSectionA="
				+ idSectionA + ", longueurA=" + longueurA + ", moduleB=" + moduleB + ", idSectionB=" + idSectionB
				+ ", longueurB=" + longueurB + ", IdAngle=" + IdAngle + ", angle=" + angle + "]";
	}
	
	
}
