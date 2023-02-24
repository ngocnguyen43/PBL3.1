package com.PBL3.controllers.admin.api.certificate;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.PBL3.config.ResponseConfig;
import com.PBL3.utils.helpers.IDGeneration;

@WebServlet(urlPatterns = { "/api/v1/private/certificate" })
public class CertifiacateController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String UPLOAD_DIRECTORY = "public";

	// upload settings
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		resp.addHeader("Access-Control-Allow-Origin", "*");
//		resp.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
//		resp.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
		ResponseConfig.ConfigHeader(resp);
		ArrayList<String> imagepath = new ArrayList<String>();

		// checks if the request actually contains upload file
		if (!ServletFileUpload.isMultipartContent(req)) {
			System.out.println(("Error: Form must has enctype=multipart/form-data."));
			return;
		}

		// configures upload settings
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// sets memory threshold - beyond which files are stored in disk
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		// sets temporary location to store files
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

		ServletFileUpload upload = new ServletFileUpload(factory);

		// sets maximum size of upload file
		upload.setFileSizeMax(MAX_FILE_SIZE);

		// sets maximum size of request (include file + form data)
		upload.setSizeMax(MAX_REQUEST_SIZE);

		// constructs the directory path to store upload file
		// this path is relative to application's directory
		String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;

		// creates the directory if it does not exist
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}

		try {
			// parses the request's content to extract file data
			@SuppressWarnings("unchecked")
			List<FileItem> formItems = upload.parseRequest(req);

			if (formItems != null && formItems.size() > 0) {
				// iterates over form's fields
				for (FileItem item : formItems) {
					// processes only fields that are not form fields
					if (!item.isFormField()) {
						String fileName = new File(item.getName()).getName();

						String id = IDGeneration.generate();

						String[] fileNameSplits = fileName.split("\\.");
						int extensionIndex = fileNameSplits.length - 1;
						String newfilename = id + "." + fileNameSplits[extensionIndex];
						imagepath.add(newfilename);

						String filePath = uploadPath + File.separator + newfilename;
						File storeFile = new File(filePath);

						// saves the file on disk
						item.write(storeFile);
						System.out.println("Successfully");
					}
				}
			}
		} catch (Exception ex) {
//			request.setAttribute("message", "There was an error: " + ex.getMessage());
		}
		// redirects client to message page
//		getServletContext().getRequestDispatcher("/message.jsp").forward(request, response);
//		httpC
		resp.setStatus(200);
	}
}
