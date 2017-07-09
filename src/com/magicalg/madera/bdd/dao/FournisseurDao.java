package com.magicalg.madera.bdd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.magicalg.madera.bdd.ConnectionBdd;
import com.magicalg.madera.entity.Fournisseur;

public class FournisseurDao {

	public static List<Fournisseur> getAllFournisseur() throws Exception{
		List<Fournisseur> fs = new ArrayList<>();
		Fournisseur f =null;
		
		String sql = "SELECT * FROM fournisseur";
		Connection con = ConnectionBdd.connect();
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()){
			f = new Fournisseur();
			f.setAdresse(rs.getString("adresse_fournisseur"));
			f.setId(rs.getInt("id_fournisseur"));
			f.setSociete(rs.getString("societe_fournisseur"));
			f.setTel(rs.getString("tel_fournisseur"));
			fs.add(f);
		}
		return fs;
	}
}
