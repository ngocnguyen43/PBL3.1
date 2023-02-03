package com.PBL3.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.PBL3.DAO.IUserDAO;
import com.PBL3.model.User;
import com.PBL3.service.IUserService;

public class UserService implements IUserService {

	@Inject
	private IUserDAO userDao;

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userDao.findAll();
	}

	@Override
	public List<User> findByUserId(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String save(User user) {
		// TODO Auto-generated method stub
		return userDao.save(user);
	}

//	@Override
//	public List<Role> findAll() {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
