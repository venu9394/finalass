package com.tds.services;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.html.simpleparser.HTMLWorker; // deprecated
import com.itextpdf.text.html.simpleparser.StyleSheet;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;

import java.io.File;


public class TdsForeCast_bkp {
	// itextpdf-5.4.1.jar  http://sourceforge.net/projects/itext/files/iText/
	public static void main(String ... args ) {

		// TdsForeCast.myCode();

	}
	public static void myCode(String proposedMonths, Map basic, Map taxException, ArrayList taxempids, ArrayList empids, Map finalComponents, Map TaxSections,String PathCode){ 

		myCode_FillData(proposedMonths, basic, taxException, taxempids, empids, finalComponents, TaxSections,PathCode);

	}

	public static void myCode_FillData(String proposedMonths, Map basic, Map TaxException, ArrayList taxempids, ArrayList empids, Map finalComponents, Map TaxSections,String PathCode){


		
		try {
			
			
			String FilePath="C:/TDS_FORECAST";
			String Sub_FilePath="C:/TDS_FORECAST/"+PathCode+"/FORECAST/";

			//Sub_FilePath="//10.30.0.24/f/java/TDS_ forcast/";
			Sub_FilePath=Sub_FilePath.concat(basic.get("BuName").toString());

			File file = new File(FilePath);
			if (!file.exists()) {
				if (file.mkdir()) {
					//System.out.println("Directory is created!");
				} else {
					//System.out.println("Failed to create directory!");
				}
			}

			File files = new File(Sub_FilePath);
			if (!files.exists()) {
				if (files.mkdirs()) {
					//System.out.println("Multiple directories are created!");
				} else {
					//System.out.println("Failed to create multiple directories!");
				}
			}


			//System.out.println("step 1");

			Date dNow = new Date();
			SimpleDateFormat ft = 
					new SimpleDateFormat ("dd.MM.yyyy");

			// new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
		//	System.out.println( ft.format(dNow) );


			String ProposedMonths=proposedMonths;

			NumberFormat formatter = new DecimalFormat("#0.00");

			//12345678L


			int Total_Months=0;
			int Total_Months_paid=0;
			java.util.Iterator empseq = taxempids.iterator();


			while(empseq.hasNext()){

				Document document = new Document(PageSize.A4);
				String empid= empseq.next().toString(); 
				Double gross=0.0 , pt=0.0 , income=0.0 ;
				gross=Double.parseDouble(basic.get(empid+".gross").toString());
				pt=Double.parseDouble(basic.get(empid+".Pt").toString());
				income=gross-pt;
				Total_Months=(Integer.parseInt(basic.get(empid+".pm").toString()))+(Integer.parseInt(ProposedMonths));

				Total_Months_paid=Integer.parseInt(basic.get(empid+".pm").toString());
			//	System.out.println("step 2");

				PdfWriter.getInstance(document, new FileOutputStream(Sub_FilePath+"/"+empid+".pdf"));

			//	System.out.println("step 3");

				float fntSize, lineSpacing;
				fntSize = 14f;
				lineSpacing = 15f;
				BaseFont base = null;
				try {
					base = BaseFont.createFont("c:/Windows/fonts/Calibri.ttf", BaseFont.WINANSI, false);
				} catch (DocumentException e3) {
					e3.printStackTrace();
				} catch (IOException e3) {
					e3.printStackTrace();
				}
				document.open();

				
				float[] columnWidths = {30,30,20,20};
		        PdfPTable table = new PdfPTable(columnWidths);
		        table.setWidthPercentage(100);
		        
				//PdfPTable table = new PdfPTable(4);
				//table.setWidthPercentage(100);

				PdfPCell cell;
				PdfPCell cell1;
				PdfPCell cell2;

				Font font = new Font(base, 10f, Font.BOLD);
				Font font1 = new Font(base, 10f, Font.BOLD);
				Paragraph  p = new Paragraph("");

				p = new Paragraph(new Phrase(lineSpacing,"Income Tax Forecasting Report", font));
				p.setAlignment(Element.ALIGN_CENTER);
				cell = new PdfPCell();
				cell1 = new PdfPCell();
				cell2 = new PdfPCell();
				cell.setBorder(PdfPCell.NO_BORDER);
				cell.setPadding(3);
				cell.addElement(p);
				table.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setColspan(4);
				table.addCell(cell);

				//
			p = new Paragraph(new Phrase(lineSpacing,basic.get("BuName").toString(), font));
				
				//p = new Paragraph(new Phrase("HHC - CORPORATE", font));
				
				
				p.setAlignment(Element.ALIGN_CENTER);
				cell = new PdfPCell();
				cell.setBorder(PdfPCell.NO_BORDER);
				cell.setPadding(3);
				cell.addElement(p);
				table.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setColspan(4);
				table.addCell(cell);

				font = new Font(base, 10f, Font.NORMAL);


				p = new Paragraph(new Phrase(lineSpacing,"Location : "+TaxSections.get(empid+"LOCATION"), font));
				p.setAlignment(Element.ALIGN_LEFT);
				cell = new PdfPCell();

				cell.setBorder(PdfPCell.NO_BORDER);

				// cell.setBorderWidth      (3f);         // sets border width to 3 units
				//cell.setBorderWidthLeft  (1f);
				// cell.setBorderWidthRight (1f);
				cell.setBorderWidthTop   (0.1f);

				cell.setBorderWidthBottom(0.1f);

				cell.setPadding(3);
				cell.addElement(p);
				table.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setColspan(4);
				//cell.setRowspan(2);
				table.addCell(cell);

				p = new Paragraph(new Phrase(lineSpacing,"Employee Code : "+empid, font));
				p.setAlignment(Element.ALIGN_LEFT);
				cell = new PdfPCell();
				cell.setBorder(PdfPCell.NO_BORDER);
				// cell.setBorderWidth      (3f);         // sets border width to 3 units
				//cell.setBorderWidthLeft  (1f);
				// cell.setBorderWidthRight (1f);
				// cell.setBorderWidthTop   (0f);
				// cell.setBorderWidthBottom(0.1f);
				// cell.setBackgroundColor(myColor);
				// cell.setBackgroundColor(BaseColor.#d5dce0);
				cell.setPadding(3);
				cell.addElement(p);
				table.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setColspan(3);
				//cell.setRowspan(2);
				table.addCell(cell);

				p = new Paragraph(new Phrase(lineSpacing, ft.format(dNow) , font));
				p.setAlignment(Element.ALIGN_RIGHT);
				cell = new PdfPCell();
				cell.setBorder(PdfPCell.NO_BORDER);
				// cell.setBorderWidth      (3f);         // sets border width to 3 units
				//cell.setBorderWidthLeft  (1f);
				// cell.setBorderWidthRight (1f);
				// cell.setBorderWidthTop   (0f);
				// cell.setBorderWidthBottom(0.1f);
				// cell.setBackgroundColor(myColor);
				// cell.setBackgroundColor(BaseColor.#d5dce0);
				cell.setPadding(3);
				cell.addElement(p);
				table.setHorizontalAlignment(Element.ALIGN_CENTER);
				//cell.setColspan(5);
				//cell.setRowspan(2);
				table.addCell(cell);

				p = new Paragraph(new Phrase(lineSpacing,"Employee Name : "+basic.get(empid+".callname"), font));
				p.setAlignment(Element.ALIGN_LEFT);
				cell = new PdfPCell();
				cell.setBorder(PdfPCell.NO_BORDER);
				// cell.setBorderWidth      (3f);         // sets border width to 3 units
				//cell.setBorderWidthLeft  (1f);
				// cell.setBorderWidthRight (1f);
				// cell.setBorderWidthTop   (0f);
				// cell.setBorderWidthBottom(0.1f);
				// cell.setBackgroundColor(myColor);
				// cell.setBackgroundColor(BaseColor.#d5dce0);
				cell.setPadding(3);
				cell.addElement(p);
				table.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setColspan(4);
				//cell.setRowspan(2);

				table.addCell(cell);


				p = new Paragraph(new Phrase(lineSpacing,"Headings", font1));
				p.setAlignment(Element.ALIGN_LEFT);
				cell = new PdfPCell();
				cell.setBorder(PdfPCell.NO_BORDER);
				// cell.setBorderWidth      (3f);         // sets border width to 3 units
				//cell.setBorderWidthLeft  (1f);
				// cell.setBorderWidthRight (1f);
				// cell.setBorderWidthTop   (0f);
				cell.setBorderWidthBottom(0.1f);
				// cell.setBackgroundColor(myColor);
				// cell.setBackgroundColor(BaseColor.#d5dce0);
				cell.setPadding(3);
				cell.addElement(p);
				//table.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setColspan(2);
				//cell.setRowspan(2);
				table.addCell(cell);


				p = new Paragraph(new Phrase(lineSpacing,"Amount(Cumulative)", font1));
				p.setAlignment(Element.ALIGN_RIGHT);
				cell = new PdfPCell();
				cell.setBorder(PdfPCell.NO_BORDER);
				// cell.setBorderWidth      (3f);         // sets border width to 3 units
				//cell.setBorderWidthLeft  (1f);
				// cell.setBorderWidthRight (1f);
				// cell.setBorderWidthTop   (0f);
				cell.setBorderWidthBottom(0.1f);
				// cell.setBackgroundColor(myColor);
				// cell.setBackgroundColor(BaseColor.#d5dce0);
				cell.setPadding(3);
				cell.addElement(p);
				//table.setHorizontalAlignment(Element.ALIGN_CENTER);
				//cell.setColspan(5);
				//cell.setRowspan(2);
				table.addCell(cell);

				p = new Paragraph(new Phrase(lineSpacing,"Amount(PA*)", font1));
				p.setAlignment(Element.ALIGN_RIGHT);
				cell1 = new PdfPCell();
				cell1.setBorder(PdfPCell.NO_BORDER);
				// cell.setBorderWidth      (3f);         // sets border width to 3 units
				//cell.setBorderWidthLeft  (1f);
				// cell.setBorderWidthRight (1f);
				// cell.setBorderWidthTop   (0f);
				cell1.setBorderWidthBottom(0.1f);
				// cell.setBackgroundColor(myColor);
				// cell.setBackgroundColor(BaseColor.#d5dce0);
				cell1.setPadding(3);
				cell1.addElement(p);
				//table.setHorizontalAlignment(Element.ALIGN_RIGHT);
				//cell.setColspan(5);
				//cell.setRowspan(2);
				table.addCell(cell1);

				// Break up Salary

				//	cell.setMinimumHeight(50f);
				//cell1.setMinimumHeight(50f);


				lineSpacing=6f;
				p = new Paragraph(new Phrase(lineSpacing,"1)Total Gross Salary Cumulative(Paid)/Annum(Paid & to be Paid)", font1));
				p.setAlignment(Element.ALIGN_LEFT);
				//p.setSpacingAfter(4);
				cell = new PdfPCell();
				cell.setBorder(PdfPCell.NO_BORDER);
				cell.setColspan(2);
				cell.setPadding(3);
				cell.addElement(p);
				table.addCell(cell);

				p = new Paragraph(new Phrase(lineSpacing, ""+formatter.format(Double.parseDouble(finalComponents.get(empid+"-gross").toString()))+"", font1));
				p.setAlignment(Element.ALIGN_RIGHT);
				//p.setSpacingAfter(4);
				cell1 = new PdfPCell();
				cell1.setBorder(PdfPCell.NO_BORDER);
				cell1.setPadding(3);
				cell1.addElement(p);
				table.addCell(cell1);

				p = new Paragraph(new Phrase(lineSpacing, ""+formatter.format(Double.parseDouble(finalComponents.get(empid+"-ANN_gross").toString()))+"", font1));
				p.setAlignment(Element.ALIGN_RIGHT);
				//p.setSpacingAfter(4);
				cell2 = new PdfPCell();
				cell2.setBorder(PdfPCell.NO_BORDER);
				cell2.setPadding(3);
				cell2.addElement(p);
				table.addCell(cell2);
				//************************************
				p = new Paragraph(new Phrase(lineSpacing,"2)Less:Allowances To The Extent Exempt Under Section 10", font));
				p.setAlignment(Element.ALIGN_LEFT);
				//p.setSpacingAfter(4);
				cell = new PdfPCell();
				cell.setBorder(PdfPCell.NO_BORDER);
				cell.setColspan(2);
				cell.setPadding(3);
				cell.addElement(p);
				table.addCell(cell);

				p = new Paragraph(new Phrase(lineSpacing, " ", font));
				p.setAlignment(Element.ALIGN_RIGHT);
				//p.setSpacingAfter(4);
				cell1 = new PdfPCell();
				cell1.setBorder(PdfPCell.NO_BORDER);
				cell1.setPadding(3);
				cell1.addElement(p);
				table.addCell(cell1);

				p = new Paragraph(new Phrase(lineSpacing, " ", font));
				p.setAlignment(Element.ALIGN_RIGHT);
				//p.setSpacingAfter(4);
				cell2 = new PdfPCell();
				cell2.setBorder(PdfPCell.NO_BORDER);
				cell2.setPadding(3);
				cell2.addElement(p);
				table.addCell(cell2);
				//****************** 
				p = new Paragraph(new Phrase(lineSpacing,"\t	Conveyance Allowance", font));
				p.setAlignment(Element.ALIGN_LEFT);
				//p.setSpacingAfter(4);
				cell = new PdfPCell();
				cell.setBorder(PdfPCell.NO_BORDER);
				cell.setColspan(2);
				cell.setPadding(3);
				cell.addElement(p);
				table.addCell(cell);

				p = new Paragraph(new Phrase(lineSpacing, ""+formatter.format(Double.parseDouble(TaxException.get(empid+"_CA_M").toString()))+"", font));
				p.setAlignment(Element.ALIGN_RIGHT);
				// p.setSpacingAfter(4);
				cell1 = new PdfPCell();
				cell1.setBorder(PdfPCell.NO_BORDER);
				cell1.setPadding(3);
				cell1.addElement(p);
				table.addCell(cell1);

				p = new Paragraph(new Phrase(lineSpacing, ""+formatter.format(Double.parseDouble(TaxException.get(empid+"_CA_M_y").toString()))+"", font));
				p.setAlignment(Element.ALIGN_RIGHT);
				//p.setSpacingAfter(4);
				cell2 = new PdfPCell();
				cell2.setBorder(PdfPCell.NO_BORDER);
				cell2.setPadding(3);
				cell2.addElement(p);
				table.addCell(cell2);
				//*****************************************************
				p = new Paragraph(new Phrase(lineSpacing,"\t	HRA", font));
				p.setAlignment(Element.ALIGN_LEFT);
				//p.setSpacingAfter(4);
				cell = new PdfPCell();
				cell.setBorder(PdfPCell.NO_BORDER);
				cell.setColspan(2);
				cell.setPadding(3);
				cell.addElement(p);
				table.addCell(cell);

				p = new Paragraph(new Phrase(lineSpacing, ""+formatter.format(Double.parseDouble(TaxException.get(empid+".Basi40_RE").toString()))+"", font));
				p.setAlignment(Element.ALIGN_RIGHT);
				// p.setSpacingAfter(4);
				cell1 = new PdfPCell();
				cell1.setBorder(PdfPCell.NO_BORDER);
				cell1.setPadding(3);
				cell1.addElement(p);
				table.addCell(cell1);

				p = new Paragraph(new Phrase(lineSpacing, ""+formatter.format(Double.parseDouble(TaxException.get(empid+".Basi40_RE_y").toString()))+"", font));
				p.setAlignment(Element.ALIGN_RIGHT);
				//p.setSpacingAfter(4);
				cell2 = new PdfPCell();
				cell2.setBorder(PdfPCell.NO_BORDER);
				cell2.setPadding(3);
				cell2.addElement(p);
				table.addCell(cell2);
				//*************************************************	
				p = new Paragraph(new Phrase(lineSpacing,"\t	EDUALLOW", font));
				p.setAlignment(Element.ALIGN_LEFT);
				//p.setSpacingAfter(4);
				cell = new PdfPCell();
				cell.setBorder(PdfPCell.NO_BORDER);
				cell.setColspan(2);
				cell.setPadding(3);
				cell.addElement(p);
				table.addCell(cell);

				p = new Paragraph(new Phrase(lineSpacing, ""+formatter.format(Double.parseDouble(TaxException.get(empid+".ChildEdu").toString()))+"", font));
				p.setAlignment(Element.ALIGN_RIGHT);
				// p.setSpacingAfter(4);
				cell1 = new PdfPCell();
				cell1.setBorder(PdfPCell.NO_BORDER);
				cell1.setPadding(3);
				cell1.addElement(p);
				table.addCell(cell1);

				p = new Paragraph(new Phrase(lineSpacing, ""+formatter.format(Double.parseDouble(TaxException.get(empid+".ChildEdu_y").toString()))+"", font));
				p.setAlignment(Element.ALIGN_RIGHT);
				//p.setSpacingAfter(4);
				cell2 = new PdfPCell();
				cell2.setBorder(PdfPCell.NO_BORDER);
				cell2.setPadding(3);
				cell2.addElement(p);
				table.addCell(cell2);

				//************************************************	
				p = new Paragraph(new Phrase(lineSpacing,"\t	MEDALLOW", font));
				p.setAlignment(Element.ALIGN_LEFT);
				//p.setSpacingAfter(4);
				cell = new PdfPCell();
				cell.setBorder(PdfPCell.NO_BORDER);
				cell.setColspan(2);
				cell.setPadding(3);
				cell.addElement(p);
				table.addCell(cell);

				p = new Paragraph(new Phrase(lineSpacing, ""+formatter.format(Double.parseDouble(TaxException.get(empid+".Medical").toString()))+"", font));
				p.setAlignment(Element.ALIGN_RIGHT);
				// p.setSpacingAfter(4);
				cell1 = new PdfPCell();
				cell1.setBorder(PdfPCell.NO_BORDER);
				cell1.setPadding(3);
				cell1.addElement(p);
				table.addCell(cell1);

				p = new Paragraph(new Phrase(lineSpacing, ""+formatter.format(Double.parseDouble(TaxException.get(empid+".Medical_y").toString()))+"", font));
				p.setAlignment(Element.ALIGN_RIGHT);
				//p.setSpacingAfter(4);
				cell2 = new PdfPCell();
				cell2.setBorder(PdfPCell.NO_BORDER);
				cell2.setPadding(3);
				cell2.addElement(p);
				table.addCell(cell2);


				//********************************************
				p = new Paragraph(new Phrase(lineSpacing,"\t	LTA (Declared)", font));
				p.setAlignment(Element.ALIGN_LEFT);
				//p.setSpacingAfter(4);
				cell = new PdfPCell();
				cell.setBorder(PdfPCell.NO_BORDER);
				cell.setColspan(2);
				cell.setPadding(3);
				cell.addElement(p);
				table.addCell(cell);

				p = new Paragraph(new Phrase(lineSpacing, ""+formatter.format(Double.parseDouble(TaxSections.get(empid+"_LTA_P").toString()))+"", font));
				p.setAlignment(Element.ALIGN_RIGHT);
				// p.setSpacingAfter(4);
				cell1 = new PdfPCell();
				cell1.setBorder(PdfPCell.NO_BORDER);
				cell1.setPadding(3);
				cell1.addElement(p);
				table.addCell(cell1);

				p = new Paragraph(new Phrase(lineSpacing, ""+formatter.format(Double.parseDouble(TaxSections.get(empid+"_LTA_P").toString()))+"", font));
				p.setAlignment(Element.ALIGN_RIGHT);
				//p.setSpacingAfter(4);
				cell2 = new PdfPCell();
				cell2.setBorder(PdfPCell.NO_BORDER);
				cell2.setPadding(3);
				cell2.addElement(p);
				table.addCell(cell2);

				//***************************************   
				//********************************************
				p = new Paragraph(new Phrase(lineSpacing,"\tTotal Exemption Amount", font));
				p.setAlignment(Element.ALIGN_LEFT);
				//p.setSpacingAfter(4);
				cell = new PdfPCell();
				cell.setBorder(PdfPCell.NO_BORDER);
				cell.setColspan(2);
				cell.setPadding(3);
				cell.addElement(p);
				table.addCell(cell);

				p = new Paragraph(new Phrase(lineSpacing, ""+formatter.format(Double.parseDouble(TaxException.get(empid+".ExmpAmount").toString()))+"", font));
				p.setAlignment(Element.ALIGN_RIGHT);
				// p.setSpacingAfter(4);
				cell1 = new PdfPCell();
				cell1.setBorder(PdfPCell.NO_BORDER);
				cell1.setPadding(3);
				cell1.addElement(p);
				table.addCell(cell1);

				p = new Paragraph(new Phrase(lineSpacing, ""+formatter.format(Double.parseDouble(TaxException.get(empid+".ANN_ExmpAmount").toString()))+"", font));
				p.setAlignment(Element.ALIGN_RIGHT);
				//p.setSpacingAfter(4);
				cell2 = new PdfPCell();
				cell2.setBorder(PdfPCell.NO_BORDER);
				cell2.setPadding(3);
				cell2.addElement(p);
				table.addCell(cell2);

				//***************************************
				//********************************************
				p = new Paragraph(new Phrase(lineSpacing,"3) Balance (1-2) ", font));
				p.setAlignment(Element.ALIGN_LEFT);
				//p.setSpacingAfter(4);
				cell = new PdfPCell();
				cell.setBorder(PdfPCell.NO_BORDER);
				cell.setColspan(2);
				cell.setPadding(3);
				cell.addElement(p);
				table.addCell(cell);

				p = new Paragraph(new Phrase(lineSpacing, ""+formatter.format(Double.parseDouble(finalComponents.get(empid+"-gross_ExAmt").toString()))+"", font));
				p.setAlignment(Element.ALIGN_RIGHT);
				// p.setSpacingAfter(4);
				cell1 = new PdfPCell();
				cell1.setBorder(PdfPCell.NO_BORDER);
				cell1.setPadding(3);
				cell1.addElement(p);
				table.addCell(cell1);

				p = new Paragraph(new Phrase(lineSpacing, ""+formatter.format(Double.parseDouble(finalComponents.get(empid+"-ANN_gross_ExAmt").toString()))+"", font));
				p.setAlignment(Element.ALIGN_RIGHT);
				//p.setSpacingAfter(4);
				cell2 = new PdfPCell();
				cell2.setBorder(PdfPCell.NO_BORDER);
				cell2.setPadding(3);
				cell2.addElement(p);
				table.addCell(cell2);

				//***************************************	

				//********************************************
				p = new Paragraph(new Phrase(lineSpacing,"4)Deductions:", font));
				p.setAlignment(Element.ALIGN_LEFT);
				//p.setSpacingAfter(4);
				cell = new PdfPCell();
				cell.setBorder(PdfPCell.NO_BORDER);
				cell.setColspan(2);
				cell.setPadding(3);
				cell.addElement(p);
				table.addCell(cell);

				p = new Paragraph(new Phrase(lineSpacing, "  "   , font));
				p.setAlignment(Element.ALIGN_RIGHT);
				// p.setSpacingAfter(4);
				cell1 = new PdfPCell();
				cell1.setBorder(PdfPCell.NO_BORDER);
				cell1.setPadding(3);
				cell1.addElement(p);
				table.addCell(cell1);

				p = new Paragraph(new Phrase(lineSpacing, "  " , font));
				p.setAlignment(Element.ALIGN_RIGHT);
				//p.setSpacingAfter(4);
				cell2 = new PdfPCell();
				cell2.setBorder(PdfPCell.NO_BORDER);
				cell2.setPadding(3);
				cell2.addElement(p);
				table.addCell(cell2);
				//***************************************

				//********************************************
				p = new Paragraph(new Phrase(lineSpacing,"\t Professional Tax:", font));
				p.setAlignment(Element.ALIGN_LEFT);
				//p.setSpacingAfter(4);
				cell = new PdfPCell();
				cell.setBorder(PdfPCell.NO_BORDER);
				cell.setColspan(2);
				cell.setPadding(3);
				cell.addElement(p);
				table.addCell(cell);

				p = new Paragraph(new Phrase(lineSpacing, " "+formatter.format(Double.parseDouble(finalComponents.get(empid+"-Pt").toString()))+" "   , font));
				p.setAlignment(Element.ALIGN_RIGHT);
				// p.setSpacingAfter(4);
				cell1 = new PdfPCell();
				cell1.setBorder(PdfPCell.NO_BORDER);
				cell1.setPadding(3);
				cell1.addElement(p);
				table.addCell(cell1);

				p = new Paragraph(new Phrase(lineSpacing, ""+formatter.format(Double.parseDouble(finalComponents.get(empid+"-ANN_Pt").toString()))+" " , font));
				p.setAlignment(Element.ALIGN_RIGHT);
				//p.setSpacingAfter(4);
				cell2 = new PdfPCell();
				cell2.setBorder(PdfPCell.NO_BORDER);
				cell2.setPadding(3);
				cell2.addElement(p);
				table.addCell(cell2);
				//***************************************
				//********************************************
				p = new Paragraph(new Phrase(lineSpacing,"5)Aggregate of(4) above", font));
				p.setAlignment(Element.ALIGN_LEFT);
				//p.setSpacingAfter(4);
				cell = new PdfPCell();
				cell.setBorder(PdfPCell.NO_BORDER);
				cell.setColspan(2);
				cell.setPadding(3);
				cell.addElement(p);
				table.addCell(cell);

				p = new Paragraph(new Phrase(lineSpacing, " "+formatter.format(Double.parseDouble(finalComponents.get(empid+"-Pt").toString()))+" "   , font));
				p.setAlignment(Element.ALIGN_RIGHT);
				// p.setSpacingAfter(4);
				cell1 = new PdfPCell();
				cell1.setBorder(PdfPCell.NO_BORDER);
				cell1.setPadding(3);
				cell1.addElement(p);
				table.addCell(cell1);

				p = new Paragraph(new Phrase(lineSpacing, ""+formatter.format(Double.parseDouble(finalComponents.get(empid+"-ANN_Pt").toString()))+" " , font));
				p.setAlignment(Element.ALIGN_RIGHT);
				//p.setSpacingAfter(4);
				cell2 = new PdfPCell();
				cell2.setBorder(PdfPCell.NO_BORDER);
				cell2.setPadding(3);
				cell2.addElement(p);
				table.addCell(cell2);
				//***************************************	

				//********************************************
				p = new Paragraph(new Phrase(lineSpacing,"6)Income Chargeable Under The Head Salaries", font));
				p.setAlignment(Element.ALIGN_LEFT);
				//p.setSpacingAfter(4);
				cell = new PdfPCell();
				cell.setBorder(PdfPCell.NO_BORDER);
				cell.setColspan(2);
				cell.setPadding(3);
				cell.addElement(p);
				table.addCell(cell);

				p = new Paragraph(new Phrase(lineSpacing, " "+formatter.format(Double.parseDouble(TaxException.get(empid+".FinalIncome_B").toString()))+" "   , font));
				p.setAlignment(Element.ALIGN_RIGHT);
				// p.setSpacingAfter(4);
				cell1 = new PdfPCell();
				cell1.setBorder(PdfPCell.NO_BORDER);
				cell1.setPadding(3);
				cell1.addElement(p);
				table.addCell(cell1);

				p = new Paragraph(new Phrase(lineSpacing, ""+formatter.format(Double.parseDouble(TaxException.get(empid+".ANN_FinalIncome_B").toString()))+"" , font));
				p.setAlignment(Element.ALIGN_RIGHT);
				//p.setSpacingAfter(4);
				cell2 = new PdfPCell();
				cell2.setBorder(PdfPCell.NO_BORDER);
				cell2.setPadding(3);
				cell2.addElement(p);
				table.addCell(cell2);
				//***************************************
				//********************************************
				p = new Paragraph(new Phrase(lineSpacing,"7)Income From House Property(Eligible loss set off 2lac)", font));
				p.setAlignment(Element.ALIGN_LEFT);
				//p.setSpacingAfter(4);
				cell = new PdfPCell();
				cell.setBorder(PdfPCell.NO_BORDER);
				cell.setColspan(2);
				cell.setPadding(3);
				cell.addElement(p);
				table.addCell(cell);

				p = new Paragraph(new Phrase(lineSpacing, "  "   , font));
				p.setAlignment(Element.ALIGN_RIGHT);
				// p.setSpacingAfter(4);
				cell1 = new PdfPCell();
				cell1.setBorder(PdfPCell.NO_BORDER);
				cell1.setPadding(3);
				cell1.addElement(p);
				table.addCell(cell1);

				p = new Paragraph(new Phrase(lineSpacing, " " , font));
				p.setAlignment(Element.ALIGN_RIGHT);
				//p.setSpacingAfter(4);
				cell2 = new PdfPCell();
				cell2.setBorder(PdfPCell.NO_BORDER);
				cell2.setPadding(3);
				cell2.addElement(p);
				table.addCell(cell2);
				//***************************************	


				//********************************************
				p = new Paragraph(new Phrase(lineSpacing,"\t\t\t i)Income From Let-Out Property (Eligible) ", font));
				p.setAlignment(Element.ALIGN_LEFT);
				//p.setSpacingAfter(4);
				cell = new PdfPCell();
				cell.setBorder(PdfPCell.NO_BORDER);
				cell.setColspan(2);
				cell.setPadding(3);
				cell.addElement(p);
				table.addCell(cell);

				p = new Paragraph(new Phrase(lineSpacing, " "+formatter.format(Double.parseDouble(TaxSections.get(empid+"INHR").toString()))+" "   , font));
				p.setAlignment(Element.ALIGN_RIGHT);
				// p.setSpacingAfter(4);
				cell1 = new PdfPCell();
				cell1.setBorder(PdfPCell.NO_BORDER);
				cell1.setPadding(3);
				cell1.addElement(p);
				table.addCell(cell1);

				p = new Paragraph(new Phrase(lineSpacing, "  "+formatter.format(Double.parseDouble(TaxSections.get(empid+"INHR").toString()))+"" , font));
				p.setAlignment(Element.ALIGN_RIGHT);
				//p.setSpacingAfter(4);
				cell2 = new PdfPCell();
				cell2.setBorder(PdfPCell.NO_BORDER);
				cell2.setPadding(3);
				cell2.addElement(p);
				table.addCell(cell2);
				
				
				p = new Paragraph(new Phrase(lineSpacing,"\t\t\t ii)Less:Housing Loan Interest Deduction (Eligible)", font));
				p.setAlignment(Element.ALIGN_LEFT);
				//p.setSpacingAfter(4);
				cell = new PdfPCell();
				cell.setBorder(PdfPCell.NO_BORDER);
				cell.setColspan(2);
				cell.setPadding(3);
				cell.addElement(p);
				table.addCell(cell);

				p = new Paragraph(new Phrase(lineSpacing, ""+formatter.format(Double.parseDouble(TaxSections.get(empid+"_RENTINTEREST_E").toString()))+""   , font));
				p.setAlignment(Element.ALIGN_RIGHT);
				// p.setSpacingAfter(4);
				cell1 = new PdfPCell();
				cell1.setBorder(PdfPCell.NO_BORDER);
				cell1.setPadding(3);
				cell1.addElement(p);
				table.addCell(cell1);

				p = new Paragraph(new Phrase(lineSpacing, ""+formatter.format(Double.parseDouble(TaxSections.get(empid+"_RENTINTEREST_E").toString()))+"" , font));
				p.setAlignment(Element.ALIGN_RIGHT);
				//p.setSpacingAfter(4);
				cell2 = new PdfPCell();
				cell2.setBorder(PdfPCell.NO_BORDER);
				cell2.setPadding(3);
				cell2.addElement(p);
				table.addCell(cell2);
				//***************************************	
				//********************************************
				p = new Paragraph(new Phrase(lineSpacing," 8) Income From Other Source", font));
				p.setAlignment(Element.ALIGN_LEFT);
				//p.setSpacingAfter(4);
				cell = new PdfPCell();
				cell.setBorder(PdfPCell.NO_BORDER);
				cell.setColspan(2);
				cell.setPadding(3);
				cell.addElement(p);
				table.addCell(cell);

				p = new Paragraph(new Phrase(lineSpacing, ""+formatter.format(Double.parseDouble(TaxSections.get(empid+"_OTHERINCOME").toString()))+""   , font));
				p.setAlignment(Element.ALIGN_RIGHT);
				// p.setSpacingAfter(4);
				cell1 = new PdfPCell();
				cell1.setBorder(PdfPCell.NO_BORDER);
				cell1.setPadding(3);
				cell1.addElement(p);
				table.addCell(cell1);

				p = new Paragraph(new Phrase(lineSpacing, ""+formatter.format(Double.parseDouble(TaxSections.get(empid+"_OTHERINCOME").toString()))+" " , font));
				p.setAlignment(Element.ALIGN_RIGHT);
				//p.setSpacingAfter(4);
				cell2 = new PdfPCell();
				cell2.setBorder(PdfPCell.NO_BORDER);
				cell2.setPadding(3);
				cell2.addElement(p);
				table.addCell(cell2);
				//***************************************	
				//********************************************
				p = new Paragraph(new Phrase(lineSpacing,"9)Gross Total Income(6+7+8)", font));
				p.setAlignment(Element.ALIGN_LEFT);
				//p.setSpacingAfter(4);
				cell = new PdfPCell();
				cell.setBorder(PdfPCell.NO_BORDER);
				cell.setColspan(2);
				cell.setPadding(3);
				cell.addElement(p);
				table.addCell(cell);

				p = new Paragraph(new Phrase(lineSpacing, ""+formatter.format(Double.parseDouble(TaxException.get(empid+".CUmm_LEXAMT").toString()))+""   , font));
				p.setAlignment(Element.ALIGN_RIGHT);
				// p.setSpacingAfter(4);
				cell1 = new PdfPCell();
				cell1.setBorder(PdfPCell.NO_BORDER);
				cell1.setPadding(3);
				cell1.addElement(p);
				table.addCell(cell1);

				p = new Paragraph(new Phrase(lineSpacing, ""+formatter.format(Double.parseDouble(TaxException.get(empid+".ANN_LEXAMT").toString()))+" " , font));
				p.setAlignment(Element.ALIGN_RIGHT);
				//p.setSpacingAfter(4);
				cell2 = new PdfPCell();
				cell2.setBorder(PdfPCell.NO_BORDER);
				cell2.setPadding(3);
				cell2.addElement(p);
				table.addCell(cell2);
				//***************************************
				//********************************************
				
				//***************************************	

				//********************************************
				p = new Paragraph(new Phrase(lineSpacing,"10)Deduction Under Chapter VIA (80C to 80U)", font));
				p.setAlignment(Element.ALIGN_LEFT);
				//p.setSpacingAfter(4);
				cell = new PdfPCell();
				cell.setBorder(PdfPCell.NO_BORDER);
				cell.setColspan(2);
				cell.setPadding(3);
				cell.addElement(p);
				table.addCell(cell);

				p = new Paragraph(new Phrase(lineSpacing, " "   , font));
				p.setAlignment(Element.ALIGN_RIGHT);
				// p.setSpacingAfter(4);
				cell1 = new PdfPCell();
				cell1.setBorder(PdfPCell.NO_BORDER);
				cell1.setPadding(3);
				cell1.addElement(p);
				table.addCell(cell1);

				p = new Paragraph(new Phrase(lineSpacing, " " , font));
				p.setAlignment(Element.ALIGN_RIGHT);
				//p.setSpacingAfter(4);
				cell2 = new PdfPCell();
				cell2.setBorder(PdfPCell.NO_BORDER);
				cell2.setPadding(3);
				cell2.addElement(p);
				table.addCell(cell2);
				//***************************************
				//********************************************
				p = new Paragraph(new Phrase(lineSpacing,"\t\t\t Deduction Amount Under 80C,80CCC,80CCD", font));
				p.setAlignment(Element.ALIGN_LEFT);
				//p.setSpacingAfter(4);
				cell = new PdfPCell();
				cell.setBorder(PdfPCell.NO_BORDER);
				cell.setColspan(2);
				cell.setPadding(3);
				cell.addElement(p);
				table.addCell(cell);

				p = new Paragraph(new Phrase(lineSpacing, " "   , font));
				p.setAlignment(Element.ALIGN_RIGHT);
				// p.setSpacingAfter(4);
				cell1 = new PdfPCell();
				cell1.setBorder(PdfPCell.NO_BORDER);
				cell1.setPadding(3);
				cell1.addElement(p);
				table.addCell(cell1);

				p = new Paragraph(new Phrase(lineSpacing, " " , font));
				p.setAlignment(Element.ALIGN_RIGHT);
				//p.setSpacingAfter(4);
				cell2 = new PdfPCell();
				cell2.setBorder(PdfPCell.NO_BORDER);
				cell2.setPadding(3);
				cell2.addElement(p);
				table.addCell(cell2);
				//***************************************
				//********************************************
				p = new Paragraph(new Phrase(lineSpacing,"\t\t\t 1)PF", font));
				p.setAlignment(Element.ALIGN_LEFT);
				//p.setSpacingAfter(4);
				cell = new PdfPCell();
				cell.setBorder(PdfPCell.NO_BORDER);
				cell.setColspan(2);
				cell.setPadding(3);
				cell.addElement(p);
				table.addCell(cell);

				p = new Paragraph(new Phrase(lineSpacing, ""+formatter.format(Double.parseDouble(finalComponents.get(empid+"-PF").toString()))+" "   , font));
				p.setAlignment(Element.ALIGN_RIGHT);
				// p.setSpacingAfter(4);
				cell1 = new PdfPCell();
				cell1.setBorder(PdfPCell.NO_BORDER);
				cell1.setPadding(3);
				cell1.addElement(p);
				table.addCell(cell1);

				p = new Paragraph(new Phrase(lineSpacing, " "+formatter.format(Double.parseDouble(finalComponents.get(empid+"_ANN-PF").toString()))+"" , font));
				p.setAlignment(Element.ALIGN_RIGHT);
				//p.setSpacingAfter(4);
				cell2 = new PdfPCell();
				cell2.setBorder(PdfPCell.NO_BORDER);
				cell2.setPadding(3);
				cell2.addElement(p);
				table.addCell(cell2);
				//***************************************

				//********************************************
				p = new Paragraph(new Phrase(lineSpacing,"\t\t\t 2)Section 80C (80C Others)", font));
				p.setAlignment(Element.ALIGN_LEFT);
				//p.setSpacingAfter(4);
				cell = new PdfPCell();
				cell.setBorder(PdfPCell.NO_BORDER);
				cell.setColspan(2);
				cell.setPadding(3);
				cell.addElement(p);
				table.addCell(cell);

				p = new Paragraph(new Phrase(lineSpacing, ""+formatter.format(Double.parseDouble(TaxSections.get(empid+"80C").toString()))+""   , font));
				p.setAlignment(Element.ALIGN_RIGHT);
				// p.setSpacingAfter(4);
				cell1 = new PdfPCell();
				cell1.setBorder(PdfPCell.NO_BORDER);
				cell1.setPadding(3);
				cell1.addElement(p);
				table.addCell(cell1);

				p = new Paragraph(new Phrase(lineSpacing, ""+formatter.format(Double.parseDouble(TaxSections.get(empid+"80C").toString()))+"" , font));
				p.setAlignment(Element.ALIGN_RIGHT);
				//p.setSpacingAfter(4);
				cell2 = new PdfPCell();
				cell2.setBorder(PdfPCell.NO_BORDER);
				cell2.setPadding(3);
				cell2.addElement(p);
				table.addCell(cell2);
				//***************************************
				//********************************************
				p = new Paragraph(new Phrase(lineSpacing,"\t\t\t 3)Contribution to Pension Scheme of Central Govt- Section 80 CCD", font));
				p.setAlignment(Element.ALIGN_LEFT);
				//p.setSpacingAfter(4);
				cell = new PdfPCell();
				cell.setBorder(PdfPCell.NO_BORDER);
				cell.setColspan(2);
				cell.setPadding(3);
				cell.addElement(p);
				table.addCell(cell);

				p = new Paragraph(new Phrase(lineSpacing, ""+formatter.format(Double.parseDouble(TaxSections.get(empid+"NPS").toString()))+""   , font));
				p.setAlignment(Element.ALIGN_RIGHT);
				// p.setSpacingAfter(4);
				cell1 = new PdfPCell();
				cell1.setBorder(PdfPCell.NO_BORDER);
				cell1.setPadding(3);
				cell1.addElement(p);
				table.addCell(cell1);

				p = new Paragraph(new Phrase(lineSpacing, ""+formatter.format(Double.parseDouble(TaxSections.get(empid+"NPS").toString()))+"" , font));
				p.setAlignment(Element.ALIGN_RIGHT);
				//p.setSpacingAfter(4);
				cell2 = new PdfPCell();
				cell2.setBorder(PdfPCell.NO_BORDER);
				cell2.setPadding(3);
				cell2.addElement(p);
				table.addCell(cell2);
				//***************************************
				//********************************************
				p = new Paragraph(new Phrase(lineSpacing,"11) Qualifying Amount for 80C,80CCC,80CCD", font));
				p.setAlignment(Element.ALIGN_LEFT);
				//p.setSpacingAfter(4);
				cell = new PdfPCell();
				cell.setBorder(PdfPCell.NO_BORDER);
				cell.setColspan(2);
				cell.setPadding(3);
				cell.addElement(p);
				table.addCell(cell);

				p = new Paragraph(new Phrase(lineSpacing, ""+formatter.format(Double.parseDouble(TaxSections.get(empid+"_80C_E").toString()))+""   , font));
				p.setAlignment(Element.ALIGN_RIGHT);
				// p.setSpacingAfter(4);
				cell1 = new PdfPCell();
				cell1.setBorder(PdfPCell.NO_BORDER);
				cell1.setPadding(3);
				cell1.addElement(p);
				table.addCell(cell1);

				p = new Paragraph(new Phrase(lineSpacing, ""+formatter.format(Double.parseDouble(TaxSections.get(empid+"_80C_E_y").toString()))+"" , font));
				p.setAlignment(Element.ALIGN_RIGHT);
				//p.setSpacingAfter(4);
				cell2 = new PdfPCell();
				cell2.setBorder(PdfPCell.NO_BORDER);
				cell2.setPadding(3);
				cell2.addElement(p);
				table.addCell(cell2);
				//***************************************	

				//********************************************
				p = new Paragraph(new Phrase(lineSpacing,"\t\t\t 1)DONATION TO SPECIFIED INSTITUTIONS AND ORGANIZATIONS", font));
				p.setAlignment(Element.ALIGN_LEFT);
				//p.setSpacingAfter(4);
				cell = new PdfPCell();
				cell.setBorder(PdfPCell.NO_BORDER);
				cell.setColspan(2);
				cell.setPadding(3);
				cell.addElement(p);
				table.addCell(cell);

				p = new Paragraph(new Phrase(lineSpacing, ""+formatter.format(Double.parseDouble(TaxSections.get(empid+"_S80GAMT").toString()))+""   , font));
				p.setAlignment(Element.ALIGN_RIGHT);
				// p.setSpacingAfter(4);
				cell1 = new PdfPCell();
				cell1.setBorder(PdfPCell.NO_BORDER);
				cell1.setPadding(3);
				cell1.addElement(p);
				table.addCell(cell1);

				p = new Paragraph(new Phrase(lineSpacing, ""+formatter.format(Double.parseDouble(TaxSections.get(empid+"_S80GAMT").toString()))+"" , font));
				p.setAlignment(Element.ALIGN_RIGHT);
				//p.setSpacingAfter(4);
				cell2 = new PdfPCell();
				cell2.setBorder(PdfPCell.NO_BORDER);
				cell2.setPadding(3);
				cell2.addElement(p);
				table.addCell(cell2);
				//***************************************	

				//********************************************
				p = new Paragraph(new Phrase(lineSpacing,"\t\t\t  Qualifying Amount under Section 80G", font));
				p.setAlignment(Element.ALIGN_LEFT);
				//p.setSpacingAfter(4);
				cell = new PdfPCell();
				cell.setBorder(PdfPCell.NO_BORDER);
				cell.setColspan(2);
				cell.setPadding(3);
				cell.addElement(p);
				table.addCell(cell);

				p = new Paragraph(new Phrase(lineSpacing, ""+formatter.format(Double.parseDouble(TaxSections.get(empid+"S_80G_E").toString()))+""   , font));
				p.setAlignment(Element.ALIGN_RIGHT);
				// p.setSpacingAfter(4);
				cell1 = new PdfPCell();
				cell1.setBorder(PdfPCell.NO_BORDER);
				cell1.setPadding(3);
				cell1.addElement(p);
				table.addCell(cell1);

				p = new Paragraph(new Phrase(lineSpacing, ""+formatter.format(Double.parseDouble(TaxSections.get(empid+"S_80G_E").toString()))+"" , font));
				p.setAlignment(Element.ALIGN_RIGHT);
				//p.setSpacingAfter(4);
				cell2 = new PdfPCell();
				cell2.setBorder(PdfPCell.NO_BORDER);
				cell2.setPadding(3);
				cell2.addElement(p);
				table.addCell(cell2);
				//***************************************
				//********************************************
				
				// Comment For Medical Re embresement
				
				/*p = new Paragraph(new Phrase(lineSpacing,"\t\t\t 2)MEDICAL REIMBURSEMENT", font));
				p.setAlignment(Element.ALIGN_LEFT);
				//p.setSpacingAfter(4);
				cell = new PdfPCell();
				cell.setBorder(PdfPCell.NO_BORDER);
				cell.setColspan(2);
				cell.setPadding(3);
				cell.addElement(p);
				table.addCell(cell);

				
				p = new Paragraph(new Phrase(lineSpacing, ""+formatter.format(Double.parseDouble(TaxException.get(empid+".Medical").toString()))+""   , font));
				p.setAlignment(Element.ALIGN_RIGHT);
				// p.setSpacingAfter(4);
				cell1 = new PdfPCell();
				cell1.setBorder(PdfPCell.NO_BORDER);
				cell1.setPadding(3);
				cell1.addElement(p);
				table.addCell(cell1);

				p = new Paragraph(new Phrase(lineSpacing, ""+formatter.format(Double.parseDouble(TaxException.get(empid+".Medical").toString()))+"" , font));
				p.setAlignment(Element.ALIGN_RIGHT);
				//p.setSpacingAfter(4);
				cell2 = new PdfPCell();
				cell2.setBorder(PdfPCell.NO_BORDER);
				cell2.setPadding(3);
				cell2.addElement(p);
				table.addCell(cell2);
				*/
				
				//***************************************	
				//********************************************
				p = new Paragraph(new Phrase(lineSpacing,"\t\t\t 2)Under Section (80D to 80TTA) Declaration", font));
				p.setAlignment(Element.ALIGN_LEFT);
				//p.setSpacingAfter(4);
				cell = new PdfPCell();
				cell.setBorder(PdfPCell.NO_BORDER);
				cell.setColspan(2);
				cell.setPadding(3);
				cell.addElement(p);
				table.addCell(cell);

				p = new Paragraph(new Phrase(lineSpacing, ""+formatter.format(Double.parseDouble(TaxSections.get(empid+"80D").toString()))+""   , font));
				p.setAlignment(Element.ALIGN_RIGHT);
				// p.setSpacingAfter(4);
				cell1 = new PdfPCell();
				cell1.setBorder(PdfPCell.NO_BORDER);
				cell1.setPadding(3);
				cell1.addElement(p);
				table.addCell(cell1);

				p = new Paragraph(new Phrase(lineSpacing, ""+formatter.format(Double.parseDouble(TaxSections.get(empid+"80D").toString()))+"" , font));
				p.setAlignment(Element.ALIGN_RIGHT);
				//p.setSpacingAfter(4);
				cell2 = new PdfPCell();
				cell2.setBorder(PdfPCell.NO_BORDER);
				cell2.setPadding(3);
				cell2.addElement(p);
				table.addCell(cell2);
				//***************************************
				//********************************************
				p = new Paragraph(new Phrase(lineSpacing,"12)Qualifying Amount for Section (80D to 80TTA)", font));
				p.setAlignment(Element.ALIGN_LEFT);
				//p.setSpacingAfter(4);
				cell = new PdfPCell();
				cell.setBorder(PdfPCell.NO_BORDER);
				cell.setColspan(2);
				cell.setPadding(3);
				cell.addElement(p);
				table.addCell(cell);

				p = new Paragraph(new Phrase(lineSpacing, ""+formatter.format(Double.parseDouble(TaxSections.get(empid+"_80D_E").toString()))+""   , font));
				p.setAlignment(Element.ALIGN_RIGHT);
				// p.setSpacingAfter(4);
				cell1 = new PdfPCell();
				cell1.setBorder(PdfPCell.NO_BORDER);
				cell1.setPadding(3);
				cell1.addElement(p);
				table.addCell(cell1);

				p = new Paragraph(new Phrase(lineSpacing, ""+formatter.format(Double.parseDouble(TaxSections.get(empid+"_80D_E").toString()))+"" , font));
				p.setAlignment(Element.ALIGN_RIGHT);
				//p.setSpacingAfter(4);
				cell2 = new PdfPCell();
				cell2.setBorder(PdfPCell.NO_BORDER);
				cell2.setPadding(3);
				cell2.addElement(p);
				table.addCell(cell2);
				//***************************************
				//********************************************
				p = new Paragraph(new Phrase(lineSpacing,"13)Taxable income ", font));
				p.setAlignment(Element.ALIGN_LEFT);
				//p.setSpacingAfter(4);
				cell = new PdfPCell();
				cell.setBorder(PdfPCell.NO_BORDER);
				cell.setColspan(2);
				cell.setPadding(3);
				cell.addElement(p);
				table.addCell(cell);

				p = new Paragraph(new Phrase(lineSpacing, ""+formatter.format(Double.parseDouble(TaxSections.get(empid+"TxamT").toString()))+""   , font));
				p.setAlignment(Element.ALIGN_RIGHT);
				// p.setSpacingAfter(4);
				cell1 = new PdfPCell();
				cell1.setBorder(PdfPCell.NO_BORDER);
				cell1.setPadding(3);
				cell1.addElement(p);
				table.addCell(cell1);

				p = new Paragraph(new Phrase(lineSpacing, ""+formatter.format(Double.parseDouble(TaxSections.get(empid+"ANN_TxamT").toString()))+"" , font));
				p.setAlignment(Element.ALIGN_RIGHT);
				//p.setSpacingAfter(4);
				cell2 = new PdfPCell();
				cell2.setBorder(PdfPCell.NO_BORDER);
				cell2.setPadding(3);
				cell2.addElement(p);
				table.addCell(cell2);
				//***************************************
				
				  // ADDED BY VENU ON 28-12-2016
				p = new Paragraph(new Phrase(lineSpacing,"14)Tax On taxable Income", font));
				p.setAlignment(Element.ALIGN_LEFT);
				//p.setSpacingAfter(4);
				cell = new PdfPCell();
				cell.setBorder(PdfPCell.NO_BORDER);
				cell.setColspan(2);
				cell.setPadding(3);
				cell.addElement(p);
				table.addCell(cell);

				p = new Paragraph(new Phrase(lineSpacing, ""+formatter.format(Double.parseDouble(TaxSections.get(empid+"_Before_Dedu_tax").toString()))+""   , font));
				p.setAlignment(Element.ALIGN_RIGHT);
				// p.setSpacingAfter(4);
				cell1 = new PdfPCell();
				cell1.setBorder(PdfPCell.NO_BORDER);
				cell1.setPadding(3);
				cell1.addElement(p);
				table.addCell(cell1);

				p = new Paragraph(new Phrase(lineSpacing, ""+formatter.format(Double.parseDouble(TaxSections.get(empid+"_ANN_Before_Dedu_tax").toString()))+"" , font));
				p.setAlignment(Element.ALIGN_RIGHT);
				//p.setSpacingAfter(4);
				cell2 = new PdfPCell();
				cell2.setBorder(PdfPCell.NO_BORDER);
				cell2.setPadding(3);
				cell2.addElement(p);
				table.addCell(cell2);
				//********************************************
				p = new Paragraph(new Phrase(lineSpacing,"\t\t\t i)Less:Tax Rebate U/s 87A", font));
				p.setAlignment(Element.ALIGN_LEFT);
				//p.setSpacingAfter(4);
				cell = new PdfPCell();
				cell.setBorder(PdfPCell.NO_BORDER);
				cell.setColspan(2);
				cell.setPadding(3);
				cell.addElement(p);
				table.addCell(cell);

				p = new Paragraph(new Phrase(lineSpacing, ""+formatter.format(Double.parseDouble(TaxSections.get(empid+"_EmpTaxAddl_E").toString()))+""   , font));
				p.setAlignment(Element.ALIGN_RIGHT);
				// p.setSpacingAfter(4);
				cell1 = new PdfPCell();
				cell1.setBorder(PdfPCell.NO_BORDER);
				cell1.setPadding(3);
				cell1.addElement(p);
				table.addCell(cell1);

				p = new Paragraph(new Phrase(lineSpacing, ""+formatter.format(Double.parseDouble(TaxSections.get(empid+"_ANN_EmpTaxAddl_E").toString()))+"" , font));
				p.setAlignment(Element.ALIGN_RIGHT);
				//p.setSpacingAfter(4);
				cell2 = new PdfPCell();
				cell2.setBorder(PdfPCell.NO_BORDER);
				cell2.setPadding(3);
				cell2.addElement(p);
				table.addCell(cell2);
				//***************************************
				//********************************************
				
				//***************************************
				//********************************************
				p = new Paragraph(new Phrase(lineSpacing,"15)Tax after Rebate (U/s 87A)", font));
				p.setAlignment(Element.ALIGN_LEFT);
				//p.setSpacingAfter(4);
				cell = new PdfPCell();
				cell.setBorder(PdfPCell.NO_BORDER);
				cell.setColspan(2);
				cell.setPadding(3);
				cell.addElement(p);
				table.addCell(cell);

				p = new Paragraph(new Phrase(lineSpacing, ""+formatter.format(Double.parseDouble(TaxSections.get(empid+"_Emp_Tax_E").toString()))+""   , font));
				p.setAlignment(Element.ALIGN_RIGHT);
				// p.setSpacingAfter(4);
				cell1 = new PdfPCell();
				cell1.setBorder(PdfPCell.NO_BORDER);
				cell1.setPadding(3);
				cell1.addElement(p);
				table.addCell(cell1);

				p = new Paragraph(new Phrase(lineSpacing, ""+formatter.format(Double.parseDouble(TaxSections.get(empid+"_ANN_Emp_Tax_E").toString()))+"" , font));
				p.setAlignment(Element.ALIGN_RIGHT);
				//p.setSpacingAfter(4);
				cell2 = new PdfPCell();
				cell2.setBorder(PdfPCell.NO_BORDER);
				cell2.setPadding(3);
				cell2.addElement(p);
				table.addCell(cell2);
				//***************************************
				//********************************************
				
				
				p = new Paragraph(new Phrase(lineSpacing,"\t\t\t i)Add:Surcharge(with relief who have taxable income>1cr)", font));
				p.setAlignment(Element.ALIGN_LEFT);
				//p.setSpacingAfter(4);
				cell = new PdfPCell();
				cell.setBorder(PdfPCell.NO_BORDER);
				cell.setColspan(2);
				cell.setPadding(3);
				cell.addElement(p);
				table.addCell(cell);

				p = new Paragraph(new Phrase(lineSpacing, ""+formatter.format(Double.parseDouble(TaxSections.get(empid+"_SURCHARGE").toString()))+""   , font));
				p.setAlignment(Element.ALIGN_RIGHT);
				// p.setSpacingAfter(4);
				cell1 = new PdfPCell();
				cell1.setBorder(PdfPCell.NO_BORDER);
				cell1.setPadding(3);
				cell1.addElement(p);
				table.addCell(cell1);

				p = new Paragraph(new Phrase(lineSpacing, ""+formatter.format(Double.parseDouble(TaxSections.get(empid+"_ANN_SURCHARGE").toString()))+"" , font));
				p.setAlignment(Element.ALIGN_RIGHT);
				//p.setSpacingAfter(4);
				cell2 = new PdfPCell();
				cell2.setBorder(PdfPCell.NO_BORDER);
				cell2.setPadding(3);
				cell2.addElement(p);
				table.addCell(cell2);
				
				
				p = new Paragraph(new Phrase(lineSpacing,"\t\t\t ii)Add:Education cess", font));
				p.setAlignment(Element.ALIGN_LEFT);
				//p.setSpacingAfter(4);
				cell = new PdfPCell();
				cell.setBorder(PdfPCell.NO_BORDER);
				cell.setColspan(2);
				cell.setPadding(3);
				cell.addElement(p);
				table.addCell(cell);

				p = new Paragraph(new Phrase(lineSpacing, ""+formatter.format(Double.parseDouble(TaxSections.get(empid+"Educess").toString()))+""   , font));
				p.setAlignment(Element.ALIGN_RIGHT);
				// p.setSpacingAfter(4);
				cell1 = new PdfPCell();
				cell1.setBorder(PdfPCell.NO_BORDER);
				cell1.setPadding(3);
				cell1.addElement(p);
				table.addCell(cell1);

				p = new Paragraph(new Phrase(lineSpacing, ""+formatter.format(Double.parseDouble(TaxSections.get(empid+"ANN_Educess").toString()))+"" , font));
				p.setAlignment(Element.ALIGN_RIGHT);
				//p.setSpacingAfter(4);
				cell2 = new PdfPCell();
				cell2.setBorder(PdfPCell.NO_BORDER);
				cell2.setPadding(3);
				cell2.addElement(p);
				table.addCell(cell2);
				//***************************************
				//********************************************
				p = new Paragraph(new Phrase(lineSpacing,"16) Total Tax Payable Per cumulative/Annum(i+ii)", font));
				p.setAlignment(Element.ALIGN_LEFT);
				//p.setSpacingAfter(4);
				cell = new PdfPCell();
				cell.setBorder(PdfPCell.NO_BORDER);
				cell.setColspan(2);
				cell.setPadding(3);
				cell.addElement(p);
				table.addCell(cell);

				p = new Paragraph(new Phrase(lineSpacing, ""+formatter.format(Double.parseDouble(TaxSections.get(empid+"tx_Paid").toString()))+""   , font));
				p.setAlignment(Element.ALIGN_RIGHT);
				// p.setSpacingAfter(4);
				cell1 = new PdfPCell();
				cell1.setBorder(PdfPCell.NO_BORDER);
				cell1.setPadding(3);
				cell1.addElement(p);
				table.addCell(cell1);

				p = new Paragraph(new Phrase(lineSpacing, ""+formatter.format(Double.parseDouble(TaxSections.get(empid+"ANN_tx_Paid").toString()))+"" , font));
				p.setAlignment(Element.ALIGN_RIGHT);
				//p.setSpacingAfter(4);
				cell2 = new PdfPCell();
				cell2.setBorder(PdfPCell.NO_BORDER);
				cell2.setPadding(3);
				cell2.addElement(p);
				table.addCell(cell2);
				//***************************************

				
				//********************************************
				/*p = new Paragraph(new Phrase(lineSpacing,"\t\t\t Payable Tax", font));
				p.setAlignment(Element.ALIGN_LEFT);
				//p.setSpacingAfter(4);
				cell = new PdfPCell();
				cell.setBorder(PdfPCell.NO_BORDER);
				cell.setColspan(2);
				cell.setPadding(3);
				cell.addElement(p);
				table.addCell(cell);

				p = new Paragraph(new Phrase(lineSpacing, ""+formatter.format(Double.parseDouble(TaxSections.get(empid+"tx_Paid_F").toString()))+""   , font));
				p.setAlignment(Element.ALIGN_RIGHT);
				// p.setSpacingAfter(4);
				cell1 = new PdfPCell();
				cell1.setBorder(PdfPCell.NO_BORDER);
				cell1.setPadding(3);
				cell1.addElement(p);
				table.addCell(cell1);

				p = new Paragraph(new Phrase(lineSpacing, ""+formatter.format(Double.parseDouble(TaxSections.get(empid+"ANN_tx_Paid").toString()))+"" , font));
				p.setAlignment(Element.ALIGN_RIGHT);
				//p.setSpacingAfter(4);
				cell2 = new PdfPCell();
				cell2.setBorder(PdfPCell.NO_BORDER);
				cell2.setPadding(3);
				cell2.addElement(p);
				table.addCell(cell2);*/
				//***************************************
				//********************************************
				p = new Paragraph(new Phrase(lineSpacing,"\t\t\t No. Of Months Paid/Annum", font));
				p.setAlignment(Element.ALIGN_LEFT);
				//p.setSpacingAfter(4);
				cell = new PdfPCell();
				cell.setBorder(PdfPCell.NO_BORDER);
				cell.setColspan(2);
				cell.setPadding(3);
				cell.addElement(p);
				table.addCell(cell);

			//	p = new Paragraph(new Phrase(lineSpacing, ""+ProposedMonths+""   , font));
				
				p = new Paragraph(new Phrase(lineSpacing, ""+Total_Months_paid+""   , font));
				
				//p = new Paragraph(new Phrase(lineSpacing, "7"   , font));
				
				p.setAlignment(Element.ALIGN_RIGHT);
				// p.setSpacingAfter(4);
				cell1 = new PdfPCell();
				cell1.setBorder(PdfPCell.NO_BORDER);
				cell1.setPadding(3);
				cell1.addElement(p);
				table.addCell(cell1);

				p = new Paragraph(new Phrase(lineSpacing, ""+Total_Months+"" , font));
				//p = new Paragraph(new Phrase(lineSpacing, "7" , font));
				p.setAlignment(Element.ALIGN_RIGHT);
				//p.setSpacingAfter(4);
				cell2 = new PdfPCell();
				cell2.setBorder(PdfPCell.NO_BORDER);
				cell2.setPadding(3);
				cell2.addElement(p);
				table.addCell(cell2);
				//***************************************
				//********************************************
				p = new Paragraph(new Phrase(lineSpacing,"\t\t\t Total Tax Payable For Month ", font));
				p.setAlignment(Element.ALIGN_LEFT);
				//p.setSpacingAfter(4);
				cell = new PdfPCell();
				cell.setBorder(PdfPCell.NO_BORDER);
				cell.setColspan(2);
				cell.setPadding(3);
				cell.addElement(p);
				table.addCell(cell);

				//double tx_p_mon=Math.round( Double.parseDouble(TaxSections.get(empid+"Paid_Month").toString())  );

				double tx_p_mon=Math.round( Double.parseDouble(TaxSections.get(empid+"tx_Paid").toString())/Total_Months_paid );
				
				//double tx_p_mon=Math.round( Double.parseDouble(TaxSections.get(empid+"tx_Paid").toString())/7 );
				
				
				p = new Paragraph(new Phrase(lineSpacing, ""+formatter.format(tx_p_mon)+""   , font));
				p.setAlignment(Element.ALIGN_RIGHT);
				// p.setSpacingAfter(4);
				cell1 = new PdfPCell();
				cell1.setBorder(PdfPCell.NO_BORDER);
				cell1.setPadding(3);
				cell1.addElement(p);
				table.addCell(cell1);

				double tx_p_mon_y=Math.round( (Double.parseDouble(TaxSections.get(empid+"ANN_tx_Paid").toString() ))/Total_Months );
				
			//	double tx_p_mon_y=Math.round( (Double.parseDouble(TaxSections.get(empid+"ANN_tx_Paid").toString() ))/7 );
				
				p = new Paragraph(new Phrase(lineSpacing, ""+tx_p_mon_y+"" , font));
				p.setAlignment(Element.ALIGN_RIGHT);
				//p.setSpacingAfter(4);
				cell2 = new PdfPCell();
				cell2.setBorder(PdfPCell.NO_BORDER);
				cell2.setPadding(3);
				cell2.addElement(p);
				table.addCell(cell2);
				//***************************************

				
				
				
				
				//********************************************
				p = new Paragraph(new Phrase(lineSpacing,"\t\t\t Less : Tax Deduct at Source (Deducted till Date)", font));
				p.setAlignment(Element.ALIGN_LEFT);
				//p.setSpacingAfter(4);
				cell = new PdfPCell();
				cell.setBorder(PdfPCell.NO_BORDER);
				cell.setColspan(2);
				cell.setPadding(3);
				cell.addElement(p);
				table.addCell(cell);

				p = new Paragraph(new Phrase(lineSpacing, ""+formatter.format(Double.parseDouble(TaxSections.get(empid+"_TDS_F").toString()))+""   , font));
				p.setAlignment(Element.ALIGN_RIGHT);
				// p.setSpacingAfter(4);
				cell1 = new PdfPCell();
				cell1.setBorder(PdfPCell.NO_BORDER);
				cell1.setPadding(3);
				cell1.addElement(p);
				table.addCell(cell1);

				p = new Paragraph(new Phrase(lineSpacing, ""+formatter.format(Double.parseDouble(TaxSections.get(empid+"_TDS_F").toString()))+"" , font));
				p.setAlignment(Element.ALIGN_RIGHT);
				//p.setSpacingAfter(4);
				cell2 = new PdfPCell();
				cell2.setBorder(PdfPCell.NO_BORDER);
				cell2.setPadding(3);
				cell2.addElement(p);
				table.addCell(cell2);
				//***************************************

		//*********************************************************************************
				p = new Paragraph(new Phrase(lineSpacing,"\t\t\t TDS due (paid & to be paid)", font));
				p.setAlignment(Element.ALIGN_LEFT);
				//p.setSpacingAfter(4);
				cell = new PdfPCell();
				cell.setBorder(PdfPCell.NO_BORDER);
				cell.setColspan(2);
				cell.setPadding(3);
				cell.addElement(p);
				table.addCell(cell);

				//double tx_p_mon=Math.round( Double.parseDouble(TaxSections.get(empid+"Paid_Month").toString())  );
				double TaxDue_pm=(Double.parseDouble(TaxSections.get(empid+"tx_Paid").toString()))-(Double.parseDouble(TaxSections.get(empid+"_TDS_F").toString()));
				
				p = new Paragraph(new Phrase(lineSpacing, ""+formatter.format(TaxDue_pm)+""   , font));
				p.setAlignment(Element.ALIGN_RIGHT);
				// p.setSpacingAfter(4);
				cell1 = new PdfPCell();
				cell1.setBorder(PdfPCell.NO_BORDER);
				cell1.setPadding(3);
				cell1.addElement(p);
				table.addCell(cell1);

				double TaxDue_pm_ann=(Double.parseDouble(TaxSections.get(empid+"ANN_tx_Paid").toString() ))-Double.parseDouble(TaxSections.get(empid+"_TDS_F").toString());
				
				p = new Paragraph(new Phrase(lineSpacing, ""+TaxDue_pm_ann+"" , font));
				p.setAlignment(Element.ALIGN_RIGHT);
				//p.setSpacingAfter(4);
				cell2 = new PdfPCell();
				cell2.setBorder(PdfPCell.NO_BORDER);
				cell2.setPadding(3);
				cell2.addElement(p);
				table.addCell(cell2);
				//***************************************		
				
		//********************************************************************************
		
				
				
				
				p = new Paragraph(new Phrase(lineSpacing," ", font1));
				p.setAlignment(Element.ALIGN_LEFT);
				p.setSpacingAfter(4);
				cell = new PdfPCell();
				cell.setBorder(PdfPCell.NO_BORDER);
				cell.setBorderWidthBottom(0.1f);
				cell.setPadding(3);
				cell.addElement(p);
				cell.setColspan(2);
				table.addCell(cell);


				p = new Paragraph(new Phrase(lineSpacing," ", font1));
				p.setAlignment(Element.ALIGN_RIGHT);
				p.setSpacingAfter(4);
				cell = new PdfPCell();
				cell.setBorder(PdfPCell.NO_BORDER);
				cell.setBorderWidthBottom(0.1f);
				cell.setPadding(3);
				cell.addElement(p);
				table.addCell(cell);

				p = new Paragraph(new Phrase(lineSpacing," ", font1));
				p.setAlignment(Element.ALIGN_RIGHT);
				p.setSpacingAfter(4);
				cell1 = new PdfPCell();
				cell1.setBorder(PdfPCell.NO_BORDER);
				cell1.setBorderWidthBottom(0.1f);
				cell1.setPadding(3);
				cell1.addElement(p);
				table.addCell(cell1);
				
				
	//=====================================================================
				
				font = new Font(base, 10f, Font.NORMAL);
				p = new Paragraph(new Phrase(lineSpacing,"Remaining Months", font));
				p.setAlignment(Element.ALIGN_LEFT);
				p.setSpacingAfter(4);
				cell = new PdfPCell();
				cell.setBorder(PdfPCell.NO_BORDER);
				//cell.setBorderWidthBottom(0.1f);
				
				cell.setBorder(PdfPCell.NO_BORDER);
				
				cell.setPadding(3);
				cell.addElement(p);
				cell.setColspan(2);
				table.addCell(cell);

				font = new Font(base, 10f, Font.BOLD);
				
				p = new Paragraph(new Phrase(lineSpacing,"   ", font));
				p.setAlignment(Element.ALIGN_RIGHT);
				p.setSpacingAfter(4);
				cell = new PdfPCell();
				//cell.setBorder(PdfPCell.NO_BORDER);
				cell.setBorder(PdfPCell.NO_BORDER);
				cell.setPadding(3);
				cell.addElement(p);
				table.addCell(cell);
               
				font = new Font(base, 10f, Font.NORMAL);
				
				Double Paid_Ann_Month=0.00;
				
				if(Integer.parseInt(ProposedMonths)!=0){
				 Paid_Ann_Month=(TaxDue_pm_ann / Double.parseDouble(ProposedMonths));
				}else{
					 Paid_Ann_Month=TaxDue_pm_ann;
				}
				
				p = new Paragraph(new Phrase(lineSpacing," "+ProposedMonths+" ", font1));
				p.setAlignment(Element.ALIGN_RIGHT);
				p.setSpacingAfter(4);
				cell1 = new PdfPCell();
				cell1.setBorder(PdfPCell.NO_BORDER);
				//cell1.setBorderWidthBottom(0.1f);
				cell1.setPadding(3);
				cell1.addElement(p);
				table.addCell(cell1);
			
				//===================================
				p = new Paragraph(new Phrase(lineSpacing,"To be Paid TDS For Month(Which is calculate on annual CTC)", font));
				p.setAlignment(Element.ALIGN_LEFT);
				p.setSpacingAfter(2);
				cell = new PdfPCell();
				cell.setBorder(PdfPCell.NO_BORDER);
				//cell.setBorderWidthBottom(0.1f);
				cell.setPadding(3);
				cell.addElement(p);
				cell.setColspan(2);
				table.addCell(cell);

				
				font = new Font(base, 10f, Font.BOLD);

				p = new Paragraph(new Phrase(lineSpacing,"   ", font));
				p.setAlignment(Element.ALIGN_RIGHT);
				p.setSpacingAfter(2);
				cell = new PdfPCell();
				//cell.setBorder(PdfPCell.NO_BORDER);
				cell.setBorder(PdfPCell.NO_BORDER);
				cell.setPadding(3);
				cell.addElement(p);
				table.addCell(cell);

				font = new Font(base, 10f, Font.BOLD);
				
				p = new Paragraph(new Phrase(lineSpacing,""+formatter.format(Paid_Ann_Month)+"", font));
				p.setAlignment(Element.ALIGN_RIGHT);
				p.setSpacingAfter(2);
				cell1 = new PdfPCell();
				//cell1.setBorder(PdfPCell.NO_BORDER);
				cell1.setBorder(PdfPCell.NO_BORDER);
				cell1.setPadding(3);
				cell1.addElement(p);
				table.addCell(cell1);
				
	//=======================Border=================================================			
				
				p = new Paragraph(new Phrase(lineSpacing," ", font1));
				p.setAlignment(Element.ALIGN_LEFT);
				p.setSpacingAfter(2);
				cell = new PdfPCell();
				cell.setBorder(PdfPCell.NO_BORDER);
				cell.setBorderWidthTop(0.1f);
				cell.setPadding(3);
				cell.addElement(p);
				cell.setColspan(2);
				table.addCell(cell);


				p = new Paragraph(new Phrase(lineSpacing," ", font1));
				p.setAlignment(Element.ALIGN_RIGHT);
				p.setSpacingAfter(2);
				cell = new PdfPCell();
				cell.setBorder(PdfPCell.NO_BORDER);
				cell.setBorderWidthTop(0.1f);
				cell.setPadding(3);
				cell.addElement(p);
				table.addCell(cell);

				p = new Paragraph(new Phrase(lineSpacing," ", font1));
				p.setAlignment(Element.ALIGN_RIGHT);
				p.setSpacingAfter(2);
				cell1 = new PdfPCell();
				cell1.setBorder(PdfPCell.NO_BORDER);
				cell1.setBorderWidthTop(0.1f);
				cell1.setPadding(3);
				cell1.addElement(p);
				table.addCell(cell1);
	//=============================================================================			

				try {


					document.add(table);

					p.setAlignment(Element.ALIGN_CENTER);
					cell1.setMinimumHeight(35f);
					cell1.addElement(p);
					table.addCell(cell);
					table.addCell(cell1);
					document.addAuthor("Hetero Health Care");
					document.addCreator("Java Team/Venu");
					document.addSubject("Thanks");
					document.addCreationDate();
					document.addTitle("Please read this");
					StyleSheet styles = new StyleSheet();

					p = new Paragraph(new Phrase(6f,"  ", font));
					p.setSpacingAfter(4);
					document.add(p);

					font = new Font(base, 8f, Font.NORMAL);
					font1 = new Font(base, 8f, Font.NORMAL);
					p = new Paragraph(new Phrase(6f,"Note : LTA, Bonus, Incentive, Actual Bonus & Any Other taxable components will be added to Gross & considered under TDS once they were Paid.", font1));
					p.setSpacingAfter(4);
					document.add(p);
					/*p = new Paragraph(new Phrase(6f," ", font));
					document.add(p);*/
					/*p = new Paragraph(new Phrase(6f,"\t\t\t\t\t\t\t\t\t\t\t\t once they were Paid.", font1));
					p.setSpacingAfter(4);
					document.add(p);*/
					/*p = new Paragraph(new Phrase(6f," ", font));
					document.add(p);*/
					p = new Paragraph(new Phrase(6f," PA : TDS Based on CTC \n", font1));
					p.setSpacingAfter(6);
					document.add(p);

					p = new Paragraph(new Phrase(6f," Note : Income from previous employear added to gross ", font1));
					p.setSpacingAfter(6);
					document.add(p);
					
					p = new Paragraph(new Phrase(6f," ", font));
					document.add(p);

					p = new Paragraph(new Phrase(4f,"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t***This is system generated TDS forecast report hence signature is not required", font1));
					p.setSpacingAfter(4);
					document.add(p);


					/*p = new Paragraph(new Phrase(7f,"\t *UMP : Upto this month Paid gross and proposed gross. & *PA : Proposed For Annuam ", font1));

					document.add(p);
					 */

					//document.add(Date);
					//document.add(name);
					//document.add(empno);
					//document.add(empDesg);
					/*document.add(empDept);
						document.add(empSub);
						document.add(Data1);
						document.add(Data2);
						document.add(Data3);
						document.add(Data4);
						document.add(Data5);
						document.add(Data6);
						document.add(Data7);*/
					
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				/* HTMLWorker htmlWorker = new HTMLWorker(document);

      String str = "<html><head></head><body style='font-family:tahoma;text-size:9px'>"+
        " <table width='100%' >"+
        " <tr><td colspan='3' align='center'> Income Tax Forecasting Report<br/>HETERO HEALTHCARE LTD  </td> </tr>"+
        " <tr><td colspan='3' >  <hr width='100%'>  </td> </tr>"+
        " <tr ><td align='left' height='20px'>Location : HYD/MUMBAY </td> <td colspan='2'></td> </tr>"+
        " <tr><td colspan='3' > <hr width='100%'>  </td> </tr>"+   //Table With In Table For Design
        " <tr><td align='left' colspan='2' >Employee Code : HYD/MUMBAY </td> <td align='right'> 25-May-2016</td> </tr>"+
        " <tr><td align='left' colspan='2'> &nbsp;&nbsp;  Headings </td> <td align='right'>Amount &nbsp;&nbsp;</td> </tr>"+
        " <tr><td colspan='3' > <hr width='100%'></td> </tr>"+
        "<tr><td align='left'  colspan='2' >dfgd</td>  <td align='right'>3</td> </tr> "+
        " <tr><td align='left'  colspan='2' >dfg</td> <td align='right'>3000</td> </tr>"+
        " <tr><td align='left'  colspan='2' style='font-family:tahoma;text-size:9px;'>1) Total Gross Salary Including Previous Gross </td> <td align='right'> 8000000</td> </tr>"+
        " <tr><td align='left'  colspan='2' >2)Less:Allowances To The Extent Exempt Under Section 10 </td> <td align='right'></td> </tr>"+
        " <tr><td align='left' colspan='2'>Conv</td>  <td align='right'> 20000</td> </tr>"+
        " <tr><td align='left'  colspan='2' >EDUALLOW</td>  <td align='right'>15000</td> </tr>"+
        " <tr><td align='left'  colspan='2'>HRA <div>HRA1</div></td>  <td align='right'>16000</td> </tr>"+
        " <tr><td align='left'  colspan='2'>MEDALLOW</td>  <td align='right'>8000</td> </tr>"+
        " <tr><td align='left'  colspan='2'>LTA</td>  <td align='right'>0.00</td> </tr>"+
        " <tr><td align='left'  colspan='2'>Total Exemption Amount</td>  <td align='right'>0.00</td> </tr>"+
        " <tr><td align='left'  colspan='2'>3)Balance(1-2)</td>  <td align='right'>23445566</td> </tr>"+
        " <tr><td align='left'  colspan='2'>4)Deductions</td>  <td align='right'>23445566</td> </tr>"+
        " <tr><td align='left'  colspan='2'>&nbsp;&nbsp; Professional Tax</td>  <td align='right'>234</td> </tr>"+
        " <tr><td align='left'  colspan='2'>5)Aggregate of(4) above</td>  <td align='right'>234</td> </tr>"+
        " <tr><td align='left'  colspan='2'>6)Income Chargeable Under The Head Salaries</td>  <td align='right'>234</td> </tr>"+
        " <tr><td align='left'  colspan='2'>7)Income From Other Sources</td>  <td align='right'>234</td> </tr>"+
        " <tr><td align='left'  colspan='2'>8)Gross Total Income(8-9)</td>  <td align='right'>  "+name.toString()+" </td> </tr>"+
        "</table>"+
        "</body></html>";
      htmlWorker.parse(new StringReader(str));
				 */
				document.close();
				//System.out.println("Forcast Done For Employee:"+empid);
			}
			//document.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
