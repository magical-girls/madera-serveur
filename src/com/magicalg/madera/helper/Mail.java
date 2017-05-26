package com.magicalg.madera.helper;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {

	private static final String username = "madera.france.mg@gmail.com";
	private static final String password = "madera2017";

	public static void sendMail(String mail, String subject, String text) {
		/**
		 * ATTENTION déverrouiller
		 * https://www.google.com/settings/security/lesssecureapps
		 **/

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			MimeMessage simpleMessage = new MimeMessage(session);
//			Message message = new MimeMessage(simpleMessage);
			simpleMessage.setFrom(new InternetAddress("maderafrance@gmail.com"));
			simpleMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail));
			simpleMessage.setSubject(subject);
			simpleMessage.setText(text, "utf-8", "html");
			Transport.send(simpleMessage);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
