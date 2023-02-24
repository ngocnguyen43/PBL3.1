package com.PBL3.repositories;

import com.PBL3.models.User;
import com.PBL3.utils.exceptions.authExceptions.InvalidCredentialsException;
import com.PBL3.utils.exceptions.authExceptions.RegistrationFailedException;
import com.PBL3.utils.exceptions.dbExceptions.DuplicateEntryException;
import com.PBL3.utils.response.Message;

public interface IAuthRepository {
	Message registerUser(User user) throws DuplicateEntryException, InvalidCredentialsException, Exception, RegistrationFailedException;

	Message loginUser(User user) throws InvalidCredentialsException, Exception;
}
