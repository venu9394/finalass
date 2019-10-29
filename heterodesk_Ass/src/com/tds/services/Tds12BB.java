package com.tds.services;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Map;

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

public class Tds12BB {
  // itextpdf-5.4.1.jar  http://sourceforge.net/projects/itext/files/iText/
  public static void main(String ... args ) {
	  
	  Tds12BB.myCode();
	  
  }
 /* public static void myCodess(String proposedMonths, Map basic, Map taxException, ArrayList taxempids, ArrayList empids, Map finalComponents, Map TaxSections){ 
  
	//  myCode_FillData(proposedMonths, basic, taxException, taxempids, empids, finalComponents, TaxSections);
	  
  }*/
	  
  public  static void myCode(){
  try {
	  
	  
	    	 Document document = new Document(PageSize.A4);
    		 Double gross=0.0 , pt=0.0 , income=0.0 ;
             PdfWriter.getInstance(document, new FileOutputStream("D:/ForCast/_12BB.pdf"));
             System.out.println("step 3");
      
      float fntSize, lineSpacing;
      fntSize = 14f;
      lineSpacing = 20f;
      BaseFont base = null;
		try {
			base = BaseFont.createFont("c:/Windows/fonts/Calibri.ttf", BaseFont.WINANSI, false);
		} catch (DocumentException e3) {
			e3.printStackTrace();
		} catch (IOException e3) {
			e3.printStackTrace();
		}
		document.open();
		PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        
        PdfPCell cell1;
        PdfPCell cell2,cell3,cell4;
       Font font = new Font(base, 10f, Font.BOLD);
       Font font1 = new Font(base, 10f, Font.BOLD);
       Paragraph  p =null;
       table.setHorizontalAlignment(Element.ALIGN_CENTER);
       
       cell1 = new PdfPCell();
       cell1.setBorder(PdfPCell.NO_BORDER);
       cell1.setPadding(5);
       
       cell2 = new PdfPCell();
       cell2.setBorder(PdfPCell.NO_BORDER);
       cell2.setPadding(5);
       
       cell3 = new PdfPCell();
       cell3.setBorder(PdfPCell.NO_BORDER);
       cell3.setPadding(5);
       
       cell4 = new PdfPCell();
       cell4.setBorder(PdfPCell.NO_BORDER);
       cell4.setPadding(5);
       
       //***********************************************************
       p = new Paragraph(new Phrase(lineSpacing,"Income Tax Forecasting Report", font));
       p.setAlignment(Element.ALIGN_CENTER);
       cell1.addElement(p);
       cell1.setColspan(3);
       table.addCell(cell1);
       
       p = new Paragraph(new Phrase(lineSpacing,"Genex", font));
       p.setAlignment(Element.ALIGN_CENTER);
       cell2.addElement(p);
       table.setHorizontalAlignment(Element.ALIGN_CENTER);
       cell2.setColspan(3);
       table.addCell(cell2);
        // Normal Font
        font = new Font(base, 10f, Font.NORMAL);
       
      
        p = new Paragraph(new Phrase(lineSpacing,"Income Tax Forecasting Report", font));
        p.setAlignment(Element.ALIGN_CENTER);
        cell1.addElement(p);
        //cell1.setColspan(3);
        table.addCell(cell1);
        
        p = new Paragraph(new Phrase(lineSpacing,"Genex", font));
        p.setAlignment(Element.ALIGN_CENTER);
        cell2.addElement(p);
        table.setHorizontalAlignment(Element.ALIGN_CENTER);
        //cell2.setColspan(3);
        table.addCell(cell2);
        
        p = new Paragraph(new Phrase(lineSpacing,"Income Tax Forecasting Report", font));
        p.setAlignment(Element.ALIGN_CENTER);
        cell3.addElement(p);
        //cell1.setColspan(3);
        table.addCell(cell3);
        
        p = new Paragraph(new Phrase(lineSpacing,"Genex", font));
        p.setAlignment(Element.ALIGN_CENTER);
        cell4.addElement(p);
        table.setHorizontalAlignment(Element.ALIGN_CENTER);
        //cell2.setColspan(3);
        table.addCell(cell4);
        
      
        document.addAuthor("Hetero Health Care");
        document.addCreator("Java Team/Venu");
        document.addSubject("Thanks");
        document.addCreationDate();
        document.addTitle("Please read this");
        
        StyleSheet styles = new StyleSheet();
        
  	   //styles.loadTagStyle("p", "f", null);
      //styles.loadTagStyle("span", "");
      try {
    	  
    	  document.add(table);
    	  
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
      System.out.println("Forcast Done For Employee:");
    
      //document.close();
  }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
