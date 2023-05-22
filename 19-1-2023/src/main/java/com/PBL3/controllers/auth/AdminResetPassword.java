package com.PBL3.controllers.auth;

import com.PBL3.services.IAuthService;
import com.PBL3.utils.exceptions.ErrorHandler;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.PBL3.utils.Constants.EndPoint.*;

@MultipartConfig
@WebServlet(urlPatterns = {V1 + PRIVATE + ADMIN + "/reset"})
public class AdminResetPassword extends HttpServlet {
    @Inject
    private IAuthService authService;

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ErrorHandler.handle(resp, () -> authService.ResetPassword(req.getHeader("client_id")));
    }
}
