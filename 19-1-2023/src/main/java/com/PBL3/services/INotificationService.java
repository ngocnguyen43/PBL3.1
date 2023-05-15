package com.PBL3.services;

import com.PBL3.models.Notification;
import com.PBL3.utils.exceptions.dbExceptions.UnexpectedException;
import com.PBL3.utils.response.Message;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.InvalidPropertiesFormatException;

public interface INotificationService {
    void create(Notification dto) throws InvalidPropertiesFormatException, JsonProcessingException, UnexpectedException;
    Message getAllNotifications(String id);
}
