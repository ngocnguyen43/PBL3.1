package com.PBL3.controllers.admin.api.business;

import com.PBL3.dtos.BusinessTypesDTO;
import com.PBL3.services.IBusinessTypesService;
import com.PBL3.utils.Constants.Constants;
import com.PBL3.utils.exceptions.ErrorHandler;
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

@WebServlet(urlPatterns = {Constants.URL_V1 + Constants.PRIVATE + "/business/type"})
@MultipartConfig
public class BusinessTypesController extends HttpServlet {

    @Inject
    private IBusinessTypesService businessTypesService;
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BusinessTypesDTO businessTypeDto = Helper.paramsToString(req.getParameterMap()).toModel(BusinessTypesDTO.class);
        ErrorHandler.handle(resp, () -> businessTypesService.createBusinessType(businessTypeDto));
    }

}
