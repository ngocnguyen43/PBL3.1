package com.PBL3.daos.impl;

import com.PBL3.daos.IProductDAO;
import com.PBL3.models.ProductModel;

public class ProductDAO extends AbstractDAO<ProductModel> implements IProductDAO {
    @Override
    public void save(ProductModel domain) {
        String sql = "INSERT INTO products (product_id,user_id,product_name,kindof,action,modified_by) VALUES (?,?,?,?,?,?)";
        insert(sql,domain.getId(),domain.getUserId(),domain.getProductName(),domain.getKindof(),domain.getAction(),domain.getModifiedBy());
    }
}
