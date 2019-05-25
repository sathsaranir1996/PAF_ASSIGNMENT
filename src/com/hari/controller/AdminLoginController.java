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
@RequestMapping("/AdminLogin")
public class AdminLoginController implements AdminLoginControllerImpl {

	
	
	private Connection con = null;
	adminloginDao dao;
	Admin adm;
	
	@Override
	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public void login(@RequestParam("aname") String aname, @RequestParam("password") String password, HttpServletRequest request, HttpServletResponse response) {
		String msg = "";
		String url = "";
		try(ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml")) {
			dao=(adminloginDao)ctx.getBean("mdao5");
			int x = dao.adminloginValidate(aname, password);
			if (x == 0) {
				msg = "Login invalid";
				url = "http://localhost:8080/PAF/adminlog.jsp?msg="+msg;
				redirect(url, request, response);
			}
			else if(x == -99){
				msg = "Something went wrong";
				url = "http://localhost:8080/PAF/adminlog.jsp?msg="+msg;
				redirect(url, request, response);
			}
			else {
				url = "http://localhost:8080/PAF/ProxyO.jsp?name="+aname;
				redirect(url, request, response);
			}
		}
		catch (Exception e) {
			msg = "Something went wrong";
			url = "http://localhost:8080/PAF/adminlog.jsp?msg="+msg;
			System.out.println("Error: " + e);
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
	
	
	

