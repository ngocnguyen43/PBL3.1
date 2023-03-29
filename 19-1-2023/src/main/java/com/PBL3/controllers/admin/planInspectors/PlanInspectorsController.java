package com.PBL3.controllers.admin.planInspectors;

import com.PBL3.dtos.PlanInspectorDTO;
import com.PBL3.services.IPlanInspectorService;
import com.PBL3.utils.Constants.Constants;
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

@WebServlet(urlPatterns = {Constants.URL_V1 + Constants.PRIVATE + Constants.ADMIN + "/plan/inspectors"})
@MultipartConfig
public class PlanInspectorsController extends HttpServlet {
    @Inject
    private IPlanInspectorService iPlanInspectorService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PlanInspectorDTO dto = Helper.paramsToString(req.getParameterMap()).toModel(PlanInspectorDTO.class);
        ErrorHandler.handle(resp, () -> iPlanInspectorService.createOne(dto));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PlanInspectorDTO dto = Helper.paramsToString(req.getParameterMap()).toModel(PlanInspectorDTO.class);
        ErrorHandler.handle(resp, () -> iPlanInspectorService.deactive(dto));
    }
}
