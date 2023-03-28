package com.PBL3.utils.exceptions;

import com.PBL3.utils.response.Message;
import com.PBL3.utils.response.Meta;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.Callable;

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
    static  public void handle(HttpServletResponse res, Callable<Message> function ) throws IOException {
        try {
            Message message = function.call();
            res.setStatus(function.call().getMeta().getStatusCode());
            res.getWriter().print(new ObjectMapper().writeValueAsString(function.call()));
            res.getWriter().flush();
        }
        catch (java.lang.Exception e) {
            if (e instanceof Exception){
                Meta meta = new Meta.Builder(((Exception) e).statusCode).withErrCode(((Exception) e).getErrorCode()).withError(e.getMessage()).build();
                res.getWriter().print(new ObjectMapper().writeValueAsString(new Message.Builder(meta).build()));
                res.getWriter().flush();
            }
            else {
                throw new RuntimeException(e);
            }
        }
    }
}
