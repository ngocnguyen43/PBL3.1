package com.PBL3.utils.exceptions.dbExceptions;

import com.PBL3.utils.enums.ErrorCodes;
import com.PBL3.utils.enums.ErrorStatusCodes;

public class NotFoundException extends com.PBL3.utils.exceptions.Exception {

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
