package com.PBL3.controllers.admin.api.product;

import com.PBL3.config.ResponseConfig;
import com.PBL3.dtos.ProductDTO;
import com.PBL3.services.IProductService;
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

@WebServlet(urlPatterns = {Constants.URL_V1+Constants.PRIVATE+ "/product"})
@MultipartConfig
public class ProductController extends HttpServlet {
    @Inject
    IProductService iProductService;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        ResponseConfig.ConfigHeader(resp);
        ProductDTO dto = Helper.paramsToString(req.getParameterMap()).toModel(ProductDTO.class);
        Message message = iProductService.createNewProduct(dto);
        resp.setStatus(message.getMeta().getStatusCode().intValue());
        resp.getWriter().print(new ObjectMapper().writeValueAsString(message));
        resp.getWriter().flush();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        ResponseConfig.ConfigHeader(resp);
        ProductDTO dto = Helper.paramsToString(req.getParameterMap()).toModel(ProductDTO.class);
        System.out.println(new ObjectMapper().writeValueAsString(dto));
        Message message = iProductService.updateProduct(dto);
        resp.setStatus(message.getMeta().getStatusCode().intValue());
        resp.getWriter().print(new ObjectMapper().writeValueAsString(message));
        resp.getWriter().flush();
    }
}
