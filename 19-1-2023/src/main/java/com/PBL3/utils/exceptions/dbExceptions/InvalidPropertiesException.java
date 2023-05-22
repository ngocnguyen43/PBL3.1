package com.PBL3.utils.exceptions.dbExceptions;

import com.PBL3.utils.enums.ErrorCodes;
import com.PBL3.utils.enums.ErrorStatusCodes;
import com.PBL3.utils.exceptions.Exception;

public class InvalidPropertiesException extends Exception {
    public InvalidPropertiesException(String message) {
        this.message = message;
        this.errorCode = ErrorCodes.InvalidPropertiesException.getValue();
        this.statusCode = ErrorStatusCodes.InvalidPropertiesException.getValue();
    }
}
