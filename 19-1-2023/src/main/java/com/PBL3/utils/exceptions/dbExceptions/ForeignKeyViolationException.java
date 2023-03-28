package com.PBL3.utils.exceptions.dbExceptions;

import com.PBL3.utils.enums.ErrorCodes;
import com.PBL3.utils.enums.ErrorStatusCodes;
import com.PBL3.utils.exceptions.Exception;

public class ForeignKeyViolationException extends Exception {
    public ForeignKeyViolationException(String message) {
        this.message = message;
        this.statusCode = ErrorStatusCodes.ForeignKeyViolationException.getValue();
        this.errorCode = ErrorCodes.ForeignKeyViolationException.getValue();
    }
}
