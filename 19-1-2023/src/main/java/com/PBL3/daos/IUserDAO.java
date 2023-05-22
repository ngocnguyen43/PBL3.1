package com.PBL3.daos;

import com.PBL3.models.User;
import com.PBL3.models.pagination.UserPagination;

import java.util.List;

public interface IUserDAO extends GenericDAO<User> {
    List<User> findAll(String role);

    List<User> findAll(String role, UserPagination pagination);

    User findByUserId(String id);

    User findByUserId(String id, boolean withPassword);

    User findByEmail(String email);

    User findByCompanyId(String companyId);

    User findByNationalId(String nationalId);

    Integer countAllRecord(UserPagination pagination, String role);

    void save(User user);

    void update(User user);

    void delete(String USerId);

    List<User> getAllMods();

    void updatePassword(String id, String password);

    String getUserRole(String id);

    String getUserName(String id);

}
