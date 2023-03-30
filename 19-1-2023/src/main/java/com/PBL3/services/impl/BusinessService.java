package com.PBL3.services.impl;

import com.PBL3.daos.IBusinessDAO;
import com.PBL3.dtos.BusinessDTO;
import com.PBL3.models.Business;
import com.PBL3.services.IBusinessService;
import com.PBL3.utils.exceptions.dbExceptions.CreateFailedException;
import com.PBL3.utils.exceptions.dbExceptions.DuplicateEntryException;
import com.PBL3.utils.helpers.Helper;
import com.PBL3.utils.helpers.IDGeneration;
import com.PBL3.utils.response.Message;
import com.PBL3.utils.response.Meta;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

public class BusinessService implements IBusinessService {

    @Inject
    private IBusinessDAO iBusinessDAO;

    @Override
    public Message createBusiness(BusinessDTO businessDTO) throws CreateFailedException, DuplicateEntryException {

        Business business = Helper.objectMapper(businessDTO, Business.class);
        Business isExisted = iBusinessDAO.findOne(business.getBusinessName());
        if (isExisted != null) throw new DuplicateEntryException("Business Name has already existed");
        String id = IDGeneration.generate();
        business.setId(id);
        try {
            iBusinessDAO.save(business);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_CREATED).withMessage("Created Successfully").build();
            return new Message.Builder(meta).build();
        } catch (Exception e) {
            throw new CreateFailedException("Create New Business Failed");
        }

    }

}
