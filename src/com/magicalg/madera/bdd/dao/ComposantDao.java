package com.magicalg.madera.bdd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.magicalg.madera.bdd.ConnectionBdd;
import com.magicalg.madera.model.ComposantWithModule;

public class ComposantDao {
	
	private ComposantDao() {
	}

	public static List<ComposantWithModule> getAllComposant() throws Exception {
		List<ComposantWithModule> composantWithModule = new ArrayList<>();
		ComposantWithModule composant = null;
		String sql = "SELECT reference_composant as idReference, nom_composant as nom, "
				+ "commentaire_composant as commentaire FROM composant WHERE suppression_composant = 0";
		Connection con = ConnectionBdd.connect();
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet res = stmt.executeQuery();
		while(res.next()){
			composant = new ComposantWithModule();
			composant.setIdReference(res.getString("idReference"));
			composant.setNom(res.getString("nom"));
			composant.setCommentaire(res.getString("commentaire"));
			composant.setIdModule(getListIdModule(res.getString("idReference"), con));
			composantWithModule.add(composant);
		}
		stmt.close();
		con.close();
		return composantWithModule;
	}
	
	public static ComposantWithModule getComposantByIdRef(String reference) throws Exception {
		ComposantWithModule composant = null;
		String sql = "SELECT reference_composant as idReference, nom_composant as nom, "
				+ "commentaire_composant as commentaire, prixHT_composant as prixHT, "
				+ "stock_composant as stock, id_fournisseur as idFournisseur FROM composant WHERE reference_composant = ?";
		Connection con = ConnectionBdd.connect();
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, reference);
		ResultSet res = stmt.executeQuery();
		while(res.next()){
			composant = new ComposantWithModule();
			composant.setIdReference(res.getString("idReference"));
			composant.setNom(res.getString("nom"));
			composant.setCommentaire(res.getString("commentaire"));
			composant.setPrixHT(res.getFloat("prixHT"));
			composant.setStock(res.getFloat("stock"));
			composant.setIdModule(getListIdModule(res.getString("idReference"), con));
		}
		stmt.close();
		con.close();
		return composant;
	}

	private synchronized static List<String> getListIdModule(String string, Connection con) throws Exception {
		List<String> lstModule = new ArrayList<>();
		String sql = "SELECT reference_module as idModule FROM composant_module WHERE reference_composant = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, string);
		ResultSet res = stmt.executeQuery();
		while(res.next()){
			lstModule.add(res.getString("idModule"));
		}
		stmt.close();
		return lstModule;
	}
}
