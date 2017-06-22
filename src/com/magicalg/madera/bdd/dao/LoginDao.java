package com.magicalg.madera.bdd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.magicalg.madera.bdd.ConnectionBdd;
import com.magicalg.madera.entity.Login;
import com.magicalg.madera.entity.Salarie;
import com.magicalg.madera.model.LoginWithSalarie;

public class LoginDao {
	
	private LoginDao() {}

	public static LoginWithSalarie checkLogin(String name, String pwd) throws Exception {
		Connection con = ConnectionBdd.connect();
		Login login = new Login();
		Salarie salarie = new Salarie();
		LoginWithSalarie loginWithSalarielogin = null;
		String sql = "SELECT id_login, login_login, mdp_login, login.matricule_salarie, nom_salarie, "
				+ "prenom_salarie, mail_salarie, tel_salarie FROM login "
				+ "LEFT JOIN salarie ON salarie.matricule_salarie = login.matricule_salarie "
				+ "WHERE login_login = ? AND mdp_login = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, name);
		stmt.setString(2, pwd);
		ResultSet res = stmt.executeQuery();
		while(res.next()){
			loginWithSalarielogin = new LoginWithSalarie();
			login.setId(res.getInt("id_login"));
			login.setLogin(res.getString("login_login"));
			login.setMdp(res.getString("mdp_login"));
			login.setMatriculeSalarie(res.getString("matricule_salarie"));
			loginWithSalarielogin.setLogin(login);
			salarie.setMail(res.getString("mail_salarie"));
			salarie.setNom(res.getString("nom_salarie"));
			salarie.setPrenom(res.getString("prenom_salarie"));
			salarie.setTel(res.getString("tel_salarie"));
			loginWithSalarielogin.setSalarie(salarie);
		}
		stmt.close();
		con.close();
		return loginWithSalarielogin;
		
	}

}
