package com.PBL3.daos;

import com.PBL3.models.ProductModel;

import java.util.List;

public interface IProductDAO extends GenericDAO<ProductModel> {

    void save(ProductModel domain);

    List<ProductModel> findAll();
}
