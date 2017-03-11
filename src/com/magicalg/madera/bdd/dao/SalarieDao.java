package com.magicalg.madera.bdd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.magicalg.madera.bdd.ConnectionBdd;
import com.magicalg.madera.entity.Salarie;

public class SalarieDao {

	private SalarieDao() {
	}
	
	public static Salarie getSalarie(String matricule) throws Exception {
		Salarie salarie = null;
		Connection con = ConnectionBdd.connect();
		String sql = "SELECT matricule_salarie as id, nom_salarie as nom, prenom_salarie as prenom, "
				+ "mail_salarie as mail, tel_salarie as tel FROM salarie"
				+ " WHERE matricule_salarie = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, matricule);
		ResultSet res = stmt.executeQuery();
		while(res.next()){
			salarie = new Salarie(res.getString("id"), res.getString("nom"), res.getString("prenom"), 
					res.getString("mail"), res.getString("tel"));
		}
		stmt.close();
		con.close();
		return salarie;
	}
}
