package com.magicalg.madera.bdd.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.magicalg.madera.bdd.ConnectionBdd;
import com.magicalg.madera.entity.Angle;
import com.magicalg.madera.entity.Client;
import com.magicalg.madera.entity.Composant;
import com.magicalg.madera.entity.Devis;
import com.magicalg.madera.entity.Gamme;
import com.magicalg.madera.entity.Module;
import com.magicalg.madera.entity.Salarie;
import com.magicalg.madera.model.AddDevis;
import com.magicalg.madera.model.DevisId;
import com.magicalg.madera.model.ListDevis;
import com.magicalg.madera.model.SectionWithRefModule;

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
				+ "GROUP BY devis.reference_devis ORDER BY devis_salarie_modif.datemodif DESC";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet res = stmt.executeQuery();
		while (res.next()) {
			dev = new ListDevis();
			dev.setReference(res.getString("reference"));
			dev.setClient(res.getString("client"));
			dev.setCreation(sdf.format(res.getDate("creation")));
			if(null != res.getDate("modif")){
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
				+ "prenom_client as prenomClient, " + "tel_client as telClient, " + "mail_client as mailClient, "
				+ "devis.reference_devis as referenceDevis, " + "datecreation_devis as creationDevis, "
				+ "max(datemodif) as modifDevis, " + "status_devis as statusDevis, "
				+ "salarie.matricule_salarie as matriculeCommercial, " + "salarie.nom_salarie as nomCommercial, "
				+ "salarie.prenom_salarie as prenomCommercial, " + "salarie.mail_salarie as mailCommercial, "
				+ "salarie.tel_salarie as telCommercial, " + "gamme.reference_gamme as referenceGamme, "
				+ "gamme.nom_gamme as nomGamme, " + "gamme.commentaire_gamme as commentaireGamme " + "FROM devis  "
				+ "LEFT JOIN client ON client.id_client = devis.id_client  "
				+ "LEFT JOIN salarie ON salarie.matricule_salarie = devis.matricule_salarie "
				+ "LEFT JOIN devis_salarie_modif ON devis.reference_devis = devis_salarie_modif.reference_devis "
				+ "LEFT JOIN gamme ON gamme.reference_gamme = devis.reference_gamme "
				+ "WHERE devis.reference_devis = ? AND devis.suppression_devis = 0 "
				+ "GROUP BY devis_salarie_modif.reference_devis ORDER BY devis_salarie_modif.datemodif DESC";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, reference);
		ResultSet res = stmt.executeQuery();
		while (res.next()) {
			Client client = new Client(res.getInt("idClient"), res.getString("nomClient"),
					res.getString("prenomClient"), null, res.getString("telClient"), null, null,
					res.getString("mailClient"), null);
			devis.setClient(client);
			Devis dev = new Devis();
			dev.setReference(res.getString("referenceDevis"));
			dev.setStatus(res.getInt("statusDevis"));
			dev.setDateCreation(sdf.format(res.getDate("creationDevis")));
			dev.setMotif(sdf.format(res.getDate("modifDevis")));
			devis.setDevis(dev);
			Salarie salarie = new Salarie(res.getString("matriculeCommercial"), res.getString("nomCommercial"),
					res.getString("prenomCommercial"), res.getString("mailCommercial"), res.getString("telCommercial"));
			devis.setSalarie(salarie);
			Gamme gamme = new Gamme();
			gamme.setIdReference(res.getString("referenceGamme"));
			gamme.setNom(res.getString("nomGamme"));
			gamme.setCommentaire(res.getString("commentaireGamme"));
			devis.setGamme(gamme);
			devis.setLstAngle(getAngleByRefDevis(dev.getReference(), con));
			devis.setLstComposant(getComposantByRefDevis(dev.getReference(), con));
			devis.setLstModule(getModuleByRefDevis(dev.getReference(), con));
			devis.setLstSection(getSectionByRefDevis(dev.getReference(), con));
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

		// INSERT Angle
		try {
			insertAngleForDevis(devis, con);
		} catch (Exception e) {
			con.rollback();
			con.close();
			throw new SQLException("Erreur pendant l'enregistrement des Angles : " + e.getMessage());
		}

		con.commit();
		con.close();
	}

	
	
	
	
	/**************************************************************************************/
	/** PRIVATE METHODE */
	/**************************************************************************************/

	
	
	
	/**
	 * INSERT angle pour devis
	 * 
	 * @param devis
	 * @param con
	 * @throws SQLException
	 */
	private static void insertAngleForDevis(AddDevis devis, Connection con) throws SQLException {

		for (Angle angle : devis.getLstAngle()) {
			String sqlAngle = "INSERT INTO angle (type_angle, degre_angle, moduleA, moduleB) VALUES (?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(sqlAngle);
			stmt.setString(1, angle.getType());
			stmt.setFloat(2, angle.getDegre());
			stmt.setString(3, angle.getModuleA());
			stmt.setString(4, angle.getModuleB());
			stmt.executeUpdate();
		}
	}

	/**
	 * INSERT choix des modules pour devis
	 * 
	 * @param devis
	 * @param con
	 * @throws Exception
	 */
	private static void insertChoixModuleForDevis(AddDevis devis, Connection con) throws Exception {

		for (Module refModule : devis.getLstModule()) {
			Integer idDevisMod = null;
			String sqlDevisMod = "INSERT INTO devis_module_choix (id_devis, reference_module) VALUES (?,?)";
			PreparedStatement stmtDevMod = con.prepareStatement(sqlDevisMod, Statement.RETURN_GENERATED_KEYS);
			stmtDevMod.setString(1, devis.getReferenceDevis());
			stmtDevMod.setString(2, refModule.getIdReference());
			stmtDevMod.executeUpdate();
			ResultSet res = stmtDevMod.getGeneratedKeys();
			if (res.next()) {
				idDevisMod = res.getInt(1);
			}
			if (null != idDevisMod) {
				// Pour chaque choix de module enregistré, on enregistre les
				// section
				for (SectionWithRefModule section : devis.getLstSection()) {
					if (section.getRefModule().equals(refModule.getIdReference())) {
						insertSectionForDevis(idDevisMod, section, con);
					}
				}
			} else {
				throw new SQLException();
			}
		}
	}

	/**
	 * INSERT section pour devis
	 * 
	 * @param idDevisMod
	 * @param section
	 * @param con
	 * @throws Exception
	 */
	private static void insertSectionForDevis(Integer idDevisMod, SectionWithRefModule section, Connection con)
			throws Exception {

		String sqlSection = "INSERT INTO section (longueur_section, id_devis_mod) VALUES (?,?)";
		PreparedStatement stmtSection = con.prepareStatement(sqlSection);
		stmtSection.setFloat(1, section.getLongueur());
		stmtSection.setInt(2, idDevisMod);
		stmtSection.executeUpdate();
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

	/**
	 * Obtenir les angles par référence du devis
	 * 
	 * @param reference
	 * @param con
	 * @return
	 * @throws Exception
	 */
	private static List<Angle> getAngleByRefDevis(String reference, Connection con) throws Exception {

		List<Angle> lstAngle = new ArrayList<>();
		Angle angle = null;
		String sql = "SELECT id_angle AS idAngle, type_angle AS typeAngle, degre_angle AS degreAngle, moduleA, moduleB FROM angle "
				+ "LEFT JOIN section ON section.id_section = angle.moduleA "
				+ "LEFT JOIN devis_module_choix ON devis_module_choix.id_devismod = section.id_devis_mod "
				+ "WHERE devis_module_choix.id_devis = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, reference);
		ResultSet res = stmt.executeQuery();
		while (res.next()) {
			angle = new Angle();
			angle.setId(res.getInt("idAngle"));
			angle.setType(res.getString("typeAngle"));
			angle.setDegre(res.getFloat("degreAngle"));
			angle.setModuleA(res.getString("moduleA"));
			angle.setSectionB(res.getString("moduleB"));
			lstAngle.add(angle);
		}
		stmt.close();
		return lstAngle;

	}

	/**
	 * Liste des sections avec les références des modules
	 * 
	 * @param reference
	 * @param con
	 * @return
	 * @throws Exception
	 */
	private static List<SectionWithRefModule> getSectionByRefDevis(String reference, Connection con) throws Exception {
		List<SectionWithRefModule> lstSection = new ArrayList<>();
		SectionWithRefModule section = null;
		String sql = "select id_section as idSection, longueur_section as longueurSection, "
				+ "devis_module_choix.reference_module as idModule from section "
				+ "left join devis_module_choix on devis_module_choix.id_devismod = section.id_devis_mod "
				+ "left join devis on  devis_module_choix.id_devis = devis.reference_devis "
				+ "where devis.reference_devis = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, reference);
		ResultSet res = stmt.executeQuery();
		while (res.next()) {
			section = new SectionWithRefModule();
			section.setId(res.getInt("idSection"));
			section.setLongueur(res.getFloat("longueurSection"));
			section.setRefModule(res.getString("idModule"));
			lstSection.add(section);
		}
		stmt.close();
		return lstSection;
	}

	/**
	 * Listes des modules par la référence du devis
	 * 
	 * @param reference
	 * @param con
	 * @return
	 * @throws Exception
	 */
	private static List<Module> getModuleByRefDevis(String reference, Connection con) throws Exception {
		List<Module> lstModule = new ArrayList<>();
		Module module = null;
		String sql = "SELECT module.reference_module as idModule, module.commentaire_module as commentaire FROM module "
				+ "LEFT JOIN devis_module_choix ON devis_module_choix.reference_module = module.reference_module "
				+ "WHERE id_devis = ? AND module.suppression_module = 0";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, reference);
		ResultSet res = stmt.executeQuery();
		while (res.next()) {
			module = new Module();
			module.setIdReference(res.getString("idModule"));
			module.setCommentaire(res.getString("commentaire"));
			lstModule.add(module);
		}
		stmt.close();
		return lstModule;
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
				+ "LEFT JOIN devis_module_choix ON devis_module_choix.reference_module = module.reference_module "
				+ "WHERE devis_module_choix.id_devis = ? AND composant.suppression_composant = 0";
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
