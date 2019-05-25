package com.hari.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hari.model.Post;
import com.hari.model.Shipping;

public interface ShippingControllerImpl {

	public void delete();

	public Shipping register(HttpServletRequest request, HttpServletResponse response, String shipname, String shipaddress,
			int shipnum);

	public void redirect(String url, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;

	public Shipping ShippingProfile(HttpServletRequest request, HttpServletResponse response, int shipid);

	public Shipping generateUpdateForm(int shipid, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;

	public void update(HttpServletRequest request, HttpServletResponse response, String shipname, String shipaddress,
			int shipnum) throws ServletException, IOException;

	public void deactivate(int shipid, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;

}
