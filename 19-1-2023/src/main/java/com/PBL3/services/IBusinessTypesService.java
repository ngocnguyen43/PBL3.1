package com.PBL3.services;

import com.PBL3.dtos.BusinessTypesDTO;
import com.PBL3.utils.exceptions.dbExceptions.CreateFailedException;
import com.PBL3.utils.exceptions.dbExceptions.DuplicateEntryException;
import com.PBL3.utils.exceptions.dbExceptions.NotFoundException;
import com.PBL3.utils.response.Message;

public interface IBusinessTypesService {
    Message createBusinessType(BusinessTypesDTO businessTypesDTO) throws DuplicateEntryException, NotFoundException, CreateFailedException;
}
