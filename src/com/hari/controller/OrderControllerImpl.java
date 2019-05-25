package com.hari.controller;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hari.model.*;

public interface OrderControllerImpl {
	public Order register(HttpServletRequest request, HttpServletResponse response, String productname, int quantity,
			String paymenttype);
	//public Order register(HttpServletRequest request, HttpServletResponse response, String productname, String address, int contactNumber, String quantity);
	public void delete();
	//public void update(HttpServletRequest request, HttpServletResponse response, String productname, String addess, int contactnumber, String quantity)throws ServletException, IOException;
	public void redirect(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	//public Order orderProfile(HttpServletRequest request, HttpServletResponse response, String productname);
	//public Order generateUpdateForm(String name, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	//public void deactivate(String name, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	public Order generateUpdateForm(int orderid, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;
	public Order orderProfile(HttpServletRequest request, HttpServletResponse response, int orderid);
	
	public void update(HttpServletRequest request, HttpServletResponse response, String productname, 
			int quantity) throws ServletException, IOException;
	public void deactivate(int orderid, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;
	
	
		
	
	
}
