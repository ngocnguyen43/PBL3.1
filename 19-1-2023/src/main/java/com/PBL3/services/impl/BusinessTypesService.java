package com.PBL3.services.impl;

import com.PBL3.daos.IBusinessDAO;
import com.PBL3.daos.IBusinessTypesDAO;
import com.PBL3.daos.IUserDAO;
import com.PBL3.dtos.BusinessTypesDTO;
import com.PBL3.models.Business;
import com.PBL3.models.BusinessTypes;
import com.PBL3.models.Notification;
import com.PBL3.services.IBusinessTypesService;
import com.PBL3.services.INotificationService;
import com.PBL3.utils.exceptions.dbExceptions.CreateFailedException;
import com.PBL3.utils.exceptions.dbExceptions.DuplicateEntryException;
import com.PBL3.utils.exceptions.dbExceptions.NotFoundException;
import com.PBL3.utils.helpers.Helper;
import com.PBL3.utils.helpers.IDGeneration;
import com.PBL3.utils.response.Data;
import com.PBL3.utils.response.Message;
import com.PBL3.utils.response.Meta;
import com.PBL3.utils.response.Response;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

public class BusinessTypesService implements IBusinessTypesService {

    @Inject
    private IBusinessTypesDAO iBusinessTypesDAO;
    @Inject
    private IBusinessDAO businessDAO;
    @Inject
    private IUserDAO userDAO;
    @Inject
    private INotificationService iNotificationService;

    @Override
    public Message createBusinessType(BusinessTypesDTO businessTypesDTO, String userId) throws DuplicateEntryException, NotFoundException, CreateFailedException, JsonProcessingException {
        // TODO Auto-generated method stub

        BusinessTypes businessTypes = Helper.objectMapper(businessTypesDTO, BusinessTypes.class);

        Business business = businessDAO.findOneById(businessTypes.getBusinessId());
        if (business == null)
            throw new NotFoundException(Response.BUSINESS_NOT_FOUND);
        boolean isExisted = iBusinessTypesDAO.findOne(businessTypes.getTypeName()) != null;

        if (isExisted)
            throw new DuplicateEntryException(Response.BUSINESS_TYPE_EXISTED);
        String id = IDGeneration.generate();
        businessTypes.setId(id);
        String creator = userDAO.getUserName(userId);
        Notification notification = new Notification
                .Builder(IDGeneration.generate())
                .withCreator(userId)
                .withMods(Collections.singletonList("all"))
                .withAdmin(true)
                .withMessage("New business type has been created by " + creator)
                .build();
        try {
            iBusinessTypesDAO.save(businessTypes);
            iNotificationService.create(notification);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_CREATED).withMessage(Response.CREATED).build();
            return new Message.Builder(meta).build();
        } catch (Exception e) {
            throw new CreateFailedException(Response.CREATE_FAILED);
        }
    }

    @Override
    public Message GetAllBusinessTypes() {
        List<BusinessTypes> businessTypes = iBusinessTypesDAO.FindAll();
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.OK).build();
        Data data = new Data.Builder(null).withResults(businessTypes).build();
        return new Message.Builder(meta).withData(data).build();
    }

}
