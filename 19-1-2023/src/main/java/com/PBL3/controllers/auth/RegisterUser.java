package com.PBL3.controllers.auth;

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

@WebServlet(urlPatterns = {Constants.URL_V1 + Constants.AUTH + "/register"})
@MultipartConfig
public class RegisterUser extends HttpServlet {
    private static final long serialVersionUID = 5425347944387647554L;
    @Inject
    private IAuthService authService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDTO userDTO = Helper.paramsToString(req.getParameterMap()).toModel(UserDTO.class);
        ErrorHandler.handle(resp, authService.Register(userDTO, null));
    }

}
