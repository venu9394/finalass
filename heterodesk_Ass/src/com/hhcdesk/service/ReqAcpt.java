package com.hhcdesk.service;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.hhcdesk.db.DataSource_Cls;
import com.hhcdesk.db.GetDbData;
/**
 * Servlet implementation class ReqAcpt
 */
public class ReqAcpt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReqAcpt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String Atten_Req_Message="Successfully Submit.";
		System.out.println("Hit ReqActp 1");
		PrintWriter out = response.getWriter();
		int rs=0;
		//System.out.println(request.getParameter("id")+"<---------");
		String empid=request.getParameter("id");
		String flagupdate=request.getParameter("flag");
		String rid=request.getParameter("rid");
		String randomid=request.getParameter("randomid");
		
		String comments=request.getParameter("comment");
		
		HttpSession session=request.getSession();
		String User_id=request.getParameter("User_id");
		String User_pwd=request.getParameter("User_pwd");
		
		//out.write(Atten_Req_Message);
		//out.write("~"+flagupdate);
		//out.write("~Mahesh");
		ResultSet Res=null;
		GetDbData DataObj=new GetDbData();
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
		
		int Authcode=0;
		int record=0;
		String Emp_name=null,Emp_id=null,approved_Mesg=null;
		Res=null;
	    StringBuffer validate=new StringBuffer();
		validate.append(" SELECT COUNT(*),A.EMPLOYEECODE,B.CALLNAME FROM  ");
		validate.append(" HCLHRM_PROD.TBL_EMPLOYEE_LOGIN A ");
		validate.append(" LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PRIMARY B ON A.EMPLOYEECODE=B.EMPLOYEESEQUENCENO ");
		validate.append(" WHERE EMPLOYEECODE="+User_id);
		validate.append(" AND PASSWORD=md5('"+User_pwd+"') ");
		
		
		try {
			Res=(ResultSet)DataObj.FetchData(validate.toString(), "Emp_DOJ", Res ,con);
			if(Res.next()){
				
				Authcode=Res.getInt(1); 
				Emp_id=Res.getString(2);
				Emp_name=Res.getString(3);
				
			}  
		}catch(Exception Er){
			System.out.println("Exception At Er::"+Er);
		}
		
		
		StringBuffer updateflag=new StringBuffer();
	
   if(Authcode>0){
		try{
			updateflag.append("UPDATE hclhrm_prod_others.tbl_emp_attn_req set FLAG='"+flagupdate+"',comments= '"+comments+"',approvedby='"+Emp_id+"',approvedname='"+Emp_name+"' , MAIL_STATUS='N' WHERE FLAG='P' AND EMPLOYEEID="+empid+" AND RID="+rid+" AND RANDOMID="+randomid+"");
			System.out.println(updateflag.toString());
			PreparedStatement ps=con.prepareStatement(updateflag.toString());
			try{
				    rs=ps.executeUpdate();
					
					if(rs==0){
						 Atten_Req_Message="Faild your request please try again with valid data/contact system admin";
					}
					
			}catch(Exception e){
				 System.out.println("record "+ record);
				 Atten_Req_Message="Faild to submit your request/try again(OR)contact system admin  ";
				e.printStackTrace();
			}
			
		}catch(Exception err){
			err.printStackTrace();
		}
		
		try{
			
			System.out.println("rs  ::: "+rs);
			if(rs>0){
				
				Atten_Req_Message="Successfully Submit.";
				System.out.println("Done");
			}else{
				
				try {
					Res=(ResultSet)DataObj.FetchData("select IF(FLAG='P','Pending',if(FLAG='A','APPROVED','REJECTED')) FLAG from hclhrm_prod_others.tbl_emp_attn_req WHERE EMPLOYEEID="+empid+" AND RID="+rid+" AND RANDOMID="+randomid+" ", "Status of leave", Res ,con);
					if(Res.next()){
						
						approved_Mesg=Res.getString(1);
						
						Atten_Req_Message="Your are already "+approved_Mesg;
					}  
				}catch(Exception Er){
					System.out.println("Exception At Er::"+Er);
					Atten_Req_Message="Unable to Process your request right now";
				}
				
				
			}
			
			}catch(Exception g){
				
				Atten_Req_Message="Unable to Process your request right now";
				g.printStackTrace();
				System.out.println("Fail");
			}
		try{
			
			if(con!=null){
				con.close();
			}
		}catch(Exception er){
			er.printStackTrace();
		}
		
		
   }else{
	   Atten_Req_Message="Invalid Password/User id";
   }
		
		
		
   out.write(Atten_Req_Message);	
		
		
	}
       
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
