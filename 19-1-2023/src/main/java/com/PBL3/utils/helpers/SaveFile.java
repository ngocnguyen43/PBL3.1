package com.PBL3.utils.helpers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;

public class SaveFile {
    private static final String UPLOAD_DIRECTORY = "public";

    public static String save(HttpServletRequest request, String folder) {

        Part filePart = null;
        try {
            filePart = request.getPart("image");
            String fileName = filePart.getSubmittedFileName();
            String id = IDGeneration.generate();
            String[] fileNameSplits = fileName.split("\\.");
            int extensionIndex = fileNameSplits.length - 1;
            String path = null;
            for (Part part : request.getParts()) {
                path = "/" + id + "." + fileNameSplits[extensionIndex];
                part.write("D:\\PBL3.1\\" + folder + "\\" + id + "." + fileNameSplits[extensionIndex]);
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
