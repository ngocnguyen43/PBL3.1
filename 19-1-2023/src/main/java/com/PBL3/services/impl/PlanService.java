package com.PBL3.services.impl;

import com.PBL3.daos.IPlanDAO;
import com.PBL3.daos.IUserDAO;
import com.PBL3.dtos.PlanDTO;
import com.PBL3.models.PlanModel;
import com.PBL3.models.User;
import com.PBL3.services.IPlanService;
import com.PBL3.utils.exceptions.dbExceptions.CreateFailedException;
import com.PBL3.utils.exceptions.dbExceptions.InvalidPropertiesException;
import com.PBL3.utils.exceptions.dbExceptions.NotFoundException;
import com.PBL3.utils.exceptions.dbExceptions.UpdateFailedException;
import com.PBL3.utils.helpers.Helper;
import com.PBL3.utils.helpers.IDGeneration;
import com.PBL3.utils.helpers.TimestampConvert;
import com.PBL3.utils.response.Data;
import com.PBL3.utils.response.Message;
import com.PBL3.utils.response.Meta;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class PlanService implements IPlanService {
    @Inject
    private IPlanDAO iPlanDAO;
    @Inject
    private IUserDAO iUserDAO;

    @Override
    public Message createOne(PlanDTO dto) throws NotFoundException, InvalidPropertiesException, CreateFailedException {
        User user = iUserDAO.findByCompanyId(dto.getCompanyId());
        if (user == null) throw new NotFoundException("Company not found");
        if (dto.getCompanyId() == null) throw new InvalidPropertiesException("Invalid company Id");
        PlanModel domain = Helper.objectMapper(dto, PlanModel.class);
        String id = IDGeneration.generate();
        domain.setId(id);
        domain.setTime(TimestampConvert.convert(domain.getTime().getTime()));
        try {
            iPlanDAO.createPlan(domain);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_CREATED).withMessage("Create Plan Successfully!").build();
            return new Message.Builder(meta).build();
        } catch (Exception e) {
            throw new CreateFailedException("Create Plan Failed!");
        }

    }

    @Override
    public Message getOneById(String id) throws NotFoundException {
        PlanModel plan = iPlanDAO.findOneByPlanId(id);
        if (plan == null) throw new NotFoundException("Plan Not Found");
        Data data = new Data.Builder(null).withResults(plan).build();
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage("OK!").build();
        return new Message.Builder(meta).withData(data).build();
//        try {
//        } catch (NotFoundException e) {
//            Meta meta = new Meta.Builder(e.getStatusCode()).withError(e.getMessage()).build();
//            return new Message.Builder(meta).build();
//        }
    }

    @Override
    public Message updateTime(PlanDTO dto) throws InvalidPropertiesException, NotFoundException, UpdateFailedException {
        PlanModel domain = Helper.objectMapper(dto, PlanModel.class);
        if (domain.getId() == null || domain.getTime() == null)
            throw new InvalidPropertiesException("Invalid Plan Id or Plan Time");
        PlanModel plan = iPlanDAO.findOneWithoutJoin(domain.getId());
        if (plan == null) throw new NotFoundException("Plan Not Found");
        domain.setTime(TimestampConvert.convert(domain.getTime().getTime()));
        try {
            iPlanDAO.updateTime(domain);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage("OK!").build();
            return new Message.Builder(meta).build();
        } catch (Exception e) {
            throw new UpdateFailedException("Update Plan Failed!");
        }
    }

    @Override
    public Message inactivePlan(String id) throws InvalidPropertiesException, UpdateFailedException {
        if (id == null) throw new InvalidPropertiesException("Plan Id is Invalid");
        try {
            iPlanDAO.inactivePlan(id);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage("OK!").build();
            return new Message.Builder(meta).build();
        } catch (Exception e) {
            throw new UpdateFailedException("Inactive Plan Failed");
        }
    }

    @Override
    public Message getAll() throws NotFoundException {
        List<PlanModel> plans = iPlanDAO.findAll();
        if (plans == null) throw new NotFoundException("Plans Not Found");
//            List<PlanModel> plans = iPlanRepository.findAll();
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage("OK!").build();
        Data data = new Data.Builder(null).withResults(plans).build();
        return new Message.Builder(meta).withData(data).build();
//        try {
//        } catch (NotFoundException e) {
//            Meta meta = new Meta.Builder(e.getStatusCode()).withError(e.getMessage()).build();
//            return new Message.Builder(meta).build();
//        }

    }
}
