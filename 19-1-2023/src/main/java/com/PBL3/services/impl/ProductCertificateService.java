package com.PBL3.services.impl;

import com.PBL3.daos.IProductCertificateDAO;
import com.PBL3.dtos.ProductCertificateDTO;
import com.PBL3.models.ProductCertificatesModel;
import com.PBL3.services.IProductCertificateServie;
import com.PBL3.utils.exceptions.dbExceptions.CreateFailedException;
import com.PBL3.utils.exceptions.dbExceptions.DuplicateEntryException;
import com.PBL3.utils.exceptions.dbExceptions.UnexpectedException;
import com.PBL3.utils.helpers.Helper;
import com.PBL3.utils.helpers.IDGeneration;
import com.PBL3.utils.response.Message;
import com.PBL3.utils.response.Meta;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

public class ProductCertificateService implements IProductCertificateServie {

    @Inject
    private IProductCertificateDAO iProductCertificateDAO;

    @Override
    public Message createOne(ProductCertificateDTO dto) throws DuplicateEntryException, CreateFailedException {
        ProductCertificatesModel domain = Helper.objectMapper(dto, ProductCertificatesModel.class);
        String id = IDGeneration.generate();
        domain.setId(id);
        ProductCertificatesModel old = iProductCertificateDAO.findOne(domain.getId());
        if (old != null && (!domain.getCertificateId().equals(old.getCertificateId()) || !domain.getProductId().equals(old.getProductId())))
            throw new DuplicateEntryException("Product's Certificate is Existed");
        try {
            iProductCertificateDAO.createOne(domain);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_CREATED).withMessage("Create Product Certificate Success!").build();
            return new Message.Builder(meta).build();
        } catch (Exception e) {
            throw new CreateFailedException("Create new Certificate for Product Failed");
        }

    }

    @Override
    public Message deleteOne(String id) throws UnexpectedException {
        try {
            iProductCertificateDAO.deleteOne(id);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage("Delete Product Certificate Success!").build();
            return new Message.Builder(meta).build();
        } catch (Exception e) {
            throw new UnexpectedException();
        }
    }
}
