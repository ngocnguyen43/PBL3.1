package com.PBL3.utils.exceptions.dbExceptions;

import com.PBL3.utils.enums.ErrorCodes;
import com.PBL3.utils.enums.ErrorStatusCodes;
import com.PBL3.utils.exceptions.Exception;

public class CreateFailedException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public CreateFailedException(String message) {
        this.message = message;
        this.errorCode = ErrorCodes.CreateFailedException.getValue();
        this.statusCode = ErrorStatusCodes.CreateFailedException.getValue();

    }
}
