package com.PBL3.controllers.admin.post;

import com.PBL3.dtos.pagination.PostPaginationDTO;
import com.PBL3.services.IPostService;
import com.PBL3.utils.Constants.EndPoint;
import com.PBL3.utils.exceptions.ErrorHandler;
import com.PBL3.utils.helpers.Helper;
import com.PBL3.utils.helpers.QueryParams;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {EndPoint.V1 + EndPoint.PRIVATE + EndPoint.ADMIN + "/posts"})
public class PostController extends HttpServlet {
    @Inject
    private IPostService iPostService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PostPaginationDTO dto = Helper.paramsToString(req.getParameterMap()).toModel(PostPaginationDTO.class);
        ErrorHandler.handle(resp, () -> iPostService.GetAll(dto));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ErrorHandler.handle(resp, () -> iPostService.InactivePost(QueryParams.get(req).get("id")));
    }
}
