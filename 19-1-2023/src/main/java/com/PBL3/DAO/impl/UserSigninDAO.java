package com.PBL3.DAO.impl;

import java.util.List;

import com.PBL3.DAO.IUserSigninDAO;
import com.PBL3.mapper.UserSigninMapper;
import com.PBL3.model.UserSignin;

public class UserSigninDAO extends AbstractDAO<UserSignin> implements IUserSigninDAO {

	@Override
	public UserSignin findOne(String email) {
		String sql = "SELECT userId,email,password FROM users WHERE email = ?";
		
		List<UserSignin> users = query(sql, new UserSigninMapper(),email );
		return users.isEmpty() ? null : users.get(0);
	}

}
