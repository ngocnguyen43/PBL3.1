package com.PBL3.daos;

import com.PBL3.models.ProductModel;

public interface IProductDAO extends GenericDAO<ProductModel> {

    void save(ProductModel domain);
}
