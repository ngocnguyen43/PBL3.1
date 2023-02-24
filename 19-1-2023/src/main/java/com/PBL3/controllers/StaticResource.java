package com.PBL3.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

@WebServlet("/img/*")
public class StaticResource extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIRECTORY = "public";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
//	      ServletContext cntx= req.getServletContext();

//		 String pathInfo = req.getPathInfo();
//		  String[] parts = pathInfo.substring(1).split("/");
//		String fileName = req.getParameter("fileName");
//			+ File.separator + UPLOAD_DIRECTORY;

//		String filePath = uploadPath+ File.separator + parts[0];
//
//		String uploadPath = getServletContext().getRealPath("public/lDNgq7J6KJecHZzrtyEGmdS.png");
//		System.out.println(uploadPath);
//		ServletContext context = getServletContext();
////		String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
//		Path path = Paths.get(uploadPath);
////		String mime = context.getMimeType(uploadPath);
//		String test = Files.probeContentType(path);
//		
//		System.out.println(test);
//		System.out.println(System.getProperty("java.io.tmpdir"));
//		// Error handling
//		if (test == null) {
//			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//			return;
//		}
//	
		Path currentDirPath = Paths.get("");
		String currentDir = Paths.get(".").toAbsolutePath().normalize().toString();
		String xxx = getServletContext().getRealPath("/");
		File abc = new File("");
		
		
		Properties prop = new Properties();
		InputStream input = null;
		
		input = new FileInputStream("config.properties");
		System.out.println(xxx);
		// // Setting MIME content type
//		resp.setContentType(test);
//		// Getting file objec
//		File file = new File(uploadPath);
//		// Setting content length header
//		resp.setContentLength((int) file.length());
//
//		// FileInputStream to read from file
//		FileInputStream in = new FileInputStream(file);
//		// Obtain OutputStream from response object
//		OutputStream out = resp.getOutputStream();
//
//		// Writing to the OutputStream
////		byte[] buffer = new byte[1024];
////		int bytes = 0;
////		// we stop when in.read returns -1 and read untill it does not
////		while ((bytes = in.read(buffer)) >= 0) {
////			out.write(buffer, 0, bytes);
////		}
//	      IOUtils.copy(in,out);
//		// Clean up, closing resources
//		out.close();
//		in.close();

	}



}
