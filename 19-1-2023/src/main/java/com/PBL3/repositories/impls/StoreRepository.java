package com.PBL3.repositories.impls;

import javax.inject.Inject;

import com.PBL3.daos.IStoreDAO;
import com.PBL3.models.Store;
import com.PBL3.repositories.IStoreRepository;
import com.PBL3.ultils.exceptions.dbExceptions.CreateFailedException;
import com.PBL3.ultils.response.Message;
import com.PBL3.ultils.response.Meta;

public class StoreRepository implements IStoreRepository {
	@Inject
	private IStoreDAO storeDao;

	@Override
	public Message createStore(Store store) throws CreateFailedException {
		String id = storeDao.save(store);
		if (id == null)
			throw new CreateFailedException("Create Store Failed");
		Meta meta = new Meta.Builder(200).withMessage("Create Success!").build();
		Message message = new Message.Builder(meta).build();
		return message;
	}

}
