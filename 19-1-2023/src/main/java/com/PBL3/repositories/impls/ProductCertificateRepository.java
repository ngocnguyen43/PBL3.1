package com.PBL3.repositories.impls;

import com.PBL3.daos.IProductCertificateDAO;
import com.PBL3.models.ProductCertificatesModel;
import com.PBL3.repositories.IProductCertificateRepository;
import com.PBL3.utils.exceptions.dbExceptions.CreateFailedException;
import com.PBL3.utils.exceptions.dbExceptions.DuplicateEntryException;
import com.PBL3.utils.exceptions.dbExceptions.UpdateFailedException;

import javax.inject.Inject;

public class ProductCertificateRepository implements IProductCertificateRepository {
    @Inject
    IProductCertificateDAO iProductCertificateDAO;
    @Override
    public void createOne(ProductCertificatesModel domain) throws DuplicateEntryException, CreateFailedException {
        ProductCertificatesModel old = iProductCertificateDAO.findOne(domain.getId());
        if (old != null && (!domain.getCertificateId().equals( old.getCertificateId()) || !domain.getProductId().equals( old.getProductId())))
            throw new DuplicateEntryException("Product's Certificate is Existed");
        try{
            iProductCertificateDAO.createOne(domain);
        }catch (Exception e){
            e.printStackTrace();
            throw new CreateFailedException("Create Product's Certificate failed");
        }
    }

    @Override
    public void deleteOne(String id) throws UpdateFailedException {
        try {
            iProductCertificateDAO.deleteOne(id);
        }catch (Exception e){
            e.printStackTrace();
            throw new UpdateFailedException("Delete Product's Certificate Failed");
        }
    }
}
