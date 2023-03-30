package com.PBL3.services;

import com.PBL3.dtos.UserDTO;
import com.PBL3.models.User;
import com.PBL3.utils.exceptions.dbExceptions.CreateFailedException;
import com.PBL3.utils.exceptions.dbExceptions.DuplicateEntryException;
import com.PBL3.utils.exceptions.dbExceptions.InvalidPropertiesException;
import com.PBL3.utils.response.Message;

import javax.ws.rs.NotFoundException;

public interface IUserService {
    Message findAll(String role) throws NotFoundException, InvalidPropertiesException;

    Message findByUserId(String id);

    Message save(UserDTO dto) throws DuplicateEntryException, CreateFailedException;

    void delete(String userId);
}
