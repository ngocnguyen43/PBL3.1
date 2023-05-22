package com.PBL3.services.impl;

import com.PBL3.daos.ICompanyDAO;
import com.PBL3.models.User;
import com.PBL3.services.ICompanyService;
import com.PBL3.utils.response.Data;
import com.PBL3.utils.response.Message;
import com.PBL3.utils.response.Meta;
import com.PBL3.utils.response.Response;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CompanyService implements ICompanyService {
    @Inject
    private ICompanyDAO companyDAO;

    @Override
    public Message getAllcompanies() {
        List<User> users = companyDAO.getAllCompanies();
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.OK).build();
        Data data = new Data.Builder(null).withResults(users).build();
        return new Message.Builder(meta).withData(data).build();
    }
}
