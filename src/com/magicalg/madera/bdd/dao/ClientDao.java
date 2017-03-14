package com.magicalg.madera.bdd.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.magicalg.madera.bdd.ConnectionBdd;
import com.magicalg.madera.entity.Client;

public class ClientDao {
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

	public ClientDao() {}
	
	public static List<Client> getAllClient()throws Exception {
		List<Client> lstClient = new ArrayList<>();
		Client client = null;
		String sql = "SELECT id_client as id, nom_client as nom, prenom_client as prenom, "
				+ "naissance_client as naissance, tel_client as tel, adresse_client as adresse, "
				+ "profession_client as profession, mail_client as mail, creation_client as creation "
				+ "FROM client";
		Connection con = ConnectionBdd.connect();
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet res = stmt.executeQuery();
		while(res.next()){
			client = new Client(res.getInt("id"), res.getString("nom"), 
					res.getString("prenom"), sdf.format(res.getDate("naissance")), res.getString("tel"), 
					res.getString("adresse"), res.getString("profession"), res.getString("mail"), sdf.format(res.getDate("creation")));
			lstClient.add(client);
		}
		stmt.close();
		con.close();
		return lstClient;
	}
	
	public static Client getClientById(Integer id)throws Exception {
		Client client = null;
		String sql = "SELECT id_client as id, nom_client as nom, prenom_client as prenom, "
				+ "naissance_client as naissance, tel_client as tel, adresse_client as adresse, "
				+ "profession_client as profession, mail_client as mail, creation_client as creation "
				+ "FROM client WHERE id_client = ?";
		Connection con = ConnectionBdd.connect();
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet res = stmt.executeQuery();
		while(res.next()){
			client = new Client(res.getInt("id"), res.getString("nom"), 
					res.getString("prenom"), sdf.format(res.getDate("naissance")), res.getString("tel"), 
					res.getString("adresse"), res.getString("profession"), res.getString("mail"), sdf.format(res.getDate("creation")));
		}
		stmt.close();
		con.close();
		return client;
	}
	
	public static void updateCient(Client client) throws Exception{
		String sql = "UPDATE client SET nom_client = ?, prenom_client = ?, naissance_client = ?, "
				+ "tel_client = ?, adresse_client = ?, profession_client = ?, mail_client = ? "
				+ "WHERE id_client = ?";
		Connection con = ConnectionBdd.connect();
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, client.getNom());
		stmt.setString(2, client.getPrenom());
		java.util.Date date = sdf.parse(client.getNaissance());
		java.sql.Date dateSql = new Date(date.getTime());
		stmt.setDate(3, dateSql);
		stmt.setString(4, client.getTel());
		stmt.setString(5, client.getAdresse());
		stmt.setString(6, client.getProfession());
		stmt.setString(7, client.getMail());
		stmt.setInt(8, client.getId());
		stmt.executeUpdate();
		stmt.close();
		con.close();
	}

	public static void addCient(Client client) throws Exception{
		String sql = "INSERT INTO client (nom_client, prenom_client, naissance_client, tel_client, "
				+ "adresse_client, profession_client, mail_client) VALUES (?,?,?,?,?,?,?)";
		Connection con = ConnectionBdd.connect();
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, client.getNom());
		stmt.setString(2, client.getPrenom());
		java.util.Date date = sdf.parse(client.getNaissance());
		java.sql.Date dateSql = new Date(date.getTime());
		stmt.setDate(3, dateSql);
		stmt.setString(4, client.getTel());
		stmt.setString(5, client.getAdresse());
		stmt.setString(6, client.getProfession());
		stmt.setString(7, client.getMail());
		stmt.executeUpdate();
		stmt.close();
		con.close();
	}
}
