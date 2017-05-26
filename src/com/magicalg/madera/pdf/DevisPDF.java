package com.magicalg.madera.pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.magicalg.madera.entity.Angle;
import com.magicalg.madera.entity.Composant;
import com.magicalg.madera.entity.Module;
import com.magicalg.madera.model.DevisId;

public class DevisPDF {

	private static Document document;
	private static DevisId devis;
	private static PdfPTable table;
	private static float TVA = 1.2f;
	private static float totalHT = 0f;
	private static float totalTTC = 0f;
	private static String FILE = "devis.pdf";
	private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
	private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	private static Font nameFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLUE);
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");

	public static void main(String[] args) {

	}

	public DevisPDF(DevisId devis) {
		this.devis = devis;
	}

	public File getDevisPdf() {
		try {
			document = new Document();
			if (!devis.getClient().getNom().isEmpty() && devis.getClient().getNom() != null) {
				FILE = devis.getClient().getNom() + "_devis" + ".pdf";
			}
			PdfWriter.getInstance(document, new FileOutputStream(FILE));
			document.open();
			addMetaData();
			addTitlePage();
			addContent();
			document.close();
			System.out.println("OK");
			return new File(FILE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// iText allows to add metadata to the PDF which can be viewed in your Adobe
	// Reader
	// under File -> Properties
	private static void addMetaData() {
		document.addTitle("Madera");
		document.addSubject("Devis de votre maison");
		document.addKeywords("Madera");
		document.addAuthor(devis.getSalarie().getNom() + " " + devis.getSalarie().getNom());
		document.addCreator(devis.getSalarie().getNom() + " " + devis.getSalarie().getNom());
	}

	private static void addTitlePage() throws DocumentException {
		Paragraph preface = new Paragraph();
		// We add one empty line
		addEmptyLine(preface, 1);
		// Lets write a big header
		preface.add(new Paragraph("Madera", catFont));

		addEmptyLine(preface, 1);
		// Will create: Report generated by: _name, _date
		preface.add(new Paragraph("Devis pour :", smallBold));
		preface.add(new Paragraph(devis.getClient().getNom() + " " + devis.getClient().getPrenom(), nameFont));
		preface.add(new Paragraph(devis.getClient().getAdresse()));
		preface.add(new Paragraph("Téléphone : " + devis.getClient().getTel()));

		addEmptyLine(preface, 1);
		preface.add(new Paragraph(
				"Votre reponsable commercial : " + devis.getSalarie().getPrenom() + " " + devis.getSalarie().getNom(),
				smallBold));
		addEmptyLine(preface, 1);
		preface.add(new Paragraph("Voici le récapitulatif de votre devis : "));

		document.add(preface);
		// Start a new page
		// document.newPage();
	}

	private static void addContent() throws DocumentException {

		Paragraph content = new Paragraph(" ");

		// add a table
		createTable(content);
		addEmptyLine(content, 2);
		document.add(content);
		content = new Paragraph("Bon pour Accord le : " + sdf.format(new Date()));
		content.setAlignment(Element.ALIGN_RIGHT);
		document.add(content);

	}

	private static void createTable(Paragraph content) throws BadElementException {
		table = new PdfPTable(4);

		// t.setBorderColor(BaseColor.GRAY);
		// t.setPadding(4);
		// t.setSpacing(4);
		// t.setBorderWidth(1);

		PdfPCell c1 = new PdfPCell(new Phrase("Réference", smallBold));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Designation", smallBold));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Prix HT", smallBold));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Prix TTC", smallBold));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		table.setHeaderRows(1);

		addGamme();
		addListModule();
		addListAngle();
		addListComposant();
		addFooterTotal();
		content.add(table);

	}

	private static void addGamme() {

		PdfPCell c2 = new PdfPCell(new Phrase("Votre gamme : "));
		c2.setHorizontalAlignment(Element.ALIGN_LEFT);
		c2.setColspan(2);
		table.addCell(c2);
		c2 = new PdfPCell(new Phrase(" "));
		c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c2);
		c2 = new PdfPCell(new Phrase(" "));
		c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c2);

		c2 = new PdfPCell(new Phrase(devis.getGamme().getIdReference()));
		c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c2);
		c2 = new PdfPCell(new Phrase(devis.getGamme().getNom()));
		c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c2);
		c2 = new PdfPCell(new Phrase(" "));
		c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c2);
		c2 = new PdfPCell(new Phrase(" "));
		c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c2);

	}

	private static void addListModule() {

		PdfPCell c3 = new PdfPCell(new Phrase("Vos modules : "));
		c3.setHorizontalAlignment(Element.ALIGN_LEFT);
		c3.setColspan(2);
		table.addCell(c3);
		c3 = new PdfPCell(new Phrase(" "));
		c3.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c3);
		c3 = new PdfPCell(new Phrase(" "));
		c3.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c3);

		if (!devis.getLstModule().isEmpty()) {
			for (Module module : devis.getLstModule()) {
				c3 = new PdfPCell(new Phrase(module.getIdReference()));
				c3.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(c3);
				c3 = new PdfPCell(new Phrase(module.getCommentaire()));
				c3.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(c3);
				c3 = new PdfPCell(new Phrase(" "));
				c3.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(c3);
				c3 = new PdfPCell(new Phrase(" "));
				c3.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(c3);
			}
		}
	}

	private static void addListComposant() {
		PdfPCell c3 = new PdfPCell(new Phrase("Les composants nécessaires : "));
		c3.setHorizontalAlignment(Element.ALIGN_LEFT);
		c3.setColspan(2);
		table.addCell(c3);
		c3 = new PdfPCell(new Phrase(" "));
		c3.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c3);
		c3 = new PdfPCell(new Phrase(" "));
		c3.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c3);

		if (!devis.getLstComposant().isEmpty()) {
			for (Composant composant : devis.getLstComposant()) {
				c3 = new PdfPCell(new Phrase(composant.getIdReference()));
				c3.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(c3);
				c3 = new PdfPCell(new Phrase(composant.getNom()));
				c3.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(c3);
				c3 = new PdfPCell(new Phrase(String.format("%.2f", composant.getPrixHT()) + " €"));
				c3.setHorizontalAlignment(Element.ALIGN_RIGHT);
				totalHT += composant.getPrixHT();
				table.addCell(c3);
				float temp = composant.getPrixHT() * TVA;
				c3 = new PdfPCell(new Phrase(String.format("%.2f", temp) + " €"));
				c3.setHorizontalAlignment(Element.ALIGN_RIGHT);
				totalTTC += temp;
				table.addCell(c3);
			}
		}
	}

	private static void addListAngle() {
		PdfPCell c3 = new PdfPCell(new Phrase("Vos angles choisis : "));
		c3.setHorizontalAlignment(Element.ALIGN_LEFT);
		c3.setColspan(2);
		table.addCell(c3);
		c3 = new PdfPCell(new Phrase(" "));
		c3.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c3);
		c3 = new PdfPCell(new Phrase(" "));
		c3.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c3);

		if (!devis.getLstAngle().isEmpty()) {
			for (Angle angle : devis.getLstAngle()) {
				c3 = new PdfPCell(new Phrase(" "));
				c3.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(c3);
				c3 = new PdfPCell(new Phrase("Angle entre " + angle.getModuleA() + " et " + angle.getModuleB() + " : "
						+ angle.getDegre() + "°"));
				c3.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(c3);
				c3 = new PdfPCell(new Phrase(" "));
				c3.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(c3);
				c3 = new PdfPCell(new Phrase(" "));
				c3.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(c3);
			}
		}
	}

	private static void addFooterTotal() {

		PdfPCell c3 = new PdfPCell(new Phrase("Total HT : "));
		c3.setHorizontalAlignment(Element.ALIGN_RIGHT);
		c3.setColspan(3);
		table.addCell(c3);
		c3 = new PdfPCell(new Phrase(String.format("%.2f", totalHT)));
		c3.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table.addCell(c3);

		c3 = new PdfPCell(new Phrase("Total TTC : "));
		c3.setHorizontalAlignment(Element.ALIGN_RIGHT);
		c3.setColspan(3);
		table.addCell(c3);
		c3 = new PdfPCell(new Phrase(String.format("%.2f", totalTTC)));
		c3.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table.addCell(c3);

	}

	private static void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}
}
