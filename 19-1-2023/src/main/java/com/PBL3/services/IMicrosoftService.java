package com.PBL3.services;

import com.PBL3.utils.exceptions.dbExceptions.UnexpectedException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface IMicrosoftService {
    String refreshToken() throws IOException;

    String uploadFile(HttpServletRequest request) throws UnexpectedException, ServletException, IOException;

    String createShareLink(String itemId, String token) throws IOException;
}
