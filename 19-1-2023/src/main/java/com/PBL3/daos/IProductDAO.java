package com.PBL3.daos;

import com.PBL3.models.ProductModel;
import com.PBL3.models.pagination.ProductPagination;

import java.util.List;

public interface IProductDAO extends GenericDAO<ProductModel> {

    void save(ProductModel domain);

    List<ProductModel> findAll();

    List<ProductModel> findAll(ProductPagination domain);
    List<ProductModel> findAll(ProductPagination domain,String id);
    List<ProductModel> findAllByUserId(String id);

    void updateProduct(ProductModel domain);

    ProductModel findOne(String id,String userId);
    ProductModel findOne(String id);

    void deleteOne(String id);

    Integer countToTalProducts(ProductPagination domain);
}
