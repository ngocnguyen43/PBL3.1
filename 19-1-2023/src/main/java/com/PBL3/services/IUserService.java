package com.PBL3.services;

import com.PBL3.models.User;
import com.PBL3.utils.response.Message;

import java.util.List;

public interface IUserService {
    List<User> findAll();

    Message findByUserId(String id);

    User save(User user);

    void delete(String userId);
}
