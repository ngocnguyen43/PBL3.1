package com.PBL3.controllers.admin.kindOfProduct;

import com.PBL3.dtos.KindOfProductDTO;
import com.PBL3.services.IKindOfProductService;
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

@WebServlet(urlPatterns = {EndPoint.V1 + EndPoint.PRIVATE + "/productkind"})
@MultipartConfig
public class KindOfProductController extends HttpServlet {

    @Inject
    private IKindOfProductService iKindOfProductService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        KindOfProductDTO dto = Helper.paramsToString(req.getParameterMap()).toModel(KindOfProductDTO.class);
        ErrorHandler.handle(resp, () -> iKindOfProductService.createNewKind(dto, req.getHeader("client_id")));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ErrorHandler.handle(resp, () -> iKindOfProductService.getAllKinds());
    }
}
