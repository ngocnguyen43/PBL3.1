package com.PBL3.DAO.impl;

import java.util.List;
import java.util.UUID;
import com.PBL3.DAO.IUserDAO;
import com.PBL3.mapper.UserMapper;
import com.PBL3.model.User;

public class UserDAO extends AbstractDAO<User> implements IUserDAO {

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM users";
//		System.out.println(query(sql, new UserMapper()));
		return query(sql, new UserMapper());
	}

	@Override
	public User findByUserId(String id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM users WHERE userId = ? ";
		List<User> users = query(sql, new UserMapper(), id);
		
		return users.isEmpty() ? null : users.get(0);
	}

	@Override
	public String save(User user) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO users (userId," + "roleId," + "firstname," + "lastname," + "nationalId," + "genderId,"
				+ "phonenumber," + "email," + "username," + "password," + "action," + "modifiedBy)"
				+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
		//helper
		UUID uuid = UUID.randomUUID();
		String userId = uuid.toString();
		//helper hashed password
		
		insert(sql, userId, user.getRoleId(), user.getFirstName(), user.getLastName(), user.getNationalId(),
				user.getGender(), user.getPhoneNumber(), user.getEmail(), user.getUserName(), user.getPassword(),
				user.getAction(), user.getModifiedBy());
		return userId;

	}

	@Override
	public void update(String sql, Object... params) {
		// TODO Auto-generated method stub

	}

}
