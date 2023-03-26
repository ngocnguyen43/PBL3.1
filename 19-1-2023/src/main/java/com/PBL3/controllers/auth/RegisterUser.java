package com.PBL3.controllers.auth;

import com.PBL3.config.ResponseConfig;
import com.PBL3.dtos.UserDTO;
import com.PBL3.services.IAuthService;
import com.PBL3.utils.Constants.Constants;
import com.PBL3.utils.helpers.Helper;
import com.PBL3.utils.response.Message;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {Constants.URL_V1 + Constants.AUTH +"/register"})
@MultipartConfig
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
		ResponseConfig.ConfigHeader(resp);
		UserDTO userDTO = Helper.paramsToString(req.getParameterMap()).toModel(UserDTO.class);
		Message message = authService.Register(userDTO,null);
		resp.setStatus(message.getMeta().getStatusCode());
		resp.getWriter().print(new ObjectMapper().writeValueAsString(message));
		resp.getWriter().flush();
	}

}
