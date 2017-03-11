package com.magicalg.madera.servlet;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.magicalg.madera.bdd.dao.ClientDao;
import com.magicalg.madera.entity.Client;
import com.magicalg.madera.helper.CheckTokenHelper;
import com.magicalg.madera.helper.RequestJsonHelper;

/**
 * Servlet implementation class ClientServlet
 */
@WebServlet("/client")
public class ClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			if(!CheckTokenHelper.checkToken(request.getHeader("token"), request.getSession())){
				response.sendError(401, "Erreur token invalide");
			} else {
				if(null == request.getParameter("id")){
					List<Client> lst = ClientDao.getAllClient();
					ObjectMapper mapper = new ObjectMapper();
					response.getWriter().append(mapper.writeValueAsString(lst));
				} else {
					ObjectMapper mapper = new ObjectMapper();
					Client client = ClientDao.getClientById(Integer.valueOf(request.getParameter("id")));
					response.getWriter().append(mapper.writeValueAsString(client));
				}
			}
		} catch (Exception e1) {
			response.sendError(500, e1.getMessage());
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if(!CheckTokenHelper.checkToken(request.getHeader("token"), request.getSession())){
				response.sendError(401, "Erreur token invalide");
			} else {
				
				String data = RequestJsonHelper.getJsonFromRequest(request);
				ObjectMapper mapper = new ObjectMapper();
				Client client = mapper.readValue(new StringReader(data), Client.class);
				ClientDao.updateCient(client);
				response.getWriter().append("Ok");
			}
		} catch (Exception e1) {
			response.sendError(500, e1.getMessage());
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	
}
