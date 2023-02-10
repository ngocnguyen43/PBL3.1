package com.PBL3.controller.admin.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.crypto.bcrypt.BCrypt;

import com.PBL3.helpers.HashPassword;
import com.PBL3.helpers.Helper;
import com.PBL3.helpers.JWTGenerator;
import com.PBL3.helpers.JWTVerify;
import com.PBL3.model.User;
import com.PBL3.service.IUserService;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
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
//		String jsonString = null;
//		String password = "123456";
//		String Salt = BCrypt.gensalt("$2b",15);
//		String Hashed = BCrypt.hashpw(password, Salt);
//		String hashedDB = "$2b$10$ufd70YPa8yMmNyHjwedbx.nHBGXAPMWj8HnUN8jQjD9O/sab3mA1O";
		try {
			List<User> users = userService.findAll();
			String json = objectMapper.writeValueAsString(users);
//			JWTGenerator generator = new JWTGenerator();
//			Map<String, String> claims = new HashMap<>();
//			String userId = UUID.randomUUID().toString();
//			claims.put("userId", userId);
//			try {
//				String token = generator.generatorJWT(claims);
//				claims.put("ACCESS_TOKEN", token);
//				JWTVerify validateJWT = new JWTVerify(token);
//				jsonString = objectMapper.writeValueAsString(claims);
//
//				DecodedJWT decoded = validateJWT.verifyingJWT();
//
//				Claim claim = decoded.getClaim("userId");
//				System.out.println(claim.asString().toString());
//				
//				System.out.println(Hashed);
				out.print(json);
				out.flush();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		resp.setContentType("application/x-www-form-urlencoded");
		User user = Helper.of(req.getReader()).toModel(User.class);
//		System.out.println(user.getPassword());
		user.setPassword(HashPassword.HashPW(user.getPassword()));
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
		System.out.println(Helper.of(req.getReader()));

	}

	public void handleFormData(HttpServletRequest request) {
//	    Enumeration params = request.getParameterNames();
//	    while (params.hasMoreElements()) {
//	        String param = params.nextElement();
//	        System.out.println("name = " + param + ", value = " + request.getParameter(param));
//	    }
	}
}
