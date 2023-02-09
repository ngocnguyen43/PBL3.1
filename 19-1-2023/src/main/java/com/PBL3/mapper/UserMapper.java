package com.PBL3.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.PBL3.model.User;

public class UserMapper implements IMapper<User> {

	@Override
	public User mapRow(ResultSet result) {
		User user = new User();
		try {
			user.setUserId(result.getString("userId"));
			user.setRoleId(result.getInt("roleId"));
			user.setFirstName(result.getString("firstname"));
			user.setLastName(result.getString("lastname"));
			user.setEmail(result.getString("email"));
			user.setNationalId(result.getString("nationalid"));
			user.setGender(result.getInt("genderId"));
			user.setPhoneNumber(result.getString("phonenumber"));
			user.setUserName(result.getString("username"));
			user.setPassword(result.getString("password"));
			user.setAction(result.getInt("action"));
			user.setModifiedBy(result.getString("modifiedBy"));
			user.setCreatedAt(result.getTimestamp("createdAt"));
			user.setUpdatedAt(result.getTimestamp("updatedAt"));

			return user;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		return null;

	}

}
