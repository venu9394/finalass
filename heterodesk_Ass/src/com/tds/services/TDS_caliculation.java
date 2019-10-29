package com.tds.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Arrear_lwp_caliculation
 */
public class TDS_caliculation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TDS_caliculation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.addHeader("Pragma", "no-cache");
		response.addHeader("Cache-Control", "no-cache");
		response.addHeader("Cache-Control", "no-store");
		response.addHeader("Cache-Control", "must-revalidate");
		response.addHeader("Cache-Control", "Post-Check=0");
		response.addHeader("Cache-Control", "Pre-Check=0");
		response.addHeader("Expires", "Mon, 1 Jan 2006 05:00:00 GMT");
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		
		Statement statement=null;
		 HttpSession session=request.getSession();
		ResultSet rs=null;
		/*conn = Util.getConnection();*/
		
		 try {
				//conn =dataSource.getConnection();
			 conn=(java.sql.Connection)session.getAttribute("ConnectionObj");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
					e1.printStackTrace();
			}
			
		 
		GetDbData DataObj=new GetDbData();
		/*conn = Util.getConnection();*/
		int[] str;
		ServletContext context = getServletContext();
		System.out.println("Hi I am in TDS _Caliculation Class..!" +conn);
		
		boolean callSelect=false;
		String Filename=null;
		Set  linkedHashSet_title = new LinkedHashSet();
		Set  linkedHashSet_employee = new LinkedHashSet();
		Map  FetchData_Map = new HashMap();
		System.out.println("we are at Arrear_lwp_caliculation ");
		ArrayList Selected_List=new ArrayList();
		StringBuffer Selected_List_buf=new StringBuffer();
		Map Emp_map=new HashMap();
		String message=null;
		//HttpSession session=request.getSession();
		// String PayPeriod=(String) session.getAttribute("PayPeriod");
		// String CurrPayPeriod=(String) session.getAttribute("CurrPayPeriod");
		// StringBuffer ALLBUS=(StringBuffer) session.getAttribute("ALLBUS") ;
		  String LOGINBY=(String)session.getAttribute("LOGINBY");
		  
		  System.out.println("LOGINBY::"+LOGINBY);
		  
		  /*if(LOGINBY==null){
			  message="Your session Expire,Please Relogin..!";
			  session.invalidate();
		  }*/
		  
		 String companyid=request.getParameter("BusinessUnit");
		 String[] chkBox=request.getParameterValues("chkBox");
		 String chkBox_emp=request.getParameter("chkBox_emp");
		 
		 String FromPeriod=request.getParameter("FromPeriod");
		 String ToPeriod=request.getParameter("ToPeriod");
		 
		 StringBuffer Components_Buffer=new StringBuffer();
		 
		 System.out.println(chkBox.toString());
		 
		 System.out.println(companyid);
		 System.out.println(chkBox_emp);
		 
		 System.out.println(FromPeriod);
		 System.out.println(ToPeriod);
		 String DwDLocation=null;
		 for(int i=0;i<chkBox.length-1;i++){
			 
			 Components_Buffer.append(chkBox[i]);
			 Components_Buffer.append(",");
			 //System.out.println("CheckBoxParams::"+chkBox[i]);
		 }
		 Components_Buffer.append("22,97,98");
		 
		 System.out.println("Components_Buffer::"+Components_Buffer.toString());
		 StringBuffer list_BU_la=new StringBuffer();
		 
		 if(companyid!=null && companyid.equalsIgnoreCase("HYD")){
	    	  DwDLocation="HYD";
	    	  
	    	  list_BU_la.append("10,");
			  list_BU_la.append("11,");
			  list_BU_la.append("15,");
			  list_BU_la.append("16,");
			  list_BU_la.append("19,");
			  list_BU_la.append("20,");
			  list_BU_la.append("21,");
			  list_BU_la.append("22");
			  
			 // list_BU_la.append("26"); // no employess
			  
			 /* list_BU_la.append("14,");
			  list_BU_la.append("17,");
			  list_BU_la.append("18,");
			  list_BU_la.append("24");*/
			  
	      }else if(companyid!=null && companyid.equalsIgnoreCase("HYDAZ")){
	    	  
	    	  list_BU_la.append("14,");
			  list_BU_la.append("17,");
			  list_BU_la.append("18,");
			  list_BU_la.append("24,");
			  
			  list_BU_la.append("27,");
			  list_BU_la.append("28,");
			  list_BU_la.append("29");
			  
			  //14,17,18,24,27,28,29
		 
	      }else if(companyid!=null && companyid.equalsIgnoreCase("MUM")){
	    	  DwDLocation="MUM";
	    	  list_BU_la.append("3,");
			  list_BU_la.append("5,");
			  list_BU_la.append("6,");
			  list_BU_la.append("7,");
			  list_BU_la.append("8,");
			  list_BU_la.append("12,");
			  list_BU_la.append("13,");
			  list_BU_la.append("23");  
	    	  
		  }else if(companyid!=null && companyid.equalsIgnoreCase("ADM")){
			  DwDLocation="ADM";
			  list_BU_la.append("10,");
			  list_BU_la.append("11,");
			 // list_BU_la.append("14,");
			  list_BU_la.append("15,");
			  list_BU_la.append("16,");
			//  list_BU_la.append("17,");
			//  list_BU_la.append("18,");
			  list_BU_la.append("19,");
			  list_BU_la.append("20,");
			  list_BU_la.append("21,");
			  list_BU_la.append("22,");
			
			  //list_BU_la.append("24,");
			  //--
			  list_BU_la.append("3,");
			  list_BU_la.append("5,");
			  list_BU_la.append("6,");
			  list_BU_la.append("7,");
			  list_BU_la.append("8,");
			  list_BU_la.append("12,");
			  list_BU_la.append("13,");
			  list_BU_la.append("23,"); 
			  
			//  list_BU_la.append("26"); no employess
			  
			  list_BU_la.append("27,");
			  list_BU_la.append("28,");
			  list_BU_la.append("29");
			  
			  
		  }else if(companyid!=null && companyid.equalsIgnoreCase("EMPID")){
			    DwDLocation="ADM";
			    list_BU_la.append("10001");
			  
		  }else{
			    DwDLocation="ADM";
			    list_BU_la.append(companyid);
			  
		  }
		 
		 
		 
		 
		    FetchData_Map.put("FROMPAYPERIOD",FromPeriod);
			FetchData_Map.put("TOPAYPERIOD" ,ToPeriod);
			
		    StringBuffer Employee_ID_Seq_Bulk=new StringBuffer();
			StringBuffer EmployeeIDs_Bulk=new StringBuffer();
			StringBuffer Employee_Seq_Bulk=new StringBuffer();
			StringBuffer TDS_DATA_QUERY=new StringBuffer();
			StringBuffer Components_Sum=new StringBuffer();
			
			
			
			
		if(chkBox_emp.equalsIgnoreCase("ALL")){
			
		    Employee_ID_Seq_Bulk.append(" select  a.employeeid,a.employeesequenceno,a.callname from    ");
	    	Employee_ID_Seq_Bulk.append(" hclhrm_prod.tbl_employee_payperiod_details b ");
	    	Employee_ID_Seq_Bulk.append(" left join hclhrm_prod.tbl_employee_primary a on ");
	    	Employee_ID_Seq_Bulk.append(" a.employeeid=b.employeeid   ");
	    //	Employee_ID_Seq_Bulk.append(" and  b.businessunitid=a.companyid ");
	    	Employee_ID_Seq_Bulk.append(" where  a.companyid in("+list_BU_la.toString()+") and  ");
	    	
	    //	Employee_ID_Seq_Bulk.append(" b.payperiod between '"+FromPeriod+"' and '"+ToPeriod+"' ");
	    	Employee_ID_Seq_Bulk.append(" b.payperiod between '201704' and '"+ToPeriod+"' ");
	    	
	    	Employee_ID_Seq_Bulk.append(" group by a.employeeid ");
	    	
		}else if(chkBox_emp.equalsIgnoreCase("TDS_DEDUCT")){
			
			
			
			Employee_ID_Seq_Bulk.append(" select employeeid,employeesequenceno,callname from ");
			Employee_ID_Seq_Bulk.append(" hclhrm_prod.tbl_employee_primary where employeeid in( ");
			Employee_ID_Seq_Bulk.append(" select employeeid from hclhrm_prod.tbl_employee_tds ");
			Employee_ID_Seq_Bulk.append(" where transactionid between '"+FromPeriod+"' and '"+ToPeriod+"' and businessunitid in("+list_BU_la.toString()+") ) ");
			
			
			
			
			
			
		}
	    System.out.println(chkBox_emp+"~Employee_ID_Seq_Bulk::"+Employee_ID_Seq_Bulk);   	
		rs=null;
			try {
				rs=(ResultSet)DataObj.getBasic(Employee_ID_Seq_Bulk.toString(), "buunit", rs ,conn);
				while(rs.next()){
					EmployeeIDs_Bulk.append(rs.getString("employeeid"));
					EmployeeIDs_Bulk.append(",");
					Employee_Seq_Bulk.append(rs.getString("employeesequenceno"));
					Employee_Seq_Bulk.append(",");
					
					linkedHashSet_employee.add(rs.getString("employeeid"));
					
					FetchData_Map.put(rs.getString("employeeid")+"_ID",rs.getString("employeesequenceno"));
					FetchData_Map.put(rs.getString("employeeid")+"_NAME",rs.getString("callname"));
					FetchData_Map.put(rs.getString("employeeid")+"_PAN"," ");
					FetchData_Map.put(rs.getString("employeeid")+"_PAN_FLAG","N");
					
					for(int i=Integer.parseInt(FromPeriod);i<=Integer.parseInt(ToPeriod);i++){
						
					FetchData_Map.put(rs.getString("employeeid")+"_"+i+"_DAT","00-00-0000");
					FetchData_Map.put(rs.getString("employeeid")+"_"+i+"_AMT","0");
					
				
					FetchData_Map.put(rs.getString("employeeid")+"_"+i+"_TAX","0");
					FetchData_Map.put(rs.getString("employeeid")+"_"+i+"_SUR","0");
					FetchData_Map.put(rs.getString("employeeid")+"_"+i+"_EDUCESS","0");
					FetchData_Map.put(rs.getString("employeeid")+"_"+i+"_TOTAL_TDS","0");
					
					FetchData_Map.put(rs.getString("employeeid")+"_"+i+"_DAT_DEPOSIT","00/00/0000");
					
					FetchData_Map.put(rs.getString("employeeid")+"_"+i ,"N");
					
					
				}
					
					
					
					
					
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
			try{
			EmployeeIDs_Bulk.deleteCharAt(EmployeeIDs_Bulk.length()-1);
			Employee_Seq_Bulk.deleteCharAt(Employee_Seq_Bulk.length()-1);
			}catch(Exception ERR){
				System.out.println("Exception at::"+ERR);
			}
			System.out.println(Employee_Seq_Bulk+"::EmployeeIDs_Bulk::" +EmployeeIDs_Bulk);
			
			
			
		/*	Components_Sum.append(" select b.employeesequenceno,b.employeeid,b.callname, date_format(a.LUPDATE,'%d/%m/%Y') DAT, ifnull(sum(a.netvalue),0) amount , ");
			Components_Sum.append(" a.payperiod from hclhrm_prod.tbl_employee_pay_data a,hclhrm_prod.tbl_employee_primary b ");
			Components_Sum.append(" where a.componentid in ("+Components_Buffer.toString()+") and ");
			Components_Sum.append(" b.employeeid=a.employeeid ");
			Components_Sum.append(" and a.status=1001 ");
			Components_Sum.append(" and b.employeeid in ("+EmployeeIDs_Bulk.toString()+") ");
			Components_Sum.append(" and a.payperiod between '"+FromPeriod+"' and '"+ToPeriod+"' ");
			Components_Sum.append(" group by a.payperiod,b.employeesequenceno ");
			*/
			

			Components_Sum.append(" select b.employeesequenceno,b.employeeid,b.callname, date_format(a.LUPDATE,'%d/%m/%Y') DAT, ifnull(sum(a.netvalue),0) amount , ");
			Components_Sum.append(" a.payperiod from hclhrm_prod.tbl_employee_pay_data a,hclhrm_prod.tbl_employee_primary b ,hclhrm_prod.tbl_employee_payperiod_details c ");
			Components_Sum.append(" where a.componentid in ("+Components_Buffer.toString()+") and ");
			Components_Sum.append(" b.employeeid=a.employeeid ");
			Components_Sum.append(" and a.status=1001  and a.payperiod=c.payperiod and a.employeeid=c.employeeid ");
			Components_Sum.append(" and b.employeeid in ("+EmployeeIDs_Bulk.toString()+") ");
			Components_Sum.append(" and a.payperiod between '"+FromPeriod+"' and '"+ToPeriod+"' ");
			Components_Sum.append(" group by a.payperiod,b.employeesequenceno ");
			
			
			//Components_Sum.append(" group by b.employeesequenceno ");
			
			
			System.out.println("Components_Sum::"+Components_Sum);
			
			
			rs=null;
			try {
				rs=(ResultSet)DataObj.getBasic(Components_Sum.toString(), "Components_Sum", rs ,conn);
				while(rs.next()){
					
					
					FetchData_Map.put(rs.getString("employeeid")+"_ID",rs.getString("employeesequenceno"));
					FetchData_Map.put(rs.getString("employeeid")+"_NAME",rs.getString("callname"));
					
					
				//	FetchData_Map.put(rs.getString("employeeid")+"_DAT",rs.getString("DAT"));
				//	FetchData_Map.put(rs.getString("employeeid")+"++""_AMT",rs.getString("amount"));
					
					FetchData_Map.put(rs.getString("employeeid")+"_"+rs.getString("payperiod")+"_DAT",rs.getString("DAT"));
					FetchData_Map.put(rs.getString("employeeid")+"_"+rs.getString("payperiod")+"_AMT",rs.getString("amount"));
					
					FetchData_Map.put(rs.getString("payperiod"),rs.getString("DAT"));
					
					
					FetchData_Map.put(rs.getString("employeeid")+"_PAN" , " ");
					
				
							
					/*EmployeeIDs_Bulk.append(rs.getString("employeeid"));
					EmployeeIDs_Bulk.append(",");
					Employee_Seq_Bulk.append(rs.getString("employeesequenceno"));
					Employee_Seq_Bulk.append(",");*/
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			/*TDS_DATA_QUERY.append(" select transactionid,employeeid,round(TDSVALUE) ,round(TDSVALUE-round((TDSVALUE*0.03))) TDSVAL1,round(TDSVALUE*0.03) EDUCESS , ");
			TDS_DATA_QUERY.append(" round(TDSVALUE-round((TDSVALUE*0.03)))+round(TDSVALUE*0.03) FINALTDS from ");
			TDS_DATA_QUERY.append(" hclhrm_prod.tbl_employee_tds ");
			TDS_DATA_QUERY.append(" where transactionid between '"+FromPeriod+"' and '"+ToPeriod+"' ");
			TDS_DATA_QUERY.append(" and employeeid in ("+EmployeeIDs_Bulk.toString()+") group by employeeid,transactionid ");*/
			
			
			TDS_DATA_QUERY.append(" select payperiod,employeeid,round(netvalue) ,round(netvalue-round((netvalue*0.03))) TDSVAL1,round(netvalue*0.03) EDUCESS , ");
			TDS_DATA_QUERY.append(" round(netvalue-round((netvalue*0.03)))+round(netvalue*0.03) FINALTDS from ");
			TDS_DATA_QUERY.append(" hclhrm_prod.tbl_employee_pay_data where componentid=21 and payperiod between '"+FromPeriod+"' and '"+ToPeriod+"' ");
			TDS_DATA_QUERY.append(" and employeeid in("+EmployeeIDs_Bulk.toString()+") and status=1001 and netvalue!=0 ");
			
			
			
			rs=null;
			try {
				rs=(ResultSet)DataObj.getBasic(TDS_DATA_QUERY.toString(), "Components_Sum", rs ,conn);
				while(rs.next()){
					FetchData_Map.put(rs.getString("employeeid")+"_"+rs.getString("payperiod")+"_TAX",rs.getString("TDSVAL1"));
					FetchData_Map.put(rs.getString("employeeid")+"_"+rs.getString("payperiod")+"_EDUCESS",rs.getString("EDUCESS"));
					FetchData_Map.put(rs.getString("employeeid")+"_"+rs.getString("payperiod")+"_TOTAL_TDS",rs.getString("FINALTDS"));
					
					FetchData_Map.put(rs.getString("employeeid")+"_"+rs.getString("payperiod"), "Y");
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			StringBuffer TDS_PAN=new StringBuffer();
			TDS_PAN.append(" select employeeid,PAN from hclhrm_prod.tbl_employee_personalinfo where employeeid ");
			TDS_PAN.append(" in ("+EmployeeIDs_Bulk.toString()+") and PAN!='NA' and PAN!='0' and PAN is not null  having(length(PAN)>3) ");
			
			rs=null;
			try {
				rs=(ResultSet)DataObj.getBasic(TDS_PAN.toString(), "Components_Sum", rs ,conn);
				while(rs.next()){
					
					FetchData_Map.put(rs.getString("employeeid")+"_PAN",rs.getString("PAN"));
					FetchData_Map.put(rs.getString("employeeid")+"_PAN_FLAG","Y");
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
			
			//*****************************************************************
		 
		 
		 
		 
		 
		 /* BusinessUnit //
		  chkBox//

		  chkBox_emp//
		  FromPeriod//ToPeriod
		  */
		  
		  /*	String Routing=request.getParameter("ROUTING");
		System.out.println("Routing::"+Routing);
		if(Routing==null){
		String[] Data_Acc=(String[])request.getParameterValues("checkboxlwp");
		int EmployeeSelectedSize=Data_Acc.length;
        System.out.println("Data Length "+EmployeeSelectedSize);
	   if(EmployeeSelectedSize!=0){
		for(int i=0 ;i<Data_Acc.length;i++){
			System.out.println("Data :"+Data_Acc[i]);
			String Data[]=Data_Acc[i].split("~");
			Selected_List.add(Data[0]);
			Emp_map.put(Data[0]+"_LOP", Data[2]);
			Emp_map.put(Data[0]+"_SEQ", Data[1]);
			Selected_List_buf.append(Data[0]+",");
		    }
		  Selected_List_buf.append("00000");
       }
	   ListIterator  Empselect=Selected_List.listIterator();
       try{
    	   conn.setAutoCommit(false);//commit trasaction manually

    	   String insertTableSQL ="insert into hclhrm_prod_others.tbl_emp_lwp_arrear_payperiod(EMPLOYEEID,BUSINESSUNITID,PAYMENT,PAYPERIOD,PAYSTRUCTUREID,GROSSVALUE,BASEDAYS,PAYABLEDAYS,NOOFDAYS,COMPONENTID,ARREAR)"
    			   +" SELECT EMPLOYEEID,BUSINESSUNITID,"+PayPeriod+" PAYMENT,"+CurrPayPeriod+" PAYPERIOD,PAYSTRUCTUREID,GROSSVALUE,BASEDAYS,PAYABLEDAYS,? NOD,COMPONENTID,"
    			   +" ROUND(GROSSVALUE/BASEDAYS)*? ARREAR "
    			   +" FROM "
    			   +" HCLHRM_PROD.TBL_EMPLOYEE_PAY_DATA t WHERE EMPLOYEEID=? AND PAYPERIOD="+PayPeriod+" AND STATUS=1001 "
    			   +" AND COMPONENTTYPEID IN (1,2) "
    			   +" ON DUPLICATE KEY UPDATE NOOFDAYS=?,ARREAR=ROUND(t.GROSSVALUE/t.BASEDAYS)*? " ;
    	           pstmt = conn.prepareStatement(insertTableSQL);
    	
       
       while(Empselect.hasNext()){
       String EMPID=Empselect.next().toString();
       
          pstmt.setDouble(1, Double.parseDouble(Emp_map.get(EMPID+"_LOP").toString()));
          pstmt.setDouble(2, Double.parseDouble(Emp_map.get(EMPID+"_LOP").toString()));
          pstmt.setInt(3,    Integer.parseInt(EMPID));       
          pstmt.setDouble(4, Double.parseDouble(Emp_map.get(EMPID+"_LOP").toString()));
          pstmt.setDouble(5, Double.parseDouble(Emp_map.get(EMPID+"_LOP").toString()));
          pstmt.addBatch();
          
       }
         str=pstmt.executeBatch();
         System.out.println("Get Data::"+ str.toString());
         if(str.length>=1){
        	 conn.commit(); 
        	 callSelect=true;
        	 message="Request Submited Successfully!";
         }
       
       }catch(SQLException Sql){
    	   System.out.println("SQL Exception at INSERT ::"+Sql);
    	   message=Sql.toString();
       }catch(Exception ERD){
    	   System.out.println("OTHER Exception at INSERT ::"+ERD);
    	   message=ERD.toString();
       }
     // Calling For Data
	}
       if(callSelect || Routing.equals("REPORT")){
    	   
    	   StringBuffer Fetch_Record=new StringBuffer();
		   Fetch_Record.append(" select b.employeesequenceno,b.employeeid,b.callname,d.name buname,c.name componentname,a.noofdays,a.arrear,a.componentid,a.payment from hclhrm_prod_others.tbl_emp_lwp_arrear_payperiod a ");
		   Fetch_Record.append(" left join hclhrm_prod.tbl_employee_primary b on b.employeeid=a.employeeid ");
		   Fetch_Record.append(" left join hcladm_prod.tbl_pay_component c on c.paycomponentid=a.componentid ");
		   Fetch_Record.append(" left join hcladm_prod.tbl_businessunit d on d.businessunitid=b.companyid ");
		   Fetch_Record.append(" where a.payment="+PayPeriod+" and b.companyid in("+ALLBUS.toString()+") ");
		   Fetch_Record.append(" and a.componentid!=30 order by b.employeeid,c.indexno ");
    	   
    	   
    	   Res=null;
		    try {
				Res=(ResultSet)DataObj.FetchData(Fetch_Record.toString(), "Fetch_Record", Res ,conn);
				
				while(Res.next()){
					linkedHashSet_title.add(Res.getString("componentid"));
					linkedHashSet_employee.add(Res.getString("employeesequenceno"));
					FetchData_Map.put(Res.getString("employeesequenceno")+"_ID",Res.getString("employeesequenceno"));
					FetchData_Map.put(Res.getString("employeesequenceno")+"_NAME",Res.getString("callname"));
					FetchData_Map.put(Res.getString("employeesequenceno")+"_BU",Res.getString("buname"));
					FetchData_Map.put(Res.getString("employeesequenceno")+"_"+Res.getString("componentid"),Res.getString("arrear"));
					FetchData_Map.put(Res.getString("employeesequenceno")+"_NOD",Res.getString("noofdays"));
					FetchData_Map.put(Res.getString("employeesequenceno")+"_PD",Res.getString("payment"));
					FetchData_Map.put(Res.getString("componentid").toString(),Res.getString("componentname"));
					
				}
				
				
		    }catch(SQLException exp){
		    	System.out.println("SQL ERROR AT 1:"+exp);
		    }catch(Exception exp){
		    	System.out.println("ERROR AT 1:"+exp);
		    }
		    try{
    	    Filename=Report_lwp.Report_lwp_Gen(linkedHashSet_title,linkedHashSet_employee,FetchData_Map, LOGINBY);
		    }catch(Exception er){
		    	System.out.println("File Generation Exception ::" +er);
		    }
		    
       }else{
    	   message="Request not processed,please Relogin and try again..!";
       }
       */
		  
		//*********************************************************************************************************** 
		 /* StringBuffer Fetch_Record=new StringBuffer();
		   Fetch_Record.append(" select b.employeesequenceno,b.employeeid,b.callname,d.name buname,c.name componentname,a.noofdays,a.arrear,a.componentid,a.payment from hclhrm_prod_others.tbl_emp_lwp_arrear_payperiod a ");
		   Fetch_Record.append(" left join hclhrm_prod.tbl_employee_primary b on b.employeeid=a.employeeid ");
		   Fetch_Record.append(" left join hcladm_prod.tbl_pay_component c on c.paycomponentid=a.componentid ");
		   Fetch_Record.append(" left join hcladm_prod.tbl_businessunit d on d.businessunitid=b.companyid ");
		   Fetch_Record.append(" where a.payment="+PayPeriod+" and b.companyid in("+ALLBUS.toString()+") ");
		   Fetch_Record.append(" and a.componentid!=30 order by b.employeeid,c.indexno ");
   	   
   	   
   	   Res=null;
		    try {
				Res=(ResultSet)DataObj.fupaid(Fetch_Record.toString(), "Fetch_Record", Res ,conn);
				
				while(Res.next()){
					linkedHashSet_title.add(Res.getString("componentid"));
					linkedHashSet_employee.add(Res.getString("employeesequenceno"));
					FetchData_Map.put(Res.getString("employeesequenceno")+"_ID",Res.getString("employeesequenceno"));
					FetchData_Map.put(Res.getString("employeesequenceno")+"_NAME",Res.getString("callname"));
					FetchData_Map.put(Res.getString("employeesequenceno")+"_BU",Res.getString("buname"));
					FetchData_Map.put(Res.getString("employeesequenceno")+"_"+Res.getString("componentid"),Res.getString("arrear"));
					FetchData_Map.put(Res.getString("employeesequenceno")+"_NOD",Res.getString("noofdays"));
					FetchData_Map.put(Res.getString("employeesequenceno")+"_PD",Res.getString("payment"));
					FetchData_Map.put(Res.getString("componentid").toString(),Res.getString("componentname"));
					
				}
				
				
		    }catch(SQLException exp){
		    	System.out.println("SQL ERROR AT 1:"+exp);
		    }catch(Exception exp){
		    	System.out.println("ERROR AT 1:"+exp);
		    }  */
		  
		//***********************************************************************************************************
		
	/*	linkedHashSet_employee.add(1001);
		linkedHashSet_employee.add(1002);
		linkedHashSet_employee.add(1003);
		
		FetchData_Map.put(1001+"_PAN","20000");
		
		
		FetchData_Map.put(1002+"_PAN","20001");
		FetchData_Map.put(1003+"_PAN","20003");
		
		FetchData_Map.put(1003+"_NAME","Venu BAbu ");
		FetchData_Map.put(1003+"_DAT","20/04/2017");
		FetchData_Map.put(1003+"_AMT","20003");
		
		
		FetchData_Map.put(1003+"_TAX","20003");
		FetchData_Map.put(1003+"_SUR","20003");
		FetchData_Map.put(1003+"_EDUCESS","20003");
		FetchData_Map.put(1003+"_TOTAL_TDS","20003");
		FetchData_Map.put(1003+"_DAT_DEPOSIT","07/05/2017");
		FetchData_Map.put(1003+"_PAN_FLAG","Y");
		
		
		FetchData_Map.put(1001+"_NAME","Venu BAbu 3");
		FetchData_Map.put(1001+"_DAT","20/04/2017");
		FetchData_Map.put(1001+"_AMT","20003");
		FetchData_Map.put(1001+"_TAX","20003");
		FetchData_Map.put(1001+"_SUR","20003");
		FetchData_Map.put(1001+"_EDUCESS","20003");
		FetchData_Map.put(1001+"_TOTAL_TDS","20003");
		FetchData_Map.put(1001+"_DAT_DEPOSIT","07/05/2017");
		FetchData_Map.put(1001+"_PAN_FLAG","Y");
		
		
		FetchData_Map.put(1002+"_NAME","Venu BAbu 2");
		FetchData_Map.put(1002+"_DAT","20/04/2017");
		FetchData_Map.put(1002+"_AMT","20003");
		
		FetchData_Map.put(1003+"_PAN","20003");
		FetchData_Map.put(1002+"_TAX","20003");
		FetchData_Map.put(1002+"_SUR","20003");
		FetchData_Map.put(1002+"_EDUCESS","20003");
		FetchData_Map.put(1002+"_TOTAL_TDS","20003");
		FetchData_Map.put(1002+"_DAT_DEPOSIT","07/05/2017");
		FetchData_Map.put(1002+"_PAN_FLAG","Y");
		
		*/
		
		
		
		
		
		
		
       
       try{
   	      Filename=Report_TDS.Report_lwp_Gen(linkedHashSet_title,linkedHashSet_employee,FetchData_Map, LOGINBY);
		  }
             catch(Exception er){
		    	System.out.println("File Generation Exception ::" +er);
		    }
       System.out.println("Filename::"+Filename);
       
       
       String mimeType = context.getMimeType("C:/lwparrear/24Q_TDS.xls");
       
       if (mimeType == null) {        
           // set to binary type if MIME mapping not found
           mimeType = "application/octet-stream";
       }
        System.out.println("MIME type: " + mimeType);
        
       // modifies response
       
       File downloadFile = new File("C:/lwparrear/24Q_TDS.xls");
       FileInputStream inStream = new FileInputStream(downloadFile);
        
       
       response.setContentType(mimeType);
       response.setContentLength((int) downloadFile.length());
        
       // forces download
       String headerKey = "Content-Disposition";
       String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
       response.setHeader(headerKey, headerValue);
        
       // obtains response's output stream
       OutputStream outStream = response.getOutputStream();
        
       byte[] buffer = new byte[4096*10];
       int bytesRead = -1;
        
       while ((bytesRead = inStream.read(buffer)) != -1) {
           outStream.write(buffer, 0, bytesRead);
       }
        
       inStream.close();
       outStream.close();    
       
       
       
      /* session.setAttribute("linkedHashSet_title", linkedHashSet_title);
       session.setAttribute("linkedHashSet_employee", linkedHashSet_employee);
       session.setAttribute("FetchData_Map", FetchData_Map);
       System.out.println("linkedHashSet::"+linkedHashSet_title.toString());
       request.getRequestDispatcher("lwp_arrear_download.jsp").forward(request, response);	*/  
	}// Close Do post

}
