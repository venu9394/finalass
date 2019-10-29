package com.tds.services;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.sql.DataSource;






@SuppressWarnings({ "serial", "unused" })
public class PayData extends HttpServlet {
	
	     Connection connection;
	     Statement statement;
	     Statement statement1;
	     Statement statement2;
	     Statement statement3;
	    
	    public void init() throws ServletException {
	        // Get DataSource
			 try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 System.out.println("Driver loaded....!");
			 try {
				connection=DriverManager.getConnection("jdbc:mysql://192.168.30.105:3306/","hcluser","hcluser!23");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 System.out.println("Connection Established....!");
			 
			 
			System.out.println("initContext::"+connection);
	    }
	
	
	
public void doPost(HttpServletRequest request, HttpServletResponse response)
                     throws ServletException, IOException {
	//String company=request.getParameter("bus");
	//String pp=request.getParameter("pp");
	
	 ArrayList components=new ArrayList();
	 
	 
	 
	 ArrayList emppayperiods=new ArrayList();
	 ArrayList empcomponents=new ArrayList();
	 
	 ArrayList empids=new ArrayList();
	 ArrayList payperiod=new ArrayList();
	 
	 ArrayList BulkList=new ArrayList();
	 
	 ArrayList subList=new ArrayList();
	 
	 ArrayList subList2=new ArrayList();
	 
	 Map empmap=new HashMap();
	 
	 Map ComMod=new HashMap();
	 
	 
	 Map Qry2=new HashMap();
	 
	 Map empseq=new HashMap();
	 
	 Map comptitle=new HashMap();
	 
	HttpSession session=request.getSession(true);
	ResultSet rs = null;
	ResultSet rs1 = null;
	ResultSet rs2 = null;
	ResultSet rs3 = null;
	String filename = "D:/BankStatement.csv";
	
	StringBuffer component=new StringBuffer();
 try {
    	FileWriter fw = new FileWriter(filename);
       /* Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.30.105:3306/","hcluser","hcluser!23");
        System.out.println("Connection Established...!");*/
    
    	 //connection = dataSource.getConnection();
    	 
    	
        StringBuffer buff=new StringBuffer();
       // String query = "SELECT PAYPERIOD,DURATION,FROM_DATE,TO_DATE,EMPLOYEESEQUENCENO,if(companyid=3,'Genx',if(companyid=5,'HHCL-Main',if(companyid=6,'HHCL-Oncology',if(companyid=7,'HHCL-Kris',if(companyid=8,'HHCL-Gastro',if(companyid=12,'HHCL-ARV',companyid))))))as Division,ARREAR_EARNING_AMOUNT AS ARREAR,ARREAR_PF,ARREAR_ESI,ARREAR_PT FROM test.arrear_cal WHERE COMPANYID="+company+" AND PAYPERIOD="+"'"+pp+"'";
        
        /*buff.append(" select a.payperiod,c.name as Devision,if((select count(*) from test.tbl_employee_bank_process ");
        buff.append(" where employeeid=b.employeeid)>0,(select bankname from test.tbl_employee_bank_process ");
        buff.append(" where employeeid=b.employeeid),b.callname ) as EmployeeName,e.bankname as BankName, ");
        buff.append(" d.accountno as Accountno, ");
        buff.append(" if((select count(*) from test.tbl_employee_bank_process ");
        buff.append(" where employeeid=b.employeeid )>0,(select processtype from test.tbl_employee_bank_process ");
        buff.append(" where employeeid=b.employeeid),if(b.status=1001,'TRANSFOR',f.name )) as TransforStatus, ");
        buff.append(" a.basedays as ActualDays,a.payabledays ");
        buff.append(" as PaidDays,a.netvalue ");
        buff.append(" as NetSalary ");
        buff.append(" from hclhrm_prod.TBL_EMPLOYEE_PAY_DATA a,hclhrm_prod.tbl_employee_primary b,hcladm_prod.tbl_businessunit c, ");
        buff.append(" hclhrm_prod.tbl_employee_other_detail d, ");
        buff.append(" hcladm_prod.tbl_bank_details e,hcladm_prod.tbl_status_codes f ");
        buff.append(" where ");
        buff.append(" a.payperiod=201604 and ");
        buff.append(" a.businessunitid=b.companyid and ");
        buff.append(" a.employeeid=b.employeeid and ");
        buff.append(" a.employeeid=d.employeeid and ");
        buff.append(" a.status=b.status and ");
        buff.append(" a.componentid=46 and ");
        buff.append(" a.componenttypeid=9 and ");
        buff.append(" b.companyid=9 and ");
        buff.append(" b.companyid=c.businessunitid And ");
        buff.append(" e.bankid=d.bankid and ");
        buff.append(" f.status=b.status and ");
        buff.append(" f.status=a.status and ");
        buff.append(" b.status not in(1003) ");*/
        String qury="select a.paycomponentid,b.displayname,b.componenttypeid,c.name,c.componenttypecode from hcladm_prod.tbl_pay_structure_details a,hcladm_prod.tbl_pay_component b,hcladm_prod.tbl_pay_component_type c where a.paycomponentid=b.paycomponentid and a.paycomponentid not in(35,34) and b.componenttypeid=c.componenttypeid and a.paystructureid=(select paystructureid from hcladm_prod.tbl_pay_structure_businessunit where businessunitid=9 and status=1001) order by a.paycomponentid ";
        buff.append("select d.employeeid,d.payperiod,d.netvalue,a.paycomponentid,b.displayname,b.componenttypeid,c.name,c.componenttypecode ");
        buff.append("from hcladm_prod.tbl_pay_structure_details a,hcladm_prod.tbl_pay_component b,hcladm_prod.tbl_pay_component_type c, ");
        buff.append("hclhrm_prod.tbl_employee_pay_data d ");
        buff.append("where a.paycomponentid=b.paycomponentid ");
        buff.append("and a.paycomponentid not in(35,34) ");
        buff.append("and a.paycomponentid=d.componentid ");
        buff.append("and b.paycomponentid=d.componentid and d.status=1001 and d.businessunitid=9 ");
        buff.append("and b.componenttypeid=d.componenttypeid ");
        buff.append("and c.componenttypeid=d.componenttypeid ");
        buff.append("and b.componenttypeid=c.componenttypeid ");
        buff.append("and a.paystructureid=(select paystructureid from hcladm_prod.tbl_pay_structure_businessunit ");
        buff.append("where businessunitid=9 and status=1001) ");
        buff.append("order by d.payperiod ");
        
        
        System.out.println(qury.toString());
         statement = connection.createStatement();
         rs = statement.executeQuery(qury.toString());
         
     	 
         /*fw.append("Payperiod");	//col-1
         fw.append(',');
         fw.append("Devision");
         fw.append(',');
         fw.append("EmployeeName");
         fw.append(',');
         fw.append("BankName");
         fw.append(',');
         fw.append("Accountno");
         fw.append(',');
         fw.append(" TransforStatus");
         fw.append(',');
         fw.append(" ActualDays");
         fw.append(',');
         fw.append("PaidDays");
         fw.append(',');
         fw.append("NetSalary");*/
        // fw.append(',');
        // fw.append("Arrear_PT");
        /* fw.append('\n');*/
        // payperiod.add("24");
     	// payperiod.add("32");
     int s=0;
        while (rs.next()) {
        	
        	
        	//empids.add((String)rs.getString(1));
        	
        //	components.add((String)rs.getString(4) )	;
        	
        //	empmap.put((String)rs.getString(2) +"-"+ (String)rs.getString(4) , (String)rs.getString(3));
        	
        	comptitle.put(rs.getString(1), rs.getString(2));
        	
        	ComMod.put((String)rs.getString(1)+"-M",  rs.getString(5));
        	empcomponents.add(rs.getString(1));
        	
        	if(s!=0){
        	component.append(",");
        	}
        	component.append(rs.getString(1));
        	s++;
        	
           /* fw.append(rs.getString(1));
            fw.append(',');
            fw.append(rs.getString(2));
            fw.append(',');
            fw.append(rs.getString(3));
            fw.append(',');
            fw.append(rs.getString(4));
            fw.append(',');
            fw.append(rs.getString(5));
            fw.append(',');
            fw.append(rs.getString(6));
            fw.append(',');
            fw.append(rs.getString(7));
            fw.append(',');
            fw.append(rs.getString(8));
            fw.append(',');
            fw.append(rs.getString(9));
           // fw.append(',');
           // fw.append(rs.getString(10));
            fw.append('\n');*/
        }
        
        fw.flush();
        fw.close();
       // connection.close();
        
        System.out.println("Disconnected From SQL...!"+component.toString());
        
        //rs.close();
        //ps.close(); //Locaking csv file in server
        System.out.println("Arrear File Is Downloaded Successfully...!");
        
    } catch (Exception e) {
        e.printStackTrace();
    }
   
  StringBuffer query1=new StringBuffer();
 // StringBuffer disp=new StringBuffer();
  query1.append(" select employeeid,componentid,sum(netvalue) from hclhrm_prod.tbl_employee_pay_data where payperiod between 201504 and 201603 ");
  query1.append(" and employeeid in(select employeeid from hclhrm_prod.tbl_employee_primary ");
  query1.append(" where status=1001 and companyid=9) ");
  query1.append(" and  businessunitid=9 ");
  query1.append(" and componentid in("+component.toString()+") ");
  query1.append(" group by componentid,employeeid ");
  
  
  try{
	     ///Qry2
	    System.out.println("This Is in adding Query::");
         statement1 = connection.createStatement();
         rs1 = statement1.executeQuery(query1.toString());
         
         while (rs1.next()) {
        	 
        	// empids.add((String)rs1.getString(1));
        	 Qry2.put((String)rs1.getString(1)+"-"+(String)rs1.getString(2), (String)rs1.getString(3));
        	
         	System.out.println("2nd Query::"+(String)rs1.getString(3));
         	
         }
         System.out.println("2nd Query::"+subList2.toString());
 
  }catch(Exception ee){
	  
	  System.out.println("");
	  
  }
  
  
  
  StringBuffer query2=new StringBuffer();
  // StringBuffer disp=new StringBuffer();
   query2.append(" select employeeid,componentid,test.getComponent(componentid,test.CallMaxFunction(employeeid))*round((201603-201512)/30) net from hclhrm_prod.tbl_employee_pay_data where  ");
   query2.append(" and employeeid in(select employeeid from hclhrm_prod.tbl_employee_primary ");
   query2.append(" where status=1001 and companyid=9) ");
   query2.append(" and  businessunitid=9 ");
   query2.append(" and componentid in("+component.toString()+") ");
   query2.append(" group by componentid,employeeid ");
   
   
   try{
 	     ///Qry2
 	    System.out.println("This Is in adding Query 4::");
          statement2 = connection.createStatement();
          rs2 = statement2.executeQuery(query2.toString());
          
          while (rs2.next()) {
         	 
         	 
         	 Qry2.put((String)rs2.getString(1)+"-"+(String)rs2.getString(2)+"-FR", (String)rs2.getString(3));
         	
          	//System.out.println("2nd Query::"+(String)rs1.getString(3));
          	
          }
          //System.out.println("2nd Query::"+subList2.toString());
  
   }catch(Exception ee){
 	  
 	  System.out.println("");
 	  
   }
  
   try{
	     ///Qry2
	    System.out.println("This Is in adding Query 5::");
        statement3 = connection.createStatement();
        rs3 = statement3.executeQuery("select employeeid,employeesequenceno from hclhrm_prod.tbl_employee_primary where status=1001 and companyid=9");
        
        while (rs3.next()) {
       	 
       	    
        	empids.add((String)rs3.getString(1));
        	empseq.put((String)rs3.getString(1), (String)rs3.getString(2));
        	//System.out.println("2nd Query::"+(String)rs1.getString(3));
        	
        }
        //System.out.println("2nd Query::"+subList2.toString());

 }catch(Exception ee){
	  
	  System.out.println("at Last 5" +ee);
	  
 }
   
   System.out.println("at Last 5" +empids.toString());
   
  /*try{
      statement2 = connection.createStatement();
     rs2 = statement2.executeQuery(qury.toString());

}catch(Exception ee){
	  
	  System.out.println("");
	  
}*/
    /*M Code End Here */
 /*empids.add((String)rs.getString(1));
	payperiod.add((String)rs.getString(2));
	
	components.add((String)rs.getString(4) )	;
	
	BulkList  //capital
	
	subList //
	*
	*/
        java.util.Iterator itr = empids.iterator();
        java.util.Iterator itr1 = payperiod.iterator();
 		java.util.Iterator itr2 = components.iterator();
 		
 		String one="",two="",three="";
 		  while(itr.hasNext()){
 				
 			 StringBuffer buffr=new StringBuffer();
 			 one=itr.next().toString();
 			 System.out.println("Employeeid::"+one);
 			//java.util.Iterator itr1 = payperiod.iterator();
 			
 			while(itr1.hasNext()){
 				 two=itr1.next().toString();
 				// System.out.println("Payperiod::"+two);
 				//java.util.Iterator itr2 = components.iterator();
 				
 				
 				buffr.append(one);
 				buffr.append(",");
 				buffr.append(two);
 				buffr.append(",");
 			 while(itr2.hasNext()){
 				three=itr2.next().toString();
 			  System.out.println("componentData:" +three);
 			//System.out.println("get Data:"+empmap.get(two+"-"+three));
 				buffr.append(empmap.get(two+"-"+three));
 				buffr.append(",");
 			 
 			 } //third
 			
 		  } //second
 				
 			System.out.println("get Data:"+buffr.toString());
 	} //first


   /* try{   
         File pdfFile = new File(filename);
        response.setContentType("application/ms-excel");
    	response.addHeader("Content-Disposition", "attachment; filename="+filename);
    	response.setContentLength((int) pdfFile.length());
    	FileInputStream fileInputStream = new FileInputStream(pdfFile);
    	OutputStream responseOutputStream = response.getOutputStream();
    	int bytes;
    	while ((bytes = fileInputStream.read()) != -1) {
    		responseOutputStream.write(bytes);
    		}
    	 fileInputStream.close();
    	 responseOutputStream.close();
    	 
        }
   
      catch (Exception e) {
        e.printStackTrace();
        System.out.println("Problem at generating front"+e);
      }*/
    
    request.setAttribute("comptitle",comptitle);
    request.setAttribute("empcomponents",empcomponents);
    request.setAttribute("empids",empids);
    request.setAttribute("Qry2",Qry2);
    
    request.setAttribute("empseq",empseq);
    
    
    request.setAttribute("ComMods",ComMod);
    
    
    RequestDispatcher rd=request.getRequestDispatcher("TDS.jsp");
	 rd.forward (request,response);
    
    
}
     }
