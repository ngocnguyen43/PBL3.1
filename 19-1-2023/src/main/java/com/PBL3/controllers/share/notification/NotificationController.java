package com.PBL3.controllers.share.notification;

import com.PBL3.dtos.pagination.NotificationPaginationDTO;
import com.PBL3.services.INotificationService;
import com.PBL3.utils.exceptions.ErrorHandler;
import com.PBL3.utils.helpers.Helper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.PBL3.utils.Constants.EndPoint.V1;

@WebServlet(urlPatterns = {V1 + "/notifications"})
public class NotificationController extends HttpServlet {
    @Inject
    private INotificationService iNotificationService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NotificationPaginationDTO dto = Helper.paramsToString(req.getParameterMap()).toModel(NotificationPaginationDTO.class);
        ErrorHandler.handle(resp, () -> iNotificationService.getAllNotifications(dto, req.getHeader("client_id")));
    }
}
