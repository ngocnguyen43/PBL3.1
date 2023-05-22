package com.PBL3.utils.helpers;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class QueryParams {
    public static Map<String, String> get(HttpServletRequest request) throws UnsupportedEncodingException {
        Map<String, String> queryParameters = new HashMap<>();
        String queryString = request.getQueryString();
        System.out.println(queryString);
        if (StringUtils.isNotEmpty(queryString)) {
            queryString = URLDecoder.decode(queryString, StandardCharsets.UTF_8.toString());
            String[] parameters = queryString.split("&");
            for (String parameter : parameters) {
                String[] keyValuePair = parameter.split("=");
                queryParameters.put(keyValuePair[0], keyValuePair[1]);
            }
        }
        return queryParameters;
    }

    public static Map<String, String[]> getQuery(HttpServletRequest request) throws UnsupportedEncodingException {
        Map<String, String[]> queryParameters = new HashMap<>();
        String queryString = request.getQueryString();
        if (StringUtils.isNotEmpty(queryString)) {
            queryString = URLDecoder.decode(queryString, StandardCharsets.UTF_8.toString());
            String[] parameters = queryString.split("&");
            for (String parameter : parameters) {
                String[] keyValuePair = parameter.split("=");
                String[] temp = {keyValuePair[1]};
                queryParameters.put(keyValuePair[0], temp);
            }
        }
        return queryParameters;
    }

}
