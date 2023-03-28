package com.PBL3.utils.enums;

public enum ErrorStatusCodes {

    InvalidPropertiesException(400), NotFoundException(404), DuplicateEntryException(409), TokenMissingException(401),
    InternalServerException(500), UnauthorizedException(401), UnexpectedException(404), CreateFailedException(500),
    UpdateFailedException(500), InvalidCredentialsException(401), RegistrationFailedException(500),
    InvalidEndpointException(404), TokenVerificationException(401), OTPGenerationException(401),
    OTPExpiredException(401), OTPVerificationException(401), ForeignKeyViolationException(512),
    UnimplementedException(404), HealthCheckFailedException(503),

    ;
    private final Integer value;

    ErrorStatusCodes(Integer value) {
        // TODO Auto-generated constructor stub
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
