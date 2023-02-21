package com.PBL3.daos.impl;

import java.util.List;

import com.PBL3.daos.IUserDAO;
import com.PBL3.models.User;
import com.PBL3.ultils.helpers.HashPassword;
import com.PBL3.ultils.helpers.IDGeneration;
import com.PBL3.ultils.mapper.UserMapper;

public class UserDAO extends AbstractDAO<User> implements IUserDAO {

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM users INNER JOIN roles ON users.roleId = roles.roleId";

//		System.out.println(query(sql, new UserMapper()));
		return query(sql, new UserMapper());
	}

	@Override
	public User findByUserId(String id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM users  INNER JOIN roles ON users.roleId = roles.roleId WHERE userId = ?";
		List<User> users = query(sql, new UserMapper(), id);

		return users.isEmpty() ? null : users.get(0);
	}

	@Override
	public String save(User user) {
		// TODO Auto-generated method stub
		String password = HashPassword.HashPW(user.getPassword());

		String sql = "INSERT INTO users (user_id," 
				+ "role_id," 
				+"company_name,"
				+ "tax_indentification_number,"
				+ "business_id,"
				+ "phone_number,"
				+ "fax_number"
				+ "email,"
				+ "full_name," 
				+ "national_id," 
				+ "user_number," 
				+  "password," 
				+ "action," 
				+ "modifiedBy)" + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		// helper
		String id = IDGeneration.generate();
		// helper hashed password
//		String taxId = user.getTaxIndentity() != null ? user.getTaxIndentity() : null;
		insert(sql, id, user.getRoleId(), user.getCompanyName(), user.getTaxIndentity(), user.getBusinessId(),
				user.getPhoneNumber(), user.getFaxNumber(),user.getEmail(),user.getFullName(),user.getNationalId()
				,user.getUserNumber(), password, user.getAction(), user.getModifiedBy());
		return id;

	}

	public void delete(String userId) {
		// TODO Auto-generated method stub
		User user = findByUserId(userId);

		int action = user.getAction() == 0 ? 1 : 0;
		String sql = "UPDATE users SET action = ? WHERE userId = ?";
		insert(sql, action, user.getId());

	}

	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM users INNER JOIN roles ON users.role_id = roles.role_id WHERE email = ? ";
//		String sql = "SELECT * FROM users INNER JOIN roles ON users.roleId = roles.roleId WHERE email = ? ";

		List<User> users = query(sql, new UserMapper(), email);

		return users.isEmpty() ? null : users.get(0);
	}

	@Override
	public User findByNationalId(String nationalId) {
		String sql = "SELECT * FROM users INNER JOIN roles ON users.role_id = roles.role_Id WHERE national_id = ?";
		// TODO Auto-generated method stub
		List<User> users = query(sql, new UserMapper(), nationalId);
		return users.isEmpty() ? null : users.get(0);
	}

}
