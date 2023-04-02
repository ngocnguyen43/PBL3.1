package com.PBL3.daos;

import com.PBL3.dtos.pagination.UserPaginationDTO;
import com.PBL3.models.User;

import java.util.List;

public interface IUserDAO extends GenericDAO<User> {
    List<User> findAll(String role);

    List<User> findAll(String role,String name,String productName,String page);

    User findByUserId(String id);

    User findByEmail(String email);

    User findByCompanyId(String companyId);

    User findByNationalId(String nationalId);

    void save(User user);
    void update(User user);
    void delete(String USerId);

}
