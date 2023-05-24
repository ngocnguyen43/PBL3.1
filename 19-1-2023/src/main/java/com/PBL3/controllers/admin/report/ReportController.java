package com.PBL3.controllers.admin.report;

import com.PBL3.dtos.ReportDTO;
import com.PBL3.services.IMicrosoftService;
import com.PBL3.services.IReportService;
import com.PBL3.utils.Constants.EndPoint;
import com.PBL3.utils.exceptions.ErrorHandler;
import com.PBL3.utils.exceptions.dbExceptions.UnexpectedException;
import com.PBL3.utils.helpers.Helper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {EndPoint.V1 + EndPoint.PRIVATE + "/report"})
@MultipartConfig
public class ReportController extends HttpServlet {
    @Inject
    private IReportService iReportService;
    @Inject
    private IMicrosoftService microsoftService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReportDTO dto = Helper.paramsToString(req.getParameterMap()).toModel(ReportDTO.class);
        String path = null;
        try {
            path = microsoftService.uploadFile(req);
        } catch (UnexpectedException e) {
            throw new RuntimeException(e);
        }
        dto.setPath(path);
        ErrorHandler.handle(resp, () -> iReportService.createOne(dto, req.getHeader("client_id")));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ErrorHandler.handle(resp, () -> iReportService.findOneById(req.getParameter("id")));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }
}
