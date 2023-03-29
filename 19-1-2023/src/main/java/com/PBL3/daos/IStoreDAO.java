package com.PBL3.daos;

import com.PBL3.models.Store;

import java.util.List;

public interface IStoreDAO extends GenericDAO<Store> {
    List<Store> findAll();

    Store findByStoreNumber(String id);

    Store findByStoreId(String id);

    String save(Store store);

}
