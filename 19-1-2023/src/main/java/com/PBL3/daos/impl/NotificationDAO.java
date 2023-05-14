package com.PBL3.daos.impl;

import com.PBL3.daos.INotificationDAO;
import com.PBL3.models.Notification;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.List;

public class NotificationDAO extends AbstractDAO<Notification> implements INotificationDAO {
    @Override
    public void create(Notification domain) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();
        ArrayNode mods = objectNode.putArray("mods");
        if (domain.getMods() != null) {
            domain.getMods().forEach(mods::add);
        }
        if (domain.getUsers() != null) {
            ArrayNode users = objectNode.putArray("users");
            domain.getUsers().forEach(users::add);
        }

        String jsonString = mapper.writeValueAsString(objectNode);

        String sql = "INSERT INTO login.notifications (notification_id,creator,refs,modified_by) VALUES (?,?,?,?)";

        insert(sql, domain.getId(), domain.getCreator(), jsonString, domain.getModifiedBy());

    }

    @Override
    public List<Notification> getAllById() {
        return null;
    }
}
