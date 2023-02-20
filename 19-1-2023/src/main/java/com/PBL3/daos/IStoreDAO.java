package com.PBL3.daos;

import java.util.List;

import com.PBL3.models.Store;

public interface IStoreDAO extends GenericDAO<Store> {
	List<Store> findAll();
	
	Store findByStoreId(String id);
	
	String save(Store store);

}
