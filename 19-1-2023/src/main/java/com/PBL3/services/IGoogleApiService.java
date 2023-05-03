package com.PBL3.services;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface IGoogleApiService {
    String getAccessToken();
    String refreshAccessToken() throws IOException;
    String uploadFile(HttpServletRequest request) throws ServletException, IOException;
    void createPermissions() throws IOException;
    void configGoogleDrive(String name);
}
