package com.hari.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AdminLoginControllerImpl {

	public void login(String aname, String password, HttpServletRequest request, HttpServletResponse response);

	public void redirect(String url, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;

}
