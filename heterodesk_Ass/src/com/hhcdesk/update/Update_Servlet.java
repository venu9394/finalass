package com.hhcdesk.update;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Update_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Update_Servlet() {
		super();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		String empolyee_id = request.getParameter("EmployeeId");
		String type = request.getParameter("type");
		String first_name = request.getParameter("first_name");
		String last_name = request.getParameter("last_name");
		String Date = request.getParameter("Date");

		String NUMBER = request.getParameter("NUMBER");

		String Banktype = request.getParameter("bank_type");

		String acno = request.getParameter("acno");

		String per_address1 = request.getParameter("per_address1");
		String per_address2 = request.getParameter("per_address2");
		String email_id = request.getParameter("email_id");
		String mobile_id = request.getParameter("mobile_id");
		String state = request.getParameter("state");
		String city = request.getParameter("city");
		
		
		String Issuetype = request.getParameter("Issuetype");
		String request_msg = request.getParameter("request_msg");
		String email = request.getParameter("email_id");
		//System.out.println(""+Issuetype+""+request_msg+""+email);
		
		String username=(String)session.getAttribute("EMP_ID");

		//PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		//ResultSet rs = null;
		Connection connection = null;

		try {
			// conn =dataSource.getConnection();
			connection = (java.sql.Connection) session
					.getAttribute("ConnectionObj");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		PrintWriter out = response.getWriter();

		try {

			if (empolyee_id != null && type.equalsIgnoreCase("EmployeeName")) {

				ps1 = connection
						.prepareStatement("INSERT INTO hclhrm_prod_others.hhcl_emp_profile_table(employee_id, first_name, last_name,status,UPDATE_BY) VALUES(?,?,?,?,?)");
				ps1.setString(1, empolyee_id);
				ps1.setString(2, first_name);
				ps1.setString(3, last_name);
				ps1.setString(4, "1111");
				ps1.setString(5, username);
				
				

				int count = ps1.executeUpdate();

				if (count > 0) {
					out.println("1");
				}

			}

			else if (empolyee_id!= null
					&& type.equalsIgnoreCase("DateOfBirth")) {

				ps1 = connection
						.prepareStatement("INSERT INTO hclhrm_prod_others.hhcl_emp_profile_table(employee_id,dateofbirth,status,UPDATE_BY) VALUES(?,?,?,?)");
				ps1.setString(1, empolyee_id);
				ps1.setString(2, Date);
				ps1.setString(3, "1112");
				ps1.setString(4, username);
				
				System.out.println(""+ps1);

				int count = ps1.executeUpdate();

				if (count > 0) {
					out.println("1");
				}

			}

			else if (empolyee_id != null && type.equalsIgnoreCase("PFNO")) {

				ps1 = connection
						.prepareStatement("INSERT INTO hclhrm_prod_others.hhcl_emp_profile_table(employee_id,pf_no,status,UPDATE_BY) VALUES(?,?,?,?)");
				ps1.setString(1, empolyee_id);
				ps1.setString(2, NUMBER);
				ps1.setString(3, "1113");
				ps1.setString(4, username);

				int count = ps1.executeUpdate();

				if (count > 0) {
					out.println("1");
				}

			}

			/*
			 * else if(empolyee_id!=null&&type.equalsIgnoreCase("ACCOUNTNO")) {
			 * 
			 * 
			 * ps1 = connection.prepareStatement(
			 * "INSERT INTO hhcl_desk.hhcl_emp_profile_table(employee_id,account_no,status) VALUES(?,?,?)"
			 * ); ps1.setString(1, empolyee_id); ps1.setString(2, NUMBER);
			 * ps1.setString(3, "1114"); int count = ps1.executeUpdate();
			 * 
			 * if(count > 0) { out.println("Successfully Inserted.."); }
			 * 
			 * 
			 * 
			 * 
			 * 
			 * 
			 * }
			 */

			else if (empolyee_id != null && type.equalsIgnoreCase("PFUAN")) {

				ps1 = connection
						.prepareStatement("INSERT INTO hclhrm_prod_others.hhcl_emp_profile_table(employee_id,pf_uan_no,status,UPDATE_BY) VALUES(?,?,?,?)");
				ps1.setString(1, empolyee_id);
				ps1.setString(2, NUMBER);
				ps1.setString(3, "1114");
				ps1.setString(4, username);

				int count = ps1.executeUpdate();

				if (count > 0) {
					out.println("1");
				}

			}

			else if (empolyee_id != null&& acno != null
					&& type.equalsIgnoreCase("BANKDETAILS") ) {

				ps1 = connection
						.prepareStatement("INSERT INTO hclhrm_prod_others.hhcl_emp_profile_table(employee_id,bank_type,account_no,status,UPDATE_BY,lupdate) VALUES(?,?,?,?,?,?)");
				ps1.setString(1, empolyee_id);
				ps1.setString(2, Banktype);
				ps1.setString(3, acno);
				ps1.setString(4, "1115");
				ps1.setString(5, username);
				ps1.setTimestamp(6,new java.sql.Timestamp(new java.util.Date().getTime()));
				int count = ps1.executeUpdate();

				if (count > 0) {
					out.println("1");
				}

			}

			else if (empolyee_id != null && type.equalsIgnoreCase("PerAddress")) {

				ps1 = connection
						.prepareStatement("INSERT INTO hclhrm_prod_others.hhcl_emp_profile_table(employee_id,per_address1,per_address2,email_id,mobile_id,state,city,status,UPDATE_BY) VALUES(?,?,?,?,?,?,?,?,?)");
				ps1.setString(1, empolyee_id);
				ps1.setString(2, per_address1);
				ps1.setString(3, per_address2);
				ps1.setString(4, email_id);
				ps1.setString(5, mobile_id);
				ps1.setString(6, state);
				ps1.setString(7, city);
				ps1.setString(8, "1116");
				ps1.setString(9, username);

				int count = ps1.executeUpdate();

				if (count > 0) {
					out.println("1");
				}

			}
			
			else if(empolyee_id!=null&&type.equalsIgnoreCase("Network"))
			{
				
				
			 
				ps1 = connection
						.prepareStatement("INSERT INTO hclhrm_prod_others.hhcl_emp_profile_table(employee_id,E_mail,Issue_Type,Reason,status,UPDATE_BY) VALUES(?,?,?,?,?,?)");
				ps1.setString(1, empolyee_id);
				ps1.setString(2, email);
				ps1.setString(3, Issuetype);
				ps1.setString(4, request_msg);
				ps1.setString(5, "1117");
				ps1.setString(6, username);

				int count = ps1.executeUpdate();

				if (count > 0) {
					out.println("1");
				}
				
				
			}
			
			else if(empolyee_id!=null&&type.equalsIgnoreCase("Others"))
			{
				
				
			 
				ps1 = connection
						.prepareStatement("INSERT INTO hclhrm_prod_others.hhcl_emp_profile_table(employee_id,E_mail,Reason,status,UPDATE_BY) VALUES(?,?,?,?,?)");
				ps1.setString(1, empolyee_id);
				ps1.setString(2, email);
				ps1.setString(3, request_msg);
				ps1.setString(4, "1118");
				ps1.setString(5, username);

				int count = ps1.executeUpdate();

				if (count > 0) {
					out.println("1");
				}
				
				
			}

		}

		catch (SQLException e) {
			System.out.println(e.getMessage());

			out.println("0");
		} catch (Exception e) {
			System.out.println(e.getMessage());

			out.println("0");
		}

	}

}
