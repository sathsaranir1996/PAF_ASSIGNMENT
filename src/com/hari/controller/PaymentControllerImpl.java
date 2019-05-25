package com.hari.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hari.model.Payment;

public interface PaymentControllerImpl {

	public void delete();

	public Payment register(HttpServletRequest request, HttpServletResponse response, String productname, String paymenttype,
			String paymentstatus);

	public void redirect(String url, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;

	public Payment paymentProfile(HttpServletRequest request, HttpServletResponse response, int paymentid);

	public Payment generateUpdateForm(int paymentid, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;

	public void update(HttpServletRequest request, HttpServletResponse response, String productname, String paymenttype,
			String paymentstatus) throws ServletException, IOException;

	public void deactivate(int paymentid, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;

}
