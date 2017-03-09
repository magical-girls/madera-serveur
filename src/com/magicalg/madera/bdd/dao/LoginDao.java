package com.magicalg.madera.bdd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.magicalg.madera.bdd.ConnectionBdd;
import com.magicalg.madera.entity.Login;

public class LoginDao {
	
	private LoginDao() {}

	public static Login checkLogin(String name, String pwd) throws Exception {
		Connection con = ConnectionBdd.connect();
		Login login = new Login();
		String sql = "SELECT id_login, login_login, mdp_login, matricule_salarie FROM login "
				+ "WHERE login_login = ? AND mdp_login = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, name);
		stmt.setString(2, pwd);
		ResultSet res = stmt.executeQuery();
		while(res.next()){
			login.setId(res.getInt("id_login"));
			login.setLogin(res.getString("login_login"));
			login.setMdp(res.getString("mdp_login"));
			login.setMatriculeSalarie(res.getString("matricule_salarie"));
		}
		stmt.close();
		con.close();
		return login;
		
	}

}
