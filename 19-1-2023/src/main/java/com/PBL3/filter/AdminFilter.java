package com.PBL3.filter;

import com.PBL3.config.ResponseConfig;
import com.PBL3.filter.checkRole.CheckRole;
import com.PBL3.utils.Constants.EndPoint;
import com.PBL3.utils.exceptions.ErrorHandler;
import com.PBL3.utils.response.Message;
import com.PBL3.utils.response.Meta;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {EndPoint.V1 + EndPoint.PRIVATE + EndPoint.ADMIN + "/*"})
public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        ResponseConfig.ConfigHeader(res);
        if (CheckRole.check(req, "ADM")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            Meta meta = new Meta.Builder(HttpServletResponse.SC_FORBIDDEN).withMessage("Forbidden!").build();
            ErrorHandler.handle(res, new Message.Builder(meta).build());
        }
    }

    @Override
    public void destroy() {

    }
}
