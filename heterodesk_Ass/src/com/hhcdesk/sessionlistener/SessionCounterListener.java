package com.hhcdesk.sessionlistener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionCounterListener implements HttpSessionListener {

  private static int totalActiveSessions;

  public static int getTotalActiveSession(){
	  System.out.println("totalActiveSessions::"+totalActiveSessions);
	return totalActiveSessions;
  }

  public void sessionCreated(HttpSessionEvent arg0) {
	totalActiveSessions++;
	System.out.println("sessionCreated - add one session into counter");
  }

  public void sessionDestroyed(HttpSessionEvent arg0) {
	totalActiveSessions--;
	System.out.println("sessionDestroyed - deduct one session from counter");
  }
}