package com.PBL3.utils.helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Helper {
	private String data;

	public Helper(String data) {
		this.data = data;
	}

	public <T> T toModel(Class<T> tClass) {
		try {
			return new ObjectMapper().readValue(data, tClass);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public static Helper of(BufferedReader reader) {
		StringBuilder sb = new StringBuilder();
		String line;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			System.out.println(e);
		}
//		System.out.println(sb.toString());
		return new Helper(sb.toString());
	}


	public static <T> T objectMapper(Object object,Class<T> tClass){
    	ObjectMapper mapper = new ObjectMapper();
    	mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    	T res = mapper.convertValue(object, tClass);
        return res;
    }

}
