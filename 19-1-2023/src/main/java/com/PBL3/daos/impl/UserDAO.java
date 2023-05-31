package com.PBL3.daos.impl;

import com.PBL3.daos.IUserDAO;
import com.PBL3.models.User;
import com.PBL3.models.pagination.UserPagination;
import com.PBL3.utils.helpers.HashPassword;
import com.PBL3.utils.helpers.IDGeneration;
import com.PBL3.utils.mapper.CountMapper;
import com.PBL3.utils.mapper.UserMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import static com.PBL3.utils.Constants.Pagination.PER_PAGE;

public class UserDAO extends AbstractDAO<User> implements IUserDAO {

    @Override
    public List<User> findAll(String role) {
        // TODO Auto-generated method stub
//        String sql = "SELECT * FROM users INNER JOIN roles ON users.roleId = roles.roleId";
        final String sql_admin = " SELECT login.users.*,login.roles.role_code,login.roles.role_name \n" + " FROM login.users \n" + " INNER JOIN login.roles ON login.users.role_id = login.roles.role_id \n" + " WHERE login.roles.role_id  LIKE 3 LIMIT 5 ";
        final String sql_mod = "SELECT login.users.*,login.roles.role_code,login.roles.role_name " + "FROM login.users " + "INNER JOIN login.roles ON login.users.role_id = login.roles.role_id " + "WHERE login.roles.role_id  LIKE 3 ORDER BY role_id,created_at ASC ";
//		System.out.println(query(sql, new UserMapper()));

        final String sql = "SELECT login.users.user_id,login.users.role_id,login.users.company_id," + "login.users.company_name,login.users.tax_identification_number,login.users.phone_number,login.users.fax_number,login.users.email,login.users.full_name,login.users.password,login.users.national_id,login.users.user_number,login.users.action,login.users.modified_by,login.users.updated_at,login.users.created_at," + " login.business.business_id,login.business.business_name," + " login.products.product_id,login.products.product_name," + " login.kind_of_product.kindId,login.kind_of_product.name as kind_name," + " login.certificates.certificate_id,login.certificates.name as certificate_name,login.certificates.image" + " FROM login.users" + " LEFT JOIN login.business ON login.users.business_id = login.business.business_id " + " LEFT JOIN login.products ON login.products.user_id = login.users.user_id " + " LEFT JOIN login.kind_of_product ON login.products.kindof = login.kind_of_product.kindId" + " LEFT JOIN login.product_certificates ON login.product_certificates.product_id = login.products.product_id" + " LEFT JOIN login.certificates ON login.certificates.certificate_id = login.product_certificates.certificate_id" + " WHERE login.users.role_id LIKE 3" +
//                " ORDER BY login.users.role_id ASC " +
                " LIMIT 5";
        if (role.equals("ADMIN")) return query(sql_admin, new UserMapper());
        if (role.equals("MOD")) return query(sql_admin, new UserMapper());
        return null;
    }

    @Override
    public List<User> findAll(String role, UserPagination pagination) {
        String sql = "SELECT * FROM login.users  INNER JOIN login.business ON users.business_id = business.business_id " +
                "INNER JOIN login.roles ON login.users.role_id = login.roles.role_id  WHERE ";
        if (pagination.getFullname() != null)
            sql += " login.users.full_name LIKE '%" + pagination.getFullname() + "%' ";
        else sql += " true ";
        if (pagination.getEmail() != null) sql += " AND login.users.email LIKE '%" + pagination.getEmail() + "%'";
        else sql += "AND true ";
        if (role.equals("ADMIN")) sql += "AND login.users.role_id != 1";
        if (role.equals("MODERATOR")) sql += "AND login.users.role_id = 3";
        sql += " ORDER BY login.users.role_id ASC, login.users.created_at DESC LIMIT " + PER_PAGE + " OFFSET " + (pagination.getPage() - 1) * PER_PAGE;
        System.out.println(sql);
        return query(sql, new UserMapper());
    }

    @Override
    public User findByUserId(String id) {
        // TODO Auto-generated method stub
        String sql = "SELECT * FROM users  INNER JOIN roles ON users.role_id = roles.role_id WHERE user_id = ?";
        List<User> users = query(sql, new UserMapper(), id);
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public User findByUserId(String id, boolean withPassword) {
        String sql = "SELECT * FROM users  INNER JOIN roles ON users.role_id = roles.role_id WHERE user_id = ?";
        List<User> users = query(sql, new UserMapper(false, withPassword), id);
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public void save(User user) {
        // TODO Auto-generated method stub
        String password = HashPassword.HashPW(user.getPassword());

        String sql = "INSERT INTO users (user_id," + "role_id," + "company_id," + "company_name," + "tax_identification_number," + "business_id," + "phone_number," + "fax_number," + "email," + "full_name," + "national_id," + "user_number," + "password," + "action," + "modified_by)" + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        // helper
        String id = IDGeneration.generate();
        // helper hashed password
//		String taxId = user.getTaxIndentity() != null ? user.getTaxIndentity() : null;
        insert(sql, id, user.getRoleId(), user.getCompanyId(), user.getCompanyName(), user.getTaxIndentity(), user.getBusinessId(), user.getPhoneNumber(), user.getFaxNumber(), user.getEmail(), user.getFullName(), user.getNationalId(), user.getUserNumber(), password, user.getAction(), user.getModifiedBy());

    }

    @Override
    public void update(User user) {

        String sql = "UPDATE login.users SET " + "users.company_name = ?, " + "users.tax_identification_number = ?," + "users.phone_number = ?," + "users.fax_number = ?," + "users.email = ?," + "users.full_name = ?," + "users.national_id = ?," + "users.user_number = ?" + "WHERE users.user_id = ? ";
        update(sql, user.getCompanyName(), user.getTaxIndentity(), user.getPhoneNumber(), user.getFaxNumber(), user.getEmail(), user.getFullName(), user.getNationalId(), user.getUserNumber(), user.getId());
    }

    public void delete(String userId) {
        // TODO Auto-generated method stub
        User user = findByUserId(userId);
        try {
            System.out.println(new ObjectMapper().writeValueAsString(user));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        int action = user.getAction() == 0 ? 1 : 0;
        String sql = "UPDATE users SET action = ? WHERE user_id = ?";
        insert(sql, action, user.getId());

    }

    @Override
    public List<User> getAllMods() {
        String sql = "SELECT * FROM users  INNER JOIN roles ON users.role_id = roles.role_id WHERE users.role_id  = 2 ORDER BY created_at";
        return query(sql, new UserMapper());
    }

    @Override
    public void updatePassword(String id, String password) {
        String sql = "UPDATE login.users SET password = ? WHERE users.user_id = ?";
        update(sql, password, id);
    }

    @Override
    public String getUserRole(String id) {
        String sql = "SELECT * FROM login.users " +
//                "INNER JOIN login.business ON users.business_id = business.business_id " +
                "INNER JOIN roles ON users.role_id = roles.role_id WHERE login.users.user_id = ?";
        List<User> users = query(sql, new UserMapper(), id);
        return users.isEmpty() ? null : users.get(0).getRole().getRoleName();
    }

    @Override
    public String getUserName(String id) {
        String sql = "SELECT * FROM login.users  " +
                "INNER JOIN roles ON users.role_id = roles.role_id WHERE login.users.user_id = ?";
        List<User> users = query(sql, new UserMapper(), id);
        return users.isEmpty() ? null : users.get(0).getFullName();
    }

    @Override
    public User findByEmail(String email) {
        // TODO Auto-generated method stub
        String sql = "SELECT * FROM login.users  " +
                "INNER JOIN roles ON users.role_id = roles.role_id WHERE users.email = ? ";
//		String sql = "SELECT * FROM users INNER JOIN roles ON users.roleId = roles.roleId WHERE email = ? ";

        List<User> users = query(sql, new UserMapper(false, true), email);

        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public User findByCompanyId(String companyId) {
        String sql = "SELECT * FROM users  " +
                "INNER JOIN login.business ON users.business_id = business.business_id " +
                "INNER JOIN roles ON users.role_id = roles.role_id WHERE company_id = ? ";
//		String sql = "SELECT * FROM users INNER JOIN roles ON users.roleId = roles.roleId WHERE email = ? ";

        List<User> users = query(sql, new UserMapper(), companyId);

        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public User findByNationalId(String nationalId) {
        String sql = "SELECT * FROM users " +
                "INNER JOIN roles ON users.role_id = roles.role_Id WHERE national_id = ?";
        // TODO Auto-generated method stub
        List<User> users = query(sql, new UserMapper(false, true), nationalId);
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public Integer countAllRecord(UserPagination pagination, String role) {
        String sql = "SELECT COUNT(user_id) as total FROM login.users  INNER JOIN login.roles ON login.users.role_id = login.roles.role_id  WHERE ";
        if (pagination.getFullname() != null)
            sql += " login.users.full_name LIKE '%" + pagination.getFullname() + "%' ";
        else sql += " true ";
        if (pagination.getEmail() != null) sql += " AND login.users.email LIKE '%" + pagination.getEmail() + "%'";
        else sql += "AND true ";
        if (role.equals("ADMIN")) sql += "AND login.users.role_id != 1";
        if (role.equals("MODERATOR")) sql += "AND login.users.role_id = 3";
        List<Integer> pages = query(sql, new CountMapper());
        return pages.isEmpty() ? null : pages.get(0);
    }

}
