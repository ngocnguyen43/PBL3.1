package com.PBL3.utils.mapper;

import com.PBL3.models.Notification;
import com.PBL3.utils.helpers.DivergenceJson;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NotificationMapper implements IMapper<Notification> {
    @Override
    public Notification mapRow(ResultSet result) {
        Notification notification = new Notification();
        try {
            notification.setId(result.getString("notification_id"));
            notification.setCreatedAt(result.getTimestamp("created_at"));
            notification.setUpdatedAt(result.getTimestamp("updated_at"));
            notification.setModifiedBy(result.getString("modified_by"));
            ArrayList<List<String>> list = DivergenceJson.get(result.getString("refs"));
            if (list != null) {
                notification.setMods(list.get(1));
                notification.setUsers(list.get(0));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notification;
    }
}
