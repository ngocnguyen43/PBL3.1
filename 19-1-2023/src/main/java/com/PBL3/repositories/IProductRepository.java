package com.PBL3.repositories;

import com.PBL3.models.ProductModel;
import com.PBL3.utils.exceptions.dbExceptions.CreateFailedException;
import com.PBL3.utils.exceptions.dbExceptions.NotFoundException;
import com.PBL3.utils.exceptions.dbExceptions.UpdateFailedException;

import java.util.List;

public interface IProductRepository {
    void createNewProduct(ProductModel domain) throws CreateFailedException;

    List<ProductModel> getAllProduct() throws NotFoundException;

    void updateProduct(ProductModel domain) throws UpdateFailedException, NotFoundException;

    void deleteProduct(String id) throws UpdateFailedException;
}
