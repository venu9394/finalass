package com.hhcdesk.bean;

public class Bean {

	private String Username;
	private String Password;
	boolean valid;
	boolean valid1;
	public boolean isValid() {
		return valid;
	}
	public boolean isValid1() {
		return valid1;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public String getUsername() {
		
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
}
