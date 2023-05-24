package com.PBL3.daos;

import com.PBL3.models.User;
import com.PBL3.models.pagination.UserPagination;

import java.util.List;

public interface ICompanyDAO extends GenericDAO<User> {
    List<User> getAllCompanies(UserPagination domain);
}
