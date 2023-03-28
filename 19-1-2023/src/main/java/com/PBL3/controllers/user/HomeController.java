package com.PBL3.controllers.user;

import com.PBL3.models.Role;
import com.PBL3.services.impl.RoleService;
import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;

@WebServlet(urlPatterns = {"/home"})
public class HomeController extends HttpServlet {

    public static final char[] salt = "_-0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%^&*()"
            .toCharArray();
    private static final long serialVersionUID = 1L;
    Random random = new Random();
    /**
     *
     */
    @Inject
    private RoleService roleService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Role> roles = roleService.findAll();
        PrintWriter out = resp.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(roles);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
//		resp.getWriter().write(objectMapper.writeValueAsString(jsonString));
        String nanoId = NanoIdUtils.randomNanoId(random, salt, 15);
        System.out.println(nanoId);
//	
//		Properties properties = System.getProperties();
//		Enumeration<Object> enumeration = properties.keys();
//		for (int i = 0; i < properties.size(); i++) {
//		    Object obj = enumeration.nextElement();
//		    System.out.println("Key: "+obj+"\tOutPut= "+System.getProperty(obj.toString()));
//		}
        String currentUsersHomeDir = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        System.out.println(currentUsersHomeDir);
        out.print(jsonString);
        out.flush();
//		RequestDispatcher rd = req.getRequestDispatcher("/VIEWS/WEB/home.jsp");
//		rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // TODO Auto-generated method stub
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = req.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
            buffer.append(System.lineSeparator());
        }
        String data = buffer.toString();

        PrintWriter out = resp.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(data);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
//		resp.getWriter().write(objectMapper.writeValueAsString(jsonString));
        out.print(jsonString);
        out.flush();
    }
}
