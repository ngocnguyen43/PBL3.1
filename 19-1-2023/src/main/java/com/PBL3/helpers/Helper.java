package com.PBL3.helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.fasterxml.jackson.core.JsonParseException;
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
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static Helper of(BufferedReader reader) {
		StringBuilder sb = new StringBuilder();
		String line;
		try {
			while((line = reader.readLine()) != null) {
				sb.append(line);
;		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
//			e.printStackTrace();
		}
		return new Helper(sb.toString());
	}
}
