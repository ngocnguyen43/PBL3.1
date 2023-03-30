package com.PBL3.controllers.user;

import com.PBL3.services.IUserService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(urlPatterns = {"/api/v1/users"})
public class GetUsers extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Inject
    private IUserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
//        List<User> users = userService.findAll();
//        System.out.println(users);
//        PrintWriter out = resp.getWriter();
//        ObjectMapper objectMapper = new ObjectMapper();
//        String jsonString = objectMapper.writeValueAsString(users);
//        resp.setContentType("application/json");
//        resp.setCharacterEncoding("UTF-8");
//        out.print(jsonString);
//        out.flush();
//		super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		User user = new User();
//		user.setAction(1);
//		user.setEmail("mcncnbcsss");
//		user.setFirstName("test servlet");
//		user.setLastName("bingnngg");	
//		user.setNationalId("AbcAbcc");
//		user.setPassword("123456");
//		user.setPhoneNumber("098765432");
//		user.setRoleId(1);
//		user.setGender(1);
//		user.setUserName("aloalo");
//		user.setModifiedBy("d4216c1a-fabe-4dea-9b9e-84271c784497");
//		String id = userService.save(user);
//		System.out.println(id);
//		PrintWriter out = resp.getWriter();
//		ObjectMapper objectMapper= new ObjectMapper();
//		String jsonString = objectMapper.writeValueAsString(id);
//		resp.setContentType("application/json");
//		resp.setCharacterEncoding("UTF-8");
//		out.print(jsonString);
//		out.flush();
//		super.doPost(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub

    }

}
