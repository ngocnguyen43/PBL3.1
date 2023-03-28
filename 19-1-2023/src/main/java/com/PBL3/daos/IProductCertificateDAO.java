package com.PBL3.daos;

import com.PBL3.models.ProductCertificatesModel;

public interface IProductCertificateDAO extends GenericDAO<ProductCertificatesModel> {
    void createOne(ProductCertificatesModel domain);

    void deleteOne(String id);

    ProductCertificatesModel findOne(String id);
}
