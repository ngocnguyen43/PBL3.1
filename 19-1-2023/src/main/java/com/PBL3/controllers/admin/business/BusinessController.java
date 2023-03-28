package com.PBL3.controllers.admin.business;

import com.PBL3.dtos.BusinessDTO;
import com.PBL3.services.IBusinessService;
import com.PBL3.utils.Constants.Constants;
import com.PBL3.utils.exceptions.ErrorHandler;
import com.PBL3.utils.helpers.Helper;
import com.PBL3.utils.response.Message;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {Constants.URL_V1 + Constants.PRIVATE + "/business"})
@MultipartConfig
public class BusinessController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    @Inject
    private IBusinessService businessService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BusinessDTO businessDto = Helper.paramsToString(req.getParameterMap()).toModel(BusinessDTO.class);
        Message message = businessService.createBusiness(businessDto);
        ErrorHandler.handle(resp, () -> businessService.createBusiness(businessDto));
    }
}
