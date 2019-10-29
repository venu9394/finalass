package com.hhcdesk.service;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.PrintWriter;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSetMetaData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.mysql.jdbc.Statement;
import com.hhcdesk.db.*;

/**
 * Servlet implementation class Leave_Bal_New for second version 
 * Hetero Health Care Limited
 * By Java HHCL Java Tem 
 * Written By Venu
 */
//@WebServlet("/User_Auth")
public class Leave_Bal_New extends HttpServlet {

	public Leave_Bal_New() {
		// TODO Auto-generated constructor stub
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext c = getServletContext();
		System.out.println("GetParameter"+ c.getAttribute("Venu"));
		HttpSession session = request.getSession(false);
		Statement statement=null;
		ResultSet Res=null;
		DataSource dataSource=null;
		java.sql.Connection conn=null;
		java.sql.PreparedStatement ps=null;

		PrintWriter out=response.getWriter();
		Map UserMap=new HashMap();

		boolean ConnFlag=true;
		int User_Auth=0;
		GetDbData DataObj=new GetDbData();
		JSONObject jason = new JSONObject();
		JSONObject DOJ_DOB = new JSONObject();
		JSONObject EM_DOB = new JSONObject();
		
		
		JSONObject LeaveBalance = new JSONObject();
		
		JSONObject PaySlip= new JSONObject();
		JSONObject ForeCast= new JSONObject();

		int dobcnt=0;
		int dobcnt1=0;
		JSONArray values,Emp_exp;
		values = new JSONArray();
		
		Emp_exp = new JSONArray();
		
		String Routing=request.getParameter("Routing");

		System.out.println("Routing  :::" +Routing);

		BatchInsertRecords BatchInsert=new BatchInsertRecords();
		ArrayList MasterDataList = new ArrayList(); 
		ArrayList<PrepareData> list = new ArrayList<PrepareData>();
		Map FetchRc=new HashMap();
		StringBuffer biodata=new StringBuffer();
		StringBuffer Emp_DOB=new StringBuffer();
		StringBuffer Emp_DOJ=new StringBuffer();
		StringBuffer Emp_DOJ1=new StringBuffer();
		ArrayList DOB=new ArrayList();
		ArrayList DOJ=new ArrayList();
		ArrayList DOJ_DEPT=new ArrayList();
        String Back_Dates="";

		JSONObject  Doj= new JSONObject();

		session.setAttribute("Notice" ,"N");
		ResourceBundle bundle_info =(ResourceBundle)(c.getAttribute("bundle"));

		String Query=bundle_info.getString("HHCL_DESK_USER_LOGIN");

		String HHCL_EVENT_INFO=bundle_info.getString("HHCL_DESK_EVENT");

		session.setAttribute("HHCL_EVENT_INFO" ,HHCL_EVENT_INFO);

		String message=null;
		message=bundle_info.getString("HHCL_DESK_NEWJOINEE");
		System.out.println("Query:::"+Query);
		
		try {
			//conn =dataSource.getConnection();
			conn=(java.sql.Connection)session.getAttribute("ConnectionObj");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
				e1.printStackTrace();
		}
		
		System.out.println("Step 2");
		String _FAIL_PAGE="/NewJoinee_Faild.jsp";
		String _SUCCESS_PAGE=null;
		String username=request.getParameter("username");  
		String password=request.getParameter("pwd"); 

		if(Routing!=null &&  (Routing.equalsIgnoreCase("LEAVE_BALANCE")||Routing.equalsIgnoreCase("LEAVE_BALANCE_AJX") )){
			username=(String)session.getAttribute("EMP_ID");
			password=(String)session.getAttribute("EMP_PASSWORD");
		}

		StringBuffer Email_Buff_data=new StringBuffer();
		
		Email_Buff_data.append(" SELECT A.EMPLOYEESEQUENCENO EMPLOYEEID,A.CALLNAME EMPLOYEENAME,ifnull(PROEMP.EMAIL,' ') AS EMPEMAIL, ");
		Email_Buff_data.append(" ifnull( MANGER.EMPLOYEESEQUENCENO,' ') MANGERID,ifnull(MANGER.CALLNAME,' ') MANAGERNAME,ifnull(PROMANG.EMAIL,'') AS MNGEMAIL,ifnull(BU.callname,'DEFAULT') AS BULOC ");
		Email_Buff_data.append(" FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A ");
		Email_Buff_data.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PROFESSIONAL_DETAILS B ON A.EMPLOYEEID=B.EMPLOYEEID ");
		Email_Buff_data.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY MANGER ON MANGER.EMPLOYEEID=B.MANAGERID ");
		Email_Buff_data.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PROFESSIONAL_CONTACT PROEMP ON PROEMP.EMPLOYEEID=A.EMPLOYEEID ");
		Email_Buff_data.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PROFESSIONAL_CONTACT PROMANG ON PROMANG.EMPLOYEEID=B.MANAGERID ");
		Email_Buff_data.append(" LEFT JOIN hcladm_prod.tbl_businessunit BU ON BU.businessunitid=A.COMPANYID ");
		Email_Buff_data.append(" WHERE A.EMPLOYEESEQUENCENO="+username+" ");
		

		String invalid=null;
		StringBuffer User_Authen=new StringBuffer();
		User_Authen.append(Query);

    if(Routing.equalsIgnoreCase("LEAVE_BALANCE") ||Routing.equalsIgnoreCase("LEAVE_BALANCE_AJX") ){
    	
    	
    
 		/*if(Routing.equalsIgnoreCase("LEAVE_BALANCE") ||Routing.equalsIgnoreCase("LEAVE_BALANCE_AJX")){
 			
 			
 			 String DEFAULT="DEFAULT";
 			 Res=null;
 		       try {
 					Res=(ResultSet)DataObj.FetchData_Emp_DOB(Email_Buff_data.toString(), "Email_Buff_data", Res ,conn);
 					if(Res.next()){
 					//	LeaveBalance.put(Res.getString("SHORTNAME") , Res.getString("quantity"));
 					//	Back_Dates=Res.getString("FRDATE");
 						
 						EMAILDATA.put("EMPLOYEEID",Res.getString("EMPLOYEEID"));
 						EMAILDATA.put("EMPLOYEENAME",Res.getString("EMPLOYEENAME"));
 						EMAILDATA.put("EMPEMAIL",Res.getString("EMPEMAIL"));
 						EMAILDATA.put("MANGERID",Res.getString("MANGERID"));
 						EMAILDATA.put("MANAGERNAME",Res.getString("MANAGERNAME"));
 						EMAILDATA.put("MNGEMAIL",Res.getString("MNGEMAIL"));
 					  try{
 						if(Res.getString("BULOC")!=null){
 							DEFAULT=bundle_info.getString(""+Res.getString("BULOC")+"");
 						}
 					  }catch(Exception Ert){
 						  Ert.printStackTrace();
 					  }
 						EMAILDATA.put("BULOC",DEFAULT);
 						
 						EMAILDATA_MAP.put("EMPLOYEEID",Res.getString("EMPLOYEEID"));
 						EMAILDATA_MAP.put("EMPLOYEENAME",Res.getString("EMPLOYEENAME"));
 						EMAILDATA_MAP.put("EMPEMAIL",Res.getString("EMPEMAIL"));
 						EMAILDATA_MAP.put("MANGERID",Res.getString("MANGERID"));
 						EMAILDATA_MAP.put("MANAGERNAME",Res.getString("MANAGERNAME"));
 						EMAILDATA_MAP.put("MNGEMAIL",Res.getString("MNGEMAIL"));
 						EMAILDATA_MAP.put("BULOC",DEFAULT);				
 					}
 				}catch(Exception ERFD){
 					   System.out.println("Error Loaded At::" +ERFD);
 				}   
 			
 			session.setAttribute("EMAILDATA_MAP", EMAILDATA_MAP);
 			session.setAttribute("EMAILDATA", ""+EMAILDATA.toString()+"");
 		}*/
      
		
    // ************** For Leave Balance Caliculation *****************************/////////////////////////
		
    /*	
    	String QueryLop=" select if(count(*)=0,0,sum(A.LOPCOUNT)) from HCLHRM_PROD.TBL_EMPLOYEE_LOP A "
    			+ "LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY B ON A.EMPLOYEEID=B.EMPLOYEEID "
    			+ "where B.EMPLOYEESEQUENCENO IN ("+username+") and A.LOPTRANSACTIONID between CONCAT(YEAR(CURDATE()),'01') and CONCAT(YEAR(CURDATE()),'12') ";
    */	
     StringBuffer BuffBck=new StringBuffer();
     BuffBck.append(" select max(FROMDATE) FRDATE from hcladm_prod.tbl_transaction_dates ");
     BuffBck.append(" where businessunitid in(select companyid from hclhrm_prod.tbl_employee_primary where employeesequenceno in("+username+")) and transactiontypeid=1 ");
     
      Res=null;
       try {
			Res=(ResultSet)DataObj.FetchData_Emp_DOB(BuffBck.toString(), "Leave_Quota", Res ,conn);
			while(Res.next()){
			//	LeaveBalance.put(Res.getString("SHORTNAME") , Res.getString("quantity"));
				Back_Dates=Res.getString("FRDATE");
							
			}
		}catch(Exception ERFD){
			   System.out.println("Error Loaded At::" +ERFD);
		}   
		
		
		
    	LeaveBalance.put("CL_P" , "0");
    	LeaveBalance.put("CL_A" , "0");
    	LeaveBalance.put("CL_USED" , "0");
    	LeaveBalance.put("CL_TOT" , "0");
    	LeaveBalance.put("CL_REM" , "0");

    	LeaveBalance.put("CL_R" , "0");
    	LeaveBalance.put("SL_P" , "0");
    	LeaveBalance.put("SL_A" , "0");
    	LeaveBalance.put("SL_USED" , "0");
    	LeaveBalance.put("SL_TOT" , "0");
    	LeaveBalance.put("SL_REM" , "0");
    	LeaveBalance.put("SL_R" , "0");

    	LeaveBalance.put("EL_P" , "0");
    	LeaveBalance.put("EL_A" , "0");
    	LeaveBalance.put("EL_USED" , "0");
    	LeaveBalance.put("EL_TOT" , "0");
    	LeaveBalance.put("EL_REM" , "0");

    	LeaveBalance.put("EL_R" , "0");
    	LeaveBalance.put("OD_P" , "0");
    	LeaveBalance.put("OD_A" , "0");
    	LeaveBalance.put("OD_USED" , "0");
    	LeaveBalance.put("OD_R" , "0");
    	LeaveBalance.put("CF_P" , "0");
    	LeaveBalance.put("CF_A" , "0");
    	LeaveBalance.put("CF_USED" , "0");
    	LeaveBalance.put("CF_R" , "0");								
    
    	LeaveBalance.put("CL" , "0");
    	LeaveBalance.put("SL" , "0");
    	LeaveBalance.put("EL" , "0");
    	///LeaveBalance.put("CF_R" , "0");
    	
    	LeaveBalance.put("LOP" , "0");
    	
    	StringBuffer  Leave_Quota=new  StringBuffer();
    	
    	//Back_Dates
    	Leave_Quota.append(" select C.employeesequenceno,trim(A.SHORTNAME) SHORTNAME,B.quantity , B.AVAILABLEQTY , B.HOLD, B.quantity+B.HOLD as totalavl, b.USEDQTY, trim(A.NAME) Fullname,B.DAYMODE,if(B.daymode=0,if(B.availableqty<=B.maxleave,B.availableqty,B.maxleave),B.maxleave) MAXLEAVE_C ,B.COUNT_WOFF, ");
    	
    	Leave_Quota.append(" if('"+Back_Dates+"'=now(),0,if('"+Back_Dates+"' < now(),datediff(now(),'"+Back_Dates+"'),if('"+Back_Dates+"' > now(),4,0))) as bkdays ");
    	Leave_Quota.append(", B.BACKDATE from  hclhrm_prod.tbl_leave_type A,  ");
    	Leave_Quota.append(" hclhrm_prod_others.tbl_emp_leave_quota B, ");
    	Leave_Quota.append(" hclhrm_prod.tbl_employee_primary C ");
    	Leave_Quota.append(" where B.employeeid=C.employeeid and C.employeesequenceno in("+username+") and ");
    	Leave_Quota.append(" B.Leavetypeid=A.Leavetypeid  and b.status=1001");
    	
    	Res=null;
        boolean LevFlag=false;
        Map Combination=new HashMap();
		try {
			Res=(ResultSet)DataObj.FetchData_Emp_DOB(Leave_Quota.toString(), "Leave_Quota", Res ,conn);
			while(Res.next()){
			//	LeaveBalance.put(Res.getString("SHORTNAME") , Res.getString("quantity"));
				Doj= new JSONObject();
				Doj.put("SHORTNAME", Res.getString("SHORTNAME"));
				Doj.put("QTY", Res.getString("quantity"));
				Doj.put("AVAIL", Res.getString("AVAILABLEQTY"));
				Doj.put("HOLD", Res.getString("HOLD"));
				Doj.put("STILLAVAIL", Res.getString("totalavl"));
				Doj.put("USEDQTY", Res.getString("USEDQTY"));
				Doj.put("FULLNAME", Res.getString("Fullname"));
				Doj.put("MAXLEAVE", Res.getString("MAXLEAVE_C"));
				Doj.put("DAYMODE", Res.getString("DAYMODE"));
				Doj.put("COUNT_WOFF", Res.getString("COUNT_WOFF"));
				
				if(Double.parseDouble(Res.getString("bkdays").toString()) <= Double.parseDouble(Res.getString("BACKDATE").toString()) ){
				
					//Res.getString("AVAILABLEQTY")
					if(Res.getString("SHORTNAME").equalsIgnoreCase("SL")){
						Doj.put("BACKDATE", Res.getString("bkdays"));
					   // Doj.put("BACKDATE", Res.getString("AVAILABLEQTY"));
					}else{
					    Doj.put("BACKDATE", Res.getString("bkdays"));
					}
				}else if(Double.parseDouble(Res.getString("bkdays").toString())>Double.parseDouble(Res.getString("BACKDATE").toString()) ){
					//Res.getString("AVAILABLEQTY")
					if(Res.getString("SHORTNAME").equalsIgnoreCase("SL")){
						
					  //  Doj.put("BACKDATE", Res.getString("AVAILABLEQTY"));
						Doj.put("BACKDATE", Res.getString("BACKDATE"));  
					    
					}else{
						Doj.put("BACKDATE", Res.getString("BACKDATE"));
					}
					
				}
				
				Doj.put("DEDUCTED", Res.getString("HOLD"));
				if(Res.getString("DAYMODE").equalsIgnoreCase("1")||(Double.parseDouble(Res.getString("AVAILABLEQTY"))>0 || Double.parseDouble(Res.getString("AVAILABLEQTY"))>0.00)){
				    Doj.put("LEAVETYPE", Res.getString("Fullname"));
				}else{
					Doj.put("LEAVETYPE", "NODATA");
				}
				values.add(Doj);
				LevFlag=true;
			}
		}catch(Exception ERFD){
			    Doj.put("SHORTNAME", "Leave Quota Not Assigned/No Leave Balance");
				Doj.put("QTY", "0");
				Doj.put("AVAIL", "0");
				Doj.put("HOLD", "0");
				Doj.put("STILLAVAIL", "0");
				Doj.put("USEDQTY", "0");
				Doj.put("LEAVETYPE", "NODATA");
				Doj.put("FULLNAME", "Leave Quota Not Assigned /No Leave Balance");
				
				Doj.put("DEDUCTED", "0");
				
				values.add(Doj);
		}
	  if(!LevFlag){
			 
			    Doj.put("SHORTNAME", "Leave Quota Not Assigned /No Leave Balance");
				Doj.put("QTY", "0");
				Doj.put("AVAIL", "0");
				Doj.put("HOLD", "0");
				Doj.put("STILLAVAIL", "0");
				Doj.put("USEDQTY", "0");
				Doj.put("LEAVETYPE", "NODATA");
				
				Doj.put("FULLNAME", "Leave Quota Not Assigned /No Leave Balance");
				
				Doj.put("DEDUCTED", "0");
				
				values.add(Doj);
			 
			 
		 }
    	
	 }  /// End at Leave Balance If Condition

		Res=null;
		try {
			ps=conn.prepareStatement(User_Authen.toString());
			ps.setInt(1,Integer.parseInt(username));
			ps.setString(2,password);
			Res=(ResultSet)DataObj.FetchDataPrepare_2(ps, "User Authentication",Res,conn);
			//Res=(ResultSet)DataObj.FetchData("Select * from hclhrm_prod.tbl_employee_primary", "UserAuthentiCation", Res ,conn);
			if(Res.next()){

				User_Auth=Res.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Res=null;
		ps=null;
		Res=null;
		ResultSetMetaData rsmd=null;
		if(User_Auth==1){
  
		 try{
			 
			
			if(Routing.equalsIgnoreCase("LEAVE_BALANCE")){

				_SUCCESS_PAGE="HHCL_LEAVE_SUMMARY_Balance.jsp";
			}
			
				/*DOJ_DOB.put(Res.getString(1) , Res.getString(2)); 
				 * */
				
			}catch(Exception Er){
				System.out.println("Exception At Er2::"+Er);
			}
		}else{
			_SUCCESS_PAGE="/login.html";
			request.setAttribute("message",bundle_info.getString("HHCL_DESK_LOGI_FAILD")); 
		}
		
		
		 System.out.println("values.toString()::" +values.toString());
		 
		 
		 request.setAttribute("DOJ_DOB",values.toString());
		//request.setAttribute("Emp_exp",Emp_exp.toString());
		
		
		request.setAttribute("LeaveBalance",values.toString());
		
		
		if(Routing==null){
			session.setAttribute("EMP_ID", username);
			session.setAttribute("EMP_PASSWORD", password);

		}
		
  		  try {
  			     if(statement!=null){
  			       statement.close();
  			     }
  			   if(Res!=null){
  				    Res.close();
  	  			}
  			 /*if(conn!=null){
  				conn.close();
    	       }*/
  			 if(ps!=null){
  				ps.close();
    	       }
    	 		
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
  	 
		System.out.println(values.toString()+"_SUCCESS_PAGE::"+_SUCCESS_PAGE);
		
	   if(!Routing.equalsIgnoreCase("LEAVE_BALANCE")){
		   System.out.println("values.toString()::" +values.toString());
		  out.print(values.toString());
	   }else{
		  request.getRequestDispatcher(_SUCCESS_PAGE).forward(request, response);
	   }
	}  
}
