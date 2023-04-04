package com.PBL3.controllers.admin.user;

import com.PBL3.dtos.pagination.UserPaginationDTO;
import com.PBL3.models.pagination.UserPagination;
import com.PBL3.services.IUserService;
import com.PBL3.utils.Constants.EndPoint;
import com.PBL3.utils.exceptions.ErrorHandler;
import com.PBL3.utils.helpers.Helper;
import com.PBL3.utils.helpers.QueryParams;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(EndPoint.V1 + EndPoint.PRIVATE + EndPoint.ADMIN + "/users")
@MultipartConfig
public class AdminUsersController extends HttpServlet {

    private static final long serialVersionUID = -3848721747697052811L;
    @Inject
    private IUserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserPaginationDTO dto = Helper.paramsToString(QueryParams.getQuery(req)).toModel(UserPaginationDTO.class);
        UserPagination domain = Helper.objectMapper(dto,UserPagination.class);
        System.out.println(new ObjectMapper().writeValueAsString(domain));
        ErrorHandler.handle(resp, () -> userService.findAll(dto,"ADMIN"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
