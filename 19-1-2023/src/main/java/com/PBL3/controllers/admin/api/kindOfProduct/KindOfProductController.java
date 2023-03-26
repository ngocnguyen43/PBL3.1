package com.PBL3.controllers.admin.api.kindOfProduct;

import com.PBL3.config.ResponseConfig;
import com.PBL3.dtos.KindOfProductDTO;
import com.PBL3.services.IKindOfProductService;
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

@WebServlet(urlPatterns = {Constants.URL_V1 + Constants.PRIVATE + "/productkind"})
@MultipartConfig
public class KindOfProductController extends HttpServlet {

    @Inject
    private IKindOfProductService iKindOfProductService;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        ResponseConfig.ConfigHeader(resp);
        KindOfProductDTO dto = Helper.paramsToString(req.getParameterMap()).toModel(KindOfProductDTO.class);
        Message message = iKindOfProductService.createNewKind(dto);
        resp.setStatus(message.getMeta().getStatusCode());
        resp.getWriter().print(new ObjectMapper().writeValueAsString(message));
        resp.getWriter().flush();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        ResponseConfig.ConfigHeader(resp);
        Message message = iKindOfProductService.getAllKinds();
        resp.setStatus(message.getMeta().getStatusCode());
        resp.getWriter().print(new ObjectMapper().writeValueAsString(message));
        resp.getWriter().flush();
    }
}
