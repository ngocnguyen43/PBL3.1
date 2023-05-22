package com.PBL3.controllers.admin.user;

import com.PBL3.dtos.UserDTO;
import com.PBL3.services.IUserService;
import com.PBL3.utils.exceptions.ErrorHandler;
import com.PBL3.utils.helpers.Helper;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.PBL3.utils.Constants.EndPoint.V1;

@WebServlet(urlPatterns = {V1 + "/user/*"})
@MultipartConfig
public class UserController extends HttpServlet {

    private static final long serialVersionUID = -7630254703535243920L;
    /**
     *
     */
    @Inject
    private IUserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(new ObjectMapper().writeValueAsString(req.getPathInfo().substring(1)));
        String id = req.getPathInfo().substring(1);
        ErrorHandler.handle(resp, () -> userService.findOne(id));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getPathInfo().substring(1);
        UserDTO dto = Helper.paramsToString(req.getParameterMap()).toModel(UserDTO.class);
        ErrorHandler.handle(resp, () -> userService.update(dto, id, req.getHeader("client_id")));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getPathInfo().substring(1);
        ErrorHandler.handle(resp, () -> userService.delete(id, req.getHeader("client_id")));
    }
}
