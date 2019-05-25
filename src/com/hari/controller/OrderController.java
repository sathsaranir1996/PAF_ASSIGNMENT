package com.hari.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.swing.text.Document;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hari.dao.*;
import com.hari.model.Member;
import com.hari.model.Order;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/order")

public class OrderController implements OrderControllerImpl {
	
	private Connection con = null;
	Order mem;
	OrderDao dao;
	
	@Override
	public void delete() {
		// TODO Auto-generated method stub

	}

	@Override
	@RequestMapping(value = "/Register", method = RequestMethod.POST)
	public Order register(HttpServletRequest request, HttpServletResponse response, @RequestParam("productname") String productname, @RequestParam("quantity") int quantity,  @RequestParam("paymenttype") String paymenttype) {
		// TODO Auto-generated method stub
		
		try(ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml")) {
			int orderid = 1;
			String status2 = "Active";
			dao=(OrderDao)ctx.getBean("mdao1");
			mem = new Order(orderid, productname, quantity, paymenttype);
			int status=dao.registerOrder(mem);
			String url;
			if(status == -99) {
				System.out.println("Order failed to add");
				url = "http://localhost:8080/PAF/AddOrder.jsp";
			}
			else{
				System.out.println("Order added to cart");
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

	@RequestMapping(value = "/orderProfile", method = RequestMethod.GET)
	@Override
	public Order orderProfile(HttpServletRequest request, HttpServletResponse response,   @RequestParam("orderid") int orderid) {
		// TODO Auto-generated method stub
		try(ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml")) {
			String url;
			dao=(OrderDao)ctx.getBean("mdao1");
			mem = dao.getOrder(orderid);
			url = "http://localhost:8080/PAF/orderprofile.jsp?productname="+mem.getProductname()+"&quantity="+mem.getQuantity()+"&paymenttype="+mem.getPaymenttype();
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
	public Order generateUpdateForm(int orderid, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try(ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml")) {
			dao=(OrderDao)ctx.getBean("mdao1");
			mem = dao.getOrder(orderid);
			String url, msg;
			if(mem == null) {
				System.out.println("Something went wrong");
				msg = "Something went wrong";
				url = "http://localhost:8080/PAF/editOrderProfile.jsp?msg="+msg;
			}
			else{
				System.out.println("Order found");
				//String number = Integer.toString(mem.getContactNumber());
				url = "http://localhost:8080/PAF/editOrderProfile.jsp?productname="+mem.getProductname()+"&quantity="+mem.getQuantity();
				//url = "http://localhost:8081/PAF/editMyProfile.jsp?type="+mem.getType()+"&password="+mem.getPassword()+"&address="+mem.getAddress()+"&mail="+mem.getMail()+"&num="+mem.getContactNumber()+"&status="+mem.getStatus();
			}
			redirect(url, request, response);
			return mem;
		}
		catch (Exception e) {
			System.out.println("Error: " + e);
			String msg = "Something went wrong";
			String url = "http://localhost:8081/PAF/editOrderProfile.jsp?msg="+msg;
			redirect(url, request, response);
			return null;
		}
	}


	

	
	@Override
	@RequestMapping(value = "/updateOrder", method = RequestMethod.POST)
	public void update(HttpServletRequest request, HttpServletResponse response,@RequestParam("productname") String productname, @RequestParam("quantity") int quantity) throws ServletException, IOException {
		
		try(ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml")) {
			dao=(OrderDao)ctx.getBean("mdao1");
			mem = new Order(1, productname, quantity, "null");
			int status=dao.updateOrder(mem);
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
	public void deactivate(@RequestParam("orderid") int orderid, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		try(ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml")) {
			dao=(OrderDao)ctx.getBean("mdao1");
			String url = "";
			String msg = "Your Order is deleted";
			int status = dao.deactivate(orderid);
			if(status == -99) {
				System.out.println("Order failed to deactivate");
			}
			else{
				System.out.println("Order deactivated");
				url = "http://localhost:8080/PAF/Home.jsp?msg="+msg;
			}
			redirect(url, request, response);
		}
		catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}

}
	/**{
		
		try(ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml")) {
			String url;
			dao=(OrderDao)ctx.getBean("mdao1");
			url = "http://localhost:8080/PAF/orderprofile.jsp?productname="+mem.getProductname()+"&address="+mem.getAddress()+"&contactnumber="+mem.getContactNumber()+"&quantity="+mem.getQuantity();
			
			return mem;
		}
		catch (Exception e) {
			System.out.println("Error: " + e);
			return mem;
		}
	}

	/**
	@Override
	public void update(HttpServletRequest request, HttpServletResponse response, String productname, String addess,
			int contactnumber, String quantity) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Order generateUpdateForm(String name, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deactivate(String name, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}


/**	@Override
	@RequestMapping(value = "/generateUpdateForm", method = RequestMethod.GET)
	public Order generateUpdateForm(@RequestParam("name") String name, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try(ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml")) {
			dao=(MemberDao)ctx.getBean("mdao");
			mem = dao.getMem(name);
			String url, msg;
			if(mem == null) {
				System.out.println("Something went wrong");
				msg = "Something went wrong";
				url = "http://localhost:8081/PAF/editMyProfile.jsp?msg="+msg;
			}
			else{
				System.out.println("Member found");
				String number = Integer.toString(mem.getContactNumber());
				url = "http://localhost:8081/PAF/editMyProfile.jsp?type="+mem.getType()+"&address="+mem.getAddress()+"&mail="+mem.getMail()+"&num="+number+"&password="+mem.getPassword();
				//url = "http://localhost:8081/PAF/editMyProfile.jsp?type="+mem.getType()+"&password="+mem.getPassword()+"&address="+mem.getAddress()+"&mail="+mem.getMail()+"&num="+mem.getContactNumber()+"&status="+mem.getStatus();
			}
			redirect(url, request, response);
			return mem;
		}
		catch (Exception e) {
			System.out.println("Error: " + e);
			String msg = "Something went wrong";
			String url = "http://localhost:8081/PAF/editMyProfile.jsp?msg="+msg;
			redirect(url, request, response);
			return null;
		}
	}

	@Override
	@RequestMapping(value = "/updateMemeber", method = RequestMethod.POST)
	public void update(HttpServletRequest request, HttpServletResponse response,  @RequestParam("name") String name,  @RequestParam("type") String type, @RequestParam("add") String add, @RequestParam("mail") String mail, @RequestParam("cno") int cno) throws ServletException, IOException {
		
		try(ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml")) {
			dao=(MemberDao)ctx.getBean("mdao");
			mem = new Order(1, name, "null", type, add, mail, cno, "null");
			int status=dao.updateEmployee(mem);
			String url = "";
			if(status == -99) {
				System.out.println("Member failed to update");
			}
			else{
				System.out.println("Member updated");
				url = "http://localhost:8081/PAF/UpdMemSucc.jsp";
			}
			redirect(url, request, response);
		}
		catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}

	@Override
	@RequestMapping(value = "/deactivate", method = RequestMethod.GET)
	public void deactivate(@RequestParam("name") String name, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		try(ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml")) {
			dao=(MemberDao)ctx.getBean("mdao");
			String url = "";
			String msg = "Your account is deactivated";
			int status = dao.deactivate(name);
			if(status == -99) {
				System.out.println("Member failed to deactivate");
			}
			else{
				System.out.println("Member deactivated");
				url = "http://localhost:8081/PAF/logReg.jsp?msg="+msg;
			}
			redirect(url, request, response);
		}
		catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}

	@Override
	public void update(HttpServletRequest request, HttpServletResponse response, String productname, String addess,
			int contactnumber, String quantity) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}**/
	
	/*
	@Override
	@RequestMapping(value = "/anc", method = RequestMethod.POST)
    public Member insert(@RequestParam("name") String name, @RequestParam("password") String password, @RequestParam("type") String type, @RequestParam("add") String add, @RequestParam("mail") String mail, @RequestParam("cno") int cno) {
        int id = 1;
		Member mem = new Member(id, name, password, type, add, mail, cno);

        String url = "jdbc:mysql://localhost/test";
        String username = "root";
        String pw = "";
        
        try{
        	Class.forName("com.mysql.jdbc.Driver");
        	con = DriverManager.getConnection(url, username, pw);
        	String sql="INSERT INTO `member`(`mid`, `mname`, `password`, `mtype`) VALUES ('"+id+"','"+name+"','"+password+"', '"+type+"')";
            PreparedStatement pst=con.prepareStatement(sql);
            pst.executeUpdate();
            System.out.println("Member added...");
        }
        catch(Exception e) {
        	System.out.println(e);
        }
        
        return mem;
    }
*/

