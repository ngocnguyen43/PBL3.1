package com.PBL3.controllers.admin.api.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Path;

//@WebServlet(urlPatterns = { "/api/v1/users" })
//@WebServlet("/api/v1/admin/users")
public class UsersController extends HttpServlet {

	/**
	 * 
	 */

	private static final long serialVersionUID = -3848721747697052811L;
//	@Path("/{id}")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		resp.setStatus(HttpServletResponse.SC_BAD_GATEWAY);
		System.out.println(req.getParameter("id"));
	}
}
