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

import com.hari.dao.PostDao;
import com.hari.dao.ShippingDao;
import com.hari.model.Post;
import com.hari.model.Shipping;

@RestController
@RequestMapping("/ship")
public class ShippingController implements ShippingControllerImpl {
	
	private Connection con = null;
	Shipping mem;
	ShippingDao dao;
	
	@Override
	public void delete() {
		// TODO Auto-generated method stub

	}
	


	
	@Override
	@RequestMapping(value = "/Register", method = RequestMethod.POST)
	public Shipping register(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("shipname") String shipname, @RequestParam("shipaddress") String shipaddress, @RequestParam("shipnum") int shipnum) {
		// TODO Auto-generated method stub
		
          try(ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml")) {
        	  String shippic = "shippics/" +request.getParameter("shippic");
			int shipid = 1;
			String status2 = "Active";
			dao=(ShippingDao)ctx.getBean("mdao3");
			mem = new Shipping(shipid, shippic, shipname, shipaddress, shipnum);
			int status=dao.registerShipping(mem);
			String url;
			if(status == -99) {
				System.out.println("Shipping failed to add");
				url = "http://localhost:8080/PAF/AddShip.jsp";
			}
			else{
				System.out.println("Shipping added ");
				url = "http://localhost:8080/PAF/Home.jsp?memI="+shipname;
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
	public Shipping ShippingProfile(HttpServletRequest request, HttpServletResponse response,   @RequestParam("shipid") int shipid) {
		// TODO Auto-generated method stub
		try(ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml")) {
			String url;
			dao=(ShippingDao)ctx.getBean("mdao3");
			mem = dao.getShipping(shipid);
			url = "http://localhost:8080/PAF/shipuser.jsp?shippic = "+mem.getShippic()+"&shipname="+mem.getShipname()+"&shipaddress="+mem.getShipaddress()+"&shipnum="+mem.getShipnum()+"";
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
	public Shipping generateUpdateForm(int shipid, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try(ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml")) {
			dao=(ShippingDao)ctx.getBean("mdao3");
			mem = dao.getShipping(shipid);
			String url, msg;
			if(mem == null) {
				System.out.println("Something went wrong");
				msg = "Something went wrong";
				url = "http://localhost:8080/PAF/editPostProfile.jsp?msg="+msg;
			}
			else{
				System.out.println("Post found");
				//String number = Integer.toString(mem.getContactNumber());
				url = "http://localhost:8080/PAF/editShip.jsp?shipname="+mem.getShipname()+"&shipaddress="+mem.getShipaddress()+"&shipnum="+mem.getShipnum();
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
	@RequestMapping(value = "/updateShip", method = RequestMethod.POST)
	public void update(HttpServletRequest request, HttpServletResponse response,@RequestParam("shipname") String shipname, @RequestParam("shipaddress") String shipaddress,@RequestParam("shipnum") int shipnum) throws ServletException, IOException {
		
		try(ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml")) {
			dao=(ShippingDao)ctx.getBean("mdao3");
			mem = new Shipping(1, "null", shipname, shipaddress, shipnum);
			int status=dao.updateShipping(mem);
			String url = "";
			if(status == -99) {
				System.out.println("Ship failed to update");
				url = "http://localhost:8080/PAF/UpdOrdfail.jsp";
			}
			else{
				System.out.println("Ship updated");
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
	public void deactivate(@RequestParam("shipid") int shipid, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		try(ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml")) {
			dao=(ShippingDao)ctx.getBean("mdao3");
			String url = "";
			String msg = "Your Company is deleted";
			int status = dao.deactivate(shipid);
			if(status == -99) {
				System.out.println("ship failed to deactivate");
			}
			else{
				System.out.println("ship deactivated");
				url = "http://localhost:8080/PAF/Home.jsp?msg="+msg;
			}
			redirect(url, request, response);
		}
		catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}

	


}
