package com.magicalg.madera.bdd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.magicalg.madera.bdd.ConnectionBdd;
import com.magicalg.madera.entity.Gamme;

public class GammeDao {

	private GammeDao() {}
	
	public static List<Gamme> getAllGamme() throws Exception{
		List<Gamme> lstGamme = new ArrayList<>();
		Gamme gamme = null;
		String sql = "SELECT reference_gamme as idReference, nom_gamme as nom, "
				+ "commentaire_gamme as commentaire FROM gamme WHERE suppression_gamme = 0";
		Connection con = ConnectionBdd.connect();
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet res = stmt.executeQuery();
		while(res.next()){
			gamme = new Gamme(res.getString("idReference"), res.getString("nom"), res.getString("commentaire"));
			lstGamme.add(gamme);
		}
		stmt.close();
		con.close();
		return lstGamme;		
	}

	public static Gamme getGammeById(String id) throws Exception {
		Gamme gamme = null;
		String sql = "SELECT reference_gamme as idReference, nom_gamme as nom, "
				+ "commentaire_gamme as commentaire FROM gamme WHERE suppression_gamme = 0"
				+ " AND reference_gamme = ?";
		Connection con = ConnectionBdd.connect();
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, id);
		ResultSet res = stmt.executeQuery();
		while(res.next()){
			gamme = new Gamme(res.getString("idReference"), res.getString("nom"), res.getString("commentaire"));
		}
		stmt.close();
		con.close();
		return gamme;
	}

	public static void updateGamme(Gamme gamme) throws Exception {
		String sql = "UPDATE gamme SET nom_gamme = ?, commentaire_gamme = ? "
				+ "WHERE reference_gamme = ?";
		Connection con = ConnectionBdd.connect();
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, gamme.getNom());
		stmt.setString(2, gamme.getCommentaire());
		stmt.setString(3, gamme.getIdReference());
		stmt.executeUpdate();
		stmt.close();
		con.close();
	}

	public static void deleteGamme(String reference) throws Exception {
		String sql = "UPDATE gamme SET suppression_gamme = 1 WHERE reference_gamme = ?";
		Connection con = ConnectionBdd.connect();
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, reference);
		stmt.executeUpdate();
		stmt.close();
		con.close();
	}
}
