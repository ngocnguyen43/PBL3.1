package com.PBL3.controllers.admin.api.certificate;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.PBL3.utils.helpers.HandleImage;

import com.PBL3.config.ResponseConfig;

@WebServlet(urlPatterns = { "/api/v1/private/certificate" })
public class CertifiacateController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		

		ResponseConfig.ConfigHeader(resp);
		String id = HandleImage.save(req);
		System.out.println(id);

		resp.setStatus(200);
	}
}
