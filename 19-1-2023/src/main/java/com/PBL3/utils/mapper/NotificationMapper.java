package com.PBL3.utils.mapper;

import com.PBL3.dtos.NotificationDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NotificationMapper implements IMapper<NotificationDTO> {
    @Override
    public NotificationDTO mapRow(ResultSet result) {
        NotificationDTO notification = new NotificationDTO();
        try {
            //notification.setCreator(result.getString("creator"));
            notification.setCreatedAt(result.getTimestamp("created_at"));
            notification.setMessage(result.getString("message"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notification;
    }
}
