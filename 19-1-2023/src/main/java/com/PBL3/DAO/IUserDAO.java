package com.PBL3.DAO;

import java.util.List;

import com.PBL3.model.User;

public interface IUserDAO extends GenericDAO<User> {
	List<User> findAll();

	User findByUserId(String id);
	
	User findByEmail(String email);
	
	String save(User user);
	
	void delete(String USerId);

}
