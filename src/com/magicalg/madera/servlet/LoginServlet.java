package com.magicalg.madera.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.magicalg.madera.bdd.dao.LoginDao;
import com.magicalg.madera.entity.Login;
import com.magicalg.madera.helper.AEScrypt;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		Login login = null;
		try {
			login = LoginDao.checkLogin(name, pwd);
			if(null != login && null != login.getId()){
				String token = AEScrypt.encrypt(login.getLogin()) + "."
						+ AEScrypt.encrypt(login.getMdp()) + "."
						+ AEScrypt.encrypt(login.getMatriculeSalarie()) + "."
						+ request.getSession().getId();
				ObjectMapper mapper = new ObjectMapper();
				response.setHeader("token", token);
				response.getWriter().append("Ok");
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
