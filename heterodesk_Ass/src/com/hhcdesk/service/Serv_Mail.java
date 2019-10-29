package com.hhcdesk.service;
import java.beans.PropertyVetoException;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.sql.DataSource;

import com.hhcdesk.db.DataSource_Cls;
import com.hhcdesk.db.GetDbData;

@SuppressWarnings("serial")
public class Serv_Mail extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session=request.getSession(true);
		PrintWriter out = response.getWriter();
		String id=request.getParameter("AUTHCODE");
		Map rmap=new HashMap();
		String mysessionid=session.getId();
		System.out.println(mysessionid+"--> Session ID");
		java.sql.Connection con=null;
		ServletContext c = getServletContext();
		System.out.println("GetParameter"+ c.getAttribute("Venu"));
		DataSource dataSource=null;
		
		ResourceBundle bundle_info =(ResourceBundle)(c.getAttribute("bundle"));
		
		//Connection con = (Connection) Dbconnection.getDBConection();
		
		/*try {
			dataSource=(DataSource)(c.getAttribute("dataSource"));

		} catch (Exception e) {
			
			e.printStackTrace();
		}
		try {
			con =dataSource.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		/*DataSource_Cls ConnObj=null;
		try {
			 ConnObj=new DataSource_Cls();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (PropertyVetoException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			//conn =dataSource.getConnection();
			con=ConnObj.getConnection();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		
		 try {
	           	Context initContext = new InitialContext();
	           	Context envContext = (Context) initContext.lookup("java:comp/env");
	           	DataSource ds = (DataSource) envContext.lookup("jdbc/HHCL_DESK");
	           	con = ds.getConnection();
	       } catch (SQLException  SQLEx) {
	           	
	    	   System.err.println(SQLEx);
	    	   System.out.println("There was a problem with the database connection.");
	    	   System.out.println(SQLEx);
	    	   
	    	   System.out.println(SQLEx.getCause());	
	           request.setAttribute("Reason", SQLEx.getCause());
	    	   
	       } catch (NamingException ex) {
	            System.err.println(ex);
	            request.setAttribute("Reason", ex);
	       }
		 
		
		/*try {
			//conn =dataSource.getConnection();
			con=(java.sql.Connection)session.getAttribute("ConnectionObj");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
				e1.printStackTrace();
		}*/
		String valid=null;
		int Empid=0;
		int RID=0;
		ResultSet rs=null;
		StringBuffer rb=new StringBuffer();
		try{
			System.out.println("Setp:1");
			//String qry="select employeeid from hclhrm_prod.tbl_employee_primary where md5(employeesequenceno)=trim('"+id+"')";
			//String qry="select count(*),employeeid from hclhrm_prod.tbl_employee_primary where md5(concat(rid,'#',randomid))=''";
			//	String qry="SELECT count(*),employeeid,rid FROM hclhrm_prod_others.tbl_emp_attn_req where md5(concat(rid,'#',randomid))='"+id+"'";
			/*rb.append("SELECT count(*),A.employeeid,A.rid,B.CALLNAME,DATE_FORMAT(A.REQ_DATE,'%d-%m-%Y') FROM_DATE,A.TO_DATE,A.MESSAGE");
			rb.append(" FROM HCLHRM_PROD_OTHERS.tbl_emp_attn_req A");
			rb.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY B ON A.EMPLOYEEID=B.EMPLOYEESEQUENCENO");
			rb.append(" where A.FLAG='P' AND md5(concat(A.rid,'#',A.randomid))='"+id+"'");*/
			
			
			rb.append(" SELECT COUNT(*),A.EMPLOYEEID,A.RID,B.CALLNAME NAME,");
			rb.append(" IF(REQ_TYPE='AR',DATE_FORMAT(A.REQ_DATE,'%d-%m-%Y'),DATE_FORMAT(A.FROM_DATE,'%d-%m-%Y'))FROM_DATE,");
			rb.append(" IF(REQ_TYPE='AR',DATE_FORMAT(A.REQ_DATE,'%d-%m-%Y'),DATE_FORMAT(A.TO_DATE,'%d-%m-%Y'))TO_DATE,");
			rb.append(" UPPER(A.MESSAGE)MESSAGE,A.SUBJECT,");
			rb.append(" IF(REQ_TYPE='AR',A.FROM_DATE,'0') FIN,");
			rb.append(" IF(REQ_TYPE='AR',A.TO_DATE,'0') FIN,");
			rb.append(" IF(REQ_TYPE='AR',A.TOTA_HOURS,'0')NETH,");
			rb.append(" A.REQ_TYPE,A.RANDOMID,IF(FLAG='P','Pending',if(FLAG='A','APPROVED','REJECTED')) FLAG");
			//rb.append(" A.REQ_TYPE,A.RANDOMID,");
			//rb.append(" IF(FLAG='P','Pending',if(FLAG='A','APPROVED',if(FLAG='SC','SELF CANCELED BY EMPLOYEE:',IF(FLAG='MC','CANCELED BY YOUR MANAGER',IF(FLAG='R','REJECTED','UNKNOWN'))))) FLAG" );
			rb.append(" FROM ");
			rb.append(" HCLHRM_PROD_OTHERS.TBL_EMP_ATTN_REQ A");
			rb.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY B ON A.EMPLOYEEID=B.EMPLOYEESEQUENCENO");
			rb.append(" WHERE A.FLAG='P' AND MD5(concat(A.rid,'#',A.randomid))='"+id+"'");
			

			System.out.println(rb.toString());
			PreparedStatement ps=con.prepareStatement(rb.toString());
			//System.out.println(ps);

			try{
				rs=ps.executeQuery();	
			}catch(Exception e){
				e.printStackTrace();
			}
			GetDbData DataObj=new GetDbData();
			//System.out.println(rs);
			String FaildFlag="NO_DATA";
			boolean authflg=false;
			System.out.println("Setp:2");
			while(rs.next()){
				valid=rs.getString(1);
				Empid=rs.getInt(2);
				RID=rs.getInt(3);
				rmap.put("_empid", Empid);
				rmap.put("_empname", rs.getString(4));//Name
				rmap.put("_from", rs.getString(5));// from date
				rmap.put("_to", rs.getString(6));//to date
				rmap.put("_message", rs.getString(7));//Message
				rmap.put("_subject", rs.getString(8));//subject
				rmap.put("_fin", rs.getString(9));//FIN
				rmap.put("_lout", rs.getString(10));//lout
				rmap.put("_neth", rs.getString(11));//Net
				rmap.put("_type", rs.getString(12));//req_type
				
				rmap.put("_rid", rs.getString(3));//RID
				rmap.put("_randomid", rs.getString(13));//RANDOMID	
				rmap.put("_Flag", rs.getString(13));//Flag
				
			}

			System.out.println("Setp:3 :::->" +valid);
			
			try{
				
			if(valid.equalsIgnoreCase("1")){
				System.out.println(valid+"-->Valid ID?");
				System.out.println(Empid+"-->Employee ID");
				System.out.println(RID+"-->Requested ID");
				session.setAttribute("mymap", rmap);

				RequestDispatcher rd=request.getRequestDispatcher("result_mod.jsp");
				rd.forward(request,response);
			}
         	}catch(Exception g){
				g.printStackTrace();
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Link is Expired...!' );");
				//out.println("location='loan.jsp';");
				out.println("window.close()");
				out.println("</script>");
				System.out.println("Url Re Hit");
				con.close();
				ps.close();
				rs.close();
			}

			try{
				if(valid.equalsIgnoreCase("0")){
					//rs=null;
					try {
						rs=(ResultSet)DataObj.FetchData("select IF(FLAG='P','Pending',if(FLAG='A','APPROVED',if(FLAG='SC','SELF CANCELLED BY EMPLOYEE:',IF(FLAG='MC','CANCELLED BY YOUR MANAGER',IF(FLAG='R','REJECTED',IF(FLAG='AM','Approved By Admin', FLAG)))))),approvedname FLAG from hclhrm_prod_others.tbl_emp_attn_req WHERE MD5(concat(rid,'#',randomid))='"+id+"' ", "Status of leave", rs ,con);
						if(rs.next()){
							FaildFlag=rs.getString(1) +" BY "+ rs.getString(2);
							System.out.println("FaildFlag:::"+FaildFlag);
							authflg=true;
						    String alt="alert('Link is Expired...!   "+FaildFlag+" ' );";
						   // alt.concat(FaildFlag).concat("");
							out.println("<script type=\"text/javascript\">");
							out.println(alt);
							//out.println("alert('Link is Expired...! "+FaildFlag+"' )");
							out.println("location='http://heterohealthcare.com/';");
							out.println("</script>");
							
						}  
					}catch(Exception Er){
						System.out.println("Exception At Er::"+Er);
						
					}finally{
					
					
					
					
					
					//session.invalidate();
					con.close();
					ps.close();
					rs.close();
					}
					
					//RequestDispatcher rd=request.getRequestDispatcher("fail.jsp");
					//rd.forward(request,response);
				}
				
				
				
			}catch(Exception g1){
				g1.printStackTrace();
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Link is Expired...!' );");
				//out.println("location='loan.jsp';");
				out.println("window.close()");
				out.println("</script>");
				System.out.println("Url Re Hit");
				System.out.println("Url Re Hit");
				session.invalidate();
				con.close();
				ps.close();
				rs.close();
			}
			
		}
		catch(Exception e2)
		{
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Connection Problem Please Contact Administration...!');");
			//out.println("location='loan.jsp';");
			out.println("location='http://heterohealthcare.com/';");
			out.println("</script>");
			//e2.printStackTrace();
			//RequestDispatcher rd=request.getRequestDispatcher("fail.jsp");
			//rd.forward(request,response);
			session.invalidate();
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}