package com.PBL3.controllers.auth;

import com.PBL3.dtos.UserSigninDTO;
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

@WebServlet(urlPatterns = {Constants.URL_V1 + Constants.AUTH + "/login"})
@MultipartConfig
public class LoginUser extends HttpServlet {
    @Inject
    private IAuthService signinService;
    private static final long serialVersionUID = -975955435760814368L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserSigninDTO user = Helper.paramsToString(req.getParameterMap()).toModel(UserSigninDTO.class);
        ErrorHandler.handle(resp, signinService.Signin(user));
    }
}
