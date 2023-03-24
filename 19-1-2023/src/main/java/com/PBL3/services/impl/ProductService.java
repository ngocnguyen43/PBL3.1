package com.PBL3.services.impl;

import com.PBL3.dtos.ProductDTO;
import com.PBL3.models.ProductModel;
import com.PBL3.repositories.IProductRepository;
import com.PBL3.services.IProductService;
import com.PBL3.utils.exceptions.dbExceptions.CreateFailedException;
import com.PBL3.utils.helpers.Helper;
import com.PBL3.utils.helpers.IDGeneration;
import com.PBL3.utils.response.Message;
import com.PBL3.utils.response.Meta;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

public class ProductService implements IProductService {
    @Inject
    IProductRepository iProductRepository;
    @Override
    public Message createNewProduct(ProductDTO dto) {
        try{
            ProductModel domain = Helper.objectMapper(dto,ProductModel.class);
            String id = IDGeneration.generate();
            domain.setId(id);
            iProductRepository.createNewProduct(domain);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_CREATED).withMessage("Create Product Success!").build();
            return new Message.Builder(meta).build();
        } catch (CreateFailedException e) {
            Meta meta = new Meta.Builder(500).withError(e.getMessage()).build();
            return new Message.Builder(meta).build();
        }
    }
}
