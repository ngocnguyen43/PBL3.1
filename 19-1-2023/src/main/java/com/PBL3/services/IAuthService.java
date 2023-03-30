package com.PBL3.services;

import com.PBL3.dtos.UserDTO;
import com.PBL3.utils.exceptions.authExceptions.InvalidCredentialsException;
import com.PBL3.utils.exceptions.dbExceptions.CreateFailedException;
import com.PBL3.utils.exceptions.dbExceptions.DuplicateEntryException;
import com.PBL3.utils.exceptions.dbExceptions.NotFoundException;
import com.PBL3.utils.exceptions.dbExceptions.UnexpectedException;
import com.PBL3.utils.response.Message;

public interface IAuthService {
    Message Login(String email, String password) throws UnexpectedException, NotFoundException, InvalidCredentialsException;

    Message Register(UserDTO user, String type) throws DuplicateEntryException, CreateFailedException, UnexpectedException, NotFoundException, InvalidCredentialsException;

    Message InspectorRegister(UserDTO dto) throws DuplicateEntryException, CreateFailedException;
}
