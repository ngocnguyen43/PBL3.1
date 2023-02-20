package com.PBL3.ultils.exceptions.authExceptions;

import com.PBL3.ultils.enums.ErrorCodes;
import com.PBL3.ultils.enums.ErrorStatusCodes;

public class UnauthorizedException extends com.PBL3.ultils.exceptions.Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnauthorizedException() {
		this.message = "User unauthorized for action";
		this.errorCode = ErrorCodes.UnauthorizedException.getValue();
		this.statusCode = ErrorStatusCodes.UnauthorizedException.getValue();
	}
}
