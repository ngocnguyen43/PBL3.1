package com.PBL3.controller.admin.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.PBL3.helpers.Helper;
import com.PBL3.helpers.JWTGenerator;
import com.PBL3.model.User;
import com.PBL3.service.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet(urlPatterns = { "/api/v1/admin/users" })
public class UserApi extends HttpServlet {

	/**
	 * 
	 */
	@Inject
	private IUserService userService;
	private static final long serialVersionUID = 327340588098344440L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = null;
		try {
			JWTGenerator generator = new JWTGenerator();
			Map<String, String> claims = new HashMap<>();
			Map<String, String> json = new HashMap<>();
//			claims.put("action", "read");
			try {
				String token = generator.generatorJWT(claims);
				json.put("ACCESS_TOKEN", token);
				jsonString = objectMapper.writeValueAsString(json);
				out.print(jsonString);
				out.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		resp.setContentType("application/x-www-form-urlencoded");
		User user = Helper.of(req.getReader()).toModel(User.class);
		user = userService.save(user);
		System.out.println(user);

	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		resp.setContentType("application/x-www-form-urlencoded");
		System.out.println(req.getParameterMap());

	}

	public void handleFormData(HttpServletRequest request) {
//	    Enumeration params = request.getParameterNames();
//	    while (params.hasMoreElements()) {
//	        String param = params.nextElement();
//	        System.out.println("name = " + param + ", value = " + request.getParameter(param));
//	    }
	}
}
