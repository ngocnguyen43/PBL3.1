package com.PBL3.controllers;

import com.PBL3.services.INotificationService;
import com.PBL3.utils.exceptions.ErrorHandler;
import com.PBL3.utils.helpers.DivergenceJson;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.PBL3.utils.Constants.EndPoint.V1;

@WebServlet(urlPatterns = {V1 + "/try"})
public class TestController extends HttpServlet {
    @Inject
    private INotificationService iNotificationService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        String jdbcUrl = "jdbc:mysql://localhost:3306/login";
//        String username = "root";
//        String password = "040303";
//        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password)) {
//            String insertSql = "INSERT INTO login.test(json) VALUES (?)";
//            PreparedStatement stmt = conn.prepareStatement(insertSql);
//
//            ObjectMapper mapper = new ObjectMapper();
//
//            ObjectNode objectNode = mapper.createObjectNode();
//            objectNode.put("created_by", "nah");
//            ObjectNode refsNode = mapper.createObjectNode();
//            refsNode.putArray("inspectors").add("abc").add("xyz").add("zzz");
//            refsNode.putArray("companies");
//            objectNode.set("refs", refsNode);
//
//            String jsonString = mapper.writeValueAsString(objectNode);
//
//            stmt.setString(1, jsonString);
//
//            int rowsInserted = stmt.executeUpdate();
//            if (rowsInserted > 0) {
//                System.out.println("JSON data inserted successfully");
//            }
//        } catch (SQLException e) {
//            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        ErrorHandler.handle(resp, () -> iNotificationService.getAllNotifications(req.getHeader("client_id")));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String jdbcUrl = "jdbc:mysql://localhost:3306/login";
        String username = "root";
        String password = "040303";

        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password)) {
            String selectSql = "SELECT * FROM login.test";
            PreparedStatement stmt = conn.prepareStatement(selectSql);


            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String jsonString = rs.getString("json");
                ObjectMapper mapper = new ObjectMapper();
                JsonNode jsonNode = mapper.readTree(jsonString);
                String prettyString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
//                Map<String, ArrayList<String>> map = mapper.readValue(jsonString, Map.class);
//                ArrayList<String> usersList = map.get("users");
//                ArrayList<String> modsList = map.get("mods");
//                String[] users = usersList.toArray(new String[0]);
//                String[] mods = modsList.toArray(new String[0]);
                ArrayList<List<String>> list = DivergenceJson.get(jsonString);
                System.out.println("Users: " + list.get(0).toString());
                System.out.println("Mods: " + list.get(1).toString());

//                System.out.println(prettyString);
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        NotificationDTO dto = Helper.of(req.getReader()).toModel(NotificationDTO.class);
//        dto.setCreator(req.getHeader("id"));
//        dto.setModifiedBy(req.getHeader("id"));
        //ErrorHandler.handle(resp, () -> iNotificationService.create(dto));
    }
}
