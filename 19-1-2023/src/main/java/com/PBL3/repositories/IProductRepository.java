package com.PBL3.repositories;

import com.PBL3.models.ProductModel;
import com.PBL3.utils.exceptions.dbExceptions.CreateFailedException;

public interface IProductRepository {
    void createNewProduct(ProductModel domain) throws CreateFailedException;
}
