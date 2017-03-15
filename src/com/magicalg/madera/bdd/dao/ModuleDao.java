package com.magicalg.madera.bdd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.magicalg.madera.bdd.ConnectionBdd;
import com.magicalg.madera.model.ModuleWithGamme;

public class ModuleDao {

	private ModuleDao(){}
	
	public static List<ModuleWithGamme> getAllModule() throws Exception {
		List<ModuleWithGamme> moduleWithGamme = new ArrayList<>();
		ModuleWithGamme modules = null;
		String sql = "SELECT reference_module as idReference, commentaire_module as commentaire FROM module";
		Connection con = ConnectionBdd.connect();
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet res = stmt.executeQuery();
		while(res.next()){
			modules = new ModuleWithGamme();
			modules.setIdReference(res.getString("idReference"));
			modules.setCommentaire("commentaire");
			modules.setIdGamme(getListIdGamme(res.getString("idReference"), con));
			moduleWithGamme.add(modules);
		}
		stmt.close();
		con.close();
		return moduleWithGamme;
	}
	
	public static ModuleWithGamme getModuleById(String reference) throws Exception {
		ModuleWithGamme module = null;
		String sql = "SELECT reference_module as idReference, commentaire_module as commentaire FROM module WHERE reference_module = ?";
		Connection con = ConnectionBdd.connect();
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, reference);
		ResultSet res = stmt.executeQuery();
		while(res.next()){
			module = new ModuleWithGamme();
			module.setIdReference(res.getString("idReference"));
			module.setCommentaire("commentaire");
			module.setIdGamme(getListIdGamme(res.getString("idReference"), con));
		}
		stmt.close();
		con.close();
		return module;
	}

	synchronized private static List<String> getListIdGamme(String string, Connection con) throws Exception {
		List<String> lstGamme = new ArrayList<>();
		String sql = "SELECT reference_gamme as idGamme FROM module_gamme WHERE reference_module = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, string);
		ResultSet res = stmt.executeQuery();
		while(res.next()){
			lstGamme.add(res.getString("idGamme"));
		}
		stmt.close();
		return lstGamme;
	}

	public static void addModule(ModuleWithGamme module) throws Exception {
		Connection con = ConnectionBdd.connect();
		con.setAutoCommit(false);
		insertModule(module, con);
		if(!module.getIdGamme().isEmpty()){
			for( String gamme : module.getIdGamme()){
				insertModuleGamme(gamme, module.getIdReference(), con);
			}
		}
		con.commit();
		con.close();
	}
	
	public static void updateModule(ModuleWithGamme module) throws Exception {
		Connection con = ConnectionBdd.connect();
		String sql = "UPDATE module SET commentaire_module = ? WHERE reference_module = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, module.getCommentaire());
		stmt.setString(2, module.getIdReference());
		stmt.executeUpdate();
		stmt.close();
		String sql2 = "DELETE FROM module_gamme WHERE reference_module = ?";
		PreparedStatement stmt2 = con.prepareStatement(sql2);
		stmt2.setString(1, module.getIdReference());
		stmt2.executeUpdate();
		stmt2.close();
		if(!module.getIdGamme().isEmpty()){
			for( String gamme : module.getIdGamme()){
				insertModuleGamme(gamme, module.getIdReference(), con);
			}
		}
		con.close();
	}

	private static void insertModuleGamme(String gamme, String module, Connection con) throws Exception {
		String sql = "INSERT INTO module_gamme (reference_gamme, reference_module) VALUES (?,?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, gamme);
		stmt.setString(2, module);
		stmt.executeUpdate();
		stmt.close();
	}

	private static void insertModule(ModuleWithGamme module, Connection con) throws Exception {
		String sql = "INSERT INTO module (reference_module, commentaire_module) VALUES (?,?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, module.getIdReference());
		stmt.setString(2, module.getCommentaire());
		stmt.executeUpdate();
		stmt.close();
	}
}
