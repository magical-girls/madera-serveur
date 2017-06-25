package com.magicalg.madera.servlet;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.magicalg.madera.bdd.dao.DevisDao;
import com.magicalg.madera.helper.CheckTokenHelper;
import com.magicalg.madera.helper.RequestJsonHelper;
import com.magicalg.madera.model.AddDevis;
import com.magicalg.madera.model.DevisId;
import com.magicalg.madera.model.ListDevis;
import com.magicalg.madera.model.PutDevis;

/**
 * Servlet implementation class DevisServlet
 */
@WebServlet("/devis")
public class DevisServlet extends HttpServlet {
	
	/* TEMPORAIRE */
	private final String devisJSON = "{\"client\":{\"id\":65,\"nom\":\"meli\",\"prenom\":\"meli\",\"naissance\":null,\"tel\":\"1234567891\",\"adresse\":\"adresseClient\",\"profession\":null,\"mail\":\"melissa.fontaine4@gmail.com\",\"creation\":null},\"salarie\":{\"idMatricule\":\"125RO355LM\",\"nom\":\"Bertrand\",\"prenom\":\"Arthur\",\"mail\":\"bertrand.arthur@blabla.com\",\"tel\":\"0589562332\"},\"devis\":{\"reference\":\"maReference\",\"motif\":\"05-06-2017\",\"status\":\"En cours\",\"dateCreation\":\"05-06-2017\",\"dateFin\":null,\"tempsContruction\":null,\"prixTTC\":0.0,\"prixHT\":0.0,\"margeCom\":null,\"margeEnt\":null},\"gamme\":{\"idReference\":\"G001\",\"nom\":\"Gamme finition cr�pi\",\"commentaire\":\"Cr�pi \"},\"modules\":[{\"idChoixModule\":1,\"moduleA\":{\"id\":\"M001\",\"section\":\"Contrefort\",\"longueur\":1000},\"moduleB\":{\"id\":\"M002\",\"section\":\"Contrefort\",\"longueur\":2000},\"typeAngle\":\"Entrant\",\"Angle\":90},{\"idChoixModule\":2,\"moduleA\":{\"id\":\"M002\",\"section\":\"Contrefort\",\"longueur\":2000},\"moduleB\":{\"id\":\"M003\",\"section\":\"lisse\",\"longueur\":500},\"typeAngle\":\"Sortant\",\"Angle\":90}],\"lstComposant\":[{\"idReference\":\"C001\",\"nom\":\"Cr�pi\",\"prixHT\":100.0,\"commentaire\":\"par pot de 25 kg\",\"stock\":5.0},{\"idReference\":\"C003\",\"nom\":\"Fen�tre\",\"prixHT\":150.0,\"commentaire\":\"Par 4, double battants\",\"stock\":5.0},{\"idReference\":\"C004\",\"nom\":\"Placo-pl�tre\",\"prixHT\":70.0,\"commentaire\":\"Par lot de 25\",\"stock\":5.0},{\"idReference\":\"C005\",\"nom\":\"Visserie\",\"prixHT\":30.0,\"commentaire\":\"Par lot de 10\",\"stock\":5.0}]}";
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			response.setContentType("application/json; charset=UTF-8");
			if (!CheckTokenHelper.checkToken(request.getHeader("token"), request.getSession())) {
				response.sendError(401, "Erreur token invalide");
			} else {
				if (null == request.getParameter("id")) {
					List<ListDevis> lst = DevisDao.getListDevis();
					JSONArray jarray = new JSONArray(lst);
					response.getWriter().append(jarray.toString());
					
				} else {
					//TODO
					/* Devis hors service pour le moment, objet � refaire*/
					 DevisId devis = DevisDao.getDevisById(request.getParameter("id"));
					ObjectMapper mapper = new ObjectMapper();
					response.getWriter().append(mapper.writeValueAsString(devis));
//					response.getWriter().append(devisJSON);
				}
			}
		} catch (Exception e1) {
			response.sendError(500, e1.getMessage());
		}

	}

	/**
	 * Cr�er devis
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			if (!CheckTokenHelper.checkToken(request.getHeader("token"), request.getSession())) {
				response.sendError(401, "Erreur token invalide");
			} else {
				ObjectMapper mapper = new ObjectMapper(); 
				String json = RequestJsonHelper.getJsonFromRequest(request);
				System.out.println(json);
				AddDevis devis = mapper.readValue(new StringReader(json), AddDevis.class);
				System.out.println(devis);
				String check = checkNullAddDevis(devis);
				if(!check.isEmpty()){
					response.sendError(500, check);
				} else {
					System.out.println(devis.toString());
					DevisDao.addDevis(devis);
					response.getWriter().append("Ok");
				}
			}
		} catch (Exception e1) {
			response.sendError(500, e1.getMessage());
		}
	}
	
	private String checkNullAddDevis(AddDevis devis) {
		
		if(null == devis.getNomClient()){
			return "Nom du client obligatoire";
		} else if (null == devis.getPrenomClient()){
			return "Pr�nom du client obligatoire";
		} else if (null == devis.getNaissanceClient()){
			return "Date de naissance du client obligatoire";
		} else if (null == devis.getTelClient()){
			return "T�l�phone du client obligatoire";
		} else if (null == devis.getAdresseClient()){
			return "Adresse du client obligatoire";
		} else if (null == devis.getReferenceDevis()){
			return "R�f�rence du devis obligatoire";
		} else if (null == devis.getMargeComDevis()){
			return "Marge de com du devis obligatoire";
		} else if (null == devis.getMargeEntDevis()){
			return "Marge entreprise du devis obligatoire";
		} else if (null == devis.getIdMatriculeSalarie()){
			return "Matricule du commercial du devis obligatoire";
		} else if (null == devis.getIdReferenceGamme()){
			return "R�f�rence gamme du devis obligatoire";
		}
		return "";
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("charset=UTF-8");
			if (!CheckTokenHelper.checkToken(request.getHeader("token"), request.getSession())) {
				response.sendError(401, "Erreur token invalide");
			} else {
				String json = RequestJsonHelper.getJsonFromRequest(request);
				ObjectMapper mapper = new ObjectMapper();
				PutDevis devis = mapper.readValue(new StringReader(json), PutDevis.class);
				DevisDao.updateDevis(devis);
				response.getWriter().append("OK");
			}
		} catch (Exception e1) {
			response.sendError(500, e1.getMessage());
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("charset=UTF-8");
			if (!CheckTokenHelper.checkToken(request.getHeader("token"), request.getSession())) {
				response.sendError(401, "Erreur token invalide");
			} else {
				String json = RequestJsonHelper.getJsonFromRequest(request);
				JSONObject objJson = new JSONObject(json);
				String reference = objJson.getString("reference");
				DevisDao.deleteDevis(reference);
				response.getWriter().append("ok");
			}
		} catch (Exception e1) {
			response.sendError(500, e1.getMessage());
		}
	}

}
