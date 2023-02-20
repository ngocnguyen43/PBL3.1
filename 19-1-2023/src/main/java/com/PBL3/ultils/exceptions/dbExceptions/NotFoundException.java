package com.PBL3.ultils.exceptions.dbExceptions;

import com.PBL3.ultils.enums.ErrorCodes;
import com.PBL3.ultils.enums.ErrorStatusCodes;

public class NotFoundException extends com.PBL3.ultils.exceptions.Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NotFoundException(String message) {
		this.message = message;
		this.errorCode = ErrorCodes.NotFoundException.getValue();
		this.statusCode = ErrorStatusCodes.NotFoundException.getValue();
	}
}
