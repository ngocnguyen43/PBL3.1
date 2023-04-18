package com.PBL3.controllers.admin.certificate;

import com.PBL3.dtos.CertificateDTO;
import com.PBL3.services.ICertificateService;
import com.PBL3.utils.exceptions.ErrorHandler;
import com.PBL3.utils.helpers.CheckContainsFile;
import com.PBL3.utils.helpers.Helper;
import com.PBL3.utils.helpers.QueryParams;
import com.PBL3.utils.helpers.SaveFile;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.PBL3.utils.Constants.EndPoint.PRIVATE;
import static com.PBL3.utils.Constants.EndPoint.V1;

@WebServlet(urlPatterns = {V1 + PRIVATE + "/certificate"})
@MultipartConfig
public class CertificateController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Inject
    private ICertificateService certificateService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = SaveFile.save(req, "image");
        CertificateDTO dto = Helper.paramsToString(req.getParameterMap()).toModel(CertificateDTO.class);
        dto.setPath(path);
        ErrorHandler.handle(resp, () -> certificateService.createCertificate(dto));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ErrorHandler.handle(resp, () -> certificateService.getAllCertificate());
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CertificateDTO dto = Helper.paramsToString(req.getParameterMap()).toModel(CertificateDTO.class);
        if (CheckContainsFile.check(req)) {
            String path = SaveFile.save(req, "image");
            dto.setPath(path);
        }
        ErrorHandler.handle(resp, () -> certificateService.updateCertificate(dto));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ErrorHandler.handle(resp, () -> certificateService.deleteCertificate(QueryParams.get(req).get("id")));
    }
}
