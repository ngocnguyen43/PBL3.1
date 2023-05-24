package com.PBL3.services;

import com.PBL3.dtos.BusinessTypesDTO;
import com.PBL3.utils.exceptions.dbExceptions.CreateFailedException;
import com.PBL3.utils.exceptions.dbExceptions.DuplicateEntryException;
import com.PBL3.utils.exceptions.dbExceptions.NotFoundException;
import com.PBL3.utils.response.Message;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface IBusinessTypesService {
    Message createBusinessType(BusinessTypesDTO businessTypesDTO, String userId) throws DuplicateEntryException, NotFoundException, CreateFailedException, JsonProcessingException;

    Message GetAllBusinessTypes();
}
