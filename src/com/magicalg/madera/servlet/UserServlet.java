package com.magicalg.madera.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.magicalg.madera.bdd.dao.SalarieDao;
import com.magicalg.madera.entity.Salarie;
import com.magicalg.madera.helper.CheckTokenHelper;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			if(!CheckTokenHelper.checkToken(request.getHeader("token"), request.getSession())){
				response.sendError(401, "Erreur token invalide");
			} else {
				if(null != request.getParameter("id")){
					Salarie user = SalarieDao.getSalarie(request.getParameter("id"));
					ObjectMapper mapper = new ObjectMapper();
					response.getWriter().append(mapper.writeValueAsString(user));
				} else {
					response.sendError(500, "Pas de liste User possible");
				}
			}
		} catch (Exception e1) {
			response.sendError(401, e1.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("POST En cours de construction");
	}

}
