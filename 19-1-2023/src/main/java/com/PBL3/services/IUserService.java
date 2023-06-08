package com.PBL3.services;

import com.PBL3.dtos.UserDTO;
import com.PBL3.dtos.pagination.UserPaginationDTO;
import com.PBL3.utils.exceptions.dbExceptions.*;
import com.PBL3.utils.response.Message;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.InvalidPropertiesFormatException;

public interface IUserService {
    Message findAll(String role) throws InvalidPropertiesException;

    Message findAll(UserPaginationDTO dto, String role) throws InvalidPropertiesException;

    Message findByUserId(String id);

    Message save(UserDTO dto) throws DuplicateEntryException, CreateFailedException;

    Message delete(String userId, String creator) throws InvalidPropertiesException, UnexpectedException, InvalidPropertiesFormatException, JsonProcessingException;

    Message findOne(String id);

    Message findAllMods();

    Message update(UserDTO dto, String id, String userId) throws DuplicateEntryException, UpdateFailedException, UnexpectedException, NotFoundException, InvalidPropertiesException;
}
