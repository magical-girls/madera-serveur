package com.magicalg.madera.servlet;

import java.io.IOException;
import java.text.DecimalFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.magicalg.madera.bdd.dao.DevisDao;
import com.magicalg.madera.helper.CheckTokenHelper;
import com.magicalg.madera.model.DevisId;
import com.magicalg.madera.model.Modules;

/**
 * Servlet implementation class Mail
 */
@WebServlet("/mail")
public class Mail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			response.setContentType("application/json; charset=UTF-8");
			if(!CheckTokenHelper.checkToken(request.getHeader("token"), request.getSession())){
				response.sendError(401, "Erreur token invalide");
			} else {
				if(null != request.getParameter("id")){
					try {
						DevisId devis = DevisDao.getDevisById(request.getParameter("id"));
						DecimalFormat df = new DecimalFormat();
						df.setMaximumFractionDigits(2);
						String html = "<html>"
								+ "<head><meta charset=\"utf-8\"/>"
								+ "<style>table, th, td {border: 1px solid black;}</style>"
								+ "</head>"
								+ "Madera"
								+ "<hr/>"
								+ "<h1>Votre devis :</h1>"	
								+ "<p style=\"text-align : left\"><b> Votre commercial : </b>"+ devis.getSalarie().getNom()+ " " + devis.getSalarie().getPrenom() +"</p>"
								+ "<strong>" + devis.getClient().getNom() + " " + devis.getClient().getPrenom() + "</strong>"
								+ "<br/>"+ devis.getClient().getAdresse()
								+ "<br/>" + devis.getClient().getMail()
								+ "<br/>" + devis.getClient().getTel() + "</p>"
								+ "<br/>"
								+ "<p>Voici le status de l'étude de votre devis. Le détail complet sera envoyé par courrier à votre adresse postale</p>"
								+ "<table>"
								+ "<tr>"
								+ "<td><b>Référence : </b></td>"
								+ "<td><b>Statut : </b></td>"
								+ "</tr>"
								+ "<tr>"
								+ "<td style=\"color : blue\">"+ devis.getDevis().getReference() +"</td>"
								+ "<td style=\"color : blue\">"+ devis.getDevis().getStatus()+"</td>"
								+ "</tr>"
								+ "</table>"
								+ "<br/>"
								+ "<p style=\"text-align : left\">Merci de votre confiance, l'équipe MADERA</p>"
								+ "</html>";
//						DevisPDF pdf = new DevisPDF(devis);
//						File file = pdf.getDevisPdf();
//						DevisDao.insertPDF(devis.getClient().getId(), file);
						com.magicalg.madera.helper.Mail.sendMail(devis.getClient().getMail(), "Devis Madera", html);
						response.getWriter().append(html);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				} else {
					response.sendError(500, "Pas de liste User possible");
				}
			}
		} catch (Exception e1) {
			response.sendError(401, e1.getMessage());
		}
	}

}
