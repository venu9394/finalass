package com.hhcdesk.regreports;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;


public class Document_Dowload extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public Document_Dowload() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		
		HttpSession session=request.getSession();
		Connection con=null;
		String   emp_id = (String) session.getAttribute("EMP_ID");
		try {
			//conn =dataSource.getConnection();
			con=(java.sql.Connection)session.getAttribute("ConnectionObj");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
				e1.printStackTrace();
		}
		System.out.println("Step 1");
		
		
		 RequestDispatcher rd=null;
		 String filepath = "C:/iconnect_jasper/downloads/";
		        try {
		        	Map hm=new HashMap();
					
					// hm.put("$P{emp_id}",1);
		        	
		        	System.out.println("emp_id::"+emp_id);
		        	hm.put("empid",emp_id);
		        	
		        	hm.put("IMAGE_PATH","C:/iconnect_jasper/");
		        	
		        	
		        	
		        	
		        	 JasperFillManager.fillReportToFile("C:/iconnect_jasper/resignation.jasper/", new HashMap(),con);
		    		 System.out.println("Done!");

		    		 
		    		 
		    		 String printFileName="C:/iconnect_jasper/resignation.jasper/";
		    		 
		    		 
		    		 
		    		 JasperFillManager.fillReportToFile(printFileName,hm,con);
		    		 System.out.println("Done!");

		    		 String printFileName1="C:/iconnect_jasper/resignation.jrprint";
		    		  
		    		 JasperExportManager.exportReportToPdfFile(printFileName1, "C:/iconnect_jasper/downloads/"+hm.get("empid")+".pdf");
		    		 
		    		
		             
		        	   System.out.println("Pdf created successfully..");
		        	   
						response.setContentType("APPLICATION/OCTET-STREAM");
						response.setHeader("Content-Disposition", "attachment; filename=\""+emp_id+".pdf\"");

						
						FileInputStream fileInputStream = new FileInputStream(filepath+ emp_id +".pdf");
						int i;
						while ((i = fileInputStream.read()) != -1) {
							out.write(i);
						}
						try{
						fileInputStream.close();
						out.close();
						}catch(Exception err){
							System.out.println("err::"+err);
						}
		            
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		
		}	
		
	

}
