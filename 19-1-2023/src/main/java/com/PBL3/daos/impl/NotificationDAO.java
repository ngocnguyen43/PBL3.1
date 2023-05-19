package com.PBL3.daos.impl;

import com.PBL3.daos.INotificationDAO;
import com.PBL3.daos.IUserDAO;
import com.PBL3.dtos.NotificationDTO;
import com.PBL3.models.Notification;
import com.PBL3.utils.mapper.NotificationMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javax.inject.Inject;
import java.util.List;

public class NotificationDAO extends AbstractDAO<Notification> implements INotificationDAO {
    @Inject
    private IUserDAO userDAO;

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
        if (domain.getAdmin() != null) {
            ObjectNode admin = objectNode.put("admin", domain.getAdmin());
        }
        String jsonString = mapper.writeValueAsString(objectNode);

        String sql = "INSERT INTO login.notifications (notification_id,creator,message,refs,modified_by) VALUES (?,?,?,?,?)";

        insert(sql, domain.getId(), domain.getCreator(), domain.getMessage(), jsonString, domain.getModifiedBy());

    }

    @Override
    public List<NotificationDTO> getAllById(String id) {
        String role = userDAO.getUserRole(id);
        String sql = "SELECT creator,message,created_at FROM login.notifications " +
                "WHERE JSON_CONTAINS(refs->'$.mods','\"" +
                id +
                "\"') " +
                "OR JSON_CONTAINS(refs->'$.users','\"all\" " + "AND refs->'$.admin' = false";
        String sqlUser = "SELECT creator,message,created_at FROM login.notifications " +
                "WHERE JSON_CONTAINS(refs->'$.users','\"" +
                id +
                "\"') ";
        String sqlAdmin = "SELECT creator,message,created_at FROM login.notifications " +
                "WHERE refs->'$.admin' = true ";
//        SELECT * FROM login.notifications
//        WHERE JSON_CONTAINS(refs->'$.mods', '"N-4ebNaV1ab_X-vNlbyv-yy"')
//        OR JSON_LENGTH(refs->'$.mods') = 0;
        System.out.println(sql);
        return role.equals("Store") ? query(sqlUser, new NotificationMapper()) :
                role.equals("Admin") ? query(sqlAdmin, new NotificationMapper()) :
                        query(sql, new NotificationMapper());
    }
}
