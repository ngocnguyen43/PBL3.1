package com.PBL3.daos.impl;

import com.PBL3.daos.ICompanyDAO;
import com.PBL3.models.User;
import com.PBL3.utils.mapper.CompanyMapper;

import java.util.List;

public class CompanyDAO extends AbstractDAO<User> implements ICompanyDAO {
    @Override
    public List<User> getAllCompanies() {
        String sql = "SELECT login.users.user_id,login.users.company_id,login.users.company_name FROM login.users WHERE role_id = 3";
        return query(sql, new CompanyMapper());
    }
}
