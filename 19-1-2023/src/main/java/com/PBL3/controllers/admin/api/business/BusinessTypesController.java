package com.PBL3.controllers.admin.api.business;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.PBL3.dtos.BusinessTypesDTO;
import com.PBL3.services.IBusinessTypesService;
import com.PBL3.ultils.helpers.Helper;
import com.PBL3.ultils.response.Message;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet(urlPatterns = {"/api/v1/private/admin/business/type"})
public class BusinessTypesController extends HttpServlet {

	/**
	 * 
	 */
	@Inject
	private IBusinessTypesService businessTypesService;
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		ObjectMapper obj = new ObjectMapper();
		PrintWriter out = resp.getWriter();
		BusinessTypesDTO bussinessTypeDto = Helper.of(req.getReader()).toModel(BusinessTypesDTO.class);
		Message message = businessTypesService.createBusinessType(bussinessTypeDto);
		String json = obj.writeValueAsString(message);
		resp.setStatus(message.getMeta().getStatusCode().intValue());
		out.print(json);
		out.flush();
	}

}
