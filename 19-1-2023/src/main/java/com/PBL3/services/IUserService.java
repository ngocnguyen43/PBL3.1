package com.PBL3.services;

import java.util.List;

import com.PBL3.models.User;
import com.PBL3.ultils.response.Message;

public interface IUserService {
	List<User> findAll();
	Message findByUserId(String id);
	User save(User user);
	void delete(String userId);
}
