package com.PBL3.utils.exceptions.dbExceptions;

import com.PBL3.utils.enums.ErrorCodes;
import com.PBL3.utils.enums.ErrorStatusCodes;
import com.PBL3.utils.exceptions.Exception;

public class DuplicateEntryException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public DuplicateEntryException(String message) {
        this.message = message;
        this.errorCode = ErrorCodes.DuplicateEntryException.getValue();
        this.statusCode = ErrorStatusCodes.DuplicateEntryException.getValue();
    }
}
