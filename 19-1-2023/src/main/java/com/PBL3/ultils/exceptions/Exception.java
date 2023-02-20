package com.PBL3.ultils.exceptions;

import com.PBL3.ultils.enums.ErrorCodes;
import com.PBL3.ultils.enums.ErrorStatusCodes;

public class Exception extends Throwable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String name;
	protected int errorCode;
	protected int statusCode;
	protected String message;


	public String getName() {
		return name;
	}


	public int getErrorCode() {
		return errorCode;
	}



	public int getStatusCode() {
		return statusCode;
	}



	public String getMessage() {
		return message;
	}

}
