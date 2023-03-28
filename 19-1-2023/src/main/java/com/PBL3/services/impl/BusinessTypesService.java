package com.PBL3.services.impl;

import com.PBL3.dtos.BusinessTypesDTO;
import com.PBL3.models.BusinessTypes;
import com.PBL3.repositories.IBusinessTypesRepository;
import com.PBL3.services.IBusinessTypesService;
import com.PBL3.utils.exceptions.dbExceptions.CreateFailedException;
import com.PBL3.utils.exceptions.dbExceptions.DuplicateEntryException;
import com.PBL3.utils.exceptions.dbExceptions.NotFoundException;
import com.PBL3.utils.helpers.Helper;
import com.PBL3.utils.helpers.IDGeneration;
import com.PBL3.utils.response.Message;
import com.PBL3.utils.response.Meta;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

public class BusinessTypesService implements IBusinessTypesService {

    @Inject
    private IBusinessTypesRepository businessTypesRepository;

    @Override
    public Message createBusinessType(BusinessTypesDTO businessTypesDTO) {
        // TODO Auto-generated method stub
        try {
            BusinessTypes businessTypes = Helper.objectMapper(businessTypesDTO, BusinessTypes.class);
            String id = IDGeneration.generate();
            businessTypes.setId(id);
            businessTypesRepository.createBusinessType(businessTypes);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_CREATED).withMessage("Created Successfully").build();
            return new Message.Builder(meta).build();
        } catch (NotFoundException | DuplicateEntryException | CreateFailedException e) {
            Meta meta = new Meta.Builder(e.getStatusCode()).withErrCode(e.getErrorCode()).withError(e.getMessage())
                    .build();
            return new Message.Builder(meta).build();
        } catch (Exception e) {
            // TODO: handle exception
            Meta meta = new Meta.Builder(500).withError(e.getMessage()).build();
            return new Message.Builder(meta).build();
        }

    }

}
