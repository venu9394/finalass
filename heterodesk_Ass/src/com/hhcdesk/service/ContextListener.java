package com.hhcdesk.service; 
import java.io.File;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.ResourceBundle.Control;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.PropertyConfigurator;
 

public class ContextListener implements ServletContextListener {
 
    /**
     * Initialize log4j when the application is being started
     */
    public void contextInitialized(ServletContextEvent event) {
        // initialize log4j here
    	
    	System.out.println("Context Listener Invoked");
        ServletContext context = event.getServletContext();
        
        System.out.println("context.getRealPath"+context.getRealPath(""));
        String fullPath = context.getRealPath("") + File.separator + "log4j.properties";
        PropertyConfigurator.configure(fullPath);
       
        ResourceBundle.Control rbc =ResourceBundle.Control.getControl(Control.FORMAT_DEFAULT);
		ResourceBundle bundle =ResourceBundle.getBundle("sqlqueries", Locale.US, rbc);
		context.setAttribute("bundle", bundle);
    
		
    }
		
		
    public void contextDestroyed(ServletContextEvent event) {
    	ServletContext context = event.getServletContext();
    	context.removeAttribute("dataSource");
    	System.out.println("CONTEXT DESTROYED");
    	
        // do nothing
    }  
}