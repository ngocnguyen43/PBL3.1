package com.PBL3.controller.admin.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.PBL3.DTO.UserSigninDTO;
import com.PBL3.helpers.Helper;
import com.PBL3.helpers.message.SigninMessage;
import com.PBL3.service.IAuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@WebServlet(urlPatterns = { "/api/v1/auth/signin" })
public class Login extends HttpServlet {

	/**
	 * 
	 */
	@Inject
	private IAuthService signinService;
	private static final long serialVersionUID = 251088703685976384L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		ObjectMapper obj = new ObjectMapper();
		ObjectNode json = obj.createObjectNode();
		PrintWriter out = resp.getWriter();
		try {
			UserSigninDTO userDTO = Helper.of(req.getReader()).toModel(UserSigninDTO.class);
			SigninMessage message = signinService.Signin(userDTO);
			resp.setStatus(message.getStatusCode());
			json.put("statusCode", message.getStatusCode());
			if (message.getUserId() != null) {
				json.put("userId", message.getUserId());
			}
			json.put("message", message.getMessage());
			if (message.getIsAdmin() != null) {
				
				json.put("isAdmin", message.getIsAdmin());
			}
			if (message.getACCESS_TOKEN() != null) {
				
				json.put("ACCESS_TOKEN", message.getACCESS_TOKEN());
			}
			if (message.getREFRESH_TOKEN() != null) {
				
				json.put("REFRESH_TOKEN", message.getREFRESH_TOKEN());
			}
			out.print(json.toString());

			out.flush();
			
		} catch (Exception e) {
			System.out.println(e);
		}

	}
}
