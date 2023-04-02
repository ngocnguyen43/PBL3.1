package com.PBL3.services.impl;

import com.PBL3.daos.ICertificateDAO;
import com.PBL3.daos.IKindOfProductDAO;
import com.PBL3.daos.IProductCertificateDAO;
import com.PBL3.daos.IProductDAO;
import com.PBL3.dtos.ProductDTO;
import com.PBL3.models.Certificate;
import com.PBL3.models.KindOfProductModel;
import com.PBL3.models.ProductCertificatesModel;
import com.PBL3.models.ProductModel;
import com.PBL3.services.IProductService;
import com.PBL3.utils.exceptions.dbExceptions.CreateFailedException;
import com.PBL3.utils.exceptions.dbExceptions.NotFoundException;
import com.PBL3.utils.exceptions.dbExceptions.UnexpectedException;
import com.PBL3.utils.exceptions.dbExceptions.UpdateFailedException;
import com.PBL3.utils.helpers.Helper;
import com.PBL3.utils.helpers.IDGeneration;
import com.PBL3.utils.response.Data;
import com.PBL3.utils.response.Message;
import com.PBL3.utils.response.Meta;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProductService {

    @Inject
    private IProductDAO iProductDAO;
    @Inject
    private IProductCertificateDAO productCertificateDAO;
    @Inject
    private ICertificateDAO iCertificateDAO;
    @Inject
    private IKindOfProductDAO kindOfProductDAO;

    @Override
    public Message createNewProduct(ProductDTO dto) throws CreateFailedException {
        try {
            ProductModel domain = Helper.objectMapper(dto, ProductModel.class);
            String id = IDGeneration.generate();
            domain.setId(id);
            iProductDAO.save(domain);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_CREATED).withMessage("Create Product Success!").build();
            return new Message.Builder(meta).build();
        } catch (Exception e) {
            throw new CreateFailedException("Create New Product Failed");
        }
    }

    @Override
    public Message getAllProducts() throws UnexpectedException {
        try {
            List<ProductModel> products = iProductDAO.findAll();
            for (ProductModel product : products) {
                List<ProductCertificatesModel> productCertificates = productCertificateDAO.findAllById(product.getId());
                KindOfProductModel kindOfProductModel = kindOfProductDAO.findOne(product.getKindof());
                product.setKindOfProductModel(kindOfProductModel);
                List<Certificate> certificates = new ArrayList<>();
                for (ProductCertificatesModel productCertificate : productCertificates) {
                    Certificate certificate = iCertificateDAO.findOne(productCertificate.getCertificateId());
                    certificates.add(certificate);
                }
                product.setCertificate(certificates);
            }
            Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage("OK!").build();
            Data data = new Data.Builder(null).withResults(products).build();
            return new Message.Builder(meta).withData(data).build();
        } catch (Exception e) {
            throw new UnexpectedException();
        }

    }

    @Override
    public Message updateProduct(ProductDTO dto) throws UpdateFailedException, NotFoundException {
            ProductModel domain = Helper.objectMapper(dto, ProductModel.class);
            ProductModel existedProduct = iProductDAO.findOne(domain.getId());
            if (existedProduct == null) throw new NotFoundException("Product Not Found");
            if (domain.getProductName() == null) domain.setProductName(existedProduct.getProductName());
            if (domain.getUserId() == null) domain.setUserId(existedProduct.getUserId());
            if (domain.getKindof() == null) domain.setKindof(existedProduct.getKindof());
            if (domain.getAction() == null) domain.setAction(existedProduct.getAction());
            if (domain.getModifiedBy() == null) domain.setModifiedBy(existedProduct.getModifiedBy());
        try {
            iProductDAO.updateProduct(domain);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage("OK!").build();
            return new Message.Builder(meta).build();
        } catch (Exception e) {
           throw new UpdateFailedException("Update Product Failed");
        }
    }

    @Override
    public Message deleteProduct(String id) throws UpdateFailedException {
        try {
            iProductDAO.deleteOne(id);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage("OK!").build();
            return new Message.Builder(meta).build();
        } catch (Exception e) {
            throw new UpdateFailedException("Updated Product Failed");
        }
    }
}
