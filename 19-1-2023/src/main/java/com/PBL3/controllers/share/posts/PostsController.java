package com.PBL3.controllers.share.posts;

import com.PBL3.dtos.PostDTO;
import com.PBL3.services.IPostService;
import com.PBL3.utils.Constants.EndPoint;
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

@MultipartConfig
@WebServlet(urlPatterns = {EndPoint.V1 + "/post"})
public class PostsController extends HttpServlet {
    @Inject
    private IPostService iPostService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PostDTO dto = Helper.paramsToString(req.getParameterMap()).toModel(PostDTO.class);
        ErrorHandler.handle(resp, () -> iPostService.Create(dto));
    }
}
