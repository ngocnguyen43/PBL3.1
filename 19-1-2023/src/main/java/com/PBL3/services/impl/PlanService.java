package com.PBL3.services.impl;

import com.PBL3.dtos.PlanDTO;
import com.PBL3.models.PlanModel;
import com.PBL3.repositories.IPlanRepository;
import com.PBL3.services.IPlanService;
import com.PBL3.utils.exceptions.dbExceptions.CreateFailedException;
import com.PBL3.utils.exceptions.dbExceptions.InvalidPropertiesException;
import com.PBL3.utils.exceptions.dbExceptions.NotFoundException;
import com.PBL3.utils.helpers.Helper;
import com.PBL3.utils.helpers.IDGeneration;
import com.PBL3.utils.helpers.TimestampConvert;
import com.PBL3.utils.response.Message;
import com.PBL3.utils.response.Meta;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

public class PlanService implements IPlanService {
    @Inject
    private IPlanRepository iPlanRepository;
    @Override
    public Message createOne(PlanDTO dto) {
        try{
            PlanModel domain = Helper.objectMapper(dto,PlanModel.class);
            String id = IDGeneration.generate();
            domain.setId(id);
            iPlanRepository.createOne(domain);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_CREATED).withMessage("Create Plan Successfully!").build();
            return new Message.Builder(meta).build();
        } catch (CreateFailedException | InvalidPropertiesException | NotFoundException e) {
            Meta meta = new Meta.Builder(e.getStatusCode()).withError(e.getMessage()).build();
            return new Message.Builder(meta).build();
        }
    }
}
