package com.PBL3.controllers.admin.api.business;

import com.PBL3.config.ResponseConfig;
import com.PBL3.dtos.BusinessDTO;
import com.PBL3.services.IBusinessService;
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

@WebServlet(urlPatterns = {Constants.URL_V1 + Constants.PRIVATE + "/business"})
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
		ResponseConfig.ConfigHeader(resp);
		BusinessDTO businessDto = Helper.paramsToString(req.getParameterMap()).toModel(BusinessDTO.class);
		Message message = businessService.createBusiness(businessDto);
		resp.setStatus(message.getMeta().getStatusCode());
		resp.getWriter().print(new ObjectMapper().writeValueAsString(message));
		resp.getWriter().flush();

	}
}
