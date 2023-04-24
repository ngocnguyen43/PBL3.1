package com.PBL3.controllers.admin.business;

import com.PBL3.dtos.BusinessTypesDTO;
import com.PBL3.services.IBusinessTypesService;
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

import static com.PBL3.utils.Constants.EndPoint.PRIVATE;
import static com.PBL3.utils.Constants.EndPoint.V1;

@WebServlet(urlPatterns = {V1 + PRIVATE + "/business/type"})
@MultipartConfig
public class BusinessTypesController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    @Inject
    private IBusinessTypesService businessTypesService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BusinessTypesDTO businessTypeDto = Helper.paramsToString(req.getParameterMap()).toModel(BusinessTypesDTO.class);
        ErrorHandler.handle(resp, () -> businessTypesService.createBusinessType(businessTypeDto));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ErrorHandler.handle(resp,()-> businessTypesService.GetAllBusinessTypes());
    }
}
