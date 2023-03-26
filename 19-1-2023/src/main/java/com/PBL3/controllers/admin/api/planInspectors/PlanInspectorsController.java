package com.PBL3.controllers.admin.api.planInspectors;

import com.PBL3.config.ResponseConfig;
import com.PBL3.dtos.PlanInspectorDTO;
import com.PBL3.services.IPlanInspectorService;
import com.PBL3.utils.Constants.Constants;
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

@WebServlet(urlPatterns = {Constants.URL_V1 + Constants.PRIVATE + "/admin" + "/plan/inspectors"})
@MultipartConfig
public class PlanInspectorsController extends HttpServlet {
    @Inject
    private IPlanInspectorService iPlanInspectorService;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        ResponseConfig.ConfigHeader(resp);
        PlanInspectorDTO dto = Helper.paramsToString(req.getParameterMap()).toModel(PlanInspectorDTO.class);
        Message message = iPlanInspectorService.createOne(dto);
        resp.setStatus(message.getMeta().getStatusCode());
        resp.getWriter().print(new ObjectMapper().writeValueAsString(message));
        resp.getWriter().flush();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        ResponseConfig.ConfigHeader(resp);
        PlanInspectorDTO dto = Helper.paramsToString(req.getParameterMap()).toModel(PlanInspectorDTO.class);
        Message message = iPlanInspectorService.deactive(dto);
        resp.setStatus(message.getMeta().getStatusCode());
        resp.getWriter().print(new ObjectMapper().writeValueAsString(message));
        resp.getWriter().flush();
    }
}
