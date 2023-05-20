package com.PBL3.services.impl;

import com.PBL3.daos.IPlanDAO;
import com.PBL3.daos.IUserDAO;
import com.PBL3.dtos.PlanDTO;
import com.PBL3.dtos.pagination.PlanPaginationDTO;
import com.PBL3.models.PlanModel;
import com.PBL3.models.User;
import com.PBL3.models.pagination.PlanPaginationModel;
import com.PBL3.services.IPlanService;
import com.PBL3.utils.exceptions.dbExceptions.CreateFailedException;
import com.PBL3.utils.exceptions.dbExceptions.InvalidPropertiesException;
import com.PBL3.utils.exceptions.dbExceptions.NotFoundException;
import com.PBL3.utils.exceptions.dbExceptions.UpdateFailedException;
import com.PBL3.utils.helpers.Helper;
import com.PBL3.utils.helpers.IDGeneration;
import com.PBL3.utils.helpers.TimestampConvert;
import com.PBL3.utils.response.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.PBL3.utils.Constants.Pagination.PER_PAGE;

public class PlanService implements IPlanService {
    @Inject
    private IPlanDAO iPlanDAO;
    @Inject
    private IUserDAO iUserDAO;


    @Override
    public Message createOne(PlanDTO dto) throws NotFoundException, InvalidPropertiesException, CreateFailedException {
        User user = iUserDAO.findByCompanyId(dto.getCompanyId());
        if (user == null) throw new NotFoundException(Response.NOT_FOUND);
        if (dto.getCompanyId() == null) throw new InvalidPropertiesException(Response.INVALID_ID);
        PlanModel domain = Helper.objectMapper(dto, PlanModel.class);
        String id = IDGeneration.generate();
        domain.setId(id);
        domain.setTime(TimestampConvert.convert(domain.getTime().getTime()));
        try {
            iPlanDAO.createPlan(domain);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_CREATED).withMessage(Response.CREATED).build();
            return new Message.Builder(meta).build();
        } catch (Exception e) {
            throw new CreateFailedException(Response.CREATE_FAILED);
        }

    }

    @Override
    public Message getOneById(String id) throws NotFoundException {
        PlanModel plan = iPlanDAO.findOneByPlanId(id);
        if (plan == null) throw new NotFoundException(Response.NOT_FOUND);
        Data data = new Data.Builder(null).withResults(plan).build();
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.OK).build();
        return new Message.Builder(meta).withData(data).build();
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
            Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.OK).build();
            return new Message.Builder(meta).build();
        } catch (Exception e) {
            throw new UpdateFailedException(Response.FAILED);
        }
    }

    @Override
    public Message inactivePlan(String id) throws InvalidPropertiesException, UpdateFailedException {
        if (id == null) throw new InvalidPropertiesException(Response.INVALID_ID);
        try {
            iPlanDAO.inactivePlan(id);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.OK).build();
            return new Message.Builder(meta).build();
        } catch (Exception e) {
            throw new UpdateFailedException(Response.FAILED);
        }
    }

    @Override
    public Message getAll(PlanPaginationDTO dto, String id) throws NotFoundException {
        PlanPaginationModel domain = Helper.objectMapper(dto, PlanPaginationModel.class);
        String role = iUserDAO.getUserRole(id);
        List<PlanModel> plans;
        Integer pages;
        if (role.equals("Admin")) {
            plans = iPlanDAO.findAll(domain);
            pages = iPlanDAO.countAll();
        } else {
            plans = iPlanDAO.findAll(domain, id);
            pages = iPlanDAO.countAll(id);
        }
        Pagination pagination = new Pagination.Builder().
                withCurrentPage(domain.getPage()).
                withTotalPages((int) Math.ceil((double) pages / PER_PAGE)).
                withTotalResults(pages).build();
        if (plans == null) throw new NotFoundException(Response.NOT_FOUND);
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.OK).build();
        Data data = new Data.Builder(null).withResults(plans).build();
        return new Message.Builder(meta).withData(data).withPagination(pagination).build();
    }
}
