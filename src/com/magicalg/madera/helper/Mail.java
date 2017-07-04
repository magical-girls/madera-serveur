package com.magicalg.madera.helper;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Mail {

	private static final String username = "madera.france.mg@gmail.com";
	private static final String password = "madera2017";

	public static void sendMail(String mail, String subject, String text, File file) throws IOException {
		/**
		 * ATTENTION d�verrouiller
		 * https://www.google.com/settings/security/lesssecureapps
		 **/
		System.out.println(" file " + file.getAbsolutePath());
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
			MimeMultipart multipart = new MimeMultipart();
			
			MimeBodyPart filePart = new MimeBodyPart();
			FileDataSource source = new FileDataSource(file);
			filePart.setDataHandler(new DataHandler(source));
			filePart.setFileName(file.getName());
			multipart.addBodyPart(filePart);
			
			simpleMessage.setContent(multipart);
			simpleMessage.setFrom(new InternetAddress("maderafrance@gmail.com"));
			simpleMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail));
			simpleMessage.setSubject(subject);
			simpleMessage.setText(text, "utf-8", "html");
			session.setDebug(true);
			Transport.send(simpleMessage);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
