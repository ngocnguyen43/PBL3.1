package com.PBL3.filters;

import com.PBL3.config.ResponseConfig;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"})
public class GlobalFilter implements Filter {
    private static final String REQUEST_METHOD_POST = "POST";
    private static final String CONTENT_TYPE_MULTIPART = "multipart/";


    public static final boolean isMultipartRequest(HttpServletRequest request) {
        return REQUEST_METHOD_POST.equalsIgnoreCase(request.getMethod())
                && request.getContentType() != null
                && request.getContentType().toLowerCase().startsWith(CONTENT_TYPE_MULTIPART);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("UTF-8");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if ("OPTIONS".equals(request.getMethod())) {
            ResponseConfig.ConfigHeader((HttpServletResponse) servletResponse);
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }
        ResponseConfig.ConfigHeader((HttpServletResponse) servletResponse);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
