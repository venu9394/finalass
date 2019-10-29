package com.hhcdesk.update;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.hhcdesk.db.GetDbData;

 
public class Fetch_Data extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public Fetch_Data() {
        
    }

	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request,response);
		 
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String Empolyee_id=request.getParameter("EmployeeId");
		String type=request.getParameter("type");
		
		String Mode=request.getParameter("Mode");
		
		System.out.println("1111111111"+Mode+"----------"+type);
		
		
		HttpSession session = request.getSession(false);
		
		String locationid=request.getParameter("stateid");
		
		String data=request.getParameter("data");
		
		String Rejectid=request.getParameter("Rejectid");
		
		
		//System.out.println("data:::"+type+""+Mode);
		
		String username=(String)session.getAttribute("EMP_ID");
		
		String comment=request.getParameter("comment");
		
		
		
		 boolean rsflag=false;
		
		 
		
		  //String[] tempData=buf.toString().split("~");	
		   
		 
		// System.out.println("11111"+type);
		
		
		
		ArrayList<String> list4= new ArrayList<String>();
	      PreparedStatement ps1 = null;
	      PreparedStatement ps2 = null;
	      ResultSet rs=null;
	      Connection  connection =null;
	      
	      try {
	    	  connection=(java.sql.Connection)session.getAttribute("ConnectionObj");
			} catch (Exception e1) {
					e1.printStackTrace();
			}
	      
	      
	      
	      GetDbData Db=new GetDbData();
	      
	      String Location_id=null;
	     
	      PrintWriter out=response.getWriter();
	      
	      
	      
	         int i=0;
			 int i1=0;
	      try
	      {
	    	  
	    	  
	    	  
	    	  if(Mode!=null&&Mode.equalsIgnoreCase("reject"))
	    	  
	    	  
	    	  {
	    		  String status=null;
	    		  String status1=null;
	    		  
	    		 if(type.equalsIgnoreCase("Employee_approve")) {
	    			 
	    			 status="0000";
	    			 status1="1111";
	    		 }
	    		 
	    		 else if(type.equalsIgnoreCase("Dateofbirth_approve"))
	    		 {
	    			 status="0001";
	    			 status1="1112";
	    			 
	    		 }
	    		 else if(type.equalsIgnoreCase("PFNO_approve"))
	    		 {
	    			 status="0002";
	    			 status1="1113";
	    			 
	    		 }
	    		 
	    		 else if(type.equalsIgnoreCase("PFUAN_approve"))
	    		 {
	    			 status="0003";
	    			 status1="1114";
	    			 
	    		 }
	    		 else if(type.equalsIgnoreCase("Bank_approve"))
	    		 {
	    			 status="0004";
	    			 status1="1115";
	    			 
	    			  
	    			 
	    		 } else if(type.equalsIgnoreCase("Adresss_approve"))
	    		 {
	    			 
	    			 status="0005";
	    			 status1="1116";
	    			 
	    		 }
	    		 
	    		 else if(type.equalsIgnoreCase("Others_approve"))
	    		 {
	    			 
	    			 status="0011";
	    			 status1="1118";
	    			 
	    		 }
	    		
	    		 // System.out.println("REject");
	    		  connection.setAutoCommit(false);
	    		  System.out.println(""+data);
	    		  String Employeeid=data.toString().split("~")[0];
					 
	    		  
	    		  String r1=" update hclhrm_prod_others.hhcl_emp_profile_table  set hr_status='1004',comment_rej='"+comment+"',APPROVED_BY='"+username+"' where id='"+Rejectid+"'";
					ps2 = connection.prepareStatement(r1);
					//System.out.println(ps2);
					i=ps2.executeUpdate();
					if(i>0)
					{
						String r2="update hclhrm_prod_others.hhcl_emp_profile_table  set status='"+status+"' where employee_id='"+Employeeid+"' and status='"+status1+"' and hr_status=1002";
						ps2 = connection.prepareStatement(r2);
						
						System.out.println(ps2);
						ps2.executeUpdate();
						 
						
					}
	    		  
	    		  
					if(i>0)
					{
						connection.commit();
						list4.add("1");
					}
					else
					{
						connection.rollback();
						list4.add("0");
					}
	    		  
	    	  }
	    	  
	      
	      else    if(Empolyee_id!=null&&type.equalsIgnoreCase("EmployeeName"))
	      {
	    	
	    	      ps1 = connection.prepareStatement("SELECT FIRSTNAME,LASTNAME FROM hclhrm_prod.tbl_employee_primary where EMPLOYEESEQUENCENO='"+Empolyee_id+"' and status=1001");
	    	               
	    	              ResultSet rs1 = ps1.executeQuery();
	    	              if (rs1.next()) {
	    	                      list4.add(rs1.getString(1));
	    	                    
	    	                      
	    	                       
	    	                    	  list4.add(rs1.getString(2));
	    	                       
	    	              }
	    	      
	    	      
	    	    	  
	    	    	  
	    	    	  
	    	    	  if(list4.isEmpty())
	    	    	  {
	    	    		  list4.add("NODATA");
	                      
	                      
	    	    	  }
	    	    	  
	    	    	 
	    				
	    				
	    	    	   
	    	  
	      }
	      
	      else if(Empolyee_id!=null&&type.equalsIgnoreCase("DateOfBirth"))
	      {
	    	  
	    	      ps1 = connection.prepareStatement("SELECT DATEOFBIRTH FROM hclhrm_prod.tbl_employee_primary where EMPLOYEESEQUENCENO='"+Empolyee_id+"'");
	    	               
	    	              ResultSet rs1 = ps1.executeQuery();
	    	              if (rs1.next()) {
	    	                        
	    	                    	  list4.add(rs1.getString(1));
	    	                      
	    	              }
	    	     
	    	    
	    	    	  
	    	    	  
	    	    	  
	    	    	  if(list4.isEmpty())
	    	    	  {
	    	    		  list4.add("NODATA");
	                      
	                      
	    	    	  }
	    	    	  
	    	    	   
	    				
	    			 
	      }
	      
	      
	      
	      else if(Empolyee_id!=null&&type.equalsIgnoreCase("PFNO"))
	      {
	    	   
	    		  
	    		  String Query="SELECT B.PFNO FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A"
	    		  		+ " LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_OTHER_DETAIL B ON A.EMPLOYEEID=B.EMPLOYEEID WHERE A.EMPLOYEESEQUENCENO="+Empolyee_id;
	    	      ps1 = connection.prepareStatement(Query);
	    	              ResultSet rs1 = ps1.executeQuery();
	    	              if (rs1.next()) {
	    	                    	  list4.add(rs1.getString(1));
	    	              }	    	      
	    	    	  
	    	    	  if(list4.isEmpty())
	    	    	  {
	    	    		  list4.add("NODATA");
	    	    	  }
	    	    	  
	    	    	    
	    		  
	      }
	      
	      else if(Empolyee_id!=null&&type.equalsIgnoreCase("PFUAN"))
	      {
	    	  
	    		  
	    			
	    	  String Query="SELECT B.PFUAN FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A"
	    		  		+ " LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_OTHER_DETAIL B ON A.EMPLOYEEID=B.EMPLOYEEID WHERE A.EMPLOYEESEQUENCENO="+Empolyee_id;
	    	  
	    	      ps1 = connection.prepareStatement(Query);
	    	              ResultSet rs1 = ps1.executeQuery();
	    	              if (rs1.next()) {
	    	                    	  list4.add(rs1.getString(1));
	    	                      
	    	              }
	    	    
	    	    	  if(list4.isEmpty())
	    	    	  {
	    	    		  list4.add("NODATA");
	                      
	    	    	  }
	      }
	      
	      else if(Empolyee_id!=null&&type.equalsIgnoreCase("BANKDETAILS"))
	      { 
	    		
	    		  String Query=" SELECT C.BANKNAME,B.ACCOUNTNO,B.BANKID FROM"
	    		  				+ " HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A"
	    		  				+ " LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_OTHER_DETAIL B ON A.EMPLOYEEID=B.EMPLOYEEID"
	    		  				+ " LEFT JOIN HCLADM_PROD.TBL_BANK_DETAILS C ON B.BANKID=C.BANKID  WHERE A.EMPLOYEESEQUENCENO="+Empolyee_id;
	    		  
	    		  
	    		  
	    	      ps1 = connection.prepareStatement(Query);
	    	              ResultSet rs1 = ps1.executeQuery();
	    	              if (rs1.next()) {
	    	                       
	    	                    
	    	                      
	    	                      if(rs1.getString(1)==null||rs1.getString(1)==""||rs1.getString(1).length()==0||rs1.getString(2)==null||rs1.getString(2)==""||rs1.getString(2).length()==0)
	    	                      {
	    	                    	  list4.add("NODATA");
	    	                    	 
	    	                      }
	    	                      
	    	                      else
	    	                      {
	    	                    	  list4.add(rs1.getString(3));
	    	                    	  list4.add(rs1.getString(2));
	    	                      }
	    	              }
	    	      
	    	    	  
	    	    	  
	    	    	  
	    	    	  if(list4.isEmpty())
	    	    	  {
	    	    		  list4.add("0");
	                      
	                      
	    	    	  }
	    	    	  
	    	    	 
	    				
	    			 
	      }
	      
	      else if(Empolyee_id!=null&&type.equalsIgnoreCase("PerAddress"))
	      {
	    	  
	    	     
	    	 // String Query="select b.employeesequenceno,a.PERMANENTADDRESS, a.PERMANENTADDRESS2,a.PERMANENTZIP,if(a.PERMANENTLOCATIONID=0,'0',a.PERMANENTLOCATIONID) as city,ifnull(if(d.locationid=0,'0',d.locationid),'Select') as State,a.mobile,a.email from tbl_employee_personal_contact a left join tbl_employee_primary b on a.employeeid=b.employeeid left join hcllcm_prod.tbl_location c on a.PERMANENTLOCATIONID=c.locationid left join hcllcm_prod.tbl_location d on d.locationid=c.parent where b.employeesequenceno='"+Empolyee_id+"'";

	    	  String Query=" SELECT"
	    	  		+ " A.EMPLOYEESEQUENCENO,B.PERMANENTADDRESS,B.PERMANENTADDRESS2,B.PERMANENTZIP,B.PERMANENTLOCATIONID,D.LOCATIONID STATE,"
	    	  		+ " B.MOBILE,B.EMAIL FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A"
	    	  		+ " LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PERSONAL_CONTACT B ON A.EMPLOYEEID=B.EMPLOYEEID"
	    	  		+ " LEFT JOIN HCLLCM_PROD.TBL_LOCATION C ON B.PERMANENTLOCATIONID=C.LOCATIONID "
	    	  		+ " LEFT JOIN HCLLCM_PROD.TBL_LOCATION D ON C.PARENT=D.LOCATIONID WHERE"
	    	  		+ " A.EMPLOYEESEQUENCENO="+Empolyee_id;
	    	  
	    	  
	    	  
	    	  ps1 = connection.prepareStatement(Query);
	    	              ResultSet rs1 = ps1.executeQuery();
	    	              if (rs1.next()) {
	    	            	  list4.add(rs1.getString("PERMANENTADDRESS"));
	    	            	  list4.add(rs1.getString("PERMANENTADDRESS2"));
	    	            	  list4.add(rs1.getString("EMAIL"));
	    	            	  list4.add(rs1.getString("MOBILE"));
	    	            	  list4.add(rs1.getString("STATE"));
	    	            	  list4.add(rs1.getString("PERMANENTLOCATIONID"));
	    	            	      
	    	                     
	    	              }
	    	      
	    	      
	    	    	  
	    	    	  if(list4.isEmpty())
	    	    	  {
	    	    		  
	    	    		  
	    	    		  list4.add("0");
	                      
	                      
	                     // System.out.println("123123");
	    	    	  
	    			 
	    	      } 
	      }
	      
	      
	      else if(type.equalsIgnoreCase("Banktypes"))
	      {
	    	  
	    		  
	    		  String Query="SELECT * FROM hcladm_prod.tbl_bank_details";
	    		  
	    		  ps1 = connection.prepareStatement(Query);
	    		  
	              ResultSet rs1 = ps1.executeQuery();
	            
              while(rs1.next())
              {
            	  list4.add(rs1.getString(1)+"#"+rs1.getString(2)); 
              }
	    	  
               
	    	      
	      }
	      
	      
	      else if(type.equalsIgnoreCase("State"))
			{
				//String r = "SELECT LOCATIONID,STATE FROM hhcl_induction.STATES where status=1001 order by state asc";
				String r = "SELECT LOCATIONID,NAME STATE FROM HCLLCM_PROD.TBL_LOCATION WHERE PARENT=1 AND STATUS=1001 ORDER BY STATE ASC";
				
				 ps1 = connection.prepareStatement(r);
				 ResultSet rs1 = ps1.executeQuery();
				 
			 
				 list4.add("Select#--Select One--");

				while (rs1.next()) {
					list4.add(rs1.getString(1)+"#"+rs1.getString(2));
				}
				
			 
			}
			
			else if(type!=null && type.equalsIgnoreCase("City"))
			{
				 
					 
				//	String r="SELECT CITYID,NAME FROM hhcl_induction.CITIES WHERE PARENT=(SELECT LOCATIONID FROM hhcl_induction.STATES WHERE LOCATIONID='"+locationid+"')";
					
					//String r = "SELECT LOCATIONID,STATE FROM hhcl_induction.STATES where status=1001 order by state asc";
					
				String r="SELECT LOCATIONID,NAME FROM HCLLCM_PROD.TBL_LOCATION WHERE PARENT="+locationid;
					
					
					ps1 = connection.prepareStatement(r);
					
					System.out.println(ps1.toString());
					 ResultSet rs1 = ps1.executeQuery();
				 
					list4.add("Select#--Select One--");

					while (rs1.next()) {

				 
						list4.add(rs1.getString(1)+"#"+rs1.getString(2));
				 

					}
					
					 
					 
				 
			}
	      
	      
	      
			else if(Mode!=null&&type.equalsIgnoreCase("Employee_approve")&&Mode.equalsIgnoreCase("approved"))
			{
				 
				System.out.println("1");
					 
					 String Employeeid=data.toString().split("~")[0];
					 String FirstName=data.toString().split("~")[1];
					 //System.out.println(FirstName);
					 String LastName=data.toString().split("~")[2];
					 String CallName=FirstName.concat(" ").concat(LastName);
					// String id=tempData[3];
					 String id=data.toString().split("~")[3];
					 
					 
					 
					String EmployeeSearch="Select count(*) from hclhrm_prod.tbl_employee_primary A,hclhrm_prod.tbl_employee_primary_history B where A.employeesequenceno='"+Employeeid+"' and A.EMPLOYEEID=B.EMPLOYEEID";
					
					
					 ps1 = connection.prepareStatement(EmployeeSearch);
					 System.out.println(""+ps1);
					 connection.setAutoCommit(false);
					 rs=ps1.executeQuery();
					
					 if(rs.next())
					 {
						 if(rs.getInt(1)==0)
						 {
							 
							 String InsertBackup="insert into hclhrm_prod.tbl_employee_primary_history (EMPLOYEEID,FIRSTNAME,LASTNAME,CALLNAME,employeesequenceno) Select EMPLOYEEID,FIRSTNAME,LASTNAME,CALLNAME,employeesequenceno from hclhrm_prod.tbl_employee_primary where employeesequenceno='"+Employeeid+"'";
							 ps2 = connection.prepareStatement(InsertBackup);
							 i=ps2.executeUpdate();
							
						 }
						 
						 else if(rs.getInt(1)>0)
						 {
							 String backup="update hclhrm_prod.tbl_employee_primary A,hclhrm_prod.tbl_employee_primary_history B set B.FIRSTNAME=A.FIRSTNAME,B.LASTNAME=A.LASTNAME,B.CALLNAME=A.CALLNAME where A.employeesequenceno='"+Employeeid+"' and A.EMPLOYEEID=B.EMPLOYEEID";
							 ps2= connection.prepareStatement(backup);
							 
							 System.out.println("----"+ps2);
							 i=ps2.executeUpdate();
							 System.out.println("3");
						 }
						 
						 if(i>0)
							{
							    String r="update HCLHRM_PROD.tbl_employee_primary set FIRSTNAME='"+FirstName+"',LASTNAME='"+LastName+"',CALLNAME='"+CallName+"' where EMPLOYEESEQUENCENO='"+Employeeid+"'";
								ps2 = connection.prepareStatement(r);
								i=ps2.executeUpdate();
								String r1=" update hclhrm_prod_others.hhcl_emp_profile_table  set hr_status='1001',APPROVED_BY='"+username+"' where id='"+Integer.valueOf(id)+"'";
								ps2 = connection.prepareStatement(r1);
								i1=ps2.executeUpdate();
								
								 System.out.println("4");
								if(i1>0&&i>0)
								{
									String r2=" update hclhrm_prod_others.hhcl_emp_profile_table  set status='0000' where employee_id='"+Employeeid+"' and status='1111' and hr_status=1002";
									ps2 = connection.prepareStatement(r2);
									ps2.executeUpdate();
									 
									
								}
								
							 } 
						 
					 }
					
					 
					 
					if(i1>0&&i>0)
					{
						connection.commit();
						list4.add("1");
						//System.out.println("Yes");
					}
					else
					{
						connection.rollback();
						list4.add("0");
					}
				
			}
	      
	      
			else if(Mode!=null&&type.equalsIgnoreCase("Dateofbirth_approve")&&Mode.equalsIgnoreCase("approved"))
			{
				 
					 
					// System.out.println(data);
					 
					 String Employeeid=data.split("~")[0];
					 String Dateofbirth=data.split("~")[1];
					 String id=data.split("~")[2];
					 
					 ///t1.employee_id,t1.dateofbirth,t1.id
					 
					 
					 
					 
					 
					 
						String EmployeeSearch="Select count(*) from hclhrm_prod.tbl_employee_primary A,hclhrm_prod.tbl_employee_primary_history B where A.employeesequenceno='"+Employeeid+"' and A.EMPLOYEEID=B.EMPLOYEEID";
						
						
						 ps1 = connection.prepareStatement(EmployeeSearch);
						  
						 connection.setAutoCommit(false);
						 rs=ps1.executeQuery();
						
						 if(rs.next())
						 {
							 if(rs.getInt(1)==0)
							 {
								 
								 String InsertBackup="insert into hclhrm_prod.tbl_employee_primary_history (EMPLOYEEID,DATEOFBIRTH,employeesequenceno) Select EMPLOYEEID,DATEOFBIRTH,employeesequenceno from hclhrm_prod.tbl_employee_primary where employeesequenceno='"+Employeeid+"'";
								 ps2 = connection.prepareStatement(InsertBackup);
								 i=ps2.executeUpdate();
							 }
							 
							 else if(rs.getInt(1)>0)
							 {
								 String backup="update hclhrm_prod.tbl_employee_primary A,hclhrm_prod.tbl_employee_primary_history B set B.DATEOFBIRTH=A.DATEOFBIRTH where A.employeesequenceno='"+Employeeid+"' and A.EMPLOYEEID=B.EMPLOYEEID";
								 ps2= connection.prepareStatement(backup);
								 i=ps2.executeUpdate();
								 
							 }
							 
							 if(i>0)
								{
								  String r="update HCLHRM_PROD.tbl_employee_primary set dateofbirth='"+Dateofbirth+"' where EMPLOYEESEQUENCENO='"+Employeeid+"'";
								    ps2 = connection.prepareStatement(r);
									i=ps2.executeUpdate();
									String r1=" update hclhrm_prod_others.hhcl_emp_profile_table  set hr_status='1001',APPROVED_BY='"+username+"' where id='"+Integer.valueOf(id)+"'";
									ps2 = connection.prepareStatement(r1);
									i1=ps2.executeUpdate();
									if(i1>0&&i>0)
									{
										 
										String r2=" update hclhrm_prod_others.hhcl_emp_profile_table  set status='0001' where employee_id='"+Employeeid+"' and status='1112' and hr_status=1002";
										ps2 = connection.prepareStatement(r2);
										ps2.executeUpdate();
										 
										
									}
									
								 } 
							 
						 }
						 
						 
					 
					 
					if(i>0&&i1>0)
					{
						connection.commit();
						list4.add("1");
					}
					else
					{
						connection.rollback();
						list4.add("0");
					}
				 
					
					
			}
	      
	      
			else if(Mode!=null&&type.equalsIgnoreCase("PFNO_approve")&&Mode.equalsIgnoreCase("approved"))
			{
				 
					 
					// System.out.println(data);
					 
					 String Employeeid=data.split("~")[0];
					 String PFNO=data.split("~")[1];
					 String id=data.split("~")[2];
					
					 

						//String EmployeeSearch="Select count(*) from hclhrm_prod.tbl_employee_primary A,hclhrm_prod.tbl_employee_other_detail_history B where A.employeesequenceno='"+Employeeid+"' and A.EMPLOYEEID=B.EMPLOYEEID";
						
					  String EmployeeSearch= "SELECT IF(B.EMPLOYEEID =A.EMPLOYEEID, '1', '0') as existtable, IF(C.EMPLOYEEID=A.EMPLOYEEID, '1', '0') as history FROM hclhrm_prod.tbl_employee_primary A LEFT JOIN hclhrm_prod.tbl_employee_other_detail B ON  A.EMPLOYEEID=B.EMPLOYEEID LEFT JOIN hclhrm_prod.tbl_employee_other_detail_history C ON A.EMPLOYEEID=C.EMPLOYEEID where A.employeesequenceno='"+Employeeid+"'";	
					 
						 ps1 = connection.prepareStatement(EmployeeSearch);
						  
						 connection.setAutoCommit(false);
						 rs=ps1.executeQuery();
						
						 if(rs.next())
						 {
							 if(rs.getInt("existtable")==1&&rs.getInt("history")==0)
							 {
								 
								 String InsertBackup="insert into hclhrm_prod.tbl_employee_other_detail_history (EMPLOYEEID,PFNO) Select B.EMPLOYEEID,B.PFNO from hclhrm_prod.tbl_employee_primary A,tbl_employee_other_detail B where A.employeesequenceno='"+Employeeid+"' AND A.EMPLOYEEID=B.EMPLOYEEID";
								 ps2 = connection.prepareStatement(InsertBackup);
								 i=ps2.executeUpdate();
							 }
							 else if(rs.getInt("existtable")==0&&rs.getInt("history")==0)
							 {
						        String InsertBackup="insert into hclhrm_prod.tbl_employee_other_detail(EMPLOYEEID,PFNO) values((Select EMPLOYEEID from hclhrm_prod.tbl_employee_primary where employeesequenceno='"+Employeeid+"'),'"+PFNO+"')";
						        ps2 = connection.prepareStatement(InsertBackup);
						        i=ps2.executeUpdate();
							 }
							 
							 else if(rs.getInt("existtable")>0&&rs.getInt("history")>0)
							 {
								 String backup="update hclhrm_prod.tbl_employee_primary A,hclhrm_prod.tbl_employee_other_detail B,hclhrm_prod.tbl_employee_other_detail_history C set C.PFNO=B.PFNO where A.employeesequenceno='"+Employeeid+"' and A.EMPLOYEEID=B.EMPLOYEEID";
								 ps2= connection.prepareStatement(backup);
								 i=ps2.executeUpdate();
								 
							 }
							 
							 if(i>0)
								{
								 String r="update HCLHRM_PROD.tbl_employee_primary a,HCLHRM_PROD.tbl_employee_other_detail b set b.pfno='"+PFNO+"' where A.EMPLOYEESEQUENCENO='"+Employeeid+"' and a.employeeid=b.employeeid";
								 
								    ps2 = connection.prepareStatement(r);
									i=ps2.executeUpdate();
									String r1=" update hclhrm_prod_others.hhcl_emp_profile_table  set hr_status='1001',APPROVED_BY='"+username+"' where id='"+Integer.valueOf(id)+"'";
									ps2 = connection.prepareStatement(r1);
									i1=ps2.executeUpdate();
									if(i1>0&&i>0)
									{
										 
										String r2=" update hclhrm_prod_others.hhcl_emp_profile_table  set status='0002' where employee_id='"+Employeeid+"' and status='1113' and hr_status=1002";
										ps2 = connection.prepareStatement(r2);
										ps2.executeUpdate();
										 
										
									}
									
								 } 
							 
						 }
						 
						 
					 
					 
					if(i>0&&i1>0)
					{
						connection.commit();
						list4.add("1");
					}
					else
					{
						connection.rollback();
						list4.add("0");
					}
					 
				 
			}
			else if(Mode!=null&&type.equalsIgnoreCase("PFUAN_approve")&&Mode.equalsIgnoreCase("approved"))
			{
				 
					 
					// System.out.println(data);
					 
					 String Employeeid=data.split("~")[0];
					 String PFUAN=data.split("~")[1];
					 String id=data.split("~")[2];
					 
				  
						//String EmployeeSearch="Select count(*) from hclhrm_prod.tbl_employee_primary A,hclhrm_prod.tbl_employee_other_detail_history B where A.employeesequenceno='"+Employeeid+"' and A.EMPLOYEEID=B.EMPLOYEEID";
						
					  String EmployeeSearch= "SELECT IF(B.EMPLOYEEID =A.EMPLOYEEID, '1', '0') as existtable, IF(C.EMPLOYEEID=A.EMPLOYEEID, '1', '0') as history FROM hclhrm_prod.tbl_employee_primary A LEFT JOIN hclhrm_prod.tbl_employee_other_detail B ON  A.EMPLOYEEID=B.EMPLOYEEID LEFT JOIN hclhrm_prod.tbl_employee_other_detail_history C ON A.EMPLOYEEID=C.EMPLOYEEID where A.employeesequenceno='"+Employeeid+"'";	
					 
						 ps1 = connection.prepareStatement(EmployeeSearch);
						  
						 connection.setAutoCommit(false);
						 rs=ps1.executeQuery();
						
						 if(rs.next())
						 {
							 if(rs.getInt("existtable")==1&&rs.getInt("history")==0)
							 {
								 
								 String InsertBackup="insert into hclhrm_prod.tbl_employee_other_detail_history (EMPLOYEEID,PFUAN) Select B.EMPLOYEEID,B.PFUAN from hclhrm_prod.tbl_employee_primary A,tbl_employee_other_detail B where A.employeesequenceno='"+Employeeid+"' AND A.EMPLOYEEID=B.EMPLOYEEID";
								 ps2 = connection.prepareStatement(InsertBackup);
								 i=ps2.executeUpdate();
							 }
							 
							 else if(rs.getInt("existtable")==0&&rs.getInt("history")==0)
							 {
						        String InsertBackup="insert into hclhrm_prod.tbl_employee_other_detail(EMPLOYEEID,PFUAN) values((Select EMPLOYEEID from hclhrm_prod.tbl_employee_primary where employeesequenceno='"+Employeeid+"'),'"+PFUAN+"')";
						        ps2 = connection.prepareStatement(InsertBackup);
						        i=ps2.executeUpdate();
							 }
							 
							 else if(rs.getInt("existtable")>0&&rs.getInt("history")>0)
							 {
								 String backup="update hclhrm_prod.tbl_employee_primary A,hclhrm_prod.tbl_employee_other_detail B,hclhrm_prod.tbl_employee_other_detail_history C set C.PFUAN=B.PFUAN where A.employeesequenceno='"+Employeeid+"' and A.EMPLOYEEID=B.EMPLOYEEID";
								 ps2= connection.prepareStatement(backup);
								 i=ps2.executeUpdate();
								 
							 }
							 
							 if(i>0)
								{
								 String r="update HCLHRM_PROD.tbl_employee_primary a,HCLHRM_PROD.tbl_employee_other_detail b set b.PFUAN='"+PFUAN+"' where A.EMPLOYEESEQUENCENO='"+Employeeid+"' and a.employeeid=b.employeeid";
								 
								    ps2 = connection.prepareStatement(r);
									i=ps2.executeUpdate();
									String r1=" update hclhrm_prod_others.hhcl_emp_profile_table  set hr_status='1001',APPROVED_BY='"+username+"' where id='"+Integer.valueOf(id)+"'";
									ps2 = connection.prepareStatement(r1);
									i1=ps2.executeUpdate();
									if(i1>0&&i>0)
									{
										 
										String r2=" update hclhrm_prod_others.hhcl_emp_profile_table  set status='0003' where employee_id='"+Employeeid+"' and status='1114' and hr_status=1002 ";
										ps2 = connection.prepareStatement(r2);
										ps2.executeUpdate();
										 
										
									}
									
								 } 
							 
						 }
						 
						 
					 
					 
					if(i>0&&i1>0)
					{
						connection.commit();
						list4.add("1");
					}
					else
					{
						connection.rollback();
						list4.add("0");
					}
				 
			}
	      
	      
			else if(Mode!=null&&type.equalsIgnoreCase("Bank_approve")&&Mode.equalsIgnoreCase("approved"))
			{
				 
					 
					// System.out.println(data);
					 
					 String Employeeid=data.split("~")[0];
					 String BANKID=data.split("~")[1];
					 String id=data.split("~")[2];
					 String acno=data.split("~")[3];
					 
					 
					 
					 
					 
					 
					// String EmployeeSearch="Select count(*) from hclhrm_prod.tbl_employee_primary A,hclhrm_prod.tbl_employee_other_detail_history B where A.employeesequenceno='"+Employeeid+"' and A.EMPLOYEEID=B.EMPLOYEEID";
						
					 String EmployeeSearch= "SELECT IF(B.EMPLOYEEID =A.EMPLOYEEID, '1', '0') as existtable, IF(C.EMPLOYEEID=A.EMPLOYEEID, '1', '0') as history FROM hclhrm_prod.tbl_employee_primary A LEFT JOIN hclhrm_prod.tbl_employee_other_detail B ON  A.EMPLOYEEID=B.EMPLOYEEID LEFT JOIN hclhrm_prod.tbl_employee_other_detail_history C ON A.EMPLOYEEID=C.EMPLOYEEID where A.employeesequenceno='"+Employeeid+"'";	
					 
					 ps1 = connection.prepareStatement(EmployeeSearch);
					  
					 connection.setAutoCommit(false);
					 rs=ps1.executeQuery();
					
					 if(rs.next())
					 {
						 if(rs.getInt("existtable")==1&&rs.getInt("history")==0)
						 {
							 
							 String InsertBackup="insert into hclhrm_prod.tbl_employee_other_detail_history (EMPLOYEEID,BANKID,ACCOUNTNO) Select B.EMPLOYEEID,B.BANKID,B.ACCOUNTNO from hclhrm_prod.tbl_employee_primary A,hclhrm_prod.tbl_employee_other_detail B where A.employeesequenceno='"+Employeeid+"' AND A.EMPLOYEEID=B.EMPLOYEEID";
							 ps2 = connection.prepareStatement(InsertBackup);
							 i=ps2.executeUpdate();
						 }
						 else if(rs.getInt("existtable")==0&&rs.getInt("history")==0)
								 {
							 String InsertBackup="insert into hclhrm_prod.tbl_employee_other_detail(EMPLOYEEID,BANKID,ACCOUNTNO) values((Select EMPLOYEEID from hclhrm_prod.tbl_employee_primary where employeesequenceno='"+Employeeid+"'),'"+BANKID+"','"+acno+"')";
							 ps2 = connection.prepareStatement(InsertBackup);
							 i=ps2.executeUpdate();
								 }
						 else if(rs.getInt("existtable")>0&&rs.getInt("history")>0)
						 {
							 String backup="update hclhrm_prod.tbl_employee_primary A,hclhrm_prod.tbl_employee_other_detail B,hclhrm_prod.tbl_employee_other_detail_history C set C.BANKID=B.BANKID,C.ACCOUNTNO=B.ACCOUNTNO where A.employeesequenceno='"+Employeeid+"' and A.EMPLOYEEID=B.EMPLOYEEID";
							 ps2= connection.prepareStatement(backup);
							 i=ps2.executeUpdate();
							 
						 }
						 
						 if(i>0)
							{
							 String r="update HCLHRM_PROD.tbl_employee_primary a,HCLHRM_PROD.tbl_employee_other_detail b set b.BANKID='"+BANKID+"', b.ACCOUNTNO='"+acno+"' where A.EMPLOYEESEQUENCENO='"+Employeeid+"' and a.employeeid=b.employeeid";
								
							    ps2 = connection.prepareStatement(r);
								i=ps2.executeUpdate();
								String r1=" update hclhrm_prod_others.hhcl_emp_profile_table  set hr_status='1001',APPROVED_BY='"+username+"' where id='"+Integer.valueOf(id)+"'";
								ps2 = connection.prepareStatement(r1);
								i1=ps2.executeUpdate();
								if(i1>0&&i>0)
								{
									 
									String r2=" update hclhrm_prod_others.hhcl_emp_profile_table  set status='0004' where employee_id='"+Employeeid+"' and status='1115' and hr_status=1002";
									ps2 = connection.prepareStatement(r2);
									ps2.executeUpdate();
									 
									
								}
								
							 } 
						 
					 }
					 
					 
				 
				 
				if(i>0&&i1>0)
				{
					connection.commit();
					list4.add("1");
				}
				else
				{
					//connection.rollback();
					list4.add("0");
				}
					 
				
				 
			}
	      
	      
			else if(Mode!=null&&type.equalsIgnoreCase("Adresss_approve")&&Mode.equalsIgnoreCase("approved"))
			{
				 
					 
					 //System.out.println(data);
					 
					 
					 
					 String Employeeid=data.split("~")[0];
					 String Peraddress1=data.split("~")[1];
					 String Peraddress2=data.split("~")[2];
					 String Mobileid=data.split("~")[3];
					 String Emailid=data.split("~")[4];
					 String Stateid=data.split("~")[5];
					 String Cityid=data.split("~")[6];
					 String id=data.split("~")[7];
					 
					 
					 
					    rs=null;
					    ps1=null;
					    
					    
					    
					    
					    
					    try {
							ps1=connection.prepareStatement("select c.locationid from HCLHRM_PROD.tbl_employee_personal_contact a join HCLHRM_PROD.tbl_employee_primary b on a.employeeid=b.employeeid join hcllcm_prod.tbl_location c on c.locationid=? join hcllcm_prod.tbl_location d on d.locationid=? where b.employeesequenceno=?");
							ps1.setString(1, Cityid);
							ps1.setString(2, Stateid);
							ps1.setString(3, Employeeid);
							//ps1.execute();
							 
							
							rs=(ResultSet)Db.FetchDataPrepare_2(ps1, "Location_id",rs,connection);
							
							if(rs.next()){

								
								Location_id=rs.getString(1);
								
								
								rsflag=true;
								
								
								
							}
						} catch (SQLException e) {
							
							e.printStackTrace();
						}
					    
					    
					    
					    
					    
					    
					   // String EmployeeSearch="Select count(*) from hclhrm_prod.tbl_employee_primary A,hclhrm_prod.tbl_employee_personal_contact_history B where A.employeesequenceno='"+Employeeid+"' and A.EMPLOYEEID=B.EMPLOYEEID";
						
					    String EmployeeSearch= "SELECT IF(B.EMPLOYEEID =A.EMPLOYEEID, '1', '0') as existtable, IF(C.EMPLOYEEID=A.EMPLOYEEID, '1', '0') as history FROM hclhrm_prod.tbl_employee_primary A LEFT JOIN hclhrm_prod.tbl_employee_personal_contact B ON  A.EMPLOYEEID=B.EMPLOYEEID LEFT JOIN hclhrm_prod.tbl_employee_personal_contact_history C ON A.EMPLOYEEID=C.EMPLOYEEID where A.employeesequenceno='"+Employeeid+"'";	
						
						 ps1 = connection.prepareStatement(EmployeeSearch);
						  
						 connection.setAutoCommit(false);
						 rs=ps1.executeQuery();
						
						 if(rs.next())
						 {
							 if(rs.getInt("existtable")==1&&rs.getInt("history")==0)
							 {
								 
								 String InsertBackup="insert into hclhrm_prod.tbl_employee_personal_contact_history (EMPLOYEEID,EMAIL,MOBILE,PERMANENTADDRESS,PERMANENTADDRESS2,PERMANENTLOCATIONID) Select B.EMPLOYEEID,B.EMAIL,B.MOBILE,B.PERMANENTADDRESS,B.PERMANENTADDRESS2,B.PERMANENTLOCATIONID from hclhrm_prod.tbl_employee_primary A,tbl_employee_personal_contact B where A.employeesequenceno='"+Employeeid+"' AND A.EMPLOYEEID=B.EMPLOYEEID";
								 ps2 = connection.prepareStatement(InsertBackup);
								 i=ps2.executeUpdate();
							 }
							 
							 else if(rs.getInt("existtable")==0&&rs.getInt("history")==0)
							 {
								 
								 
								//System.out.println(); 
						       String InsertBackup="insert into hclhrm_prod.tbl_employee_personal_contact(EMPLOYEEID,EMAIL,MOBILE,PERMANENTADDRESS,PERMANENTADDRESS2,PERMANENTLOCATIONID) values((Select EMPLOYEEID from hclhrm_prod.tbl_employee_primary where employeesequenceno='"+Employeeid+"'),'"+Emailid+"','"+Mobileid+"','"+Peraddress1+"','"+Peraddress2+"','"+id+"')";
						       ps2 = connection.prepareStatement(InsertBackup);
						       i=ps2.executeUpdate();
							 }
							 
							 else if(rs.getInt("existtable")>0&&rs.getInt("history")>0)
							 {
								 String backup="update hclhrm_prod.tbl_employee_primary A,hclhrm_prod.tbl_employee_personal_contact B,hclhrm_prod.tbl_employee_personal_contact_history C set C.EMAIL=B.EMAIL,C.MOBILE=B.MOBILE,C.PERMANENTADDRESS=B.PERMANENTADDRESS,C.PERMANENTADDRESS2=B.PERMANENTADDRESS2,C.PERMANENTLOCATIONID=B.PERMANENTLOCATIONID where A.employeesequenceno='"+Employeeid+"' and A.EMPLOYEEID=B.EMPLOYEEID";
								 ps2= connection.prepareStatement(backup);
								 i=ps2.executeUpdate();
								 
							 }
							 
							 if(rsflag)
								{
								
									String r="update HCLHRM_PROD.tbl_employee_primary a,HCLHRM_PROD.tbl_employee_personal_contact b set b.EMAIL='"+Emailid+"',b.MOBILE='"+Mobileid+"',b.PERMANENTADDRESS='"+Peraddress1+"',b.PERMANENTADDRESS2='"+Peraddress2+"',b.PERMANENTLOCATIONID='"+Location_id+"' where A.EMPLOYEESEQUENCENO='"+Employeeid+"' and a.employeeid=b.employeeid";
									 
									
								//	System.out.println(r+"---------------------->");
									ps1 = connection.prepareStatement(r);
									 
									connection.setAutoCommit(false);
									 
									 i=ps1.executeUpdate();
									
									
									
									
								}
							 
							 if(i>0)
								{
									
									 
									String r1=" update hclhrm_prod_others.hhcl_emp_profile_table  set hr_status='1001',hrms_process_date=NOW(),APPROVED_BY='"+username+"' where id='"+Integer.valueOf(id)+"'";
									ps1 = connection.prepareStatement(r1);
									 i1=ps1.executeUpdate();
									if(i1>0)
									{
										String r2="update hclhrm_prod_others.hhcl_emp_profile_table  set status='0005' where employee_id='"+Employeeid+"' and status='1116' and hr_status=1002";
										ps1 = connection.prepareStatement(r2);
										System.out.println(ps1);
										ps1.executeUpdate();
										
									 
									 
									}
									
									
									
								}
							 
						 }
						 
						 
					 
					 
					if(i>0&&i1>0)
					{
						connection.commit();
						list4.add("1");
					}
					else
					{
						//connection.rollback();
						list4.add("0");
					}
					    
					 
				
				 
			}
	    	  
	    	  
	    	  
			else if(Mode!=null&&type.equalsIgnoreCase("Others_approve")&&Mode.equalsIgnoreCase("approved"))
			{
				 
					 
					// System.out.println(data);
					 
					 String Employeeid=data.split("~")[0];
					 String E_mail=data.split("~")[1];
					 String id=data.split("~")[2];
					 String Reason=data.split("~")[3];
						
					 
					          connection.setAutoCommit(false);
								String r1=" update hclhrm_prod_others.hhcl_emp_profile_table  set hr_status='1001',E_mail='"+E_mail+"',Reason='"+Reason+"',APPROVED_BY='"+username+"' where id='"+Integer.valueOf(id)+"'";
								ps2 = connection.prepareStatement(r1);
								i1=ps2.executeUpdate();
								if(i1>0)
								{
									 
									String r2=" update hclhrm_prod_others.hhcl_emp_profile_table  set status='0011' where employee_id='"+Employeeid+"' and status='1118' and hr_status=1002";
									ps2 = connection.prepareStatement(r2);
									ps2.executeUpdate();
									 
									
								}
								
							 
					 
				 
				 
				if(i1>0)
				{
					connection.commit();
					list4.add("1");
				}
				else
				{
					//connection.rollback();
					list4.add("0");
				}
					 
				
				 
	      }	
	
	      
	      
	      
	      
	      
			else if(type.equalsIgnoreCase("EMPIDCHECK"))
					{
				
				
				    
				ps1 = connection.prepareStatement("SELECT  count(*) from  hclhrm_prod.tbl_employee_primary where EMPLOYEESEQUENCENO='"+Empolyee_id+"' and status=1001");
	               
	              ResultSet rs1 = ps1.executeQuery();
	              if(rs1.next()) {
	                      //data1 = rs1.getString("Stockist_Name");
	                    if(rs1.getInt(1)==0)
	                    
	                    {
	                    list4.add("0");
	                    } 
	                    
	                    else
	                    {
	                    	 list4.add("1");
	                    }
	                    
	                   }
	       
					}
	      
	      
	    }
	      
	      catch (Exception e) {
				e.printStackTrace();
				try {
					connection.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				list4.add("Unable to Process...");
			} 

	      
	      
	      finally
	      {
	    	    Gson gson = new Gson();
				String messages = gson.toJson(list4);
				out.println(messages);
	      }
  	       
	   
	}
		 
	 

}
