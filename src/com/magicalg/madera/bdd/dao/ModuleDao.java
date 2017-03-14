package com.magicalg.madera.bdd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.magicalg.madera.bdd.ConnectionBdd;
import com.magicalg.madera.model.LstModule;

public class ModuleDao {

	private ModuleDao(){}
	
	public static List<LstModule> getAllModule() throws Exception {
		List<LstModule> lstModule = new ArrayList<>();
		LstModule modules = null;
		String sql = "SELECT reference_module as idReference, commentaire_module as commentaire FROM module";
		Connection con = ConnectionBdd.connect();
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet res = stmt.executeQuery();
		while(res.next()){
			modules = new LstModule();
			modules.setIdReference(res.getString("idReference"));
			modules.setCommentaire("commentaire");
			modules.setIdGamme(getListIdGamme(res.getString("idReference"), con));
			lstModule.add(modules);
		}
		stmt.close();
		con.close();
		return lstModule;
	}
	
	public static LstModule getModuleById(String reference) throws Exception {
		LstModule module = null;
		String sql = "SELECT reference_module as idReference, commentaire_module as commentaire FROM module WHERE reference_module = ?";
		Connection con = ConnectionBdd.connect();
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, reference);
		ResultSet res = stmt.executeQuery();
		while(res.next()){
			module = new LstModule();
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
}
