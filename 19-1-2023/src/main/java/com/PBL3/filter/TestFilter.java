package com.PBL3.filter;

import com.PBL3.config.ResponseConfig;
import com.PBL3.utils.Constants.Constants;
import com.PBL3.utils.response.Message;
import com.PBL3.utils.response.Meta;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebFilter(urlPatterns = {Constants.URL_V1 +Constants.PRIVATE + "/admin/*"})
public class TestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        Enumeration<String> headerNames = httpRequest.getHeaderNames();

        String JWT = req.getHeader("ACCESS_TOKEN");
        System.out.println(JWT);
        if (!JWT.equals("1")){
            ResponseConfig.ConfigHeader(res);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage("OK!").build();
            res.setStatus(meta.getStatusCode());
            res.getWriter().print(new ObjectMapper().writeValueAsString(new Message.Builder(meta).build()));
            res.getWriter().flush();
        }else {

        filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
