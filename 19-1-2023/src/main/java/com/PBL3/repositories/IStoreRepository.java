package com.PBL3.repositories;

import com.PBL3.models.Store;
import com.PBL3.ultils.exceptions.dbExceptions.CreateFailedException;
import com.PBL3.ultils.response.Message;

public interface IStoreRepository {
	Message createStore(Store store) throws CreateFailedException;
}
