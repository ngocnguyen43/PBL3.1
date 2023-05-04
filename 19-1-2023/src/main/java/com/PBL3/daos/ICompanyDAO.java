package com.PBL3.daos;

import com.PBL3.models.User;

import java.util.List;

public interface ICompanyDAO extends GenericDAO<User>{
    List<User> getAllCompanies();
}
