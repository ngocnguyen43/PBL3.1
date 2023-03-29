package com.PBL3.utils.mapper;

import com.PBL3.models.ProductCertificatesModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductCertificateMapper implements IMapper<ProductCertificatesModel> {
    @Override
    public ProductCertificatesModel mapRow(ResultSet result) {
        ProductCertificatesModel productCertificatesModel = new ProductCertificatesModel();
        try {
            productCertificatesModel.setId(result.getString("id"));
            productCertificatesModel.setProductId(result.getString("product_id"));
            productCertificatesModel.setCertificateId(result.getString("certificate_id"));
            productCertificatesModel.setAction(result.getInt("action"));
            return productCertificatesModel;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
