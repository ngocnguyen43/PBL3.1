package com.PBL3.controllers.auth;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.PBL3.config.ResponseConfig;
import com.PBL3.dtos.UserSigninDTO;
import com.PBL3.services.IAuthService;
import com.PBL3.utils.helpers.Helper;
import com.PBL3.utils.response.Message;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet(urlPatterns = { "/api/v1/auth/signin" })
@MultipartConfig
public class UserSignin extends HttpServlet {

	/**
	 * 
	 */
	@Inject
	private IAuthService signinService;
	private static final long serialVersionUID = -975955435760814368L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		ResponseConfig.ConfigHeader(resp);
		req.setCharacterEncoding("UTF-8");
		UserSigninDTO user = Helper.paramsToString(req.getParameterMap()).toModel(UserSigninDTO.class);
		Message message = signinService.Signin(user);
		resp.setStatus(message.getMeta().getStatusCode());
		out.print(new ObjectMapper().writeValueAsString(message));
		out.flush();
	}
}
