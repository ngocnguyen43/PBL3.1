package com.PBL3.services;

import com.PBL3.dtos.ResetPasswordDTO;
import com.PBL3.dtos.UserDTO;
import com.PBL3.dtos.UserSigninDTO;
import com.PBL3.utils.exceptions.authExceptions.InvalidCredentialsException;
import com.PBL3.utils.exceptions.dbExceptions.CreateFailedException;
import com.PBL3.utils.exceptions.dbExceptions.DuplicateEntryException;
import com.PBL3.utils.exceptions.dbExceptions.NotFoundException;
import com.PBL3.utils.exceptions.dbExceptions.UnexpectedException;
import com.PBL3.utils.response.Message;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface IAuthService {
    Message Login(UserSigninDTO dto) throws UnexpectedException, NotFoundException, InvalidCredentialsException;

    Message Register(UserDTO user, String type) throws DuplicateEntryException, CreateFailedException, UnexpectedException, NotFoundException, InvalidCredentialsException;

    Message InspectorRegister(UserDTO dto, String userId) throws DuplicateEntryException, CreateFailedException;

    Message ResetPassword(ResetPasswordDTO dto) throws NotFoundException, InvalidCredentialsException, UnexpectedException, JsonProcessingException;

    Message ResetPassword(String userId) throws NotFoundException, UnexpectedException;
}
