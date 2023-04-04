package com.PBL3.services.impl;

import com.PBL3.daos.IBusinessDAO;
import com.PBL3.daos.IBusinessTypesDAO;
import com.PBL3.dtos.BusinessTypesDTO;
import com.PBL3.models.Business;
import com.PBL3.models.BusinessTypes;
import com.PBL3.services.IBusinessTypesService;
import com.PBL3.utils.exceptions.dbExceptions.CreateFailedException;
import com.PBL3.utils.exceptions.dbExceptions.DuplicateEntryException;
import com.PBL3.utils.exceptions.dbExceptions.NotFoundException;
import com.PBL3.utils.helpers.Helper;
import com.PBL3.utils.helpers.IDGeneration;
import com.PBL3.utils.response.Message;
import com.PBL3.utils.response.Meta;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

public class BusinessTypesService implements IBusinessTypesService {

    @Inject
    private IBusinessTypesDAO iBusinessTypesDAO;
    @Inject
    private IBusinessDAO businessDAO;

    @Override
    public Message createBusinessType(BusinessTypesDTO businessTypesDTO) throws DuplicateEntryException, NotFoundException, CreateFailedException, JsonProcessingException {
        // TODO Auto-generated method stub

        BusinessTypes businessTypes = Helper.objectMapper(businessTypesDTO, BusinessTypes.class);

        Business business = businessDAO.findOneById(businessTypes.getBusinessId());
        if (business == null)
            throw new NotFoundException("Not Found Business to Create Type");
        boolean isExisted = iBusinessTypesDAO.findOne(businessTypes.getTypeName()) != null;

        if (isExisted)
            throw new DuplicateEntryException("Business Type has already existed");
        String id = IDGeneration.generate();
        businessTypes.setId(id);
        try {
            iBusinessTypesDAO.save(businessTypes);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_CREATED).withMessage("Created Successfully!").build();
            return new Message.Builder(meta).build();
        } catch (Exception e) {
            throw new CreateFailedException("Create new Business Type Failed!");
        }


    }

}
