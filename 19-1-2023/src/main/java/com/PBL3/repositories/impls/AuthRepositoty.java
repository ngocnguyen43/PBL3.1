package com.PBL3.repositories.impls;

import java.util.HashMap;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import com.PBL3.daos.IUserDAO;
import com.PBL3.models.User;
import com.PBL3.repositories.IAuthRepository;
import com.PBL3.ultils.exceptions.authExceptions.InvalidCredentialsException;
import com.PBL3.ultils.exceptions.authExceptions.RegistrationFailedException;
import com.PBL3.ultils.exceptions.dbExceptions.DuplicateEntryException;
import com.PBL3.ultils.helpers.DecryptPassword;
import com.PBL3.ultils.helpers.JWTGeneration;
import com.PBL3.ultils.helpers.RandomTokenGenearation;
import com.PBL3.ultils.response.Data;
import com.PBL3.ultils.response.Message;
import com.PBL3.ultils.response.Meta;

public class AuthRepositoty implements IAuthRepository {

	@Inject
	private IUserDAO userDao;

	@Override
	public Message registerUser(User domainUser) throws DuplicateEntryException, InvalidCredentialsException, Exception, RegistrationFailedException {
		boolean isEmailExist = userDao.findByEmail(domainUser.getEmail()) != null;
		if (isEmailExist)
			throw new DuplicateEntryException("Email is already used!");
		boolean isNationalIdExist = userDao.findByNationalId(domainUser.getNationalId()) != null;
		if (isNationalIdExist)
			throw new DuplicateEntryException("NationalId is already used");
	
		String id = userDao.save(domainUser);
		if (id == null) throw new RegistrationFailedException();
		Message message = loginUser(domainUser);
		return message;
	}

	@Override
	public Message loginUser(User domainUser) throws InvalidCredentialsException, Exception {
		HashMap<String, String> claims = new HashMap<>();
		JWTGeneration JWT = new JWTGeneration();
		boolean isExist = userDao.findByEmail(domainUser.getEmail()) == null;
		if (isExist)
			throw new NotFoundException("User not found!");
		User user = userDao.findByEmail(domainUser.getEmail());
		String hashedPassword = user.getPassword();
		String password = domainUser.getPassword();
		if (!DecryptPassword.Decrypt(password, hashedPassword))
			throw new InvalidCredentialsException("Wrong password");

		String userId = user.getUserId();
		claims.put("userId", userId);
		claims.put("role", user.getRole().getRoleCode());
		String accessToken = JWT.generate(claims);
		String refreshToken = RandomTokenGenearation.getRandomHexString(40);

		Data data = new Data.Builder(accessToken).withRefreshToken(refreshToken).build();
		Meta meta = new Meta.Builder(200).withMessage("Login Success!").build();
		Message message = new Message.Builder(meta).withData(data).build();

		return message;
	}

}
