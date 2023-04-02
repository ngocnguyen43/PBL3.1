package com.PBL3.utils.mapper;

import com.PBL3.models.ProductModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements IMapper<ProductModel> {
    @Override
    public ProductModel mapRow(ResultSet result) {
        ProductModel product = new ProductModel();
        try {
            product.setId(result.getString("product_id"));
            product.setCompanyId(result.getString("company_id"));
            product.setProductName(result.getString("product_name"));
            product.setKindof(result.getString("kindof"));
            product.setAction(result.getInt("action"));
            product.setModifiedBy(result.getString("modified_by"));
            product.setUpdatedAt(result.getTimestamp("updated_at"));
            product.setCreatedAt(result.getTimestamp("created_at"));
            return product;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
