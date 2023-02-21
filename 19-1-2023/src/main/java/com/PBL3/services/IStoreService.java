package com.PBL3.services;

import com.PBL3.dtos.StoreDTO;
import com.PBL3.ultils.response.Message;

public interface IStoreService {
	Message createStoreService(StoreDTO store);
	Message findStoreById(String id);
	Message findAllStoreAdmin();
}
