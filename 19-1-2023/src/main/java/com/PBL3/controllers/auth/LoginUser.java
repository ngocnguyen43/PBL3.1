package com.PBL3.controllers.auth;

import com.PBL3.dtos.UserSigninDTO;
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

@WebServlet(urlPatterns = {V1 + AUTH + "/login"})
@MultipartConfig
public class LoginUser extends HttpServlet {
    private static final long serialVersionUID = -975955435760814368L;
    @Inject
    private IAuthService authService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserSigninDTO dto = Helper.paramsToString(req.getParameterMap()).toModel(UserSigninDTO.class);
        ErrorHandler.handle(resp, () -> this.authService.Login(dto));
    }
}
