package com.PBL3.controller.admin.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.PBL3.controller.SigninMessage;
import com.PBL3.helpers.Helper;
import com.PBL3.model.UserSignin;
import com.PBL3.service.ISigninService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@WebServlet(urlPatterns = {"/api/v1/auth/signin"})
public class Login extends HttpServlet {

	/**
	 * 
	 */
	@Inject
	private ISigninService signinService;
	private static final long serialVersionUID = 251088703685976384L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		ObjectMapper obj = new ObjectMapper();
		ObjectNode json = obj.createObjectNode();
		PrintWriter out = resp.getWriter();
		try {
			UserSignin user = Helper.of(req.getReader()).toModel(UserSignin.class);
			SigninMessage message = signinService.Signin(user);
			resp.setStatus(message.getStatusCode());
			json.put("statusCode",message.getStatusCode());
			json.put("userId", message.getUserId());
			json.put("message", message.getMessage());
			json.put("ACCESS_TOKEN", message.getACCESS_TOKEN());
			json.put("REFRESH_TOKEN", message.getREFRESH_TOKEN());
			out.print(json.toString());
			
			out.flush();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
	}
}
