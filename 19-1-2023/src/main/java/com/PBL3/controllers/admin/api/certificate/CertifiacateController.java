package com.PBL3.controllers.admin.api.certificate;

import com.PBL3.config.ResponseConfig;
import com.PBL3.dtos.CertificateDTO;
import com.PBL3.services.ICertificateService;
import com.PBL3.utils.helpers.CheckContainsFile;
import com.PBL3.utils.helpers.GetQueryParams;
import com.PBL3.utils.helpers.HandleImage;
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
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(urlPatterns = {"/api/v1/private/certificate"})
@MultipartConfig
public class CertifiacateController extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Inject
    private ICertificateService certificateService;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
//			resp.setContentType("application/json");
        ResponseConfig.ConfigHeader(resp);
        ObjectMapper obj = new ObjectMapper();
        PrintWriter out = resp.getWriter();
        String path = HandleImage.save(req);
        CertificateDTO dto = Helper.paramsToString(req.getParameterMap()).toModel(CertificateDTO.class);
        dto.setPath(path);
        Message message = certificateService.createCertificate(dto);
        String json = obj.writeValueAsString(message);
        resp.setStatus(message.getMeta().getStatusCode().intValue());
        out.print(json);
        out.flush();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        ResponseConfig.ConfigHeader(resp);
        ObjectMapper obj = new ObjectMapper();
        PrintWriter out = resp.getWriter();
        Message message = certificateService.getAllCertificate();
        String json = obj.writeValueAsString(message);
        resp.setStatus(message.getMeta().getStatusCode().intValue());
        out.print(json);
        out.flush();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        ResponseConfig.ConfigHeader(resp);
        ObjectMapper obj = new ObjectMapper();
        PrintWriter out = resp.getWriter();
//        Map<String,String> queries = GetQueryParams.getQueryParameters(req);
        CertificateDTO dto = Helper.paramsToString(req.getParameterMap()).toModel(CertificateDTO.class);

        if (CheckContainsFile.check(req)){
            String path = HandleImage.save(req);
            dto.setPath(path);
        }
        System.out.println(new ObjectMapper().writeValueAsString(dto));
//        System.out.println(CheckContainsFile.check(req));
        System.out.println(new ObjectMapper().writeValueAsString(req.getParameterMap()));
//        System.out.println(req.getPart("file").getSize());
        Message message = certificateService.updateCertificate(dto);
        String json = obj.writeValueAsString(message);
        resp.setStatus(message.getMeta().getStatusCode().intValue());
        out.print(json);
        out.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        ResponseConfig.ConfigHeader(resp);
        ObjectMapper obj = new ObjectMapper();
        PrintWriter out = resp.getWriter();
        Map<String,String> queries = GetQueryParams.getQueryParameters(req);
//        System.out.println(new ObjectMapper().writeValueAsString(queries.get("id")));
        Message message = certificateService.deleteCertificate(queries.get("id"));
        String json = obj.writeValueAsString(message);
        resp.setStatus(message.getMeta().getStatusCode().intValue());
        out.print(json);
        out.flush();
    }
}
