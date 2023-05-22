package com.PBL3.services;

import com.PBL3.dtos.BusinessDTO;
import com.PBL3.utils.exceptions.dbExceptions.CreateFailedException;
import com.PBL3.utils.exceptions.dbExceptions.DuplicateEntryException;
import com.PBL3.utils.exceptions.dbExceptions.UnexpectedException;
import com.PBL3.utils.response.Message;

public interface IBusinessService {
    Message createBusiness(BusinessDTO businessDTO, String id) throws CreateFailedException, DuplicateEntryException;

    Message getAllBusiness() throws UnexpectedException;
}
