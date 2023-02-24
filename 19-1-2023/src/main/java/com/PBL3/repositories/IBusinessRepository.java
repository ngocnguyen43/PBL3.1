package com.PBL3.repositories;

import com.PBL3.models.Business;
import com.PBL3.utils.exceptions.dbExceptions.CreateFailedException;
import com.PBL3.utils.exceptions.dbExceptions.DuplicateEntryException;

public interface IBusinessRepository {
	void createBusines(Business business) throws DuplicateEntryException, CreateFailedException;
}
