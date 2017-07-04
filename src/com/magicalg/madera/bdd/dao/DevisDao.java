package com.magicalg.madera.bdd.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.magicalg.madera.bdd.ConnectionBdd;
import com.magicalg.madera.entity.Client;
import com.magicalg.madera.entity.Composant;
import com.magicalg.madera.entity.Devis;
import com.magicalg.madera.entity.Gamme;
import com.magicalg.madera.entity.Salarie;
import com.magicalg.madera.model.AddDevis;
import com.magicalg.madera.model.DevisId;
import com.magicalg.madera.model.ListDevis;
import com.magicalg.madera.model.Modules;
import com.magicalg.madera.model.PutDevis;

public class DevisDao {

	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

	private DevisDao() {
	}

	/**
	 * Liste de tous les devis
	 * 
	 * @return
	 * @throws Exception
	 */
	public static List<ListDevis> getListDevis() throws Exception {
		List<ListDevis> lst = new ArrayList<>();
		ListDevis dev = null;
		Connection con = ConnectionBdd.connect();
		String sql = "SELECT  devis.reference_devis as reference, nom_client as client, datecreation_devis as creation,"
				+ " max(datemodif) as modif, status_devis as status FROM devis "
				+ "LEFT JOIN client ON client.id_client = devis.id_client "
				+ "LEFT JOIN devis_salarie_modif ON devis.reference_devis = devis_salarie_modif.reference_devis "
				+ "WHERE devis.suppression_devis = 0 "
				+ "GROUP BY devis_salarie_modif.reference_devis ORDER BY devis_salarie_modif.datemodif DESC";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet res = stmt.executeQuery();
		while (res.next()) {
			dev = new ListDevis();
			dev.setReference(res.getString("reference"));
			dev.setClient(res.getString("client"));
			dev.setCreation(sdf.format(res.getDate("creation")));
			if (null != res.getDate("modif")) {
				dev.setModif(sdf.format(res.getDate("modif")));
			}
			dev.setStatus(res.getInt("status"));
			lst.add(dev);
		}
		stmt.close();
		con.close();
		return lst;
	}

	/**
	 * Obtenir un devis par l'id (vient du clique sur la liste des devis)
	 * 
	 * @param reference
	 * @return
	 * @throws Exception
	 */
	public static DevisId getDevisById(String reference) throws Exception {

		DevisId devis = new DevisId();

		Connection con = ConnectionBdd.connect();
		String sql = "SELECT " + "client.id_client as idClient, " + "nom_client as nomClient, "
				+ "prenom_client as prenomClient, " + "tel_client as telClient, "
				+ "mail_client as mailClient, adresse_client as adresse, " + "devis.reference_devis as referenceDevis, "
				+ "datecreation_devis as creationDevis, " + "max(datemodif) as modifDevis, "
				+ "status_devis as statusDevis, " + "prixht_devis as prixHT, prixttc_devis as prixTTC, "
				+ "salarie.matricule_salarie as matriculeCommercial, " + "salarie.nom_salarie as nomCommercial, "
				+ "salarie.prenom_salarie as prenomCommercial, " + "salarie.mail_salarie as mailCommercial, "
				+ "salarie.tel_salarie as telCommercial, " + "gamme.reference_gamme as referenceGamme, "
				+ "gamme.nom_gamme as nomGamme, " + "gamme.commentaire_gamme as commentaireGamme " + "FROM devis  "
				+ "LEFT JOIN client ON client.id_client = devis.id_client  "
				+ "LEFT JOIN salarie ON salarie.matricule_salarie = devis.matricule_salarie "
				+ "LEFT JOIN devis_salarie_modif ON devis.reference_devis = devis_salarie_modif.reference_devis "
				+ "LEFT JOIN gamme ON gamme.reference_gamme = devis.reference_gamme "
				+ "WHERE devis.reference_devis = ? " + "GROUP BY devis_salarie_modif.reference_devis ";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, reference);
		ResultSet res = stmt.executeQuery();
		while (res.next()) {
			Client client = new Client(res.getInt("idClient"), res.getString("nomClient"),
					res.getString("prenomClient"), null, res.getString("telClient"), res.getString("adresse"), null,
					res.getString("mailClient"), null);
			devis.setClient(client);
			Devis dev = new Devis();
			dev.setReference(res.getString("referenceDevis"));
			dev.setStatus(res.getInt("statusDevis"));
			if (null != res.getDate("creationDevis")) {
				dev.setDateCreation(sdf.format(res.getDate("creationDevis")));
			}
			if (null != res.getDate("modifDevis")) {
				dev.setMotif(sdf.format(res.getDate("modifDevis")));
			}
			dev.setPrixHT(res.getFloat("prixHT"));
			dev.setPrixTTC(res.getFloat("prixTTC"));
			devis.setDevis(dev);
			Salarie salarie = new Salarie(res.getString("matriculeCommercial"), res.getString("nomCommercial"),
					res.getString("prenomCommercial"), res.getString("mailCommercial"), res.getString("telCommercial"));
			devis.setSalarie(salarie);
			Gamme gamme = new Gamme();
			gamme.setIdReference(res.getString("referenceGamme"));
			gamme.setNom(res.getString("nomGamme"));
			gamme.setCommentaire(res.getString("commentaireGamme"));
			devis.setGamme(gamme);
			devis.setLstComposant(getComposantByRefDevis(dev.getReference(), con));
			devis.setLstModule(getModuleByRefDevis(dev.getReference(), con));
		}
		stmt.close();
		con.close();

		return devis;
	}

	/**
	 * Suppression d'un devis
	 * 
	 * @param reference
	 * @throws Exception
	 */
	public static void deleteDevis(String reference) throws Exception {
		String sql = "UPDATE devis SET suppression_devis = 1 " + "WHERE reference_devis = ?";
		Connection con = ConnectionBdd.connect();
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, reference);
		stmt.executeUpdate();
		stmt.close();
		con.close();
	}

	/**
	 * Ajout d'un devis
	 * 
	 * @param devis
	 * @return
	 * @throws Exception
	 */
	public static void addDevis(AddDevis devis) throws Exception {

		Connection con = ConnectionBdd.connect();
		con.setAutoCommit(false);

		// INSERT client
		Integer idClient = null;
		try {
			idClient = insertClientForDevis(devis, con);
			if (null == idClient) {
				con.rollback();
				con.close();
				throw new SQLException("Erreur pendant l'enregistrement du client");
			}
		} catch (Exception e) {
			con.rollback();
			con.close();
			throw new SQLException("Erreur pendant l'enregistrement du client : " + e.getMessage());
		}

		// INSERT devis
		try {
			insertDevisForDevis(devis, idClient, con);
		} catch (Exception e) {
			con.rollback();
			con.close();
			throw new SQLException("Erreur pendant l'enregistrement du devis : " + e.getMessage());
		}

		// INSERT Module
		try {
			insertChoixModuleForDevis(devis, con);
		} catch (Exception e) {
			con.rollback();
			con.close();
			throw new SQLException("Erreur pendant l'enregistrement des modules : " + e.getMessage());
		}

		insertModifForDevis(devis, con);

		con.commit();
		con.close();
	}

	public static void updateDevis(PutDevis devis) throws Exception {
		Client client = ClientDao.getClientById(devis.getClient().getId());
		if (!client.equals(devis.getClient())) {
			ClientDao.updateCient(client);
		}
		String sql = "UPDATE devis SET motif_devis = ?, status_devis = ?, datefin_devis = ?, tempsconstr_devis = ?,"
				+ " prixttc_devis = ?, prixht_devis = ?, margeCom_devis = ?, margeEnt_devis = ?, reference_gamme = ? WHERE reference_devis = ?";
		Connection con = ConnectionBdd.connect();
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, devis.getDevis().getMotif());
		if (devis.getDevis().getStatus().equals("Refusé")) {
			stmt.setInt(2, -1);
			stmt.setTimestamp(3, new Timestamp(new java.util.Date().getTime()));
		} else if (devis.getDevis().getStatus().equals("En attente")) {
			stmt.setInt(2, 1);
			stmt.setTimestamp(3, null);
		} else if (devis.getDevis().getStatus().equals("Validé")) {
			stmt.setInt(2, 2);
			stmt.setTimestamp(3, new Timestamp(new java.util.Date().getTime()));
		} else {
			stmt.setInt(2, 0);
			stmt.setTimestamp(3, null);
		}
		if (null == devis.getDevis().getTempsContruction()) {
			stmt.setInt(4, 0);
		} else {
			stmt.setInt(4, devis.getDevis().getTempsContruction());
		}
		if (null == devis.getDevis().getPrixTTC()) {
			stmt.setFloat(5, 0);
		} else {
			stmt.setFloat(5, devis.getDevis().getPrixTTC());
		}
		if (null == devis.getDevis().getPrixHT()) {
			stmt.setFloat(6, 0);
		} else {
			stmt.setFloat(6, devis.getDevis().getPrixHT());
		}
		if (null == devis.getDevis().getMargeCom()) {
			stmt.setFloat(7, 0);
		} else {
			stmt.setFloat(7, devis.getDevis().getMargeCom());
		}
		if (null == devis.getDevis().getMargeEnt()) {
			stmt.setFloat(8, 0);
		} else {
			stmt.setFloat(8, devis.getDevis().getMargeEnt());
		}
		stmt.setString(9, devis.getGamme().getIdReference());
		stmt.setString(10, devis.getDevis().getReference());
		stmt.executeUpdate();
		stmt.close();
		updateChoixModuleForDevis(devis, con);
		con.close();

	}

	/**************************************************************************************/
	/** PRIVATE METHODE */
	/**************************************************************************************/

	/**
	 * INSERT choix des modules pour devis
	 * 
	 * @param devis
	 * @param con
	 * @throws Exception
	 */
	private static void insertChoixModuleForDevis(AddDevis devis, Connection con) throws Exception {
		Integer sectionA = null;
		Integer sectionB = null;
		Integer typeAngle = null;
		String sqlGetSection = "SELECT id_section FROM section WHERE nom_section = ?";
		String sqlTypeAngle = "SELECT id_angle FROM angle WHERE type_angle = ?";

		for (Modules refModule : devis.getModules()) {
			//Section Module A
			PreparedStatement stmt = con.prepareStatement(sqlGetSection);
			stmt.setString(1, refModule.getModuleA().getSection());
			ResultSet set = stmt.executeQuery();
			while (set.next()) {
				sectionA = set.getInt("id_section");
			}
			stmt.close();
			// section Module B
			if (null != refModule.getModuleB().getId()) {
				PreparedStatement stmt2 = con.prepareStatement(sqlGetSection);
				stmt2.setString(1, refModule.getModuleB().getSection());
				ResultSet set2 = stmt2.executeQuery();
				while (set2.next()) {
					sectionB = set2.getInt("id_section");
				}
				stmt2.close();

				// type d'angle
				PreparedStatement stmt3 = con.prepareStatement(sqlTypeAngle);
				stmt3.setString(1, refModule.getTypeAngle());
				ResultSet set3 = stmt3.executeQuery();
				while (set3.next()) {
					typeAngle = set3.getInt("id_angle");
				}
				stmt3.close();
			}
			// Enregistrement devis_module_choix
			String sqlDevisMod = "INSERT INTO devis_module_choix (id_devis, moduleA, id_sectionA,"
					+ "longueurA, moduleB, id_sectionB, longueurB, id_angle, angle) VALUES (?,?,?,?,?,?,?,?,?)";
			PreparedStatement stmtDevMod = con.prepareStatement(sqlDevisMod);
			stmtDevMod.setString(1, devis.getReferenceDevis());
			stmtDevMod.setString(2, refModule.getModuleA().getId());
			stmtDevMod.setInt(3, sectionA);
			stmtDevMod.setInt(4, refModule.getModuleA().getLongueur());
			stmtDevMod.setString(5, refModule.getModuleB().getId());
			stmtDevMod.setObject(6, null, sectionB);
			stmtDevMod.setObject(7, null, refModule.getModuleB().getLongueur());
			stmtDevMod.setObject(8, null, typeAngle);
			stmtDevMod.setObject(9, null, refModule.getAngle());
			stmtDevMod.executeUpdate();
		}
	}

	/**
	 * UPDATE choix des modules pour devis
	 * 
	 * @param devis
	 * @param con
	 * @throws Exception
	 */
	private static void updateChoixModuleForDevis(PutDevis devis, Connection con) throws Exception {
		Integer sectionA = null;
		Integer sectionB = null;
		Integer typeAngle = null;
		String sqlGetSection = "SELECT id_section FROM section WHERE nom_section = ?";
		String sqlTypeAngle = "SELECT id_angle FROM angle WHERE type_angle = ?";
		List<Modules> lstUpdate = new ArrayList<>();
		List<Modules> lstInsert = new ArrayList<>();
		for (Modules module : devis.getModules()) {
			if (null == module.getIdChoixModule()) {
				lstInsert.add(module);
			} else {
				lstUpdate.add(module);
			}
		}
		if(lstUpdate.size() > 0){
			for (Modules up : lstUpdate) {
				// Section Module A
				PreparedStatement stmt = con.prepareStatement(sqlGetSection);
				stmt.setString(1, up.getModuleA().getSection());
				ResultSet set = stmt.executeQuery();
				while (set.next()) {
					sectionA = set.getInt("id_section");
				}
				stmt.close();
				// section Module B
				if (null != up.getModuleB().getId()) {
					PreparedStatement stmt2 = con.prepareStatement(sqlGetSection);
					stmt2.setString(1, up.getModuleB().getSection());
					ResultSet set2 = stmt2.executeQuery();
					while (set2.next()) {
						sectionB = set2.getInt("id_section");
					}
					stmt2.close();
	
					// type d'angle
					PreparedStatement stmt3 = con.prepareStatement(sqlTypeAngle);
					stmt3.setString(1, up.getTypeAngle());
					ResultSet set3 = stmt3.executeQuery();
					while (set3.next()) {
						typeAngle = set3.getInt("id_angle");
					}
					stmt3.close();
				}
				// Enregistrement devis_module_choix
				String sqlDevisMod = "UPDATE devis_module_choix SET moduleA = ?, id_sectionA = ?,"
						+ "longueurA = ?, moduleB = ?, id_sectionB = ?, longueurB = ?, id_angle = ?, angle = ? "
						+ "WHERE id_devismod = ?";
				PreparedStatement stmtDevMod = con.prepareStatement(sqlDevisMod);
				stmtDevMod.setString(1, up.getModuleA().getId());
				stmtDevMod.setInt(2, sectionA);
				stmtDevMod.setInt(3, up.getModuleA().getLongueur());
				stmtDevMod.setString(4, up.getModuleB().getId());
				stmtDevMod.setObject(5, null, sectionB);
				stmtDevMod.setObject(6, null, up.getModuleB().getLongueur());
				stmtDevMod.setObject(7, null, typeAngle);
				stmtDevMod.setObject(8, null, up.getAngle());
				stmtDevMod.setInt(9, up.getIdChoixModule());
				stmtDevMod.executeUpdate();
				stmtDevMod.close();
			}
		}
		
		if(lstInsert.size() > 0){
			for (Modules ins : lstInsert) {
				// Section Module A
				PreparedStatement stmt = con.prepareStatement(sqlGetSection);
				stmt.setString(1, ins.getModuleA().getSection());
				ResultSet set = stmt.executeQuery();
				while (set.next()) {
					sectionA = set.getInt("id_section");
				}
				stmt.close();
				// section Module B
				if (null != ins.getModuleB().getId()) {
					PreparedStatement stmt2 = con.prepareStatement(sqlGetSection);
					stmt2.setString(1, ins.getModuleB().getSection());
					ResultSet set2 = stmt2.executeQuery();
					while (set2.next()) {
						sectionB = set2.getInt("id_section");
					}
					stmt2.close();
	
					// type d'angle
					PreparedStatement stmt3 = con.prepareStatement(sqlTypeAngle);
					stmt3.setString(1, ins.getTypeAngle());
					ResultSet set3 = stmt3.executeQuery();
					while (set3.next()) {
						typeAngle = set3.getInt("id_angle");
					}
					stmt3.close();
				}
				// Enregistrement devis_module_choix
				String sqlDevisMod = "INSERT INTO devis_module_choix (id_devis, moduleA, id_sectionA,"
						+ "longueurA, moduleB, id_sectionB, longueurB, id_angle, angle) VALUES (?,?,?,?,?,?,?,?,?)";
				PreparedStatement stmtDevMod = con.prepareStatement(sqlDevisMod);
				stmtDevMod.setString(1, devis.getDevis().getReference());
				stmtDevMod.setString(2, ins.getModuleA().getId());
				stmtDevMod.setInt(3, sectionA);
				stmtDevMod.setInt(4, ins.getModuleA().getLongueur());
				stmtDevMod.setString(5, ins.getModuleB().getId());
				stmtDevMod.setObject(6, sectionB);
				stmtDevMod.setObject(7, ins.getModuleB().getLongueur());
				stmtDevMod.setObject(8, typeAngle);
				stmtDevMod.setObject(9, ins.getAngle());
				stmtDevMod.executeUpdate();
			}
		}
	}

	
	
	/**
	 * INSERT devis pour le devis
	 * 
	 * @param devis
	 * @param idClient
	 * @param con
	 * @throws Exception
	 */
	private static void insertDevisForDevis(AddDevis devis, Integer idClient, Connection con) throws Exception {
		String sqlDevis = "INSERT INTO devis (reference_devis, motif_devis, margeCom_devis, margeEnt_devis, "
				+ "matricule_salarie, id_client, reference_gamme) VALUES (?,?,?,?,?,?,?)";
		PreparedStatement stmtDevis = con.prepareStatement(sqlDevis);
		stmtDevis.setString(1, devis.getReferenceDevis());
		stmtDevis.setString(2, devis.getMotifDevis());
		stmtDevis.setFloat(3, devis.getMargeComDevis());
		stmtDevis.setFloat(4, devis.getMargeEntDevis());
		stmtDevis.setString(5, devis.getIdMatriculeSalarie());
		stmtDevis.setInt(6, idClient);
		stmtDevis.setString(7, devis.getIdReferenceGamme());
		stmtDevis.executeUpdate();
	}

	/**
	 * 
	 * @param devis
	 * @param con
	 * @throws Exception
	 */
	private static void insertModifForDevis(AddDevis devis, Connection con) throws Exception {
		String sqlDevis = "INSERT INTO devis_salarie_modif (reference_devis, matricule_salarie) VALUES (?,?)";
		PreparedStatement stmtDevis = con.prepareStatement(sqlDevis);
		stmtDevis.setString(1, devis.getReferenceDevis());
		stmtDevis.setString(2, devis.getIdMatriculeSalarie());
		stmtDevis.executeUpdate();
	}

	/**
	 * INSERT client pour le devis
	 * 
	 * @param devis
	 * @param con
	 * @return
	 * @throws Exception
	 */
	private static Integer insertClientForDevis(AddDevis devis, Connection con) throws Exception {
		Integer idClient = null;
		String sqlClient = "INSERT INTO client (nom_client, prenom_client, naissance_client, "
				+ "tel_client, adresse_client, profession_client, mail_client) VALUES (?,?,?,?,?,?,?)";
		PreparedStatement stmtClient = con.prepareStatement(sqlClient, Statement.RETURN_GENERATED_KEYS);
		stmtClient.setString(1, devis.getNomClient());
		stmtClient.setString(2, devis.getPrenomClient());
		stmtClient.setDate(3, new Date(sdf.parse(devis.getNaissanceClient()).getTime()));
		stmtClient.setString(4, devis.getTelClient());
		stmtClient.setString(5, devis.getAdresseClient());
		stmtClient.setString(6, devis.getProfessionClient());
		stmtClient.setString(7, devis.getMailClient());
		stmtClient.executeUpdate();
		ResultSet rs = stmtClient.getGeneratedKeys();
		if (rs.next()) {
			idClient = rs.getInt(1);
		}
		return idClient;
	}

	private static List<Modules> getModuleByRefDevis(String reference, Connection con) throws Exception {
		List<Modules> lstDevisModuleChoix = new ArrayList<>();
		Modules mod = null;
		String sql = "SELECT devis_module_choix.id_devismod as idChoixModule, a.reference_module as idModuleA,"
				+ " c.nom_section as sectionA, devis_module_choix.longueurA, b.reference_module as idModuleB,"
				+ " d.nom_section as sectionB, devis_module_choix.longueurB, angle.type_angle, devis_module_choix.angle"
				+ " FROM devis_module_choix"
				+ " LEFT JOIN module as a ON a.reference_module = devis_module_choix.moduleA"
				+ " LEFT JOIN module as b ON b.reference_module = devis_module_choix.moduleB"
				+ " LEFT JOIN section as c ON c.id_section = devis_module_choix.id_sectionA"
				+ " LEFT JOIN section as d ON d.id_section = devis_module_choix.id_sectionB"
				+ " LEFT JOIN angle ON angle.id_angle = devis_module_choix.id_angle"
				+ " WHERE devis_module_choix.id_devis = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, reference);
		ResultSet res = stmt.executeQuery();
		while (res.next()) {
			mod = new Modules();
			mod.setIdChoixModule(res.getInt("idChoixModule"));
			mod.getModuleA().setId(res.getString("idModuleA"));
			mod.getModuleA().setSection(res.getString("sectionA"));
			mod.getModuleA().setLongueur(res.getInt("longueurA"));
			mod.getModuleB().setId(res.getString("idModuleB"));
			mod.getModuleB().setSection(res.getString("sectionB"));
			mod.getModuleB().setLongueur(res.getInt("longueurA"));
			mod.setTypeAngle(res.getString("type_angle"));
			mod.setAngle(res.getInt("angle"));
			lstDevisModuleChoix.add(mod);
		}

		return lstDevisModuleChoix;
	}

	/**
	 * Liste des composants par référence du devis
	 * 
	 * @param reference
	 * @param con
	 * @return
	 * @throws Exception
	 */
	private static List<Composant> getComposantByRefDevis(String reference, Connection con) throws Exception {
		List<Composant> lstComposant = new ArrayList<>();
		Composant composant = null;
		String sql = "SELECT composant.reference_composant as idComposant, nom_composant as nomComposant, "
				+ "prixht_composant as prixHTComposant, commentaire_composant as commentaireComposant, "
				+ "stock_composant as stockComposant FROM composant "
				+ "LEFT JOIN composant_module ON composant_module.reference_composant = composant.reference_composant "
				+ "LEFT JOIN module ON module.reference_module = composant_module.reference_module "
				+ "LEFT JOIN devis_module_choix as a ON a.moduleA = module.reference_module "
				+ "LEFT JOIN devis_module_choix as b ON b.moduleB = module.reference_module "
				+ "WHERE a.id_devis = ? AND composant.suppression_composant = 0";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, reference);
		ResultSet res = stmt.executeQuery();
		while (res.next()) {
			composant = new Composant();
			composant.setIdReference(res.getString("idComposant"));
			composant.setNom(res.getString("nomComposant"));
			composant.setCommentaire(res.getString("commentaireComposant"));
			composant.setPrixHT(res.getFloat("prixHTComposant"));
			composant.setStock(res.getFloat("stockComposant"));
			lstComposant.add(composant);
		}
		stmt.close();
		return lstComposant;
	}

}
