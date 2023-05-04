package com.PBL3.daos.impl;

import com.PBL3.daos.IProductCertificateDAO;
import com.PBL3.models.ProductCertificatesModel;
import com.PBL3.utils.mapper.ProductCertificateMapper;

import java.util.List;

public class ProductCertificateDAO extends AbstractDAO<ProductCertificatesModel> implements IProductCertificateDAO {
    @Override
    public void createOne(ProductCertificatesModel domain) {
        String sql = "INSERT INTO product_certificates (id,certificate_id,product_id,action) VALUES (?,?,?,?) ";
        insert(sql, domain.getId(), domain.getCertificateId(), domain.getProductId(), domain.getAction());
    }

    @Override
    public void deleteOne(ProductCertificatesModel domain) {
        String sql = "DELETE FROM product_certificates WHERE product_id = ? AND certificate_id = ?";
        delete(sql, domain.getProductId(), domain.getCertificateId());
    }

    @Override
    public ProductCertificatesModel findOne(String id) {
        String sql = "SELECT * FROM product_certificates WHERE id = ?";
        List<ProductCertificatesModel> productCertificatesModels = query(sql, new ProductCertificateMapper(), id);
        return productCertificatesModels.isEmpty() ? null : productCertificatesModels.get(0);
    }

    @Override
    public List<ProductCertificatesModel> findAllById(String id) {
        String sql = "SELECT * FROM product_certificates WHERE product_id = ?";
        return query(sql, new ProductCertificateMapper(), id);
    }
}
