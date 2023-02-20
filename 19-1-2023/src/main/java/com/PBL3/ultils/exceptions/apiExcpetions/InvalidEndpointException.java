package com.PBL3.ultils.exceptions.apiExcpetions;

import com.PBL3.ultils.enums.ErrorCodes;
import com.PBL3.ultils.enums.ErrorStatusCodes;

public class InvalidEndpointException extends com.PBL3.ultils.exceptions.Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public InvalidEndpointException() {
		this.errorCode = ErrorCodes.InvalidEndpointException.getValue();
		this.statusCode = ErrorStatusCodes.InvalidEndpointException.getValue();
		this.message = "URI not found";
	}
}
