package com.PBL3.controllers.admin.api.kindOfProduct;

import com.PBL3.config.ResponseConfig;
import com.PBL3.dtos.KindOfProductDTO;
import com.PBL3.services.IKindOfProductService;
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
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/api/v1/private/productkind"})
@MultipartConfig
public class KindOfProductController extends HttpServlet {

    @Inject
    IKindOfProductService iKindOfProductService;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        ResponseConfig.ConfigHeader(resp);
        ObjectMapper obj = new ObjectMapper();
        PrintWriter out = resp.getWriter();

        KindOfProductDTO dto = Helper.paramsToString(req.getParameterMap()).toModel(KindOfProductDTO.class);
        Message message = iKindOfProductService.createNewKind(dto);
        String json = obj.writeValueAsString(message);
        resp.setStatus(message.getMeta().getStatusCode().intValue());
        out.print(json);
        out.flush();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        ResponseConfig.ConfigHeader(resp);
        ObjectMapper obj = new ObjectMapper();
        PrintWriter out = resp.getWriter();

        Message message = iKindOfProductService.getAllKinds();
        String json = obj.writeValueAsString(message);
        resp.setStatus(message.getMeta().getStatusCode().intValue());
        out.print(json);
        out.flush();
    }
}
