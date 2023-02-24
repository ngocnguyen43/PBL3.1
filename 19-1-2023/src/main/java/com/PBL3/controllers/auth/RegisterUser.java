package com.PBL3.controllers.auth;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.PBL3.config.ResponseConfig;
import com.PBL3.dtos.UserDTO;
import com.PBL3.services.IAuthService;
import com.PBL3.services.IUserService;
import com.PBL3.utils.helpers.Helper;
import com.PBL3.utils.response.Message;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet(urlPatterns = {"/api/v1/auth/register"})
public class RegisterUser extends HttpServlet {

	/**
	 * 
	 */

	@Inject
	private IAuthService authService;
	private static final long serialVersionUID = 5425347944387647554L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
//		resp.setContentType("application/json");
		ResponseConfig.ConfigHeader(resp);
		ObjectMapper obj = new ObjectMapper();
		PrintWriter out = resp.getWriter();
		UserDTO userDTO = Helper.of(req.getReader()).toModel(UserDTO.class);
////		User user = Helper.objectMapper(userDTO, User.class);
		Message message = authService.Register(userDTO);
		String json = obj.writeValueAsString(message);
		resp.setStatus(message.getMeta().getStatusCode());
		out.print(json);
		out.flush();
	}

}
