package com.PBL3.controllers.admin.inspectors;

import com.PBL3.dtos.UserDTO;
import com.PBL3.services.IAuthService;
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

@WebServlet(Constants.URL_V1 + Constants.PRIVATE + Constants.ADMIN + "/moderators")
@MultipartConfig
public class NewInspectorController extends HttpServlet {
    @Inject
    private IAuthService authService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDTO dto = Helper.paramsToString(req.getParameterMap()).toModel(UserDTO.class);
        ErrorHandler.handle(resp, () -> authService.InspectorRegister(dto));
    }
}
