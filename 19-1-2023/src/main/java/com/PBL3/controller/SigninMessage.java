package com.PBL3.controller;

public class SigninMessage {
	
	private Integer statusCode;
	private String userId;
	private String message;
	private String ACCESS_TOKEN;
	private String REFRESH_TOKEN;
	private String role;
	public SigninMessage() {}
	public SigninMessage(Integer statusCode,String userId, String message, String ACCESS_TOKEN, String REFRESH_TOKEN) {
		this.statusCode = statusCode;
		this.userId = userId;
		this.message = message;
		this.ACCESS_TOKEN = ACCESS_TOKEN;
		this.REFRESH_TOKEN = REFRESH_TOKEN;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getACCESS_TOKEN() {
		return ACCESS_TOKEN;
	}

	public void setACCESS_TOKEN(String aCCESS_TOKEN) {
		ACCESS_TOKEN = aCCESS_TOKEN;
	}

	public String getREFRESH_TOKEN() {
		return REFRESH_TOKEN;
	}

	public void setREFRESH_TOKEN(String rEFRESH_TOKEN) {
		REFRESH_TOKEN = rEFRESH_TOKEN;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}


}
