package com.hhcdesk.update;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Servlet implementation class Data
 */
public class Data extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet~HttpServlet()
     */
    public Data() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet~doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doPost(request,response);
	}

	/**
	 * @see HttpServlet~doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String type=request.getParameter("type");
		HttpSession session = request.getSession(false);
	   // System.out.print("123456");
	    PrintWriter out=response.getWriter();
	    JSONObject obj=new JSONObject();
	    
	    StringBuffer Bank_Details=new StringBuffer();
        
       
        
        
	    try
	    {
	        Connection conn=null;
	        
	        try {
	        	conn=(java.sql.Connection)session.getAttribute("ConnectionObj");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
					e1.printStackTrace();
			}
	      
	        ResultSet rs=null;
	        Statement stm1=conn.createStatement();
	 
	        JSONArray list = new JSONArray();
	        
	        
	        if(type.equalsIgnoreCase("EmployeeName"))
	        {
	        	
	        	String sql  = "SELECT t1.employee_id,t1.first_name,t1.last_name,t1.id FROM hclhrm_prod_others.hhcl_emp_profile_table t1 WHERE t1.lupdate = (SELECT MAX(t2.lupdate) FROM hclhrm_prod_others.hhcl_emp_profile_table t2 WHERE t2.employee_id = t1.employee_id and status=1111 and hr_status=1002)";
	    	    
	        rs=stm1.executeQuery(sql);
	        
	        if(!rs.isBeforeFirst())
	        {
	        	     obj=new JSONObject();
		            obj.put("EMPLOYEEID", "NODATA");
		            obj.put("FIRSTNAME", "NODATA");
		            obj.put("LASTNAME", "NODATA");
		            obj.put("BUTTON","none");
		            obj.put("BUTTON1","none");
		            
		            obj.put("td1", " ");
		            obj.put("td2", " ");
		            obj.put("td3", " ");
		            obj.put("td4", "none");
		            obj.put("td5", "none");
		            obj.put("td6", "none");
		            obj.put("td7", "none");
		            
		            list.add(obj);
	        }
	        
	        
	        while(rs.next())
	        {
	            obj=new JSONObject();
	            obj.put("EMPLOYEEID", rs.getString("employee_id"));
	            obj.put("FIRSTNAME", rs.getString("first_name"));
	            obj.put("LASTNAME", rs.getString("last_name"));
	            obj.put("id", rs.getString("id"));
	            obj.put("BUTTON", " ");
	            obj.put("BUTTON1", " ");
	            
	            obj.put("td1", " ");
	            obj.put("td2", " ");
	            obj.put("td3", " ");
	            obj.put("td4", "none");
	            obj.put("td5", "none");
	            obj.put("td6", "none");
	            obj.put("td7", "none");
	            
	            
	            
	            String data1=rs.getString("employee_id")+"~"+rs.getString("first_name")+"~"+rs.getString("last_name")+"~"+rs.getString("id");
	            obj.put("Data", data1);
	            
	            obj.put("alt", "Employee_approve");
	            
	           // obj.put("BUTTON", "<input type='button' class='btn btn-primary' alt='Employee_approve' id='"+rs.getString("id")+"' value='Approve' name='"+data1+"' onclick='Approve(this)'><span id='"+rs.getString("id").concat("H")+"'></span>");
	 
	           // obj.put("BUTTON1", "<input type='button' class='btn btn-danger' alt='Employee_approve' id='"+rs.getString("id")+"' value='Reject' name='"+data1+"' onclick='Reject(this)'><span id='"+rs.getString("id").concat("R")+"'></span>");
	       	 
	            
	            list.add(obj);
	        }
	        
	        }
	        
	        else  if(type.equalsIgnoreCase("DateOfBirth"))
	        {
	         
	        String sql  = "SELECT t1.employee_id,t1.dateofbirth,t1.id FROM hclhrm_prod_others.hhcl_emp_profile_table t1 WHERE t1.lupdate = (SELECT MAX(t2.lupdate) FROM hclhrm_prod_others.hhcl_emp_profile_table t2 WHERE t2.employee_id = t1.employee_id and status=1112 and hr_status=1002)";
 	        rs=stm1.executeQuery(sql);
	        
	        if(!rs.isBeforeFirst())
	        {
	        	     obj=new JSONObject();
		            obj.put("EMPLOYEEID", "NODATA");
		            
		            
		            
		            obj.put("LASTNAME", "NODATA");
		            
		           // obj.put("DATEOFBIRTH", "NODATA");
		           
		            obj.put("BUTTON","none");
		            obj.put("BUTTON1","none");
		            
		            obj.put("td1", " ");
		            obj.put("td2", " ");
		            obj.put("td3", "none");
		            obj.put("td4", "none");
		            obj.put("td5", "none");
		            obj.put("td6", "none");
		            obj.put("td7", "none");
		            
		            list.add(obj);
		            
		            
		          
		            
		            
		            
		            
	        }
	        
	        
	        while(rs.next())
	        {
	             obj=new JSONObject();
	           /* obj.put("EMPLOYEEID", rs.getString("employee_id"));
	            obj.put("DATEOFBIRTH", rs.getString("dateofbirth"));
	            
                 String data1=""+rs.getString("employee_id")+"~"+rs.getString("dateofbirth")+"~"+rs.getString("id")+"";
	            
	            obj.put("BUTTON", "<input type='button' class='btn btn-primary' alt='Dateofbirth_approve' id='"+rs.getString("id")+"' value='Approve' name="+data1+" onclick='Approve(this)'><span id="+rs.getString("id").concat("D")+"></span>");
	 
	            obj.put("BUTTON1", "<input type='button' class='btn btn-danger' alt='Employee_approve' id='"+rs.getString("id")+"' value='Reject' name='"+data1+"' onclick='Reject(this)'><span id='"+rs.getString("id").concat("R")+"'></span>");
		       	 
	            */
	            
	            
	            
	            
	            obj.put("EMPLOYEEID", rs.getString("employee_id"));
	            
	            //obj.put("LASTNAME", "NODATA");
	            
	            obj.put("FIRSTNAME",  rs.getString("dateofbirth"));
	            
	            obj.put("id", rs.getString("id"));
	            obj.put("BUTTON", " ");
	            obj.put("BUTTON1", " ");
	            
	            obj.put("td1", " ");
	            obj.put("td2", " ");
	            obj.put("td3", "none");
	            obj.put("td4", "none");
	            obj.put("td5", "none");
	            obj.put("td6", "none");
	            obj.put("td7", "none");
	            
	            String data1=rs.getString("employee_id")+"~"+rs.getString("dateofbirth")+"~"+rs.getString("id");
	            obj.put("Data", data1);
	            
	            obj.put("alt", "Dateofbirth_approve");
	            list.add(obj);
	        }
	        }
	        else  if(type.equalsIgnoreCase("PFNO"))
	        {
	        	
	        	String sql  = "SELECT t1.employee_id,t1.pf_no,t1.id FROM hclhrm_prod_others.hhcl_emp_profile_table t1 WHERE t1.lupdate = (SELECT MAX(t2.lupdate) FROM hclhrm_prod_others.hhcl_emp_profile_table t2 WHERE t2.employee_id = t1.employee_id and status=1113 and hr_status=1002)";
	    	    
	        rs=stm1.executeQuery(sql);
	        
	        if(!rs.isBeforeFirst())
	        {
	        	     obj=new JSONObject();
		            obj.put("EMPLOYEEID", "NODATA");
		            obj.put("FIRSTNAME", "NODATA");
		            obj.put("BUTTON","none");
		            obj.put("BUTTON1","none");
		            
		            obj.put("td1", " ");
		            obj.put("td2", " ");
		            obj.put("td3", "none");
		            obj.put("td4", "none");
		            obj.put("td5", "none");
		            obj.put("td6", "none");
		            obj.put("td7", "none");
		            
		            list.add(obj);
	        }
	        
	        while(rs.next())
	        {
	             obj=new JSONObject();
	           /* obj.put("EMPLOYEEID", rs.getString("employee_id"));
	            obj.put("PFNO", rs.getString("pf_no"));
	             
                 String data1=""+rs.getString("employee_id")+"~"+rs.getString("pf_no")+"~"+rs.getString("id")+"";
	            
	            obj.put("BUTTON", "<input type='button'class='btn btn-primary' alt='PFNO_approve' id='"+rs.getString("id")+"' value='Approve' name="+data1+" onclick='Approve(this)'><span id="+rs.getString("id").concat("P")+"></span>");
	 
	            obj.put("BUTTON1", "<input type='button' class='btn btn-danger' alt='Employee_approve' id='"+rs.getString("id")+"' value='Reject' name='"+data1+"' onclick='Reject(this)'><span id='"+rs.getString("id").concat("R")+"'></span>");
		       	 
	            
	            list.add(obj);*/
	            
	            
	            
                 obj.put("EMPLOYEEID", rs.getString("employee_id"));
	            
	            //obj.put("LASTNAME", "NODATA");
	            
	            obj.put("FIRSTNAME",  rs.getString("pf_no"));
	            
	            obj.put("id", rs.getString("id"));
	            obj.put("BUTTON", " ");
	            obj.put("BUTTON1", " ");
	            
	            obj.put("td1", " ");
	            obj.put("td2", " ");
	            obj.put("td3", "none");
	            obj.put("td4", "none");
	            obj.put("td5", "none");
	            obj.put("td6", "none");
	            obj.put("td7", "none");
	            
	            String data1=""+rs.getString("employee_id")+"~"+rs.getString("pf_no")+"~"+rs.getString("id")+"";
	            obj.put("Data", data1);
	            
	            obj.put("alt", "PFNO_approve");
	            list.add(obj);
	            
	            
	            
	            
	        }
	        }
	        
	        else  if(type.equalsIgnoreCase("PFUAN"))
	        {
	        	//String sql  = "SELECT employee_id,pf_uan_no FROM hhcl_desk.hhcl_emp_profile_table where status=1115";
	    	    	
	        	String sql  = "SELECT t1.employee_id,t1.pf_uan_no,t1.id FROM hclhrm_prod_others.hhcl_emp_profile_table t1 WHERE t1.lupdate = (SELECT MAX(t2.lupdate) FROM hclhrm_prod_others.hhcl_emp_profile_table t2 WHERE t2.employee_id = t1.employee_id and status=1114 and hr_status=1002)";
		    	
	        rs=stm1.executeQuery(sql);
	        
	        
	        
	        if(!rs.isBeforeFirst())
	        {
	        	     obj=new JSONObject();
		            obj.put("EMPLOYEEID", "NODATA");
		            obj.put("FIRSTNAME", "NODATA");
		            obj.put("BUTTON","none");
		            obj.put("BUTTON1","none");
		            
		            obj.put("td1", " ");
		            obj.put("td2", " ");
		            obj.put("td3", "none");
		            obj.put("td4", "none");
		            obj.put("td5", "none");
		            obj.put("td6", "none");
		            obj.put("td7", "none");
		           
		            
		            list.add(obj);
	        }
	        
	        
	        while(rs.next())
	        {
	             /*obj=new JSONObject();
	            obj.put("EMPLOYEEID", rs.getString("employee_id"));
	            obj.put("UANNO", rs.getString("pf_uan_no"));
                 String data1=""+rs.getString("employee_id")+"~"+rs.getString("pf_uan_no")+"~"+rs.getString("id")+"";
	            
	            obj.put("BUTTON", "<input type='button' alt='PFUAN_approve' class='btn btn-primary' id='"+rs.getString("id")+"' value='Approve' name="+data1+" onclick='Approve(this)'><span id="+rs.getString("id").concat("U")+"></span>");
	 
	            obj.put("BUTTON1", "<input type='button' class='btn btn-danger' alt='Employee_approve' id='"+rs.getString("id")+"' value='Reject' name='"+data1+"' onclick='Reject(this)'><span id='"+rs.getString("id").concat("R")+"'></span>");
		       	 
	            
	            list.add(obj);*/
	            
	            
                obj.put("EMPLOYEEID", rs.getString("employee_id"));
	            
	            //obj.put("LASTNAME", "NODATA");
	            
	            obj.put("FIRSTNAME",  rs.getString("pf_uan_no"));
	            
	            obj.put("id", rs.getString("id"));
	            obj.put("BUTTON", " ");
	            obj.put("BUTTON1", " ");
	            
	            obj.put("td1", " ");
	            obj.put("td2", " ");
	            obj.put("td3", "none");
	            obj.put("td4", "none");
	            obj.put("td5", "none");
	            obj.put("td6", "none");
	            obj.put("td7", "none");
	            
	            String data1=""+rs.getString("employee_id")+"~"+rs.getString("pf_uan_no")+"~"+rs.getString("id")+"";
	            obj.put("Data", data1);
	            
	            obj.put("alt", "PFUAN_approve");
	            list.add(obj);
	            
	            
	        }
	        }
	        else  if(type.equalsIgnoreCase("BANKDETAILS"))
	        {
	        	
	        	 Bank_Details.append("SELECT A.ID,C.EMPLOYEESEQUENCENO EID,A.BANK_type,C.CALLNAME NAME,B.BANKNAME,A.ACCOUNT_NO FROM");
	             Bank_Details.append("  HCLHRM_PROD_OTHERS.HHCL_EMP_PROFILE_TABLE A");
	             Bank_Details.append("  JOIN HCLADM_PROD.TBL_BANK_DETAILS B ON A.BANK_TYPE=B.BANKID");
	             Bank_Details.append("  JOIN HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY C ON A.EMPLOYEE_ID=C.EMPLOYEESEQUENCENO");
	             Bank_Details.append(" WHERE A.LUPDATE IN(SELECT MAX(LUPDATE) FROM HCLHRM_PROD_OTHERS.HHCL_EMP_PROFILE_TABLE GROUP BY EMPLOYEE_ID)");
	             Bank_Details.append(" AND A.STATUS=1115 AND A.HR_STATUS=1002");
	             Bank_Details.append("   GROUP BY A.EMPLOYEE_ID ORDER BY C.EMPLOYEESEQUENCENO");
	        	
	       
	       // System.out.println("Bank_Details::"+Bank_Details.toString());
	        rs=stm1.executeQuery(Bank_Details.toString());
	        	//String sql  = "SELECT employee_id, FROM hhcl_desk.hhcl_emp_profile_table  where status=1116";
	    		
//	        	String sql  = "SELECT t1.employee_id,IF(t1.bank_type='1','ICIC',"
//	        			+ "IF(t1.bank_type='2','AXIS',IF(t1.bank_type='3','STATE BANK OF INDIA',IF(t1.bank_type='4','STATE BANK OF HYDERABAD',IF(t1.bank_type='5','UTI BANK',IF(t1.bank_type='6','CHEQUE','---')))))) AS bank_type,t1.id,t1.bank_type as bank_id,t1.account_no FROM hclhrm_prod_others.hhcl_emp_profile_table t1 WHERE t1.lupdate = (SELECT MAX(t2.lupdate) FROM hclhrm_prod_others.hhcl_emp_profile_table t2 WHERE t2.employee_id = t1.employee_id and status=1115 and hr_status=1002)";
//		    	
	        if(!rs.isBeforeFirst())
	        {
	        	     obj=new JSONObject();
		            obj.put("EMPLOYEEID", "NODATA");
		            obj.put("FIRSTNAME", "NODATA");
		            obj.put("LASTNAME", "NODATA");
		            obj.put("BUTTON","none");
		            obj.put("BUTTON1","none");
		            
		            obj.put("td1", " ");
		            obj.put("td2", " ");
		            obj.put("td3", " ");
		            obj.put("td4", "none");
		            obj.put("td5", "none");
		            obj.put("td6", "none");
		            obj.put("td7", "none");
		            list.add(obj);
	        }
	        
	        
	        while(rs.next())
	        {
	             obj=new JSONObject();
	            /*obj.put("EMPLOYEEID", rs.getString("employee_id"));
	            obj.put("BANKTYPE", rs.getString("bank_type"));
	            obj.put("ACCOUNTNO", rs.getString("account_no"));
	            
                String data1=""+rs.getString("employee_id")+"~"+rs.getString("t1.bank_id")+"~"+rs.getString("id")+"~"+rs.getString("t1.account_no")+"";
	            
	            obj.put("BUTTON", "<input type='button' alt='Bank_approve' class='btn btn-primary' id='"+rs.getString("id")+"' value='Approve' name="+data1+" onclick='Approve(this)'><span id="+rs.getString("id").concat("B")+"></span>");
	 
	            obj.put("BUTTON1", "<input type='button' class='btn btn-danger' alt='Employee_approve' id='"+rs.getString("id")+"' value='Reject' name='"+data1+"' onclick='Reject(this)'><span id='"+rs.getString("id").concat("R")+"'></span>");
		       	 
	            
	            list.add(obj);*/
	           obj.put("EMPLOYEEID", rs.getString("EID"));
	            
	            //obj.put("LASTNAME", "NODATA");
	            
	            obj.put("FIRSTNAME",  rs.getString("BANKNAME"));
	            obj.put("LASTNAME", rs.getString("ACCOUNT_NO"));
	            
	            obj.put("id", rs.getString("id"));
	            obj.put("BUTTON", " ");
	            obj.put("BUTTON1", " ");
	            
	            obj.put("td1", " ");
	            obj.put("td2", " ");
	            obj.put("td3", " ");
	            obj.put("td4", "none");
	            obj.put("td5", "none");
	            obj.put("td6", "none");
	            obj.put("td7", "none");
	            
	            String data1=rs.getString("EID")+"~"+rs.getString("BANK_type")+"~"+rs.getString("id")+"~"+rs.getString("ACCOUNT_NO");
	            obj.put("Data", data1);
	            
	            obj.put("alt", "Bank_approve");
	            list.add(obj);
	            
	        }
	        }
	        
	        else  if(type.equalsIgnoreCase("PerAddress"))
	        {
	        	//String sql  = "select c.employee_id,c.per_address1,c.per_address2,c.email_id,c.mobile_id,a.name state,b.name city from tbl_location a join tbl_location b on a.locationid=b.parent join hhcl_desk.hhcl_emp_profile_table c where b.locationid=c.city and a.locationid=c.state";
	    	    
	        	//employee_id, per_address1, per_address2, email_id, mobile_id, state, city
	        	
	        	
	        	String sql  = "select c.employee_id,c.per_address1,c.per_address2,c.mobile_id,c.email_id,c.mobile_id,a.name state,b.name city,c.id,c.state as statid,c.city as cityid  from hcllcm_prod.tbl_location a join hcllcm_prod.tbl_location b on a.locationid=b.parent join hclhrm_prod_others.hhcl_emp_profile_table c where b.locationid=c.city and a.locationid=c.state and c.lupdate=(SELECT MAX(t2.lupdate) FROM hclhrm_prod_others.hhcl_emp_profile_table t2 WHERE t2.employee_id = c.employee_id and t2.status='1116' and hr_status=1002)";
	        	
	        	
	        	
	        rs=stm1.executeQuery(sql);
	        
	        if(!rs.isBeforeFirst())
	        {
	        	     obj=new JSONObject();
		            obj.put("EMPLOYEEID", "NODATA");
		            obj.put("FIRSTNAME","NODATA");
		            obj.put("LASTNAME","NODATA");
		            obj.put("MOBILE","NODATA");
		            obj.put("EMAILID","NODATA");
		            obj.put("STATE","NODATA");
		            obj.put("CITY", "NODATA");
		            obj.put("BUTTON","none");
		            obj.put("BUTTON1","none");
		            
		            
		            obj.put("td1", " ");
		            obj.put("td2", " ");
		            obj.put("td3", "");
		            obj.put("td4", "");
		            obj.put("td5", "");
		            obj.put("td6", "");
		            obj.put("td7", "");
		            
		            list.add(obj);
	        }
	        
	        
	        while(rs.next())
	        {
	             obj=new JSONObject();
	            /*obj.put("EMPLOYEEID", rs.getString("employee_id"));
	            obj.put("PERADDRESS1", rs.getString("per_address1"));
	            obj.put("PERADDRESS2", rs.getString("per_address2"));
	            obj.put("MOBILE", rs.getString("mobile_id"));
	            obj.put("EMAILID", rs.getString("email_id"));
	            obj.put("STATE", rs.getString("state"));
	            obj.put("CITY", rs.getString("city"));
                 String data1=rs.getString("employee_id").concat("~").concat(rs.getString("per_address1")).concat("~").concat(rs.getString("per_address2")).concat("~").concat(rs.getString("mobile_id")).concat("~").concat( rs.getString("email_id")).concat("~").concat(rs.getString("statid")).concat("~").concat(rs.getString("cityid")).concat("~").concat(rs.getString("id"));
	          //  System.out.println(data1.toString()+"------------------>");
	            obj.put("BUTTON", "<input type='button' class='btn btn-primary' alt='Adresss_approve' id='"+rs.getString("id")+"' value='Approve' name='"+data1+"' onclick='Approve(this)'><span id="+rs.getString("id").concat("ADD")+"></span>");
	 
	            obj.put("BUTTON1", "<input type='button' class='btn btn-danger' alt='Employee_approve' id='"+rs.getString("id")+"' value='Reject' name='"+data1+"' onclick='Reject(this)'><span id='"+rs.getString("id").concat("R")+"'></span>");
		       	 
	            
	            list.add(obj);*/
	            
	            
	            
	            
               obj.put("EMPLOYEEID", rs.getString("employee_id"));
	             
	            obj.put("FIRSTNAME", rs.getString("per_address1"));
	            
	            obj.put("LASTNAME", rs.getString("per_address2"));
	            obj.put("MOBILE", rs.getString("mobile_id"));
	            obj.put("EMAILID", rs.getString("email_id"));
	            obj.put("STATE", rs.getString("state"));
	            obj.put("CITY", rs.getString("city"));
	            
	            
	            
	            obj.put("id", rs.getString("id"));
	            obj.put("BUTTON", " ");
	            obj.put("BUTTON1", " ");
	            
	            obj.put("td1", " ");
	            obj.put("td2", " ");
	            obj.put("td3", "");
	            obj.put("td4", "");
	            obj.put("td5", "");
	            obj.put("td6", "");
	            obj.put("td7", "");
	            
	            String data1=rs.getString("employee_id").concat("~").concat(rs.getString("per_address1")).concat("~").concat(rs.getString("per_address2")).concat("~").concat(rs.getString("mobile_id")).concat("~").concat( rs.getString("email_id")).concat("~").concat(rs.getString("statid")).concat("~").concat(rs.getString("cityid")).concat("~").concat(rs.getString("id"));
	   	         obj.put("Data", data1);
	            
	            obj.put("alt", "Adresss_approve");
	            list.add(obj);
	            
	            
	            
	            
	            
	            
	            
	            
	        }
	        }
	        
	        
	        else  if(type.equalsIgnoreCase("Others"))
	        {
	        	
	        	
	        	Bank_Details.append(" SELECT t1.employee_id,t1.Reason,t1.E_mail,t1.id FROM hclhrm_prod_others.hhcl_emp_profile_table t1");
	        	Bank_Details.append(" WHERE t1.lupdate = (SELECT MAX(t2.lupdate) FROM hclhrm_prod_others.hhcl_emp_profile_table t2");
	        	Bank_Details.append(" WHERE t2.employee_id = t1.employee_id and status=1118 and hr_status=1002)");
	        	 
	       
	       // System.out.println("Bank_Details::"+Bank_Details.toString());
	        rs=stm1.executeQuery(Bank_Details.toString());
	        	//String sql  = "SELECT employee_id, FROM hhcl_desk.hhcl_emp_profile_table  where status=1116";
	    		
//	        	String sql  = "SELECT t1.employee_id,IF(t1.bank_type='1','ICIC',"
//	        			+ "IF(t1.bank_type='2','AXIS',IF(t1.bank_type='3','STATE BANK OF INDIA',IF(t1.bank_type='4','STATE BANK OF HYDERABAD',IF(t1.bank_type='5','UTI BANK',IF(t1.bank_type='6','CHEQUE','---')))))) AS bank_type,t1.id,t1.bank_type as bank_id,t1.account_no FROM hclhrm_prod_others.hhcl_emp_profile_table t1 WHERE t1.lupdate = (SELECT MAX(t2.lupdate) FROM hclhrm_prod_others.hhcl_emp_profile_table t2 WHERE t2.employee_id = t1.employee_id and status=1115 and hr_status=1002)";
//		    	
	        if(!rs.isBeforeFirst())
	        {
	        	     obj=new JSONObject();
		            obj.put("EMPLOYEEID", "NODATA");
		            obj.put("FIRSTNAME", "NODATA");
		            obj.put("LASTNAME", "NODATA");
		            obj.put("BUTTON","none");
		            obj.put("BUTTON1","none");
		            
		            obj.put("td1", " ");
		            obj.put("td2", " ");
		            obj.put("td3", " ");
		            obj.put("td4", "none");
		            obj.put("td5", "none");
		            obj.put("td6", "none");
		            obj.put("td7", "none");
		            list.add(obj);
	        }
	        
	        
	        while(rs.next())
	        {
	             obj=new JSONObject();
	            /*obj.put("EMPLOYEEID", rs.getString("employee_id"));
	            obj.put("BANKTYPE", rs.getString("bank_type"));
	            obj.put("ACCOUNTNO", rs.getString("account_no"));
	            
                String data1=""+rs.getString("employee_id")+"~"+rs.getString("t1.bank_id")+"~"+rs.getString("id")+"~"+rs.getString("t1.account_no")+"";
	            
	            obj.put("BUTTON", "<input type='button' alt='Bank_approve' class='btn btn-primary' id='"+rs.getString("id")+"' value='Approve' name="+data1+" onclick='Approve(this)'><span id="+rs.getString("id").concat("B")+"></span>");
	 
	            obj.put("BUTTON1", "<input type='button' class='btn btn-danger' alt='Employee_approve' id='"+rs.getString("id")+"' value='Reject' name='"+data1+"' onclick='Reject(this)'><span id='"+rs.getString("id").concat("R")+"'></span>");
		       	 
	            
	            list.add(obj);*/
	           obj.put("EMPLOYEEID", rs.getString("employee_id"));
	            
	            //obj.put("LASTNAME", "NODATA");
	            
	            obj.put("FIRSTNAME",  rs.getString("E_mail"));
	            obj.put("LASTNAME", rs.getString("Reason"));
	            
	            obj.put("id", rs.getString("id"));
	            obj.put("BUTTON", " ");
	            obj.put("BUTTON1", " ");
	            
	            obj.put("td1", " ");
	            obj.put("td2", " ");
	            obj.put("td3", " ");
	            obj.put("td4", "none");
	            obj.put("td5", "none");
	            obj.put("td6", "none");
	            obj.put("td7", "none");
	            
	            String data1=rs.getString("employee_id")+"~"+rs.getString("E_mail")+"~"+rs.getString("id")+"~"+rs.getString("Reason");
	            obj.put("Data", data1);
	            
	            obj.put("alt", "Others_approve");
	            list.add(obj);
	            
	        }
	        }
	        
	        
	        //out.print(obj.toString());
	        
	        out.print(list);
	    }
	    catch(Exception ex)
	    {
	        out.println("<h1>"+ex+"</g1>");
	    }
	}

}
