package com.magicalg.madera.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.magicalg.madera.bdd.dao.LoginDao;
import com.magicalg.madera.helper.AEScrypt;
import com.magicalg.madera.model.LoginWithSalarie;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=UTF-8");
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		LoginWithSalarie login = null;
		try {
			login = LoginDao.checkLogin(name, pwd);
			if(null != login){
				String token = AEScrypt.encrypt(login.getLogin().getLogin()) + "."
						+ AEScrypt.encrypt(login.getLogin().getMdp()) + "."
						+ AEScrypt.encrypt(login.getLogin().getMatriculeSalarie()) + "."
						+ request.getSession().getId();
				response.setHeader("token", token);
				ObjectMapper mapper =  new ObjectMapper();
				response.getWriter().append(mapper.writeValueAsString(login));
			} else {
				response.sendError(401, "Bad logins");
			}
		} catch (Exception e) {
			response.sendError(500, e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("POST en cours de construction");
	}

}
