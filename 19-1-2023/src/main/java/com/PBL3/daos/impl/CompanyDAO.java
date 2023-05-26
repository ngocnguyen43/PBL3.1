package com.PBL3.daos.impl;

import com.PBL3.daos.ICompanyDAO;
import com.PBL3.models.User;
import com.PBL3.models.pagination.UserPagination;
import com.PBL3.utils.mapper.CompanyMapper;

import java.util.List;

import static com.PBL3.utils.Constants.Pagination.PER_PAGE;

public class CompanyDAO extends AbstractDAO<User> implements ICompanyDAO {
    @Override
    public List<User> getAllCompanies(UserPagination domain) {
        String sql = "SELECT login.users.user_id,login.users.company_id,login.users.company_name FROM login.users" +
                " WHERE role_id = 3" + " ORDER BY role_id ASC,created_at DESC" +
                " LIMIT " + PER_PAGE + " OFFSET " + (domain.getPage() - 1) * PER_PAGE;
        return query(sql, new CompanyMapper());
    }
}
