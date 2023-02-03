package com.PBL3.controller.admin.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.PBL3.helpers.Helper;

@WebServlet(urlPatterns = { "/api/v1/admin/users" })
public class UserApi extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 327340588098344440L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
//		System.out.println(req.getReader());
		resp.setContentType("application/json");
		resp.setContentType("application/x-www-form-urlencoded");
//		System.out.println(Helper.of(req.getReader()).toModel(null));
		

	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
}
