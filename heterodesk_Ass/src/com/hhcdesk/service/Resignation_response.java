package com.hhcdesk.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import sun.util.BuddhistCalendar;



public class Resignation_response extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Resignation_response() {
		super();
	}

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

		Connection con1 = null;
		PreparedStatement st1 = null;
		ResultSet rs1 = null;
		RequestDispatcher rd = null;
		StringBuffer qry=new StringBuffer();
		System.out.println("Resignation Calling");
		ArrayList<String> resig_status = new ArrayList<String>();
//		response.setContentType("text/html");
		HttpSession session=request.getSession();
        String flag = null;
        String User_id=(String)session.getAttribute("EMP_ID");
        try {
			con1=(java.sql.Connection)session.getAttribute("ConnectionObj");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
				e1.printStackTrace();
		}
        try {
        	
        	qry.append("SELECT IF(MANAGER_MSTATUS='P','Pending',if(MANAGER_MSTATUS='A','Approved','Reject'))MM,");
        	qry.append(" IFNULL(MANAGERS_COMMENTS,'--') MRSC,");
        	qry.append(" IF(CC_STATUS='P','Pending',if(CC_STATUS='A','Approved','Reject'))CC,IFNULL(HRS_COMMENTS,'--')HRSC,");
        	qry.append(" IF(MAIL_STATUS='N','In Queue','Mail Sent')MAIL_STATUS,");
        	qry.append(" DATE_FORMAT(DATEMODIFIED,'%d-%m-%Y')UpdatedOn,");
        	qry.append(" IFNULL(DATE_FORMAT(MA_APP_DATE,'%d-%m-%Y'),'--')Managers_ApprovedDate,");
        	qry.append(" IFNULL(DATE_FORMAT(HR_APP_DATE,'%d-%m-%Y'),'--')HR_ApprovedDate");
        	qry.append(" FROM");
        	qry.append(" HCLHRM_PROD_OTHERS.TBL_EMP_RESIGNATION WHERE EMPLOYEEID="+User_id+" AND STATUS=1001 and MGR_OK<>1001");
        	
        	st1=con1.prepareStatement(qry.toString());
        	//st1 = con1.prepareStatement("select employeeid, Mr_Mail_Status,Mr_resig_status,Cc_Resig__Status from emp_resignation where employeeid = ? "+User_id+" and Emp_status = ?");
        	//st1.setString(1, User_id);
        	rs1 = st1.executeQuery();

        	if(!rs1.next()) {
					    
					   
					  
					   flag = "NO" ;
					   resig_status.add(flag);//4
					  
					   resig_status.add("");
					   resig_status.add("");
					   resig_status.add("");
					   resig_status.add("");
					   resig_status.add("");
					   resig_status.add("");
					   
					   resig_status.add("");
					   resig_status.add("");
					 
					
					  
					    request.setAttribute("resig_status", resig_status);
				   }
				   else {
					   
					   
				    flag = "YES";
				   resig_status.add(flag);//4
				   
				   resig_status.add(rs1.getString(1));//Managers Status
				   resig_status.add(rs1.getString(2));//M Comments
				   resig_status.add(rs1.getString(3));//CC Status
				   resig_status.add(rs1.getString(4));//Hr Comments
				   resig_status.add(rs1.getString(5));//Mail Status
				   resig_status.add(rs1.getString(6));//APPLYEDdate
				   
				   resig_status.add(rs1.getString(7));//Mail Status
				   resig_status.add(rs1.getString(8));//HRdate
				   
				   
				   
				   resig_status.add("resig_status");
				   
				   request.setAttribute("resig_status2", resig_status);
				  
				   }
				   
        	System.out.println("Mahesh flag---->"+flag);
				    rd=getServletContext().getRequestDispatcher("/Reg_emp.jsp");
		  			rd.forward(request, response); 
				    
				   
			}

			catch (Exception e) {

				System.out.println(e);
			}
			
	}
}
