package com.PBL3.services.impl;

import javax.inject.Inject;

import com.PBL3.dtos.StoreDTO;
import com.PBL3.models.Store;
import com.PBL3.repositories.IStoreRepository;
import com.PBL3.services.IStoreService;
import com.PBL3.ultils.exceptions.dbExceptions.CreateFailedException;
import com.PBL3.ultils.exceptions.dbExceptions.NotFoundException;
import com.PBL3.ultils.helpers.Helper;
import com.PBL3.ultils.response.Message;
import com.PBL3.ultils.response.Meta;

public class StoreService implements IStoreService {
	@Inject
	private IStoreRepository storeRepository;

	@Override
	public Message createStoreService(StoreDTO storeDto) {
		// TODO Auto-generated method stub
		Store store = Helper.objectMapper(storeDto, Store.class);
		try {
			Message message = storeRepository.createStore(store);
			return message;
		} catch (CreateFailedException e) {
			Meta meta = new Meta.Builder(e.getStatusCode()).withErrCode(e.getErrorCode()).withError(e.getMessage())
					.build();
			Message message = new Message.Builder(meta).build();
			return message;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Meta meta = new Meta.Builder(500).withError("DB Error").build();
			Message message = new Message.Builder(meta).build();
			return message;
		}
	}

	@Override
	public Message findStoreById(String id) {
		// TODO Auto-generated method stub

		try {
			Message message = storeRepository.getStoreById(id);
			return message;
		} catch (NotFoundException e) {
			Meta meta = new Meta.Builder(e.getStatusCode()).withErrCode(e.getErrorCode()).withError(e.getMessage())
					.build();
			Message message = new Message.Builder(meta).build();
			return message;
		} catch (Exception e) {
			Meta meta = new Meta.Builder(500).withError("Error store service find by number").build();
			Message message = new Message.Builder(meta).build();
			return message;
		}
	}

	@Override
	public Message findAllStoreAdmin() {
		// TODO Auto-generated method stub
		try {
			Message message = storeRepository.getAllStoresAdmin();
			return message;
		}catch(NotFoundException e) {
			Meta meta = new Meta.Builder(e.getStatusCode()).withErrCode(e.getErrorCode()).withError(e.getMessage())
					.build();
			Message message = new Message.Builder(meta).build();
			return message;
		} catch (Exception e) {
			// TODO: handle exception
			Meta meta = new Meta.Builder(500).withError("Error store service find all").build();
			Message message = new Message.Builder(meta).build();
			return message;
		}
	}

}
