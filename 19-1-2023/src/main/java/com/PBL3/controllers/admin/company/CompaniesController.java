package com.PBL3.controllers.admin.company;

import com.PBL3.services.ICompanyService;
import com.PBL3.utils.Constants.EndPoint;
import com.PBL3.utils.exceptions.ErrorHandler;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {EndPoint.V1 + EndPoint.PRIVATE + "/all-companies"})
public class CompaniesController extends HttpServlet {
    @Inject
    private ICompanyService companyService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ErrorHandler.handle(resp, () -> companyService.getAllCompanies());
    }
}
