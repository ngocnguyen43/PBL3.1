package com.PBL3.controllers.user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.PBL3.models.Role;
import com.PBL3.services.impl.RoleService;
import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet(urlPatterns = { "/home" })
public class HomeController extends HttpServlet {

	/**
	 * 
	 */
	@Inject
	private RoleService roleService;
    public static final char[] salt = "_-0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%^&*()".toCharArray();
    Random random = new Random();
    
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Role> roles= roleService.findAll();
		PrintWriter out = resp.getWriter();
		ObjectMapper objectMapper= new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(roles);
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
//		resp.getWriter().write(objectMapper.writeValueAsString(jsonString));
		String nanoId = NanoIdUtils.randomNanoId(random, salt,15);
		System.out.println(nanoId);
//
		out.print(jsonString);
		out.flush();
//		RequestDispatcher rd = req.getRequestDispatcher("/VIEWS/WEB/home.jsp");
//		rd.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// TODO Auto-generated method stub
	    StringBuilder buffer = new StringBuilder();
	    BufferedReader reader = req.getReader();
	    String line;
	    while ((line = reader.readLine()) != null) {
	        buffer.append(line);
	        buffer.append(System.lineSeparator());
	    }
	    String data = buffer.toString();
	    		
		PrintWriter out = resp.getWriter();
		ObjectMapper objectMapper= new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(data);
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
//		resp.getWriter().write(objectMapper.writeValueAsString(jsonString));
		out.print(jsonString);
		out.flush();
	}
}
