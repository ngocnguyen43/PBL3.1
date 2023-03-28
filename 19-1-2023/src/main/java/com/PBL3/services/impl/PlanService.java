package com.PBL3.services.impl;

import com.PBL3.dtos.PlanDTO;
import com.PBL3.models.PlanModel;
import com.PBL3.repositories.IPlanRepository;
import com.PBL3.services.IPlanService;
import com.PBL3.utils.exceptions.dbExceptions.CreateFailedException;
import com.PBL3.utils.exceptions.dbExceptions.InvalidPropertiesException;
import com.PBL3.utils.exceptions.dbExceptions.NotFoundException;
import com.PBL3.utils.exceptions.dbExceptions.UpdateFailedException;
import com.PBL3.utils.helpers.Helper;
import com.PBL3.utils.helpers.IDGeneration;
import com.PBL3.utils.response.Data;
import com.PBL3.utils.response.Message;
import com.PBL3.utils.response.Meta;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class PlanService implements IPlanService {
    @Inject
    private IPlanRepository iPlanRepository;

    @Override
    public Message createOne(PlanDTO dto) {
        try {
            PlanModel domain = Helper.objectMapper(dto, PlanModel.class);
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

    @Override
    public Message getOneById(String id) {
        try {
            PlanModel plan = iPlanRepository.findOneById(id);
            Data data = new Data.Builder(null).withResults(plan).build();
            Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage("OK!").build();
            return new Message.Builder(meta).withData(data).build();
        } catch (NotFoundException e) {
            Meta meta = new Meta.Builder(e.getStatusCode()).withError(e.getMessage()).build();
            return new Message.Builder(meta).build();
        }
    }

    @Override
    public Message updateTime(PlanDTO dto) {
        try {
            PlanModel domain = Helper.objectMapper(dto, PlanModel.class);
            iPlanRepository.updateTime(domain);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage("OK!").build();
            return new Message.Builder(meta).build();
        } catch (NotFoundException | UpdateFailedException | InvalidPropertiesException e) {
            Meta meta = new Meta.Builder(e.getStatusCode()).withError(e.getMessage()).build();
            return new Message.Builder(meta).build();
        }
    }

    @Override
    public Message inactivePlan(String id) {
        try {
            iPlanRepository.inactivePlan(id);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage("OK!").build();
            return new Message.Builder(meta).build();
        } catch (InvalidPropertiesException | UpdateFailedException e) {
            Meta meta = new Meta.Builder(e.getStatusCode()).withError(e.getMessage()).build();
            return new Message.Builder(meta).build();
        }
    }

    @Override
    public Message getAll() {
        try {
            List<PlanModel> plans = iPlanRepository.findAll();
            Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage("OK!").build();
            Data data = new Data.Builder(null).withResults(plans).build();
            return new Message.Builder(meta).withData(data).build();
        } catch (NotFoundException e) {
            Meta meta = new Meta.Builder(e.getStatusCode()).withError(e.getMessage()).build();
            return new Message.Builder(meta).build();
        }

    }
}
