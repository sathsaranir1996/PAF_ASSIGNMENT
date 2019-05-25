package com.hari.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hari.model.Admin;
import com.hari.model.Member;

public interface AdminImpl {

	void delete();

	public Admin register(HttpServletRequest request, HttpServletResponse response, String name, String password);

	public void redirect(String url, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;

}
