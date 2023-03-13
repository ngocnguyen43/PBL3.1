package com.PBL3.utils.helpers;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class HandleImage {
    private static final String UPLOAD_DIRECTORY = "public";

    // upload settings
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

    public static String save(HttpServletRequest request){

        ArrayList<String> imagepath = new ArrayList<String>();

        if (!ServletFileUpload.isMultipartContent(request)) {
            System.out.println(("Error: Form must has enctype=multipart/form-data."));
            return null;
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

        String uploadPath = "D:\\PBL3.1" + File.separator + UPLOAD_DIRECTORY;
        // creates the directory if it does not exist
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        try {
            List<FileItem> formItems = upload.parseRequest(request);

            if (formItems != null && formItems.size() > 0) {
                // iterates over form's fields
                        String id = IDGeneration.generate();
                for (FileItem item : formItems) {
                    // processes only fields that are not form fields
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();


                        String[] fileNameSplits = fileName.split("\\.");
                        int extensionIndex = fileNameSplits.length - 1;
                        String newfilename = id + "." + fileNameSplits[extensionIndex];
                        imagepath.add(newfilename);

                        String filePath = uploadPath + File.separator + newfilename;
                        File storeFile = new File(filePath);

                        // saves the file on disk
                        item.write(storeFile);
                        System.out.println("Successfully");
                        return "\\" + newfilename;
                    }
                }
            }
        } catch (Exception ex) {
//			request.setAttribute("message", "There was an error: " + ex.getMessage());
            System.out.println("Error to save image");
            return null;
        }
            return null;
    }
//    public static FileInputStream getImgae(String filename){
//        return new FileInputStream(filename);
//    }
}
