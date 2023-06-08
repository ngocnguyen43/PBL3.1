package com.PBL3.controllers.admin.company;

import com.PBL3.dtos.pagination.UserPaginationDTO;
import com.PBL3.services.ICompanyService;
import com.PBL3.utils.exceptions.ErrorHandler;
import com.PBL3.utils.helpers.Helper;
import com.PBL3.utils.helpers.QueryParams;

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

@MultipartConfig
@WebServlet(urlPatterns = {V1 + PRIVATE + "/companies"})
public class CompanyController extends HttpServlet {
    @Inject
    private ICompanyService companyService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserPaginationDTO dto = Helper.paramsToString(QueryParams.getQuery(req)).toModel(UserPaginationDTO.class);
        ErrorHandler.handle(resp, () -> companyService.getAllcompanies(dto));
    }
}
