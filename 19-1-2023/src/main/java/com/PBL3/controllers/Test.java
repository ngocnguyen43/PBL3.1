package com.PBL3.controllers;

import com.PBL3.services.ICertificateService;
import com.PBL3.services.IPlanService;
import com.PBL3.services.IUserService;
import com.PBL3.utils.exceptions.ErrorHandler;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/test")
public class Test extends HttpServlet {
    @Inject
    private IUserService userService;
    @Inject
    private ICertificateService certificateService;
    @Inject
    private IPlanService iPlanService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ErrorHandler.handle(resp, () -> iPlanService.countCreatedPlans());
    }
}
