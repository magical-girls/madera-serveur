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
	
	public static Fournisseur getFournisseur(Integer fr) throws Exception{
		Fournisseur f =null;
		
		String sql = "SELECT * FROM fournisseur WHERE id_fournisseur = ?";
		Connection con = ConnectionBdd.connect();
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, fr);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()){
			f = new Fournisseur();
			f.setAdresse(rs.getString("adresse_fournisseur"));
			f.setId(rs.getInt("id_fournisseur"));
			f.setSociete(rs.getString("societe_fournisseur"));
			f.setTel(rs.getString("tel_fournisseur"));
			f.setPays(rs.getString("pays_fournisseur"));
			f.setCategorie(rs.getString("categorie_fournisseur"));
			f.setContactTel(rs.getString("contacttel_fournisseur"));
			f.setContactNom(rs.getString("contactnom_fournisseur"));
			f.setContactPrenom(rs.getString("contactprenom_fournisseur"));
			f.setContactMail(rs.getString("contactmail_fournisseur"));
			f.setHoraires(rs.getString("horaires_fournisseur"));
		}
		return f;
	}
}
