package com.PBL3.repositories.impls;

import com.PBL3.daos.IProductDAO;
import com.PBL3.models.ProductModel;
import com.PBL3.repositories.IProductRepository;
import com.PBL3.utils.exceptions.dbExceptions.CreateFailedException;

import javax.inject.Inject;

public class ProductRepository implements IProductRepository {
    @Inject
    IProductDAO iProductDAO;
    @Override
    public void createNewProduct(ProductModel domain) throws CreateFailedException {
        try{
            iProductDAO.save(domain);
        }catch (Exception e){
            e.printStackTrace();
            throw new CreateFailedException("Create Product Failed");
        }
    }
}
