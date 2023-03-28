package com.PBL3.services.impl;

import com.PBL3.dtos.UserDTO;
import com.PBL3.dtos.UserSigninDTO;
import com.PBL3.models.User;
import com.PBL3.repositories.IAuthRepository;
import com.PBL3.services.IAuthService;
import com.PBL3.utils.exceptions.authExceptions.InvalidCredentialsException;
import com.PBL3.utils.exceptions.authExceptions.RegistrationFailedException;
import com.PBL3.utils.exceptions.dbExceptions.DuplicateEntryException;
import com.PBL3.utils.exceptions.dbExceptions.NotFoundException;
import com.PBL3.utils.helpers.Helper;
import com.PBL3.utils.helpers.IDGeneration;
import com.PBL3.utils.response.Message;
import com.PBL3.utils.response.Meta;

import javax.inject.Inject;

public class AuthService implements IAuthService {

	@Inject
	private IAuthRepository authRepository;

	@Override
	public Message Signin(UserSigninDTO tempUser) {
		try {
			User user = Helper.objectMapper(tempUser, User.class);
			return authRepository.loginUser(user);
		} catch (InvalidCredentialsException| NotFoundException e) {
			Meta meta = new Meta.Builder(e.getStatusCode()).withError(e.getMessage()).build();
			return new Message.Builder(meta).build();

		} catch (Exception e) {
			e.printStackTrace();
			Meta meta = new Meta.Builder(500).withError("Login Error").build();
			return new Message.Builder(meta).build();
		}
//		
	}

	@Override
	public Message Register(UserDTO userDTO ,String type) {

		try {
			User user = Helper.objectMapper(userDTO, User.class);
			if (type != null && type.equals("company")){
				String id = IDGeneration.generate();
				String taxtId = IDGeneration.generate(10);
				user.setCompanyId(id);
				user.setTaxIndentity(taxtId);
			}
			return authRepository.registerUser(user);

		} catch (DuplicateEntryException | InvalidCredentialsException | RegistrationFailedException|NotFoundException e) {
			Meta meta = new Meta.Builder(e.getStatusCode()).withErrCode(e.getErrorCode()).withError(e.getMessage())
					.build();
			return new Message.Builder(meta).build();

		} catch (Exception e) {
			e.printStackTrace();
			Meta meta = new Meta.Builder(500).withError("Create New User Failed").build();
			return new Message.Builder(meta).build();
		}

	}

}
