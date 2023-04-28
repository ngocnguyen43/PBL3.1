package com.PBL3.daos;

import com.PBL3.models.ProductCertificatesModel;

import java.util.List;

public interface IProductCertificateDAO extends GenericDAO<ProductCertificatesModel> {
    void createOne(ProductCertificatesModel domain);

    void deleteOne(ProductCertificatesModel domain);

    ProductCertificatesModel findOne(String id);

    List<ProductCertificatesModel> findAllById(String id);
}
