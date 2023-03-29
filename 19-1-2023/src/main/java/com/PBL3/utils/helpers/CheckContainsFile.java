package com.PBL3.utils.helpers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class CheckContainsFile {
    static public boolean check(HttpServletRequest req) {
        try {
            return req.getPart("file") != null && req.getPart("file").getSize() > 0;
        } catch (IOException | ServletException e) {
            return false;
        }
    }
}
