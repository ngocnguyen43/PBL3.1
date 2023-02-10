package com.PBL3.service.impl;

import java.util.HashMap;

import javax.inject.Inject;

import com.PBL3.DAO.impl.UserDAO;
import com.PBL3.controller.SigninMessage;
import com.PBL3.helpers.DecryptPassword;
import com.PBL3.helpers.JWTGenerator;
import com.PBL3.helpers.RandomToken;
import com.PBL3.model.User;
import com.PBL3.model.UserSignin;
import com.PBL3.service.ISigninService;

public class SigninService implements ISigninService {


	@Inject
	private UserDAO userDao;
	@Override
	public SigninMessage Signin(UserSignin tempUser) {

		SigninMessage signinMessage = null;
		String userId = null;
		Integer statusCode = null;
		String message = null;
		String accessToken = null;
		String refreshToken = null;
		Boolean isAdmin = null;
		HashMap<String, String> claims = new HashMap<>();
		try {
			JWTGenerator generator = new JWTGenerator();
			User user = userDao.findByEmail(tempUser.getEmail());
			if (user != null) {
				String hashedPassword = user.getPassword();
				String password = tempUser.getPassword();
				if (DecryptPassword.Decrypt(password, hashedPassword)) {
					userId =  user.getUserId();
					claims.put("userId", userId);
					statusCode = 200;
					message = "Login Succeed!";
					isAdmin = user.getRole().getRoleCode().equals("ADM") ? true : false;
					accessToken = generator.generatorJWT(claims);
					refreshToken = RandomToken.getRandomHexString(40);
					
				}else {
					statusCode = 401;
					message = "Login Failed!" ;
				}
			}else {
				statusCode = 404;
				message = "User Not Found!";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		signinMessage = new SigninMessage(statusCode,userId,message,accessToken,refreshToken,isAdmin);
		return signinMessage;
	}

}
