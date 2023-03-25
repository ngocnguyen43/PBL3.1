package com.PBL3.controllers.admin.api.product;

import com.PBL3.utils.Constants.*;
import com.PBL3.config.ResponseConfig;
import com.PBL3.utils.response.Message;
import com.PBL3.services.IProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {Constants.URL_V1 + Constants.PRIVATE+ "/products"})
public class ProductsController extends HttpServlet {
    @Inject
    IProductService iProductService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        ResponseConfig.ConfigHeader(resp);
        Message message = iProductService.getAllProducts();
        resp.setStatus(message.getMeta().getStatusCode().intValue());
        resp.getWriter().print(new ObjectMapper().writeValueAsString(message));
        resp.getWriter().flush();
    }
}
