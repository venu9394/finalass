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

public class Report_lwp {

    public static String Report_lwp_Gen(Set linkedHashSet_title,Set linkedHashSet_employee,Map FetchData_Map,String File) throws IOException {

    	String FileName="LWParrear.xls";
        try {

            FileInputStream file = new FileInputStream(new File("C:/lwparrear/Lwparrear_Template.xls"));

            HSSFWorkbook workbook = new HSSFWorkbook(file);
            HSSFSheet sheet = workbook.getSheetAt(0);
            
            HSSFFont defaultFont= workbook.createFont();
            sheet.createFreezePane(0, 7);
           // sheet.createFreezePane(0, 0, 8, 7);
            Iterator Titles = linkedHashSet_title.iterator(); 
            Iterator empdata = linkedHashSet_employee.iterator();
            // check values
            System.out.println("FetchData_Map::"+FetchData_Map.toString());
            Row row = sheet.getRow(6);
            System.out.println("row::"+row);
            if (null == row) {
                row = sheet.createRow(6);
            }
            row.createCell(1).setCellValue("ID");
            row.createCell(2).setCellValue("NAME");
            row.createCell(3).setCellValue("BUSINESSUNIT");
            row.createCell(4).setCellValue("NODAYS");
            row.createCell(5).setCellValue("PAYPERIOD");
            
            int i=6;
            while (Titles.hasNext()){
            	try{
                String Component=Titles.next().toString();
                row.createCell(i).setCellValue(""+FetchData_Map.get(Component)+"");
            	}catch(Exception err){
            		System.out.println(err);
            	}
               i++;
            }
            
            int rownum=7;
            while (empdata.hasNext()){
            	
            	row = sheet.getRow(rownum);
            	if (null == row) {
                    row = sheet.createRow(rownum);
                }
            	String empid=empdata.next().toString();
            	Titles = linkedHashSet_title.iterator();
            	
            	row.createCell(1).setCellValue(""+FetchData_Map.get(empid+"_ID")+"");
                row.createCell(2).setCellValue(""+FetchData_Map.get(empid+"_NAME")+"");
                row.createCell(3).setCellValue(""+FetchData_Map.get(empid+"_BU")+"");
                row.createCell(4).setCellValue(""+FetchData_Map.get(empid+"_NOD")+"");
                row.createCell(5).setCellValue(""+FetchData_Map.get(empid+"_PD")+"");
                
                int col=6;
            	while(Titles.hasNext()){
            	try{
            		String Component=Titles.next().toString();
                
                 row.createCell(col).setCellValue(""+FetchData_Map.get(empid+"_"+Component)+"");
            	
            	}catch(Exception err){
            		System.out.println(err);
            	}
            	col++;
            }
            	
            	rownum++;
            }  
            
           
            file.close();

            FileOutputStream outFile =new FileOutputStream(new File("C:/lwparrear/LWParrear.xls"));
            workbook.write(outFile);
            outFile.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return FileName;
    }
    


    public static String Report_lop_Gen(Set linkedHashSet_title,Set linkedHashSet_employee,Map FetchData_Map,String File) throws IOException {

    	String FileName="LWParrear.xls";
        try {

            FileInputStream file = new FileInputStream(new File("C:/lwparrear/Loparrear_Template.xls"));

            HSSFWorkbook workbook = new HSSFWorkbook(file);
            HSSFSheet sheet = workbook.getSheetAt(0);
            
            HSSFFont defaultFont= workbook.createFont();
            sheet.createFreezePane(0, 7);
           // sheet.createFreezePane(0, 0, 8, 7);
            Iterator Titles = linkedHashSet_title.iterator(); 
            Iterator empdata = linkedHashSet_employee.iterator();
            // check values
            System.out.println("FetchData_Map::"+FetchData_Map.toString());
            Row row = sheet.getRow(6);
            System.out.println("row::"+row);
            if (null == row) {
                row = sheet.createRow(6);
            }
            row.createCell(1).setCellValue("ID");
            row.createCell(2).setCellValue("NAME");
            row.createCell(3).setCellValue("BUSINESSUNIT");
            row.createCell(4).setCellValue("NODAYS");
            row.createCell(5).setCellValue("PAYPERIOD");
            
            int i=6;
            while (Titles.hasNext()){
            	try{
                String Component=Titles.next().toString();
                row.createCell(i).setCellValue(""+FetchData_Map.get(Component)+"");
            	}catch(Exception err){
            		System.out.println(err);
            	}
               i++;
            }
            
            int rownum=7;
            while (empdata.hasNext()){
            	
            	row = sheet.getRow(rownum);
            	if (null == row) {
                    row = sheet.createRow(rownum);
                }
            	String empid=empdata.next().toString();
            	Titles = linkedHashSet_title.iterator();
            	
            	row.createCell(1).setCellValue(""+FetchData_Map.get(empid+"_ID")+"");
                row.createCell(2).setCellValue(""+FetchData_Map.get(empid+"_NAME")+"");
                row.createCell(3).setCellValue(""+FetchData_Map.get(empid+"_BU")+"");
                row.createCell(4).setCellValue(""+FetchData_Map.get(empid+"_NOD")+"");
                row.createCell(5).setCellValue(""+FetchData_Map.get(empid+"_PD")+"");
                
                int col=6;
            	while(Titles.hasNext()){
            	try{
            		String Component=Titles.next().toString();
                
                 row.createCell(col).setCellValue(""+FetchData_Map.get(empid+"_"+Component)+"");
            	
            	}catch(Exception err){
            		System.out.println(err);
            	}
            	col++;
            }
            	
            	rownum++;
            }  
            
           
            file.close();

            FileOutputStream outFile =new FileOutputStream(new File("C:/lwparrear/LOParrear.xls"));
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
