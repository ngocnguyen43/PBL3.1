package com.PBL3.daos.impl;

import com.PBL3.daos.IStoreDAO;
import com.PBL3.models.Store;
import com.PBL3.utils.mapper.StoreMapper;

import java.util.List;
import java.util.UUID;

public class StoreDAO extends AbstractDAO<Store> implements IStoreDAO {

    @Override
    public List<Store> findAll() {
        String sql = "SELECT * FROM stores ";
        return query(sql, new StoreMapper());
    }

    @Override
    public Store findByStoreNumber(String number) {
        // TODO Auto-generated method stub
        String sql = "SELECT * FROM stores WHERE number = ?";
        List<Store> stores = query(sql, new StoreMapper(), number);
        return stores.isEmpty() ? null : stores.get(0);
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

    @Override
    public Store findByStoreId(String id) {
        String sql = "SELECT * FROM stores WHERE storeId = ?";
        List<Store> stores = query(sql, new StoreMapper(), id);
        return stores.isEmpty() ? null : stores.get(0);
    }

}
