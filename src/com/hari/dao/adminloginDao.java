package com.hari.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import com.hari.model.Admin;
import com.hari.model.Member;

public class adminloginDao {
	private JdbcTemplate jdbcTemplate;
	
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public int registerAdmin(Admin a){
		try {
			String query="INSERT INTO `admin`(`aname`, `password`) VALUES ('"+a.getAname()+"','"+a.getPassword()+"')";
			return jdbcTemplate.update(query);
		}
		catch(Exception e) {
			System.out.println("Error: " + e);
			return -99;
		}
	}

	
	public int adminloginValidate(String aname, String password) {
		try {
			return this.jdbcTemplate.queryForInt("SELECT count(*) FROM `admin` WHERE aname = '"+aname+"' AND password = '"+password+"'");
		}
		catch(Exception e) {
			System.out.println(e);
			return -99;
		}
	}

	
	
	
}
