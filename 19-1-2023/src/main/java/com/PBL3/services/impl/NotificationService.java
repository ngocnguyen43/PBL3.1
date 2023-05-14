package com.PBL3.services.impl;

import com.PBL3.daos.INotificationDAO;
import com.PBL3.dtos.NotificationDTO;
import com.PBL3.models.Notification;
import com.PBL3.services.INotificationService;
import com.PBL3.utils.helpers.Helper;
import com.PBL3.utils.helpers.IDGeneration;
import com.PBL3.utils.response.Message;
import com.PBL3.utils.response.Meta;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.util.InvalidPropertiesFormatException;

public class NotificationService implements INotificationService {
    @Inject
    private INotificationDAO iNotificationDAO;
    @Override
    public Message create(Notification domain) throws InvalidPropertiesFormatException, JsonProcessingException {
        if (domain == null) throw new InvalidPropertiesFormatException("Invalid properties");
        iNotificationDAO.create(domain);
        Meta meta = new Meta.Builder(HttpServletResponse.SC_CREATED).withMessage("created").build();
        return new Message.Builder(meta).build();
    }
}
