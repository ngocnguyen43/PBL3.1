package com.PBL3.services.impl;

import com.PBL3.daos.INotificationDAO;
import com.PBL3.dtos.NotificationDTO;
import com.PBL3.dtos.pagination.NotificationPaginationDTO;
import com.PBL3.models.Notification;
import com.PBL3.models.pagination.NotificationPaginationModel;
import com.PBL3.services.INotificationService;
import com.PBL3.utils.exceptions.dbExceptions.UnexpectedException;
import com.PBL3.utils.helpers.Helper;
import com.PBL3.utils.response.*;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.util.InvalidPropertiesFormatException;
import java.util.List;

import static com.PBL3.utils.Constants.Pagination.PER_PAGE;

public class NotificationService implements INotificationService {
    @Inject
    private INotificationDAO iNotificationDAO;

    @Override
    public void create(Notification domain) throws InvalidPropertiesFormatException, JsonProcessingException, UnexpectedException {
        if (domain == null) throw new InvalidPropertiesFormatException("Invalid properties");
        try {
            iNotificationDAO.create(domain);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UnexpectedException();
        }
    }

    @Override
    public Message getAllNotifications(NotificationPaginationDTO dto, String id) {
        NotificationPaginationModel domain = Helper.objectMapper(dto, NotificationPaginationModel.class);
        List<NotificationDTO> list = iNotificationDAO.getAllById(domain, id);
        Integer pages = iNotificationDAO.countAllNotifications(id);
        Pagination pagination = new Pagination.Builder().
                withCurrentPage(domain.getPage()).
                withTotalPages((int) Math.ceil((double) pages / PER_PAGE)).
                withTotalResults(pages).build();
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.OK).build();
        Data data = new Data.Builder(null).withResults(list).build();
        return new Message.Builder(meta).withData(data).withPagination(pagination).build();
    }
}
