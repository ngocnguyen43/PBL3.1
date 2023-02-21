package com.PBL3.repositories;

import com.PBL3.models.Business;
import com.PBL3.ultils.exceptions.dbExceptions.CreateFailedException;
import com.PBL3.ultils.exceptions.dbExceptions.DuplicateEntryException;
import com.PBL3.ultils.response.Message;

public interface IBusinessRepository {
	Message createBusines(Business business) throws DuplicateEntryException, CreateFailedException;
}
