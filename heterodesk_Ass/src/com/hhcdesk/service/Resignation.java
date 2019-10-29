package com.hhcdesk.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

public class Resignation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Resignation() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con1 = null;
		PreparedStatement st1 = null;
		Statement st;
		HttpSession session = request.getSession();
		String emp_id = (String) session.getAttribute("EMP_ID");
		String emp_reg_sub = request.getParameter("subject");
		String cc_mail = request.getParameter("cc_mail");
		String emp_reg_reason = request.getParameter("reason");
		String emp_feedback = request.getParameter("FeedBack");
		String to_mail = request.getParameter("to_mail");
		ResultSet rs =null;
		ArrayList<String> list1 = new ArrayList<String>();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		boolean flag =  false;

		StringBuffer qry=new StringBuffer();

		try {
			con1=(java.sql.Connection)session.getAttribute("ConnectionObj");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {


			if (to_mail != null && to_mail != "" && cc_mail != null && cc_mail != "" && emp_reg_sub != null && emp_reg_sub != "" && emp_reg_reason != null && emp_reg_reason != "")

			{
				System.out.println("1");
				
				
				con1.setAutoCommit(false);


				/*st1 = con1.prepareStatement("insert into emp_resignation  (employeeid,emp_reg_sub"
		     		+ ",emp_reg_reason,emp_feedback,emp_suggest,employee_tomail_id,Mr_Mail_Status,Cc_Mail_Status,Mr_resig_status,Cc_Resig__Status,emp_cc_mail,emp_Self_mail,Emp_status) values('10514',?,?,?,?,?,'Pending','Pending','Pending','Pending',?,?,'1001')");
				 */


				st1=con1.prepareStatement("INSERT INTO HCLHRM_PROD_OTHERS.TBL_EMP_RESIGNATION"
						+ "(EMPLOYEEID, TO_MAIL, CC_MAIL, MANAGER_MSTATUS, CC_STATUS, REASON, SUBJECT, FEEDBACK, MAIL_STATUS, STATUS, DATEMODIFIED)"
						+ " VALUES (?,?,?,?,?,?,?,?,?,?,?)");

				System.out.println("2");

				st1.setString(1, emp_id);
				st1.setString(2, to_mail);
				st1.setString(3, cc_mail);
				st1.setString(4, "P");
				st1.setString(5, "P");
				st1.setString(6, emp_reg_reason);
				st1.setString(7, emp_reg_sub);
				st1.setString(8, emp_feedback);
				st1.setString(9, "N");
				st1.setInt(10, 1001);
				st1.setTimestamp(11,new Timestamp(System.currentTimeMillis()));

				System.out.println("3"+st1.toString());
				boolean k = st1.execute(); 
				con1.commit();
				System.out.println("4"+k);
				if (k) {
					flag  = true ;
					// System.out.println("LOGGEDD True"+k);
				}

				else {

                  //  System.out.println("LOGGEDD fASE DATA IS iNSERETD ______"+k);
 
					qry.append("SELECT IF(MANAGER_MSTATUS='P','Pending',if(MANAGER_MSTATUS='A','Approved','Rejected')) MM,");
		        	qry.append(" IFNULL(MANAGERS_COMMENTS,'--') MRSC,");
		        	qry.append("IF(CC_STATUS='P','Pending',if(CC_STATUS='A','Approved','Rejected')) CC,  IFNULL(HRS_COMMENTS,'--')HRSC,");
		        	qry.append(" IF(MAIL_STATUS='N','In Queue','Mail Sent')MAIL_STATUS,");
		        	qry.append(" DATE_FORMAT(DATEMODIFIED,'%d-%m-%Y')UpdatedOn,");
		        	qry.append(" IFNULL(DATE_FORMAT(MA_APP_DATE,'%d-%m-%Y'),'--')Managers_ApprovedDate,");
		        	qry.append(" IFNULL(DATE_FORMAT(HR_APP_DATE,'%d-%m-%Y'),'--')HR_ApprovedDate");
		        	qry.append(" FROM");
		        	qry.append(" HCLHRM_PROD_OTHERS.TBL_EMP_RESIGNATION WHERE EMPLOYEEID="+emp_id+" AND STATUS=1001 and MGR_OK not in(1001)");



					//String Mq = "select employeeid, Mr_Mail_Status,Mr_resig_status,Cc_Resig__Status from emp_resignation where employeeid = '10514' and Emp_status = 1001";


					st = (Statement) con1.createStatement();
					rs = (ResultSet) st.executeQuery(qry.toString());


					while(rs.next())
					{

						list1.add(""+k);
						list1 .add(rs.getString(1));
						list1 .add(rs.getString(2));
						list1 .add(rs.getString(3));
						list1 .add(rs.getString(4));
						list1 .add(rs.getString(5));
						list1 .add(rs.getString(6));
						list1 .add(rs.getString(7));
						list1 .add(rs.getString(8));
					}	
				}
				String messages = gson.toJson(list1).trim();

				out.println("{\"Out\":"+messages+"}");
			}	  
		}
		catch (SQLException e) {
			list1.add("sql_error");
			list1.add(""+e);
			//System.out.println(e.getMessage());

			String messages = gson.toJson(list1).trim();

			out.println("{\"Out\":"+messages+"}");
			try {
				con1.rollback();
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
		} 
		finally{

			if (st1 != null) {
				try {
					st1.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
		}
	}
}