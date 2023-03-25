package com.PBL3.services.impl;

import com.PBL3.dtos.ProductCertificateDTO;
import com.PBL3.models.ProductCertificatesModel;
import com.PBL3.repositories.IProductCertificateRepository;
import com.PBL3.services.IProductCertificateServie;
import com.PBL3.utils.exceptions.dbExceptions.CreateFailedException;
import com.PBL3.utils.exceptions.dbExceptions.DuplicateEntryException;
import com.PBL3.utils.exceptions.dbExceptions.UpdateFailedException;
import com.PBL3.utils.helpers.Helper;
import com.PBL3.utils.helpers.IDGeneration;
import com.PBL3.utils.response.Message;
import com.PBL3.utils.response.Meta;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

public class ProductCertificateService implements IProductCertificateServie {
    @Inject
    IProductCertificateRepository iProductCertificateRepository;
    @Override
    public Message createOne(ProductCertificateDTO dto) {
        try{
            ProductCertificatesModel domain = Helper.objectMapper(dto,ProductCertificatesModel.class);
            String id = IDGeneration.generate();
            domain.setId(id);
            iProductCertificateRepository.createOne(domain);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_CREATED).withMessage("Create Product Certificate Success!").build();
            return new Message.Builder(meta).build();
        } catch (DuplicateEntryException | CreateFailedException e) {
            Meta meta = new Meta.Builder(e.getStatusCode()).withError(e.getMessage()).build();
            return new Message.Builder(meta).build();
        }

    }

    @Override
    public Message deleteOne(String id) {
        try{
            iProductCertificateRepository.deleteOne(id);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage("Delete Product Certificate Success!").build();
            return new Message.Builder(meta).build();
        } catch (UpdateFailedException e) {
            Meta meta = new Meta.Builder(e.getStatusCode()).withError(e.getMessage()).build();
            return new Message.Builder(meta).build();
        }
    }
}
