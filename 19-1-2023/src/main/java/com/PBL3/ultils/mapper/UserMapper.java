package com.PBL3.ultils.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.PBL3.models.Role;
import com.PBL3.models.User;

public class UserMapper implements IMapper<User> {

	@Override
	public User mapRow(ResultSet result) {
		User user = new User();
		try {
			user.setUserId(result.getString("userId"));
			user.setRoleId(result.getInt("roleId"));
			user.setFullName(result.getString("fullname"));
			user.setEmail(result.getString("email"));
			user.setNationalId(result.getString("nationalid"));
			user.setGender(result.getInt("genderId"));
			user.setPhoneNumber(result.getString("phonenumber"));
			user.setPassword(result.getString("password"));
			user.setAction(result.getInt("action"));
			user.setModifiedBy(result.getString("modifiedBy"));
			user.setCreatedAt(result.getTimestamp("createdAt"));
			user.setUpdatedAt(result.getTimestamp("updatedAt"));
			Role role = new Role();
			role.setRoleId(result.getInt("roleId"));
			role.setRoleCode(result.getString("rolecode"));
			role.setRoleName(result.getString("rolename"));
			user.setRole(role);


			return user;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		return null;

	}

}
