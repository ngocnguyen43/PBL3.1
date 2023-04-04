package com.PBL3.controllers.admin.company;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.PBL3.utils.Constants.EndPoint.PRIVATE;
import static com.PBL3.utils.Constants.EndPoint.V1;

@MultipartConfig
@WebServlet(urlPatterns = {V1 + PRIVATE + "/company"})
public class CompanyController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
