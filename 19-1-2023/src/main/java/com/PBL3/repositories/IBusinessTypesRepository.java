package com.PBL3.repositories;

import com.PBL3.models.BusinessTypes;
import com.PBL3.ultils.exceptions.dbExceptions.CreateFailedException;
import com.PBL3.ultils.exceptions.dbExceptions.DuplicateEntryException;
import com.PBL3.ultils.exceptions.dbExceptions.NotFoundException;
import com.PBL3.ultils.response.Message;

public interface IBusinessTypesRepository {
	Message createBusinessType(BusinessTypes businessTypes) throws DuplicateEntryException, CreateFailedException, NotFoundException;
}
