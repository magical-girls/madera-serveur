package com.magicalg.madera.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.magicalg.madera.bdd.dao.DevisDao;
import com.magicalg.madera.helper.CheckTokenHelper;
import com.magicalg.madera.model.DevisId;
import com.magicalg.madera.model.ListDevis;

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

}
