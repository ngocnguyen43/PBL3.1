package com.PBL3.utils.enums;

public enum ErrorCodes {
    InvalidCredentialsException(1), TokenMissingException(2), TokenVerificationException(2), TokenExpiredException(3),
    UnauthorizedException(4), OTPGenerationException(5), OTPExpiredException(6), OTPVerificationException(7),
    ForeignKeyViolationException(8), DuplicateEntryException(9), CreateFailedException(10), UpdateFailedException(11),
    RegistrationFailedException(12), NotFoundException(13), InternalServerException(14), InvalidPropertiesException(15),
    InvalidEndpointException(16), UnexpectedException(17), UnimplementedException(18), HealthCheckFailedException(19);

    private final Integer value;

    ErrorCodes(Integer value) {
        // TODO Auto-generated constructor stub
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
