package com.hhcdesk.service;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request,response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	 HttpSession session = request.getSession(true);
    	 String username=(String)session.getAttribute("EMP_ID");
    	 ServletContext c = getServletContext();
    	 c.removeAttribute(username);
    	 java.sql.Connection conn=null;
    	 try {
 			//conn =dataSource.getConnection();
    		 conn=(java.sql.Connection)session.getAttribute("ConnectionObj");
    		 
    		 if(conn!=null){
    			 conn.close();
    		 }
    		 
 		} catch (Exception e1) {
 			// TODO Auto-generated catch block
 				e1.printStackTrace();
 		}
    	 
    	 
        if (session!= null) {
            session.invalidate();
        }
        //session.setMaxInactiveInterval(-1);
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setDateHeader("Expires", 1); // Proxies.
        response.sendRedirect("login.html");
          
    }
}
