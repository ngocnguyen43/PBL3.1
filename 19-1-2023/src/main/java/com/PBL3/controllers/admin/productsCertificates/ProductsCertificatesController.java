package com.PBL3.controllers.admin.productsCertificates;

import com.PBL3.dtos.ProductCertificateDTO;
import com.PBL3.services.IProductCertificateServie;
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

@WebServlet(urlPatterns = {EndPoint.V1 + EndPoint.PRIVATE + "/product/certificate"})
@MultipartConfig
public class ProductsCertificatesController extends HttpServlet {
    @Inject
    private IProductCertificateServie iProductCertificateServie;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductCertificateDTO dto = Helper.paramsToString(req.getParameterMap()).toModel(ProductCertificateDTO.class);
        ErrorHandler.handle(resp, () -> iProductCertificateServie.createOne(dto));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ErrorHandler.handle(resp, () -> iProductCertificateServie.deleteOne(req.getParameter("id")));
    }
}
