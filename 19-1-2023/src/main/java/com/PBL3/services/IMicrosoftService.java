package com.PBL3.services;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface IMicrosoftService {
    String refreshToken() throws IOException;
    String uploadFile(HttpServletRequest request);
    void createShareLink(String itemId);
}
