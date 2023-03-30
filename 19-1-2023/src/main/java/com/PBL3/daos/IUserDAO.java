package com.PBL3.daos;

import com.PBL3.models.User;

import java.util.List;

public interface IUserDAO extends GenericDAO<User> {
    List<User> findAll(String role);

    User findByUserId(String id);

    User findByEmail(String email);

    User findByCompanyId(String companyId);

    User findByNationalId(String nationalId);

    String save(User user);

    void delete(String USerId);

}
