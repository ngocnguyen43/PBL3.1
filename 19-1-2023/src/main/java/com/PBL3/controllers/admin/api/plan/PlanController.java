package com.PBL3.controllers.admin.api.plan;

import com.PBL3.config.ResponseConfig;
import com.PBL3.dtos.PlanDTO;
import com.PBL3.services.IPlanService;
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


@WebServlet(urlPatterns = {Constants.URL_V1 +Constants.PRIVATE + "/plan" })
@MultipartConfig
public class PlanController extends HttpServlet {
    @Inject
   private IPlanService iPlanService;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        ResponseConfig.ConfigHeader(resp);
        PlanDTO dto = Helper.paramsToString(req.getParameterMap()).toModel(PlanDTO.class);
        Message message = iPlanService.createOne(dto);
        resp.setStatus(message.getMeta().getStatusCode());
        resp.getWriter().print(new ObjectMapper().writeValueAsString(message));
        resp.getWriter().flush();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        ResponseConfig.ConfigHeader(resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
