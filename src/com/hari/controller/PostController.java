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

import com.hari.dao.OrderDao;
import com.hari.dao.PostDao;
import com.hari.model.Order;
import com.hari.model.Post;

@RestController
@RequestMapping("/post")

public class PostController implements PostControllerImpl {
	
	private Connection con = null;
	Post mem;
	PostDao dao;
	
	@Override
	public void delete() {
		// TODO Auto-generated method stub

	}
	


	
	@Override
	@RequestMapping(value = "/Register", method = RequestMethod.POST)
	public Post register(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("productname") String productname, @RequestParam("price") int price, @RequestParam("contactnumber") int contactnumber,@RequestParam("category") String category,@RequestParam("dec") String dec) {
		// TODO Auto-generated method stub
		
          try(ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml")) {
        	  String profilepic = "productpics/" +request.getParameter("profilepic");
			int postid = 1;
			String status2 = "Active";
			dao=(PostDao)ctx.getBean("mdao2");
			mem = new Post(postid, profilepic, productname, price, contactnumber,category,dec);
			int status=dao.registerPost(mem);
			String url;
			if(status == -99) {
				System.out.println("Post failed to add");
				url = "http://localhost:8080/PAF/AddPost.jsp";
			}
			else{
				System.out.println("Post added ");
				url = "http://localhost:8080/PAF/Home.jsp?memI="+productname;
			}
			
			redirect(url, request, response);
			return mem;
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

	
	@RequestMapping(value = "/postProfile", method = RequestMethod.GET)
	@Override
	public Post postProfile(HttpServletRequest request, HttpServletResponse response,   @RequestParam("postid") int postid) {
		// TODO Auto-generated method stub
		try(ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml")) {
			String url;
			dao=(PostDao)ctx.getBean("mdao2");
			mem = dao.getPost(postid);
			url = "http://localhost:8080/PAF/inventory.jsp?profilepic = "+mem.getProfileepic()+"&productname="+mem.getProductname()+"&price="+mem.getPrice()+"&contactnumber="+mem.getContactnumber()+"&category="+mem.getCategory()+"";
			redirect(url, request, response);
			return mem;
		}
		catch (Exception e) {
			System.out.println("Error: " + e);
			return mem;
		}
	}
	
	@Override
	@RequestMapping(value = "/generateUpdateForm", method = RequestMethod.GET)
	public Post generateUpdateForm(int postid, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try(ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml")) {
			dao=(PostDao)ctx.getBean("mdao2");
			mem = dao.getPost(postid);
			String url, msg;
			if(mem == null) {
				System.out.println("Something went wrong");
				msg = "Something went wrong";
				url = "http://localhost:8080/PAF/editPostProfile.jsp?msg="+msg;
			}
			else{
				System.out.println("Post found");
				//String number = Integer.toString(mem.getContactNumber());
				url = "http://localhost:8080/PAF/editPostProfile.jsp?productname="+mem.getProductname()+"&price="+mem.getPrice()+"&contactnumber="+mem.getContactnumber();
				//url = "http://localhost:8081/PAF/editMyProfile.jsp?type="+mem.getType()+"&password="+mem.getPassword()+"&address="+mem.getAddress()+"&mail="+mem.getMail()+"&num="+mem.getContactNumber()+"&status="+mem.getStatus();
			}
			redirect(url, request, response);
			return mem;
		}
		catch (Exception e) {
			System.out.println("Error: " + e);
			String msg = "Something went wrong";
			String url = "http://localhost:8081/PAF/editPostProfile.jsp?msg="+msg;
			redirect(url, request, response);
			return null;
		}
	}


	@Override
	@RequestMapping(value = "/updatePost", method = RequestMethod.POST)
	public void update(HttpServletRequest request, HttpServletResponse response,@RequestParam("productname") String productname, @RequestParam("price") int price,@RequestParam("contactnumber") int contactnumber) throws ServletException, IOException {
		
		try(ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml")) {
			dao=(PostDao)ctx.getBean("mdao2");
			mem = new Post(1, "null", productname, price, contactnumber,"null","null");
			int status=dao.updatePost(mem);
			String url = "";
			if(status == -99) {
				System.out.println("Member failed to update");
				url = "http://localhost:8080/PAF/UpdOrdfail.jsp";
			}
			else{
				System.out.println("Member updated");
				url = "http://localhost:8080/PAF/UpdOrdSucc.jsp";
			}
			redirect(url, request, response);
		}
		catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}

	@Override
	@RequestMapping(value = "/deactivate", method = RequestMethod.GET)
	public void deactivate(@RequestParam("postid") int postid, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		try(ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml")) {
			dao=(PostDao)ctx.getBean("mdao2");
			String url = "";
			String msg = "Your Order is deleted";
			int status = dao.deactivate(postid);
			if(status == -99) {
				System.out.println("Post failed to deactivate");
			}
			else{
				System.out.println("Post deactivated");
				url = "http://localhost:8080/PAF/Home.jsp?msg="+msg;
			}
			redirect(url, request, response);
		}
		catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}

	
}
