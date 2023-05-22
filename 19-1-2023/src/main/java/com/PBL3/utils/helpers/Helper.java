package com.PBL3.utils.helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Helper {
    private final String data;

    public Helper(String data) {
        this.data = data;
    }

    public static Helper of(BufferedReader reader) {
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//		System.out.println(sb.toString());
        return new Helper(sb.toString());
    }

    public static <T> T objectMapper(Object object, Class<T> tClass) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        T res = mapper.convertValue(object, tClass);
        return res;
    }

    public static Helper paramsToString(Map<String, String[]> paramsMap) {
//		Map<String,String[]> data = req.getParameterMap();
        Map<String, String> params = new HashMap<>();
        paramsMap.forEach((key, value) -> {
            params.put(key, value[0]);
        });
        String value;
        try {
            value = new ObjectMapper().writeValueAsString(params);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return new Helper(value);
    }

    public <T> T toModel(Class<T> tClass) {
        try {
            return new ObjectMapper().readValue(data, tClass);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public String read() {
        return this.data;
    }
//	public static String savePart(HttpServletRequest req,String name){
//		Part part;
//		try {
//			 part = req.getPart(name);
//			 if (part == null){
//				 return null;
//			 }
//
//		} catch (IOException e) {
//			throw new RuntimeException(e);
//		} catch (ServletException e) {
//			throw new RuntimeException(e);
//		}
//	}
}
