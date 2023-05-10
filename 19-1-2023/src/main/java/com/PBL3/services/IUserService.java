package com.PBL3.services;

import com.PBL3.dtos.UserDTO;
import com.PBL3.dtos.pagination.UserPaginationDTO;
import com.PBL3.utils.exceptions.dbExceptions.*;
import com.PBL3.utils.response.Message;

public interface IUserService {
    Message findAll(String role) throws InvalidPropertiesException;

    Message findAll(UserPaginationDTO dto, String role);

    Message findByUserId(String id);

    Message save(UserDTO dto) throws DuplicateEntryException, CreateFailedException;

    Message delete(String userId);

    Message findOne(String id);

    Message findAllMods();

    Message update(UserDTO dto, String id) throws DuplicateEntryException, UpdateFailedException, UnexpectedException, NotFoundException;
}
