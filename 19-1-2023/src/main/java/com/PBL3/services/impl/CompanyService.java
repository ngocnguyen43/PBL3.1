package com.PBL3.services.impl;

import com.PBL3.daos.ICompanyDAO;
import com.PBL3.daos.IUserDAO;
import com.PBL3.dtos.pagination.UserPaginationDTO;
import com.PBL3.models.User;
import com.PBL3.models.pagination.UserPagination;
import com.PBL3.services.ICompanyService;
import com.PBL3.utils.helpers.Helper;
import com.PBL3.utils.response.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.PBL3.utils.Constants.Pagination.PER_PAGE;

public class CompanyService implements ICompanyService {
    @Inject
    private ICompanyDAO companyDAO;
    @Inject
    private IUserDAO userDAO;

    @Override
    public Message getAllcompanies(UserPaginationDTO dto) {
        UserPagination domain = Helper.objectMapper(dto, UserPagination.class);
        List<User> users = companyDAO.getAllCompanies(domain);
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.OK).build();
        Data data = new Data.Builder(null).withResults(users).build();
        Integer pages = userDAO.countAllRecord(domain, "MODERATOR");
        Pagination pagination = new Pagination.Builder().
                withCurrentPage(domain.getPage()).
                withTotalPages((int) Math.ceil((double) pages / PER_PAGE)).
                withTotalResults(pages).build();
        return new Message.Builder(meta).withData(data).withPagination(pagination).build();
    }
}
