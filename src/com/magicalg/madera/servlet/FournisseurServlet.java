package com.magicalg.madera.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.magicalg.madera.bdd.dao.ComposantDao;
import com.magicalg.madera.bdd.dao.FournisseurDao;
import com.magicalg.madera.entity.Fournisseur;
import com.magicalg.madera.helper.CheckTokenHelper;
import com.magicalg.madera.model.ComposantWithModule;

/**
 * Servlet implementation class ComposantServlet
 */
@WebServlet("/fournisseur")
public class FournisseurServlet extends HttpServlet {
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
				if(null == request.getParameter("id")){
					ObjectMapper mapper = new ObjectMapper();
					List<Fournisseur> fs = FournisseurDao.getAllFournisseur();
					response.getWriter().append(mapper.writeValueAsString(fs));
				} else {
					ObjectMapper mapper = new ObjectMapper();
					Fournisseur f = FournisseurDao.getFournisseur(Integer.valueOf(request.getParameter("id")));
					response.getWriter().append(mapper.writeValueAsString(f));
//					response.getWriter().append("PAS DE FOUNISSEUR PAR ID");
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
	}

}
