package com.hari.model;

public class Admin {
	
	private int idadmin;
	private String aname;
	private String password;
	
	public Admin() {
		super();
	}
	
	public Admin(int idadmin, String aname, String password) {
		this.idadmin = idadmin;
		this.aname = aname;
		this.password = password;
	}

	public int getIdadmin() {
		return idadmin;
	}

	public void setIdadmin(int idadmin) {
		this.idadmin = idadmin;
	}

	public String getAname() {
		return aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
}
