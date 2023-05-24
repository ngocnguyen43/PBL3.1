package com.PBL3.utils.helpers;

import com.PBL3.config.EnvConfig;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

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
            InputStream inputStream = filePart.getInputStream();
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            String path = "/" + id + "." + fileNameSplits[extensionIndex];
            File file = new File(EnvConfig.load().get("IMAGE_FOLDER") + "\\", id + "." + fileNameSplits[extensionIndex]);
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(bytes);
            outputStream.close();
            return path;
        } catch (IOException | ServletException e) {
            throw new RuntimeException(e);
        }
    }
//    public static FileInputStream getImgae(String filename){
//        return new FileInputStream(filename);
//    }
}
