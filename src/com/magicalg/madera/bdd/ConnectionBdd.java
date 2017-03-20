package com.magicalg.madera.bdd;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionBdd {

	private static Connection connect;

	public static Connection connect() throws Exception {

		String url = "jdbc:mysql://127.0.0.1";
		String port = "3306";
		String userName = "root";
		/* Localhost */
		String pwd = "";
		/* sur le site */
		//String pwd = "MagicalGirl";
		String dbName = "madera-bdd";
		String urlPort = url + ":" + port + "/" + dbName;
		Class.forName("com.mysql.jdbc.Driver");
		connect = DriverManager.getConnection(urlPort, userName, pwd);
		return connect;

	}
}
