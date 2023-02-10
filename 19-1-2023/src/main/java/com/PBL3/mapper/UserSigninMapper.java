package com.PBL3.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.PBL3.model.UserSignin;

public class UserSigninMapper implements IMapper<UserSignin> {

	@Override
	public UserSignin mapRow(ResultSet result) {
		UserSignin user = new UserSignin();
		try {
			user.setEmail(result.getString("email"));
			user.setPassword(result.getString("password"));
			return user;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		return null;
	}

}
