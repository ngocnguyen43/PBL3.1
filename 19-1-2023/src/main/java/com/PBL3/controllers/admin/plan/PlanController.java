package com.PBL3.controllers.admin.plan;

import com.PBL3.dtos.PlanDTO;
import com.PBL3.services.IPlanService;
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


@WebServlet(urlPatterns = {Constants.URL_V1 + Constants.PRIVATE + Constants.ADMIN + "/plan"})
@MultipartConfig
public class PlanController extends HttpServlet {
    @Inject
    private IPlanService iPlanService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PlanDTO dto = Helper.paramsToString(req.getParameterMap()).toModel(PlanDTO.class);
        ErrorHandler.handle(resp, () -> iPlanService.createOne(dto));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PlanDTO dto = Helper.paramsToString(req.getParameterMap()).toModel(PlanDTO.class);
        ErrorHandler.handle(resp, () -> iPlanService.updateTime(dto));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ErrorHandler.handle(resp, () -> iPlanService.inactivePlan(req.getParameter("id")));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ErrorHandler.handle(resp, () -> iPlanService.getOneById(req.getParameter("id")));
    }
}
