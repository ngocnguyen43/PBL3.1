package com.PBL3.daos;

import com.PBL3.dtos.NotificationDTO;
import com.PBL3.models.Notification;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface INotificationDAO extends GenericDAO<Notification> {
    void create(Notification domain) throws JsonProcessingException;

    List<NotificationDTO> getAllById(String id);
}
