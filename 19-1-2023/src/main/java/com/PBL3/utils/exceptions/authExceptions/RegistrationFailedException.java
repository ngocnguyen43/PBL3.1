package com.PBL3.utils.exceptions.authExceptions;

import com.PBL3.utils.enums.ErrorCodes;
import com.PBL3.utils.enums.ErrorStatusCodes;
import com.PBL3.utils.exceptions.Exception;

public class RegistrationFailedException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public RegistrationFailedException() {
        this.message = "User failed to be registered";
        this.errorCode = ErrorCodes.RegistrationFailedException.getValue();
        this.statusCode = ErrorStatusCodes.RegistrationFailedException.getValue();
    }

}
