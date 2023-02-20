package com.PBL3.daos.impl;

import java.util.List;
import java.util.UUID;

import com.PBL3.daos.IStoreDAO;
import com.PBL3.models.Store;
import com.PBL3.ultils.mapper.StoreMapper;

public class StoreDAO extends AbstractDAO<Store> implements IStoreDAO {

	@Override
	public List<Store> findAll() {
		String sql = "SELECT * FROM stores WHERE action = 1";
		return query(sql, new StoreMapper());
	}

	@Override
	public Store findByStoreId(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String save(Store store) {
		// TODO Auto-generated method stub
		UUID uuid = UUID.randomUUID();
		String storeId = uuid.toString();
		String sql = "INSERT INTO stores (storeId,storename,address,number,description,kindof,action) VALUES(?,?,?,?,?,?,?)";
		insert(sql, storeId, store.getStoreName(), store.getStoreAddress(), store.getNumber(), store.getDescription(),
				store.getKindof(), store.getAction());
		return storeId;
	}

}
