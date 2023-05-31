package com.PBL3.services.impl;

import com.PBL3.daos.*;
import com.PBL3.dtos.ProductDTO;
import com.PBL3.dtos.pagination.ProductPaginationDTO;
import com.PBL3.models.*;
import com.PBL3.models.pagination.ProductPagination;
import com.PBL3.services.INotificationService;
import com.PBL3.services.IProductService;
import com.PBL3.utils.exceptions.dbExceptions.*;
import com.PBL3.utils.helpers.Helper;
import com.PBL3.utils.helpers.IDGeneration;
import com.PBL3.utils.response.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static com.PBL3.utils.Constants.Pagination.PER_PAGE;

public class ProductService implements IProductService {

    @Inject
    private IProductDAO iProductDAO;
    @Inject
    private IProductCertificateDAO productCertificateDAO;
    @Inject
    private ICertificateDAO iCertificateDAO;
    @Inject
    private IKindOfProductDAO kindOfProductDAO;
    @Inject
    private INotificationService iNotificationService;

    @Inject
    private IUserDAO userDAO;

    @Override
    public Message createNewProduct(ProductDTO dto) throws CreateFailedException {
        Notification notification = new Notification
                .Builder(IDGeneration.generate())
                .withCreator(dto.getUserId())
                .withAdmin(true)
                .withMessage(dto.getUserId() + " company have just submitted new request for creating new product")
                .build();
        try {
            ProductModel domain = Helper.objectMapper(dto, ProductModel.class);
            String id = IDGeneration.generate();
            domain.setId(id);
            iProductDAO.save(domain);
            iNotificationService.create(notification);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_CREATED).withMessage(Response.CREATED).build();
            return new Message.Builder(meta).build();
        } catch (Exception e) {
            throw new CreateFailedException(Response.CREATE_FAILED);
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
            Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.OK).build();
            Data data = new Data.Builder(null).withResults(products).build();
            return new Message.Builder(meta).withData(data).build();
        } catch (Exception e) {
            throw new UnexpectedException();
        }

    }

    @Override
    public Message getAllProducts(ProductPaginationDTO dto) throws UnexpectedException {

        ProductPagination domain = Helper.objectMapper(dto, ProductPagination.class);
        if (domain.getPage() < 1) domain.setPage(1);
        try {
            List<ProductModel> products = iProductDAO.findAll(domain);
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
            Integer records = iProductDAO.countToTalProducts(domain);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.OK).build();
            Data data = new Data.Builder(null).withResults(products).build();
            Pagination pagination = new Pagination.Builder().withCurrentPage(domain.getPage())
                    .withTotalPages((int) Math.ceil((double) records / PER_PAGE))
                    .withTotalResults(records)
                    .build();
            return new Message.Builder(meta).withData(data).withPagination(pagination).build();
        } catch (Exception e) {
            throw new UnexpectedException();
        }
    }

    @Override
    public Message getProduct(String id, String userId) throws UnexpectedException {
        ProductModel domain = iProductDAO.findOne(id, userId);
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.OK).build();
        Data data = new Data.Builder(null).withResults(domain).build();
        return new Message.Builder(meta).withData(data).build();
    }

    @Override
    public Message updateProduct(ProductDTO dto) throws UpdateFailedException, NotFoundException {
        ProductModel domain = Helper.objectMapper(dto, ProductModel.class);
        ProductModel existedProduct = iProductDAO.findOne(domain.getId());
        if (existedProduct == null) throw new NotFoundException(Response.NOT_FOUND);
        if (domain.getProductName() == null) domain.setProductName(existedProduct.getProductName());
        if (domain.getUserId() == null) domain.setUserId(existedProduct.getUserId());
        if (domain.getKindof() == null) domain.setKindof(existedProduct.getKindof());
        if (domain.getAction() == null) domain.setAction(existedProduct.getAction());
        if (domain.getModifiedBy() == null) domain.setModifiedBy(existedProduct.getModifiedBy());
        try {
            iProductDAO.updateProduct(domain);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_NO_CONTENT).withMessage(Response.SUCCESS).build();
            return new Message.Builder(meta).build();
        } catch (Exception e) {
            throw new UpdateFailedException(Response.FAILED);
        }
    }

    @Override
    public Message deleteProduct(String id) throws UpdateFailedException {
        try {
            iProductDAO.deleteOne(id);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.OK).build();
            return new Message.Builder(meta).build();
        } catch (Exception e) {
            throw new UpdateFailedException(Response.FAILED);
        }
    }

    @Override
    public Message getAllProducts(ProductPaginationDTO dto, String id) throws UnexpectedException {
        ProductPagination domain = Helper.objectMapper(dto, ProductPagination.class);
        if (domain.getPage() < 1) domain.setPage(1);
        try {
            List<ProductModel> products = iProductDAO.findAll(domain, id);
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
            Integer records = iProductDAO.countToTalProducts(domain);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.OK).build();
            Data data = new Data.Builder(null).withResults(products).build();
            Pagination pagination = new Pagination.Builder().withCurrentPage(domain.getPage())
                    .withTotalPages((int) Math.ceil((double) records / PER_PAGE))
                    .withTotalResults(records)
                    .build();
            return new Message.Builder(meta).withData(data).withPagination(pagination).build();
        } catch (Exception e) {
            throw new UnexpectedException();
        }
    }

    @Override
    public Message createNewProduct(ProductDTO dto, String id) throws CreateFailedException, InvalidPropertiesException {
        if (id == null) throw new InvalidPropertiesException("Invalid properties");
        Notification notification = new Notification
                .Builder(IDGeneration.generate())
                .withCreator(id)
                .withAdmin(true)
                .withMessage(id + " company have just submitted new request for creating new product")
                .build();
        ProductModel domain = Helper.objectMapper(dto, ProductModel.class);
        try {
            String idTemp = IDGeneration.generate();
            domain.setId(idTemp);
            iProductDAO.save(domain);
            iNotificationService.create(notification);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_CREATED).withMessage(Response.CREATED).build();
            return new Message.Builder(meta).build();
        } catch (Exception e) {
            throw new CreateFailedException(Response.CREATE_FAILED);
        }
    }
}
