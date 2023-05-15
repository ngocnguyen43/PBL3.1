package com.PBL3.services.impl;

import com.PBL3.daos.INotificationDAO;
import com.PBL3.dtos.NotificationDTO;
import com.PBL3.models.Notification;
import com.PBL3.services.INotificationService;
import com.PBL3.utils.exceptions.dbExceptions.UnexpectedException;
import com.PBL3.utils.response.Data;
import com.PBL3.utils.response.Message;
import com.PBL3.utils.response.Meta;
import com.PBL3.utils.response.Response;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.util.InvalidPropertiesFormatException;
import java.util.List;

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
    public Message getAllNotifications(String id) {
        List<NotificationDTO> list = iNotificationDAO.getAllById(id);
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.OK).build();
        Data data = new Data.Builder(null).withResults(list).build();
        return new Message.Builder(meta).withData(data).build();
    }
}
