package com.PBL3.services;

import com.PBL3.models.User;
import com.PBL3.utils.exceptions.dbExceptions.InvalidPropertiesException;
import com.PBL3.utils.response.Message;

import javax.ws.rs.NotFoundException;

public interface IUserService {
    Message findAll(String role) throws NotFoundException, InvalidPropertiesException;

    Message findByUserId(String id);

    User save(User user);

    void delete(String userId);
}
