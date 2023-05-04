package com.PBL3.services.impl;

import com.PBL3.daos.IBusinessDAO;
import com.PBL3.dtos.BusinessDTO;
import com.PBL3.models.Business;
import com.PBL3.services.IBusinessService;
import com.PBL3.utils.exceptions.dbExceptions.CreateFailedException;
import com.PBL3.utils.exceptions.dbExceptions.DuplicateEntryException;
import com.PBL3.utils.exceptions.dbExceptions.UnexpectedException;
import com.PBL3.utils.helpers.Helper;
import com.PBL3.utils.helpers.IDGeneration;
import com.PBL3.utils.response.Data;
import com.PBL3.utils.response.Message;
import com.PBL3.utils.response.Meta;
import com.PBL3.utils.response.Response;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class BusinessService implements IBusinessService {

    @Inject
    private IBusinessDAO iBusinessDAO;

    @Override
    public Message createBusiness(BusinessDTO businessDTO) throws CreateFailedException, DuplicateEntryException {

        Business business = Helper.objectMapper(businessDTO, Business.class);
        Business isExisted = iBusinessDAO.findOne(business.getBusinessName());
        if (isExisted != null) throw new DuplicateEntryException(Response.BUSINESS_EXISTED);
        String id = IDGeneration.generate();
        business.setId(id);
        try {
            iBusinessDAO.save(business);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_CREATED).withMessage(Response.CREATED).build();
            return new Message.Builder(meta).build();
        } catch (Exception e) {
            throw new CreateFailedException(Response.CREATE_FAILED);
        }

    }

    @Override
    public Message getAllBusiness() throws UnexpectedException {
        List<Business> businessList;
        try {
            businessList = iBusinessDAO.findAll();
        } catch (Exception e) {
            throw new UnexpectedException();
        }
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.OK).build();
        Data data = new Data.Builder(null).withResults(businessList).build();
        return new Message.Builder(meta).withData(data).build();
    }

}
