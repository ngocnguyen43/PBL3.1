package com.PBL3.controllers.admin.product;

import com.PBL3.dtos.ProductDTO;
import com.PBL3.services.IProductService;
import com.PBL3.utils.Constants.EndPoint;
import com.PBL3.utils.exceptions.ErrorHandler;
import com.PBL3.utils.helpers.Helper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {EndPoint.V1 + EndPoint.PRIVATE + "/product"})
@MultipartConfig
public class ProductController extends HttpServlet {
    @Inject
    private IProductService iProductService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDTO dto = Helper.paramsToString(req.getParameterMap()).toModel(ProductDTO.class);
        ErrorHandler.handle(resp, () -> iProductService.createNewProduct(dto));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDTO dto = Helper.paramsToString(req.getParameterMap()).toModel(ProductDTO.class);
        ErrorHandler.handle(resp, () -> iProductService.updateProduct(dto));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ErrorHandler.handle(resp, () -> iProductService.deleteProduct(req.getParameter("id")));
    }
}
