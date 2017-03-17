package com.magicalg.madera.servlet;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.magicalg.madera.bdd.dao.GammeDao;
import com.magicalg.madera.entity.Gamme;
import com.magicalg.madera.helper.CheckTokenHelper;
import com.magicalg.madera.helper.RequestJsonHelper;

/**
 * Servlet implementation class GammeServlet
 */
@WebServlet("/gamme")
public class GammeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("application/json; charset=UTF-8");
			if(!CheckTokenHelper.checkToken(request.getHeader("token"), request.getSession())){
				response.sendError(401, "Erreur token invalide");
			} else {
				if(null != request.getParameter("id")){
					ObjectMapper mapper = new ObjectMapper();
					Gamme gamme = GammeDao.getGammeById(request.getParameter("id"));
					response.getWriter().append(mapper.writeValueAsString(gamme));
				}else {
					ObjectMapper mapper = new ObjectMapper();
					List<Gamme> lstGamme = GammeDao.getAllGamme();
					response.getWriter().append(mapper.writeValueAsString(lstGamme));
				}
			}
		} catch (Exception e1) {
			response.sendError(500, e1.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("charset=UTF-8");
			if(!CheckTokenHelper.checkToken(request.getHeader("token"), request.getSession())){
				response.sendError(401, "Erreur token invalide");
			} else {
				String json = RequestJsonHelper.getJsonFromRequest(request);
				ObjectMapper mapper = new ObjectMapper();
				Gamme gamme = mapper.readValue(new StringReader(json), Gamme.class);
				GammeDao.addGamme(gamme);
				response.getWriter().append("Ok");
			}
		} catch (Exception e1) {
			response.sendError(500, e1.getMessage());
		}
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("charset=UTF-8");
			if(!CheckTokenHelper.checkToken(request.getHeader("token"), request.getSession())){
				response.sendError(401, "Erreur token invalide");
			} else {
				String json = RequestJsonHelper.getJsonFromRequest(request);
				ObjectMapper mapper = new ObjectMapper();
				Gamme gamme = mapper.readValue(new StringReader(json), Gamme.class);
				GammeDao.updateGamme(gamme);
				response.getWriter().append("Ok");
			}
		} catch (Exception e) {
			response.sendError(500, e.getMessage());
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("charset=UTF-8");
			if(!CheckTokenHelper.checkToken(request.getHeader("token"), request.getSession())){
				response.sendError(401, "Erreur token invalide");
			} else {
				String json = RequestJsonHelper.getJsonFromRequest(request);
				JSONObject objJson = new JSONObject(json);
				String reference = objJson.getString("idReference");
				GammeDao.deleteGamme(reference);
				response.getWriter().append("Ok : " + reference);
			}
		} catch (Exception e) {
			response.sendError(500, e.getMessage());
		}
	}

}
