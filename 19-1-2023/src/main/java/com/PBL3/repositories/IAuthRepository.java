package com.PBL3.repositories;

import com.PBL3.models.User;
import com.PBL3.utils.exceptions.authExceptions.InvalidCredentialsException;
import com.PBL3.utils.exceptions.authExceptions.RegistrationFailedException;
import com.PBL3.utils.exceptions.dbExceptions.DuplicateEntryException;
import com.PBL3.utils.exceptions.dbExceptions.NotFoundException;
import com.PBL3.utils.response.Message;

public interface IAuthRepository {
    Message registerUser(User user) throws Exception;

    Message loginUser(User user) throws Exception;
}
