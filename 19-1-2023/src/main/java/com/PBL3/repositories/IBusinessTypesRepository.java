package com.PBL3.repositories;

import com.PBL3.models.BusinessTypes;
import com.PBL3.utils.exceptions.dbExceptions.CreateFailedException;
import com.PBL3.utils.exceptions.dbExceptions.DuplicateEntryException;
import com.PBL3.utils.exceptions.dbExceptions.NotFoundException;
import com.PBL3.utils.response.Message;

public interface IBusinessTypesRepository {
	void createBusinessType(BusinessTypes businessTypes) throws DuplicateEntryException, CreateFailedException, NotFoundException;
}
