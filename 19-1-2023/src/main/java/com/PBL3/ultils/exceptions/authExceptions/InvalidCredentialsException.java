package com.PBL3.ultils.exceptions.authExceptions;

import com.PBL3.ultils.enums.ErrorCodes;
import com.PBL3.ultils.enums.ErrorStatusCodes;

public class InvalidCredentialsException extends com.PBL3.ultils.exceptions.Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;

	public InvalidCredentialsException(String message) {
		this.message = message;
		this.errorCode = ErrorCodes.InvalidCredentialsException.getValue();
		this.statusCode = ErrorStatusCodes.InvalidCredentialsException.getValue();
	}
}
