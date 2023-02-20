package com.PBL3.daos;

import java.util.List;

import com.PBL3.models.User;

public interface IUserDAO extends GenericDAO<User> {
	List<User> findAll();

	User findByUserId(String id);
	
	User findByEmail(String email);
	
	User findByNationalId(String nationalId);
	
	String save(User user);
	
	void delete(String USerId);

}
