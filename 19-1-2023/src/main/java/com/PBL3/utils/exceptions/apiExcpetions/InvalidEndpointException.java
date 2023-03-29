package com.PBL3.utils.exceptions.apiExcpetions;

import com.PBL3.utils.enums.ErrorCodes;
import com.PBL3.utils.enums.ErrorStatusCodes;

public class InvalidEndpointException extends com.PBL3.utils.exceptions.Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public InvalidEndpointException() {
        this.errorCode = ErrorCodes.InvalidEndpointException.getValue();
        this.statusCode = ErrorStatusCodes.InvalidEndpointException.getValue();
        this.message = "URL Not Found";
    }
}
