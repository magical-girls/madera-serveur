package com.magicalg.madera.entity;

import java.sql.Date;

public class PosteSalarie {

	private Integer id;
	private String matriculeSalarie;
	private Integer idPoste;
	private Date debut;
	private Date fin;

	public PosteSalarie() {
	}

	public PosteSalarie(Integer id, String matriculeSalarie, Integer idPoste, Date debut, Date fin) {
		super();
		this.id = id;
		this.matriculeSalarie = matriculeSalarie;
		this.idPoste = idPoste;
		this.debut = debut;
		this.fin = fin;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMatriculeSalarie() {
		return matriculeSalarie;
	}

	public void setMatriculeSalarie(String matriculeSalarie) {
		this.matriculeSalarie = matriculeSalarie;
	}

	public Integer getIdPoste() {
		return idPoste;
	}

	public void setIdPoste(Integer idPoste) {
		this.idPoste = idPoste;
	}

	public Date getDebut() {
		return debut;
	}

	public void setDebut(Date debut) {
		this.debut = debut;
	}

	public Date getFin() {
		return fin;
	}

	public void setFin(Date fin) {
		this.fin = fin;
	}

	@Override
	public String toString() {
		return "PosteSalarie [id=" + id + ", matriculeSalarie=" + matriculeSalarie + ", idPoste=" + idPoste + ", debut="
				+ debut + ", fin=" + fin + "]";
	}

}
