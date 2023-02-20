package com.PBL3.services.impl;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import com.PBL3.daos.IUserDAO;
import com.PBL3.dtos.UserDTO;
import com.PBL3.models.User;
import com.PBL3.services.IUserService;
import com.PBL3.ultils.helpers.HashPassword;
import com.PBL3.ultils.helpers.Helper;
import com.PBL3.ultils.response.Data;
import com.PBL3.ultils.response.Message;
import com.PBL3.ultils.response.Meta;
import com.auth0.jwt.interfaces.Header;

public class UserService implements IUserService {

	@Inject
	private IUserDAO userDao;

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userDao.findAll();
	}

	@Override
	public Message findByUserId(String id) {
		// TODO Auto-generated method stub
//		Message message = new Message();
//		User user = userDao.findByUserId(id);
//		if (user != null) {
//			Meta header = new Meta.Builder(HttpServletResponse.SC_OK).withErrCode(0).withMessage("User has found!").build();
//			UserDTO userDTO = Helper.objectMapper(user, UserDTO.class);
//			userDTO.setPassword(null);
//			userDTO.setRoleId(null);
//			Data body = new Data.Builder(null).withResults(userDTO).build();
//			
//			message.setBody(body);
//			message.setHeader(header);
//		}else {
//			Meta header = new Meta.Builder(HttpServletResponse.SC_NOT_FOUND).withErrCode(6).withMessage("User not found!").build();
//			message.setHeader(header);
//		}
		return null;
	}

	@Override
	public User save(User user) {
		// TODO Auto-generated method stub
		user.setPassword(HashPassword.HashPW(user.getPassword()));
		String userId = userDao.save(user);
		return userDao.findByUserId(userId);
	}

	@Override
	public void delete(String userId) {
		userDao.delete(userId);
		// TODO Auto-generated method stub
		
		
	}

//	@Override
//	public List<Role> findAll() {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
