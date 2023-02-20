package com.PBL3.controllers.admin.api.store;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.PBL3.dtos.StoreDTO;
import com.PBL3.services.IStoreService;
import com.PBL3.ultils.helpers.Helper;
import com.PBL3.ultils.response.Message;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet(urlPatterns = { "/api/v1/admin/store" })
public class StoreController extends HttpServlet {

	/**
	 * 
	 */
	@Inject
	private IStoreService storeService;
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		ObjectMapper obj = new ObjectMapper();

		StoreDTO store = Helper.of(req.getReader()).toModel(StoreDTO.class);
		Message message = storeService.createStoreService(store);
		String json = obj.writeValueAsString(message);
		resp.setStatus(message.getMeta().getStatusCode());
		resp.setStatus(message.getMeta().getStatusCode());
		out.print(json);
		out.flush();
	}
}
