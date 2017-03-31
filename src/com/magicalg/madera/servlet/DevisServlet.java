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
import com.magicalg.madera.entity.Angle;
import com.magicalg.madera.entity.Module;
import com.magicalg.madera.helper.CheckTokenHelper;
import com.magicalg.madera.helper.RequestJsonHelper;
import com.magicalg.madera.model.AddDevis;
import com.magicalg.madera.model.DevisId;
import com.magicalg.madera.model.ListDevis;
import com.magicalg.madera.model.SectionWithRefModule;

/**
 * Servlet implementation class DevisServlet
 */
@WebServlet("/devis")
public class DevisServlet extends HttpServlet {
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
					DevisId devis = DevisDao.getDevisById(request.getParameter("id"));
					ObjectMapper mapper = new ObjectMapper();
					response.getWriter().append(mapper.writeValueAsString(devis));
				}
			}
		} catch (Exception e1) {
			response.sendError(500, e1.getMessage());
		}

	}

	/**
	 * Créer devis
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
				AddDevis devis = mapper.readValue(new StringReader(json), AddDevis.class);
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
			return "Prénom du client obligatoire";
		} else if (null == devis.getNaissanceClient()){
			return "Date de naissance du client obligatoire";
		} else if (null == devis.getTelClient()){
			return "Téléphone du client obligatoire";
		} else if (null == devis.getAdresseClient()){
			return "Adresse du client obligatoire";
		} else if (null == devis.getReferenceDevis()){
			return "Référence du devis obligatoire";
		} else if (null == devis.getMargeComDevis()){
			return "Marge de com du devis obligatoire";
		} else if (null == devis.getMargeEntDevis()){
			return "Marge entreprise du devis obligatoire";
		} else if (null == devis.getIdMatriculeSalarie()){
			return "Matricule du commercial du devis obligatoire";
		} else if (null == devis.getIdReferenceGamme()){
			return "Référence gamme du devis obligatoire";
		} else if(null != devis.getLstModule() && !devis.getLstModule().isEmpty()){
			for(Module module : devis.getLstModule()){
				if(null == module.getIdReference()){
					return "Référence des modules obligatoire";
				}
			}
		} else if(null != devis.getLstSection() && !devis.getLstSection().isEmpty()){
			for(SectionWithRefModule section : devis.getLstSection()){
				if(null == section.getLongueur()){
					return "Longueur de section du devis obligatoire";
				} else if (null == section.getRefModule()){
					return "Référence du module pour la section " + section.getLongueur() + " obligatoire";
				}
			}
		} else if(null != devis.getLstAngle() && !devis.getLstAngle().isEmpty()){
			for(Angle angle : devis.getLstAngle()){
				if(null == angle.getDegre()){
					return "Degré d'angle manquant";
				} else if (null == angle.getType()){
					return "Type d'angle obligatoire";
				} else if (null == angle.getModuleA() || null == angle.getModuleB()){
					return "Deux modules sont obligatoires pour faire un angle";
				}
			}
		}
		
		return "";
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("charset=UTF-8");
		response.getWriter().append("PUT En cours de construction");
		
		try {
			if (!CheckTokenHelper.checkToken(request.getHeader("token"), request.getSession())) {
				response.sendError(401, "Erreur token invalide");
			} else {
				String json = RequestJsonHelper.getJsonFromRequest(request);
				
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
