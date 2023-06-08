package com.PBL3.controllers.auth;

import com.PBL3.dtos.ResetPasswordDTO;
import com.PBL3.services.IAuthService;
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

import static com.PBL3.utils.Constants.EndPoint.AUTH;
import static com.PBL3.utils.Constants.EndPoint.V1;

@WebServlet(urlPatterns = {V1 + AUTH + "/reset"})
@MultipartConfig
public class ResetPasswordUser extends HttpServlet {

    @Inject
    private IAuthService authService;

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResetPasswordDTO dto = Helper.paramsToString(req.getParameterMap()).toModel(ResetPasswordDTO.class);
        dto.setUserId(req.getHeader("client_id"));
        ErrorHandler.handle(resp, () -> authService.ResetPassword(dto));
    }
}
