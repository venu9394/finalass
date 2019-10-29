package com.hhcdesk.service;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;








import net.sf.json.JSONObject;

import com.mysql.jdbc.Statement;
public class Downloads extends HttpServlet {
	
	    private DataSource dataSource;
	    private Connection connection;
	    private Statement statement;
	    boolean ConnFlag=true;
	public Downloads() {
        super();
     }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   HttpSession session=request.getSession();
	   ServletContext c = getServletContext();
	   JSONObject HR_LINKS= new JSONObject();
	   JSONObject AC_LINKS= new JSONObject();
	   JSONObject SelectBox= new JSONObject();
	   ResourceBundle bundle_info =(ResourceBundle)(c.getAttribute("bundle"));
	  /* session.setAttribute("Notice" ,"N");
	   System.out.println("@ Download Page............!");*/
	   String username=(String)session.getAttribute("EMP_ID");
	   String link1=bundle_info.getString("HR_LINK_UPDATE").toString();
	   String link2=bundle_info.getString("HR_LINK_HR_UP_APP").toString();
	   String link3=bundle_info.getString("HR_LINK_HR_RS_APP").toString();
	   String link4=bundle_info.getString("HR_LINK_HR_BU_ATTENDANCE").toString();
	   String link5=bundle_info.getString("HR_LINK_BASIC_PRIVILEGES").toString();
	   String link6=bundle_info.getString("HR_LINK_DESIGNATION_MASTERS").toString();
	   String link7=bundle_info.getString("A/C_LINK").toString();
	   String link8=bundle_info.getString("A/C_ePay").toString();
	   String link9=bundle_info.getString("HR_LINK_DIVISION_ATTEANDANCE").toString();
	   
	   
	   
	   String SelectBoxMap=null;
	   boolean selBox=false;
	   try{
		   
		   SelectBoxMap=bundle_info.getString(""+username+"").toString();
		   selBox=true;
		   
	   }catch(Exception Ed){
		   selBox=false;
		   System.out.println("Error At Download File::"+Ed);
	   }
	try{
	   if(selBox){
		   
		StringTokenizer selBoxToken=new StringTokenizer(SelectBoxMap , ",");   
		while(selBoxToken.hasMoreTokens()){
			String Token=selBoxToken.nextToken();
			String Tokenx[]=Token.split("#");
			SelectBox.put(Tokenx[0], Tokenx[1]);
		}
		  
	   }
		   
	   }catch(Exception edr){
		   System.out.println("Error at Download::"+edr);
		   
	   }
	   
	   
	   System.out.println(link1 +"~~"+link2 +"~~"+link3+"~"+username);
	   
	  
	   HR_LINKS.put("LINK1", "none");
	   HR_LINKS.put("LINK2", "none");
	   HR_LINKS.put("LINK3", "none");
	   HR_LINKS.put("LINK4", "none");
	   HR_LINKS.put("LINK5", "none");
	   HR_LINKS.put("LINK6", "none");
	   AC_LINKS.put("LINK7", "none");
	   AC_LINKS.put("LINK8", "none");
	   HR_LINKS.put("LINK9", "none");
	   
	   boolean linkroutflag=false;
	try{
	   if(username!=null && link1.indexOf(username)!=-1){
		   
		   HR_LINKS.put("LINK1", " "); 
		   linkroutflag=true;
	   }if(username!=null && link2.indexOf(username)!=-1){
		   HR_LINKS.put("LINK2", " ");
		   linkroutflag=true;
	   }if(username!=null && link3.indexOf(username)!=-1){
		   linkroutflag=true;
		   HR_LINKS.put("LINK3", " "); 
	   }
	   if(username!=null && link4.indexOf(username)!=-1){
		   linkroutflag=true;
		   HR_LINKS.put("LINK4", " "); 
	   }
	   if(username!=null && link5.indexOf(username)!=-1){
		   linkroutflag=true;
		   HR_LINKS.put("LINK5", " "); 
		   
	   }
	   
	   if(username!=null && link6.indexOf(username)!=-1){
		   linkroutflag=true;
		   HR_LINKS.put("LINK6", " "); 
		   
	   }
	   
	   if(username!=null && link7.indexOf(username)!=-1){
		   linkroutflag=true;
		   AC_LINKS.put("LINK7", " "); 
		   
	   }if(username!=null && link8.indexOf(username)!=-1){
			linkroutflag=true;
			AC_LINKS.put("LINK8", " "); 

		}
	   if(username!=null && link9.indexOf(username)!=-1){
		   linkroutflag=true;
		   HR_LINKS.put("LINK9", " "); 
		   
	   }
	   
	}catch(Exception Erd){
		
		System.out.println("Error at If Condition::"+Erd);
		
	}
	   session.setAttribute("SelectBox",""+SelectBox.toString()+"");
	   
	   session.setAttribute("HR_LINKS",""+HR_LINKS.toString()+"");
	   session.setAttribute("AC_LINKS",""+AC_LINKS.toString()+"");
	   
	   System.out.println("PageRouting"+HR_LINKS);
	   //hhcl_downloads.jsp
		String page="/hhcl_downloads.jsp";
		String Routing=request.getParameter("Routing");
		if(Routing!=null && Routing.equalsIgnoreCase("UPDATE") && linkroutflag==false){
			page="/Hhcl_EmployeeUpdate.jsp";
		}else if(Routing!=null && Routing.equalsIgnoreCase("UPDATE") && linkroutflag==true){
		
			page="/Hhcl_EmployeeUpdate_by_hr.jsp";
			
		}else if(Routing!=null && Routing.equalsIgnoreCase("UPDATE_emp") && linkroutflag==true){
		
			page="/Hhcl_EmployeeUpdate_by_hr_emp.jsp";
			
		}else if(Routing!=null && Routing.equalsIgnoreCase("HR_APP_RESIGNATION") && linkroutflag==true){
		
			page="/Hr_Manager_reg_approvals.jsp";
			
		}else if(Routing!=null && Routing.equalsIgnoreCase("APPROVAL")){
			
			page="/Hhcl_HR_Approvel_List.jsp";
		}else if(Routing!=null && Routing.equalsIgnoreCase("REGION")){
			
			page="/Resignation";
		}else if(Routing!=null && Routing.equalsIgnoreCase("LeaveQuota")){
			page="/Hr_LeaveQuota.jsp";
		}else if(Routing!=null && Routing.equalsIgnoreCase("Designation")){
			page="/Hr_DesignationMaster.jsp";
		}
		else if(Routing!=null && Routing.equalsIgnoreCase("Accounts")){
			page="/AccountsHome.jsp";
		}else if(Routing!=null && Routing.equalsIgnoreCase("EmpDeclare")){
			page="/ITDeclare.jsp";
		}else if(Routing!=null && Routing.equalsIgnoreCase("EmpReDeclare")){
			page="/EmpReDeclare.jsp";
		}
		else if(Routing!=null && Routing.equalsIgnoreCase("EmpForecast")){
			page="/EmpReDeclare.jsp";
		}
		else if(Routing!=null && Routing.equalsIgnoreCase("empattendanceReport")){
			page="/buattendance.jsp";
		}
		
		
		
		
		
		
		//Tds Start
		else if(Routing!=null && Routing.equalsIgnoreCase("TDS") && linkroutflag==true){
			
			try{
				
				String EMP_TDS_LOC=bundle_info.getString(username+"_LOC").toString();
				String EMP=request.getParameter("EMP");
				if(EMP==null){
					EMP="BU";
				}
				 session.setAttribute("EMP", EMP);
				if(EMP_TDS_LOC!=null){
				  session.setAttribute("EMP_TDS_LOC", EMP_TDS_LOC);
				}else{
					session.setAttribute("EMP_TDS_LOC", "NA");
				}
				
			}catch(Exception err){
				System.out.println("Exception At TDS Load Link::"+ err);
				session.setAttribute("EMP_TDS_LOC", "NA");
			}
			
			page="tds/Hhcl_EmployeeUpdate_by_hr.jsp";
			
		}
		
		//TDS End
		else if(Routing!=null && Routing.equalsIgnoreCase("TDSChallan") && linkroutflag==true){
			page="tds/Challan/ChallanForm.jsp";
		}
		
		
		else{
			page="/hhcl_downloads.jsp";
		}
		
		System.out.println("HR_LINKS:::--->"+HR_LINKS.toString());
	   request.getRequestDispatcher(""+page+"").forward(request, response);
	}
}
