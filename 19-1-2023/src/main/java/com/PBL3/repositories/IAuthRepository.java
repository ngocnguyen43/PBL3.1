package com.PBL3.repositories;

import com.PBL3.models.User;
import com.PBL3.ultils.exceptions.authExceptions.InvalidCredentialsException;
import com.PBL3.ultils.exceptions.authExceptions.RegistrationFailedException;
import com.PBL3.ultils.exceptions.dbExceptions.DuplicateEntryException;
import com.PBL3.ultils.response.Message;

public interface IAuthRepository {
	Message registerUser(User user) throws DuplicateEntryException, InvalidCredentialsException, Exception, RegistrationFailedException;

	Message loginUser(User user) throws InvalidCredentialsException, Exception;
}
