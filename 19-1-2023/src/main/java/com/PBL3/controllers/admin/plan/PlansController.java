package com.PBL3.controllers.admin.plan;

import com.PBL3.dtos.pagination.PlanPaginationDTO;
import com.PBL3.services.IPlanService;
import com.PBL3.utils.Constants.EndPoint;
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

@MultipartConfig
@WebServlet(urlPatterns = {EndPoint.V1 + EndPoint.PRIVATE + "/plans"})
public class PlansController extends HttpServlet {
    @Inject
    private IPlanService iPlanService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PlanPaginationDTO dto = Helper.paramsToString(req.getParameterMap()).toModel(PlanPaginationDTO.class);
        ErrorHandler.handle(resp, () -> iPlanService.getAll(dto, req.getHeader("client_id")));
    }
}
