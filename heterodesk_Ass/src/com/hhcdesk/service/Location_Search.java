package com.hhcdesk.service;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

import net.sf.json.JSONObject;

import com.hhcdesk.db.DataSource_Cls;
import com.hhcdesk.db.GetDbData;
/**
 * Servlet implementation class ReqAcpt
 */
public class Location_Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Location_Search() {
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
		String loc=request.getParameter("loc");
        System.out.println("Location Search<---------"+loc);
		
		HttpSession session=request.getSession();
		ResultSet Res=null;
		GetDbData DataObj=new GetDbData();
		java.sql.Connection con=null;
		ServletContext c = getServletContext();
		System.out.println("GetParameter"+ c.getAttribute("Venu"));
		DataSource dataSource=null;
		
		ResourceBundle bundle_info =(ResourceBundle)(c.getAttribute("bundle"));
		JSONObject Location = new JSONObject();
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
		ArrayList Loc=new ArrayList();
		int Authcode=0;
		int record=0;
		String Emp_name=null,Emp_id=null,approved_Mesg=null;
		Res=null;
	    StringBuffer validate=new StringBuffer();
	
	    validate.append(" select name from hcllcm_prod.tbl_location where locationtype=2 and status=1001 and name like '%"+loc+"%' ");
		validate.append(" limit 100 ");
		try {
			Res=(ResultSet)DataObj.FetchData(validate.toString(), "Emp_DOJ", Res ,con);
			while(Res.next()){
				
				//Loc.add("\'"+Res.getString(1)+"\'");
				Location.put(Res.getString(1), Res.getString(1));
			}  
		}catch(Exception Er){
			Location.put(" ", " ");
			System.out.println("Exception At Er::"+Er);
		}
	   //StringBuffer updateflag=new StringBuffer();
		System.out.println("Location Search" +Location.toString());
        out.println(Location);	
		
		
	}
       
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
