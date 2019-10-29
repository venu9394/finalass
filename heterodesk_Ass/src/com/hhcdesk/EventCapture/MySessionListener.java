package com.hhcdesk.EventCapture;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
 
public class MySessionListener implements HttpSessionListener {
     
	
	    public static List sessions = new ArrayList();
	    public static  Map UserMap = new HashMap();
	    
	    public MySessionListener()
	    {
	    }
       
	   
	    
	    public void sessionCreated(HttpSessionEvent event)
	    {
	        HttpSession session = event.getSession();
	        sessions.add(session.getId());
	        
	        System.out.println("session.getId()::" +session.getId());
	     
	        session.setAttribute("counter", this);
	    }

	    public void sessionDestroyed(HttpSessionEvent event)
	    {
	        //HttpSession session = event.getSession();
	       // String SessionId=session.getId();
	        
	       // System.out.println("session.getId()-Remove::" +SessionId);
	        
	       // System.out.println("RemovedID::"+MySessionListener.UserMap.get(SessionId));
	        
	        try{
	       // MySessionListener.UserMap.remove(MySessionListener.UserMap.get(SessionId));
	        
	       // MySessionListener.UserMap.remove(SessionId);
	        
         //   System.out.println("RemovedID::"+MySessionListener.UserMap.get(SessionId));	 
            
	        }catch(Exception Err){
	        	
	        	System.out.println("Err::"+Err);
	        	
	        }
	       // sessions.remove(session.getId());
	      //  session.setAttribute("counter", this);
	    }

	    public int getActiveSessionNumber()
	    {
	        return sessions.size();
	    }
	
	
	/*public void sessionCreated(HttpSessionEvent event) {
		System.out.println("A new session is created:" +event.toString());
		
		
	}
 
	public void sessionDestroyed(HttpSessionEvent event) {
		System.out.println("session is destroyed");
	}*/
 
}