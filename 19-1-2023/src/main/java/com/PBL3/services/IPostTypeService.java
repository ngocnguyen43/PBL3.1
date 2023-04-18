package com.PBL3.services;

import com.PBL3.dtos.PostTypeDTO;
import com.PBL3.utils.exceptions.dbExceptions.DuplicateEntryException;
import com.PBL3.utils.exceptions.dbExceptions.UnexpectedException;
import com.PBL3.utils.response.Message;

public interface IPostTypeService {
    Message Create(PostTypeDTO postTypeDTO) throws DuplicateEntryException, UnexpectedException;
}
