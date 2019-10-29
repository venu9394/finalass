package com.tds.services;
import groovy.ui.SystemOutputInterceptor;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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


public class PaidDatainfo {
	// itextpdf-5.4.1.jar  http://sourceforge.net/projects/itext/files/iText/
	public static void main(String ... args ) {

		// TdsForeCast.myCode();

	}
	public static void myCode(String proposedMonths, Map basic, Map taxException, ArrayList taxempids, ArrayList empids, Map finalComponents, Map TaxSections,List Components,Map Components_map,String yeardata,Connection connection,String Date_Path){ 

		myCode_FillData(proposedMonths, basic, taxException, taxempids, empids, finalComponents, TaxSections,Components,Components_map,yeardata,connection,Date_Path);

	}

	public static void myCode_FillData(String proposedMonths, Map basic, Map TaxException, ArrayList taxempids, ArrayList empids, Map finalComponents, Map TaxSections,List Components,Map Components_map,String yeardata,Connection connection,String Date_Path){


		try {
			String FilePath="C:/TDS_FORECAST";
			String Sub_FilePath="C:/TDS_FORECAST/"+Date_Path+"/PAIDINFO/";

			//Sub_FilePath="//10.30.0.24/f/java/TDS_ forcast/";
			Sub_FilePath=Sub_FilePath.concat(basic.get("BuName").toString());

			//Sub_FilePath=Sub_FilePath.concat("_info");
			File file = new File(FilePath);
			if (!file.exists()) {
				if (file.mkdir()) {
					System.out.println("Directory is created!");
				} else {
					System.out.println("Failed to create directory!");
				}
			}
			File files = new File(Sub_FilePath);
			if (!files.exists()) {
				if (files.mkdirs()) {
					System.out.println("Multiple directories are created!");
				} else {
					System.out.println("Failed to create multiple directories!");
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
			java.util.Iterator empseq = taxempids.iterator();


			while(empseq.hasNext()){

				Document document = new Document(PageSize.A4);
				String empid= empseq.next().toString(); 
				Double gross=0.0 , pt=0.0 , income=0.0 ;
				//gross=Double.parseDouble(basic.get(empid+".gross").toString());
				//pt=Double.parseDouble(basic.get(empid+".Pt").toString());
				//income=gross-pt;
				//Total_Months=(Integer.parseInt(basic.get(empid+".pm").toString()))+(Integer.parseInt(ProposedMonths));

			//	System.out.println("step 2");

				//PdfWriter.getInstance(document, new FileOutputStream(Sub_FilePath+"/"+empid+"_CTCinfo.pdf"));
				
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

				p = new Paragraph(new Phrase(lineSpacing," ", font));
				//p = new Paragraph(new Phrase(lineSpacing,"Income Tax Forecasting Report", font));
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

				//p = new Paragraph(new Phrase(lineSpacing,basic.get("BuName").toString(), font));
				
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

				font1=font;

				p = new Paragraph(new Phrase(lineSpacing,"Salary Headings", font1));
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


				p = new Paragraph(new Phrase(lineSpacing,"Component Type", font1));
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

				p = new Paragraph(new Phrase(lineSpacing,"Amount", font1));
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
			//LOOP Below  Data
		  java.util.Iterator compData2 = Components.iterator();
		 
		  while(compData2.hasNext()){
			  
			  String components= compData2.next().toString(); 
			
			 String Com_name=null,Com_type=null,Com_Data="0";
			 
			 try{
				 Com_name=Components_map.get(components+"-NAME").toString();
				 Com_type=Components_map.get(components+"-ETYPE").toString();
				 Com_Data=Components_map.get(empid+"-CTC-"+components).toString();
				 
			 }catch(Exception Ers){
				 
				 System.out.println("Ers::"+Ers);
			 }
			  lineSpacing=6f;
			  //Components_map
				
			  p = new Paragraph(new Phrase(lineSpacing,""+Com_name+"", font1));
			  
				p.setAlignment(Element.ALIGN_LEFT);
				//p.setSpacingAfter(4);
				cell = new PdfPCell();
				cell.setBorder(PdfPCell.NO_BORDER);
				cell.setColspan(2);
				cell.setPadding(3);
				cell.addElement(p);
				table.addCell(cell);
               p = new Paragraph(new Phrase(lineSpacing, ""+Com_type+"", font1));
               
				p.setAlignment(Element.ALIGN_RIGHT);
				//p.setSpacingAfter(4);
				cell1 = new PdfPCell();
				cell1.setBorder(PdfPCell.NO_BORDER);
				cell1.setPadding(3);
				cell1.addElement(p);
				table.addCell(cell1); 
									//"+formatter.format(Double.parseDouble(Components_map.get(empid+"-CTC-"+components).toString()))+"
				
			//	System.out.println(empid+"-CTC-"+components);
				
				//System.out.println("DFG::"+Components_map.get(empid+"-CTC-"+components).toString());
				
				
				p = new Paragraph(new Phrase(lineSpacing, ""+Com_Data+"", font1));
				
				p.setAlignment(Element.ALIGN_RIGHT);
				//p.setSpacingAfter(4);
				cell2 = new PdfPCell();
				cell2.setBorder(PdfPCell.NO_BORDER);
				cell2.setPadding(3);
				cell2.addElement(p);
				table.addCell(cell2);
				//************************************
		  }
		  p = new Paragraph(new Phrase(lineSpacing," ", font1));
			p.setAlignment(Element.ALIGN_RIGHT);
			p.setSpacingAfter(2);
			cell = new PdfPCell();
			cell.setBorder(PdfPCell.NO_BORDER);
			cell.setBorderWidthBottom(0.1f);
			cell.setPadding(3);
			
			cell.setColspan(4);
			
			cell.addElement(p);
			table.addCell(cell);
	// For Border Selection
			float[] columnWidths1 = {20,20,20,20,20};
			PdfPTable  table1 = new PdfPTable(columnWidths1);
			table1.setWidthPercentage(100);
			com.hhcdesk.db.GetDbData DataObj=new com.hhcdesk.db.GetDbData();
			ResultSet rs;
			rs=null;
			
						//Components.add(rs.getString(1));
		    p = new Paragraph(new Phrase(lineSpacing,"Paid Months", font1));
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
			//cell.setColspan(2);
			//cell.setRowspan(2);
			table1.addCell(cell);

			p = new Paragraph(new Phrase(lineSpacing,"Payable Days", font1));
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
			//cell.setColspan(2);
			//cell.setRowspan(2);
			table1.addCell(cell);
			
			p = new Paragraph(new Phrase(lineSpacing,"Components ", font1));
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
			//cell.setColspan(2);
			//cell.setRowspan(2);
			table1.addCell(cell1);
			
			

			p = new Paragraph(new Phrase(lineSpacing,"Actuals", font1));
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
			table1.addCell(cell);

			p = new Paragraph(new Phrase(lineSpacing,"Paid", font1));
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
			table1.addCell(cell1);
	        
			/*p = new Paragraph(new Phrase(lineSpacing,"Others", font1));
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
			table1.addCell(cell1);*/
			
			
			 
			
			p = new Paragraph(new Phrase(lineSpacing," ", font1));
			p.setAlignment(Element.ALIGN_RIGHT);
			p.setSpacingAfter(2);
			cell = new PdfPCell();
			cell.setBorder(PdfPCell.NO_BORDER);
			//cell.setBorderWidthBottom(0.1f);
			cell.setPadding(5);
			cell.setColspan(5);
			cell.addElement(p);
			table1.addCell(cell);
			
			
			String LoadData= yeardata.concat(" and  b.employeesequenceno="+empid+" ");
			
		//	System.out.println("LoadData::"+LoadData.toString());
			
			rs=(ResultSet)DataObj.fupaid(LoadData.toString(), "yeardata", rs ,connection);
			try {
				while(rs.next()){
					
					
					try{
						   if(Double.parseDouble(rs.getString(5).toString())>0){
						    p = new Paragraph(new Phrase(lineSpacing,""+rs.getString(2)+"", font1));
							p.setAlignment(Element.ALIGN_LEFT);
							cell = new PdfPCell();
							cell.setBorder(PdfPCell.NO_BORDER);
							//cell.setBorderWidthBottom(0.1f);
							cell.setPadding(3);
							cell.addElement(p);
							table1.addCell(cell);

							p = new Paragraph(new Phrase(lineSpacing,""+rs.getString(3)+"", font1));
							p.setAlignment(Element.ALIGN_LEFT);
							cell = new PdfPCell();
							cell.setBorder(PdfPCell.NO_BORDER);
							//cell.setBorderWidthBottom(0.1f);
							cell.setPadding(3);
							cell.addElement(p);
							table1.addCell(cell);
                            
							
							p = new Paragraph(new Phrase(lineSpacing,""+rs.getString(6)+"", font1));
							p.setAlignment(Element.ALIGN_RIGHT);
							cell1 = new PdfPCell();
							cell1.setBorder(PdfPCell.NO_BORDER);
							//cell1.setBorderWidthBottom(0.1f);
							cell1.setPadding(3);
							cell1.addElement(p);
							//cell.setColspan(2);
							table1.addCell(cell1);
							
							
							p = new Paragraph(new Phrase(lineSpacing,""+rs.getString(4)+"", font1));
							p.setAlignment(Element.ALIGN_RIGHT);
							cell = new PdfPCell();
							cell.setBorder(PdfPCell.NO_BORDER);
						//	cell.setBorderWidthBottom(0.1f);
							cell.setPadding(3);
							cell.addElement(p);
							table1.addCell(cell);

							p = new Paragraph(new Phrase(lineSpacing,""+rs.getString(5)+"", font1));
							p.setAlignment(Element.ALIGN_RIGHT);
							cell1 = new PdfPCell();
							cell1.setBorder(PdfPCell.NO_BORDER);
							//cell1.setBorderWidthBottom(0.1f);
							cell1.setPadding(3);
							cell1.addElement(p);
							table1.addCell(cell1);
							
							
							/*p = new Paragraph(new Phrase(lineSpacing," ", font1));
							p.setAlignment(Element.ALIGN_RIGHT);
							p.setSpacingAfter(2);
							cell = new PdfPCell();
							cell.setBorder(PdfPCell.NO_BORDER);
							//cell.setBorderWidthBottom(0.1f);
							cell.setPadding(5);
							cell.setColspan(5);
							cell.addElement(p);
							table1.addCell(cell);*/
							
						   }
								
						}catch(Exception ex2){
						
						System.out.println("components_new2 ::"+ex2);
							ex2.printStackTrace();		
					}
				}

			} catch (SQLException e2) {
				
				System.out.println("components_new  e2::"+e2);
				e2.printStackTrace();
			}
			
			
			
		//LOOP Above Data

				p = new Paragraph(new Phrase(lineSpacing," ", font1));
				p.setAlignment(Element.ALIGN_RIGHT);
				p.setSpacingAfter(2);
				cell = new PdfPCell();
				cell.setBorder(PdfPCell.NO_BORDER);
				cell.setBorderWidthBottom(0.1f);
				cell.setPadding(5);
				
				cell.setColspan(6);
				
				cell.addElement(p);
				table1.addCell(cell);
//         Next Caliculation Start
				
				
				
				/*p = new Paragraph(new Phrase(lineSpacing," ", font1));
				p.setAlignment(Element.ALIGN_RIGHT);
				p.setSpacingAfter(2);
				cell1 = new PdfPCell();
				cell1.setBorder(PdfPCell.NO_BORDER);
				cell1.setBorderWidthBottom(0.1f);
				cell1.setPadding(3);
				cell1.addElement(p);
				table.addCell(cell1);*/

				try {


					document.add(table);
					document.add(table1);

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

					p = new Paragraph(new Phrase(6f," ", font1));
				//	p = new Paragraph(new Phrase(6f,"Note : Please don't considered as a salary Breakup any queries Please Contact HR Team", font1));
					p.setSpacingAfter(4);
					document.add(p);
					p = new Paragraph(new Phrase(6f," ", font));
					p.setSpacingAfter(4);
					document.add(p);
					/*p = new Paragraph(new Phrase(6f,"\t\t\t\t\t\t\t\t\t\t\t\t once they were Paid.", font1));
					p.setSpacingAfter(4);
					document.add(p);
					p = new Paragraph(new Phrase(6f," ", font));
					document.add(p);
					p = new Paragraph(new Phrase(6f,"*PM : TDS Based on Gross paid & To be Paid Gross ; PA : TDS Based on CTC \n", font1));
					p.setSpacingAfter(6);
					document.add(p);

					p = new Paragraph(new Phrase(6f," ", font));
					document.add(p);
*/
					p = new Paragraph(new Phrase(4f," ", font1));
				//	p = new Paragraph(new Phrase(4f,"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t***This is system generated  report hence signature is not required", font1));
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
