package com.PBL3.controllers.admin.api.certificate;

import com.PBL3.config.ResponseConfig;
import com.PBL3.dtos.CertificateDTO;
import com.PBL3.utils.helpers.HandleImage;
import com.PBL3.utils.helpers.Helper;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api/v1/private/certificate"})
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


        String path = HandleImage.save(req);
        CertificateDTO dto = Helper.paramsToString(req.getParameterMap()).toModel(CertificateDTO.class);
        dto.setPath(path);
        System.out.println(new ObjectMapper().writeValueAsString(dto));


        resp.setStatus(200);
    }
}
