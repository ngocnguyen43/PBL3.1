package com.PBL3.daos.impl;

import com.PBL3.daos.IProductDAO;
import com.PBL3.models.ProductModel;
import com.PBL3.utils.mapper.ProductMapper;

import java.util.List;

public class ProductDAO extends AbstractDAO<ProductModel> implements IProductDAO {
    @Override
    public void save(ProductModel domain) {
        String sql = "INSERT INTO products (product_id,user_id,product_name,kindof,action,modified_by) VALUES (?,?,?,?,?,?)";
        insert(sql, domain.getId(), domain.getUserId(), domain.getProductName(), domain.getKindof(), domain.getAction(), domain.getModifiedBy());
    }

    @Override
    public List<ProductModel> findAll() {
        String sql = "SELECT * FROM products ";

        return query(sql, new ProductMapper());
    }

    @Override
    public void updateProduct(ProductModel domain) {
        String sql = "UPDATE products SET product_name = ?,kindof = ?,action = ?,modified_by = ? WHERE product_id = ?";
        update(sql, domain.getProductName(), domain.getKindof(), domain.getAction(), domain.getModifiedBy(), domain.getId());
    }

    @Override
    public ProductModel findOne(String id) {
        String sql = "SELECT * FROM products WHERE product_id = ?";
        List<ProductModel> productModels = query(sql, new ProductMapper(), id);
        return productModels.isEmpty() ? null : productModels.get(0);

    }

    @Override
    public void deleteOne(String id) {
        String sql = "DELETE FROM products WHERE product_id = ?";
        delete(sql, id);
    }


}
