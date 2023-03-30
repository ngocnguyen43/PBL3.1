package com.PBL3.utils.exceptions.dbExceptions;

import com.PBL3.utils.enums.ErrorCodes;
import com.PBL3.utils.enums.ErrorStatusCodes;
import com.PBL3.utils.exceptions.Exception;

public class UnexpectedException extends Exception {
    public UnexpectedException() {
        this.message = "Somethings Went Wrongs";
        this.statusCode = ErrorStatusCodes.UnexpectedException.getValue();
        this.errorCode = ErrorCodes.UnexpectedException.getValue();
    }
}
