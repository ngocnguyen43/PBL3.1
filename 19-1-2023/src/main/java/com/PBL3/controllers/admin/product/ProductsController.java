package com.PBL3.controllers.admin.product;

import com.PBL3.dtos.pagination.ProductPaginationDTO;
import com.PBL3.services.IProductService;
import com.PBL3.utils.Constants.EndPoint;
import com.PBL3.utils.exceptions.ErrorHandler;
import com.PBL3.utils.helpers.Helper;
import com.PBL3.utils.helpers.QueryParams;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {EndPoint.V1 + EndPoint.PRIVATE + "/products"})
public class ProductsController extends HttpServlet {
    @Inject
    private IProductService iProductService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductPaginationDTO dto = Helper.paramsToString(QueryParams.getQuery(req)).toModel(ProductPaginationDTO.class);
        ErrorHandler.handle(resp, () -> iProductService.getAllProducts(dto));
    }
}
