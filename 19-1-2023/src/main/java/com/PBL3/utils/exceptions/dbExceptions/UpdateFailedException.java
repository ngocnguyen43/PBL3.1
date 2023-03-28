package com.PBL3.utils.exceptions.dbExceptions;

import com.PBL3.utils.enums.ErrorCodes;
import com.PBL3.utils.enums.ErrorStatusCodes;
import com.PBL3.utils.exceptions.Exception;

public class UpdateFailedException extends Exception {
    public UpdateFailedException(String message) {
        this.message = message;
        this.errorCode = ErrorCodes.UpdateFailedException.getValue();
        this.statusCode = ErrorStatusCodes.UpdateFailedException.getValue();
    }
}
