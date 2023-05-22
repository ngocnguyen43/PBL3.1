package com.PBL3.utils.response;

public interface Response {
    String OK = "OK";
    String CREATED = "Created";

    String CREATE_FAILED = "Created Failed";
    String LOGIN_SUCCESS = "Login Successfully";
    String INVALID_EMAIL_OR_PASSWORD = "Invalid email or password";
    String USER_NOT_FOUND = "User Not Found";
    String WRONG_PASSWORD = "Wrong Password";
    String EMAIL_IN_USE = "Email is already in use";

    String NATIONAL_ID_IN_USE = "National id is already in use";

    String REGISTERED_FAILED = "Registered Failed";

    String BUSINESS_EXISTED = "Business's name already existed";
    String BUSINESS_NOT_FOUND = "Business Not Found";
    String BUSINESS_TYPE_EXISTED = "Business type existed";

    String CERTIFICATE_ID_INVALID = "Certificate id invalid";

    String SUCCESS = "Success";
    String FAILED = "Failed";
    String NOT_FOUND = "Not Found";

    String DUPLICATED = "Duplicated";
    String INVALID_ID = "Invalid id";
}
