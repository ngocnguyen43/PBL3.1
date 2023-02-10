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
		String sql = "SELECT * FROM users ";
		
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
				+ "phonenumber," + "email,"  + "password," + "action," + "modifiedBy)"
				+ " VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		// helper
		UUID uuid = UUID.randomUUID();
		String userId = uuid.toString();
		// helper hashed password
		String firstname = user.getFirstName() != null ? user.getFirstName() : null;
		insert(sql, userId, user.getRoleId(), firstname, user.getLastName(), user.getNationalId(), user.getGender(),
				user.getPhoneNumber(), user.getEmail(),user.getPassword(), user.getAction(),
				user.getModifiedBy());
		return userId;

	}

	public void delete(String userId) {
		// TODO Auto-generated method stub
		User user = findByUserId(userId);

		int action = user.getAction() == 0 ? 1 : 0;
		String sql = "UPDATE users SET action = ? WHERE userId = ?";
		insert(sql, action, user.getUserId());

	}

	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM users WHERE email = ? ";
		List<User> users = query(sql, new UserMapper(), email);

		return users.isEmpty() ? null : users.get(0);
	}

}
