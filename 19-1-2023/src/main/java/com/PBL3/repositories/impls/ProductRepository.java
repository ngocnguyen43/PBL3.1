package com.PBL3.repositories.impls;

import com.PBL3.daos.IProductDAO;
import com.PBL3.models.ProductModel;
import com.PBL3.repositories.IProductRepository;
import com.PBL3.utils.exceptions.dbExceptions.CreateFailedException;
import com.PBL3.utils.exceptions.dbExceptions.UpdateFailedException;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import java.util.List;

public class ProductRepository implements IProductRepository {
    @Inject
    private IProductDAO iProductDAO;
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

    @Override
    public void updateProduct(ProductModel domain) throws UpdateFailedException,NotFoundException {
        ProductModel existedProduct= iProductDAO.findOne(domain.getId());
        if (existedProduct == null) throw new NotFoundException("Product Not Found");
        if (domain.getProductName() == null) domain.setProductName(existedProduct.getProductName());
        if (domain.getUserId() == null) domain.setUserId(existedProduct.getUserId());
        if (domain.getKindof() == null) domain.setKindof(existedProduct.getKindof());
        if (domain.getAction() == null) domain.setAction(existedProduct.getAction());
        if (domain.getModifiedBy() == null) domain.setModifiedBy(existedProduct.getModifiedBy());

        try{
            iProductDAO.updateProduct(domain);
        }catch (Exception e){
            e.printStackTrace();
            throw new UpdateFailedException("Update Product Failed");
        }
    }

    @Override
    public void deleteProduct(String id) throws UpdateFailedException {
        try {
            iProductDAO.deleteOne(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UpdateFailedException("Delete Product Failed");
        }
    }
}
