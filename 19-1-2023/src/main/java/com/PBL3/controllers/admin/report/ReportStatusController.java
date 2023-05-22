package com.PBL3.controllers.admin.report;

import com.PBL3.services.IReportService;
import com.PBL3.utils.Constants.EndPoint;
import com.PBL3.utils.exceptions.ErrorHandler;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@MultipartConfig
@WebServlet(urlPatterns = {EndPoint.V1 + EndPoint.PRIVATE + EndPoint.ADMIN + "/report"})
public class ReportStatusController extends HttpServlet {
    @Inject
    private IReportService iReportService;

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ErrorHandler.handle(resp, () -> iReportService.updateStatus(req.getParameter("id")));
    }
}
