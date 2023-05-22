package com.PBL3.controllers.admin.user;

import com.PBL3.dtos.UserDTO;
import com.PBL3.services.IUserService;
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

import static com.PBL3.utils.Constants.EndPoint.*;

@MultipartConfig
@WebServlet(urlPatterns = {V1 + PRIVATE + ADMIN + "/user"})
public class AdminCreateUserController extends HttpServlet {
    @Inject
    private IUserService userService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDTO dto = Helper.paramsToString(req.getParameterMap()).toModel(UserDTO.class);
        ErrorHandler.handle(resp, () -> userService.save(dto));
    }
}
