package com.PBL3.controllers.admin.api.business;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Path;

import com.PBL3.dtos.BusinessDTO;
import com.PBL3.services.IBusinessService;
import com.PBL3.utils.helpers.Helper;
import com.PBL3.utils.response.Message;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet(urlPatterns = {"/api/v1/private/business"})
@MultipartConfig
public class BusinessController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private IBusinessService businessService;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		ObjectMapper obj = new ObjectMapper();
		PrintWriter out = resp.getWriter();
		BusinessDTO bussinessDto = Helper.paramsToString(req.getParameterMap()).toModel(BusinessDTO.class);
		Message message = businessService.createBusiness(bussinessDto);
		String json = obj.writeValueAsString(message);
		resp.setStatus(message.getMeta().getStatusCode().intValue());
		out.print(json);
		out.flush();
	}
}
