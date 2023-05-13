package com.PBL3.daos;

import com.PBL3.models.Notification;

import java.util.List;

public interface INotificationDAO extends GenericDAO<Notification> {
    void create(Notification notification);

    List<Notification> getAllById();
}
