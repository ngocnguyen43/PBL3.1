package com.PBL3.utils.helpers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class CheckContainsFile {
    static public boolean check(HttpServletRequest req) {
        try {
            return req.getPart("image") != null && req.getPart("image").getSize() > 0;
        } catch (IOException | ServletException e) {
            return false;
        }
    }
}
