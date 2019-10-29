package com.hhcdesk.regreports;

import java.beans.PropertyVetoException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeMap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.hhcdesk.db.GetDbData;

//import statements
public class AssessmentWriteExcel
{
    public Map generate(Connection conn,String filename,String reporttype) throws IOException, SQLException, PropertyVetoException 
        {
        //Blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook();
         
        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("Employee Data");
          
        //This data needs to be written (Object[])
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        
        ResultSet Res=null;
        GetDbData DataObj=new GetDbData();
		
	   	ResourceBundle rb=ResourceBundle.getBundle("Assessmentsqlqueries"); 
        Res=null;
		StringBuffer EmpData=new StringBuffer();
		EmpData.append(rb.getString(reporttype.toString().trim()).toString());
		
		  
		//a[0]=10;//initialization  
		
		try {
			try {
			Res=(ResultSet)DataObj.FetchData(EmpData.toString(), "oldEmpData", Res ,conn);
			}catch(Exception errr) {
				System.out.println("err::"+errr);
			}
			ResultSetMetaData rsmd=Res.getMetaData();
			int columnCount = rsmd.getColumnCount();
			
			System.out.println("columncount::"+columnCount);
			
			Object[] columnobjs = new Object[columnCount];
			
			
			// The column count starts from 1
			for (int i = 1; i <= columnCount; i++ ) {
				columnobjs[i-1] = rsmd.getColumnName(i);
			  // Do stuff with name
				//System.out.println("-----"+columnobjs[i]);
			}
	
		     data.put("1",columnobjs);
		     int j=1;
		
			while(Res.next()){
				j++;
				Object[] listobj = new Object[columnCount];
		     
		        for(int i=1;i<=columnCount;i++){
		        	//employees.add(Res.getString(i).toString());
		        	listobj[i-1]=Res.getString(i);
				}
		        
		        data.put(Integer.toString(j), listobj);
			} 
		}catch(Exception Er){
			System.out.println("Exception At Er:3:"+Er);
		}
        
             
        //Iterate over data and write to sheet
        Set<String> keyset = data.keySet();
        int rownum = 0;
        for (String key : keyset)
        {
            Row row = sheet.createRow(rownum++);
            Object [] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr)
            {
               Cell cell = row.createCell(cellnum++);
               if(obj instanceof String)
                    cell.setCellValue((String)obj);
                else if(obj instanceof Integer)
                    cell.setCellValue((Integer)obj);
            }
        }
        try
        {
        	
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File(filename));
            workbook.write(out);
            out.close();
            System.out.println("howtodoinjava_demo.xlsx written successfully on disk.");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }finally {
        	 if(conn!=null)
        			try {
        				conn.close();
        				System.out.println(" connection close");
        			} catch (SQLException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			}
        }
        
        
        Map map=new HashMap();
        map.put("status", "1001");
        map.put("filepath", "");
        
        
          return map;
        
		
    }
}