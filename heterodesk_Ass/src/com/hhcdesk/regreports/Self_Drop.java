package com.hhcdesk.regreports;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

public class Self_Drop extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public Self_Drop() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Connection con=null;
		HttpSession session = request.getSession();
		String emp_id = (String) session.getAttribute("EMP_ID");

		
		
		try {
			con=(java.sql.Connection)session.getAttribute("ConnectionObj");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		 RequestDispatcher rd=null;
		 ArrayList<String> list1 = new ArrayList<String>();
		 Gson gson = new Gson();
		 
		 Statement statement = null;
		        try {
		        	
		        	if (emp_id != null && emp_id != ""){
		        	 
		        	String updateTableSQL1 = "UPDATE hclhrm_prod_others.tbl_emp_resignation  SET STATUS = 1002  WHERE STATUS = 1001 and EMPLOYEEID = "+emp_id+" ";
		        	statement = con.createStatement();
		        	statement.execute(updateTableSQL1);

	            	list1.add("Success");
	            	String messages = gson.toJson(list1).trim();
					
					//out.println("{\"Out\":"+messages+"}");
	            	
					//con.close();
	            	
		        }     
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		        
		        rd=getServletContext().getRequestDispatcher("/Resignation");
	  			rd.forward(request, response); 
		
		}	
		
	

}
