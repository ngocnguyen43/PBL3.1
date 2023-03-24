package com.PBL3.controllers.admin.api.product;

import com.PBL3.config.ResponseConfig;
import com.PBL3.dtos.ProductDTO;
import com.PBL3.models.ProductModel;
import com.PBL3.services.IProductService;
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

@WebServlet(urlPatterns = {"/api/v1/private/product"})
@MultipartConfig
public class ProductController extends HttpServlet {
    @Inject
    IProductService iProductService;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        ResponseConfig.ConfigHeader(resp);
        ObjectMapper obj = new ObjectMapper();
        PrintWriter out = resp.getWriter();

        ProductDTO dto = Helper.paramsToString(req.getParameterMap()).toModel(ProductDTO.class);
//        System.out.println(new ObjectMapper().writeValueAsString(dto));
//        ProductModel domain = Helper.objectMapper(dto,ProductModel.class);
//        System.out.println(new ObjectMapper().writeValueAsString(domain));

        Message message = iProductService.createNewProduct(dto);
        String json = obj.writeValueAsString(message);
        resp.setStatus(message.getMeta().getStatusCode().intValue());
        out.print(json);
        out.flush();

    }
}
