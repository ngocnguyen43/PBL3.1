package com.PBL3.utils.exceptions;

import com.PBL3.utils.response.Message;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ErrorHandler {
    static public void handle(HttpServletResponse res,Message func){
        try {
            res.setStatus(func.getMeta().getStatusCode());
            res.getWriter().print(new ObjectMapper().writeValueAsString(func));
            res.getWriter().flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
