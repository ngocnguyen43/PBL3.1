package com.PBL3.services.impl;

import com.PBL3.daos.IProductCertificateDAO;
import com.PBL3.dtos.ProductCertificateDTO;
import com.PBL3.models.ProductCertificatesModel;
import com.PBL3.services.IProductCertificateService;
import com.PBL3.utils.exceptions.dbExceptions.CreateFailedException;
import com.PBL3.utils.exceptions.dbExceptions.DuplicateEntryException;
import com.PBL3.utils.exceptions.dbExceptions.UpdateFailedException;
import com.PBL3.utils.helpers.Helper;
import com.PBL3.utils.helpers.IDGeneration;
import com.PBL3.utils.response.Message;
import com.PBL3.utils.response.Meta;
import com.PBL3.utils.response.Response;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

public class ProductCertificateService implements IProductCertificateService {

    @Inject
    private IProductCertificateDAO iProductCertificateDAO;

    @Override
    public Message createOne(ProductCertificateDTO dto) throws DuplicateEntryException, CreateFailedException {
        ProductCertificatesModel domain = Helper.objectMapper(dto, ProductCertificatesModel.class);
        String id = IDGeneration.generate();
        domain.setId(id);
        ProductCertificatesModel old = iProductCertificateDAO.findOne(domain.getId());
        if (old != null && (!domain.getCertificateId().equals(old.getCertificateId()) || !domain.getProductId().equals(old.getProductId())))
            throw new DuplicateEntryException(Response.DUPLICATED);
        try {
            iProductCertificateDAO.createOne(domain);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_CREATED).withMessage(Response.CREATED).build();
            return new Message.Builder(meta).build();
        } catch (Exception e) {
            throw new CreateFailedException(Response.CREATE_FAILED);
        }

    }

    @Override
    public Message deleteOne(ProductCertificateDTO dto) throws UpdateFailedException {
        ProductCertificatesModel domain = Helper.objectMapper(dto, ProductCertificatesModel.class);
        try {
            iProductCertificateDAO.deleteOne(domain);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_NO_CONTENT).build();
            return new Message.Builder(meta).build();
        } catch (Exception e) {
            throw new UpdateFailedException(Response.FAILED);
        }
    }
}
