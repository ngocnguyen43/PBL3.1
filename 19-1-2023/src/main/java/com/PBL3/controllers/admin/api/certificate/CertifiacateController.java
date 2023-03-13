package com.PBL3.controllers.admin.api.certificate;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.PBL3.dtos.CertificateDTO;
import com.PBL3.utils.helpers.HandleImage;

import com.PBL3.config.ResponseConfig;
import com.PBL3.utils.helpers.Helper;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet(urlPatterns = { "/api/v1/private/certificate" })
@MultipartConfig
public class CertifiacateController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

			req.setCharacterEncoding("UTF-8");
//			resp.setContentType("application/json");

			ResponseConfig.ConfigHeader(resp);
			ObjectMapper obj = new ObjectMapper();
//			CertificateDTO certificateDTO = Helper.of(req.getReader()).toModel(CertificateDTO.class);
//			req.getParameterValues()
//			Part p = req.getPart("ABC");
			Part filePart = req.getPart("file");
			System.out.println(req.getPart("file").toString());
			InputStream fileContent = filePart.getInputStream();
			System.out.println(fileContent.toString());
//		String id = HandleImage.save(req);
//		System.out.println(id);

		resp.setStatus(200);
	}
}
