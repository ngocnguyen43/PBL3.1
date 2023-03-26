package com.PBL3.repositories.impls;

import com.PBL3.daos.IPlanDAO;
import com.PBL3.daos.IUserDAO;
import com.PBL3.models.PlanModel;
import com.PBL3.models.User;
import com.PBL3.repositories.IPlanRepository;
import com.PBL3.utils.exceptions.dbExceptions.CreateFailedException;
import com.PBL3.utils.exceptions.dbExceptions.InvalidPropertiesException;
import com.PBL3.utils.exceptions.dbExceptions.NotFoundException;
import com.PBL3.utils.exceptions.dbExceptions.UpdateFailedException;
import com.PBL3.utils.helpers.TimestampConvert;

import javax.inject.Inject;

public class PlanRepository implements IPlanRepository {
    @Inject
    private IPlanDAO iPlanDAO;
    @Inject
    private IUserDAO iUserDAO;
    @Override
    public void createOne(PlanModel domain) throws CreateFailedException, InvalidPropertiesException, NotFoundException {
        User user  = iUserDAO.findByCompanyId(domain.getCompanyId());
        if(user == null) throw new NotFoundException("Company not found");
        if (domain.getCompanyId() == null) throw new InvalidPropertiesException("Invalid company Id");
//        if (domain)
        domain.setTime(TimestampConvert.convert(domain.getTime().getTime()));
        try{
            iPlanDAO.createPlan(domain);
        }catch (Exception e){
            throw new CreateFailedException("Create Plan Failed");
        }
    }

    @Override
    public PlanModel findOneById(String id) throws NotFoundException {
        PlanModel plan = iPlanDAO.findOneByPlanId(id);
        if (plan == null) throw new NotFoundException("Plan Not Found");
        return plan;
    }

    @Override
    public void updateTime(PlanModel domain) throws NotFoundException, UpdateFailedException, InvalidPropertiesException {
        if (domain.getId() == null || domain.getTime() == null) throw new InvalidPropertiesException("Invalid Plan Id or Plan Time");
        PlanModel plan = iPlanDAO.findOneByPlanId(domain.getId());
        if (plan == null) throw new NotFoundException("Plan Not Found");
        domain.setTime(TimestampConvert.convert(domain.getTime().getTime()));
        try{
            iPlanDAO.updateTime(domain);
        }catch (Exception e){
            throw new UpdateFailedException("Update Plan Failed");
        }

    }

    @Override
    public void inactivePlan(String id) throws InvalidPropertiesException, UpdateFailedException {
        if (id == null ) throw new InvalidPropertiesException("Plan Id is Invalid");
        try {

         iPlanDAO.inactivePlan(id);
        }catch (Exception e){
            throw  new UpdateFailedException("Inactivve Plan Failed");
        }
    }
}
