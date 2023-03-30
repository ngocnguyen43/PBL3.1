package com.PBL3.controllers.admin.report;

import com.PBL3.dtos.ReportDTO;
import com.PBL3.services.IReportService;
import com.PBL3.utils.Constants.EndPoint;
import com.PBL3.utils.exceptions.ErrorHandler;
import com.PBL3.utils.helpers.Helper;
import com.PBL3.utils.helpers.SaveFile;

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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReportDTO dto = Helper.paramsToString(req.getParameterMap()).toModel(ReportDTO.class);
        String path = SaveFile.save(req, "document");
        dto.setPath(path);
        ErrorHandler.handle(resp, () -> iReportService.createOne(dto));
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
