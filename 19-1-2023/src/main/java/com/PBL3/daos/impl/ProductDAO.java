package com.PBL3.daos.impl;

import com.PBL3.daos.IProductDAO;
import com.PBL3.models.ProductModel;
import com.PBL3.models.pagination.ProductPagination;
import com.PBL3.utils.mapper.CountMapper;
import com.PBL3.utils.mapper.ProductMapper;

import java.util.List;

import static com.PBL3.utils.Constants.Pagination.PER_PAGE;

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
    public List<ProductModel> findAll(ProductPagination domain) {
        String sql = "SELECT login.products.*,login.users.company_name FROM login.users \n" +
                " LEFT JOIN \n" +
                "\tlogin.products\n" +
                "ON login.users.user_id = login.products.user_id\n" +
                "WHERE product_id IS NOT NULL ";
        if (domain.getCompany() != null) sql += " AND company_name LIKE '%" + domain.getCompany() + "%'";
        else sql += " AND true ";
        if (domain.getProduct() != null) sql += " AND product_name LIKE '%" + domain.getProduct() + "%'";
        else sql += " AND true ";
        sql += " ORDER BY created_at LIMIT " + PER_PAGE + " OFFSET " + (domain.getPage() - 1) * PER_PAGE;
        System.out.println(sql);

        return query(sql, new ProductMapper());
    }

    @Override
    public List<ProductModel> findAll(ProductPagination domain, String id) {
        String sql = "SELECT login.products.*,login.users.company_name FROM login.users \n" +
                " LEFT JOIN \n" +
                "\tlogin.products\n" +
                "ON login.users.user_id = login.products.user_id\n" +
                "WHERE product_id IS NOT NULL AND login.products.user_id = ? ";
        if (domain.getCompany() != null) sql += " AND company_name LIKE '%" + domain.getCompany() + "%'";
        else sql += " AND true ";
        if (domain.getProduct() != null) sql += " AND product_name LIKE '%" + domain.getProduct() + "%'";
        else sql += " AND true ";
        sql += " ORDER BY created_at LIMIT " + PER_PAGE + " OFFSET " + (domain.getPage() - 1) * PER_PAGE;
        System.out.println(sql);
        return query(sql, new ProductMapper(), id);

    }

    @Override
    public List<ProductModel> findAllByUserId(String id) {
        String sql = "SELECT * FROM products  WHERE user_id = ?";

        return query(sql, new ProductMapper(), id);
    }

    @Override
    public void updateProduct(ProductModel domain) {
        String sql = "UPDATE products SET product_name = ?,kindof = ?,action = ?,modified_by = ? WHERE product_id = ?";
        update(sql, domain.getProductName(), domain.getKindof(), domain.getAction(), domain.getModifiedBy(), domain.getId());
    }

    @Override
    public ProductModel findOne(String id, String userId) {
        String sql = "SELECT * FROM products WHERE product_id = ? AND user_id = ?";
        List<ProductModel> productModels = query(sql, new ProductMapper(), id, userId);
        return productModels.isEmpty() ? null : productModels.get(0);

    }

    @Override
    public ProductModel findOne(String id) {
        String sql = "SELECT * FROM products WHERE product_id = ? ";
        List<ProductModel> productModels = query(sql, new ProductMapper(), id);
        return productModels.isEmpty() ? null : productModels.get(0);
    }

    @Override
    public void deleteOne(String id) {
        String sql = "DELETE FROM products WHERE product_id = ?";
        delete(sql, id);
    }

    @Override
    public Integer countToTalProducts(ProductPagination domain) {
        String sql = " SELECT COUNT(login.products.product_id) AS total  FROM login.users " +
                " LEFT JOIN " +
                " login.products " +
                " ON login.users.user_id = login.products.user_id\n" +
                " WHERE product_id IS NOT NULL";
        if (domain.getCompany() != null) sql += " AND company_name LIKE '%" + domain.getCompany() + "%'";
        else sql += " AND true ";
        if (domain.getProduct() != null) sql += " AND product_name LIKE '%" + domain.getProduct() + "%'";
        else sql += " AND true ";
        List<Integer> records = query(sql, new CountMapper());
        return records.isEmpty() ? null : records.get(0);
    }


}
