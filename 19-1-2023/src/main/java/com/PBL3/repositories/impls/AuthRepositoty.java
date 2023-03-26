package com.PBL3.repositories.impls;

import java.util.HashMap;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import com.PBL3.daos.IUserDAO;
import com.PBL3.models.User;
import com.PBL3.repositories.IAuthRepository;
import com.PBL3.utils.exceptions.authExceptions.InvalidCredentialsException;
import com.PBL3.utils.exceptions.authExceptions.RegistrationFailedException;
import com.PBL3.utils.exceptions.dbExceptions.DuplicateEntryException;
import com.PBL3.utils.exceptions.dbExceptions.NotFoundException;
import com.PBL3.utils.helpers.DecryptPassword;
import com.PBL3.utils.helpers.JWTGeneration;
import com.PBL3.utils.helpers.RandomTokenGenearation;
import com.PBL3.utils.response.Data;
import com.PBL3.utils.response.Message;
import com.PBL3.utils.response.Meta;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AuthRepositoty implements IAuthRepository {

	@Inject
	private IUserDAO userDao;

	@Override
	public Message registerUser(User domainUser) throws DuplicateEntryException, InvalidCredentialsException, Exception, RegistrationFailedException, NotFoundException {
		User isEmailExist = userDao.findByEmail(domainUser.getEmail()) ;
		if (isEmailExist != null)
			throw new DuplicateEntryException("Email is already used!");
		User isNationalIdExist = userDao.findByNationalId(domainUser.getNationalId()) ;
		if (isNationalIdExist != null)
			throw new DuplicateEntryException("NationalId is already used");
	
		String id = userDao.save(domainUser);
//		Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage("Login Success!").build();
//		return  new Message.Builder(meta).build();
		if (id == null) throw new RegistrationFailedException();
		try {
			return loginUser(domainUser);
		} catch (NotFoundException e) {
			throw new NotFoundException("User not found!");
		}
	}

	@Override
	public Message loginUser(User domainUser) throws InvalidCredentialsException, Exception, NotFoundException {
		HashMap<String, String> claims = new HashMap<>();
		JWTGeneration JWT = new JWTGeneration();
		User isExist = userDao.findByEmail(domainUser.getEmail()) ;
//		System.out.println(new ObjectMapper().writeValueAsString(isExist));
		if (isExist == null	)
			throw new NotFoundException("User not found!");
		User user = userDao.findByEmail(domainUser.getEmail());
		String hashedPassword = user.getPassword();
		String password = domainUser.getPassword();
		if (!DecryptPassword.Decrypt(password, hashedPassword))
			throw new InvalidCredentialsException("Wrong password");

		String userId = user.getId();
		claims.put("userId", userId);
		claims.put("role", user.getRole().getRoleCode());
		String accessToken = JWT.generate(claims);
		String refreshToken = RandomTokenGenearation.getRandomHexString(40);

		Data data = new Data.Builder(accessToken).withRefreshToken(refreshToken).build();
		Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage("Login Success!").build();
		return  new Message.Builder(meta).withData(data).build();

	}

}
