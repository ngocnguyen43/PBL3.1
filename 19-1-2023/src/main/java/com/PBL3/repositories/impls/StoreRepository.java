package com.PBL3.repositories.impls;

import java.util.List;

import javax.inject.Inject;

import com.PBL3.daos.IStoreDAO;
import com.PBL3.models.Store;
import com.PBL3.repositories.IStoreRepository;
import com.PBL3.ultils.exceptions.dbExceptions.CreateFailedException;
import com.PBL3.ultils.exceptions.dbExceptions.NotFoundException;
import com.PBL3.ultils.response.Data;
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

	@Override
	public Message getStoreByNumber(String number) throws NotFoundException {
		Store store = storeDao.findByStoreNumber(number);
		if (store == null) throw new NotFoundException("Store not found");
		Meta meta = new Meta.Builder(200).withMessage("Success").build();
		Data data = new Data.Builder(null).withResults(store).build();
		Message message = new Message.Builder(meta).withData(data).build();
		return message;
	}

	@Override
	public Message getStoreById(String id) throws NotFoundException {
		// TODO Auto-generated method stub
		Store store = storeDao.findByStoreId(id);
		if (store == null) throw new NotFoundException("Store not found");
		Meta meta = new Meta.Builder(200).withMessage("Success").build();
		Data data = new Data.Builder(null).withResults(store).build();
		Message message = new Message.Builder(meta).withData(data).build();
		return message;	
		}

	@Override
	public Message getAllStoresAdmin() throws NotFoundException {
		// TODO Auto-generated method stub
		List<Store> stores = storeDao.findAll();
		if(stores == null) throw new NotFoundException("Stores not found");
		Meta meta = new Meta.Builder(200).withMessage("Success").build();
		Data data = new Data.Builder(null).withResults(stores).build();
		Message message = new Message.Builder(meta).withData(data).build();
		return message;	
	}

}
