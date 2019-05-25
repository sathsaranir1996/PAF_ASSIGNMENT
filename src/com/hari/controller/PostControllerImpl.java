package com.hari.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.hari.model.Post;

public interface PostControllerImpl {
	
	public Post register(HttpServletRequest request, HttpServletResponse response, String productname, int price,
			int contactnumber, String category, String dec);

	void delete();
	public void redirect(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

	public Post postProfile(HttpServletRequest request, HttpServletResponse response, int postid);

	public Post generateUpdateForm(int postid, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;

	public void update(HttpServletRequest request, HttpServletResponse response, String productname, int price,
			int contactnumber) throws ServletException, IOException;

	public void deactivate(int postid, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;

}
