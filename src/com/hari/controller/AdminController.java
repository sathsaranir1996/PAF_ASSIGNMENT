package com.hari.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hari.dao.MemberDao;
import com.hari.dao.adminloginDao;
import com.hari.model.Admin;
import com.hari.model.Member;

@RestController
@RequestMapping("/admin")
public class AdminController implements AdminImpl{
	
	private Connection con = null;
	Admin adm;
	adminloginDao dao;
	
	@Override
	public void delete() {
		// TODO Auto-generated method stub

	}

	@Override
	@RequestMapping(value = "/Register", method = RequestMethod.POST)
	public Admin register(HttpServletRequest request, HttpServletResponse response, @RequestParam("aname") String aname, @RequestParam("password") String password) {
		// TODO Auto-generated method stub
		
		try(ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml")) {
			int idadmin = 1;
			String status2 = "Active";
			dao=(adminloginDao)ctx.getBean("mdao5");
			adm = new Admin(idadmin, aname, password);
			int status=dao.registerAdmin(adm);
			String url;
			if(status == -99) {
				System.out.println("Admin failed to add");
				url = "http://localhost:8080/PAF/AddMemFail.jsp";
			}
			else{
				System.out.println("Admin added");
				url = "http://localhost:8080/PAF/AddAdmSucc.jsp?aname="+aname;
			}
			
			redirect(url, request, response);
			return adm ;
		}
		catch (Exception e) {
			System.out.println("Error: " + e);
			return null;
		}
	}

	@Override
	@RequestMapping(value = "/redirect", method = RequestMethod.POST)
	public void redirect(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try{
        	response.sendRedirect(url);
        }
        catch (Exception e) {
            // TODO: handle exception
        	//response.sendRedirect("http://localhost:8081/PAF/logReg.jsp");
            System.out.println("Failed");
        }
	}

	

}
