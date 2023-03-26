package com.PBL3.controllers.admin.api.productsCertificates;

import com.PBL3.config.ResponseConfig;
import com.PBL3.dtos.ProductCertificateDTO;
import com.PBL3.services.IProductCertificateServie;
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

@WebServlet(urlPatterns = {Constants.URL_V1 + Constants.PRIVATE +"/product/certificate"})
@MultipartConfig
public class ProductsCertificatesController extends HttpServlet {
    @Inject
    private IProductCertificateServie iProductCertificateServie;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        ResponseConfig.ConfigHeader(resp);
        ProductCertificateDTO dto = Helper.paramsToString(req.getParameterMap()).toModel(ProductCertificateDTO.class);
        Message message = iProductCertificateServie.createOne(dto);
        resp.setStatus(message.getMeta().getStatusCode().intValue());
        resp.getWriter().print(new ObjectMapper().writeValueAsString(message));
        resp.getWriter().flush();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        ResponseConfig.ConfigHeader(resp);
        Message message = iProductCertificateServie.deleteOne(req.getParameter("id"));
        resp.setStatus(message.getMeta().getStatusCode());
        resp.getWriter().print(new ObjectMapper().writeValueAsString(message));
        resp.getWriter().flush();
    }
}
