package com.PBL3.daos;

import com.PBL3.models.ProductModel;

import java.util.List;

public interface IProductDAO extends GenericDAO<ProductModel> {

    void save(ProductModel domain);

    List<ProductModel> findAll();

    List<ProductModel> findAllByUserId(String id);

    void updateProduct(ProductModel domain);

    ProductModel findOne(String id);

    void deleteOne(String id);
}
