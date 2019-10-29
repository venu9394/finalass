package com.hhcdesk.service;
import java.io.IOException;
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
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.mysql.jdbc.Statement;
import com.hhcdesk.db.*;

/**
 * Servlet implementation class LoginServlet
 * Hetero Health Care Limited
 * By Java HHCL Java Team 
 * 
 */
@SuppressWarnings({"serial","unused"})
public class LoginServlet_main extends HttpServlet {

	


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletContext c = getServletContext();
		HttpSession session = request.getSession(false);

		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			   ipAddress = request.getRemoteAddr();
		}
		//getMACAddress
		///==========================================================================================
		
        String FlexiPolicy="N";
		Statement statement=null;
		ResultSet Res=null;
		//DataSource dataSource=null;
		java.sql.Connection conn=null;
		java.sql.PreparedStatement ps=null;

		Map UserMap=new HashMap();

		int User_Auth=0;
		GetDbData DataObj=new GetDbData();
		JSONObject jason = new JSONObject();
		JSONObject DOJ_DOB = new JSONObject();
		JSONObject EM_DOB = new JSONObject();

		JSONObject PaySlip= new JSONObject();
		JSONObject ForeCast= new JSONObject();
		JSONObject HOLIDAYS= new JSONObject();

		JSONObject HOLIDAYS_1= new JSONObject();

		JSONObject Cost_Center= new JSONObject();

		int dobcnt=0;
		int dobcnt1=0;
		JSONArray values;
		values = new JSONArray();

		String Routing=request.getParameter("Routing");

         
		 
		
		
		StringBuffer biodata=new StringBuffer();
		StringBuffer Emp_DOB=new StringBuffer();
		StringBuffer Emp_DOJ=new StringBuffer();
		
		StringBuffer Emp_Cost_Center=new StringBuffer();
		
		

		ResourceBundle bundle_info =(ResourceBundle)(c.getAttribute("bundle"));
		String Query=bundle_info.getString("HHCL_DESK_USER_LOGIN");
		String HHCL_EVENT_INFO=bundle_info.getString("HHCL_DESK_EVENT");
		session.setAttribute("HHCL_EVENT_INFO" ,HHCL_EVENT_INFO);
		session.setAttribute("TDSFLAG", bundle_info.getString("HHCL_DESK_TDS_ENABLE").toString());
		String message=null;
		
		String hr_main_tab_req=bundle_info.getString("HR_MAIN_TAB_REQ").toString();
		String hr_main_tab_acc=bundle_info.getString("HR_MAIN_TAB_ACC").toString();
		
		String AC_main_tab=bundle_info.getString("A/C_MAIN_TAB").toString();
		String TDS_FORECAST=bundle_info.getString("TDS_FORECAST").toString();
		
		message=bundle_info.getString("HHCL_DESK_USER_LOGIN_ERR");

		try {
			conn=(java.sql.Connection)session.getAttribute("ConnectionObj");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		

		String _FAIL_PAGE="/login.html";
		String _SUCCESS_PAGE=null;
		String NoticeData="Y";
		String username=request.getParameter("username");  
		String password=request.getParameter("pwd"); 
    try{    
    	if(username==null){
    		username="NA";
    	}
    	
    	
    	
    	
    if(c.getAttribute(username)!="Y" && Routing==null    ){	
   
		if(Routing!=null && Routing.equalsIgnoreCase("DashBoard")){
			username=(String)session.getAttribute("EMP_ID");
			password=(String)session.getAttribute("EMP_PASSWORD");
			NoticeData="N";
		}
		
	//****************************************************load Flexi Policy **********************************	
       StringBuffer FlexiPolicy_buf= new StringBuffer();
       
       FlexiPolicy_buf.append(" SELECT if(count(*)>0,'Y','N') FROM test.tbl_emp_flexi_policy where employeesequenceno in("+username+") and status=1001 ");
       
    	
    	try {
			Res=(ResultSet)DataObj.FetchData_Emp_DOB(FlexiPolicy_buf.toString(), "Flexi Policy", Res ,conn);
			if(Res.next()){
				
				FlexiPolicy=Res.getString(1);
			}  
			
		}catch(Exception Er){
			 System.out.println("Flexi Policy At Er::"+Er);
		}
    	
    
    ////****************************************************End  Flexi Policy **********************************		
    //// Below ADD For Manager Mail and Employee Maildata*******************************************************
    	
    	
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
		
		 String DEFAULT="DEFAULT";
		 JSONObject EMAILDATA = new JSONObject();
		 Map EMAILDATA_MAP=new HashMap();
		    EMAILDATA_MAP.put("EMPLOYEEID","NO_DATA");
			EMAILDATA_MAP.put("EMPLOYEENAME","NO_DATA");
			EMAILDATA_MAP.put("EMPEMAIL","NO_DATA");
			EMAILDATA_MAP.put("MANGERID","NO_DATA");
			EMAILDATA_MAP.put("MANAGERNAME","NO_DATA");
			EMAILDATA_MAP.put("MNGEMAIL","Manager Email Not available");
			EMAILDATA_MAP.put("BULOC",DEFAULT);	
			
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

		
		
	//// Below ADD For Manager Mail and Employee Maildata*******************************************************
    	
	    
		
		
    	
		session.setAttribute("Notice" ,NoticeData);
		String invalid=null;
		StringBuffer User_Authen=new StringBuffer();

		StringBuffer Q_ForeCast=new StringBuffer();
		StringBuffer Q_PaySlip=new StringBuffer();
		Q_ForeCast.append(bundle_info.getString("HHCL_DESK_FORCAST").toString());
		Q_PaySlip.append(bundle_info.getString("HHCL_DESK_PAYSLIP").toString());
		StringBuffer Holiday_List=new StringBuffer();
		User_Authen.append(Query);


		biodata.append(" SELECT A.EMPLOYEESEQUENCENO,A.CALLNAME,D.MOBILE,D.EMAIL,A.DATEOFBIRTH,C.DATEOFJOIN,I.NAME DEPARTMENT,H.NAME DESIGNATION,IF(A.EMPLOYMENTTYPEID=1,'PERMANENT','PROBATIONARY')TYPE,D.PERMANENTADDRESS,D.PERMANENTADDRESS2, ");
		biodata.append(" D.COMMUNICATIONADDRESS,D.COMMUNICATIONADDRESS2,G.RELATIONNAME,CONCAT(F.FIRSTNAME,' ',F.LASTNAME)FAMILY FROM ");
		biodata.append(" HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A ");
		biodata.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PROFESSIONAL_DETAILS B ON A.EMPLOYEEID=B.EMPLOYEEID ");
		biodata.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PROFILE C ON A.EMPLOYEEID=C.EMPLOYEEID ");
		biodata.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PERSONAL_CONTACT D ON A.EMPLOYEEID=D.EMPLOYEEID ");
		biodata.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY E ON B.MANAGERID=E.EMPLOYEEID ");
		biodata.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_FAMILY F ON A.EMPLOYEEID=F.EMPLOYEEID ");
		biodata.append(" LEFT JOIN HCLADM_PROD.TBL_RELATIONS G ON G.RELATIONID=F.RELATIONID ");
		biodata.append(" LEFT JOIN HCLADM_PROD.TBL_DESIGNATION H ON B.DESIGNATIONID=H.DESIGNATIONID ");
		biodata.append(" LEFT JOIN HCLADM_PROD.TBL_DEPARTMENT I ON I.DEPARTMENTID=B.DEPARTMENTID ");
		biodata.append(" where A.EMPLOYEESEQUENCENO="+username);


		


		Emp_DOB.append("SELECT A.CALLNAME NAME,C.NAME WISHHIM FROM   ");
		Emp_DOB.append("hclhrm_prod.tbl_employee_primary A   ");
		Emp_DOB.append("LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PROFESSIONAL_DETAILS B ON A.EMPLOYEEID=B.EMPLOYEEID   ");
		Emp_DOB.append("LEFT JOIN HCLADM_PROD.TBL_DEPARTMENT C ON B.DEPARTMENTID=C.DEPARTMENTID   ");
		Emp_DOB.append("LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PROFESSIONAL_CONTACT D ON A.EMPLOYEEID=D.EMPLOYEEID   ");
		Emp_DOB.append("WHERE A.STATUS=1001 AND DATE_FORMAT(A.DATEOFBIRTH,'%m-%d')=DATE_FORMAT(now(),'%m-%d') limit 4 ");


		Emp_DOJ.append(" SELECT A.CALLNAME NAME,D.NAME DEPT FROM  ");
		Emp_DOJ.append(" HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A  ");
		Emp_DOJ.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PROFILE B ON A.EMPLOYEEID=B.EMPLOYEEID  ");
		Emp_DOJ.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PROFESSIONAL_DETAILS C ON C.EMPLOYEEID=A.EMPLOYEEID  ");
		Emp_DOJ.append(" LEFT JOIN HCLADM_PROD.TBL_DEPARTMENT D ON C.DEPARTMENTID=D.DEPARTMENTID  ");
		Emp_DOJ.append(" WHERE A.STATUS=1001 AND  ");
		Emp_DOJ.append(" B.DATEOFJOIN >=DATE_FORMAT(curdate(),'%Y-%m-%d')- INTERVAL DAYOFWEEK(DATE_FORMAT(curdate(),'%Y-%m-%d')) DAY limit 4  ");


		Holiday_List.append("SELECT concat(trim(DATE_FORMAT(A.HOLIDAYDATE,'%d-%b-%y')),',',CONCAT(trim(DAYNAME(A.HOLIDAYDATE)))) EVENT,");
		Holiday_List.append("A.COMMENTS EVENT,   ");
		Holiday_List.append("IF(NOW()<A.HOLIDAYDATE,'UPCOMING','CLOSE')FLAG,IFNULL(B.NAME,'REGULAR')HOLIDAYTYPE   ");
		Holiday_List.append("FROM   ");
		Holiday_List.append("HCLHRM_PROD.TBL_HOLIDAYS A   ");
		Holiday_List.append("LEFT JOIN HCLHRM_PROD.TBL_HOLIDAY_TYPE B ON A.HOLIDAYTYPEID=B.HOLIDAYTYPEID   ");
		Holiday_List.append("WHERE A.BUSINESSUNITID IN (   ");
		Holiday_List.append("SELECT COMPANYID FROM HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY WHERE EMPLOYEESEQUENCENO IN ("+username+"))  ");
		Holiday_List.append(" and A.statusid=1001 and A.HOLIDAYDATE>=CURDATE()");
		Holiday_List.append(" order by A.HOLIDAYDATE  limit 4 ");
		
		StringBuffer manager_app=new StringBuffer();
		
		manager_app.append(" SELECT A.fromdate,if(length(lower(C.EMAIL))=0,'nodat',lower(C.EMAIL)) FROM  ");
		manager_app.append(" hcladm_prod.tbl_transaction_dates A ");
		manager_app.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY B ON B.COMPANYID=A.BUSINESSUNITID ");
		manager_app.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PROFESSIONAL_CONTACT C ON B.EMPLOYEEID=C.EMPLOYEEID ");
		manager_app.append(" where transactiontypeid=1 ");
		manager_app.append(" AND B.EMPLOYEESEQUENCENO=? order by transactionduration desc limit 1 ");
		
		StringBuffer Manager_rights=new StringBuffer();
		
		Manager_rights.append(" SELECT COUNT(*) FROM  ");
		Manager_rights.append(" HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY A ");
		Manager_rights.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PROFESSIONAL_CONTACT B  ON A.EMPLOYEEID=B.EMPLOYEEID ");
		Manager_rights.append(" LEFT JOIN HCLHRM_PROD_OTHERS.TBL_EMP_ATTN_REQ C ON A.EMPLOYEESEQUENCENO=C.EMPLOYEEID ");
		Manager_rights.append(" WHERE ");
		Manager_rights.append(" C.RID>73 AND ");
		// changed for me
		Manager_rights.append(" C.employeeid not in("+username+") ");
		//
		Manager_rights.append(" AND  C.TO_EMAIL LIKE ? ");
		
		StringBuffer Bu_Event=new StringBuffer();
		Bu_Event.append("select EVENTSCROLL,BUID,TDSFLAG from hclhrm_prod_others.tbl_bu_event_scroll where BUID in(");
		Bu_Event.append("select companyid from hclhrm_prod.tbl_employee_primary where employeesequenceno in(");
		Bu_Event.append("?");
		Bu_Event.append("))");
       
		
		Emp_Cost_Center.append(" select A.NAME,A.COSTCENTERID from hcladm_prod.tbl_costcenter A,hclhrm_prod.tbl_employee_primary B ");
		Emp_Cost_Center.append(" where A.COSTCENTERID=B.COSTCENTERID and B.employeesequenceno in("+username+") ");
		
    	 
    	 StringBuffer BusinessUnit=new StringBuffer();
 		
 		BusinessUnit.append(" select b.CODE,b.callname,b.name,b.businessunitid from hclhrm_prod.tbl_employee_primary a ");
 		BusinessUnit.append(" join hcladm_prod.tbl_businessunit b on a.companyid=b.businessunitid ");
 		BusinessUnit.append(" where a.employeesequenceno in(?) limit 1 ");
 		
 		Res=null;
 		try {
 			ps=conn.prepareStatement(BusinessUnit.toString());
 			ps.setInt(1,Integer.parseInt(username));
 			Res=(ResultSet)DataObj.FetchDataPrepare_2(ps, "User Authentication",Res,conn);
 			if(Res.next()){
 				session.setAttribute("EMPBU", Res.getString(2));
 				session.setAttribute("EMPBUID", Res.getString(4));
 			}
 			
 		} catch (SQLException e) {
 			e.printStackTrace();
 		}
 		
 		
 		
    	 
     if(Routing==null){ // First Time Data Insertion
		try {
			GetGeoLocation Location=new GetGeoLocation();
			String Loc=Location.GetGeoLocation(username,ipAddress , conn);
			session.setAttribute("LOCATION", Loc);
		} catch (Exception e) {
			e.printStackTrace();
		}
     }

		Res=null;
		String emp_mailid=null,From_Date_Txn=null;
		String apprisalPwd=null;
		int Manage_Rights=0;
		try {
			ps=conn.prepareStatement(User_Authen.toString());
			ps.setInt(1,Integer.parseInt(username));
			ps.setString(2,password);
			Res=(ResultSet)DataObj.FetchDataPrepare_2(ps, "User Authentication",Res,conn);
			
			if(Res.next()){
				
				User_Auth=Res.getInt(1);
				apprisalPwd=Res.getString(2).concat(Res.getString(3));
				session.setAttribute("apprisalPwd", apprisalPwd);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Res=null;
		ps=null;
		try {
			ps=conn.prepareStatement(manager_app.toString());
			ps.setInt(1,Integer.parseInt(username));
			Res=(ResultSet)DataObj.FetchDataPrepare_2(ps, "Get Mailid",Res,conn);
			if(Res.next()){

				emp_mailid=Res.getString(2);
				From_Date_Txn=Res.getString(1);
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		UserMap.put("emp_mailid", emp_mailid);
		UserMap.put("From_Date_Txn", From_Date_Txn);
		Res=null;
		ps=null;
		try {
			ps=conn.prepareStatement(Manager_rights.toString());
			ps.setString(1, "%"+emp_mailid+"%");
			Res=(ResultSet)DataObj.FetchDataPrepare_2(ps, "Manager Rights",Res,conn);
			//Res=(ResultSet)DataObj.FetchData("Select * from hclhrm_prod.tbl_employee_primary", "UserAuthentiCation", Res ,conn);
			if(Res.next()){
				Manage_Rights=Res.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UserMap.put("Manage_Rights", emp_mailid);
		session.setAttribute("Manage_Rights",""+Manage_Rights+"");
		// ForeCast &Payslip
		Res=null;
		ps=null;
		boolean payslipayth=false;
		
	if(Routing==null){ //// Payslip and Forecast Report Data
		try {
			PaySlip.put(0,"Select");
			ps=conn.prepareStatement(Q_PaySlip.toString());
			ps.setInt(1,Integer.parseInt(username));
			Res=(ResultSet)DataObj.FetchDataPrepare(ps, "PaySlip",Res,conn);

			while(Res.next()){
				PaySlip.put(Res.getString(1), Res.getString(2));
				payslipayth=true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Res=null;
		ps=null;
		int forc=1;
		try {
				ForeCast.put(0 ,"Select");
				ps=conn.prepareStatement(Q_ForeCast.toString());
				ps.setInt(1,Integer.parseInt(username));
				Res=(ResultSet)DataObj.FetchDataPrepare_2(ps, "Q_ForeCast",Res,conn);
			while(Res.next()){
				ForeCast.put(forc, Res.getString(2));
				//ForeCast.put(2, Res.getString(2));
				forc++;
			}
		} catch (SQLException e) {
			
			
			e.printStackTrace();
			
			
			
		}
		
		try {
			ps=conn.prepareStatement(Bu_Event.toString());
			ps.setInt(1,Integer.parseInt(username));
			Res=(ResultSet)DataObj.FetchDataPrepare_2(ps, "Bu_Event Load",Res,conn);
		while(Res.next()){
			
			//EVENTSCROLL,TDSFLAG,BUID UserMap
			
			session.setAttribute("HHCL_EVENT_INFO" ,Res.getString("EVENTSCROLL"));
			session.setAttribute("TDSFLAG", Res.getString("TDSFLAG"));
			UserMap.put("Companyid", ""+Res.getString("BUID")+"");
			
			
		}
	} catch (SQLException e) {
		
		e.printStackTrace();
		
	}

		
	  }  // Payslip and Forecast Report Data
	
		//System.out.println("vcvxcvxcvx Step 1 ::" +PaySlip.toString());

		//System.out.println("vcvxcvxcvx Step 2 ::" +ForeCast.toString());

		_SUCCESS_PAGE=bundle_info.getString("HHCL_DESK_DIVERT_1");

		//System.out.println("vcvxcvxcvx Step 3");
		Res=null;
		ResultSetMetaData rsmd=null;
		
		
		if(User_Auth==1 && Routing==null ){

             c.setAttribute(username, "Y");
			//System.out.println("vcvxcvxcvx Step 4");

			try {
				Res=(ResultSet)DataObj.FetchData(biodata.toString(), "biodata", Res ,conn);
				// rsmd =(ResultSetMetaData)Res.getMetaData();
				// String name = rsmd.getColumnName(1);
				//int columnCount = rsmd.getColumnCount();
				while(Res.next()){
					//Auth.add(rs.getString(1));

					session.setAttribute("EMP_NAME", Res.getString("CALLNAME"));

					jason.put("EMPLOYEESEQUENCENO" ,Res.getString("EMPLOYEESEQUENCENO"));

					jason.put("CALLNAME" ,Res.getString("CALLNAME")); 
					jason.put("MOBILE",Res.getString("MOBILE")); 
					jason.put("EMAIL",Res.getString("EMAIL")); 
					jason.put("DATEOFBIRTH",Res.getString("DATEOFBIRTH")); 
					jason.put("DATEOFJOIN",Res.getString("DATEOFJOIN")); 
					//jason.put("NAME ",Res.getString("NAME")); 
					jason.put("DEPARTMENT",Res.getString("DEPARTMENT")); 
					jason.put("DESIGNATION",Res.getString("DESIGNATION")); 
					jason.put("TYPE",Res.getString("TYPE")); 
					jason.put("PERMANENTADDRESS",Res.getString("PERMANENTADDRESS")); 
					jason.put("PERMANENTADDRESS2",Res.getString("PERMANENTADDRESS2")); 
					jason.put("COMMUNICATIONADDRESS",Res.getString("COMMUNICATIONADDRESS")); 
					jason.put("COMMUNICATIONADDRESS2",Res.getString("COMMUNICATIONADDRESS2")); 
					jason.put("RELATIONNAME",Res.getString("RELATIONNAME")); 
					jason.put("FAMILY",Res.getString("FAMILY")); 
					/*for (int i = 1; i <= columnCount; i++ ) {
  					  //String name = rsmd.getColumnName(i);
  				jason.put(rsmd.getColumnName(i),Res.getString(i));
  				System.out.println(rsmd.getColumnName(i)+"~~~"+Res.getString(i));
  			   }*/

				}  

				//System.out.println("After Query 5");

			}catch(Exception Er){
				//System.out.println("Exception At Er::"+Er);
			}
			System.out.println("After Query 5.1");

			Res=null;
			
			StringBuffer loginbuf=new StringBuffer();
			
			/*loginbuf.append(" select count(*)+1 from hclhrm_prod_others.tbl_iconnect_usage_monitoring ");
			loginbuf.append(" where DATE_FORMAT(ACCESSDATE,'%Y-%m-%d')=DATE_FORMAT(curdate(),'%Y-%m-%d') ");
			*/
			
			loginbuf.append(" select  count(distinct userid),count(*) from hclhrm_prod_others.tbl_iconnect_usage_monitoring ");
					loginbuf.append(" where DATE_FORMAT(ACCESSDATE,'%Y-%m-%d')=DATE_FORMAT(curdate(),'%Y-%m-%d') ");
							loginbuf.append(" and userid is not null ");
			
			
			String LoginCount=" ";
			String LoginAccess=" ";
			try {
				Res=(ResultSet)DataObj.FetchData_Emp_DOB(loginbuf.toString(), "LoginsCount", Res ,conn);
				while(Res.next()){
					
					LoginCount=Res.getString(1);
					LoginAccess=Res.getString(2);
					//dobcnt++;
				}  
				//System.out.println("After Query 5.4");
			}catch(Exception Er){
				 System.out.println("Exception LoginsCount At Er::"+Er);
			}
		//=====================================================	
			Res=null;
		 System.out.println("Emp_Cost_Center::"+Emp_Cost_Center.toString());
			try {
				Res=(ResultSet)DataObj.FetchData_Emp_DOB(Emp_Cost_Center.toString(), "Emp_Cost_Center", Res ,conn);
				while(Res.next()){
					
					
					System.out.println("Res.getString(1)::"+Res.getString(1));
					
					if(Res.getString(1)!=null && Res.getString(1).equalsIgnoreCase("FIELD")){
						
					         Cost_Center.put("CostCenter", "none");  
					         Cost_Center.put("Note", " ");
				    
				      }else if(Res.getString(1)!=null && Res.getString(1).equalsIgnoreCase("OFFICE")){
				    	  Cost_Center.put("CostCenter", " "); 
				    	  Cost_Center.put("Note", "none");
				      }else{
				    	  
				    	     Cost_Center.put("CostCenter", "none");  
					         Cost_Center.put("Note", " ");
				      }
					  
					//LoginCount=Res.getString(1);
					//LoginAccess=Res.getString(2);
					//dobcnt++;
				}  
				//System.out.println("After Query 5.4");
			}catch(Exception Er){
				 System.out.println("Exception Emp_Cost_Center At Er::"+Er);
			}
	    //=====================================================			
			
			session.setAttribute("LoginCount", LoginCount);
			session.setAttribute("LoginAccess", LoginAccess);
			
			Res=null;
			try {
				Res=(ResultSet)DataObj.FetchData_Emp_DOB(Emp_DOB.toString(), "Emp_DOB", Res ,conn);
				while(Res.next()){
					if(Res.getString(2)!=null){
						EM_DOB.put(Res.getString(1) , Res.getString(2)); 
					}else{
						EM_DOB.put(Res.getString(1) , "NA");
					}
					dobcnt++;
				}  
				//System.out.println("After Query 5.4");
			}catch(Exception Er){
				//System.out.println("Exception Emp_DOB At Er::"+Er);
			}

			//System.out.println("After Query 6");
			Res=null;
			int cont=0;

			JSONObject DOJ_DOB1 = new JSONObject();

			try {
				Res=(ResultSet)DataObj.FetchData_Emp_DOJ(Emp_DOJ.toString(), "Emp_DOJ", Res ,conn);
				while(Res.next()){

					if(Res.getString(2)!=null){
						DOJ_DOB.put(Res.getString(1) , Res.getString(2)); 
					}else{
						DOJ_DOB.put(Res.getString(1) , "NA");
					}
					dobcnt1++;

				}  
			}catch(Exception Er){
				//System.out.println("Exception Emp_DOJ At Er::"+Er);
			}
			System.out.println("DOJ_DOB::"+DOJ_DOB.toString());
			Res=null;
			ArrayList EMP_HOLIDAY_SUB=new ArrayList();
			int HolidaysCount=0;
			try {
				Res=(ResultSet)DataObj.FetchData_Emp_DOJ(Holiday_List.toString(), "HOLIDAYS_LIST", Res ,conn);
				while(Res.next()){
					HOLIDAYS_1= new JSONObject();
					HOLIDAYS_1.put("EVENTDATE" , Res.getString(1)); 
					HOLIDAYS_1.put("EVENT" , Res.getString(2));
					HOLIDAYS_1.put("STATUS" , Res.getString(3));
					values.add(HOLIDAYS_1);
					HolidaysCount++;
				}  
				System.out.println("values::"+values.toString());
			}catch(Exception Er){
				System.out.println("Exception HOLIDAYS_LIST  At Er::"+Er);
			}
             
			if(HolidaysCount<=0){
				
				HOLIDAYS_1= new JSONObject();
				HOLIDAYS_1.put("EVENTDATE" , "NA"); 
				HOLIDAYS_1.put("EVENT" ,  "NA");
				HOLIDAYS_1.put("STATUS" , "NA");
				values.add(HOLIDAYS_1);
				
				
			}
			
			System.out.println("After Query 7"); 

		}else if(User_Auth==1 && Routing.equalsIgnoreCase("DashBoard")){
		
			System.out.println("DashBoard Routing ..............................!");
			
		
		}else{
			_SUCCESS_PAGE="/login.html";
			request.setAttribute("message",bundle_info.getString("HHCL_DESK_LOGI_FAILD")); 
		}

		session.setAttribute("invalid",invalid);

		
  if(User_Auth==1 && Routing==null){
	  
	  if(dobcnt<1){

			EM_DOB.put(0, "No Events..");
		}

		System.out.println("dobcnt1::" + dobcnt1);

		if(dobcnt1<1){

			DOJ_DOB.put("No Events..", "No Events..");
		}

		
		request.setAttribute("EM_DOJ",DOJ_DOB.toString());
		System.out.println("EM_DOB::" +EM_DOB.toString());
		System.out.println("DOBs::" +EM_DOB.toString());
		request.setAttribute("EM_DOB",EM_DOB.toString());
		request.setAttribute("BioData",jason.toString());
		session.setAttribute("PaySlip", PaySlip.toString());
		session.setAttribute("ForeCast", ForeCast.toString());
		System.out.println("HoliDAY LIST"+HOLIDAYS.toString());
		request.setAttribute("HOLIDAYS",values.toString());
		    session.setAttribute("EM_DOJ",DOJ_DOB.toString());
		    session.setAttribute("EM_DOB",EM_DOB.toString());
		    session.setAttribute("BioData",jason.toString());
		    session.setAttribute("HOLIDAYS",values.toString());
		    
  	}else if(Routing!=null && Routing.equalsIgnoreCase("DashBoard")){
	    request.setAttribute("EM_DOJ", session.getAttribute("EM_DOJ"));
	    request.setAttribute("EM_DOB",session.getAttribute("EM_DOB"));
		request.setAttribute("BioData",session.getAttribute("BioData"));
		request.setAttribute("HOLIDAYS",session.getAttribute("HOLIDAYS"));
  	}
		
  
        System.out.println("Cost_Center::"+Cost_Center);
        session.setAttribute("Cost_Center", Cost_Center.toString());
		session.setAttribute("User_Main_Obj", UserMap);
		
		//session.setMaxInactiveInterval(15 * 60);  // 15 minutes
		/* Date hourAgo = new Date(System.currentTimeMillis() - 60*60*1000);
	      Date created = new Date(session.getCreationTime());
	      Date accessed = new Date(session.getLastAccessedTime());


	      int hours2 = created.getHours();
	      int minutes12 = created.getMinutes();
	      int minutes2 = created.getMinutes();
	      int seconds2 = created.getSeconds();

	      System.out.println(hours2+"~"+minutes12+"~"+minutes2+"~"+seconds2);

	      int hours = accessed.getHours();
	      int minutes1 = accessed.getMinutes();
	      int minutes = accessed.getMinutes();
	      int seconds = accessed.getSeconds();*/

		/*  Calendar calendar = Calendar.getInstance();
	      calendar.setTime(accessed);
	      int hours = calendar.get(Calendar.HOUR_OF_DAY);
	      int minutes = calendar.get(Calendar.MINUTE);
	      int seconds = calendar.get(Calendar.SECOND);*/

		// System.out.println(hours+"~"+minutes1+"~"+minutes+"~"+seconds);


		/* Date date = new Date();

	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);  
	    int hoursd = cal.get(Calendar.HOUR_OF_DAY);
	    int Minuts = cal.get(Calendar.MINUTE);

	    System.out.println("CurrTime::"+ Minuts);

	    System.out.println("TimeDifferens In Minutes:::" + (Minuts-minutes));*/
		
		try{
			
			if(hr_main_tab_req.equalsIgnoreCase("SINGLE")){
				
				 if(username!=null && hr_main_tab_acc.indexOf(username)!=-1){
					  
					 session.setAttribute("HR_HRMS", "YES");
					  
				   }else{
					   session.setAttribute("HR_HRMS", "NO");
				   }
			}else if(username!=null && hr_main_tab_req.equalsIgnoreCase("ALL")){
				 session.setAttribute("HR_HRMS", "YES");
			}
			
		}catch(Exception ERR){
			
			ERR.printStackTrace();
		}
		try{
			
			if(username!=null && AC_main_tab.indexOf(username)!=-1){
				session.setAttribute("AC_ITS", "YES");
			}else{
				   session.setAttribute("AC_ITS", "NO");
			   }
			
			
		}catch (Exception e) {
		}
		
        try{
			
			if(username!=null && TDS_FORECAST.indexOf(username)!=-1){
				session.setAttribute("TDS_FORECAST", "");
			}else if(username!=null && TDS_FORECAST.equalsIgnoreCase("ALL") && TDS_FORECAST.indexOf(username)!=-1){
				session.setAttribute("TDS_FORECAST", "");
			}else{
				   session.setAttribute("TDS_FORECAST", "none");
			   }
			
			
		}catch (Exception e) {
		}

		if(Routing==null){
			session.setAttribute("EMP_ID", username);
			session.setAttribute("Id", username);
			session.setAttribute("EMP_PASSWORD", password);
			// MySessionListener UserSessMap = (MySessionListener)UserMap.put(username , username);
			//  System.out.println(MySessionListener.sessions.get("username"));
			// System.out.println("LOGIN:::"+MySessionListener.UserMap.get(username));
			/* if(MySessionListener.UserMap.get(username)==null ){
	    	   MySessionListener.UserMap.put(username,username);
	        }else{
	    	   _SUCCESS_PAGE="";
	    	   request.setAttribute("message",bundle_info.getString("HHCL_DESK_MULTI_USER")); 
	     }*/
		}
		// response.sendRedirect(HandlingPage);
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

     }else{  // Final IF
     
            System.out.println("Else Condition");
            request.setAttribute("message","User already login/improper logout <a href='loginRest.jsp' style='cursor:pointer;'> <B>Please Reset</B></a>");
			_SUCCESS_PAGE="/login.html";
    	  
     }
        session.setAttribute("FlexiPolicy", FlexiPolicy);
		request.getRequestDispatcher(_SUCCESS_PAGE).forward(request, response);  
		
	} catch(Exception erf){
		 System.out.println("At Final Try Catch" +erf);
	}
}
}


