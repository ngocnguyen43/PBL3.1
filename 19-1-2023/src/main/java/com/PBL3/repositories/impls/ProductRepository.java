package com.PBL3.repositories.impls;

import com.PBL3.daos.IProductDAO;
import com.PBL3.models.ProductModel;
import com.PBL3.repositories.IProductRepository;
import com.PBL3.utils.exceptions.dbExceptions.CreateFailedException;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import java.util.List;

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

    @Override
    public List<ProductModel> getAllProduct() throws NotFoundException {
        List<ProductModel> productModels = iProductDAO.findAll();
        if (productModels.isEmpty()) throw new NotFoundException("No Products Found");
        return productModels;
    }
}
