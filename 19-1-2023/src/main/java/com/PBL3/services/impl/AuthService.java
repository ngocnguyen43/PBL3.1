package com.PBL3.services.impl;

import javax.inject.Inject;

import com.PBL3.dtos.UserDTO;
import com.PBL3.dtos.UserSigninDTO;
import com.PBL3.models.User;
import com.PBL3.repositories.IAuthRepository;
import com.PBL3.services.IAuthService;
import com.PBL3.ultils.exceptions.authExceptions.InvalidCredentialsException;
import com.PBL3.ultils.exceptions.authExceptions.RegistrationFailedException;
import com.PBL3.ultils.exceptions.dbExceptions.DuplicateEntryException;
import com.PBL3.ultils.helpers.Helper;
import com.PBL3.ultils.response.Message;
import com.PBL3.ultils.response.Meta;

public class AuthService implements IAuthService {

	@Inject
	private IAuthRepository authRepository;

	@Override
	public Message Signin(UserSigninDTO tempUser) {
		try {
			User user = Helper.objectMapper(tempUser, User.class);
			Message message = authRepository.loginUser(user);
			return message;
		} catch (InvalidCredentialsException e) {
			Meta meta = new Meta.Builder(e.getStatusCode()).withError(e.getMessage()).build();
			Message message = new Message.Builder(meta).build();
			return message;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Meta meta = new Meta.Builder(500).withError("HAHAA").build();
			Message message = new Message.Builder(meta).build();
			return message;
		}
//		
	}

	@Override
	public Message Register(UserDTO userDTO) {

		try {
			User user = Helper.objectMapper(userDTO, User.class);
			Message message = authRepository.registerUser(user);
			return message;
		} catch (DuplicateEntryException | InvalidCredentialsException | RegistrationFailedException e) {
			Meta meta = new Meta.Builder(e.getStatusCode()).withErrCode(e.getErrorCode()).withError(e.getMessage())
					.build();
			Message message = new Message.Builder(meta).build();
			return message;
		} catch (Exception e) {
			e.printStackTrace();
			Meta meta = new Meta.Builder(500).withError("HAHAA").build();
			Message message = new Message.Builder(meta).build();
			return message;

		}

	}

}
