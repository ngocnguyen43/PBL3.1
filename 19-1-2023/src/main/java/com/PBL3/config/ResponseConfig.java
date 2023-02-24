package com.PBL3.config;

import javax.servlet.http.HttpServletResponse;

public class ResponseConfig {
	public static void ConfigHeader(HttpServletResponse res) {
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT,PATCH, DELETE, HEAD");
		res.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
		res.setContentType("application/json");
	}
}
