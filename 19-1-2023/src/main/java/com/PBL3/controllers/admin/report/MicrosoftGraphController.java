package com.PBL3.controllers.admin.report;

import com.PBL3.services.IMicrosoftService;
import com.PBL3.utils.exceptions.dbExceptions.UnexpectedException;
import com.PBL3.utils.helpers.SplitEmbedUri;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.PBL3.utils.Constants.EndPoint.V1;

@WebServlet(urlPatterns = {V1 + "/test"})
@MultipartConfig
public class MicrosoftGraphController extends HttpServlet {

    @Inject
    private IMicrosoftService microsoftService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(SplitEmbedUri.split("https://ntphtdn-my.sharepoint.com/:w:/g/personal/minhngoc_40303_onedrive_iesschool_edu_vn/Ea8xiCfV3uFIj7imfs0sO78B4U0sKoXV656VfbbrKdZy7Q"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            System.out.println(microsoftService.uploadFile(req));
        } catch (UnexpectedException e) {
            throw new RuntimeException(e);
        }

    }
}
