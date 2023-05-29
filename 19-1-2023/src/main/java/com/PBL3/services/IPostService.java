package com.PBL3.services;

import com.PBL3.dtos.PostDTO;
import com.PBL3.dtos.pagination.PostPaginationDTO;
import com.PBL3.utils.exceptions.dbExceptions.InvalidPropertiesException;
import com.PBL3.utils.exceptions.dbExceptions.NotFoundException;
import com.PBL3.utils.exceptions.dbExceptions.UnexpectedException;
import com.PBL3.utils.response.Message;

public interface IPostService {
    Message Create(PostDTO postDTO) throws UnexpectedException, InvalidPropertiesException;

    Message GetAll(PostPaginationDTO dto) throws UnexpectedException;

    Message InactivePost(String id) throws NotFoundException;
}
