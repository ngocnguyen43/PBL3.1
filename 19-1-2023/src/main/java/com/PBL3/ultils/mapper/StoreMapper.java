package com.PBL3.ultils.mapper;

import java.sql.ResultSet;

import com.PBL3.models.Store;

public class StoreMapper implements IMapper<Store> {

	@Override
	public Store mapRow(ResultSet result) {
		// TODO Auto-generated method stub
		Store store = new Store();
		try {
			
			store.setStoreId(result.getString("storeId"));
			store.setStoreName(result.getString("storename"));
			store.setStoreAddress(result.getString("address"));
			store.setNumber(result.getString("number"));
			store.setDescription(result.getString("description"));
			store.setKindof(result.getString("kindof"));
			store.setAction(result.getInt("action"));
			store.setModifiedBy(result.getString("modifiedBy"));
			store.setUpdatedAt(result.getTimestamp("updatedAt"));
			store.setCreatedAt(result.getTimestamp("createdAt"));

			return store;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

}
