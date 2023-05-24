package com.PBL3.controllers.admin.certificate;

import com.PBL3.services.ICertificateService;
import com.PBL3.utils.exceptions.ErrorHandler;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.PBL3.utils.Constants.EndPoint.PRIVATE;
import static com.PBL3.utils.Constants.EndPoint.V1;

@WebServlet(urlPatterns = {V1 + PRIVATE + "/certificates"})
public class CertificatesController extends HttpServlet {
    @Inject
    private ICertificateService certificateService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ErrorHandler.handle(resp, () -> certificateService.getAll());
    }
}
