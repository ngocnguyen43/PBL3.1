package com.PBL3.service;

import java.util.List;

import com.PBL3.model.User;

public interface IUserService {
	List<User> findAll();
	List<User> findByUserId(String id);
	String save(User user);
}
