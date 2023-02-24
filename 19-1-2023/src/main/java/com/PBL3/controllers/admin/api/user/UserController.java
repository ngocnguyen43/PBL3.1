package com.PBL3.controllers.admin.api.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.PBL3.services.IUserService;
import com.PBL3.utils.exceptions.Exception;
import com.PBL3.utils.exceptions.apiExcpetions.InvalidEndpointException;
import com.PBL3.utils.exceptions.authExceptions.UnauthorizedException;
import com.PBL3.utils.response.Message;
import com.PBL3.utils.response.Meta;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet(urlPatterns = { "/api/v1/admin/user/*" })
public class UserController extends HttpServlet {

	/**
	 * 
	 */
	@Inject
	private IUserService userService;
	private static final long serialVersionUID = -7630254703535243920L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		ObjectMapper obj = new ObjectMapper();
//		Message message = new Message();
//		String json = null;
//		try {
//			if (req.getPathInfo() == null && req.getPathInfo().length() <= 1) throw new InvalidEndpointException();
//			List<String> paths = Arrays.asList(req.getPathInfo().substring(1).split("/"));
//			if (paths.size() > 1) throw new InvalidEndpointException();
////			if (req.getPathInfo() != null && req.getPathInfo().length() > 1) {
////
////				
////				if (paths.size() > 1) {
////					throw new UnauthorizedException();
//////					Headers header = new Headers.Builder(HttpServletResponse.SC_REQUEST_URI_TOO_LONG).withErrCode(5)
//////							.withMessage("Invalid URI!").build();
//////					message.setHeader(header);
//////					 json = obj.writeValueAsString(message);
//////					resp.setStatus(HttpServletResponse.SC_REQUEST_URI_TOO_LONG);
////				} else {
//					 message = userService.findByUserId(paths.get(0));
//					 json = obj.writeValueAsString(message);
////					resp.setStatus(message.getHeader().getStatusCode());
////				}
////			} else {
////				Headers header = new Headers.Builder(HttpServletResponse.SC_BAD_REQUEST).withErrCode(5)
////						.withMessage("Invalid URI!").build();
////				message.setHeader(header);
////				 json = obj.writeValueAsString(message);
////				resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
////			}
//		} catch ( Exception e) {
//			// TODO: handle exception
//			System.out.println(e.getErrorCode());
////			Headers header = new Headers.Builder(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).withErrCode(10)
////					.withMessage(e.getMessage()).build();
////			message.setHeader(header);
////			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//			resp.setStatus( e.getStatusCode());
//
//		}
//		out.print(json);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
