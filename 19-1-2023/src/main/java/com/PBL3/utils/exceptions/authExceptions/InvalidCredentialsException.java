package com.PBL3.utils.exceptions.authExceptions;

import com.PBL3.utils.enums.ErrorCodes;
import com.PBL3.utils.enums.ErrorStatusCodes;

public class InvalidCredentialsException extends com.PBL3.utils.exceptions.Exception {

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
