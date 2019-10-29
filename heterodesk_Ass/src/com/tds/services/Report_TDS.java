package com.tds.services;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import net.sf.jasperreports.engine.export.oasis.CellStyle;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Report_TDS {

    public static String Report_lwp_Gen(Set linkedHashSet_title,Set linkedHashSet_employee,Map FetchData_Map,String File) throws IOException {
    	String FileName="24Q_TDS.xls";
        try {

        	//File Fiel=new File("C:/lwparrear/24Q_TDS.xls");
        	
        	try {

      	      File file = new File("C:/lwparrear/24Q_TDS.xls");

      	      if (file.createNewFile()){
      	        System.out.println("File is created!");
      	      }else{
      	        System.out.println("File already exists.");
      	      if(file.delete()){
      	    	  
      	    	System.out.println("File Deleted.");
      	      }
      	      
      	      }

          	} catch (IOException e) {
      	      e.printStackTrace();
      	}
        	
        	
        	
            FileInputStream file = new FileInputStream(new File("C:/lwparrear/TDS24QTEMPLATE.xls"));

            HSSFWorkbook workbook = new HSSFWorkbook(file);
            HSSFSheet sheet = workbook.getSheetAt(0);
            
              HSSFFont defaultFont= workbook.createFont();
            // sheet.createFreezePane(0, 7);
            // sheet.createFreezePane(0, 0, 8, 7);
            
        //    Iterator Titles = linkedHashSet_title.iterator(); 
            Iterator empdata = linkedHashSet_employee.iterator();
            
            // check values
            System.out.println("FetchData_Map::"+FetchData_Map.toString());
            Row row = sheet.getRow(3);
          /*  System.out.println("row::"+row);
            if (null == row) {
                row = sheet.createRow(3);
            }*/
            
            
          /*  row.createCell(1).setCellValue("ID");
            row.createCell(2).setCellValue("NAME");
            row.createCell(3).setCellValue("BUSINESSUNIT");
            row.createCell(4).setCellValue("NODAYS");
            row.createCell(5).setCellValue("PAYPERIOD");*/
            
           /* int i=6;
            while (Titles.hasNext()){
            	try{
                String Component=Titles.next().toString();
                row.createCell(i).setCellValue(""+FetchData_Map.get(Component)+"");
            	}catch(Exception err){
            		System.out.println(err);
            	}
               i++;
            }*/
            
            int rownum=2;
            int colc=1;
            while (empdata.hasNext()){
            	
            	
            	
            	
            	
            	String empid=empdata.next().toString();
            	//Titles = linkedHashSet_title.iterator();
            	
            	  //System.out.println("empid ::"+empid);
            	
            	
            	for(int i=Integer.parseInt(FetchData_Map.get("FROMPAYPERIOD").toString());i<=Integer.parseInt(FetchData_Map.get("TOPAYPERIOD").toString());i++){
            		
            		
            	
            	if(FetchData_Map.get(empid+"_"+i).toString()!=null && FetchData_Map.get(empid+"_"+i).toString().equalsIgnoreCase("Y") ){ /// For Validate Loop
            		row = sheet.getRow(rownum);
                	if (null == row) {
                        row = sheet.createRow(rownum);
                    }
                	
            	
            	  row.createCell(0).setCellValue(""+colc+"");
            	  row.createCell(1).setCellValue("");
            	  row.createCell(2).setCellValue(""+FetchData_Map.get(empid+"_ID")+"");  // employee Ref Number
            	  row.createCell(3).setCellValue(""+FetchData_Map.get(empid+"_PAN")+""); // Pan Number
            	  
            	  row.createCell(4).setCellValue("");  // Pan Ref Number empty
            	  row.createCell(5).setCellValue(""+FetchData_Map.get(empid+"_NAME")+""); // Name of employee
            	  row.createCell(6).setCellValue("92B");
            	  
            	  
            	 // row.createCell(7).setCellValue(""+FetchData_Map.get(empid+"_"+i+"_DAT")+"");  // Date of payment/ credit (dd/mm/yyyy)
            	//  row.createCell(8).setCellValue(""+FetchData_Map.get(empid+"_"+i+"_DAT")+""); // Date of Deduction payment/ credit (dd/mm/yyyy)
            	  
            	 
            	  row.createCell(7).setCellValue(""+FetchData_Map.get(""+i+""));
            	  row.createCell(8).setCellValue(""+FetchData_Map.get(""+i+"")); 
            	  
            	  
            	  row.createCell(9).setCellValue(""+FetchData_Map.get(empid+"_"+i+"_AMT")+"");  //Amount Paid or Credited
            	  row.createCell(10).setCellValue(""+FetchData_Map.get(empid+"_"+i+"_TAX")+"");  // TAX
            	  row.createCell(11).setCellValue(""+FetchData_Map.get(empid+"_"+i+"_SUR")+"");  // Surcharge
            	  row.createCell(12).setCellValue(""+FetchData_Map.get(empid+"_"+i+"_EDUCESS")+"");  //Education Cess
            	  row.createCell(13).setCellValue(""+FetchData_Map.get(empid+"_"+i+"_TOTAL_TDS")+"");// TOTALTDS
            	  
            	  row.createCell(14).setCellValue(""+FetchData_Map.get(empid+"_"+i+"_TOTAL_TDS")+""); // TOTALTDS DEPOSITED
            	  
            	  row.createCell(15).setCellValue(" ");  // Date OF Deposit
            	 // row.createCell(15).setCellValue(""+FetchData_Map.get(empid+"_DAT_DEPOSIT")+"");  // Date OF Deposit
            	  
            	  row.createCell(16).setCellValue("");
            	  row.createCell(17).setCellValue("");
            	  row.createCell(18).setCellValue(""+FetchData_Map.get(empid+"_PAN_FLAG")+"");  // PAN FLAG --Y/N
            	  row.createCell(19).setCellValue("");
            	
            	  
            	  
            	// row.createCell(2).setCellValue(""+FetchData_Map.get(empid+"_NAME")+"");
               // row.createCell(3).setCellValue(""+FetchData_Map.get(empid+"_BU")+"");
               // row.createCell(4).setCellValue(""+FetchData_Map.get(empid+"_NOD")+"");
              //  row.createCell(5).setCellValue(""+FetchData_Map.get(empid+"_PD")+"");
                
         /*       int col=6;
            	while(Titles.hasNext()){
            	try{
            		String Component=Titles.next().toString();
                
                 row.createCell(col).setCellValue(""+FetchData_Map.get(empid+"_"+Component)+"");
            	
            	}catch(Exception err){
            		System.out.println(err);
            	}
            	col++;
            }*/
                
            	
            	rownum++;
            	colc++;
            	
            	}  // if Loop Closed
            	}
            }  
            
           
            file.close();

            FileOutputStream outFile =new FileOutputStream(new File("C:/lwparrear/24Q_TDS.xls"));
            workbook.write(outFile);
            outFile.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return FileName;
		
    }
	
}
