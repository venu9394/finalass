package com.hhcdesk.sessionlistener;
import javax.servlet.ServletContext;  
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;  
import javax.servlet.http.HttpSessionListener;  
  
public class CountUserListener implements HttpSessionListener{  
    ServletContext ctx=null;  
    static int total=0,current=0; 
    java.sql.Connection con=null;
    public void sessionCreated(HttpSessionEvent e) {  
    total++;  
    current++;  
      
    ctx=e.getSession().getServletContext();  
    	ctx.setAttribute("totalusers", total);  
    	ctx.setAttribute("currentusers", current);  
      
    	HttpSession session=e.getSession();
    	
    	session.setAttribute("totalusers", total);
		session.setAttribute("currentusers", current);
		
    	        
    	System.out.println("currentusers:::" +current);
    }  
  
    public void sessionDestroyed(HttpSessionEvent e) {  
        current--;  
        
        
        HttpSession session=e.getSession();
        String UserName=session.getId();
        String USER_ID=(String) session.getAttribute("EMP_ID");
        System.out.println(USER_ID +"~Session Killing.........!");
        
        try {
			//conn =dataSource.getConnection();
			con=(java.sql.Connection)session.getAttribute("ConnectionObj");
			System.out.println(con+"~Connection Object");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
				e1.printStackTrace();
		}
        
       try{
			
			if(con!=null){
				con.close();
			}
		}catch(Exception er){
			er.printStackTrace();
		}
        System.out.println(con+"~Connection Object killing");
		System.out.println("Step 1");
		
        try{
        	 
        	ctx=e.getSession().getServletContext(); 
        	if(USER_ID!=null)
            
            System.out.println(ctx.getAttribute(USER_ID)+"Context Attribute ");
        	ctx.removeAttribute(USER_ID);
        	
          }catch(Exception ER){
        	System.out.println("ER::"+ER);
         }
        System.out.println(ctx.getAttribute(USER_ID)+"Context Attribute Killing..!");
        
       // System.out.println(USER_ID + ":::UserName==Session Disroid:::"+UserName);
        ctx.setAttribute("currentusers",current);  
    }  
  
}  