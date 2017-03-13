package com.magicalg.madera.bdd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import com.magicalg.madera.entity.Section;
import com.magicalg.madera.model.DevisId;
import com.magicalg.madera.model.ListDevis;

public class DevisDao {
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

	private DevisDao() {}
	
	public static List<ListDevis> getListDevis() throws Exception{
		List<ListDevis> lst = new ArrayList<>();
		ListDevis dev = null;
		Connection con = ConnectionBdd.connect();
		String sql = "SELECT  devis.reference_devis as reference, nom_client as client, datecreation_devis as creation,"
				+ " max(datemodif) as modif, status_devis as status FROM devis " 
				+ "LEFT JOIN client ON client.id_client = devis.id_client "
				+ "LEFT JOIN devis_salarie_modif ON devis.reference_devis = devis_salarie_modif.reference_devis "
				+ "GROUP BY devis_salarie_modif.reference_devis ORDER BY devis_salarie_modif.datemodif DESC";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet res = stmt.executeQuery();
		while(res.next()){
			dev = new ListDevis();
			dev.setReference(res.getString("reference"));
			dev.setClient(res.getString("client"));
			dev.setCreation(sdf.format(res.getDate("creation")));
			dev.setModif(sdf.format(res.getDate("modif")));
			dev.setStatus(res.getInt("status"));
			lst.add(dev);
		}
		stmt.close();
		con.close();
		return lst;
	}
	
	public static DevisId getDevisById(String reference) throws Exception {
		
		DevisId devis = new DevisId();
		
		Connection con = ConnectionBdd.connect();
		String sql = "SELECT "
		+ "client.id_client as idClient, "
		+ "nom_client as nomClient, "
		+ "prenom_client as prenomClient, "
		+ "tel_client as telClient, "
		+ "mail_client as mailClient, "
		+ "devis.reference_devis as referenceDevis, "
		+ "datecreation_devis as creationDevis, "
		+ "max(datemodif) as modifDevis, "
		+ "status_devis as statusDevis, "
		+ "salarie.matricule_salarie as matriculeCommercial, "
		+ "salarie.nom_salarie as nomCommercial, "
		+ "salarie.prenom_salarie as prenomCommercial, "
		+ "salarie.mail_salarie as mailCommercial, "
		+ "salarie.tel_salarie as telCommercial, "
		+ "gamme.reference_gamme as referenceGamme, "
		+ "gamme.nom_gamme as nomGamme, "
		+ "gamme.commentaire_gamme as commentaireGamme "
		+ "FROM devis  "
		+ "LEFT JOIN client ON client.id_client = devis.id_client  "
		+ "LEFT JOIN salarie ON salarie.matricule_salarie = devis.matricule_salarie "
		+ "LEFT JOIN devis_salarie_modif ON devis.reference_devis = devis_salarie_modif.reference_devis "
		+ "LEFT JOIN gamme ON gamme.reference_gamme = devis.reference_gamme " 
		+ "WHERE devis.reference_devis = ? AND devis.suppression_devis = 0 "
		+ "GROUP BY devis_salarie_modif.reference_devis ORDER BY devis_salarie_modif.datemodif DESC";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, reference);
		ResultSet res = stmt.executeQuery();
		while(res.next()){
			Client client = new Client(res.getInt("idClient"), res.getString("nomClient"), res.getString("prenomClient"), null, res.getString("telClient"),
					null, null, res.getString("mailClient"), null);
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
	
	
	private static List<Angle> getAngleByRefDevis(String reference, Connection con) throws Exception{
		
		List<Angle> lstAngle = new ArrayList<>();
		Angle angle = null;
		String sql = "SELECT id_angle AS idAngle, type_angle AS typeAngle, degre_angle AS degreAngle, sectionA, sectionB FROM angle "
				+ "LEFT JOIN section ON section.id_section = angle.sectionA "
				+ "LEFT JOIN devis_module_choix ON devis_module_choix.id_devismod = section.id_devis_mod "
				+ "WHERE devis_module_choix.id_devis = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, reference);
		ResultSet res = stmt.executeQuery();
		while(res.next()){
			angle = new Angle();
			angle.setId(res.getInt("idAngle"));
			angle.setType(res.getString("typeAngle"));
			angle.setDegre(res.getFloat("degreAngle"));
			angle.setSectionA(res.getInt("sectionA"));
			angle.setSectionB(res.getInt("sectionB"));
			lstAngle.add(angle);
		}
		stmt.close();
		return lstAngle;
		
	}
	
	private static List<Section> getSectionByRefDevis(String reference, Connection con) throws Exception{
		List<Section> lstSection = new ArrayList<>();
		Section section = null;
		String sql = "select id_section as idSection, longueur_section as longueurSection from section "
			+ "left join devis_module_choix on devis_module_choix.id_devismod = section.id_devis_mod "
			+ "left join devis on  devis_module_choix.id_devis = devis.reference_devis "
			+ "where devis.reference_devis = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, reference);
		ResultSet res = stmt.executeQuery();
		while(res.next()){
			section = new Section();
			section.setId(res.getInt("idSection"));
			section.setLongueur(res.getFloat("longueurSection"));
			lstSection.add(section);
		}
		stmt.close();
		return lstSection;
	}
	
	private static List<Module> getModuleByRefDevis(String reference, Connection con) throws Exception{
		List<Module> lstModule = new ArrayList<>();
		Module module = null;
		String sql ="SELECT module.reference_module as idModule, module.commentaire_module as commentaire FROM module "
			+ "LEFT JOIN devis_module_choix ON devis_module_choix.reference_module = module.reference_module "
			+ "WHERE id_devis = ? AND module.suppression_module = 0";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, reference);
		ResultSet res = stmt.executeQuery();
		while(res.next()){
			module = new Module();
			module.setIdReference(res.getString("idModule"));
			module.setCommentaire(res.getString("commentaire"));
			lstModule.add(module);
		}
		stmt.close();
		return lstModule;
	}
	
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
		while(res.next()){
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

	public static void deleteDevis(String reference) throws Exception {
		String sql = "UPDATE devis SET suppression_devis = 1 "
				+ "WHERE reference_devis = ?";
		Connection con = ConnectionBdd.connect();
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, reference);
		stmt.executeUpdate();
		stmt.close();
		con.close();
	}
}
