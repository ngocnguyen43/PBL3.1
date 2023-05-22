package com.PBL3.controllers.admin.postTypes;

import com.PBL3.dtos.PostTypeDTO;
import com.PBL3.services.IPostTypeService;
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
@WebServlet(urlPatterns = {EndPoint.V1 + EndPoint.PRIVATE + EndPoint.ADMIN + "/post/types"})
public class PostTypeController extends HttpServlet {
    @Inject
    private IPostTypeService iPostTypeService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PostTypeDTO postTypeDTO = Helper.paramsToString(req.getParameterMap()).toModel(PostTypeDTO.class);
        ErrorHandler.handle(resp, () -> iPostTypeService.Create(postTypeDTO, req.getHeader("client_id")));
    }
}
