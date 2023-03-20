package com.PBL3.utils.helpers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;

public class HandleImage {
    private static final String UPLOAD_DIRECTORY = "public";

    // upload settings
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

    public static String save(HttpServletRequest request){

        Part filePart = null ;
        try {
            filePart = request.getPart("file");
            String fileName = filePart.getSubmittedFileName();
        String id = IDGeneration.generate();
        String[] fileNameSplits = fileName.split("\\.");
        int extensionIndex = fileNameSplits.length - 1;
        String path = null;
        for (Part part : request.getParts()) {
            path =  "\\" + id+"."+fileNameSplits[extensionIndex];
            part.write("D:\\PBL3.1\\public\\" + id+"."+fileNameSplits[extensionIndex]);
        }
        return path;
        } catch (IOException | ServletException e) {
            throw new RuntimeException(e);
        }
    }
//    public static FileInputStream getImgae(String filename){
//        return new FileInputStream(filename);
//    }
}
