package com.PBL3.controllers;

import com.PBL3.config.EnvConfig;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@WebServlet("/img/*")
public class ImageResource extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static final String UPLOAD_DIRECTORY = "image";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        OutputStream out = resp.getOutputStream();
        String encodings = req.getHeader("Accept-Encoding");
        resp.setHeader("Content-Encoding", "x-compress");
        String pathInfo = req.getPathInfo();
        String[] parts = pathInfo.substring(1).split("/");

//        String uploadPath = "D:\\PBL3.1" + File.separator + UPLOAD_DIRECTORY + File.separator + parts[0];
        String uploadPath = EnvConfig.load().get("IMAGE_FOLDER") + File.separator + parts[0];
        Path path = Paths.get(uploadPath);
        String filetype = Files.probeContentType(path);
        if (filetype == null) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        resp.setContentType(filetype);
        File file = new File(uploadPath);
        FileInputStream in = new FileInputStream(file);
        IOUtils.copy(in, out);
        out.close();
        in.close();

    }


}
