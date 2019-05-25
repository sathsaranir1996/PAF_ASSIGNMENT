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

import com.hari.dao.PaymentDao;
import com.hari.model.Payment;

@RestController
@RequestMapping("/payment")

public class PaymentController implements PaymentControllerImpl {
	
	
	private Connection con = null;
	Payment mem;
	PaymentDao dao;
	
	@Override
	public void delete() {
		// TODO Auto-generated method stub

	}

	@Override
	@RequestMapping(value = "/Register", method = RequestMethod.POST)
	public Payment register(HttpServletRequest request, HttpServletResponse response, @RequestParam("productname") String productname, @RequestParam("paymenttype") String paymenttype, @RequestParam("paymentstatus") String paymentstatus) {
		// TODO Auto-generated method stub
		
		try(ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml")) {
			int paymentid = 1;
			String status2 = "Active";
			dao=(PaymentDao)ctx.getBean("mdao4");
			mem = new Payment(paymentid, productname, paymenttype, paymentstatus);
			int status=dao.registerPayment(mem);
			String url;
			if(status == -99) {
				System.out.println("payment failed to add");
				url = "http://localhost:8080/PAF/AddPayment.jsp";
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
	public Payment paymentProfile(HttpServletRequest request, HttpServletResponse response,   @RequestParam("paymentid") int paymentid) {
		// TODO Auto-generated method stub
		try(ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml")) {
			String url;
			dao=(PaymentDao)ctx.getBean("mdao4");
			mem = dao.getPayment(paymentid);
			url = "http://localhost:8080/PAF/paymentprofile.jsp?productname="+mem.getProductname()+"&paymentstatus="+mem.getPaymentstatus()+"&paymenttype="+mem.getPaymenttype();
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
	public Payment generateUpdateForm(int paymentid, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try(ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml")) {
			dao=(PaymentDao)ctx.getBean("mdao4");
			mem = dao.getPayment(paymentid);
			String url, msg;
			if(mem == null) {
				System.out.println("Something went wrong");
				msg = "Something went wrong";
				url = "http://localhost:8080/PAF/editpayment.jsp?msg="+msg;
			}
			else{
				System.out.println("payment found");
				//String number = Integer.toString(mem.getContactNumber());
				url = "http://localhost:8080/PAF/editpayment.jsp?productname="+mem.getProductname()+"&paymenttype="+mem.getPaymenttype()+"&paymentstatus="+mem.getPaymentstatus();
				//url = "http://localhost:8081/PAF/editMyProfile.jsp?type="+mem.getType()+"&password="+mem.getPassword()+"&address="+mem.getAddress()+"&mail="+mem.getMail()+"&num="+mem.getContactNumber()+"&status="+mem.getStatus();
			}
			redirect(url, request, response);
			return mem;
		}
		catch (Exception e) {
			System.out.println("Error: " + e);
			String msg = "Something went wrong";
			String url = "http://localhost:8081/PAF/editpayment.jsp?msg="+msg;
			redirect(url, request, response);
			return null;
		}
	}


	

	
	@Override
	@RequestMapping(value = "/updatepayment", method = RequestMethod.POST)
	public void update(HttpServletRequest request, HttpServletResponse response,@RequestParam("productname") String productname, @RequestParam("paymenttype") String paymenttype,@RequestParam("paymentstatus") String paymentstatus) throws ServletException, IOException {
		
		try(ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml")) {
			dao=(PaymentDao)ctx.getBean("mdao4");
			mem = new Payment(1, productname, paymenttype, paymentstatus);
			int status=dao.updatePayment(mem);
			String url = "";
			if(status == -99) {
				System.out.println("payment failed to update");
				url = "http://localhost:8080/PAF/UpdOrdfail.jsp";
			}
			else{
				System.out.println("payment updated");
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
	public void deactivate(@RequestParam("paymentid") int paymentid, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		try(ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml")) {
			dao=(PaymentDao)ctx.getBean("mdao4");
			String url = "";
			String msg = "Your Order is deleted";
			int status = dao.deactivate(paymentid);
			if(status == -99) {
				System.out.println("payment failed to deactivate");
			}
			else{
				System.out.println("payment deactivated");
				url = "http://localhost:8080/PAF/Home.jsp?msg="+msg;
			}
			redirect(url, request, response);
		}
		catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}

}

